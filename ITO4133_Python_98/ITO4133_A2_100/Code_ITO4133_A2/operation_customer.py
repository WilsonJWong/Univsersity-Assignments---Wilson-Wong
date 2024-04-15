import re
import time
import math
from operation_user import UserOperation
from model_customer import Customer
from io_interface import IOInterface

check_user = UserOperation()
io = IOInterface()
user = UserOperation()


class CustomerOperation:
    @staticmethod
    def validate_email(user_email):
        """
        Validates the provided email address based on its format.
        Checks if it contains a username, the @ symbol, a domain name and the
        '.' in the specified pattern order.

        Args:
            user_email: the user email to be validated

        Returns:
            check: a boolean value determining if it was validated successfully
        """
        pattern = r'^\S+@\S+\.\S+$'
        check = bool(re.match(pattern, user_email))
        if not check:
            io.print_error_message("CustomerOperation.validate_email",
                                   "email must have the following in"
                                   " order - username, @, address, domain name")
        return check

    @staticmethod
    def validate_mobile(user_mobile):
        """
        Validates the provided mobile number based on its format.
        Checks if it starts with either 04 or 03 and 8 following digits.

        Args:
            user_mobile: the user mobile to be validated

        Returns:
            check: a boolean value determining if it was validated successfully
        """
        pattern = r'^(03|04)\d{8}$'
        check = bool(re.match(pattern, user_mobile))
        if not check:
            io.print_error_message("CustomerOperation.validate_mobile",
                                   "mobile must be 10 digits long "
                                   "and start with 04 or 03")
        return check

    def register_customer(self, user_name, user_password,
                          user_email, user_mobile):
        """
        Creates a customer with the specified inputs and adds it to the
        customer text file.

        Args:
            user_name: the user's name
            user_password: the user's password
            user_email: the user's email
            user_mobile: the user's mobile

        Returns:
            a boolean value determining if the customer was
            created successfully
        """
        if not check_user.validate_username(user_name) or \
                not check_user.validate_password(user_password) or \
                not self.validate_email(user_email) or \
                not self.validate_mobile(user_mobile):
            io.print_error_message("CustomerOperation."
                                   "register_customer",
                                   "invalid input values")
            return False
        else:
            if user.check_username_exist(user_name):
                io.print_error_message("CustomerOperation."
                                       "register_customer",
                                       "user name already exists")
                return False
            else:
                new_customer = (self.set_customer
                                (user.generate_unique_user_id(), user_name,
                                 user.encrypt_password(user_password),
                                 time.strftime("%d-%m-%Y_%H:%M:%S"),
                                 user_email, user_mobile))
                with open("data/users.txt", "a", encoding="utf8") as file:
                    file.write(str(new_customer))
                return True

    def update_profile(self, attribute_name, value, customer_object):
        """
        Update the given customer object’s attribute value.

        Args:
            attribute_name: the name of the attribute to update on
            value: the value to be updated to
            customer_object: the customer object that is to be updated

        Returns:
            a boolean value determining if the profile was
            updated successfully
        """
        input_customer = user.clean_line(str(customer_object))
        file_handle = open("data/users.txt", "r", encoding="utf8")

        flag_obj = False
        for line in file_handle:
            temp_customer = user.clean_line(line)
            if input_customer[0] == temp_customer[0]:
                flag_obj = True

        if not flag_obj:
            io.print_error_message("CustomerOperation."
                                   "update_profile",
                                   "invalid customer object")
            return False

        if attribute_name == "user_name":
            if not check_user.validate_username(value):
                io.print_error_message("CustomerOperation."
                                       "update_profile",
                                       "invalid input values")
                return False
            elif check_user.check_username_exist(value):
                io.print_error_message("CustomerOperation."
                                       "update_profile",
                                       "invalid input values")
                return False
        elif attribute_name == "user_password":
            if not check_user.validate_password(value):
                io.print_error_message("CustomerOperation."
                                       "update_profile",
                                       "invalid input values")
                return False
            else:
                value = user.encrypt_password(value)

        elif attribute_name == "user_register_time":
            try:
                time.strptime(value, "%d-%m-%Y_%H:%M:%S")
            except Exception as e:
                io.print_error_message("CustomerOperation."
                                       "update_profile", e)
                return False
        elif attribute_name == "user_role":
            io.print_error_message("CustomerOperation."
                                   "update_profile",
                                   "user role can only be "
                                   "changed by an admin")
            return False
        elif attribute_name == "user_email":
            if not self.validate_email(value):
                io.print_error_message("CustomerOperation."
                                       "update_profile",
                                       "invalid input values")
                return False
        elif attribute_name == "user_mobile":
            if not self.validate_mobile(value):
                io.print_error_message("CustomerOperation."
                                       "update_profile",
                                       "invalid input values")
            return False
        else:
            io.print_error_message("CustomerOperation."
                                   "update_profile",
                                   "invalid attribute name")
            return False

        setattr(customer_object, attribute_name, value)
        file_handle_1 = open("data/users.txt", "r", encoding="utf8")
        updated_list = []
        for line in file_handle_1:
            temp_customer = user.clean_line(line)
            if input_customer[0] == temp_customer[0]:
                updated_list.append(str(customer_object))
            else:
                updated_list.append(line)

        with open("data/users.txt", "w", encoding="utf8") as file:
            file.writelines(updated_list)
        return True

    @staticmethod
    def delete_customer(customer_id):
        """
        Delete the customer from the user file based on the
        provided customer id.

        Args:
            customer_id: the customers unique id

        Returns:
            a boolean value determining if the customer was
            deleted successfully
        """
        temp_lines = []
        flag_found = False

        with open("data/users.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()

        for line in file_lines:
            temp_user = user.clean_line(line)
            if not customer_id == temp_user[0]:
                temp_lines.append(line)
            else:
                flag_found = True

        if flag_found:
            with open("data/users.txt", "w", encoding="utf8") as file:
                file.writelines(temp_lines)
            return True
        else:
            io.print_error_message("CustomerOperation."
                                   "delete_customer",
                                   "no matching customer "
                                   "id was found")
            return False

    def get_customer_list(self, page_number):
        """
        Retrieve one page of customers from the customer file. One page
        contains a maximum of 10 customers.

        Args:
            page_number: the specified page number to be displayed

        Returns:
            a tuple including a list of customers objects and the
            total number of pages
        """
        with open("data/users.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()

        temp_customer = []
        for line in file_lines:
            temp_clean = user.clean_line(line)
            if temp_clean[4] == "customer":
                new_customer = self.set_customer(temp_clean[0],
                                                 temp_clean[1],
                                                 temp_clean[2],
                                                 temp_clean[3],
                                                 temp_clean[5],
                                                 temp_clean[6])
                temp_customer.append(new_customer)

        if not temp_customer:
            io.print_error_message("CustomerOperation."
                                   "get_customer_list",
                                   "no customers found")
            return [], 0, 1

        customer_count = len(temp_customer)
        total_pages = math.ceil(customer_count / 10)
        if 0 < page_number <= total_pages:
            specific_page = self.page_creation(temp_customer, page_number)
            return specific_page, page_number, total_pages
        else:
            io.print_error_message("CustomerOperation."
                                   "get_customer_list",
                                   "specified page number "
                                   "does not exist")
            return [], 0, 0

    @staticmethod
    def delete_all_customers():
        """
        Removes all the customers from the customer file.

        Args:
            No arguments

        Returns:
            none
        """
        with open("data/users.txt", "r", encoding="utf8") as file:
            file_lines = file.readlines()
        other_users = []

        for line in file_lines:
            temp_customer = user.clean_line(line)
            if not temp_customer[4] == "customer":
                other_users.append(line)

        if not len(file_lines) == len(other_users):
            with open("data/users.txt", "w", encoding="utf8") as file:
                file.writelines(other_users)
            io.print_message("all customers removed successfully")
        else:
            io.print_message("no customers existed to be deleted")

    @staticmethod
    def set_customer(user_id, user_name, user_password,
                     user_register_time, user_email, user_mobile):
        """
        Creates a new customer object and sets the new values as specified.

        Args:
            user_id: the user's unique id
            user_name: the user's name
            user_password: the user's password
            user_register_time: the user's time of registration
            user_email: the user's email
            user_mobile: the user's mobile

        Returns:
            a tuple including a list of customers objects and the
            total number of pages
        """
        new_customer = Customer()
        new_customer.user_id = user_id
        new_customer.user_name = user_name
        new_customer.user_password = user_password
        new_customer.user_register_time = user_register_time
        new_customer.user_email = user_email
        new_customer.user_mobile = user_mobile
        return new_customer

    @staticmethod
    def page_creation(temp_obj, page_number):
        """
        Creates and returns a list of objects that are to be displayed
        for the specified page number.

        Args:
            temp_obj: a list of all objects
            page_number: the specified page number to be displayed

        Returns:
            specific_page: a list of objects for the specified page number
        """
        count_obj = len(temp_obj)
        total_pages = math.ceil(count_obj / 10)

        start_index = 0 + (10 * (page_number - 1))
        if not page_number == total_pages and count_obj >= 10:
            upper = 10
        else:
            upper = count_obj - ((total_pages - 1) * 10)

        specific_page = []
        for number in range(0, upper):
            reference = number + start_index
            specific_page.append(temp_obj[reference])
        return specific_page
