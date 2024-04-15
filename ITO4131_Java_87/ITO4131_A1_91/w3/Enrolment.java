public class Enrolment
{
    private String date;
    private Student student;
    private Unit unit;  

    // constructor 
    public Enrolment()
    {
        date = "30 Jun 2023";
        student = new Student();
        unit = new Unit();
    }

    // non-default constructor 
    public Enrolment (String newDate, Student newStudent, Unit newUnit)
    {
        date = newDate; 
        student = newStudent; 
        unit = newUnit;
    }

    @Override
    public String toString() {
        return  date + " " + student + " " + unit;
    }
    
    // display
    public String display()
    {
        String enrolDisplay = date + " " + student.display() + " " + unit.display();
        return enrolDisplay; 
    }

    // accessor 
    public String getDate()
    {
        return date;
    }

    public Student getStudent()
    {
        return student;
    }

    public Unit getUnit()
    {
        return unit;
    }

    // mutator 
    public void setDate(String newDate)
    {
        date = newDate;
    }

    public void setStudent(Student newStudent)
    {
        student = newStudent;
    }

    public void setUnit (Unit newUnit)
    {
        unit = newUnit;
    }

    // main
    public static void main (String[] args)
    {
        /*
        Student objStudent = new Student();
        objStudent.setName("Wilson Wong");
        objStudent.setAddress("Melbourne");
        objStudent.setPhoneNo("0411111555");
        objStudent.setEmail("wwon0000@student.monash.edu");

        Unit objUnit = new Unit();
        objUnit.setUnitCode("FIT4131");
        objUnit.setUnitDescription("Java Programming");
        objUnit.setCreditPoints(6);

        Enrolment objEnrolment = new Enrolment();
        objEnrolment.setDate("01 July 2023");
        objEnrolment.setStudent(objStudent);
        objEnrolment.setUnit(objUnit); 
        System.out.println(objEnrolment.display());
        */

        /*
        // W3 Q3
        Input objInput = new Input();
        Student newStudent = new Student();
        Unit newUnit = new Unit();

        newStudent.setName(objInput.acceptStringInput("Name?"));
        newStudent.setAddress(objInput.acceptStringInput("Address"));
        newStudent.setPhoneNo(objInput.acceptStringInput("Phone Number?"));
        newStudent.setEmail(objInput.acceptStringInput("Email?"));
        
        newUnit.setUnitCode(objInput.acceptStringInput("Unit Code?"));
        newUnit.setUnitDescription(objInput.acceptStringInput("Unit Description?"));
        newUnit.setCreditPoints(objInput.acceptIntegerInput("Unit Credit Points?"));
        
        Enrolment objEnrolment = new Enrolment();
        objEnrolment.setDate("01 July 2023");
        objEnrolment.setStudent(newStudent);
        objEnrolment.setUnit(newUnit); 
        System.out.println(objEnrolment.display());
        */
        
    } 

}
