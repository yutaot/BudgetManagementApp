package model.category;

import model.ItemList;
import ui.CurrencyExchangeAPI;

public class HealthCare extends Category {

    public HealthCare(ItemList itemList, CurrencyExchangeAPI api) {
        this.itemList = itemList;
        this.api = api;
        categoryName = "healthcare";

    }
}
