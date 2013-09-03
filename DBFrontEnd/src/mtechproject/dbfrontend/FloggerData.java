package mtechproject.dbfrontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FloggerData {

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	final static String USER = "root";
	final static String PASS = "phoenix6832";

	public static void main(String args[]){
		int i = FloggerSuspicious("Niranjan C T");
		System.out.println(i);
	}

	public static int FloggerCCopy(){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count(distinct(Activity)) from flogger where Activity like '%Content Copy%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}

	public static int FloggerCCopy(String user){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count(distinct(Activity)) from flogger where Username = '"+user+"' and Activity like '%Content Copy%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}


	public static int FloggerSuspicious(){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		//  Database credentials
		//final String USER = "root";
		//final String PASS = "phoenix6832";

		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		//System.out.println("Connecting to a selected database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count((Activity)) from flogger where Activity like '%Suspicious%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}

	public static int FloggerSuspicious(String user){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		//  Database credentials
		//final String USER = "root";
		//final String PASS = "phoenix6832";

		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		//System.out.println("Connecting to a selected database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count((Activity)) from flogger where Username = '"+user+"' and Activity like '%Suspicious%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}


	public static int FloggerSessionCount(){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		//  Database credentials
		//final String USER = "root";
		//final String PASS = "phoenix6832";

		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		//System.out.println("Connecting to a selected database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count((Activity)) from flogger where Activity like '%Session Login%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}

	public static int FloggerSessionCount(String user){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		//  Database credentials
		//final String USER = "root";
		//final String PASS = "phoenix6832";

		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		//System.out.println("Connecting to a selected database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count((Activity)) from flogger where Username = '"+user+"' and Activity like '%Session Login%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}


	public static int FloggerFileCreate(){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		//  Database credentials
		//final String USER = "root";
		//final String PASS = "phoenix6832";

		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		//System.out.println("Connecting to a selected database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count(distinct(Activity)) from flogger where Activity like '%ENTRY_CREATE%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}

	public static int FloggerFileCreate(String user){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		//  Database credentials
		//final String USER = "root";
		//final String PASS = "phoenix6832";

		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		//System.out.println("Connecting to a selected database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count(distinct(Activity)) from flogger where Username = '"+user+"' and Activity like '%ENTRY_CREATE%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}

	public static int FloggerROSession(){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		//  Database credentials
		//final String USER = "root";
		//final String PASS = "phoenix6832";

		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		//System.out.println("Connecting to a selected database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count((Activity)) from flogger where Activity like '%READ ONLY LOGIN%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}

	public static int FloggerROSession(String user){
		final String DB_URL = "jdbc:mysql://localhost:3306/FloggerDB";
		int count = 0;
		//  Database credentials
		//final String USER = "root";
		//final String PASS = "phoenix6832";

		Connection conn = null;


		String sql = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//STEP 3: Open a connection
		//System.out.println("Connecting to a selected database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt1=conn.createStatement();

			sql = "Select count((Activity)) from flogger where Username = '"+user+"' and Activity like '%READ ONLY LOGIN%'";


			ResultSet rs = stmt1.executeQuery(sql);

			if(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				//System.out.println(count);
			}


			stmt1.close();
			conn.close();
			//return count;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;



	}

}
