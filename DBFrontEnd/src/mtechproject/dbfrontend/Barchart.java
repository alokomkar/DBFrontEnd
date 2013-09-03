package mtechproject.dbfrontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class Barchart extends Application {
    final static String AlokOmkar = "Alok Omkar";
    final static String Apoorva = "Apoorva";
    final static String Adithya = "Adithya";
    final static String Niranjan = "Niranjan C T";
    final static String Soumya = "Soumya";
 
    @Override public void start(Stage stage) {
        stage.setTitle("File Access Statistics");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("User Activity Summary");
        xAxis.setLabel("Activities");       
        yAxis.setLabel("Number of Actions in Sessions");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Normal Sessions");       
        series1.getData().add(new XYChart.Data("Alok Omkar", FloggerData.FloggerSessionCount(AlokOmkar)));
        series1.getData().add(new XYChart.Data("Adithya", FloggerData.FloggerSessionCount(Adithya)));
        series1.getData().add(new XYChart.Data("Apoorva", FloggerData.FloggerSessionCount(Apoorva)));
        series1.getData().add(new XYChart.Data("Niranjan", FloggerData.FloggerSessionCount(Niranjan)));
        series1.getData().add(new XYChart.Data("Soumya", FloggerData.FloggerSessionCount(Soumya)));
        
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Read Only Sessions");
        series2.getData().add(new XYChart.Data("Alok Omkar", FloggerData.FloggerROSession(AlokOmkar)));
        series2.getData().add(new XYChart.Data("Adithya", FloggerData.FloggerROSession(Adithya)));
        series2.getData().add(new XYChart.Data("Apoorva", FloggerData.FloggerROSession(Apoorva)));
        series2.getData().add(new XYChart.Data("Niranjan", FloggerData.FloggerROSession(Niranjan)));
        series2.getData().add(new XYChart.Data("Soumya", FloggerData.FloggerROSession(Soumya)));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("File Operations");
        series3.getData().add(new XYChart.Data("Alok Omkar", FloggerData.FloggerFileCreate(AlokOmkar)));
        series3.getData().add(new XYChart.Data("Adithya", FloggerData.FloggerFileCreate(Adithya)));
        series3.getData().add(new XYChart.Data("Apoorva", FloggerData.FloggerFileCreate(Apoorva)));
        series3.getData().add(new XYChart.Data("Niranjan", FloggerData.FloggerFileCreate(Niranjan)));
        series3.getData().add(new XYChart.Data("Soumya", FloggerData.FloggerFileCreate(Soumya)));
        
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Content Copy");
        series4.getData().add(new XYChart.Data("Alok Omkar", FloggerData.FloggerCCopy(AlokOmkar)));
        series4.getData().add(new XYChart.Data("Adithya", FloggerData.FloggerCCopy(Adithya)));
        series4.getData().add(new XYChart.Data("Apoorva", FloggerData.FloggerCCopy(Apoorva)));
        series4.getData().add(new XYChart.Data("Niranjan", FloggerData.FloggerCCopy(Niranjan)));
        series4.getData().add(new XYChart.Data("Soumya", FloggerData.FloggerCCopy(Soumya)));
        
        XYChart.Series series5 = new XYChart.Series();
        series5.setName("Suspicious Activity");
        series5.getData().add(new XYChart.Data("Alok Omkar", FloggerData.FloggerSuspicious(AlokOmkar)));
        series5.getData().add(new XYChart.Data("Adithya", FloggerData.FloggerSuspicious(Adithya)));
        series5.getData().add(new XYChart.Data("Apoorva", FloggerData.FloggerSuspicious(Apoorva)));
        series5.getData().add(new XYChart.Data("Niranjan", FloggerData.FloggerSuspicious(Niranjan)));
        series5.getData().add(new XYChart.Data("Soumya", FloggerData.FloggerSuspicious(Soumya)));
        
        
        Scene scene  = new Scene(bc,1366,700);
    	
      //  bc.getData().addAll(series1,series2,series3,series4,series5);
        bc.getData().add(series1);
		bc.getData().add(series2);
		bc.getData().add(series3);
		bc.getData().add(series4);
		bc.getData().add(series5);
	    stage.setScene(scene);
       stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}