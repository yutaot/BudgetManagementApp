//import model.Item;
//import model.ItemList;
//import model.category.Category;
//import model.category.Food;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class FoodTest {
//
//    private Category f;
//    private ItemList itemList = new ItemList();
//    private Item a = new Item("apple", 2, "food");
//    private Item b = new Item("bread", 3, "food");
//    private Item c = new Item("chair", 30, "furniture");
//    private Item d = new Item("darkchocolate", 5, "food");
//
//    @BeforeEach
//    void beforeEachTest() throws IOException {
//
//        f = new Food(itemList);
//        itemList.add(a);
//        itemList.add(b);
//        itemList.add(c);
//        itemList.add(d);
//    }
//
//    @Test
//    void testGetTotalCost() {
//        assertEquals(f.getTotalCost(), 10);
//    }
//
//    @Test
//    void testSpentRatio() {
//        assertEquals(f.spentRatio(), 1/3*100);
//    }
//}
