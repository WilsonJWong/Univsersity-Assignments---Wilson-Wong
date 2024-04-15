"""
Name: Wilson Wong
Student ID: 29704154
File creation date: 01 Oct 2023
Last modified date: 08 Oct 2023
Description of program: An e-commerce system for shopping operations
"""

import os
from operation_user import UserOperation
from operation_customer import CustomerOperation
from operation_admin import AdminOperation
from opreation_product import ProductOperation
from operation_order import OrderOperation
from io_interface import IOInterface

u_op = UserOperation()
c_op = CustomerOperation()
a_op = AdminOperation()
p_op = ProductOperation()
o_op = OrderOperation()
io = IOInterface()

customer_account = ""


def login_control():
    """
    Manages the user login and navigation in the system.
    Users can choose to log in as an existing customer
    or administrator, create a new customer account, or exit the system.

    Global Variables:
        customer_account (str):
            Stores the customer's account information after successful login.

    Args:
        No arguments

    Returns:
        None
    """
    global customer_account
    flag_exit = False
    while not flag_exit:
        io.main_menu()
        main_choice = io.get_user_input("Please enter one of "
                                        "the choices above", 1)
        choice = main_choice[0]
        if choice == "1":
            user_name = io.get_user_input("Enter user name:",
                                          1)[0]
            password = io.get_user_input("Enter password:",
                                         1)[0]
            user_account = u_op.login(user_name, password)
            clean_account = u_op.clean_line(str(user_account))
            if user_name == clean_account[1]:
                if clean_account and clean_account[4] == "customer":
                    customer_account = user_account
                    customer_control()
                elif clean_account and clean_account[4] == "admin":
                    admin_control()
        elif choice == "2":
            add_customer()
        elif choice == "3":
            io.print_message("system exited successfully")
            flag_exit = True
        else:
            io.print_error_message("main.login_control",
                                   "selection must be "
                                   "1 or 2 or 3")


def customer_control():
    """
    Manages the customer actions and navigation in the system.

    Args:
        No arguments

    Returns:
        None
    """
    clean_account = u_op.clean_line(str(customer_account))
    flag_exit = False
    while not flag_exit:
        io.customer_menu()
        customer_choice = io.get_user_input("Please enter one of "
                                            "the choices above", 1)
        customer_choice = customer_choice[0]

        if customer_choice == "1":  # Show profile
            io.print_object(customer_account)
        elif customer_choice == "2":  # Update profile
            cust_input = (io.get_user_input("Enter name of the "
                                            "attribute and the value:",
                                            2))
            update_success = c_op.update_profile(cust_input[0],
                                                 cust_input[1],
                                                 customer_account)
            if update_success:
                io.print_message(f"attribute {cust_input[0]} successfully "
                                 f"update to {cust_input[1]}")
        elif customer_choice == "3":  # Show products
            show_products(clean_account[4])
        elif customer_choice == "4":  # Show history orders
            try:
                if not os.path.getsize("data/orders.txt") == 0:
                    cust_input = (io.get_user_input("Enter page "
                                                    "number you wish to view",
                                                    1))
                    page_num = cust_input[0] if cust_input[0] else 1
                    order_list = o_op.get_order_list(clean_account[0],
                                                     int(page_num))
                    io.show_list(clean_account[4],
                                 "Order",
                                 order_list)
                else:
                    io.print_error_message("main.customer_control",
                                           "no order history "
                                           "exists")
            except Exception as e:
                io.print_error_message("main.customer_control", e)
        elif customer_choice == "5":  # Generate all consumption figures
            io.print_message("statistical data is generating - "
                             "thank you for your patience")
            o_op.generate_all_customers_consumption_figure()
            o_op.generate_single_customer_consumption_figure(clean_account[0])
        elif customer_choice == "6":  # Logout
            io.print_message("successfully logged out")
            flag_exit = True


