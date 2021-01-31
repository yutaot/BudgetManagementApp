package ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

import java.util.List;

public class BarChart extends javafx.scene.chart.BarChart<String, Number> {
    private Stage stage;
    private Scene scene;

    public BarChart(Stage stage, Scene scene) {
        super(new CategoryAxis(), new NumberAxis());
        this.stage = stage;
        this.scene = scene;
    }

    public void show(List<String> names, List<Integer> values) {
        for (int i = 0; i < names.size(); i++) {
            Series<String, Number> data = new Series<String, Number>();
            data.setName(names.get(i));
            data.getData().add(new Data<String, Number>(names.get(i), values.get(i)));
            getData().add(data);
        }
        ((Group) scene.getRoot()).getChildren().add(this);
        stage.setScene(scene);
        stage.show();
        this.setLayoutX(500);
    }

    public void update(List<String> names, List<Integer> values) {
        for (Series<String, Number> series : getData()) {
            int i = names.indexOf(series.getName());
            series.getData().get(0).setYValue(values.get(i));
        }
    }
}
