package mtechproject.dbfrontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class dataInJTable extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//This Vector Of A String Vector will be used to hold data from 
	// database table to display in JTable.
	static Vector<Vector<String>> data=new Vector<Vector<String>>();
	static JTable table;
	static JButton Unlock_Account, Lock_Account;
	public dataInJTable()
	{
		super("User Account Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel topPanel=new JPanel();
		JLabel label1=new JLabel("Database : User Accounts Database ");
		label1.setPreferredSize(new Dimension(200,30));
		JLabel label2=new JLabel("Table :  User Accounts");
		label2.setPreferredSize(new Dimension(200,30));
		topPanel.add(label1);
		topPanel.add(label2);
		getContentPane().add(topPanel,BorderLayout.NORTH);
		Vector<String> headers=new Vector<String>();
		headers.add("Username");
		headers.add("Locked");
label:
		getData();
		//this is the model which contain actual body of JTable
		DefaultTableModel model = new DefaultTableModel(data, headers);
		table=new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		header_size();
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scroll,BorderLayout.SOUTH);
		pack();
		setResizable(true);
		setVisible(true);
		
		Unlock_Account = new JButton("Unlock Account");
		Unlock_Account.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String Username = JOptionPane.showInputDialog("Enter Account to be unlocked:");
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//fireTableChanged();
				//getData();
			}
			
		});
		//table.add(Unlock_Account,BorderLayout.PAGE_END);
		getContentPane().add(Unlock_Account,BorderLayout.PAGE_END);
		
		Lock_Account = new JButton("Lock Account");
		Lock_Account.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String Username = JOptionPane.showInputDialog("Enter Account to be locked:");
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		//table.add(Lock_Account,BorderLayout.PAGE_START);
		getContentPane().add(Lock_Account,BorderLayout.PAGE_START);
	}
	/**
	 * Setting the particular Column Size in JTable
	 */
	public static void header_size() {
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(100);

		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(100);

	}
	/**
	 * Fetching Data From MySql Database 
	 * and storing in a Vector of a Vector
	 * to Display in JTable
	 */
	private static void getData()
	{
		// Enter Your MySQL Database Table name in below Select Query.
		String str="select * from user_accounts";
		Connection cn;
		ResultSet rs;
		Statement st;
		try {
			// Change the database name, hosty name, 
			// port and password as per MySQL installed in your PC.
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccountsdb","root","phoenix6832");
			st=cn.createStatement();
			rs=st.executeQuery(str);
			while(rs.next())
			{
				Vector <String> d=new Vector<String>();
				d.add(rs.getString("Username"));
				d.add(rs.getString("Locked"));
				d.add("\n\n\n\n\n\n\n");
				data.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new dataInJTable();
		//return true;
	}

}


