package mtechproject.samples;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import mtechproject.dbfrontend.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Service1 extends Application {

	final GetDailySalesService service = new GetDailySalesService();
	 
	public GetDailySalesService newclass(){
		return new GetDailySalesService();
	}

	private void init(Stage primaryStage) {

		Group root = new Group();
		primaryStage.setScene(new Scene(root));

		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(12));
		TableView tableView = new TableView();
		
		Button refreshbtn = new Button("Refresh");
		refreshbtn.setOnAction(new EventHandler<ActionEvent>() {
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
				service.restart();
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

				service.restart();
			}
		});



		vbox.getChildren().addAll(tableView, addFirstName,btnLock,btnUnLock, refreshbtn);

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

		service.start();

	}



	public class GetDailySalesService extends Service<ObservableList<DailySales>> {


		@Override

		protected Task createTask() {

			return new GetDailySalesTask();

		}

	}



	public class GetDailySalesTask extends Task<ObservableList<DailySales>> {       

		@Override protected ObservableList<DailySales> call() throws Exception {

			for (int i = 0; i < 500; i++) {

				updateProgress(i, 500);

				Thread.sleep(5);

			}

			ObservableList<DailySales> sales = FXCollections.observableArrayList();

			Connection c ;
			c = DBConnect.connect();
			//SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "SELECT * from user_accounts";
			//ResultSet
			ResultSet rs = c.createStatement().executeQuery(SQL);

			while(rs.next()){
				sales.add(new DailySales(rs.getString("Username"),rs.getString("Locked")));	
			}

			return sales;

		}

	}



	public class DailySales {

		String Username;
		String Locked;
		
		public DailySales() {

		}

		public DailySales(String Username, String Locked) {

			this.Username = Username;

			this.Locked = Locked;

		}



		public String getUsername() {

			return Username;

		}



		public void setUsername(String Username) {

			this.Username = Username;

		}



		public String getLocked() {

			return Locked;

		}



		public void setLocked(String Locked) {

			this.Locked = Locked;

		}



	}



	@Override public void start(Stage primaryStage) throws Exception {

		init(primaryStage);

		primaryStage.show();

	}

	public static void main(String[] args) { launch(args); }

}