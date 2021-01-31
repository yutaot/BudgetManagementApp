package ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Field extends javafx.scene.control.TextField {
    public Field(String labelText, Scene scene, int x, int y) {
        Label label = new Label();
        label.setText(labelText);
        VBox box = new VBox();
        box.getChildren().add(label);
        box.getChildren().add(this);
        ((Group) scene.getRoot()).getChildren().add(box);
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setMaxWidth(100);
    }
}
