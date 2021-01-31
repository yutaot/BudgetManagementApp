package model.category;

import model.ItemList;
import ui.CurrencyExchangeAPI;

public class Housing extends Category {

    public Housing(ItemList itemList, CurrencyExchangeAPI api) {
        this.itemList = itemList;
        this.api = api;
        categoryName = "housing";
    }
}
