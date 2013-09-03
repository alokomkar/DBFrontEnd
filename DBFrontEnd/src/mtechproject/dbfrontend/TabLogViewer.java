package mtechproject.dbfrontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TabLogViewer extends Application{
	int run = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();

	}

	private void init(Stage primaryStage) {
		/* To Set the title of the stage */
		primaryStage.setTitle("MAZE Log Viewer");
		
		/*
		 * Definition of new Group root to be set and displayed as scene*/
		Group root = new Group();
		primaryStage.setScene(new Scene(root));

		/*
		 * Definition of BorderPane and TabPane*/
		BorderPane borderPane = new BorderPane();
		final TabPane tabPane = new TabPane();

		/*
		 * Definition of New Tab : PieTab*/
		final Tab BarTab = new Tab();
		setBarTab(BarTab);

		/*
		 * Definition of New Tab : BarTab*/
		final Tab PieTab = new Tab();
		setLogTab(PieTab);

		/*
		 * Definition of New Tab : FAITab*/
		final Tab FAITab = new Tab();
		setFAITab(FAITab);
		
		/*
		 * Definition of New Tab : FAUTab*/
		final Tab FAUTab = new Tab();
		setFAUTab(FAUTab);
		
		/*
		 * Definition of New Tab : FAUTab*/
		final Tab AUSTab = new Tab();
		setAUSTab(AUSTab);
		
		
		/*To Set Preferred Dimensions*/
		tabPane.setPrefSize(1366, 700);
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		/*Addition of tabs to TabPane*/
		tabPane.getTabs().addAll(PieTab,BarTab,FAITab,FAUTab,AUSTab);
		borderPane.setCenter(tabPane);
		root.getChildren().add(borderPane);



	}

	private void setAUSTab(Tab aUSTab) {
		aUSTab.setText("All Users Statistics");
		
		String AlokOmkar = "Alok Omkar";
		String Apoorva = "Apoorva";
		String Adithya = "Adithya";
		String Niranjan = "Niranjan C T";
		String Soumya = "Soumya";

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


		//Scene scene  = new Scene(bc,1366,700);

		bc.getData().addAll(series1,series2,series3,series4,series5);
		
		aUSTab.setContent(bc);
		
	}

	private void setFAUTab(Tab fAUTab) {
		fAUTab.setText("File Access Users");
		String Information = "\nMAZE Log Viewer: File Access Intruder information";
		Label InfoLabel = new Label(Information);
		InfoLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
		
		String url = "jdbc:mysql://localhost:3306/floggerdb";
		String user = "root";
		String pass = "phoenix6832";
		
		final CategoryAxis yAxis = new CategoryAxis();
		final NumberAxis xAxis = new NumberAxis();
		final BarChart<Number,String> bc = 
			new BarChart<Number,String>(xAxis,yAxis);
		bc.setTitle("Files Access Summary");
		xAxis.setLabel("Number of Access Times");    
		xAxis.setTickLabelRotation(90);
		yAxis.setLabel("Files");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Number of Access times");       


		String[] filenames = null;
		int[] accesstimes = null;

		ArrayList<FileList> list = null;
		Connection c = null ;
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(url,user,pass);
			String SQL = "SELECT Filename, MAX(Total_Access_Times) from user_document_access_table where Privileged_Access = 'YES' group by Filename";
			rs = c.createStatement().executeQuery(SQL);

			while(rs.next()){

				series1.getData().add(new XYChart.Data( Integer.parseInt(rs.getString(2)),rs.getString(1)));

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene1  = new Scene(bc,800,600);
		bc.getData().addAll(series1);
		

		fAUTab.setContent(bc);




		
	}

	private void setFAITab(Tab fATab) {
		
		fATab.setText("File Access Intruders");
		String Information = "\nMAZE Log Viewer: File Access Intruder information";
		Label InfoLabel = new Label(Information);
		InfoLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
		
		String url = "jdbc:mysql://localhost:3306/floggerdb";
		String user = "root";
		String pass = "phoenix6832";
		
		final CategoryAxis yAxis = new CategoryAxis();
		final NumberAxis xAxis = new NumberAxis();
		final BarChart<Number,String> barchart = 
			new BarChart<Number,String>(xAxis,yAxis);
		
		barchart.setTitle("Files Access Summary");
		xAxis.setLabel("Number of Access Times");    
		xAxis.setTickLabelRotation(90);
		yAxis.setLabel("Files");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Number of Access Times");       


		String[] filenames = null;
		int[] accesstimes = null;

		ArrayList<FileList> list = null;
		Connection c = null ;
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(url,user,pass);
			String SQL = "SELECT Filename, MAX(Total_Access_Times) from user_document_access_table where Privileged_Access = 'NO' group by Filename";
			rs = c.createStatement().executeQuery(SQL);

			while(rs.next()){

				series1.getData().add(new XYChart.Data( Integer.parseInt(rs.getString(2)),rs.getString(1)));

			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		VBox vb = new VBox();
		vb.setPadding(new Insets(10, 50, 50, 50));
		vb.setSpacing(10);
		barchart.getData().addAll(series1);
		
		vb.getChildren().addAll(barchart);
		

		fATab.setContent(barchart);



		
	}

	private void setBarTab(Tab barTab) {
		
		barTab.setText("Pie Chart: Sessions");
		String Information = "\nMAZE Log Viewer: Pie Chart information";
		Label InfoLabel = new Label(Information);
		InfoLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));

		ObservableList<PieChart.Data> pieChartData1 =
				FXCollections.observableArrayList(
						//new PieChart.Data("Content Copy", FloggerData.FloggerCCopy()),
						new PieChart.Data("Normal Sessions", FloggerData.FloggerSessionCount()),
						//new PieChart.Data("New File Operations", FloggerData.FloggerFileCreate()),
						new PieChart.Data("Read Only Login", FloggerData.FloggerROSession())
				/*new PieChart.Data("Suspicious Activity", FloggerData.FloggerSuspicious())*/);
			final PieChart chart1 = new PieChart(pieChartData1);
			chart1.setTitle("Flogger Statistics");
			
			VBox vb = new VBox();
			vb.setPadding(new Insets(10, 50, 50, 50));
			vb.setSpacing(10);

			vb.getChildren().addAll(InfoLabel,chart1);
			barTab.setContent(vb);


		
	}

	private void setLogTab(Tab pieTab) {

		pieTab.setText("Pie Chart: All Operations");
		String Information = "\nMAZE Log Viewer: Pie Chart information";
		Label InfoLabel = new Label(Information);
		InfoLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));

		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
						new PieChart.Data("Content Copy", FloggerData.FloggerCCopy()),
						new PieChart.Data("Normal Sessions", FloggerData.FloggerSessionCount() - FloggerData.FloggerCCopy() - FloggerData.FloggerFileCreate() - FloggerData.FloggerSuspicious()),
						new PieChart.Data("New File Operations", FloggerData.FloggerFileCreate()),
						//new PieChart.Data("Read Only Login", FloggerData.FloggerROSession()),
						new PieChart.Data("Suspicious Activity", FloggerData.FloggerSuspicious()));
			final PieChart chart = new PieChart(pieChartData);
			chart.setTitle("Flogger Statistics");

			
		VBox vb = new VBox();
		vb.setPadding(new Insets(10, 50, 50, 50));
		vb.setSpacing(10);

		vb.getChildren().addAll(InfoLabel,chart);
		pieTab.setContent(vb);




	}

	public static void main(String args[]){
		launch(args);
	}

}
