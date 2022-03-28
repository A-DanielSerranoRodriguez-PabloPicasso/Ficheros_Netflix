package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.User;

public class UserDAO extends AbstractDAO {
	private Statement statement;

	public UserDAO() {
		super();
		statement = this.createStatement();
	}

	public User login(String identification, String passwd) {
		if (checkUsernamePasswd(identification, passwd)) {
			try (ResultSet user = getUserByUsername(identification)) {
				user.next();
				return new User(user.getString(1), user.getString(2), user.getString(3));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (checkMailPasswd(identification, passwd)) {
			try (ResultSet user = getUserByMail(identification)) {
				user.next();
				return new User(user.getString(1), user.getString(2), user.getString(3));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean isActive(String identification) {
		if (mailExists(identification))
			return checkActive("mail", identification);
		else if (usernameExists(identification))
			return checkActive("username", identification);

		return false;
	}

	public void register(String username, String mail, String passwd) {
		try {
			statement.execute("INSERT INTO users(username, mail, passwd, active) VALUES ('" + username + "','" + mail
					+ "','" + passwd + "', 0);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean checkUsernamePasswd(String identification, String passwd) {
		if (usernameExists(identification)) {
			try (ResultSet user = getUserPasswd("username", identification)) {
				user.next();
				if (passwd.equals(user.getString(1)))
					return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	private boolean checkMailPasswd(String identification, String passwd) {
		if (mailExists(identification)) {
			try (ResultSet user = getUserPasswd("mail", identification)) {
				user.next();
				if (passwd.equals(user.getString(1)))
					return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean usernameExists(String username) {
		try (ResultSet user = isUser("username", username)) {
			if (user.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean mailExists(String mail) {
		try (ResultSet user = isUser("mail", mail)) {
			if (user.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private ResultSet isUser(String what, String identification) {
		try {
			return statement
					.executeQuery("SELECT " + what + " FROM users WHERE " + what + "='" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean checkActive(String what, String identification) {
		try (ResultSet res = statement
				.executeQuery("SELECT active FROM users WHERE " + what + "='" + identification + "';")) {
			if (res.next())
				return res.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private ResultSet getUserPasswd(String where, String identification) {
		try {
			return statement.executeQuery("SELECT passwd FROM users WHERE " + where + "='" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private ResultSet getUserByUsername(String identification) {
		try {
			return statement.executeQuery("SELECT * FROM users WHERE username='" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private ResultSet getUserByMail(String identification) {
		try {
			return statement.executeQuery("SELECT * FROM users WHERE mail='" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
