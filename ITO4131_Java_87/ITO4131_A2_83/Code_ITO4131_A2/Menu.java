/**
 * This class provides the menu displays for the game. 
 * @author Wilson Wong
 * version ver 1.0.0
 */

public class Menu
{
    private int lineCharMax; 

    /**
     * Default constructor which creates the object of the class Menu.
     * Initialises lineCharMax to 100.
     */
    public Menu()
    {
        lineCharMax = 100;
    }

    /**
     * Non-default constructor which creates the object of the class Menu.
     * @param lineCharMax
        Accepts an integer input for the maximum character length of a line. 
     */
    public Menu(int lineCharMax)
    {
        if(lineCharMax > 0)
            this.lineCharMax = lineCharMax;
        else
            this.lineCharMax = 100;
    }

    /**
     * Adds space to a line to display with a ! at the start and end.
     * @param temp The string line input in which needs space added to. 
     * @return  The string line with space added to.
     */
    public String addSpace(String temp)
    {
        boolean remainder = true;
        int difference = lineCharMax - temp.length() - 4;
        String space = " ";

        for (int i = 1; i < difference; i++)
        {
            space = space + " ";
        }

        space = "! " + temp + space + " !";

        return space;
    }

    /**
     * Centers the title within the display output.
     * @param temp  The string title line which needs to be centered.
     * @return  The string title line that has been centered. 
     */
    public String centerTitle (String temp)
    {
        boolean remainder = true;
        String space = " "; 
        int difference = lineCharMax - temp.length() - 2;
        int halfDiff = difference/2;

        for(int i = 1; i<halfDiff; i++)
        {
            space = space + " "; 
        }

        if(difference % 2 == 0)
        {
            remainder = false;
        }

        if (remainder == true)
            temp = "!" + space + temp + space + " !";
        else
            temp = "!" + space + temp + space + "!"; 

        return temp;
    }

    /**
     * Creates the first and last line of the display.
     * @return A string of the first and last line.
     */
    public String createFirstLastLine()
    {
        String firstLast = "!";
        do
        {  
            if ((lineCharMax - firstLast.length()) == 3)
                firstLast = firstLast + "+=!";
            else if ((lineCharMax - firstLast.length()) == 2)
                firstLast = firstLast + "=!";
            else
                firstLast = firstLast + "+=";

        }while(firstLast.length() < lineCharMax);

        return firstLast;
    }

    /**
     * Display method to return the state of the Menu object.
     * @return  The state of the Menu object as a string.
     */
    public String display() 
    {
        String output = 
            "Max number of characters on a line is: " + lineCharMax;
        return output; 
    }

    /**
     * Creates and prints the explanation menu to the screen.
     */
    public void displayExplanationMenu()
    {
        String temp = "";

        String menu = createFirstLastLine() + "\n";

        temp = "WELCOME TO NOWHERE WHERE NO ONE ESCAPES!";
        menu = menu + centerTitle(temp) + "\n";

        temp = "You are quested to try and escape using ";
        temp = temp + "the only jumper device avaliable in Nowhere.";
        menu = menu + addSpace(temp) + "\n";

        temp = "Remember the following to ensure you survive:";
        menu = menu + addSpace(temp) + "\n";

        temp = "* the device will only allow you to jump short distances";
        menu = menu + addSpace(temp) + "\n";

        temp = "* the jump distance is based on the height difference ";
        temp = temp + " of the buildings being jumped";
        menu = menu + addSpace(temp) + "\n";

        temp = "* the building heights change very frequently over time";
        menu = menu + addSpace(temp) + "\n";

        temp = "* fuel cells found on rooftops can refuel ";
        temp = temp + "the device for a short while";
        menu = menu + addSpace(temp) + "\n";

        temp = "* stay far away from the frozen buildings";
        menu = menu + addSpace(temp) + "\n";

        temp = "* look out for the Nowhere police webs";
        menu = menu + addSpace(temp) + "\n";

        temp = "Lastly the Underground Guild takes no ";
        temp = temp + "responsibility or provides any guarantees";
        menu = menu + addSpace(temp) + "\n";

        temp = "Should you survive, we WILL come to collect! Good luck!";
        menu = menu + addSpace(temp) + "\n";

        menu = menu + createFirstLastLine() + "\n";

        System.out.println(menu);
    }

    /**
     * Creates the turn display, captures and returns the end-user input. 
     * @return The end-user input as an integer.
     */
    public int displayTurnMenu()
    {   
        Input objInt = new Input();

        int displayTurnMenu = 0;
        boolean flagMenu = false; 
        String action = "\n Please select an action";
        action = action + "\n - Press 1 to jump forward";
        action = action + "\n - Press 2 to jump backwards";
        action = action + "\n - Press 3 to skip a turn";
        do
        {
            try
            {
                displayTurnMenu = objInt.acceptIntegerInput(action);
                if (displayTurnMenu >= 1 && displayTurnMenu <= 3)
                    flagMenu = true;
                else 
                    System.out.print("Please enter an integer ");
                    System.out.println(" value between 1 and 3!");
            }
            catch (Exception e)
            {
                System.out.println("Please enter an integer value!");
            }
            
        } while (flagMenu == false);
        
        return displayTurnMenu; 
    }

    /**
     * Accessor method to return the maximum character length of a line.
     * @return The maximum character length of a line as an integer. 
     */
    public int getlineCharMax()
    {
        return lineCharMax;
    }

    /**
     * Mutator method to set the maximum character length of a line.
     * @param lineCharMax 
        Accepts the maximum character length of a line as an integer.
     */
    public void setlineCharMax(int lineCharMax)
    {
        if(lineCharMax > 0)
            this.lineCharMax = lineCharMax; 
    }
}
