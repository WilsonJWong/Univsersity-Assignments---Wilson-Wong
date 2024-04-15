import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter; 

public class FileIO
{
    
    private String fileName;
    
    public FileIO()
    {

    }

    public FileIO(String fileName)
    {
        this.fileName = fileName; 
    }

    public String getFileName()
    {
        return fileName; 
    }

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
                {
                    content = fileInput.nextLine();
                }
                else
                {
                    content = content + "!" + fileInput.nextLine();
                }
                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in reading from file! Exiting...");
        }
        return content; 
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName; 
    }

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
            System.out.println("Error in writing to file! Exciting...");
        }
    }
}
