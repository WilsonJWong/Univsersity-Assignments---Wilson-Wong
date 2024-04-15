/**
 * This class calculates the jumps manages the game's state progression.
 * @author Wilson Wong
 * version ver 1.0.0
 */

import java.util.ArrayList;

public class Jumper
{
    private int currentBuildNumber;
    private ArrayList<Building> buildings; 

    /**
     * Default constructor which creates the object of the class Jumper.
     * Intialises curentBuildNumber to 1 and 
        buildings as a new ArrayList of type Building
     */
    public Jumper()
    {   
        currentBuildNumber = 1;
        buildings = new ArrayList<Building>();
    }

    /**
     * Non-default constructor which creates the object of the class Jumper.
     * @param currentBuildNumber    
        Accepts the current building number the player is on as an integer.
     * @param buildings
        Accepts the collection of buildings as an arraylist of Building type.
     */
    public Jumper
        (int currentBuildNumber, ArrayList<Building> buildings)
    {
        if(currentBuildNumber > 0)
            this.currentBuildNumber = currentBuildNumber;
        else 
            this.currentBuildNumber = 1; 

        try
        {
            this.buildings = buildings;
        }
        catch(Exception e)
        {
            buildings = new ArrayList<Building>();
        }    
    }

    /**
     * Creates a random building number that is not the current building number.
     * @return 
        A random number that is not the current building number as an integer. 
     */
    public int createRandomBuildNumber()
    {
        int randomNumber = 0;
        do 
        {
            randomNumber = (int) (Math.random() * (buildings.size()) + 1);
        } while (randomNumber == currentBuildNumber);
        return randomNumber;
    }

    /**
     * Display method to return the state of the Jumper object.
     * @return  The state of the Jumper object as a string.
     */
    public void display(String visual[])
    {
        System.out.print("\nP = Player, X = Exit Portal, F = Fuel Cell, ");
        System.out.print("W = Police Web, * = Frozen Building, ");
        System.out.println("~ = Building \n");

        for (int m = 0; m < visual.length; m++)
        {
            System.out.println(visual[m]);
        }
    }

    /**
     * Accessor method to get the collection of buildings.
     * @return An array list of the buildings of Building type. 
     */
    public ArrayList<Building> getBuildings()
    {
        return buildings;
    }

    /**
     * Accessor method to get the current building number the player is on. 
     * @return The current building number the player is on as an integer. 
     */
    public int getCurrentBuildNumber()
    {
        return currentBuildNumber;
    }

    /**
     * Method to test if the jump can be performed within the boundaries.
     * @param choice
        Accepts the movement choice of the player as an integer.
     * @param position
        Accepts the current position of the player as an integer.
     * @param jump
        Accepts the number of buildings the player will jump as an integer.  
     * @param buildingCount
     */
    public boolean isJumpValid
        (int choice, int position, int jump)
    {
        boolean jumpValid = false; 
        switch(choice)
        {
            case 1: 
                if (position + jump <= buildings.size())
                    jumpValid = true; 
                break; 
            case 2: 
                if (position - jump > 0)
                    jumpValid = true;
                break;
            case 3:
                jumpValid = true;
                break;
        }
        return jumpValid;
    }

    /**
     * Randomize the buildings order.
     */
    public void randomizeBuildingsOrder()
    {
        int size = buildings.size();
        int randomIndex = 0;
        Building temp = new Building(); 

        do 
        {
            for (int i = 0; i < size; i++)
            {
                randomIndex = (int) (Math.random() * (i+1));
                temp = buildings.get(i);
                buildings.set(i, buildings.get(randomIndex));
                buildings.set(randomIndex, temp);
            }
        } while
        (buildings.get((currentBuildNumber - 1)).getHasExitPortal() == true || 
            buildings.get((currentBuildNumber - 1)).getHasFuelCell() == true);
    }

    /**
     * Randomize a building to be frozen.
     */
    public void randomizeFreezePosition()
    {
        int randomFrozen = (int) (Math.random() * (buildings.size()));
        for (Building building : buildings)
        {
            building.setHasFreeze(false);
        }
        buildings.get(randomFrozen).setHasFreeze(true);
    }

