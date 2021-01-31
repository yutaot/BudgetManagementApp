package ui;

import model.Item;
import model.ItemList;
import model.category.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Budget implements ui.Observer {

    private double totalCost;
    private double inputPrice;
    private Scanner scanner = new Scanner(System.in);
    private CurrencyExchangeAPI api = new CurrencyExchangeAPI();
    private ItemList itemList = new ItemList(api);
    private Item item;
    private UserIncome userIncome = new UserIncome();

    private Category food = new Food(itemList, api);
    private Category entertainment = new Entertainment(itemList, api);
    private Category healthcare = new HealthCare(itemList, api);
    private Category housing = new Housing(itemList, api);
    private Category personalcare = new PersonalCare(itemList, api);
    private Category utilities = new Utilities(itemList, api);

    private List<String> lines;

    {
        try {
            lines = Files.readAllLines(Paths.get("storeditems.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
////        System.out.println("Welcome");
////        new Budget();
//    }

    public Budget() {
        try {
            this.totalCost = 0;
            this.inputPrice = 0;
            load(itemList);
            userIncome.load();
            userIncome.setIncome();
            api.load();
            api.addObserver(this);
//            run();
        } catch (IOException e) {
            System.out.println("Could not interpret input.");
        }
    }

    //EFFECTS: directs user to several methods(options)
    private void run() throws IOException {
        totalCost = getCurrentCost();
        while (true) {
            System.out.println("Select option (type 'add', 'remove', 'list', 'additional features', 'quit')");
            String option = scanner.nextLine();
            System.out.println("you selected: " + option);
            if (option.equals("add")) {
                add();
            } else if (option.equals("list")) {
                showList();
            } else if (option.equals("remove")) {
                remove();
            } else if (option.equals("additional features")) {
                additionalFeatures();
            } else if (option.equals("quit")) {
                itemList.save();
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a new item that has a name and price
    //         and updates the total cost and item list
    //         iff price >= 0
    private void add() {
        inputPrice = 0;
        System.out.println("Item Bought: ");
        String itemName = scanner.nextLine();

        try {
            inputPrice();
        } catch (NegativePriceException e) {
            System.out.println("Invalid price.");
            return;
        }

        System.out.println("Enter category name: ");
        String category = scanner.nextLine();
        scanner.nextLine();
        this.item = new Item(itemName, inputPrice, category);
        boolean x = checkContainsItem();
        notInList(x);

    }

    private void notInList(boolean x) {
        if (x) {
            addingToList(item);
        }
    }

    public void updateTotalCost() {
        totalCost += inputPrice;
        System.out.println("Your total expenses is: ");
        System.out.println(totalCost + " " + api.getCurrencyType());
    }

    private boolean checkContainsItem() {
        String option;
        if (itemList.getItemList().contains(item)) {
            System.out.println("The list already contains this item.");
            System.out.println("Do you wish to add anyways? type 'yes' or 'no'");
            option = scanner.nextLine();
            if (option.equals("yes")) {
                addingToList(item);
                return false;
            } else {
                System.out.println("Item was not added.");
                return false;
            }
        }
        return true;
    }

    public void addingToList(Item item) {
        itemList.add(item);

        try {
            itemList.save();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported encoding");
        }

        updateTotalCost();
    }

    //MODIFIES: this
    //EFFECTS: returns the user input price of item iff inputPrice > 0 otherwise throw
    //         NegativePriceException
    private void inputPrice() throws NegativePriceException {
        System.out.println("Enter amount spent: ");
        this.inputPrice = scanner.nextDouble();
        scanner.nextLine();
        if (inputPrice < 0) {
            throw new NegativePriceException();
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the item with the same name in the item list
    //TODO: WHAT HAPPENS IF THERE ARE TWO ITEMS WITH THE SAME NAME?
    private void remove() {
        System.out.println("Which item would you like to remove?");
        System.out.println("Insert item name: ");
        String itemName = scanner.nextLine();

//        if (!itemList.getItemList().contains(itemName)) {
//            System.out.println("Item not found");
//            return;
//        }
//        for (Item item : itemList.getItemList()) {
//        }
//        ArrayList<Item> templist = new ArrayList<>();

        for (Item i : itemList.getItemList()) {
            if (i.getItemName().equals(itemName)) {
                itemList.remove(i);
                totalCost -= i.getPrice();
                System.out.println("Item successfully removed.");
            }
        }
    }

    //EFFECTS: prints all the items (with price) of the list
    //         if no items in the list, prints "no items"
    public void showList() {
        if (itemList.size() == 0) {
            System.out.println("No items in the list");
        } else {
            for (Item s : itemList.getItemList()) {
                System.out.println(s.getItemName() + ": " + s.getPrice() + " " + api.getCurrencyType());
            }
            System.out.println("Total: " + getTotalCost() + " " + api.getCurrencyType());
        }
    }

    //EFFECTS: returns the total cost of item list
    public double getTotalCost() {
        return totalCost;
    }

    //MODIFIES: this
    //EFFECTS: returns current cost of all items in the list
    private int getCurrentCost() {
        int cost = 0;
        for (Item i : itemList.getItemList()) {
            cost += i.getPrice();
        }
        return cost;
    }

    private void additionalFeatures() {
        while (true) {
            System.out.println("Select option (type 'analysis' or 'convert currency' or 'back')");
            String select = scanner.nextLine();
            System.out.println("you selected: " + select);
            if (select.equals("analysis")) {
                analyze();
            } else if (select.equals("convert currency")) {
                System.out.println("Type currency you want to convert to: ");
                String currency = scanner.nextLine();
                try {
                    changeCurrencyBase(api.getCurrencyExchange(currency));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (select.equals("back")) {
                break;
            }
        }
    }

    public void changeCurrencyBase(double currencyBase) {
        //System.out.println("Successfully converted currency to " + api.getCurrencyType());
        this.totalCost = totalCost * currencyBase;
        api.setCurrencyType();
        itemList.changeCurrencyAllItem(currencyBase);
    }

    private void analyze() {
        try {
            food.analysis();
            entertainment.analysis();
            healthcare.analysis();
            housing.analysis();
            personalcare.analysis();
            utilities.analysis();
        } catch (Category.EmptyListException e) {
            System.out.println("Your item list must have at least one item to use 'analysis'");
        } finally {
            if (totalCost < userIncome.getIncome()) {
                System.out.println("Good work! You are saving money!");
            } else {
                System.out.println("Maybe you should spend less");
            }
        }
    }

    @Override
    public void update() {
        System.out.println("Successfully converted currency to " + api.getCurrencyType());
    }

    //MODIFIES: this
    //EFFECTS: loads all items in storeditems.txt
    public void load(ItemList itemList) {
        for (String i : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(i);
            Item item = new Item(partsOfLine.get(0), Double.parseDouble(partsOfLine.get(1)), partsOfLine.get(2));
            itemList.add(item);
        }
    }

    //EFFECTS: splits a line up by spaces
    private ArrayList<String> splitOnSpace(String item) {
        String[] splits = item.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public static class NegativePriceException extends Exception {

    }
}

