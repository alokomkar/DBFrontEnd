package mtechproject.dbfrontend;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MazedbGui extends JFrame{

	JButton getUserAccount;
	JList AccountInfo;
	private Connection connection;
	private Statement statement;
	private ResultSet rs;

	public MazedbGui(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
	}

	private void RetrieveInfo(){
		Vector v = new Vector();
		Vector v2 = new Vector();
		v.clear();
		v2.clear();
		try {
			rs = statement.executeQuery("SELECT * FROM user_accounts");

			while (rs.next()) {
				v.addElement(rs.getString("Username"));
				v2.addElement(rs.getString("Locked"));
			}
		} catch (SQLException e) {

		}
		
		AccountInfo.setListData(v);
		AccountInfo.setListData(v2);
	}

	private void BuildGui(){

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		AccountInfo = new JList();
		AccountInfo.clearSelection();

		RetrieveInfo();
		
		AccountInfo.setVisibleRowCount(3);
		JScrollPane AccountInfoListScrollPane = new JScrollPane(AccountInfo);

		getUserAccount = new JButton("Get User Account");

		getUserAccount.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					rs.first();
					while (rs.next()) {
						if (rs.getString("Username").equals(
								AccountInfo.getSelectedValue()))
							break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		JPanel first = new JPanel(new GridLayout(2, 1));
		first.add(AccountInfoListScrollPane);
		first.add(getUserAccount);

		c.add(first);
		setSize(500, 500);
		show();
	}
	
	public void connectToDB() {
	    try {
	      connection = DriverManager
	          .getConnection("jdbc:mysql://localhost/useraccountsdb?user=root&password=phoenix6832");
	      statement = connection.createStatement();

	    } catch (SQLException connectException) {
	      System.out.println(connectException.getMessage());
	      System.out.println(connectException.getSQLState());
	      System.out.println(connectException.getErrorCode());
	      System.exit(1);
	    }
	  }
	 private void init() {
		    connectToDB();
		  }


	public static void main(String args[]){

		MazedbGui accounts = new MazedbGui();

	    accounts.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });

	    accounts.init();
	    accounts.BuildGui();

	}

}
