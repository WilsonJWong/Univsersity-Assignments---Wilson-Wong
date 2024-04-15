import time
from model_admin import Admin
from operation_user import UserOperation
from io_interface import IOInterface

user = UserOperation()
io = IOInterface()


class AdminOperation:
    """
    If the default admin account does not already exist, create a new
    admin account.

    Args:
        No arguments

    Returns:
        None
    """
    @staticmethod
    def register_admin():
        if user.check_username_exist("admin"):
            io.print_error_message("AdminOperation.register_admin",
                                   "admin account already exists")
        else:
            admin = Admin()
            admin.user_id = user.generate_unique_user_id()
            admin.user_name = "admin"
            admin.user_password = user.encrypt_password("admin")
            admin.user_register_time = time.strftime("%d-%m-%Y_%H:%M:%S")

            with open("data/users.txt", "a", encoding="utf8") as file:
                file.write(str(admin))
            io.print_message("admin account created successfully")
