package mtechproject.samples;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class AccountFXFrontEnd extends Application {

	final AccountInfoService service = new AccountInfoService();
	 
	public AccountInfoService newclass(){
		return new AccountInfoService();
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



		
	@Override public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args) { launch(args); }

}







