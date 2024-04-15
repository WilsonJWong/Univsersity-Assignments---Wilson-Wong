import random
import string
import re
from model_customer import Customer
from model_admin import Admin
from io_interface import IOInterface

io = IOInterface()


class UserOperation:

    @staticmethod
    def generate_unique_user_id():
        """
        Create a 10 digit unique order id starting with u_

        Args:
            No arguments

        Returns:
            random_id: the randomly created user id
        """
        random_id = ""
        flag_check = False

        while not flag_check:
            random_num = random.randint(1000000000, 9999999999)
            random_id = "u_" + str(random_num)
            file_handle = open("data/users.txt", "r", encoding="utf8")

            if file_handle:
                flag_check = True

            for line in file_handle:
                temp_split_a = line.split(", ")
                temp_split_b = temp_split_a[0].split(":")
                clean_id = temp_split_b[1].strip("'")
                if clean_id == random_id:
                    break
                flag_check = True
        return random_id

    @staticmethod
    def encrypt_password(user_password):
        """
        Encrypt the provided user password

        Args:
            user_password: the user's password

        Returns:
            ran_string: the encrypted password
        """
        characters = string.ascii_letters + string.digits
        ran_string = ""
        for i in range(len(user_password)):
            ran_string = (ran_string + random.choice(characters)
                          + random.choice(characters) + user_password[i])
        ran_string = "^^" + ran_string + "$$"
        return ran_string

    @staticmethod
    def decrypt_password(encrypt_password):
        """
        Decrypt the provided encrypted password

        Args:
            encrypt_password: the encrypted user's password

        Returns:
            decrypted: the decrypted password
        """
        decrypted = ""
        count = 3
        for i in range(len(encrypt_password)):
            if i > 3 and count % 3 == 0:
                decrypted = decrypted + encrypt_password[i]
            if i > 3:
                count = count + 1
        return decrypted

    @staticmethod
    def check_username_exist(user_name):
        """
        Check if the username already exists in the user file

        Args:
            user_name: the user's username

        Returns:
            a boolean value, true it exists, false it does not
        """
        file_handle = open("data/users.txt", "r", encoding="utf8")
        for line in file_handle:
            clean_name = line.split(", ")[1].split(":")[1].strip("'")
            if clean_name == user_name:
                io.print_error_message("UserOperation."
                                       "check_username_exist",
                                       "user name already exists")
                return True
        return False

    @staticmethod
    def validate_username(user_name):
        """
        Check if the username name only contains letters or underscores,
        and its length is at least 5 characters.

        Args:
            user_name: the user's username

        Returns:
            check: a boolean value indicating the success of the validation
        """
        pattern = r'^[a-zA-Z_]{5,}$'
        check = bool(re.match(pattern, user_name))
        if not check:
            io.print_error_message("UserOperation.validate_username",
                                   "user name must be a "
                                   "minimum of 5 characters and only contain "
                                   "alphabetical characters or underscores")
        return check

    @staticmethod
    def validate_password(user_password):
        """
        Check if the user password contain at least one letter and one number.
        The length of the password must be greater than or
        equal to 5 characters.

        Args:
            user_password: the user's password

        Returns:
            check: a boolean value indicating the success of the validation
        """
        pattern = r'^(?=.*[A-Za-z])(?=.*\d).{5,}$'
        check = bool(re.match(pattern, user_password))
        if not check:
            io.print_error_message("UserOperation.validate_username",
                                   "password must be a minimum "
                                   "of 5 characters, and must contain at "
                                   "least 1 letter and 1 number ")
        return check

    def login(self, user_name, user_password):
        """
        Verify the provided user’s name and password combination against
        stored user data to determine the authorization status for accessing
        the system.

        Args:
            user_name: the user's username
            user_password: the user's password

        Returns:
            a Customer or Admin object
        """
        file_handle = open("data/users.txt", "r", encoding="utf8")

        for line in file_handle:
            temp_split_b = self.clean_line(line)

            if (temp_split_b[1] == user_name and self.
                    decrypt_password(temp_split_b[2]) == user_password):
                io.print_message("Authorization Successful")

                if temp_split_b[4] == "customer":
                    ret_customer = Customer()
                    ret_customer.user_id = temp_split_b[0]
                    ret_customer.user_name = temp_split_b[1]
                    ret_customer.user_password = temp_split_b[2]
                    ret_customer.user_register_time = temp_split_b[3]
                    ret_customer.user_email = temp_split_b[5]
                    ret_customer.user_mobile = temp_split_b[6]
                    return ret_customer
                elif temp_split_b[4] == "admin":
                    ret_admin = Admin()
                    ret_admin.user_id = temp_split_b[0]
                    ret_admin.user_name = temp_split_b[1]
                    ret_admin.user_password = temp_split_b[2]
                    ret_admin.user_register_time = temp_split_b[3]
                    return ret_admin

        io.print_error_message("UserOperation.login",
                               "Invalid login")
        return Customer()

    @staticmethod
    def clean_line(line):
        """
        Extracts the values within the string input and appends
        them to a list

        Args:
            line: a string representation of an object

        Returns:
            temp_split_b: a list of the extracted values
        """
        line = line.strip("\n").strip("{}")
        temp_split_a = line.split(", ")
        temp_split_b = []
        for each in temp_split_a:
            temp_split_c = each.split(":")
            if temp_split_c[0].strip("'") == "user_register_time":
                cleaned = (temp_split_c[1] + ":"
                           + temp_split_c[2] + ":"
                           + temp_split_c[3])
                cleaned = cleaned.strip("'")
            else:
                cleaned = temp_split_c[1].strip("'")
            temp_split_b.append(cleaned)
        return temp_split_b
