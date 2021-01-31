package model.category;

import model.ItemList;
import ui.CurrencyExchangeAPI;

public class Entertainment extends Category {

    public Entertainment(ItemList itemList, CurrencyExchangeAPI api) {
        this.itemList = itemList;
        this.api = api;
        categoryName = "entertainment";
    }
}
