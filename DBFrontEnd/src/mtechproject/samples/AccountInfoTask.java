package mtechproject.samples;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import mtechproject.dbfrontend.DBConnect;

public class AccountInfoTask extends Task<ObservableList<AccountInfo>> {       

	@Override protected ObservableList<AccountInfo> call() throws Exception {

		for (int i = 0; i < 500; i++) {
			updateProgress(i, 500);
			Thread.sleep(5);
		}

		ObservableList<AccountInfo> acccounts = FXCollections.observableArrayList();

		Connection c ;
		c = DBConnect.connect();
		//SQL FOR SELECTING ALL OF USER ACCOUNTS
		String SQL = "SELECT * from user_accounts";
		//ResultSet
		ResultSet rs = c.createStatement().executeQuery(SQL);

		while(rs.next()){
			acccounts.add(new AccountInfo(rs.getString("Username"),rs.getString("Locked")));	
		}

		return acccounts;

	}

}
