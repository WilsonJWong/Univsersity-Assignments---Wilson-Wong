public class Test
{
    public static void main (String[] args)
    {

        // Test 1
        System.out.println
            ("Test 1 - Create a Player object with the default constructor.");
        Player objPlayer1 = new Player();
        System.out.println(objPlayer1.display());

        // Test 2
        System.out.print("\nTest 2.1 - Create a Player object with the ");
        System.out.println("non-default constructor with valid fields.");
        Player objPlayer21 = new Player(8, 3, 1,false, "John");
        System.out.println(objPlayer21.display());

        System.out.print("\nTest 2.2ai - Create a Player object with the ");
        System.out.println("non-default constructor with invalid fields.");
        Player objPlayer22ai = new Player(-1, 5, 1,false, "John");
        System.out.println(objPlayer22ai.display());

        System.out.print("\nTest 2.2aii - Create a Player object with the ");
        System.out.println("non-default constructor with invalid fields.");
        Player objPlayer22aii = new Player(25, 5, 1,false, "John");
        System.out.println(objPlayer22aii.display());

        System.out.print("\nTest 2.2b - Create a Player object with the ");
        System.out.println("non-default constructor with invalid fields.");
        Player objPlayer22b = new Player(1, -5, 1, false, "John");
        System.out.println(objPlayer22b.display());
        
        System.out.print("\nTest 2.2c - Create a Player object with the ");
        System.out.println("non-default constructor with invalid fields.");
        //Player objPlayer22c = new Player(1, 5, 1, "xyz", "John");
        //System.out.println(objPlayer22c.display());

        System.out.print("\nTest 2.2di - Create a Player object with the ");
        System.out.println("non-default constructor with invalid fields.");
        Player objPlayer22di = new Player(1, 5, 1, false, "Jo");
        System.out.println(objPlayer22di.display());

        System.out.print("\nTest 2.2dii - Create a Player object with the ");
        System.out.println("non-default constructor with invalid fields.");
        Player objPlayer22dii = new Player(1, 5, 1, false, "John Doe John Doe");
        System.out.println(objPlayer22dii.display());

        System.out.print("\nTest 2.2e - Create a Player object with the ");
        System.out.println("non-default constructor with valid fields.");
        Player objPlayer22e = new Player(1, 5, 5, false, "John");
        System.out.println(objPlayer22e.display());

        System.out.print("\nTest 2.2f - Create a Player object with the ");
        System.out.println("non-default constructor with invalid fields.");
        Player objPlayer22f = new Player(1, 5, -5, false, "John");
        System.out.println(objPlayer22f.display());
        
        // Test 3
        Player objPlayer3 = new Player();

        System.out.println("\nTest 3.1 - Test getCharge().");
        System.out.println("getCharge() = " + objPlayer3.getCharge());

        System.out.println("\nTest 3.2 - Test getConsumed().");
        System.out.println("getConsumed() = " + objPlayer3.getConsumed());

        System.out.println("\nTest 3.3 - Test getEscaped().");
        System.out.println("getEscaped() = " + objPlayer3.getEscaped());

        System.out.println("\nTest 3.4 - Test getName().");
        System.out.println("getName() = " + objPlayer3.getName());

        System.out.println("\nTest 3.5 - Test getTurn().");
        System.out.println("getTurn() = " + objPlayer3.getTurn());

        // Test 4
        Player objPlayer41a = new Player();
        System.out.println
            ("\nTest 4.1a - Test setCharge() with valid fields.");
        objPlayer41a.setCharge(10);
        System.out.println
            ("setCharge(10) -> charge = " + objPlayer41a.getCharge());

        Player objPlayer41bi = new Player();
        System.out.println
            ("\nTest 4.1bi - Test setCharge() with invalid fields.");
        objPlayer41bi.setCharge(25);
        System.out.println
            ("setCharge(25) -> charge = " + objPlayer41bi.getCharge());

        Player objPlayer41bii = new Player();
        System.out.println
            ("\nTest 4.1bii - Test setCharge() with invalid fields.");
        objPlayer41bii.setCharge(-5);
        System.out.println
            ("setCharge(-5) -> charge = " + objPlayer41bii.getCharge());

        Player objPlayer42a = new Player();
        System.out.println
            ("\nTest 4.2a - Test setConsumed() with valid fields.");
        objPlayer42a.setConsumed(5);
        System.out.println
            ("setConsumed(5) -> consumed = " + objPlayer42a.getConsumed());

        Player objPlayer42b = new Player();
        System.out.println
            ("\nTest 4.2b - Test setConsumed() with invalid fields.");
        objPlayer42b.setConsumed(-3);
        System.out.println
            ("setConsumed(-3) -> consumed = " + objPlayer42b.getConsumed());

        Player objPlayer43a = new Player();
        System.out.println
            ("\nTest 4.3a - Test setEscaped() with valid fields.");
        objPlayer43a.setEscaped(true);
        System.out.println
            ("setEscaped(true) -> escaped = " +  objPlayer43a.getEscaped());

        //Player objPlayer43b = new Player();
        //System.out.println
            //("\nTest 4.3b - Test setEscaped() with invalid fields.");
        //objPlayer43b.setEscaped("xyz");
        //System.out.println
            //("setEscaped to xyz -> escaped = " + objPlayer43b.getConsumed());

        Player objPlayer44a = new Player();
        System.out.println
            ("\nTest 4.4a - Test setName() with valid fields.");
        objPlayer44a.setName("John Doe");
        System.out.println
            ("setName to John Doe -> name = " + objPlayer44a.getName());

        Player objPlayer44bi = new Player();
        System.out.println
            ("\nTest 4.4bi - Test setName() with invalid fields.");
        objPlayer44bi.setName("Jo");
        System.out.println
            ("setName to Jo -> name = " + objPlayer44bi.getName());

        Player objPlayer44bii = new Player();
        System.out.println
            ("\nTest 4.4bii - Test setName() with invalid fields.");
        objPlayer44bii.setName("John Doe John Doe");
        System.out.println
            ("setName to John Doe John Doe -> name = " 
            + objPlayer44bii.getName());

        Player objPlayer45a = new Player();
        System.out.println
            ("\nTest 4.5a - Test setTurn() with valid fields.");
        objPlayer45a.setTurn(5);
        System.out.println
            ("setTurn to 5 -> turn = " + objPlayer45a.getTurn());

        Player objPlayer45b = new Player();
        System.out.println
            ("\nTest 4.5b - Test setTurn() with invalid fields.");
        objPlayer45b.setTurn(-5);
        System.out.println
            ("setTurn to -5 -> turn = " + objPlayer45b.getTurn());

        // Test 5
        Player objPlayer51a = new Player(); 
        System.out.println
            ("\nTest 5.1a - Test chargeDecrease() with valid fields.");
        objPlayer51a.chargeDecrease(4);
        System.out.println
            ("chargeDecrease(4) = -> charge " + objPlayer51a.getCharge());

        Player objPlayer51b = new Player(); 
        System.out.println
            ("\nTest 5.1b - Test chargeDecrease() with invalid fields.");
        objPlayer51b.chargeDecrease(15);
        System.out.println
            ("chargeDecrease(15) = -> charge " + objPlayer51b.getCharge());

        Player objPlayer52a = new Player(); 
        System.out.println
            ("\nTest 5.2a - Test chargeIncrease() with valid fields.");
        objPlayer52a.chargeIncrease(4);
        System.out.println
            ("chargeIncrease(4) = -> charge " + objPlayer52a.getCharge());

        Player objPlayer52b = new Player(); 
        System.out.println
            ("\nTest 5.2b - Test chargeIncrease() with invalid fields.");
        objPlayer52b.chargeIncrease(15);
        System.out.println
            ("chargeIncrease(15) = -> charge " + objPlayer52b.getCharge());

        Player objPlayer53 = new Player(); 
        System.out.println("\nTest 5.3 - Test display().");
        System.out.println("display() = \n" + objPlayer53.display());

        Player objPlayer54 = new Player(); 
        System.out.println("\nTest 5.4 - Test incrementConsumed().");
        objPlayer54.incrementConsumed();
        System.out.println("consumed = " + objPlayer54.getConsumed());

        Player objPlayer55 = new Player(); 
        System.out.println("\nTest 5.5 - Test turnIncrease().");
        objPlayer55.turnIncrease();
        System.out.println("turn = " + objPlayer55.getTurn());
        
    }
}
