/**
 * This class represents an individual player within the game. 
 * @author Wilson Wong
 * version ver 1.0.0
 */

public class Player
{
    private int charge; 
    private int consumed; 
    private int turn;
    private int currentBuildNumber;
    private boolean escaped;
    private String name; 
    

    /**
     * Default constructor which creates the object of the class Player.
     * Intialises charge to 10, consumed to 0, turn = 1, 
        escaped to false and name to an empty string. 
     */
    public Player ()
    {
        charge = 10;
        consumed = 0;
        turn = 1;
        escaped = false;
        name = "Player"; 
    }

    /**
     * Non-default constructor which creates the object of the class Player.
     * @param charge
        Accepts the number of charges as an integer.
     * @param consumed
        Accepts the number of fuel cells consumed as an integer.
     * @param turn
        Accepts the number of turns occured as an integer. 
     * @param escaped
        Accepts the state of the player's escape as a boolean.
     * @param name 
        Accepts the name of the player as a string. 
     */
    public Player(int charge, int consumed, int turn, boolean escaped, String name)
    {
        if (charge > 0 && charge <= 20)
            this.charge = charge;
        else
            this.charge = 10;

        if (consumed >= 0)
            this.consumed = consumed;
        else 
            this.consumed = 0;

        if (turn > 0)
            this.turn = turn; 
        else
            this.turn = 1; 

        this.escaped = escaped;
        
        if(name.length() >= 3 && name.length() <= 12)
            this.name = name;
        else 
            this.name = "Player";
    }

    /**
     * Decreases the charge by the specified amount.
     * @param decrease
        Accepts the charge decrease amount as an integer. 
     */
    public void chargeDecrease(int decrease)
    {
        if((charge - decrease) > 0)
            charge = charge - decrease; 
        else 
            charge = 0;
    }

    /**
     * Increases the charge by the specified amount.
     * @param increase
        Accepts the charge increase amount as an integer. 
     */
    public void chargeIncrease(int increase)
    {
        if (charge + increase < 20)
            charge = charge + increase; 
        else    
            charge = 20; 
    }

    /**
     * Display method to return the state of the Player object.
     * @return  The state of the Player object as a string.
     */
    public String display()
    {
        String output = "charge : " + charge + "\nconsumed : " 
            + consumed + "\nturn : " + turn + "\nescaped : " 
            + escaped + "\nname : " + name;
        return output; 
    }

    /**
     * Accessor method to obtain the current charge amount. 
     * @return  The current charge amount as an integer. 
     */
    public int getCharge()
    {
        return charge; 
    }

    /**
     * Accessor method to obtain the current amount of fuel cells consumed.
     * @return  The current amount of fuel cells consumed.  
     */
    public int getConsumed()
    {
        return consumed; 
    }

    /**
     * Accessor method to obtain the current state of the escaped status.
     * @return  The current state of the players escape as a boolean.
     */
    public boolean getEscaped()
    {
        return escaped;
    }

    /**
     * Accessor method to obtain the players name.
     * @return  The name of the player as a string. 
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Accessor method to get the current turn number.
     * @return The current turn number as an integer. 
     */
    public int getTurn()
    {
        return turn;
    }

    /**
     * Increases the count of the fuel cells consumed.
     */
    public void incrementConsumed()
    {
        consumed++; 
    }

    /**
     * Mutator method to set the number of charges.
     * @param   Accepts the number of charges as an integer.
     */
    public void setCharge(int charge)
    {
        if(charge < 0)
            this.charge = 0; 
        else if (charge > 20)
            this.charge = 20;
        else 
            this.charge = charge;
    }

    /**
     * Mutator method to set the number of fuel cells consumed.
     * @param   Accepts the number of fuel cells consumed as an integer.   
     */ 
    public void setConsumed(int consumed)
    {
        if(consumed >= 0)
            this.consumed = consumed; 
    }

    /**
     * Mutator method to set the player's status of escape.
     * @param   Accepts the state of the player's escape as a boolean.   
     */
    public void setEscaped(boolean escaped)
    {
        this.escaped = escaped;
    }

    /**
     * Mutator method to set the player's name. 
     * @param   Accepts the name of the player as a string.
     */
    public void setName(String name)
    {
        if(name.length() >= 3 && name.length() <= 12)
            this.name = name; 
    }

    /**
     * Mutator method to set the player's turn count. 
     * @param turn
        Accepts the turn count as an integer.
     */
    public void setTurn(int turn)
    {
        if(turn > 0)
            this.turn = turn;
        else
            this.turn = 1; 
    }

    /**
     * Increases the turn count of the game. 
     */
    public void turnIncrease()
    {
        turn++;
    }

}
