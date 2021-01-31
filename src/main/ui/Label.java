package ui;

import javafx.scene.Group;
import javafx.scene.Scene;

public class Label extends javafx.scene.control.Label {
    public Label(String label, Scene scene, int x, int y) {
        super(label);
        ((Group) scene.getRoot()).getChildren().add(this);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
