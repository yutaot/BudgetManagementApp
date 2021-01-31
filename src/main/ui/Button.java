package ui;

import javafx.scene.Group;
import javafx.scene.Scene;

public class Button extends javafx.scene.control.Button {
    public Button(String label, Scene scene, int x, int y) {
        super(label);
        ((Group) scene.getRoot()).getChildren().add(this);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