    /**
     * Randomize buildings to have fuel cells. 
     */
    public void randomizeFuelPosition()
    { 
        //generate the new position for fuel cells
        int fuelNumber = 4;
        int[] uniqueNumber = new int[fuelNumber];
        int uniqueCount = 0;

        while(uniqueCount < fuelNumber)
        {
            int randomIndex = createRandomBuildNumber();
            
            boolean isUnique = true; 
            for (int i = 0; i < uniqueCount; i++)
            {
                if (uniqueNumber[i] == randomIndex)
                {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique == true)
            {
                uniqueNumber[uniqueCount] = (randomIndex);
                uniqueCount++;
            }
        }

        for (int i = 0; i < uniqueNumber.length; i++)
        {
            uniqueNumber[i] = uniqueNumber[i] - 1;
        }

        // setting the new position for fuel cells
        int buildCounter = 0;
        for (Building building : buildings)
        {
            building.setHasFuelCell(false);
            for (int i = 0; i < (uniqueNumber.length); i++)
            {
                if (uniqueNumber[i] == buildCounter)
                    building.setHasFuelCell(true); 
            }
            buildCounter++;
        }  
    }

    /**
     * Randomize a building to have a web trap.
     */
    public void randomizeWebPosition()
    {
        int randomWeb = createRandomBuildNumber();
        for (Building building : buildings)
        {
            building.setHasWeb(false);
        }
        buildings.get((randomWeb - 1)).setHasWeb(true);
    }

    /**
     * Read the contents of the specified file to create buildings. 
     */
    public void readFile()
    {
        FileIO objFile = new FileIO();
        objFile.setFileName("buildings.txt");
        String rawInput = objFile.readFile();
        int lineCount = 1;

        String[] lineTemp = rawInput.split("!");
        for (String line: lineTemp)
        { 
            String[] temp = line.split(",");

            try 
            {
                Building tempBuilding = new Building
                    (Integer.parseInt(temp[0]), 
                    Boolean.parseBoolean(temp[1]), 
                    Boolean.parseBoolean(temp[2]), 
                    Boolean.parseBoolean(temp[3]), 
                    Boolean.parseBoolean(temp[4]));

                buildings.add(tempBuilding); 
            }
            catch (Exception E)
            {
                System.out.print("\nVariables from text file do not match ");
                System.out.print("the required input! "); 
                System.out.print("Setting line " + lineCount);
                System.out.println(" to default values instead.");
                buildings.add(new Building());
            }        
            lineCount++;
        }
    }

    /**
     * Removes a fuel cell once it has been consumed. 
     */
    public void removeConsumedFuel()
    {   
        buildings.get(currentBuildNumber - 1).setHasFuelCell(false);
    }

    /**
     * Mutator method to set the current building number. 
     * @param currentBuildNumber
        Accepts the current building number as an integer. 
     */
    public void setCurrentBuildingNum(int currentBuildNumber)
    {
        if(currentBuildNumber > 0)
            this.currentBuildNumber = currentBuildNumber;
    }

    /**
     * Mutator method to set the collection of buildings.
     * @param buildings
        Accepts the collection of buildings as an arraylist of Building type.
     */
    public void setBuildings(ArrayList<Building> buildings)
    {
        try
        {
            this.buildings = buildings; 
        }
        catch (Exception e)
        {
            System.out.println
                ("No change was made as invalid input data type.");
        }
        
    }

    /**
     * Transpose the data in the buildings arraylist to the desired data format.
     * @param turn  The current turn as an integer
     * @return The transposed data in an array. 
     */
    public String[] transposeData(int turn)
    {
        // max height calculation for dynamic 
        int maxHeight = 0; 
        for (Building building : buildings) 
        {
            String[] variables = building.display().split(" ");
            int numberOfVariables = variables.length;
            int thisHeight = numberOfVariables + building.getHeight();
            
            if (thisHeight > maxHeight)
                maxHeight = thisHeight;
        }

        // temp array for display
        String[] visual = new String [maxHeight];
        for(int v = 0; v < visual.length; v++)
        {
            visual[v] = "";
        }
            
        // every third turn change fuel cells position
        if ((turn - 1) >= 3 && (turn - 1) % 3 == 0)
        {
            randomizeFuelPosition();
            System.out.println("\nReset Fuel");
        }
            
        // every turn change freeze position 
        if (turn > 1)
        {
            randomizeFreezePosition();
            randomizeWebPosition();
        }

        // transpose and simplify the data
        for (Building building : buildings) 
        {   
            // display player
            int after = buildings.size() - currentBuildNumber;
            int before = currentBuildNumber - 1; 
            String spaceAfter = "";
            String spaceBefore = "";

            for (int i = after; i < buildings.size(); i++)
            {
                spaceAfter += "   ";
            } 
            for (int j = 0; j < before; j++)
            {
                spaceBefore += "   ";
            }
            visual[0] = spaceBefore + "  P" + spaceAfter;

            // display exit portal
            if (building.getHasExitPortal() == true)
                visual[1] += "  X";
            else
                visual[1] += "   ";
    
            // display fuelCell
            if (building.getHasFuelCell() == true)
                visual[2] += "  F";
            else
                visual[2] += "   ";

            // display web
            if (building.getHasWeb() == true)
                visual[3] += "  W";
            else
                visual[3] += "   ";    

            // display frozen
            if (building.getHasFreeze() == true)
                visual[4] += "  *";
            else
                visual[4] += "   ";     

            // display floors 
            int ref = 5;
            int randomBuildHeight = (int) (Math.random() * 5) + 1;
            if(turn > 1)
                building.setHeight(randomBuildHeight); 
            for(int i = 5; i < visual.length; i++)
            {
                if (building.getHeight() >= ref)
                    visual[i] += "  ~";
                else 
                    visual[i] += "   ";
                ref--;
            }
        } 
        return visual;
    }

    /**
     * Updates the current building number the player is on after a jump.
     * @param choice
        Accepts the choice of the players action as an integer.
     * @param currentBuildNumber
        Accepts the current building number the player is on as an integer.
     * @param buildHeight
        Accepts the current building height the player is on as an integer.
     */
    public void updateCurrentBuild
        (int choice, int currentBuildNumber, int buildHeight)
    {
        switch(choice)
        {
            case 1: 
                this.currentBuildNumber = currentBuildNumber + buildHeight;
                break;
            case 2: 
                this.currentBuildNumber = currentBuildNumber - buildHeight; 
                break;
            case 3:
                break;
        }
    }

    /**
     * Updates the player's fuel charge after the encounters. 
     * @param objPlayer
        Accepts the object of the Player class.
     */
    public void updateCharge(Player objPlayer)
    {
        Building buildingCheck = buildings.get(currentBuildNumber - 1);

        if(buildingCheck.getHasExitPortal() == true 
            && buildingCheck.getHasFreeze() == false)
        {
            objPlayer.setEscaped(true);
        }
        else if(buildingCheck.getHasExitPortal() == true 
            && buildingCheck.getHasFreeze() == true)
        {
            objPlayer.chargeDecrease(1);
        }

        if(buildingCheck.getHasFuelCell() == true)
        {
            objPlayer.chargeIncrease(5);
            objPlayer.incrementConsumed();
            System.out.println("For consuming fuel charge increased by 5.");
        }  

        if(buildingCheck.getHasWeb() == true)
        {
            objPlayer.chargeDecrease(5);
            System.out.print("For getting caught in a ");
            System.out.println("web setup fuel charge decreased by 5.");
        }  
    }

    /**
     * Writes the final outcome of the game. 
     * @param objPlayer
        Accepts the object of the Player class. 
     */
    public void writeFile(Player objPlayer)
    {
        FileIO writeOut = new FileIO();
        writeOut.setFileName("outcome.txt");

        String messageOut = "Player " + objPlayer.getName() 
            + " final stats: \n \n";
        messageOut = messageOut + " - Turns taken in the game: " 
            + (objPlayer.getTurn() - 2) + "\n";
        messageOut = messageOut + " - Fuel cells consumed: " 
            + objPlayer.getConsumed() + "\n";
        if (objPlayer.getEscaped() == true)
        {
            messageOut = messageOut 
                + " \nYou have successfully escaped and won!";
        }   
        else
        {
            messageOut = messageOut 
                + " \nInsufficient fuel cells to continue. You have lost!";
        }

        writeOut.writeFile(messageOut);
    }
    
    /**
     * Method to run the program.
     * @param args  An array of Strings representing command line arguments.
     */
    public static void main(String[] args)
    {
        Player objPlayer = new Player();
        Input objInput = new Input();
        Menu objMenu = new Menu();
        Jumper objJumper = new Jumper();

        // display rules
        objMenu.displayExplanationMenu();

        // player name 
        boolean flagName = false;
        String name = ""; 
        do
        {
            name = objInput.acceptStringInput
                ("Please enter player name (3-12 characters only)");

            if ((name.length() >= 3) && (name.length() <= 12))
                flagName = true;
            else 
                System.out.println
                ("Error! Name must be between 3 to 12 characters long.");

        } while (flagName == false);
        objPlayer.setName(name);

        // read file 
        objJumper.readFile();
        System.out.println("\nTurn: " + objPlayer.getTurn());
        System.out.println("Fuel charge level: " + objPlayer.getCharge());

        objJumper.display(objJumper.transposeData(objPlayer.getTurn()));
        objPlayer.turnIncrease();

        // repeating turns
        do 
        {
            // turn action cost  
            boolean flagAction = false;
            boolean flagValid = true; 
            boolean forwardBackwards = true;
            int buildCurrentNumber = objJumper.getCurrentBuildNumber();
            Building building = 
                objJumper.getBuildings().get((buildCurrentNumber - 1));

            if (building.getHasFreeze() == true)
            {
                forwardBackwards = false; 
                System.out.println("\nPlayer has landed on a frozen building!");
            }
            do
            {    
                int choice = objMenu.displayTurnMenu();
                if (forwardBackwards == false && choice != 3)
                {
                    System.out.print("\nBuilding is frozen! "); 
                    System.out.println("Must select 3 to skip turn.");
                }
                else if(objJumper.isJumpValid(choice, buildCurrentNumber, 
                    building.getHeight()) == true)
                {
                    if (flagValid == false)
                        objJumper.setCurrentBuildingNum(buildCurrentNumber);

                    objJumper.updateCurrentBuild
                        (choice, buildCurrentNumber, building.getHeight());
                    Building newBuilding = 
                        objJumper.getBuildings().
                        get((objJumper.getCurrentBuildNumber()) - 1);

                    if (newBuilding.getHasFreeze() == true 
                        && newBuilding.getHasExitPortal() == true)
                    {
                        System.out.print("\nAction not valid as the building ");
                        System.out.print("the player is jumping to has both ");
                        System.out.println("the exit portal and is frozen!");
                        flagValid = false; 
                    } 
                    else
                    {
                        int difference = 
                            Math.abs(building.getHeight() 
                            - newBuilding.getHeight());
                        if (choice == 3)
                        {
                            objPlayer.chargeDecrease(1);
                            System.out.print("\nFor skipping a turn ");
                            System.out.println("decrease fuel cell by 1");
                        }   
                        else
                        {
                            objPlayer.chargeDecrease(difference + 1);
                            System.out.println("\nCost of jump was " 
                                + (difference + 1) 
                                + " (absolute value of (" 
                                + building.getHeight() 
                                + " - " + newBuilding.getHeight() 
                                + ") + 1)");    
                        }
                        flagAction = true;
                    }
                }    
                else
                {
                    System.out.print("Please select a valid option ");
                    System.out.println("within the boundaries.");
                }
                    
            } while (flagAction == false);

            // post action selection update
            objJumper.updateCharge(objPlayer);
            if (objPlayer.getEscaped() == false && objPlayer.getCharge() > 0)
            {
                Building building1 = 
                    objJumper.getBuildings().
                    get((objJumper.getCurrentBuildNumber()) - 1);

                objJumper.removeConsumedFuel();

                // freeze action
                if (building1.getHasFreeze() == true 
                    && forwardBackwards == true)
                {  
                    System.out.print("Building is frozen! Skipping ");
                    System.out.println("turn and decrease fuel cell by 1.");
                    Building buildingTemp = new Building();
                    do 
                    {
                        objJumper.randomizeBuildingsOrder();
                        System.out.println("\nTurn: " + objPlayer.getTurn() 
                            + "\nFuel charge level: " 
                            + objPlayer.getCharge());
                        objJumper.display(objJumper.transposeData(objPlayer.getTurn()));
                        objPlayer.turnIncrease(); 
                        objPlayer.chargeDecrease(1);
                        objJumper.updateCharge(objPlayer);
                        objJumper.removeConsumedFuel();
                        buildingTemp = objJumper.getBuildings().
                            get((objJumper.getCurrentBuildNumber()) - 1);
                    } while (buildingTemp.getHasFreeze() == true);
                }
                // checking if frozen action made player escape successfully 
                if (objPlayer.getEscaped() == false)
                {
                    objJumper.randomizeBuildingsOrder();
                    System.out.println("\nTurn: " + objPlayer.getTurn() 
                        + "\nFuel charge level: " 
                        + objPlayer.getCharge());
                    objJumper.display(objJumper.transposeData(objPlayer.getTurn()));
                } 
            }
            else if (objPlayer.getEscaped() == true)
            {
                System.out.print("\n*** You have successfully ");
                System.out.println("escaped and won! ***");
            }
            else if(objPlayer.getCharge() <= 0)
            {
                System.out.print("\n*** Insufficient fuel cells to continue. ");
                System.out.println("You have lost! ***");
            }
    
            objPlayer.turnIncrease(); 
            
        } while(objPlayer.getEscaped() == false && objPlayer.getCharge() > 0);

        // Write file test 
        objJumper.writeFile(objPlayer);
    }

}
