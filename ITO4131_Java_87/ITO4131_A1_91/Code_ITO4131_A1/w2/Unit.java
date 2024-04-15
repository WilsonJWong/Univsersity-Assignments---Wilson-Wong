geepublic class Unit{
    
    private String unitCode; 
    private String unitDescription;
    private int creditPoints;

    // constructor
    public Unit()
    {
        unitCode = "1234567 ";
        unitDescription = "default description ";
        creditPoints = 0;
    }

    // non-default constructor
    public Unit (String newUnitCode, String newUnitDescription, int newCreditPoints)
    {
        unitCode = newUnitCode;
        unitDescription = newUnitDescription;
        creditPoints = newCreditPoints;
    }

    @Override
    public String toString() {
        return  unitCode + " " + unitDescription + " " + creditPoints;
    }

    // display
    public String display()
    {
        String unitDisplay = unitCode + " " + unitDescription + " " + creditPoints;
        return unitDisplay;
    }

    // accessor
    public int getCreditPoints()
    {
        return creditPoints;
    }

    public String getUnitCode()
    {
        return unitCode; 
    }

    public String getUnitDescription()
    {
        return unitDescription;
    }

    // mutator
    public void setCreditPoints(int newCreditPoints)
    {
        creditPoints = newCreditPoints; 
    }
    
    public void setUnitCode(String newUnitCode)
    {
        unitCode = newUnitCode;
    }

    public void setUnitDescription(String newUnitDescription)
    {
        unitDescription = newUnitDescription;
    }

    // testing 
    public static void main(String[] args)
    {

    }

}
