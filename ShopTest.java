import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

public class ShopTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Tests that the set price method actually sets a new price
     */
    @Test
    public void test_setPrice() {
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        int newPrice = 10;
        comp.setPrice(newPrice);
        assertEquals(comp.price, newPrice);
    }

    /**
     * Tests that the set OS method correctly sets to the new OS
     */
    @Test
    public void test_setOS() {
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        String newOS = "My new OS";
        comp.setOS(newOS);
        assertEquals("Set OS does not accurately set new OS", comp.operatingSystem, newOS);
    }

    /**
     * Tests that the buy method throws an exception if the computer has already been bought
     * @throws Exception supposed to throw if the computer has been bought already
     */
    @Test(expected = Exception.class) 
    public void test_buyException() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        myShop.buy(comp);
        myShop.buy(comp);
    }

    /**
     * Tests that the method actually adds the computer to the inventory
     * @throws Exception supposed to throw if the computer has been bought already
     */
    @Test
    public void test_buy() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("hi", "hi", 1, 1, "hi", 1, 1);
        myShop.buy(comp);
        assertTrue("Buy does not accurately add a computer", myShop.inventory.contains(comp));
    }

    /**
     * Tests that the method accuratly throws an exception if a computer isnt in the inventory
     * @throws Exception supposed to throw if selling a computer not in inventory
     */
    @Test(expected = Exception.class)
    public void test_sellException() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("hi", "hi", 1, 1, "hi", 1, 1);
        myShop.sell(comp);
    }

    /**
     * Checks if the sell function actually removes a computer from the inventory
     * @throws Exception supposed to throw if selling a computer not in inventory
     */
    @Test
    public void test_sell() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("hi", "hi", 1, 1, "hi", 1, 1);
        myShop.inventory.add(comp);
        myShop.sell(comp);
        assertFalse(myShop.inventory.contains(comp));
    }

    /**
     * Tests that the constructor creates a inventory larger than 0
     */
    @Test
    public void test_constructor() {
        ResaleShop myShop = new ResaleShop();
        assertTrue(myShop.inventory.size() > 0);
    }

    /**
     * Sets out a stream to catch output
     */
    @Before
    public void setUp() {
    System.setOut(new PrintStream(output));
    }

    /**
     * Tests that the print inventory method actually says the Inventory is empty when it is
     */
    @Test
    public void test_printInventoryEmpty() {
        ResaleShop myShop = new ResaleShop();
        myShop.inventory.clear();
        myShop.printInventory();
        assertEquals("Inventory is empty", output.toString().trim());
    }

    /**
     * Closes the output stream
     */
    @After
    public void tearDown() {
    System.setOut(standardOut);
    }

    /**
     * Tests that the print inventory function works
     */
    @Test
    public void test_printInventoryOne() {
        ResaleShop myShop = new ResaleShop();
        try {
            myShop.printInventory();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Makes sure that the refurbish function throws exception if computer isnt in inventory
     * @throws Exception if the computer isn't in inventory
     */
    @Test(expected = Exception.class)
    public void test_refurbishException() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("hi", "hi", 1, 1, "hi", 1, 1);
        myShop.refurbish(comp, "bye");
    }

    /**
     * Makes sure that the price is set correctly for a 2017 computer
     * @throws Exception if the computer isn't in inventory
     */
    @Test
    public void test_refurbish2017() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2017 MacBook Pro", "Intel", 256, 16, "High Sierra", 2017, 1000);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals(550, comp.price);
    }

    /**
     * Makes sure that the price is set correctly for a 2020 computer
     * @throws Exception if the computer isn't in inventory
     */
    @Test
    public void test_refurbish2020() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2020, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals(1000, comp.price);
    }

    /**
     * Makes sure that the price is set correctly for a 2002 computer
     * @throws Exception if the computer isn't in inventory
     */
    @Test
    public void test_refurbish2002() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2002, 7);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals(2500, comp.price);
    }

    /**
     * Makes sure that the price is set correctly for a 1999 computer
     * @throws Exception if the computer isn't in inventory
     */
    @Test
    public void test_refurbish1999() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 1999, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals(0, comp.price);
    }

    /**
     * Makes sure that the method accuratley sets a null operating system
     * @throws Exception if the computer isn't in inventory
     */
    @Test
    public void test_refurbishNullOS() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 1999, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, null);
        assertEquals("Refurbish does not accurately set null operating system", comp.operatingSystem, null); 
    }

    /**
     * Makes sure that the method accurately sets a "None" operating system
     * @throws Exception if the computer isn't in inventory
     */
    @Test 
    public void test_refurbishNoneOS() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 1999, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "None");
        assertNotEquals("None", comp.operatingSystem);
    }

    /**
     * Makes sure that the method accurately sets a new operating system
     * @throws Exception if the computer isn't in inventory
     */
    @Test
    public void test_refurbish() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 1999, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals("Refurbish doesn't acturrately set new operating system", comp.operatingSystem, "bello"); 
    }

    /**
     * Makes sure the function accurately sets the given memory parameter
     */
    @Test
    public void test_computerConstructorMemory() {
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 10, "High Sierra", 1999, 10);
        assertEquals("Function does not accurately set memory", 10, comp.memory);
    }

    /**
     * Tests that the function accuratly sets the given price parameter
     */
    @Test
    public void test_computerConstructorPrice() {
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 10, "High Sierra", 1999, 10);
        assertEquals("Function does not accurately set price",1000, comp.price);
    }

    /**
     * Tests that the old computers have a lower price than the new computers
     * @throws Exception if the computer isn't in inventory
     */
    @Test
    public void test_refurbishPrice() throws Exception {
        Computer old = new Computer("hi", "hi", 200, 1, "hi", 2010, 100);
        Computer newer = new Computer("hi", "hi", 0, 0, "hi", 2015, 110);
        ResaleShop myShop = new ResaleShop();
        myShop.inventory.add(old);
        myShop.inventory.add(newer);
        myShop.refurbish(old, "fire");
        myShop.refurbish(newer, "blue");
        assertTrue("Logically, the old computer should have a lower price", newer.price > old.price);
    }

}
