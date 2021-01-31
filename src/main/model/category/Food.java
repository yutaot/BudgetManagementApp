package model.category;

import model.ItemList;
import ui.CurrencyExchangeAPI;

public class Food extends Category {

    public Food(ItemList itemList, CurrencyExchangeAPI api) {
        this.itemList = itemList;
        this.api = api;
        categoryName = "food";
    }
}
