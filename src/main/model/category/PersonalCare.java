package model.category;

import model.ItemList;
import ui.CurrencyExchangeAPI;

public class PersonalCare extends Category {

    public PersonalCare(ItemList itemList, CurrencyExchangeAPI api) {
        this.itemList = itemList;
        this.api = api;
        categoryName = "personalcare";

    }
}
