package mtechproject.dbfrontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import javax.swing.JOptionPane;

import mtechproject.dbfrontend.PuzzlePieces.Desk;
import mtechproject.dbfrontend.PuzzlePieces.Piece;

public class VBoxMain extends Application {

	private Timeline timeline;
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("MAZE Server Log Viewer");
		//primaryStage.setResizable(false);
		primaryStage.setFullScreen(true);
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setMinHeight(750);
		primaryStage.setMinWidth(1350);
		//primaryStage.setResizable(false);
		//primaryStage.setMinHeight(768);
		//primaryStage.setMinWidth(1366);


		//VBox
		VBox vb = new VBox();
		vb.setPadding(new Insets(10, 50, 50, 50));
		vb.setSpacing(10);

		//Adding label to VBox and setting font bold Amble CN with size 24
		Label lbl = new Label("Welcome to MAZE Server Log Viewer");
		//lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
		lbl.setFont(Font.font("Calibri", FontWeight.BOLD, 26));
		lbl.setAlignment(Pos.BOTTOM_CENTER);
		vb.getChildren().add(lbl);

		//Buttons
		Button btnUsermachines = new Button();
		btnUsermachines.setText("User Machines           ");
		//vb.getChildren().add(btnUsermachines);

		Button btnIntruderMachines = new Button();
		btnIntruderMachines.setText("Intruder Machines     ");
		//vb.getChildren().add(btnIntruderMachines);

		HBox hbbtn1 = new HBox();
		hbbtn1.setSpacing(20);
		hbbtn1.getChildren().addAll(btnUsermachines,btnIntruderMachines);
		hbbtn1.setAlignment(Pos.CENTER);
		hbbtn1.setScaleX(1.4);
		hbbtn1.setScaleY(1.4);
		vb.getChildren().add(hbbtn1);

		Button btnIntruderIP = new Button();
		btnIntruderIP.setText("Intrusion IP Address  ");
		//vb.getChildren().add(btnIntruderIP);

		Button btnUserInfo = new Button();
		btnUserInfo.setText("User Acc. Information");
		//vb.getChildren().add(btnUserInfo);

		HBox hbbtn2 = new HBox();
		hbbtn2.setSpacing(20);
		hbbtn2.getChildren().addAll(btnIntruderIP,btnUserInfo);
		hbbtn2.setAlignment(Pos.CENTER);
		hbbtn2.setScaleX(1.4);
		hbbtn2.setScaleY(1.4);
		vb.getChildren().add(hbbtn2);

		Button btnIntruderForensics = new Button();
		btnIntruderForensics.setText("Intruder Forensics      ");
		//vb.getChildren().add(btnIntruderForensics);

		Button btnUserForensics = new Button();
		btnUserForensics.setText("User Forensics            ");
		//vb.getChildren().add(btnUserForensics);

		HBox hbbtn3 = new HBox();
		hbbtn3.setSpacing(20);
		hbbtn3.getChildren().addAll(btnIntruderForensics,btnUserForensics);
		hbbtn3.setAlignment(Pos.CENTER);
		hbbtn3.setScaleX(1.4);
		hbbtn3.setScaleY(1.4);
		vb.getChildren().add(hbbtn3);

		Button btnFloggerByDate = new Button();
		btnFloggerByDate.setText("File-Log by Date         ");
		//vb.getChildren().add(btnFloggerByDate);

		Button btnChangeLog = new Button();
		btnChangeLog.setText("File Change Log          ");
		//vb.getChildren().add(btnChangeLog);

		HBox hbbtn4 = new HBox();
		hbbtn4.setSpacing(20);
		hbbtn4.getChildren().addAll(btnFloggerByDate,btnChangeLog);
		hbbtn4.setAlignment(Pos.CENTER);
		hbbtn4.setScaleX(1.4);
		hbbtn4.setScaleY(1.4);
		vb.getChildren().add(hbbtn4);


		Button btnPieChartFlogger = new Button();
		btnPieChartFlogger.setText("MAZE Statistics           ");
		//vb.getChildren().add(btnPieChartFlogger);

		Button btnEncAccLog = new Button();
		btnEncAccLog.setText("Encryption v/s Access ");
		//vb.getChildren().add(btnEncAccLog);

		HBox hbbtn5 = new HBox();
		hbbtn5.setSpacing(20);
		hbbtn5.getChildren().addAll(btnPieChartFlogger,btnEncAccLog);
		hbbtn5.setAlignment(Pos.CENTER);
		hbbtn5.setScaleX(1.4);
		hbbtn5.setScaleY(1.4);
		vb.getChildren().add(hbbtn5);


