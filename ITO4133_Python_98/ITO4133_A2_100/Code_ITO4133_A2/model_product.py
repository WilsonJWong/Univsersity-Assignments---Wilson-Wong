class Product:
    def __init__(self, pro_id="p_0000000000", pro_model="default_model",
                 pro_category="default_category", pro_name="default_name",
                 pro_current_price=0.0, pro_raw_price=0.0,
                 pro_discount=0.0, pro_likes_count=0):
        """
        Constructor for the Product class, initialising a Product object with
        the arguments or default values.

        Args:
            pro_id: the unique id of the product
            pro_model: the model of the product
            pro_category: the category of the product
            pro_name: the name of the product
            pro_current_price: the sell price of the product
            pro_raw_price: the purchase price of the product
            pro_discount: the discount rate of the product
            pro_likes_count: the count of likes for the product

        Returns:
            None
        """
        self.pro_id = pro_id
        self.pro_model = pro_model
        self.pro_category = pro_category
        self.pro_name = pro_name
        self.pro_current_price = pro_current_price
        self.pro_raw_price = pro_raw_price
        self.pro_discount = pro_discount
        self.pro_likes_count = pro_likes_count

    def __str__(self):
        """
        Coverts the Product object to a string format

        Args:
            No arguments

        Returns:
            output: the string message
        """
        output = (f"{{'pro_id':'{self.pro_id}', "
                  f"'pro_model':'{self.pro_model}', "
                  f"'pro_category':'{self.pro_category}', "
                  f"'pro_name':'{self.pro_name}', "
                  f"'pro_current_price':'{self.pro_current_price}', "
                  f"'pro_raw_price':'{self.pro_raw_price}', "
                  f"'pro_discount':'{self.pro_discount}', "
                  f"'pro_likes_count':'{self.pro_likes_count}'}}\n")
        return output
