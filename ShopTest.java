import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

public class ShopTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Tests methods in resale shop and computer classes
    */

    @Test
    public void test_setPrice() {
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        int newPrice = 10;
        comp.setPrice(newPrice);
        assertEquals(comp.price, newPrice);
    }

    @Test
    public void test_setOS() {
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        String newOS = "My new OS";
        comp.setOS(newOS);
        assertEquals("Set OS does not accurately set new OS", comp.operatingSystem, newOS);
    }

    @Test(expected = Exception.class) 
    public void test_buyException() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        myShop.buy(comp);
        myShop.buy(comp);
    }

    @Test
    public void test_buy() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("hi", "hi", 1, 1, "hi", 1, 1);
        myShop.buy(comp);
        assertTrue("Buy does not accurately add a computer", myShop.inventory.contains(comp));
    }

    @Test(expected = Exception.class)
    public void test_sellException() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("hi", "hi", 1, 1, "hi", 1, 1);
        myShop.sell(comp);
    }

    @Test
    public void test_sell() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("hi", "hi", 1, 1, "hi", 1, 1);
        myShop.inventory.add(comp);
        myShop.sell(comp);
        assertFalse(myShop.inventory.contains(comp));
    }

    @Test
    public void test_constructor() {
        ResaleShop myShop = new ResaleShop();
        assertTrue(myShop.inventory.size() > 0);
    }

    @Before
    public void setUp() {
    System.setOut(new PrintStream(output));
    }

    @Test
    public void test_printInventoryEmpty() {
        ResaleShop myShop = new ResaleShop();
        myShop.inventory.clear();
        myShop.printInventory();
        assertEquals("Inventory is empty", output.toString().trim());
    }

    @After
    public void tearDown() {
    System.setOut(standardOut);
    }

    @Test
    public void test_printInventoryOne() {
        ResaleShop myShop = new ResaleShop();
        try {
            myShop.printInventory();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_refurbishException() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("hi", "hi", 1, 1, "hi", 1, 1);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bye");
    }

    @Test
    public void test_refurbish2017() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2017, 1000);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals(550, comp.price);
    }

    @Test
    public void test_refurbish2020() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2020, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals(1000, comp.price);
    }

    @Test
    public void test_refurbish2002() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2002, 7);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals(2500, comp.price);
    }

    @Test
    public void test_refurbish1999() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 1999, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals(0, comp.price);
    }

    @Test
    public void test_refurbishNullOS() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 1999, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, null);
        assertEquals("Refurbish doesn't accurately set null operating system", comp.operatingSystem, null); 
    }

    @Test 
    public void test_refurbishNoneOS() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 1999, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "None");
        assertNotEquals("None", comp.operatingSystem);
    }

    @Test
    public void test_refurbish() throws Exception {
        ResaleShop myShop = new ResaleShop();
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 1999, 10);
        myShop.inventory.add(comp);
        myShop.refurbish(comp, "bello");
        assertEquals("Refurbish doesn't acturrately set new operating system", comp.operatingSystem, "bello"); 
    }

    @Test
    public void test_computerConstructorMemory() {
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 10, "High Sierra", 1999, 10);
        assertEquals("Function does not accurately set memory", 10, comp.memory);
    }

    @Test
    public void test_computerConstructorPrice() {
        Computer comp = new Computer("2019 MacBook Pro", "Intel", 256, 10, "High Sierra", 1999, 10);
        assertEquals("Function does not accurately set price",1000, comp.price);
    }

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
