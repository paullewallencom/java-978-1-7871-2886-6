import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Connector {
	 private Connection conn = null;
     Statement stat = null;

     private static String url; // the url of the database
     private static String database; // the name of the database
     private static String username; //your username
     private static String password;    //your password
     private static String hostname;
     private static String port;  // the port number to connect
     private static String driver;

  public Connector (Properties props, String pswd) {
     database=props.getProperty("DATABASE");
     username=props.getProperty("USER_NAME");
     password=pswd;
     hostname=props.getProperty("HOST_NAME");
     port=props.getProperty("PORT");
     driver="com.mysql.jdbc.Driver";
     url = "jdbc:mysql://"+hostname+":"+port+"/"+database;

  }

  public boolean open() {
     try{
        DriverManager.registerDriver( (java.sql.Driver) Class.forName(driver).newInstance());
        conn = DriverManager.getConnection (url, username, password);
        stat = conn.createStatement();
     }
     catch( Exception ex ) {

        System.out.println("Error: "+ex);
        if ( conn == null ) { return false; }
     }

     System.out.println("Database connection was established successfully.");
     return true;
  }

  public void close() {
     try{
        if ( stat != null ) {
          stat.close();
        }

        if ( conn != null ) {
          conn.close();
        }
     }
     catch( SQLException ex ) {
        System.out.println("Error: "+ex);
        return;
     }

     System.out.println("Database connection was closed successfully.");
  }

  public boolean isConnected() {
    try {
        if ( conn!=null && !conn.isClosed() ) {
          return true;
        }
    }
    catch( SQLException ex ) {

    }
     return false;
  }

  public int executeUpdate(String s) throws SQLException {
     return stat.executeUpdate(s);
  }

  public ResultSet executeQuery(String s) throws SQLException {
     return stat.executeQuery(s);
  }

}