		btnEncAccLog.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = new Stage();
				NumberAxis xAxis = new NumberAxis();
				NumberAxis yAxis = new NumberAxis();
				xAxis.setLabel("File Number");
				yAxis.setLabel("Time in milliseconds");
				LineChart lineChart = new LineChart(xAxis, yAxis);
				lineChart.setData(getChartData());
				lineChart.setTitle("Encryption and Access Overhead Compared For 77 User Files");
				stage.setTitle("LineChart example");
				StackPane root = new StackPane();
				root.getChildren().add(lineChart);
				stage.setScene(new Scene(root, 1366, 760));
				stage.show();

			}

			private ObservableList<Series<Double, Double>> getChartData() {
				//double aValue = 1.56;
				//double cValue = 1.06;
				ObservableList<XYChart.Series<Double, Double>> answer = FXCollections.observableArrayList();
				Series<Double, Double> aSeries = new Series<Double, Double>();
				Series<Double, Double> cSeries = new Series<Double, Double>();
				Series<Double, Double> dSeries = new Series<Double, Double>();
				aSeries.setName("DES Encryption");
				cSeries.setName("Access");
				dSeries.setName("AES Encryption");
				Connection con = null;
				try {
					con = DBConnectFlogger.connect();
					String st = "Select * from performance_metrics";
					ResultSet rs = con.createStatement().executeQuery(st);
					Double i = 1.0;
					while(rs.next()){
						aSeries.getData().add(new XYChart.Data(i, Double.parseDouble(rs.getString(1))));
						cSeries.getData().add(new XYChart.Data(i, Double.parseDouble(rs.getString(2))));
						dSeries.getData().add(new XYChart.Data(i, Double.parseDouble(rs.getString(3))));
						i = i + 1.0;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				answer.addAll(aSeries, cSeries, dSeries );
				return answer;
			}
		});

		btnPieChartFlogger.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = new Stage();
				//stage.setFullScreen(true);

				//Scene scene = new Scene(new Group());
				stage.setTitle("MAZE Statistics");
				VBox vb = new VBox();
				vb.setPadding(new Insets(10, 50, 50, 50));
				vb.setSpacing(10);

				//StackPane sp = new StackPane();
				Button btnOpen = new Button("Flogger - All Operations");
				vb.getChildren().add(btnOpen);


				btnOpen.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						// Creating a new Stage and showing it
						Stage stage = new Stage();
						Scene scene = new Scene(new Group());
						stage.setTitle("Flogger Statistics - All Operations");
						ObservableList<PieChart.Data> pieChartData =
							FXCollections.observableArrayList(
									new PieChart.Data("Content Copy", FloggerData.FloggerCCopy()),
									new PieChart.Data("Normal Sessions", FloggerData.FloggerSessionCount() - FloggerData.FloggerCCopy() - FloggerData.FloggerFileCreate() - FloggerData.FloggerSuspicious()),
									new PieChart.Data("New File Operations", FloggerData.FloggerFileCreate()),
									//new PieChart.Data("Read Only Login", FloggerData.FloggerROSession()),
									new PieChart.Data("Suspicious Activity", FloggerData.FloggerSuspicious()));
						final PieChart chart = new PieChart(pieChartData);
						chart.setTitle("Flogger Statistics");

						((Group) scene.getRoot()).getChildren().add(chart);
						stage.setScene(scene);
						stage.show();
					}
				});

				Button btnOpen1 = new Button("Flogger - Sessions Only ");
				vb.getChildren().add(btnOpen1);

				btnOpen1.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						// Creating a new Stage and showing it
						Stage stage = new Stage();
						Scene scene = new Scene(new Group());
						stage.setTitle("Flogger Statistics - Sessions Only");
						ObservableList<PieChart.Data> pieChartData1 =
							FXCollections.observableArrayList(
									//new PieChart.Data("Content Copy", FloggerData.FloggerCCopy()),
									new PieChart.Data("Normal Sessions", FloggerData.FloggerSessionCount()),
									//new PieChart.Data("New File Operations", FloggerData.FloggerFileCreate()),
									new PieChart.Data("Read Only Login", FloggerData.FloggerROSession())
							/*new PieChart.Data("Suspicious Activity", FloggerData.FloggerSuspicious())*/);
						final PieChart chart1 = new PieChart(pieChartData1);
						chart1.setTitle("Flogger Statistics");

						((Group) scene.getRoot()).getChildren().add(chart1);
						stage.setScene(scene);
						stage.show();
					}
				});

				Button btnFAS = new Button("File Access - Intruder     ");
				vb.getChildren().add(btnFAS);

				btnFAS.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {

						String url = "jdbc:mysql://localhost:3306/floggerdb";
						String user = "root";
						String pass = "phoenix6832";

						// Creating a new Stage and showing it
						Stage stage = new Stage();
						Scene scene = new Scene(new Group());
						stage.setTitle("File Access Statistics");

						final CategoryAxis yAxis = new CategoryAxis();
						final NumberAxis xAxis = new NumberAxis();
						final BarChart<Number,String> bc = 
							new BarChart<Number,String>(xAxis,yAxis);
						bc.setTitle("Files Access Summary");
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Scene scene1  = new Scene(bc,800,600);
						bc.getData().addAll(series1);


						stage.setScene(scene1);
						stage.setFullScreen(true);
						stage.show();
					}
				});

				Button btnFAU = new Button("File Access - USER         ");
				vb.getChildren().add(btnFAU);

				btnFAU.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {

						String url = "jdbc:mysql://localhost:3306/floggerdb";
						String user = "root";
						String pass = "phoenix6832";

						// Creating a new Stage and showing it
						Stage stage = new Stage();
						Scene scene = new Scene(new Group());
						stage.setTitle("File Access Statistics");

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


						stage.setScene(scene1);
						stage.setFullScreen(true);
						stage.show();
					}
				});

				Button btnIUS = new Button("Individual User Statistic ");
				vb.getChildren().add(btnIUS);

				btnIUS.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {

						String AlokOmkar = "Alok Omkar";
						String Apoorva = "Apoorva";
						String Adithya = "Adithya";
						String Niranjan = "Niranjan C T";
						String Soumya = "Soumya";

						Stage stage = new Stage();
						stage.setTitle("File Access Statistic");
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

						bc.getData().addAll(series1,series2,series3,series4,series5);
						stage.setScene(scene);
						stage.setFullScreen(true);
						stage.show();

					}
				});





				stage.setWidth(600);
				stage.setHeight(300);

				Scene scene = new Scene(vb);
				scene.getStylesheets().add(getClass().getClassLoader().getResource("flogger.css").toExternalForm());

				stage.setScene(scene);
				final Label caption = new Label("");
				caption.setTextFill(Color.DARKORANGE);
				caption.setStyle("-fx-font: 24 arial;");


				stage.show();



			}

		});


		btnChangeLog.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = new Stage();
				stage.setFullScreen(true);
				TableView tableview;

				//TableView
				tableview = new TableView();
				DispChangelog.buildData(tableview);	

				//Adding GridPane
				GridPane gridPane = new GridPane();
				gridPane.setPadding(new Insets(20,20,20,20));
				gridPane.setHgap(5);
				gridPane.setVgap(5);

				//Main Scene
				Scene scene = new Scene(tableview);        

				stage.setScene(scene);
				stage.show();


			}

		});



		btnFloggerByDate.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = new Stage();
				stage.setFullScreen(true);
				TableView tableview;

				//TableView
				tableview = new TableView();
				String On_date = JOptionPane.showInputDialog("Enter Date [YYYY/MM/DD]:");

				if(On_date != null){
					DispFlogger.buildData(tableview, On_date);	
				}else{
					DispFlogger.buildData(tableview, null);
				}


				//Adding GridPane
				GridPane gridPane = new GridPane();
				gridPane.setPadding(new Insets(20,20,20,20));
				gridPane.setHgap(5);
				gridPane.setVgap(5);

				//Main Scene
				Scene scene = new Scene(tableview);        

				stage.setScene(scene);
				stage.show();


			}

		});



		btnUserForensics.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = new Stage();
				stage.setFullScreen(true);
				TableView tableview;

				//TableView
				tableview = new TableView();
				DispForensics.buildData(tableview);

				//Adding GridPane
				GridPane gridPane = new GridPane();
				gridPane.setPadding(new Insets(20,20,20,20));
				gridPane.setHgap(5);
				gridPane.setVgap(5);

				//Main Scene
				Scene scene = new Scene(tableview);        

				stage.setScene(scene);
				stage.show();


			}

		});



		btnIntruderForensics.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = new Stage();
				stage.setFullScreen(true);
				TableView tableview;

				//TableView
				tableview = new TableView();
				DispIntruderForensics.buildData(tableview);

				Button btnUpdate = new Button("Unlock");
				final Label lblMessage = new Label();

				//Adding GridPane
				GridPane gridPane = new GridPane();
				gridPane.setPadding(new Insets(20,20,20,20));
				gridPane.setHgap(5);
				gridPane.setVgap(5);

				gridPane.add(btnUpdate, 92, 50);
				gridPane.add(lblMessage, 94, 52);

				//Main Scene
				Scene scene = new Scene(tableview);        

				stage.setScene(scene);
				stage.show();


			}

		});

		/*ServiceModified s1 = new ServiceModified();
		final AccountInfoService service = new AccountInfoService();

		btnUserInfo.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage primaryStage = new Stage();
				Group root = new Group();
				primaryStage.setScene(new Scene(root));

				VBox vbox = new VBox(5);
				vbox.setPadding(new Insets(12));
				TableView tableView = new TableView();
				Button button = new Button("Refresh");
				button.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {

						service.restart();

					}

				});

				final TextField addFirstName = new TextField();
				addFirstName.setPromptText("Username");
				addFirstName.setMinWidth(25);


				Button btnLock = new Button("Lock Account  ");
				final Label lblMessage = new Label();

				btnLock.setOnAction(new EventHandler<ActionEvent>() {
					@Override

					public void handle(ActionEvent e) {

						String Username = addFirstName.getText();
						final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
						final String USER = "root";
						final String PASS = "phoenix6832";
						final String DB_URL = "jdbc:mysql://localhost:3306/USERACCOUNTSDB";

						Connection conn = null;

						String sql = null;
						//STEP 2: Register JDBC driver
						try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt1=conn.createStatement();

							sql = "Update User_accounts set Locked = 'Y' where Username = '"+Username+"'";
							stmt1.executeUpdate(sql);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						//(Service)DispUserInfo.restart();
					}
				});

				Button btnUnLock = new Button("Unlock Account");
				btnUnLock.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {

						String Username = addFirstName.getText();
						final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
						final String USER = "root";
						final String PASS = "phoenix6832";
						final String DB_URL = "jdbc:mysql://localhost:3306/USERACCOUNTSDB";

						Connection conn = null;

						String sql = null;
						//STEP 2: Register JDBC driver
						try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt1=conn.createStatement();

							sql = "Update User_accounts set Locked = 'N' where Username = '"+Username+"'";
							stmt1.executeUpdate(sql);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}


					}
				});



				vbox.getChildren().addAll(tableView, addFirstName,btnLock,btnUnLock, button);

				Region veil = new Region();
				veil.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");

				ProgressIndicator p = new ProgressIndicator();
				p.setMaxSize(150, 150);

				//Define table columns
				TableColumn idCol = new TableColumn();
				idCol.setText("Username");
				idCol.setCellValueFactory(new PropertyValueFactory("Username"));
				tableView.getColumns().add(idCol);
				TableColumn qtyCol = new TableColumn();
				qtyCol.setText("Locked");
				qtyCol.setCellValueFactory(new PropertyValueFactory("Locked"));
				tableView.getColumns().add(qtyCol);

				p.progressProperty().bind(service.progressProperty());

				veil.visibleProperty().bind(service.runningProperty());

				p.visibleProperty().bind(service.runningProperty());

				tableView.itemsProperty().bind(service.valueProperty());

				StackPane stack = new StackPane();

				stack.getChildren().addAll(vbox, veil, p);

				root.getChildren().add(stack);

				new Thread(new AccountInfoService()).start();
				//service.start();
				//service.cancel();



			}

		});*/

		btnUserInfo.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

				//final boolean buttonpress = false;
				Stage stage = new Stage();
				stage.setFullScreen(true);
				Scene scene = new Scene(new Group());
				final TableView tableview;

				//TableView
				tableview = new TableView();
				//tableview.getColumns().get(0)).setVisible(false);
				//tableview.getColumns().get(0).setVisible(true);
				DispUserInfo.buildData(tableview);

				final HBox hb = new HBox();

				final TextField addFirstName = new TextField();
				addFirstName.setPromptText("Username");
				addFirstName.setMinWidth(25);

				Button btnLock = new Button("Lock Account  ");
				final Label lblMessage = new Label();

				btnLock.setOnAction(new EventHandler<ActionEvent>() {
					@Override

					public void handle(ActionEvent e) {

						String Username = addFirstName.getText();
						final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
						final String USER = "root";
						final String PASS = "phoenix6832";
						final String DB_URL = "jdbc:mysql://localhost:3306/USERACCOUNTSDB";

						Connection conn = null;

						String sql = null;
						//STEP 2: Register JDBC driver
						try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt1=conn.createStatement();

							sql = "Update User_accounts set Locked = 'Y' where Username = '"+Username+"'";
							stmt1.executeUpdate(sql);
							//updateTableColoumn(tableview.getColumns());

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						//(Service)DispUserInfo.restart();
					}
				});

				Button btnUnLock = new Button("Unlock Account");
				btnUnLock.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {

						String Username = addFirstName.getText();
						final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
						final String USER = "root";
						final String PASS = "phoenix6832";
						final String DB_URL = "jdbc:mysql://localhost:3306/USERACCOUNTSDB";

						Connection conn = null;

						String sql = null;
						//STEP 2: Register JDBC driver
						try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt1=conn.createStatement();

							sql = "Update User_accounts set Locked = 'N' where Username = '"+Username+"'";
							stmt1.executeUpdate(sql);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}


					}
				});

				//Adding GridPane
				//GridPane gridPane = new GridPane();
				//gridPane.setPadding(new Insets(20,20,20,20));
				//gridPane.setHgap(5);
				//gridPane.setVgap(5);

				//gridPane.add(btnLock, 92, 50);
				//gridPane.add(lblMessage, 94, 52);

				hb.getChildren().addAll(addFirstName,btnLock,btnUnLock);
				hb.setSpacing(3);

				final VBox vbox = new VBox();
				vbox.setSpacing(5);
				vbox.setPadding(new Insets(10, 0, 0, 10));
				vbox.getChildren().addAll(tableview, hb);

				((Group) scene.getRoot()).getChildren().addAll(vbox);

				//Main Scene
				//Scene scene = new Scene(tableview);        

				stage.setScene(scene);
				stage.show();


			}

		});



		btnIntruderIP.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = new Stage();
				stage.setFullScreen(true);
				TableView tableview;

				//TableView
				tableview = new TableView();
				DispIntruderIP.buildData(tableview);

				Button btnUpdate = new Button("Unlock");
				final Label lblMessage = new Label();

				//Adding GridPane
				GridPane gridPane = new GridPane();
				gridPane.setPadding(new Insets(20,20,20,20));
				gridPane.setHgap(5);
				gridPane.setVgap(5);

				gridPane.add(btnUpdate, 92, 50);
				gridPane.add(lblMessage, 94, 52);

				//Main Scene
				Scene scene = new Scene(tableview);        

				stage.setScene(scene);
				stage.show();


			}

		});


		btnIntruderMachines.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = new Stage();
				stage.setFullScreen(true);
				TableView tableview;

				//TableView
				tableview = new TableView();
				DispIntruderMachines.buildData(tableview);

				Button btnUpdate = new Button("Unlock");
				final Label lblMessage = new Label();

				//Adding GridPane
				GridPane gridPane = new GridPane();
				gridPane.setPadding(new Insets(20,20,20,20));
				gridPane.setHgap(5);
				gridPane.setVgap(5);

				gridPane.add(btnUpdate, 92, 50);
				gridPane.add(lblMessage, 94, 52);

				//Main Scene
				Scene scene = new Scene(tableview);        

				stage.setScene(scene);
				stage.show();


			}

		});

		btnUsermachines.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// Creating a new Stage and showing it
				Stage stage = new Stage();
				stage.setFullScreen(true);
				TableView tableview;

				//TableView
				tableview = new TableView();
				UsermachinesbuildData(tableview);

				Button btnUpdate = new Button("Unlock");
				final Label lblMessage = new Label();

				//Adding GridPane
				GridPane gridPane = new GridPane();
				gridPane.setPadding(new Insets(20,20,20,20));
				gridPane.setHgap(5);
				gridPane.setVgap(5);

				gridPane.add(btnUpdate, 92, 50);
				gridPane.add(lblMessage, 94, 52);

				//Main Scene
				Scene scene = new Scene(tableview);        

				stage.setScene(scene);
				stage.show();


			}

			private void UsermachinesbuildData(TableView tableview) {

				ObservableList<ObservableList> data;
				Connection c ;
				data = FXCollections.observableArrayList();
				try{
					c = DBConnect.connect();
					//SQL FOR SELECTING ALL OF CUSTOMER
					String SQL = "SELECT * from User_Machines";
					//ResultSet
					ResultSet rs = c.createStatement().executeQuery(SQL);

					/**********************************
					 * TABLE COLUMN ADDED DYNAMICALLY *
					 **********************************/
					// for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
					//We are using non property style for making dynamic table
					//   final int j = i;                
					TableColumn col = new TableColumn(rs.getMetaData().getColumnName(1));
					col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
							return new SimpleStringProperty(param.getValue().get(0).toString());                        
						}                    
					});

					TableColumn col1 = new TableColumn(rs.getMetaData().getColumnName(2));
					col1.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
							return new SimpleStringProperty(param.getValue().get(1).toString());                        
						}                    
					});

					TableColumn col2 = new TableColumn(rs.getMetaData().getColumnName(3));
					col2.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
							return new SimpleStringProperty(param.getValue().get(2).toString());                        
						}                    
					});

					tableview.getColumns().addAll(col);
					tableview.getColumns().addAll(col1);
					tableview.getColumns().addAll(col2);
					//  System.out.println("Column ["+1+"] ");
					// }

					/********************************
					 * Data added to ObservableList *
					 ********************************/
					while(rs.next()){
						//Iterate Row
						ObservableList<String> row = FXCollections.observableArrayList();
						for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
							//Iterate Column
							row.add(rs.getString(i));
						}
						// System.out.println("Row [1] added "+row );
						data.add(row);

					}

					//FINALLY ADDED TO TableView
					tableview.setItems(data);
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("Error on Building Data");             
				}


			}

		});

		// load puzzle image
		Image image = new Image(getClass().getResourceAsStream("Desktop3.jpg"));

		int numOfColumns = (int) (image.getWidth() / Piece.SIZE);
		int numOfRows = (int) (image.getHeight() / Piece.SIZE);

		// create desk
		final Desk desk = new Desk(numOfColumns, numOfRows);

		// create puzzle pieces
		final List<Piece> pieces  = new ArrayList<Piece>();

		for (int col = 0; col < numOfColumns; col++) {
			for (int row = 0; row < numOfRows; row++) {
				int x = col * Piece.SIZE;
				int y = row * Piece.SIZE;
				final Piece piece = new Piece(image, x, y, row>0, col>0,
						row<numOfRows -1, col < numOfColumns -1,
						desk.getWidth(), desk.getHeight());
				pieces.add(piece);
			}
		}

		desk.getChildren().addAll(pieces);

		// create button box
		Button shuffleButton = new Button("Shuffle");
		// shuffleButton.setStyle("-fx-font-size: 2em;");
		shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent actionEvent) {
				if (timeline != null) timeline.stop();
				timeline = new Timeline();
				for (final Piece piece : pieces) {
					piece.setActive();
					double shuffleX = Math.random() *
					(desk.getWidth() - Piece.SIZE + 48f ) -
					24f - piece.getCorrectX();
					double shuffleY = Math.random() *
					(desk.getHeight() - Piece.SIZE + 30f ) -
					15f - piece.getCorrectY();
					timeline.getKeyFrames().add(
							new KeyFrame(Duration.seconds(1),
									new KeyValue(piece.translateXProperty(), shuffleX),
									new KeyValue(piece.translateYProperty(), shuffleY)));
				}

				timeline.playFromStart();

			}

		});

		Button solveButton = new Button("Solve..");

		// solveButton.setStyle("-fx-font-size: 2em;");

		solveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override public void handle(ActionEvent actionEvent) {
				if (timeline != null) timeline.stop();
				timeline = new Timeline();
				for (final Piece piece : pieces) {
					piece.setInactive();
					timeline.getKeyFrames().add(
							new KeyFrame(Duration.seconds(1),
									new KeyValue(piece.translateXProperty(), 0),
									new KeyValue(piece.translateYProperty(), 0)));
				}
				timeline.playFromStart();
			}

		});

		HBox buttonBox = new HBox(20);

		buttonBox.getChildren().addAll(shuffleButton, solveButton);
		buttonBox.setAlignment(Pos.CENTER);
		// create vbox for desk and buttons

		// VBox vb1 = new VBox(10);

		vb.getChildren().addAll(desk,buttonBox);

		vb.setSpacing(35);
		vb.setAlignment(Pos.BASELINE_CENTER);

		//Adding VBox to the scene
		Scene scene = new Scene(vb);
		//Scene scene = new Scene(bp);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
		//primaryStage.setScene(scene);
		primaryStage.titleProperty().bind(
				scene.widthProperty().asString().
				concat(" : ").
				concat(scene.heightProperty().asString()));

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

