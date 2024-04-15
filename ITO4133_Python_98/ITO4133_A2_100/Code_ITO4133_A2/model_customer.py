from model_user import User


class Customer(User):
    def __init__(self, user_email="default_email",
                 user_mobile="default_mobile"):
        """
        Constructor for the Customer class, initialising a Constructor object
        by calling on the super class User and sets the email and mobile

        Args:
            user_email: the customers email
            user_mobile: the customers mobile phone number

        Returns:
            None
        """
        super(Customer, self).__init__()
        self.user_email = user_email
        self.user_mobile = user_mobile

    def __str__(self):
        """
        Coverts the Customer object to a string format

        Args:
            No arguments

        Returns:
            output: the string message
        """
        output = (f"{{'user_id':'{self.user_id}', "
                  f"'user_name':'{self.user_name}', "
                  f"'user_password':'{self.user_password}', "
                  f"'user_register_time':'{self.user_register_time}', "
                  f"'user_role':'{self.user_role}', "
                  f"'user_email':'{self.user_email}', "
                  f"'user_mobile':'{self.user_mobile}'}}\n")
        return output
