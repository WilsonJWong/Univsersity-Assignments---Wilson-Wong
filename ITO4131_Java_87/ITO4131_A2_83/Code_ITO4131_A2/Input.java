/**
 * This class handles the inputs from a user. 
 * @author Wilson Wong
 * version ver 1.0.0
 */

import java.util.Scanner; 

public class Input
{
    /**
     * Default constructor which creates the object of the class Input.
     */
    public Input()
    {

    }
    
    /**
     * Displays the input request message and 
        accepts the users output as a integer.
     * @param input
        Accepts a string message to be displayed to the end-user.
     * @return 
        The integer repsonse of the end-user. 
     */
    public int acceptIntegerInput(String input)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(input);
        int intOutput = Integer.parseInt(console.nextLine());
        return intOutput;
    }

    /**
     * Displays the input request message and 
        accepts the users output as a String.
     * @param input
        Accepts a string message to be displayed to the end-user.
     * @return 
        The string repsonse of the end-user. 
     */
    public String acceptStringInput(String input)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(input);
        String stringOutput = console.nextLine();
        return stringOutput; 
    }
}
