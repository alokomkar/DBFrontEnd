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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RSAccounts extends JFrame {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private JButton getAccountButton, insertAccountButton, deleteAccountButton,
      updateAccountButton, nextButton, previousButton, lastButton,
      firstButton, gotoButton, freeQueryButton;

  private JList accountNumberList;

  private JTextField accountIDText, usernameText, passwordText, tsText,
      activeTSText, gotoText, freeQueryText;

  private JTextArea errorText;

  private Connection connection;

  private Statement statement;

  private ResultSet rs;

  public RSAccounts() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception e) {
      System.err.println("Unable to find and load driver");
      System.exit(1);
    }
  }

  private void loadAccounts() {
    Vector v = new Vector();
    try {
      rs = statement.executeQuery("SELECT * FROM user_accounts");

      while (rs.next()) {
        v.addElement(rs.getString("Username"));
      }
    } catch (SQLException e) {
      displaySQLErrors(e);
    }
    accountNumberList.setListData(v);
  }

  private void buildGUI() {
    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    accountNumberList = new JList();
    loadAccounts();
    accountNumberList.setVisibleRowCount(2);
    JScrollPane accountNumberListScrollPane = new JScrollPane(
        accountNumberList);

    gotoText = new JTextField(3);
    freeQueryText = new JTextField(40);

    //Do Get Account Button
    getAccountButton = new JButton("Get Account");
    getAccountButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rs.first();
          while (rs.next()) {
            if (rs.getString("Username").equals(
                accountNumberList.getSelectedValue()))
              break;
          }
          if (!rs.isAfterLast()) {
            accountIDText.setText(rs.getString("Mobile_no"));
            usernameText.setText(rs.getString("Username"));
            passwordText.setText(rs.getString("Prim_Password"));
            tsText.setText(rs.getString("Locked"));
            activeTSText.setText(rs.getString("Sec_Password"));
          }
        } catch (SQLException selectException) {
          displaySQLErrors(selectException);
        }
      }
    });

    //Do Insert Account Button
    insertAccountButton = new JButton("Insert Account");
    insertAccountButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Statement statement = connection.createStatement();
          int i = statement
              .executeUpdate("INSERT INTO acc_acc VALUES("
                  + accountIDText.getText() + ", " + "'"
                  + usernameText.getText() + "', " + "'"
                  + passwordText.getText() + "', " + "0"
                  + ", " + "now())");
          errorText.append("Inserted " + i + " rows successfully");
          accountNumberList.removeAll();
          loadAccounts();
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do Delete Account Button
    deleteAccountButton = new JButton("Delete Account");
    deleteAccountButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Statement statement = connection.createStatement();
          int i = statement
              .executeUpdate("DELETE FROM acc_acc WHERE acc_id = "
                  + accountNumberList.getSelectedValue());
          errorText.append("Deleted " + i + " rows successfully");
          accountNumberList.removeAll();
          loadAccounts();
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do Update Account Button
    updateAccountButton = new JButton("Update Account");
    updateAccountButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Statement statement = connection.createStatement();
          int i = statement.executeUpdate("UPDATE acc_acc "
              + "SET username='" + usernameText.getText() + "', "
              + "password='" + passwordText.getText() + "', "
              + "act_ts = now() " + "WHERE acc_id = "
              + accountNumberList.getSelectedValue());
          errorText.append("Updated " + i + " rows successfully");
          accountNumberList.removeAll();
          loadAccounts();
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do Next Button
    nextButton = new JButton(">");
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (!rs.isLast()) {
            rs.next();
            accountIDText.setText(rs.getString("acc_id"));
            usernameText.setText(rs.getString("username"));
            passwordText.setText(rs.getString("password"));
            tsText.setText(rs.getString("ts"));
            activeTSText.setText(rs.getString("act_ts"));
          }
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do Next Button
    previousButton = new JButton("<");
    previousButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (!rs.isFirst()) {
            rs.previous();
            accountIDText.setText(rs.getString("acc_id"));
            usernameText.setText(rs.getString("username"));
            passwordText.setText(rs.getString("password"));
            tsText.setText(rs.getString("ts"));
            activeTSText.setText(rs.getString("act_ts"));
          }
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do last Button
    lastButton = new JButton(">|");
    lastButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rs.last();
          accountIDText.setText(rs.getString("acc_id"));
          usernameText.setText(rs.getString("username"));
          passwordText.setText(rs.getString("password"));
          tsText.setText(rs.getString("ts"));
          activeTSText.setText(rs.getString("act_ts"));
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do first Button
    firstButton = new JButton("|<");
    firstButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rs.first();
          accountIDText.setText(rs.getString("acc_id"));
          usernameText.setText(rs.getString("username"));
          passwordText.setText(rs.getString("password"));
          tsText.setText(rs.getString("ts"));
          activeTSText.setText(rs.getString("act_ts"));
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do gotoButton
    gotoButton = new JButton("Goto");
    gotoButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rs.absolute(Integer.parseInt(gotoText.getText()));
          accountIDText.setText(rs.getString("acc_id"));
          usernameText.setText(rs.getString("username"));
          passwordText.setText(rs.getString("password"));
          tsText.setText(rs.getString("ts"));
          activeTSText.setText(rs.getString("act_ts"));
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do freeQueryButton
    freeQueryButton = new JButton("Execute Query");
    freeQueryButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (freeQueryText.getText().toUpperCase().indexOf("SELECT") >= 0) {
            rs = statement.executeQuery(freeQueryText.getText());
            if (rs.next()) {
              accountIDText.setText(rs.getString("acc_id"));
              usernameText.setText(rs.getString("username"));
              passwordText.setText(rs.getString("password"));
              tsText.setText(rs.getString("ts"));
              activeTSText.setText(rs.getString("act_ts"));
            }
          } else {
            int i = statement
                .executeUpdate(freeQueryText.getText());
            errorText.append("Rows affected = " + i);
            loadAccounts();
          }
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    JPanel first = new JPanel(new GridLayout(5, 1));
    first.add(accountNumberListScrollPane);
    first.add(getAccountButton);
    first.add(insertAccountButton);
    first.add(deleteAccountButton);
    first.add(updateAccountButton);

    accountIDText = new JTextField(15);
    usernameText = new JTextField(15);
    passwordText = new JTextField(15);
    tsText = new JTextField(15);
    activeTSText = new JTextField(15);
    errorText = new JTextArea(5, 15);
    errorText.setEditable(false);

    JPanel second = new JPanel();
    second.setLayout(new GridLayout(6, 1));
    second.add(accountIDText);
    second.add(usernameText);
    second.add(passwordText);
    second.add(tsText);
    second.add(activeTSText);

    JPanel third = new JPanel();
    third.add(new JScrollPane(errorText));

    JPanel fourth = new JPanel();
    fourth.add(firstButton);
    fourth.add(previousButton);
    fourth.add(nextButton);
    fourth.add(lastButton);
    fourth.add(gotoText);
    fourth.add(gotoButton);

    JPanel fifth = new JPanel();
    fifth.add(freeQueryText);

    c.add(first);
    c.add(second);
    c.add(third);
    c.add(fourth);
    c.add(fifth);
    c.add(freeQueryButton);
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

  private void displaySQLErrors(SQLException e) {
    errorText.append("SQLException: " + e.getMessage() + "\n");
    errorText.append("SQLState:     " + e.getSQLState() + "\n");
    errorText.append("VendorError:  " + e.getErrorCode() + "\n");
  }

  private void init() {
    connectToDB();
  }

  public static void main(String[] args) {
    RSAccounts accounts = new RSAccounts();

    accounts.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    accounts.init();
    accounts.buildGUI();
  }
}
