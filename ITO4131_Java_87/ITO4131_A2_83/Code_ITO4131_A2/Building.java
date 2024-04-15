/**
 * This class represents an individual building within the game. 
 * @author Wilson Wong
 * version ver 1.0.0
 */

public class Building
{
    private int height;
    private boolean hasExitPortal;
    private boolean hasFuelCell;
    private boolean hasWeb;
    private boolean hasFreeze; 

    /**
     * Default constructor which creates the object of the class Building.
     * Intialises height to 0, hasExitPortal to false, hasFuelCell to false,
        hasWeb to false and hasFreeze to false.
     */
    public Building()
    {
        height = 1;
        hasExitPortal = false;
        hasFuelCell = false; 
        hasWeb = false; 
        hasFreeze = false;
    }

    /**
     * Non-default constructor which creates the object of the class Building.
     * @param height  
        Accepts the total floor height of the building as an integer.
     * @param hasExitPortal    
        Accepts a boolean for if the building has an exit portal.
     * @param hasFuelCell
        Accepts a boolean for if the building has a fuel cell.
     * @param hasWeb
        Accepts a boolean for if the building has a web trap.
     * @param hasFreeze
        Accepts a boolean for if the building is frozen.
     */
    public Building(int height, boolean hasExitPortal, 
        boolean hasFuelCell, boolean hasWeb, boolean hasFreeze)
    {
        if(height > 0)
            this.height = height;
        else
            this.height = 1;

        this.hasExitPortal = hasExitPortal;
        this.hasFuelCell = hasFuelCell;
        this.hasWeb = hasWeb;
        this.hasFreeze = hasFreeze;
        
    }

    /**
     * Display method to return the state of the Building object.
     * @return  The state of the Building object as a string.
     */
    public String display()
    {
        String output = height + " " + hasExitPortal 
            + " " + hasFuelCell + " "  + hasWeb + " " + hasFreeze;
        return output;
    }

    /**
     * Accessor method to return if a building has an exit portal.
     * @return  If a building has an exit portal.
     */
    public boolean getHasExitPortal()
    {
        return hasExitPortal;
    }

    /**
     * Accessor method to return if a building has been frozen.
     * @return  If a building has been frozen.
     */
    public boolean getHasFreeze()
    {
        return hasFreeze;
    }

    /**
     * Accessor method to return if a building has a fuel cell.
     * @return  If a building has a fuel cell.
     */
    public boolean getHasFuelCell()
    {
        return hasFuelCell;
    }

    /**
     * Accessor method to return if a building has a web trap.
     * @return  If a building has a web trap.
     */
    public boolean getHasWeb()
    {
        return hasWeb;
    }

    /**
     * Accessor method to return a buildings total floor height.
     * @return  How many floors a building has.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Mutator method to set a building's exit portal state.
     * @param hasExitPortal    
        Accepts a boolean for if the building has an exit portal.
     */
    public void setHasExitPortal(boolean hasExitPortal)
    {
        this.hasExitPortal = hasExitPortal;
    }

    /**
     * Mutator method to set a building's freeze state.
     * @param hasFreeze
        Accepts a boolean for if the building is frozen.
     */
    public void setHasFreeze(boolean hasFreeze)
    {
        this.hasFreeze = hasFreeze;
    }

    /**
     * Mutator method to set a building's fuel cell state.
     * @param hasFuelCell
        Accepts a boolean for if the building has a fuel cell.
     */
    public void setHasFuelCell(boolean hasFuelCell)
    {
       this.hasFuelCell = hasFuelCell;
    }

    /**
     * Mutator method to set a building's web trap state.
     * @param hasWeb
        Accepts a boolean for if the building has a web trap.
     */
    public void setHasWeb(boolean hasWeb)
    {
       this.hasWeb = hasWeb;
    }

    /**
     * Mutator method to set a building's total floor height.
     * @param height  
        Accepts the total floor height of the building as an integer.
     */
    public void setHeight(int height)
    {
        if (height > 0)
            this.height = height;
    }
    
}
