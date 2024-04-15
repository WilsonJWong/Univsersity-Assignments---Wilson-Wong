import java.util.ArrayList;
import java.util.Iterator; 

public class University
{
    private ArrayList<Enrolment> enrolments;

    public University()
    {
       enrolments = new ArrayList<Enrolment>();
    }

    public University (ArrayList<Enrolment> enrolments)
    {
        this.enrolments = enrolments;
    }

    public void display()
    {
        for (Enrolment enrolment : enrolments) 
        {
            System.out.println(enrolment.display());
        }  
    }

    public void addEnrolment(Enrolment enrolment)
    {
        enrolments.add(enrolment); 
    }

    public ArrayList<Enrolment> getEnrolments()
    {
        return enrolments;
    }

    public int getEnrolmentSize()
    {
        return enrolments.size();
    }

    public Enrolment getSpecificEnrolment(int index)
    {
        if (index >= 0 && index < enrolments.size()) 
        {
            return enrolments.get(index);
        } 
        else 
        {
            System.out.println("! Index is not within the enrolment size. Returning first index !");
            return enrolments.get(0);
        }        
    }

    public void inputStudentDetails(Enrolment enrolment)
    {
        Input inputStudent = new Input();
        Validation inputVal = new Validation();
        Student newStudent = new Student();

        // name
        String stuName = "";
        do
        {
            stuName = inputStudent.acceptStringInput("Please enter student name.");
            if (inputVal.isBlank(stuName) == true)
            {
                System.out.println("! Name cannot be blank !");
            }
        } while (inputVal.isBlank(stuName) == true);

        // address
        String stuAddress = "";
        do
        {
            stuAddress = inputStudent.acceptStringInput("Please enter student address.");
            if (inputVal.isBlank(stuAddress) == true)
            {
                System.out.println("! Address cannot be blank !");                
            }
        } while (inputVal.isBlank(stuAddress) == true);

        // phone number
        String stuPhoneNum = "";
        do
        {
            stuPhoneNum = inputStudent.acceptStringInput("Please enter the student phone number.");
            if (inputVal.isBlank(stuPhoneNum) == true)
            {
                System.out.println("! Phone number cannot be blank !");
            }
        } while (inputVal.isBlank(stuPhoneNum) == true);   
        
        // email
        String stuEmail = "";
        do 
        {
            stuEmail = inputStudent.acceptStringInput("Please enter the student email.");
            if (inputVal.isBlank(stuEmail) == true)
            {
                System.out.println("! Email cannot be blank !");
            }
        } while(inputVal.isBlank(stuEmail) == true);

        enrolment.createStudent(stuName, stuAddress, stuPhoneNum, stuEmail);
        System.out.println("Student has been created");
    }

    public void inputUnitDetails(Enrolment enrolment)
    {
        Input inputNum = new Input();
        University objUniUnit = new University(); 

        int numUnits = 0;
        do 
        {   
            numUnits = inputNum.acceptIntegerInput("How many units is the student enrolled in?");
            if (numUnits <= 0 )
            {
                System.out.println("! Units must be greater than 0 !");
            }
        } while (numUnits <= 0);

        enrolment.setUnitsSize(numUnits);

        for (int i = 0; i < numUnits; i++)
        {
            objUniUnit.inputUnitDetailsOnce(i, enrolment); 
        }
    }

    public void inputUnitDetailsOnce(int index, Enrolment enrolment)
    {
        Input inputUnit = new Input();
        Validation inputVal = new Validation();
        Unit newUnit = new Unit();

        // unit code
        String unitCode = "";
        do
        {
            unitCode = inputUnit.acceptStringInput("Please enter the unit code.");
            if (inputVal.isBlank(unitCode) == true)
            {
                System.out.println("! Unit code cannot be blank !");
            }
            else if (inputVal.lengthWithinRange(unitCode,6,8) == false)
            {
                System.out.println("! Unit code must be 7 characters long !");
            }   
        } while (inputVal.isBlank(unitCode) == true || inputVal.lengthWithinRange(unitCode,6,8) == false);   

        // unit description
        String unitDescription = "";
        do 
        {
            unitDescription = inputUnit.acceptStringInput("Please enter the unit description.");
            if (inputVal.isBlank(unitDescription) == true)
            {
                System.out.println("! Unit description cannot be blank !");
            }
            else if (inputVal.lengthWithinRange(unitDescription,0,25) == false)  
            {
                System.out.println("! Unit description must be less than 25 characters long !");
            } 
        } while(inputVal.isBlank(unitDescription) == true || inputVal.lengthWithinRange(unitDescription,0,25) == false);

        // unit credit points
        int unitCredit = 0;
        do
        {
            unitCredit = inputUnit.acceptIntegerInput("Please enter the unit credit points.");
            if (unitCredit < 0)
            {
                System.out.println("! Unit credit points cannot be less than 0 !");
            }   
        } while (unitCredit < 0);

        enrolment.setSpecificUnit(index, unitCode, unitDescription, unitCredit);
        System.out.println("Unit has been created");
    }

    public void removeEnrolment(int index)
    {
        if (index > 0 && index <= enrolments.size()) 
        {
            enrolments.remove(index-1);
        } 
        else 
        {
            System.out.println("! Index is not within the enrolments size !");
        }
    }


    public void setEnrolments(ArrayList<Enrolment> enrolments)
    {
        this.enrolments = enrolments;
    }

    public void setSpecificEnrolment(int index, Enrolment enrolment)
    {
        if (index >= 0 && index < enrolments.size()) 
        {
            enrolments.set(index, enrolment);
        } 
        else 
        {
            System.out.println("! Index is not within the enrolments size !");
        }
    }
    
    // method to start program
    public void startProgram()
    {
        University objUniversity = new University();
        Input inputOption = new Input();
        Validation dateVal = new Validation();

        // repeating enrolment until exit
        int counter = 0;
        int selection = 0;

        do
        {
            // option menu
            do
            {
                selection = inputOption.acceptIntegerInput("1) Select 1 to enrol a student \n2) Select 2 to exit the program");
                
                if (selection != 1 && selection != 2)
                {
                    System.out.println("! Please enter a valid option !");
                }

            } while (selection != 1 && selection != 2);

            if (selection == 1)
            {
                // enrolment date
                String enrolDate = "";
                do
                {
                    enrolDate = inputOption.acceptStringInput("Please enter the enrolment date.");
                    if (dateVal.isBlank(enrolDate) == true)
                    {
                        System.out.println("! Date cannot be blank !");
                    }
                    else 
                    {
                        objUniversity.addEnrolment(new Enrolment());
                        objUniversity.getSpecificEnrolment(counter).setDate(enrolDate);
                    }
                } while (dateVal.isBlank(enrolDate) == true);
                
                objUniversity.inputStudentDetails(objUniversity.getSpecificEnrolment(counter));
                objUniversity.inputUnitDetails(objUniversity.getSpecificEnrolment(counter));
                System.out.println(objUniversity.getSpecificEnrolment(counter));
                counter++;
            }
            else if (selection == 2)
            {
                objUniversity.display();
                System.out.println("Exited the program.");
            }
        } while (selection != 2);

    } 

}
