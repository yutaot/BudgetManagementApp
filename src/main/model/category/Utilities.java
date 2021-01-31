package model.category;

import model.ItemList;
import ui.CurrencyExchangeAPI;

public class Utilities extends Category {

    public Utilities(ItemList itemList, CurrencyExchangeAPI api) {
        this.itemList = itemList;
        this.api = api;
        categoryName = "utilities";

    }
}
