import pandas as pd
import matplotlib.pyplot as plt
import os
import math
from model_product import Product
from operation_customer import CustomerOperation
from io_interface import IOInterface

customer = CustomerOperation()
io = IOInterface()


class ProductOperation:

    @staticmethod
    def extract_products_from_files():
        """
        Extracts the product information from the product data files and
        stores them into the product text file.

        Args:
            No arguments

        Returns:
            None
        """
        attributes = ["id", "model", "category", "name", "current_price",
                      "raw_price", "discount", "likes_count"]
        csv_files = os.listdir("data/product")

        with open("data/products.txt", "a", encoding="utf8") as file:
            for csv_file in csv_files:
                file_path = os.path.join("data/product", csv_file)
                df = pd.read_csv(file_path, encoding="utf8")

                for index, row in df.iterrows():
                    product = Product()

                    for column in df.columns:
                        for attribute in attributes:
                            if attribute == column:
                                setattr(product, f"pro_{attribute}",
                                        row[column])
                    file.writelines(str(product))

    def get_product_list(self, page_number):
        """
        This method retrieves one page of products from the products database.

        Args:
            page_number: the specified page number

        Returns:
            a tuple of product objects, page number and total pages
        """
        if type(page_number) == int:
            with open("data/products.txt", "r", encoding="utf8") as file:
                file_lines = file.readlines()
            temp_products = []

            for line in file_lines:
                line = line.strip("{}")
                temp_products.append(line.split("pro_"))

            products_list = []
            for line in temp_products:
                key_values = self.key_vals(line)
                new_product = self.set_product(key_values)
                products_list.append(new_product)

            customer_count = len(products_list)
            total_pages = math.ceil(customer_count / 10)
            if 0 < page_number <= total_pages:
                specific_page = customer.page_creation(products_list,
                                                       page_number)
                return specific_page, page_number, total_pages
            else:
                io.print_error_message("ProductOperation."
                                       "get_product_list",
                                       "specified page number "
                                       "does not exist")
                return [], 0, 0
        else:
            io.print_error_message("ProductOperation."
                                   "get_product_list",
                                   "page number input must "
                                   "be an integer value")
            return [], 0, 0

    def delete_product(self, product_id):
        """
        This method can delete the product info from the product text file
        based on the provided product_id.

        Args:
            product_id: the product's unique id

        Returns:
            a boolean value indicating the success of deleting a product
        """
        with open("data/products.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()

        temp_lines = []
        for line in file_lines:
            line = line.strip("{}")
            temp_lines.append(line.split("pro_"))

        flag_found = False
        obj_list = []
        for line in temp_lines:
            key_values = self.key_vals(line)
            if not key_values["id"] == product_id:
                new_product = self.set_product(key_values)
                obj_list.append(str(new_product))
            else:
                flag_found = True

        if flag_found:
            with open("data/products.txt", "w", encoding="utf8") as file:
                file.writelines(obj_list)
            return True
        else:
            io.print_error_message("ProductOperation."
                                   "delete_product",
                                   "no matching product "
                                   "id was found")
            return False

    def get_product_list_by_keyword(self, keyword):
        """
        Retrieves all the products whose name contains the keyword
        (case-insensitive).

        Args:
            keyword: the specified keyword to search by

        Returns:
            a list of product objects found
        """
        with open("data/products.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()
        temp_products = []
        for line in file_lines:
            line = line.strip("{}")
            temp_products.append(line.split("pro_"))

        products_list = []
        for line in temp_products:
            key_values = self.key_vals(line)

            if keyword.lower() in str(key_values.get("name", "")).lower():
                new_product = self.set_product(key_values)
                products_list.append(new_product)

        if len(products_list) == 0:
            io.print_error_message("ProductOperation."
                                   "get_product_list_by_keyword",
                                   "no matches found")
        return products_list

    def get_product_by_id(self, product_id):
        """
        Searches and returns one product object based on the specified.

        Args:
            product_id: the product's unique identifier

        Returns:
            a product object or none if it cannot be found
        """
        with open("data/products.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()

        temp_products = []
        for line in file_lines:
            line = line.strip("{}")
            temp_products.append(line.split("pro_"))

        for line in temp_products:
            key_values = self.key_vals(line)

            if product_id == key_values.get("id", ""):
                new_product = self.set_product(key_values)
                return new_product
        io.print_error_message("ProductOperation."
                               "get_product_by_id",
                               "no matches found")

    def generate_category_figure(self):
        """
        Creates a bar chart that shows the total number of products for
        each category in descending order.

        Args:
            No arguments

        Returns:
            none
        """
        with open("data/products.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()

        temp_products = []
        for line in file_lines:
            line = line.strip("{}")
            temp_products.append(line.split("pro_"))

        categories = []
        for line in temp_products:
            key_values = self.key_vals(line)
            categories.append(key_values.get("category", "default_category"))

        category_counts = (pd.Series(categories).value_counts().
                           sort_values(ascending=False))
        category_counts.plot(kind="bar")
        plt.title("Total Number of Products Grouped by Category")
        plt.xlabel("Category")
        plt.ylabel("Total Number of Products")
        plt.tight_layout()
        plt.savefig("data/figure/generate_category_figure.png")
        plt.close()
        io.print_message("figure created and saved "
                         "@data/figure/generate_category_figure.png")

    def generate_discount_figures(self):
        """
        Creates a pie chart that shows the proportion of products that
        have a discount value less than 30, between 30 and 60
        inclusive, and greater than 60.

        Args:
            No arguments

        Returns:
            none
        """
        with open("data/products.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()

        temp_products = []
        for line in file_lines:
            line = line.strip("{}")
            temp_products.append(line.split("pro_"))

        discounts = []
        for line in temp_products:
            key_values = self.key_vals(line)
            discounts.append(int(key_values.get("discount", 0)))

        count_29 = 0
        count_30_60 = 0
        count_61 = 0

        for item in discounts:
            if item < 30:
                count_29 += 1
            elif 30 <= item <= 60:
                count_30_60 += 1
            elif item > 60:
                count_61 += 1

        labels = ['Discount < 30%', 'Discount 30-60%', 'Discount > 60%']
        sizes = [count_29, count_30_60, count_61]
        plt.pie(sizes, labels=labels)
        plt.title("Discount Proportion of Products by Range Conditions")
        plt.tight_layout()
        plt.savefig("data/figure/generate_discount_figures.png")
        plt.close()
        io.print_message("figure created and saved "
                         "@data/figure/generate_discount_figures.png")

    def generate_likes_count_figure(self):
        """
        Creates a generates a bar chart displaying the sum of products’
        likes_count for each category in ascending order.

        Args:
            No arguments

        Returns:
            none
        """
        df = self.create_data_frame()
        df['pro_likes_count'] = pd.to_numeric(df['pro_likes_count'])
        category_likes = (df.groupby('pro_category')['pro_likes_count'].
                          sum().reset_index())
        category_likes = category_likes.sort_values(by='pro_likes_count',
                                                    ascending=True)

        plt.figure(figsize=(8, 6))
        plt.bar(category_likes['pro_category'],
                category_likes['pro_likes_count'])
        plt.title("Total Likes Count Grouped by Category")
        plt.xlabel("Category")
        plt.ylabel("Total Likes Count")
        plt.tight_layout()
        plt.savefig("data/figure/generate_likes_count_figure.png")
        plt.close()
        io.print_message("figure created and saved "
                         "@data/figure/generate_likes_count_figure.png")

    def generate_discount_likes_count_figure(self):
        """
        Creates a generates a scatter chart showing the relationship
        between likes_count and discount for all products.

        Args:
            No arguments

        Returns:
            none
        """
        df = self.create_data_frame()
        df['pro_discount'] = pd.to_numeric(df['pro_discount'])
        df['pro_likes_count'] = pd.to_numeric(df['pro_likes_count'])

        plt.figure(figsize=(30, 20))
        plt.scatter(df['pro_discount'].sort_values(ascending=True),
                    df['pro_likes_count'])
        plt.title("Scatter Between Likes Count and Discount")
        plt.xlabel("Discount (%)")
        plt.ylabel("Likes Count")
        plt.tight_layout()
        plt.savefig("data/figure/generate_discount_likes_count_figure.png")
        plt.close()
        io.print_message("figure created and saved "
                         "@data/figure/generate_discount_"
                         "likes_count_figure.png")

    @staticmethod
    def delete_all_products():
        """
        Deletes all products within the product text file.

        Args:
            No arguments

        Returns:
            none
        """
        with open("data/products.txt", "w", encoding="utf8") as file:
            file.writelines("")
        io.print_message("all products deleted successfully")

    @staticmethod
    def key_vals(line):
        """
        Extract key-value pairs from a string and store them in a dictionary.

        Args:
            line: a string containing the key-value pair

        Returns:
            key_values: a dictionary of the extracted key values
        """
        count = 0
        key_values = {}

        for val in line:
            if count > 0:
                end_remove = val[:-3]
                temp_dict = end_remove.split(":")
                key = temp_dict[0].strip("'")
                value = temp_dict[1].strip("'")
                key_values[key] = value
            count += 1
        return key_values

    @staticmethod
    def set_product(key_values):
        """
        Creates and sets the new values of a product based on the
        values provided.

        Args:
            key_values: a dictionary containing the products values

        Returns:
            new_product: a product object with the updated values
        """
        new_product = Product()
        new_product.pro_id = key_values.get("id", "default_id")
        new_product.pro_model = key_values.get("model", "default_model")
        new_product.pro_category = key_values.get("category",
                                                  "default_category")
        new_product.pro_name = key_values.get("name", "default_name")
        new_product.pro_current_price = key_values.get("current_price", 0.0)
        new_product.pro_raw_price = key_values.get("raw_price", 0.0)
        new_product.pro_discount = key_values.get("discount", 0)
        new_product.pro_likes_count = key_values.get("likes_count", 0)
        return new_product

    def create_data_frame(self):
        """
        Creates a dataframe from the products text file.

        Args:
            No arguments

        Returns:
            df: a dataframe created from the products text file
        """
        with open("data/products.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()

        temp_lines = []
        for line in file_lines:
            line = line.strip("{}")
            temp_lines.append(line.split("pro_"))

        obj_list = []
        for line in temp_lines:
            key_values = self.key_vals(line)
            new_product = self.set_product(key_values)
            obj_list.append(new_product)

        rows = []
        for entry in obj_list:
            dict_entry = vars(entry)
            rows.append(dict_entry)
        df = pd.DataFrame(rows)
        return df
