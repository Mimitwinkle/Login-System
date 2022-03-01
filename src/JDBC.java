
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	protected static Connection getConnection() throws SQLException {
		String DBusername = "root";
		String DBpassword = "";
		String DBurl = "jdbc:mysql://localhost:3306/java_demo";
		Connection connection;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
		
		return connection;
	}
}
