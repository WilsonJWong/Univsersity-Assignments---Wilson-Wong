class IOInterface:

    def get_user_input(self, message, num_of_args):
        """
        Displays a message to the user prompting a response.

        Args:
            message:the specified message to be displayed to the user
            num_of_args: the number of responses the message is asking for

        Returns:
            result: a list of responses, size determined by the num_of_args
        """
        if type(num_of_args) == int:
            user_input = input(str(message) + "\n")
            input_split = user_input.split()
            result = []

            if len(input_split) < int(num_of_args):
                diff = num_of_args - len(input_split)
                for input_arg in input_split:
                    result.append(input_arg)
                for _ in range(diff):
                    result.append("")
                return result
            else:
                for num in range(int(num_of_args)):
                    result.append(input_split[num])
                return result
        else:
            self.print_error_message("IOInterface.get_user_input",
                                     "number of arguments "
                                     "input must be an integer")

    @staticmethod
    def main_menu():
        """
        Displays the login menu options

        Args:
            No arguments

        Returns:
            None
        """
        login_menu = ("Welcome to the login menu!\n"
                      " 1)Login\n 2)Register \n 3)Quit\n")
        print(login_menu)

    @staticmethod
    def admin_menu():
        """
        Displays the admin menu options

        Args:
            No arguments

        Returns:
            None
        """
        admin_menu = ("Welcome to the admin menu!\n"
                      " 1)Show products\n"
                      " 2)Add customer\n"
                      " 3)Show customer\n"
                      " 4)Show orders\n"
                      " 5)Generate test data\n"
                      " 6)Generate all statistical figures\n"
                      " 7)Delete specific data\n"
                      " 8)Delete all data\n"
                      " 9)Logout\n")
        print(admin_menu)

    @staticmethod
    def customer_menu():
        """
        Displays the customer menu options

        Args:
            No arguments

        Returns:
            None
        """
        customer_menu = ("Welcome to the customer menu!\n"
                         " 1)Show profile\n"
                         " 2)Update profile\n"
                         " 3)Show products\n"
                         " 4)Show history orders\n"
                         " 5)Generate all consumption figures\n"
                         " 6)Logout\n")
        print(customer_menu)

    def show_list(self, user_role, list_type, object_list):
        """
        Prints out the different types of list, the number of rows and the
        total page number if a user has the defined access rights
        based on their role.

        Args:
            user_role: the role of the user. It is either customer or admin.
            list_type:the type of objects the list contains
            object_list:the list of objects

        Returns:
            None
        """
        flag_print = False
        if user_role == "customer":
            if list_type == "Product" or list_type == "Order":
                flag_print = True
        elif user_role == "admin":
            if (list_type == "Customer"
                    or list_type == "Product"
                    or list_type == "Order"):
                flag_print = True

        if flag_print:
            if 1 < len(object_list) < 4:
                print(f"List Type: {list_type}")
                print(f"Page: {object_list[1]} of {object_list[2]}")
                print("Number of rows: " + str(len(object_list[0])) + "\n")
                for item in object_list[0]:
                    self.print_object(item)
            else:
                print(f"List Type: {list_type}")
                print("Number of rows: " + str(len(object_list)) + "\n")
                for item in object_list:
                    self.print_object(item)
        else:
            self.print_error_message("IOInterface.show_list",
                                     "invalid selection")

    @staticmethod
    def print_error_message(error_source, error_message):
        """
        Displays the source of the error as well as the
        specified error message

        Args:
            error_source:the source of the error
            error_message:the detailed error message

        Returns:
            None
        """
        print(f"!!! Error at '{error_source}': '{error_message}' !!!\n")

    @staticmethod
    def print_message(message):
        """
        Displays the specified message

        Args:
            message: a specified system message to be displayed to the user

        Returns:
            None
        """
        print(f"System message: '{message}'\n")

    @staticmethod
    def print_object(target_object):
        """
        Displays a single object as a string

        Args:
            target_object: the specified object

        Returns:
            None
        """
        print(str(target_object))
