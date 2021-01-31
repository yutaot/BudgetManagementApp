import model.Item;
import model.ItemList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CurrencyExchangeAPI;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemListTest {

//    private List<String> lines;
//
//    {
//        try {
//            lines = Files.readAllLines(Paths.get(filename));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private CurrencyExchangeAPI api;
    private ItemList list;
    private Item a;

    @BeforeEach
    void setup() {
        api = new CurrencyExchangeAPI();
        list = new ItemList(api);
        a = new Item("apple", 2, "food");
    }

    @Test
    void testAdd() {
        list.add(a);
        assertEquals("apple", list.get(0).getItemName());
        assertTrue(2 == list.get(0).getPrice());
        assertEquals("food", list.get(0).getCategory());
    }

    @Test
    void testRemove() {
        list.add(a);
        list.remove(a);
        assertTrue(list.size() == 0);
    }

    @Test
    void testGet() {
        list.add(a);
        assertEquals(list.get(0).getItemName(), "apple" );
    }

    @Test
    void testSize() {
        assertTrue(list.size() == 0);
        list.add(a);
        assertTrue(list.size() == 1);
    }

    @Test
    void testSetStoreDir() {
        list.setStoreDir("a");
        assertEquals("a", list.getStoreDir());
    }

    @Test
    void testGetStoreDir() {
        list.setStoreDir("abc");
        assertEquals(list.getStoreDir(), "abc");
    }

//    @Test
//    void testShowListNoItem() {
//        list.showList(budget);
//        assertTrue(list.size() == 0);
//    }

//    @Test
//    void testShowListYesItem() {
//        list.add(a);
//        list.showList(budget);
//        assertTrue(list.size() == 1);
//    }

    @Test
    void testSaveItem() throws FileNotFoundException, UnsupportedEncodingException {
        list.setStoreDir("testSave");
        Item a = new Item("apple", 2, "food");
        Item c = new Item("bread", 3, "food");
        list.add(a);
        list.add(c);
        list.save();
    }

//    @Test
//    void testLoadItem() {
//        list.setStoreDir("testLoad");
//        list.load();
//        assertEquals(list.get(0).getItemName(), "apple");
//        assertTrue(list.get(0).getPrice() == 2.0);
//        assertEquals(list.get(0).getCategory(), "food");
////        assertEquals(list.get(1).getItemName(), "bread");
////        assertTrue(list.get(1).getPrice() == 3.0);
////        assertEquals(list.get(1).getCategory(), "food");
//    }

    @Test
    void testChangeCurrencyOneItem() {
        list.add(a);
        list.changeCurrencyAllItem(100);
        assertTrue(list.get(0).getPrice() == 200.0);
    }

    @Test
    void testChangeCurrencyAllItem() {
        Item item = new Item("pie", 100, "food");
        list.add(a);
        list.add(item);
        list.changeCurrencyAllItem(100);
        assertTrue(list.get(0).getPrice() == 200.0);
        assertTrue(list.get(1).getPrice() == 10000.0);
    }

}
