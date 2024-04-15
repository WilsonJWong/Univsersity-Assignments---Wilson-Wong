/**
 * This class reads and writes data to a file. 
 * @author Wilson Wong
 * version ver 1.0.0
 */

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter; 

public class FileIO
{

    private String fileName;
    
    /**
     * Default constructor which creates the object of the class FileIO.
     * Intialises fileName as an empty string. 
     */
    public FileIO()
    {
        fileName = ""; 
    }

    /**
     * Non-default constructor which creates the object of the class FileIO.
     * @param fileName
        Accepts a string for the name of the file. 
     */
    public FileIO(String fileName)
    {
        this.fileName = fileName; 
    }

    /**
     * Display method to return the state of the FileIO object.
     * @return  The state of the FileIO object as a string.
     */
    public String display()
    {
        String output = "Name of file: " + fileName;
        return output; 
    }

    /**
     * Accessor method to return the name of the file.
     * @return  The name of the file.
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Reads the contents of a file and returns it as a single string. 
     * @return  The contents of the file as a single string.
     */
    public String readFile()
    {
        String content = ""; 

        try
        {
            FileReader reader = new FileReader(fileName);
            Scanner fileInput = new Scanner(reader);

            int i = 0; 
            while (fileInput.hasNextLine())
            {
                if(i==0)
                    content = fileInput.nextLine();
                else
                    content = content + "!" + fileInput.nextLine();
                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in reading from file!");
        }
        return content; 
    }

    /**
     * Mutator method to set the name of the file. 
     * @param fileName
        Accepts a string for the name of the file.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName; 
    }

    /**
     * Writes the contents from a string onto the file. 
     * @param line
        Accepts a string containing the contents to be written.
     */
    public void writeFile(String line)
    {
        try
        {
            FileWriter writer = new FileWriter(fileName); 
            writer.append(line);
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in writing to file!");
        }
    }

}
