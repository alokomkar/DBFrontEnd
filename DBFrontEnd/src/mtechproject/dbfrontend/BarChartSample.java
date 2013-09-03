package mtechproject.dbfrontend;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChartSample extends Application {
	
	private static String url = "jdbc:mysql://localhost:3306/floggerdb";
	private static String user = "root";
	private static String pass = "phoenix6832";

	@Override public void start(Stage stage) {

		stage.setTitle("File Access Statistics");
		final CategoryAxis yAxis = new CategoryAxis();
		final NumberAxis xAxis = new NumberAxis();
		final BarChart<Number,String> bc = 
			new BarChart<Number,String>(xAxis,yAxis);
		bc.setTitle("Files Summary");
		xAxis.setLabel("Files");    
		xAxis.setTickLabelRotation(90);
		yAxis.setLabel("Number of Access times");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Filenames");       


		String[] filenames = null;
		int[] accesstimes = null;

		ArrayList<FileList> list = null;
		Connection c = null ;
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(url,user,pass);
			String SQL = "SELECT Filename, MAX(Total_Access_Times) from user_document_access_table group by Filename";
			rs = c.createStatement().executeQuery(SQL);
			
			while(rs.next()){

				series1.getData().add(new XYChart.Data( Integer.parseInt(rs.getString(2)),rs.getString(1)));

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene  = new Scene(bc,800,600);
		bc.getData().addAll(series1);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}