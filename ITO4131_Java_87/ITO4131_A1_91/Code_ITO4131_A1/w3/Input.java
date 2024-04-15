import java.util.Scanner; 

public class Input
{
    public Input()
    {

    }

    public char acceptCharInput(String input, int index)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(input);
        char charOutput = console.nextLine().trim().charAt(index);
        return charOutput;
    }

    public double acceptDoubleInput(String input)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(input);
        double doubleOutput = Double.parseDouble(console.nextLine());
        return doubleOutput;
    }

    public int acceptIntegerInput(String input)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(input);
        int intOutput = Integer.parseInt(console.nextLine());
        return intOutput;
    }

    public String acceptStringInput(String input)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(input);
        String stringOutput = console.nextLine();
        return stringOutput; 
    }
}
