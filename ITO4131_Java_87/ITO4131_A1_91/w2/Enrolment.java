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
        String enrolDisplay = date + " " + student.getName() + " " + student.getAddress() + " " + student.getPhoneNo() + " " + student.getEmail() + " " + unit.getUnitCode() + " " + unit.getUnitDescription() + " " + unit.getCreditPoints();
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
        objUnit.setUnitCode("FIT4131 ");
        objUnit.setUnitDescription("Java Programming ");
        objUnit.setCreditPoints(6);

        Enrolment objEnrolment = new Enrolment();
        objEnrolment.setDate("01 July 2023 ");
        objEnrolment.setStudent(objStudent);
        objEnrolment.setUnit(objUnit); 
        objEnrolment.display();
        System.out.println(objEnrolment.display());
        */
    } 

}
