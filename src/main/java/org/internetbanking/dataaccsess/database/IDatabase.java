package org.internetbanking.dataaccsess.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {
	public void connectionOpen();
	public void connectionClose() throws SQLException;
	public Connection getConnection();
}
