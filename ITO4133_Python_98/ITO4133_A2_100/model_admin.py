from model_user import User


class Admin(User):
    def __init__(self):
        """
        Constructor for the Admin class, initialising an Admin object by
        calling on the super class User and sets the role to admin

        Args:
            No arguments

        Returns:
            None
        """
        super(Admin, self).__init__(user_role="admin")

    def __str__(self):
        """
        Coverts the Admin object to a string format

        Args:
            No arguments

        Returns:
            output: the string message
        """
        output = (f"{{'user_id':'{self.user_id}', "
                  f"'user_name':'{self.user_name}', "
                  f"'user_password':'{self.user_password}', "
                  f"'user_register_time':'{self.user_register_time}', "
                  f"'user_role':'{self.user_role}'}}\n")
        return output

