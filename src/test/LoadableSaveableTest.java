import model.Item;
import model.ItemList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CurrencyExchangeAPI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoadableSaveableTest {

    private ItemList itemList;
    private CurrencyExchangeAPI api;
    private List<String> lines = Files.readAllLines(Paths.get("testLoad"));

    private LoadableSaveableTest() throws IOException {
    }

    @BeforeEach
    void beforeEachTest() {
        api = new CurrencyExchangeAPI();
        itemList = new ItemList(api);
    }

//    @Test
//    void testLoadItem() {
//        itemList.setStoreDir("testLoad");
//        itemList.load();
//        assertEquals(itemList.get(0).getItemName(), "apple");
//        assertTrue(itemList.get(0).getPrice() == 2);
//        assertEquals(itemList.get(0).getCategory(), "food");
//        assertEquals(itemList.get(1).getItemName(), "bread");
//        assertTrue(itemList.get(1).getPrice() == 3);
//        assertEquals(itemList.get(1).getCategory(), "food");
//    }

    @Test
    void testSaveItem() throws FileNotFoundException, UnsupportedEncodingException {
        itemList.setStoreDir("testSave");
        Item a = new Item("apple", 2, "food");
        Item c = new Item("bread", 3, "food");
        itemList.add(a);
        itemList.add(c);
        itemList.save();
    }

}
