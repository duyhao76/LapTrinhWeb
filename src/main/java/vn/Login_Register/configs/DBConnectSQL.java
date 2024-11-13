package vn.Login_Register.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectSQL {
	private final String serverName = "DUYHAO\\DUYHAO";
	private final String dbName = "BaiTapVeNha";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "Ahao25092004";
	
	public Connection getConnection() throws Exception{
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + " ;databaseName=" + dbName;

		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + " ;databaseName="
					+ dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);

}
public void main(String[] args) {
	try {
		System.out.println(new DBConnectSQL().getConnection());
	}catch (Exception e) {
		e.printStackTrace();
	}
}
}
