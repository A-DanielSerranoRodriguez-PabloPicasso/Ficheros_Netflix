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

	protected static Connection getConn() {
		return CONN;
	}
	
	protected static void setConn() {
		try {
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected static Statement createStatement() {
		try {
			setConn();
			return getConn().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
