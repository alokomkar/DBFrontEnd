package mtechproject.dbfrontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class DispChangelog extends Application{

    //TABLE VIEW AND DATA
    private static ObservableList<ObservableList> data;
    private TableView tableview;
    private static String url = "jdbc:mysql://localhost:3306/datarecovery";
    private static String user = "root";
    private static String pass = "phoenix6832";

    //MAIN EXECUTOR
    public static void main(String[] args) {
        launch(args);
    }

    //CONNECTION DATABASE
    public static void buildData(TableView tableview){
          Connection c ;
          data = FXCollections.observableArrayList();
          try{
            c = DriverManager.getConnection(url,user,pass);
            //SQL FOR SELECTING ALL OF CUSTOMER
           // String On_date = "2013/03/21";
            String SQL = "SELECT * from changelog";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                   }                    
                });
               
               
                tableview.getColumns().addAll(col);
                //  System.out.println("Column ["+1+"] ");
            }

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
                //System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }

    
      @Override
      public void start(Stage stage) throws Exception {
        //TableView
        tableview = new TableView();
       // buildData();

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
}