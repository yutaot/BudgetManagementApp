package ui;

import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends javafx.scene.chart.PieChart {
    private Stage stage;
    private Scene scene;

    public PieChart(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    public void show(List<String> names, List<Integer> values) {
        List<Data> data = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            data.add(new Data(names.get(i), values.get(i)));
        }
        setData(FXCollections.observableArrayList(data));
        ((Group) scene.getRoot()).getChildren().add(this);
        stage.setScene(scene);
        stage.show();
    }

    public void update(List<String> names, List<Integer> values) {
        for (Data entry : getData()) {
            int idx = names.indexOf(entry.getName());
            if (idx != -1) {
                entry.setPieValue(values.get(idx));
            }
        }
    }
}
