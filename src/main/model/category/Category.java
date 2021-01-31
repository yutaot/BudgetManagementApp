package model.category;

import model.Item;
import model.ItemList;
import ui.CurrencyExchangeAPI;

public class Category {

    protected CurrencyExchangeAPI api;
    protected ItemList itemList;
    private double cost = 0;
    private double totalcategorycost = 0;
    protected String categoryName;

    public Category() {
    }

    //EFFECTS: returns total cost of all the items within category
    //         iff item list is not empty
    public double getTotalCost() {
        cost = 0;
        addCategoryPrice();
        return cost;
    }


    //EFFECTS: returns the percentage spent of the category
    //         iff item list is not empty
    public double spentRatio() throws EmptyListException {
        if (itemList.size() == 0) {
            throw new EmptyListException();
        } else {
            cost = 0;
            totalcategorycost = 0;
            addCategoryPrice();
            double multiply100 = cost * 100;
            return multiply100 / totalcategorycost;
        }
    }

    private void addCategoryPrice() {
        for (Item i : itemList.getItemList()) {
            totalcategorycost += i.getPrice();
            if (i.getCategory().equals(getCategoryName())) {
                cost += i.getPrice();
            }
        }
    }

    private String getCategoryName() {
        return categoryName;
    }

    public void analysis() throws EmptyListException {
        System.out.println("Spent on '" + getCategoryName() + "': " + getTotalCost() + " " + api.getCurrencyType());
        System.out.println("Percentage compared to other categories: " + spentRatio() + "%");
    }

    public class EmptyListException extends Exception {

    }
}
