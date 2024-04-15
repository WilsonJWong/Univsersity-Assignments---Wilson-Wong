import random
import string
import time
import os
import matplotlib.pyplot as plt
import pandas as pd
import math
from model_order import Order
from opreation_product import ProductOperation
from operation_customer import CustomerOperation
from operation_user import UserOperation
from io_interface import IOInterface

product = ProductOperation()
customer = CustomerOperation()
user = UserOperation()
io = IOInterface()


class OrderOperation:

    @staticmethod
    def generate_unique_order_id():
        """
        Create a 5 digit unique order id starting with o_

        Args:
            No arguments
        Returns:
            random_id: the randomly created order id
        """
        random_id = ""
        flag_check = False
        while not flag_check:
            random_num = random.randint(10000, 99999)
            random_id = "o_" + str(random_num)
            file_handle = open("data/orders.txt", "r", encoding="utf8")

            if file_handle:
                flag_check = True

            for line in file_handle:
                clean_id = line.split(", ")[0].split(":")[1].strip("'")
                if clean_id == random_id:
                    break
                flag_check = True
        return random_id

    def create_an_order(self, customer_id, product_id, create_time=None):
        """
        Create a new order based on the provided inputs. If the create_time
        argument is not provided, the current time is used instead.

        Args:
            customer_id: the user's unique customer id
            product_id: the unique id of the product
            create_time: the time the order was created
        Returns:
            a boolean value determining if an order was created successfully
        """
        if create_time is None:
            create_time = time.strftime("%d-%m-%Y_%H:%M:%S")
        else:
            try:
                time.strptime(create_time, "%d-%m-%Y_%H:%M:%S")
            except Exception as e:
                print(f"An {e} error has occurred, creating new time")
                create_time = time.strftime("%d-%m-%Y_%H:%M:%S")

        with open("data/users.txt", "r", encoding="utf8") as users:
            file_lines = users.readlines()
        user_id_check = False
        for line in file_lines:
            user_id_file = line.split(",")
            if customer_id in user_id_file[0]:
                user_id_check = True
        df = product.create_data_frame()

        pro_id_check = False
        if product_id in df['pro_id'].values:
            pro_id_check = True

        if user_id_check is True and pro_id_check is True:
            new_order = Order()
            new_order.order_id = self.generate_unique_order_id()
            new_order.user_id = customer_id
            new_order.pro_id = product_id
            new_order.order_time = create_time

            with open("data/orders.txt", "a", encoding="utf8") as file:
                file.writelines(str(new_order))
            return True
        else:
            io.print_error_message("OrderOperation.create_an_order",
                                   "either or both IDs do not exist")
            return False

    def delete_order(self, order_id):
        """
        Deletes an order in the order file based on the specified order id

        Args:
            order_id: the unique id of the order
        Returns:
            boolean indicating if the order was deleted successfully
        """
        if not os.path.getsize("data/orders.txt") == 0:
            df = self.create_data_frame()
            temp_orders = []
            if order_id in df['order_id'].values:
                for index, row in df.iterrows():
                    if not order_id == row['order_id']:
                        new_order = Order()
                        new_order.order_id = row['order_id']
                        new_order.user_id = row['user_id']
                        new_order.pro_id = row['pro_id']
                        new_order.order_time = row['order_time']
                        temp_orders.append(str(new_order))
                with open("data/orders.txt", "w", encoding="utf8") as file:
                    file.writelines(temp_orders)
                return True
            else:
                io.print_error_message("OrderOperation.delete_order",
                                       "no matching order id was found")
                return False
        else:
            io.print_error_message("OrderOperation.delete_order",
                                   "no orders exist to be deleted")
            return False

    def get_order_list(self, customer_id, page_number):
        """
        Retrieves one specified page of orders from the order file

        Args:
            customer_id: the customers unique id
            page_number: the page number
        Returns:
            a tuple of order objects, page number and total pages
        """
        df = self.create_data_frame()

        temp_orders = []
        if customer_id in df['user_id'].values:
            for index, row in df.iterrows():
                if customer_id == row['user_id']:
                    new_order = Order()
                    new_order.order_id = row['order_id']
                    new_order.user_id = row['user_id']
                    new_order.pro_id = row['pro_id']
                    new_order.order_time = row['order_time']
                    temp_orders.append(new_order)

        order_count = len(temp_orders)
        total_pages = math.ceil(order_count / 10)
        if 0 < page_number <= total_pages:
            specific_page = customer.page_creation(temp_orders, page_number)
            return specific_page, page_number, total_pages
        else:
            io.print_error_message("OrderOperation.get_order_list",
                                   "specified customer has "
                                   "no orders or page number does not exist")
            return [], 0, 0

    def generate_test_order_data(self):
        """
        Creates a set of test data. 10 new users, each with 50 to 200 orders.

        Args:
            No arguments
        Returns:
            none
        """
        if not os.path.getsize("data/products.txt") == 0:
            for num in range(10):
                user_name = ""
                flag_name = False
                while not flag_name:
                    user_name = "".join(random.choice(string.ascii_letters)
                                        for _ in range(5))
                    if not user.check_username_exist(user_name):
                        flag_name = True
                user_password = "".join(random.choice(string.ascii_letters)
                                        for _ in range(4))
                user_password = user_password + (str(random.randint(0, 9)))
                user_email = user_name + "@" + user_name + ".com"
                user_mobile = "04" + str(random.randint(10000000, 99999999))
                customer.register_customer(user_name, user_password, user_email,
                                           user_mobile)

                name_check = "'user_name':'" + user_name + "'"
                customer_id = ""
                file_handle = open("data/users.txt", "r", encoding="utf8")
                for line in file_handle:
                    temp_check = line.split(", ")
                    if temp_check[1] == name_check:
                        customer_uncleaned = temp_check[0].split(":")
                        customer_id = customer_uncleaned[1].strip(" ").strip("'")

                df = product.create_data_frame()
                range_products = df.shape[0]
                test_range = random.randint(50, 200)
                for numb in range(test_range):
                    reference = random.randint(0, (range_products - 1))
                    random_line = df.iloc[reference]
                    random_product = random_line["pro_id"]

                    year = 2023
                    month = random.randint(1, 12)
                    if month == 2:
                        day = random.randint(1, 28)
                    elif (month == 1 or month == 3
                          or month == 5 or month == 7
                          or month == 8 or month == 10 or month == 12):
                        day = random.randint(1, 31)
                    else:
                        day = random.randint(1, 30)
                    hour = random.randint(0, 23)
                    minute = random.randint(0, 59)
                    second = random.randint(0, 59)
                    order_time = (f"{day:02d}-{month:02d}-{year}_{hour:02d}:"
                                  f"{minute:02d}:{second:02d}")
                    self.create_an_order(customer_id, random_product, order_time)
            io.print_message("test data generated successfully")
        else:
            io.print_error_message("OrderOperation."
                                   "generate_test_order_data",
                                   "no products exist")

    def generate_single_customer_consumption_figure(self, customer_id):
        """
        Creates a graph of consumption over 12 different months
        for a given customer.

        Args:
            customer_id: a customer's unique id
        Returns:
            none
        """
        df_o = self.create_data_frame()
        temp_orders = []
        if (not os.path.getsize("data/orders.txt") == 0
                and customer_id in df_o['user_id'].values):
            for index_o, row_o in df_o.iterrows():
                if customer_id == row_o['user_id']:
                    temp_orders.append(self.
                                       get_price_month(row_o['pro_id'],
                                                       row_o['order_time']))
            price_by_month = self.sum_price_month(temp_orders)
            plt.bar(price_by_month['month'],
                    price_by_month['pro_current_price'])
            plt.title(f"Total Product Current Price of {customer_id} "
                      f"Grouped by Month")
            plt.xlabel("Month")
            plt.ylabel("Total Product Current Price")
            plt.tight_layout()
            plt.savefig("data/figure/generate_single_customer_"
                        "consumption_figure.png")
            plt.close()
            io.print_message("figure created and saved "
                             "@data/figure/generate_single_customer_"
                             "consumption_figure.png")
        else:
            io.print_error_message("OrderOperation."
                                   "generate_single_customer_"
                                   "consumption_figure",
                                   "no matching customer id "
                                   "was found")

    def generate_all_customers_consumption_figure(self):
        """
        Creates a graph of consumption over 12 different months
        for all customers.

        Args:
            No arguments
        Returns:
            none
        """
        df_o = self.create_data_frame()
        temp_orders = []
        if not os.path.getsize("data/orders.txt") == 0:
            for index_o, row_o in df_o.iterrows():
                temp_orders.append(self.get_price_month(row_o['pro_id'],
                                                        row_o['order_time']))
                price_by_month = self.sum_price_month(temp_orders)
                plt.bar(price_by_month['month'],
                        price_by_month['pro_current_price'])
                plt.title("Total Product Current Price of "
                          "All Customers Grouped by Category")
                plt.xlabel("Month")
                plt.ylabel("Total Product Current Price")
                plt.tight_layout()
                plt.savefig("data/figure/"
                            "generate_all_customers_consumption_figure.png")
                plt.close()
            io.print_message("figure created and saved "
                             "@data/figure/generate_all_"
                             "customers_consumption_figure.png")
        else:
            io.print_error_message("OrderOperation."
                                   "generate_all_customers_consumption_figure",
                                   "no orders exist")

    def generate_all_top_10_best_sellers_figure(self):
        """
        Creates a graph of the top 10 best-selling products.

        Args:
            No arguments
        Returns:
            none
        """
        df_o = self.create_data_frame()
        if not os.path.getsize("data/orders.txt") == 0:
            pro_count = (df_o.groupby('pro_id').
                         size().reset_index(name='count'))
            pro_count = pro_count.sort_values(by='count', ascending=False)
            if pro_count.shape[0] > 10:
                pro_count = pro_count.head(10)
            plt.figure(figsize=(20, 10))
            plt.bar(pro_count['pro_id'], pro_count['count'])
            plt.title("Top 10 Best Selling Products")
            plt.xlabel("Product_ID")
            plt.ylabel("Number of Times Sold")
            plt.tight_layout()
            plt.savefig("data/figure/"
                        "generate_all_top_10_best_sellers_figure.png")
            plt.close()
            io.print_message("figure created and saved @data/figure/"
                             "generate_all_top_10_best_sellers_figure.png")
        else:
            io.print_error_message("OrderOperation."
                                   "generate_all_top_10_best_sellers_figure",
                                   "no orders exist")

    @staticmethod
    def delete_all_orders():
        """
        Deletes all order data in the orders file

        Args:
            No arguments
        Returns:
            none
        """
        with open("data/orders.txt", "w", encoding="utf8") as file:
            file.writelines("")
        io.print_message("all orders deleted successfully")

    @staticmethod
    def create_data_frame():
        """
        Creates a data frame based on the data in the orders file.

        Args:
            No arguments
        Returns:
            df: a data frame of the orders
        """
        with open("data/orders.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()

        temp_lines = []
        for line in file_lines:
            line = line.strip("\n").strip("{}")
            temp_lines.append(line.split(","))

        obj_list = []
        for line in temp_lines:

            key_values = {}
            for val in line:
                temp_dict = val.split(":")
                key = temp_dict[0].strip(" ").strip("'")
                value = temp_dict[1].strip("'")
                key_values[key] = value

            new_order = Order()
            new_order.order_id = key_values.get("order_id", "default_id")
            new_order.user_id = key_values.get("user_id", "default_id")
            new_order.pro_id = key_values.get("pro_id", "default_id")
            new_order.order_time = (key_values.get
                                    ("order_time",
                                     time.strftime("%d-%m-%Y_%H:%M:%S")))
            obj_list.append(new_order)

        rows = []
        for entry in obj_list:
            dict_entry = vars(entry)
            rows.append(dict_entry)
        df = pd.DataFrame(rows)
        return df

    @staticmethod
    def get_price_month(o_pro_id, o_order_time):
        """
        Finds the current price and month of a product based on
        its ID and the order time.

        Args:
            o_pro_id: the product id of the order
            o_order_time: the month in which the order occurred
        Returns:
            pro_time: a list containing the current price and the month number
            of when it was ordered
        """
        pro_time = []
        pro_id = o_pro_id
        order_time = o_order_time

        date_s, time_s = order_time.split("_")
        day, month, year = date_s.split("-")
        month_num = int(month)

        df_pro = product.create_data_frame()
        for index_pro, row_pro in df_pro.iterrows():
            if pro_id == row_pro['pro_id']:
                pro_current_price = float(row_pro['pro_current_price'])
                pro_time = [pro_current_price, month_num]
        return pro_time

    @staticmethod
    def sum_price_month(temp_orders):
        """
        Calculates the total current price by month.

        Args:
            temp_orders: a list of the order's current price and month
        Returns:
            price_by_month: a data frame of the summed current price by month
        """
        columns = ["pro_current_price", "month"]
        df_p_m = pd.DataFrame(temp_orders, columns=columns)
        df_p_m["pro_current_price"] = (pd.to_numeric
                                       (df_p_m["pro_current_price"]))
        df_p_m["month"] = pd.to_numeric(df_p_m["month"])

        price_by_month = (df_p_m.groupby('month')['pro_current_price'].
                          sum().reset_index())
        return price_by_month
