package mtechproject.dbfrontend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LineChartSample extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("File Number");
        yAxis.setLabel("Time in milliseconds");
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setData(getChartData());
        lineChart.setTitle("Encryption and Access Overhead Compared");
        primaryStage.setTitle("LineChart example");
        StackPane root = new StackPane();
        root.getChildren().add(lineChart);
        primaryStage.setScene(new Scene(root, 1366, 760));
        primaryStage.show();
    }
    private ObservableList<Series<Double, Double>> getChartData() {
      //double aValue = 1.56;
      //double cValue = 1.06;
      ObservableList<XYChart.Series<Double, Double>> answer = FXCollections.observableArrayList();
      Series<Double, Double> aSeries = new Series<Double, Double>();
      Series<Double, Double> cSeries = new Series<Double, Double>();
      aSeries.setName("Encryption");
      cSeries.setName("Access");
      Connection con = null;
      try {
		con = DBConnectFlogger.connect();
		String st = "Select * from performance_metrics";
		ResultSet rs = con.createStatement().executeQuery(st);
		Double i = 1.0;
		while(rs.next()){
			aSeries.getData().add(new XYChart.Data(i, Double.parseDouble(rs.getString(1))));
			cSeries.getData().add(new XYChart.Data(i, Double.parseDouble(rs.getString(2))));
			i = i + 1.0;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      answer.addAll(aSeries, cSeries);
      return answer;
    }
}