package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

abstract public class AbstractDAO {
	private static final String DB_URL = "jdbc:mysql://localhost/netflix";
	private static final String USER = "netflix";
	private static final String PASSWD = "nadmin";
	private static Connection CONN;

	protected AbstractDAO() {
		try {
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConn() {
		return CONN;
	}

	protected Statement createStatement() {
		try {
			return getConn().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
