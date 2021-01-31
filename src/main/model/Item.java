package model;

import ui.CurrencyExchangeAPI;

import java.util.Objects;

public class Item {

    private String itemName;
    private double price;
    private String category;
    private long time;
    private CurrencyExchangeAPI api;
    private ItemList itemList = new ItemList(api);

    public Item(String itemName, double price, String category) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    //EFFECTS: returns the item name
    public String getItemName() {
        return itemName;
    }

    //EFFECTS: returns the item price
    public double getPrice() {
        return price;
    }

    //EFFECTS: returns the item category
    public String getCategory() {
        return category;
    }

    //MODIFIES: this
    //EFFECTS: sets item name
    public void setItemName(String i) {
        this.itemName = i;
    }

    //MODIFIES: this
    //EFFECTS: set price iff price >= 0
    public void setPrice(double p) {
        this.price = p;
    }

    //MODIFIES: this
    //EFFECTS: set category
    public void setCategory(String c) {
        this.category = c;
    }

//    public void setTime(Item i) {
//        if (time == 0) {
//            this.time = System.currentTimeMillis();
//            itemList.add(i);
//        }
//    }

//    public long getTime() {
//        return time;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return price == item.price
                && Objects.equals(itemName, item.itemName)
                && Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(itemName, price, category);
    }
}