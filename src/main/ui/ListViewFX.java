package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Item;
import model.ItemList;

public class ListViewFX extends Application {

    private Scene scene;
    private ItemList itemList;
    private Budget budget = new Budget();
    private ObservableList<String> list = FXCollections.observableArrayList();


    public ListViewFX(ItemList itemList) {
        this.itemList = itemList;
    }

    static class XCell extends ListCell<String> {

        HBox hbox = new HBox();
        Label label = new Label("");
        Pane pane = new Pane();
        Button button = new Button("Del");

        public XCell() {
            super();

            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(event -> getListView().getItems().remove(getItem()));
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                label.setText(item);
                setGraphic(hbox);
            }
        }
    }


    public void start(Stage primaryStage) {
        Stage newWindow = new Stage();
        scene = new Scene(new Group());
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
        primaryStage.setScene(scene);
        primaryStage.show();

        budget.load(itemList);

        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 300, 150);
        primaryStage.setScene(scene);

        for (Item item  : itemList.getItemList()) {
            list.add(item.getItemName() + " " + item.getPrice() + "   " + item.getCategory());
        }

        ListView<String> lv = new ListView<>(list);
        lv.setCellFactory(param -> new XCell());
        pane.getChildren().add(lv);
        primaryStage.show();
    }

    public void updateList() {
        for (Item item  : itemList.getItemList()) {
            list.add(item.getItemName() + " " + item.getPrice() + "   " + item.getCategory());
        }
    }

    public ObservableList<String> getList() {
        return list;
    }

    public static void main(String[] args) {
        launch(args);
    }
}