def admin_control():
    """
    Manages the admins actions and navigation in the system.

    Args:
        No arguments

    Returns:
        None
    """
    flag_exit = False
    while not flag_exit:
        io.admin_menu()
        admin_choice = io.get_user_input("Please enter one of the "
                                         "choices above", 1)
        admin_choice = admin_choice[0]
        if admin_choice == "1":  # Show products
            show_products("admin")
        elif admin_choice == "2":  # Add customer
            if add_customer():
                io.print_message("customer account created successfully")
        elif admin_choice == "3":  # Show customers
            flag_list = False
            while not flag_list:
                try:
                    admin_input = io.get_user_input("Enter the page "
                                                    "number you wish "
                                                    "to view:",
                                                    1)
                    page_num = admin_input[0] if admin_input[0] else 1
                    product_list = c_op.get_customer_list(int(page_num))
                    if not product_list[2] == 0:
                        io.show_list("admin", "Customer",
                                     product_list)
                        flag_list = True
                except Exception as e:
                    io.print_error_message("main.admin_control",
                                           e)
        elif admin_choice == "4":  # Show orders
            flag_list = False
            while not flag_list:
                try:
                    if not os.path.getsize("data/orders.txt") == 0:
                        admin_input = (io.get_user_input("Enter "
                                                         "customer id and "
                                                         "page number:",
                                                         2))
                        page_num = admin_input[1] if admin_input[1] else 1
                        order_list = o_op.get_order_list(admin_input[0],
                                                         int(page_num))
                        if not order_list[2] == 0:
                            io.show_list("admin",
                                         "Order",
                                         order_list)
                            flag_list = True
                    else:
                        io.print_error_message("main."
                                               "customer_control",
                                               "no order "
                                               "history exists")
                        flag_list = True
                except Exception as e:
                    io.print_error_message("main.admin_control",
                                           e)
        elif admin_choice == "5":  # Generate test data
            io.print_message("Creating test data, thankyou for your patience")
            o_op.generate_test_order_data()
        elif admin_choice == "6":  # Generate all statistical data
            p_op.generate_category_figure()
            p_op.generate_discount_figures()
            p_op.generate_likes_count_figure()
            p_op.generate_discount_likes_count_figure()
            o_op.generate_all_top_10_best_sellers_figure()
        elif admin_choice == "7":  # Delete specific line
            flag_list = False
            while not flag_list:
                message = ("Select an action: "
                           "1) delete customer, "
                           "2) delete product, "
                           "3) delete order")
                list_choice = io.get_user_input(message, 1)[0]
                if list_choice == "1":
                    cust_id = io.get_user_input("Enter customer "
                                                "id: ", 1)[0]
                    if c_op.delete_customer(cust_id):
                        io.print_message("customer has successfully been "
                                         "deleted")
                    flag_list = True
                elif list_choice == "2":
                    product_id = io.get_user_input("Enter product "
                                                   "id: ", 1)[0]
                    if p_op.delete_product(product_id):
                        io.print_message("product has successfully been deleted")
                    flag_list = True
                elif list_choice == "3":
                    order_id = io.get_user_input("Enter order id: ",
                                                 1)[0]
                    if o_op.delete_order(order_id):
                        io.print_message("order has successfully been "
                                         "deleted")
                    flag_list = True
                else:
                    io.print_error_message("main.customer_control",
                                           "selection must be "
                                           "1 or 2 or 3")
        elif admin_choice == "8":  # Delete all data
            c_op.delete_all_customers()
            p_op.delete_all_products()
            o_op.delete_all_orders()
        elif admin_choice == "9":  # Logout
            io.print_message("successfully logged out")
            flag_exit = True


def add_customer():
    """
    Asks the user for a new customers information and creates the new user

    Args:
        No arguments

    Returns:
        None
    """
    user_name = io.get_user_input("Enter user name:",
                                  1)[0]
    password = io.get_user_input("Enter password:",
                                 1)[0]
    email = io.get_user_input("Enter email:",
                              1)[0]
    mobile = io.get_user_input("Enter mobile:",
                               1)[0]
    if c_op.register_customer(user_name, password, email, mobile):
        io.print_message("customer profile successfully created")


def show_products(user_role):
    """
    Asks the user which product search method they want to use
    and perform it. Results are printed to the screen.
    Can search all products, by a keyword or by the id.

    Args:
        user_role: the role of the user. Is either customer or admin.

    Returns:
        None
    """
    flag_list = False
    while not flag_list:
        message = ("Select an action: 1) all products, "
                   "2) products by keyword, "
                   "3) products by id")
        list_choice = io.get_user_input(message, 1)[0]
        if list_choice == "1":
            try:
                page_number = int(io.get_user_input("Enter the page "
                                                    "number you wish "
                                                    "to view:",
                                                    1)[0])
                product_list = p_op.get_product_list(page_number)
                if not product_list[2] == 0:
                    io.show_list(user_role, "Product", product_list)
                    flag_list = True
            except Exception as e:
                io.print_error_message("main.admin_control", e)
        elif list_choice == "2":
            key_word = io.get_user_input("Enter the keyword to "
                                         "search by:", 1)[0]
            product_list = p_op.get_product_list_by_keyword(key_word)
            io.show_list(user_role, "Product", product_list)
            flag_list = True
        elif list_choice == "3":
            id_search = io.get_user_input("Enter the id to "
                                          "search by:", 1)[0]
            io.print_object(p_op.get_product_by_id(id_search))
            flag_list = True
        else:
            io.print_error_message("main.customer_control",
                                   "selection must be "
                                   "1 or 2 or 3")


def main():
    """
    The main logic and starting point of the game.
    Checks if files exists and loads initial data.

    Args:
        No arguments

    Returns:
        None
    """
    io.print_message("system starting - loading data")

    # Test files exist at the start of the program before login in
    if (os.path.exists("data/users.txt")
            and os.path.exists("data/orders.txt")
            and os.path.exists("data/products.txt")):
        io.print_message("file links established")
        if os.path.getsize("data/products.txt") == 0:
            p_op.extract_products_from_files()
        io.print_message("creating an admin")
        a_op.register_admin()
        login_control()
    else:
        io.print_error_message("main",
                               "link to files could not "
                               "be established, exiting..")


if __name__ == "__main__":
    main()
