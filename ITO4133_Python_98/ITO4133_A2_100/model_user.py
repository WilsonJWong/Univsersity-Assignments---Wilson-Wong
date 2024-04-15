class User:
    def __init__(self, user_id="u_0000000000", user_name="default_name",
                 user_password="default_pass",
                 user_register_time="00-00-0000_00:00:00",
                 user_role="customer"):
        """
        Constructor for the User class, initialising a User object with
        the arguments or default values.

        Args:
            user_id: unique id for the user
            user_name: the user's name
            user_register_time: the time user account was created
            user_role:the role of the user

        Returns:
            None
        """
        self.user_id = user_id
        self.user_name = user_name
        self.user_password = user_password
        self.user_register_time = user_register_time
        self.user_role = user_role

    def __str__(self):
        """
        Converts the User object to a string format

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
