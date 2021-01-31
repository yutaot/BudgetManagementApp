package ui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Item;
import model.ItemList;
import model.category.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GuiFX extends Application {

    private Item item = new Item("null", 0, "null");
    private Item itemRemove = new Item("null", 0, "null");
    private CurrencyExchangeAPI api = new CurrencyExchangeAPI();
    private ItemList itemList = new ItemList(api);
    private Budget budget = new Budget();
    private Scene scene;
    private Scene thirdScene;
    private PieChart pieChart;
    private BarChart barChart;
    private ListViewFX listViewFX = new ListViewFX(itemList);
    private String currencyType;

    private Category food = new Food(itemList, api);
    private Category entertainment = new Entertainment(itemList, api);
    private Category healthcare = new HealthCare(itemList, api);
    private Category housing = new Housing(itemList, api);
    private Category personalcare = new PersonalCare(itemList, api);
    private Category utilities = new Utilities(itemList, api);


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        createScene(stage);
        addItemNameField();
        addItemPriceField();
        addItemCategoryField();
        addItemButton();
        addShowItemListButton(stage);
        addConvertCurrencyButton();
        addConvertCurrencyField();
        removeItemNameField();
        removeItemPriceField();
        removeItemCategoryField();
        removeItemButton();
        addImage(stage);
//        addConvertCurrencyLabel();
        addAnalysisButton(stage);
        budget.load(itemList);
        stage.setScene(scene);
        stage.show();
    }

    private void createScene(Stage stage) {
        scene = new Scene(new Group(), Color.SKYBLUE);
        stage.setWidth(570);
        stage.setHeight(270);
        stage.setTitle("Budget Application");
    }

    private void addImage(Stage stage) {
        Image image = new Image("https://2.bp.blogspot.com/-xyUcjya7HR8/U82zM5Ox0xI/AAAAAAAAjMk/wsjK8A3sV0g/s500/family_syunyu.png");
        ImageView imageView = new ImageView(image);
        VBox box = new VBox();
        box.getChildren().add(imageView);
        ((Group) scene.getRoot()).getChildren().add(box);
        box.setLayoutX(420);
        box.setLayoutY(100);
        imageView.setFitHeight(100);
//        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);
        stage.setScene(scene);
    }

    private void addItemNameField() {
        new Field("Name", scene, 10, 10).textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String name = String.valueOf(newValue);
                item.setItemName(name);
            }
        });
    }

    private void addItemPriceField() {
        new Field("Price", scene, 110, 10).textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                Label label = new Label("", scene, 350, 180);
                try {
                    double price = Double.valueOf(newValue);
                    item.setPrice(price);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input");
                }
            }
        });
    }

    private void addItemCategoryField() {
        new Field("Category", scene, 210, 10).textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String category = String.valueOf(newValue);
                item.setCategory(category);
            }
        });
    }

    private void addItemButton() {
        new Button("Add Item", scene, 310, 27)
                .setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Item i = new Item(item.getItemName(), item.getPrice(), item.getCategory());
                        itemList.add(i);
                        listViewFX.updateList();
                        try {
                            itemList.save();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        replaceNewFields();
                    }
                });
    }

    private void replaceNewFields() {
        addItemNameField();
        addItemPriceField();
        addItemCategoryField();
    }

    private void removeItemNameField() {
        new Field("Name", scene, 10, 150).textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String name = String.valueOf(newValue);
                itemRemove.setItemName(name);
            }
        });
    }

    private void removeItemPriceField() {
        new Field("Price", scene, 110, 150).textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    double price = Double.valueOf(newValue);
                    itemRemove.setPrice(price);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }
        });
    }

    private void removeItemCategoryField() {
        new Field("Category", scene, 210, 150).textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String category = String.valueOf(newValue);
                itemRemove.setCategory(category);
            }
        });
    }

    private void removeItemButton() {
        new Button("Remove Item", scene, 310, 167)
                .setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        itemList.remove(itemRemove);
                        try {
                            itemList.save();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        removeItemNameField();
                        removeItemPriceField();
                        removeItemCategoryField();
                    }
                });
    }

    private void addShowItemListButton(Stage stage) {
        new Button("Show Item List", scene, 390, 27).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                StackPane secondaryLayout = new StackPane();
                setUpNew(stage, secondaryLayout);

                ObservableList<String> list = FXCollections.observableArrayList();
                for (Item item  : itemList.getItemList()) {
                    list.add(item.getItemName() + " " + item.getPrice() + "   " + item.getCategory());
                }

                doItemListSave();
                makeCells(stage, secondaryLayout, list);
            }
        });
    }

    private void doItemListSave() {
        try {
            itemList.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void makeCells(Stage stage, StackPane secondaryLayout, ObservableList<String> list) {
        ListView<String> lv = new ListView<>(list);
        lv.setCellFactory(param -> new ListViewFX.XCell());
        secondaryLayout.getChildren().add(lv);
        stage.show();
//                        listViewFX.start(stage);
    }

    private void setUpNew(Stage stage, StackPane secondaryLayout) {
        Scene secondScene = new Scene(secondaryLayout, 300, 150);
        Stage newWindow = new Stage();
        newWindow.setTitle("Item List");
        newWindow.setScene(secondScene);

        newWindow.setX(stage.getX() + 200);
        newWindow.setY(stage.getY() + 100);
        newWindow.show();
    }

    private void addConvertCurrencyButton() {
        new Button("Convert Currency", scene, 110, 97).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    itemList.changeCurrencyAllItem(api.getCurrencyExchange(currencyType));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                api.setCurrencyType();
                try {
                    itemList.save();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addConvertCurrencyField() {
        new Field("Currency type: ", scene, 10, 80).textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String currencyType = String.valueOf(newValue);
                setCurrencyTypeField(currencyType);
//                notifyObserver();
            }
        });
    }

//    private void notifyObserver() {
//        updateLabel();
//    }

//    private void addConvertCurrencyLabel() {
//        new Label("Current currency type: " + updateLabel(), scene, 10, 130) {
//        };
//
//
//    }
//
//    private String updateLabel() {
//        return api.getCurrencyType();
//    }

    private void setCurrencyTypeField(String currencyType) {
        this.currencyType = currencyType;
    }

    private void addAnalysisButton(Stage stage) {
        new Button("Analysis", scene, 310, 97).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                thirdScene = new Scene(new Group());

                Stage newWindow = new Stage();
                setUpWindow(newWindow, stage);

                ArrayList<String> categories = new ArrayList<>();
                addToCategories(categories);

                ArrayList<Integer> price = new ArrayList<>();
                addToPrice(price);

                pieChart = new PieChart(newWindow, thirdScene);
                pieChart.show(categories, price);
                barChart = new BarChart(newWindow, thirdScene);
                barChart.show(categories, price);
            }
        });
    }

    private void setUpWindow(Stage newWindow, Stage stage) {
        newWindow.setTitle("Analysis");
        newWindow.setScene(thirdScene);
        newWindow.setX(stage.getX() - 300);
        newWindow.setY(stage.getY() + 100);
        newWindow.setWidth(1050);
        newWindow.setHeight(450);
        newWindow.show();
    }

    private void addToCategories(ArrayList<String> categories) {
        categories.add("food");
        categories.add("entertainment");
        categories.add("heath care");
        categories.add("housing");
        categories.add("personal care");
        categories.add("utilities");
    }

    private void addToPrice(ArrayList<Integer> price) {
        price.add((int) Math.round(food.getTotalCost()));
        price.add((int) Math.round(entertainment.getTotalCost()));
        price.add((int) Math.round(healthcare.getTotalCost()));
        price.add((int) Math.round(housing.getTotalCost()));
        price.add((int) Math.round(personalcare.getTotalCost()));
        price.add((int) Math.round(utilities.getTotalCost()));

    }
}
