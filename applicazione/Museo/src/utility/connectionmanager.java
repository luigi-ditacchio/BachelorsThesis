package utility;

import java.sql.*;

public class ConnectionManager {

	private ConnectionManager(){};
	
	private static boolean driverLoaded = false;
	private static final String MY_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MY_URL = "jdbc:mysql://localhost/laureadb";
	private static final String LOGIN = "visitatore";
	private static final String PASSWD = "visitatore";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(!driverLoaded) {
			Class.forName(MY_DRIVER);
			driverLoaded = true;
		}
		return DriverManager.getConnection(MY_URL,LOGIN,PASSWD);
	}
}
