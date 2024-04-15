public class Student{

    private String name;
    private String address;
    private String phoneNo;
    private String email;

    // constructor
    public Student()
    {   
        name = "default name ";
        address = "default address ";
        phoneNo = "XXXXXXXXXX ";
        email = "default@student.monash.edu";
    }

    // non-default constructor
    public Student (String newName, String newAddress, String newPhoneNo, String newEmail)
    {
        name = newName;
        address = newAddress;
        phoneNo = newPhoneNo;
        email = newEmail;
    }

    @Override
    public String toString() {
        return  name + " " + address + " " + phoneNo + " " + email;
    }

    // display
    public String display()
    {
        String stuDisplay = (name + " " + address + " " + phoneNo + " " + email);
        return stuDisplay;
    }

    // accessor
    public String getAddress()
    {
        return address;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNo()
    {
        return phoneNo; 
    }

    // mutator
    public void setAddress(String newAddress)
    {
        address = newAddress;
    }

    public void setEmail(String newEmail)
    {
        email = newEmail;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public void setPhoneNo(String newPhoneNo)
    {
        phoneNo = newPhoneNo;
    }

    // testing 
    public static void main (String[] args)
    {

    }

}
