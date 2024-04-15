import java.util.ArrayList;

public class University
{
    private ArrayList<Enrolment> enrolments;
    private final String inputName = "students.txt";
    private final String outputName = "output.txt"; 

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

    public void addUnitToExisting (University objUniversity)
    {
        Input inputOption = new Input();
        Enrolment enrolChange = new Enrolment();

        int editLine = 0;
        boolean check = false; 
        do
        {
            try
            {
                editLine = inputOption.acceptIntegerInput("Which line number of the student would you like to enrol into another unit?");
                enrolChange = objUniversity.getSpecificEnrolment((editLine -1));
                check = true;
            }
            catch (Exception e)
            {
                System.out.println("! Student line number must be an integer !");
            }
        } while (check == false);

        Enrolment newEnrol = new Enrolment();
        newEnrol.setDate(enrolChange.getDate());
        newEnrol.setStudent(enrolChange.getStudent());
        newEnrol.setUnitsSize((enrolChange.getUnitsSize()+1));

        int numExist = enrolChange.getUnitsSize();
        for (int i = 0; i<numExist; i++)
        {
            // need to reconfigure the display format and then do a split to get each value and replace the setSpecificUnit method with such obtained values
            newEnrol.setSpecificUnit(i, "FITXXXX", "Description", 6);
        }
        inputUnitDetailsOnce((newEnrol.getUnitsSize()-1), newEnrol);
        objUniversity.setSpecificEnrolment((editLine -1),newEnrol);
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

        boolean flag = false; 
        int numUnits = 0;

        do 
        {
            try
            {
                do 
                {   
                    numUnits = inputNum.acceptIntegerInput("How many units is the student enrolled in?");
                    if (numUnits <= 0 )
                    {
                        System.out.println("! Units must be greater than 0 !");
                    }
                    else
                    {
                        flag = true;
                    }
                } while (numUnits <= 0);
                
            }
            catch (Exception e)
            {
                System.out.println("Error. Please enter a numeric value");
            }
        } while(flag == false);
        
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
        boolean flag = false; 
        int unitCredit = 0;

        do
        {
            try
            {
                do
                {
                    unitCredit = inputUnit.acceptIntegerInput("Please enter the unit credit points.");
                    if (unitCredit < 0)
                    {
                        System.out.println("! Unit credit points cannot be less than 0 !");
                    }   
                    else
                    {
                        flag = true; 
                    }
                } while (unitCredit < 0);   
            }
            catch (Exception e)
            {
                System.out.println("! Error. Please enter a numeric value !");
            }
        } while(flag == false);
        

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

    public void removeSpecificUnit(University objUniversity)
    {
        
        Input inputOption = new Input();
        Enrolment enrolChange = new Enrolment();
        int editLine = 0;
        boolean check = false; 
        do
        {
            try
            {
                editLine = inputOption.acceptIntegerInput("Which line number of the student would you like to remove a unit for?");
                if (editLine <=0 || editLine > objUniversity.getEnrolmentSize())
                {
                    System.out.println("! Please enter a valid line number !");
                }
                else
                {
                    enrolChange = objUniversity.getSpecificEnrolment((editLine -1));
                    check = true;
                }  
            }
            catch (Exception e)
            {
                System.out.println("! Student line number must be an integer !");
            }
        } while (check == false);

        Enrolment newEnrol = new Enrolment();
        newEnrol.setDate(enrolChange.getDate());
        newEnrol.setStudent(enrolChange.getStudent());
        newEnrol.setUnitsSize((enrolChange.getUnitsSize()-1));
        
        int editUnit = 0; 
        boolean check1 = false;

        do
        {
            try
            {
                editUnit = inputOption.acceptIntegerInput("Which unit position number you like to remove?");
                if (editUnit <= 0 || editUnit > enrolChange.getUnitsSize())
                {
                    System.out.println("! Please enter a valid position number !");
                }
                else
                {
                    check1 = true;
                }
                        
            }
            catch (Exception e)
            {
                System.out.println("! Unit position number must be an integer !");
            }
        } while (check1 == false);

        int numUniExist = enrolChange.getUnitsSize() - 1;
        for (int i = 0; i<numUniExist; i++)
        {
            if(numUniExist == 1 && i == (editUnit-1))
            {
                // need to reconfigure the display format and then do a split to get each value and replace the setSpecificUnit method with such obtained values
                // this one is for getting the second value 
                 newEnrol.setSpecificUnit(i, "FIT1XXX", "Description", 6);
            }
             else
            {
                newEnrol.setSpecificUnit(i, "FIT2XXX", "Description", 6);
            }
        }
        objUniversity.setSpecificEnrolment((editLine -1),newEnrol);
    }

    public void readFile()
    {
        Enrolment tempEnrolment = new Enrolment();
        FileIO objFile = new FileIO();

        objFile.setFileName(inputName);
        String rawData = objFile.readFile();
        String[] lineTemp = rawData.split("!");
        
        for (String line: lineTemp)
        {
            String[] tempData = line.split(",");

            Enrolment tempEnrol = new Enrolment();

            tempEnrol.setDate(tempData[0]);
            tempEnrol.createStudent(tempData[1], tempData[2], tempData[3], tempData[4]);

            String[] tempUnit = tempData[5].split(";");
            tempEnrol.setUnitsSize(tempUnit.length);

            for(int i=0; i<tempUnit.length; i++)
            {
                String[] tempUnitInfo = tempUnit[i].split("-");
                tempEnrol.setSpecificUnit(i, tempUnitInfo[0], tempUnitInfo[1], Integer.parseInt(tempUnitInfo[2]));
            } 

            enrolments.add(new Enrolment(tempEnrol.getDate(), tempEnrol.getStudent(), tempEnrol.getUnits()));
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
        FileIO objFile = new FileIO();

        //setting filname to read
        objFile.setFileName(inputName);
        objUniversity.readFile();

        // repeating enrolment until exit
        int counter = 0;
        int selection = 0;

        do
        {
            // option menu
            do
            {
                try
                {
                selection = inputOption.acceptIntegerInput("1) Select 1 to view student \n2) Select 2 to enrol a student \n3) Select 3 to add one enrolment to a student \n4) Select 4 to remove one enrolment from a student \n5) Select 5 to remove student \n6) Select 6 to exit the program");
                    
                    if (selection != 1 && selection != 2 && selection != 3 && selection != 4 && selection != 5 && selection != 6)
                    {
                        System.out.println("! Please enter a valid option !");
                    }
                }
                catch (Exception e)
                {
                    System.out.println("! Please enter a numeric value !");
                }
            } while (selection != 1 && selection != 2 && selection != 3 && selection != 4 && selection != 5 && selection != 6);

            if (selection == 1)
            {
                objUniversity.display();
            }
            else if (selection == 2)
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
            else if (selection == 3)
            {
                objUniversity.addUnitToExisting(objUniversity);
            }
            else if (selection == 4)
            {
                objUniversity.removeSpecificUnit(objUniversity);
            }
            else if (selection == 5)
            {
                boolean check = false; 
                do
                {
                    try
                    {
                        objUniversity.removeEnrolment(inputOption.acceptIntegerInput("Which line number of the student would you like to remove?"));
                        check = true;
                    }
                    catch (Exception e)
                    {
                        System.out.println("! Student line number must be an integer !");
                    }
                } while (check == false);
            }
            else if (selection == 6)
            {
                objUniversity.writeFile();
                System.out.println("Exited the program.");
            }
        } while (selection != 6);
    } 

    public void writeFile()
    {
        FileIO writeOut = new FileIO();

        String finalOut = "";

        writeOut.setFileName(outputName);
        for (Enrolment enrolment : enrolments) 
        {
            if (finalOut.equals(""))
            {
                finalOut = enrolment.toString();
            }
            else
            {
                finalOut = finalOut + "\n" + enrolment.toString(); 
            }
        } 

        writeOut.writeFile(finalOut);
    }

    public void addUnitEnrolment(String input)
    {
        String[] lineTemp = input.split(",");
        String name = "";

        if(lineTemp.length == 4)
        {
            name = lineTemp[0];

            try
            {
                Unit unit = new Unit(lineTemp[1], lineTemp[2], Integer.parseInt(lineTemp[3]));
            }
            catch(Exception e)
            {
                System.out.println("Error! New unit could not be created as there was a(n) " + e.getMessage());
            }
        }
        else 
        {
            System.out.println("Error! There are not exactly 4 values.");
        }

        Enrolment enrolmentSearch = new Enrolment();
        boolean flagFound = false; 
        for (Enrolment enrolment : enrolments) 
        {
            if (enrolment.getStudent().getName().equals(name)) 
            {
                enrolmentSearch = enrolment;
                flagFound = true;
                break;
            }
        }

        if (flagFound == false)
            System.out.println("Error! " + name + " is not an enrolled student.");
        else
        {
            enrolmentSearch.setSpecificUnit(lineTemp[1], lineTemp[2], lineTemp[3])
        }
    }
}
