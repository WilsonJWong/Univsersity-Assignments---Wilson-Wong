class Order:
    def __init__(self, order_id="o_00000", user_id="u_0000000000",
                 pro_id="p_0000000000", order_time="00-00-0000_00:00:00"):
        """
        Constructor for the Order class, initialising an Order object with
        the arguments or default values.

        Args:
            order_id: the unique id for an order
            user_id: the user id of the person who placed the order
            pro_id: the product id of product ordered
            order_time: the time which the order was placed

        Returns:
            None
        """
        self.order_id = order_id
        self.user_id = user_id
        self.pro_id = pro_id
        self.order_time = order_time

    def __str__(self):
        """
        Converts the Order object to a string format

        Args:
            No arguments

        Returns:
            output: the string message
        """
        output = (f"{{'order_id':'{self.order_id}', "
                  f"'user_id':'{self.user_id}', "
                  f"'pro_id':'{self.pro_id}', "
                  f"'order_time':'{self.order_time}'}}\n")
        return output
