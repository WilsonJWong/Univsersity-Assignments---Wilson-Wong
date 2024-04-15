public class Validation
{
    public Validation()
    {

    }

    public boolean isBlank(String input)
    {
        if(input=="")
            return true;
        else
            return false;
    }

    public boolean lengthWithinRange(String input, int minVal, int maxVal)
    {
        if ((input.length() > minVal) && (input.length() < maxVal))
            return true;
        else 
            return false;
    }
}
