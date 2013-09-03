package mtechproject.samples;


import java.util.Arrays;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class DynamicBarChart extends Application {

	//For initialising JavaFX Front End
	private void init(Stage primaryStage) {

		//Horizontal Box
		HBox root = new HBox();
		primaryStage.setScene(new Scene(root));

		//Text Area for display of Statistics Data
		final TextArea text = new TextArea ("");
		text.setMaxHeight(100);
		text.setMaxWidth(200);
		root.getChildren().addAll(createChart(text),text);
	}

	//Method for creation of chart.
	protected BarChart<String, Number> createChart(final TextArea text) {

		final String[] specialities = {"Statistic1", "Statistic2", "Statistic3"};
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

		// setup chart
		bc.setTitle("Dynamic Bar Chart");
		xAxis.setLabel("Statistics");
		xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(specialities)));
		yAxis.setLabel("Number of individual statistics");

		// add starting data
		final XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
		series1.setName("Statistics1");

		final XYChart.Series<String,Number> series2 = new XYChart.Series<String,Number>();
		series2.setName("Statistics2");

		final XYChart.Series<String,Number> series3 = new XYChart.Series<String,Number>();
		series3.setName("Statistics3");

		// Initialisation and change of data at regular intervals of time - 5 seconds.
		Timeline Updater = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				series1.getData().clear();
				text.clear();

				//Creation and Dynamic change of data.
				int s1 = new Random().nextInt(8);
				int s2 = new Random().nextInt(8);
				int s3 = new Random().nextInt(8);

				//To ensure data is at least one. Optional.
				if(s1 == 0 ){
					s1 = 1;
				}
				if(s2 == 0 ){
					s2 = 1;
				}
				if(s3 == 0 ){
					s3 = 1;
				}

				text.appendText("Statistics1  : "+String.valueOf(s1));
				text.appendText("\nStatistics2  : "+String.valueOf(s2));
				text.appendText("\nStatistics3  : "+String.valueOf(s3));

				series1.getData().add(new XYChart.Data<String,Number>(specialities[0], s1));
				series1.getData().add(new XYChart.Data<String,Number>(specialities[1], s2));
				series1.getData().add(new XYChart.Data<String,Number>(specialities[2], s3));
			}
		}));
		Updater.setCycleCount(Timeline.INDEFINITE);
		Updater.play();

		bc.getData().add(series1);
		return bc;

	}


	//To start initialisation of Front End.
	@Override public void start(Stage primaryStage) throws Exception {

		init(primaryStage);
		primaryStage.show();

	}

	public static void main(String[] args) { 
		launch(args); 
	}

}