package model;

import ui.CurrencyExchangeAPI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ItemList implements Saveable {

    private String filename = "storeditems.txt";
    private CopyOnWriteArrayList<Item> itemList;
    private CurrencyExchangeAPI api;

    public ItemList(CurrencyExchangeAPI api) {
        itemList = new CopyOnWriteArrayList<>();
        this.api = api;
    }

    //MODIFIES: this
    //EFFECTS: adds item to the list
    public void add(Item i) {
//        if (!itemList.contains(i)) {
        itemList.add(i);
//            i.setTime(i);
//        }
    }

    //MODIFIES: this
    //EFFECTS: removes item i from list
    public void remove(Item i) {
        itemList.remove(i);
    }

    //EFFECTS: gets the item in the list at position p
    public Item get(int p) {
        return itemList.get(p);
    }

    //EFFECTS: returns the size of the list
    public int size() {
        return itemList.size();
    }

    //EFFECTS: returns itemlist
    public CopyOnWriteArrayList<Item> getItemList() {
        return itemList;
    }

    //MODIFIES: storeditems.txt
    //EFFECTS: stores all item names and prices information to storeditems.txt
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filename,"UTF-8");
        for (Item i : itemList) {
            writer.println(i.getItemName() + " " + i.getPrice() + " " + i.getCategory());
        }
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: sets the filename of save/load directory to file
    public void setStoreDir(String file) {
        this.filename = file;
    }

    public String getStoreDir() {
        return filename;
    }

    public void changeCurrencyAllItem(double currencyBase) {
        for (Item i : itemList) {
            double newPrice = i.getPrice() * currencyBase;
            i.setPrice(newPrice);
        }
    }
}