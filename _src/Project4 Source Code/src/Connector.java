import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.Statement;


public class Connector {
	Connection conn;
	Statement stat;
	static String url, database, username, password, hostname, port, driver;
	
	public Connector(Properties props, String pass){
		database = props.getProperty("Database");
		username = props.getProperty("User_Name");
		password = pass;
		hostname = props.getProperty("Host_Name");
		port = props.getProperty("Port");
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
	}
	public boolean open(){
		try {
			DriverManager.registerDriver((java.sql.Driver) Class.forName(driver).newInstance());
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn == null)
				return false;
		}
		System.out.println("Connection successful");
		return true;
	}
	
	public ResultSet executeQuery(String s) throws SQLException{
		return stat.executeQuery(s);
	}
	public void executeUpdate(String s) throws SQLException{
		stat.executeUpdate(s);
	}
}
