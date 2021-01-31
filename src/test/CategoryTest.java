import model.Item;
import model.ItemList;
import model.category.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CurrencyExchangeAPI;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class CategoryTest {

    private Category food;
    private Category entertainment;
    private Category healthcare;
    private Category housing;
    private Category personalcare;
    private Category utilities;
    private CurrencyExchangeAPI api;
    private ItemList itemList;

    @BeforeEach
    void setUp() {
        api = new CurrencyExchangeAPI();
        itemList = new ItemList(api);
        food = new Food(itemList, api);
        entertainment = new Entertainment(itemList, api);
        healthcare = new HealthCare(itemList, api);
        housing = new Housing(itemList, api);
        personalcare = new PersonalCare(itemList, api);
        utilities = new Utilities(itemList, api);
    }

    @Test
    void testNoItemInListGetTotalCost() {
        assertTrue(food.getTotalCost() == 0);
    }

    @Test
    void testItemsInListGetTotalCost() {
        Item a = new Item("apple", 2, "food");
        Item b = new Item("chair", 10, "housing");
        itemList.add(a);
        itemList.add(b);
        assertTrue(food.getTotalCost() == 2);
        assertTrue(housing.getTotalCost() == 10);
    }

    @Test
    void testNoItemInListSpentRatio() {
        try {
            food.spentRatio();
            fail("should not show this");
        } catch (Category.EmptyListException e) {
            assertTrue(true);
        }
    }

    @Test
    void testItemsInListSpentRatio() {
        Item a = new Item("apple", 2, "food");
        Item b = new Item("chair", 10, "housing");
        itemList.add(a);
        itemList.add(b);
        try {
            food.spentRatio();
            assertTrue(true);
        } catch (Category.EmptyListException e) {
            fail("should not catch");
        }
    }

    @Test
    void testAnalysisNoItemInList() {
        try {
            food.analysis();
            fail("should not show this");
        } catch (Category.EmptyListException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAnalysisYesItems() {
        Item a = new Item("apple", 2, "food");
        itemList.add(a);
        try {
            food.analysis();
            assertTrue(true);
        } catch (Category.EmptyListException e) {
            fail("should not show this");
        }
    }

}

