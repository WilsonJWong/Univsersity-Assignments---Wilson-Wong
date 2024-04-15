import java.util.ArrayList;

public class Enrolment
{
    private String date;
    private Student student;
    private Unit[] units; 

    // constructor 
    public Enrolment()
    {
        date = "30 Jun 2023";
        student = new Student();
        units = new Unit[1];
        units[0] = new Unit();
    }

    // non-default constructor 
    public Enrolment (String date, Student student, Unit units[])
    {
        this.date = date; 
        this.student = student; 

        this.units = new Unit [units.length];
        for(int i = 0; i < units.length; i++)
        {
            this.units[i] = units[i];
        }
    }
    
    @Override
    public String toString() {
        String enrolDisplay = date + " " + student.toString();

        for (Unit unit : units) 
        {
            enrolDisplay += " - " + unit.toString();
        }
        return enrolDisplay;
    }
    
    // display
    public String display()
    {
        String enrolDisplay = date + " " + student.display();
        for(int i = 0; i < units.length; i++)
        {
            enrolDisplay = enrolDisplay + " - " + units[i].toString();
        }
        return enrolDisplay;
    }

    public void createStudent(String name, String address, String phoneNo, String email)
    {
        student = new Student (name, address, phoneNo,email);
    }

    // accessor 
    public String getDate()
    {
        return date;
    }

    public Unit getSpecificUnit(int index)
    {
        if((index - 1) < units.length)
        {
            return units[index-1]; 
        }
        else
        {
            System.out.println("! index must be within the array length !"); 
        }

        return units[0];
    }
    
    public Student getStudent()
    {
        return student;
    }

    public Unit[] getUnits()
    {
        return units;
    }

    public int getUnitsSize()
    {
        return units.length;
    }

    // mutator 
    public void setDate(String date)
    {
        this.date = date;
    }

    public void setSpecificUnit(int index, String unitCode, String unitDescription, int creditPoints)
    {
        if((index) < units.length)
        {
            units[index] = new Unit(unitCode, unitDescription, creditPoints); 
        }         
        else
        {
            System.out.println("! index must be within the array length !"); 
        }
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public void setUnits (Unit units[])
    {
        this.units = units; 
    }

    public void setUnitsSize(int size)
    {
        if(size >= 0)
        {
            units = new Unit[size];
        }
        else
        {
            System.out.println("! ERROR - size cannot be negative !");
        } 
    }

    // main
    public static void main (String[] args)
    {
        
    } 
}
