package org.internetbanking.dataaccsess.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgreSqlDatabase implements IDatabase{
	String url = "jdbc:postgresql://localhost:5432/bank";
	protected Connection connection = null;
	protected String user = "postgres";
	protected String password = "root";
	
	@Override
	public void connectionOpen() {
		try {
			connection = DriverManager.getConnection(url,user, password);
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public void connectionClose() throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection(){
		return this.connection;
	}

}