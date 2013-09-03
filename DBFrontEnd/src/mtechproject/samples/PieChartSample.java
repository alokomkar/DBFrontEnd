package mtechproject.samples;

import mtechproject.dbfrontend.FloggerData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
 
public class PieChartSample extends Application {
 
    @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Flogger Statistics");
        stage.setWidth(600);
        stage.setHeight(600);
         
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Content Copy", FloggerData.FloggerCCopy()),
                new PieChart.Data("Total Sessions", FloggerData.FloggerSessionCount()),
                new PieChart.Data("New File Operations", FloggerData.FloggerFileCreate()),
                new PieChart.Data("Read Only Login", FloggerData.FloggerROSession()),
                new PieChart.Data("Suspicious Activity", FloggerData.FloggerSuspicious()));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Flogger Statistics");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}