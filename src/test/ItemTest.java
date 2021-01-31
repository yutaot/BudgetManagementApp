import model.Item;
import model.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemTest {

    private Item i;
    private String itemName;
    private double price;
    private String category;

    @BeforeEach
    void beforeEachTest() {
        i = new Item("a", 1, "food");
        this.itemName = "a";
        this.price = 1;
        this.category = "food";
    }

    @Test
    void testConstructor() {
        assertTrue(i.getItemName().equals("a"));
        assertTrue(i.getPrice() == 1);
    }

    @Test
    void testGetName() {
        assertTrue(i.getItemName().equals("a"));
        assertFalse(i.getItemName().equals("b"));
    }

    @Test
    void testGetNameNull() {
        Item h = new Item(null, 2, "food");
        assertTrue(h.getItemName() == null);
    }

    @Test
    void testGetPrice() {
        assertTrue(i.getPrice() == 1);
        assertFalse(i.getPrice() == 2);
    }

    @Test
    void testGetCategory() {
        assertTrue(i.getCategory().equals("food"));
        assertFalse(i.getCategory().equals("furniture"));
    }

    @Test
    void testSetName() {
        i.setItemName("b");
        assertTrue(i.getItemName().equals("b"));
        i.setItemName(null);
        assertTrue(i.getItemName() == null);
    }

    @Test
    void testSetPrice() {
        i.setPrice(2);
        i.setPrice(3);
        assertTrue(i.getPrice() == 3);
    }

    @Test
    void testSetCategory() {
        i.setCategory("clothes");
        assertTrue(i.getCategory().equals("clothes"));
        i.setCategory(null);
        assertTrue(i.getCategory() == null);
    }

//    @Test
//    void testSetPriceExceptionFail() {
//        try {
//            i.setPrice(-2);
//            fail("should not see this");
//        } catch (Budget.NegativePriceException e) {
//            assertTrue(true);
//        }
//    }
//
//    @Test
//    void testSetPriceExceptionWorks() {
//        try {
//            i.setPrice(2);
//            assertTrue(true);
//        } catch (Budget.NegativePriceException e) {
//            fail("should not see this");
//        }
//    }


    @Test
    void testEqualsTrue() {
        Item item = new Item("a", 1, "food");
        assertTrue(item.equals(i));
    }

    @Test
    void testEqualsFalse() {
        Item item = new Item("b", 1, "food");
        assertFalse(item.equals(i));
    }

    @Test
    void testEqualsFalseTwo() {
        Item item = new Item("b", 2, "entertainment");
        assertFalse(item.equals(i));
    }

    @Test
    void testEqualsFalseThree() {
        Item item = new Item("a", 1, "entertainment");
        assertFalse(item.equals(i));
    }

    @Test
    void testEqualsNotAnInstanceOf() {
        Category category = new Category();
        assertFalse(i.equals(category));
    }

    @Test
    void testHashCode() {
        assertTrue(i.hashCode() == Objects.hash(itemName, price, category));
    }
}
