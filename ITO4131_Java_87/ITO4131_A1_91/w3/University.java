public class University
{
    private Enrolment enrolments;

    public University()
    {
        enrolments = new Enrolment();
    }

    public University (Enrolment enrolments)
    {
        this.enrolments = enrolments;
    }

    public String display()
    {
        String output = enrolments.display();
        return output;
    }

    public Enrolment getEnrolment()
    {
        return enrolments;
    }

    public void setEnrolment(Enrolment enrolments)
    {
        this.enrolments = enrolments;
    }

    public void inputStudentDetails(Enrolment enrolments)
    {
        Input inputStudent = new Input();
        Validation inputVal = new Validation();
        Student newStudent = new Student();
        
        // name
        String stuName = inputStudent.acceptStringInput("Please enter student name.");
                
        if (inputVal.isBlank(stuName) == true)
        {
            System.out.println("Name cannot be blank. Applying default name instead!");
        }
        else 
        {
            newStudent.setName(stuName);
        }

        // address
        String stuAddress = inputStudent.acceptStringInput("Please enter student address.");
                
        if (inputVal.isBlank(stuAddress) == true)
        {
            System.out.println("Address cannot be blank. Applying default address instead!");                
        }
        else 
        {
            newStudent.setAddress(stuAddress);
        }

        // phone number
        String stuPhoneNum = inputStudent.acceptStringInput("Please enter the student phone number.");
                
        if (inputVal.isBlank(stuPhoneNum) == true)
        {
            System.out.println("Phone number cannot be blank. Applying default phone number instead!");
        }
        else 
        {
            newStudent.setPhoneNo(stuPhoneNum);
        }

        // email
        String stuEmail = inputStudent.acceptStringInput("Please enter the student email.");
                
        if (inputVal.isBlank(stuEmail) == true)
        {
            System.out.println("Email cannot be blank. Applying default email instead!");
        }
        else 
        {
            newStudent.setEmail(stuEmail);
        }

        this.enrolments.setStudent(newStudent);
        System.out.println("Student has been created");
        
    }

    public void inputUnitDetailsOnce(int x, Enrolment enrolments)
    {
        Input inputUnit = new Input();
        Validation inputVal = new Validation();
        Unit newUnit = new Unit();

        // unit code
        String unitCode = inputUnit.acceptStringInput("Please enter the unit code.");

        if (inputVal.isBlank(unitCode) == true)
        {
            System.out.println("Unit code cannot be blank. Applying default unit code instead!");
        }
        else 
        {
            if (inputVal.lengthWithinRange(unitCode,6,8) == true)  
            {
                newUnit.setUnitCode(unitCode);
            } 
            else
            {
                System.out.println("Unit code must be 7 characters long. Applying default unit code instead!");
            }
        }

        // unit description
        String unitDescription = inputUnit.acceptStringInput("Please enter the unit description.");

        if (inputVal.isBlank(unitDescription) == true)
        {
            System.out.println("Unit description cannot be blank. Applying default unit description instead!");
        }
        else 
        {
            if (inputVal.lengthWithinRange(unitDescription,0,25) == true)  
            {
                newUnit.setUnitDescription(unitDescription);
            } 
            else
            {
                System.out.println("Unit description must be less than 25 characters long. Applying default unit description instead!");
            }
        }

        // unit credit points
        int unitCredit = inputUnit.acceptIntegerInput("Please enter the unit credit points.");

        if (unitCredit < 0)
        {
            System.out.println("Unit credit points cannot be less than 0. Applying default unit credit points instead!");
        } 
        else
        {
            newUnit.setCreditPoints(unitCredit);
        }    

        this.enrolments.setUnit(newUnit);
        System.out.println("Unit has been created");
    }

    public static void main (String[] args)
    {
        University objUniversity = new University();
        Input inputOption = new Input();
        Validation dateVal = new Validation();

        // option menu
        int selection = inputOption.acceptIntegerInput("Select 1 to enrol a student \nSelect 2 to exit the program");

        if (selection == 1)
        {
            // enrolment date
            String enrolDate = inputOption.acceptStringInput("Please enter the enrolment date.");

            if (dateVal.isBlank(enrolDate) == true)
            {
                System.out.println("Date cannot be blank. Applying default date instead!");
            }
            else 
            {
                objUniversity.getEnrolment().setDate(enrolDate);
            }

            
            objUniversity.inputStudentDetails(objUniversity.getEnrolment());
            objUniversity.inputUnitDetailsOnce(0, objUniversity.getEnrolment());
            System.out.println(objUniversity.display());
        }
        else
        {
            System.out.println("Exited the program.");
        }
    }
}
