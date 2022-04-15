package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Show;
import models.User;

public class UserDAO extends AbstractDAO {
	private static Statement statement;

	public UserDAO() {
		super();
		statement = AbstractDAO.createStatement();
	}

	/**
	 * Confirms the user exists in the database
	 * 
	 * @param identification String that represents the username or mail
	 * @return True if exists, false if not
	 */
	public boolean userExists(String identification) {
		if (credIsUsername(identification) || credIsMail(identification))
			return true;
		else
			return false;
	}

	/**
	 * Returns a user to log in
	 * 
	 * @param identification String that can be the username or mail
	 * @param passwd         String that represents the password
	 * @return Object 'User' if the user exists, null if not
	 */
	public User login(String identification, String passwd) {
		if (credIsUsername(identification)) {
			return getUser(getUserData("username", identification), passwd);
		} else if (credIsMail(identification)) {
			return getUser(getUserData("mail", identification), passwd);
		}

		return null;
	}

	/**
	 * Registers the user in the database
	 * 
	 * @param username String that represents the username
	 * @param mail     String that represents the mail
	 * @param passwd   String that represents the password
	 * @param code     Int that represents the activation code
	 */
	public void register(String username, String mail, String passwd, int code) {
		try {
			statement.execute("INSERT INTO users(username, mail, passwd, active, activation_code) VALUES ('" + username
					+ "','" + mail + "','" + passwd + "', 0, " + code + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks that the user account is active
	 * 
	 * @param identification String that can be the username or mail
	 * @return True if the account is active, false if not OR if the account doesn't
	 *         exists
	 */
	public boolean isActive(String identification) {
		if (credIsUsername(identification)) {
			return checkActive("username", identification);
		} else if (credIsMail(identification)) {
			return checkActive("mail", identification);
		}

		return false;
	}

	/**
	 * Activates the user account
	 * 
	 * @param identification String that represents the username or mail
	 * @return True if the account has been activated, false if not
	 */
	public boolean activate(String identification, int activationCode) {
		if (!isActive(identification)) {
			if (credIsUsername(identification)) {
				if (getAC("username", identification) == activationCode) {
					return activateAccount("username", identification);
				}
			} else if (credIsMail(identification)) {
				if (getAC("mail", identification) == activationCode) {
					return activateAccount("mail", identification);
				}
			}
		}

		return false;
	}

	/**
	 * Retrieves the activation code of the user from the database
	 * 
	 * @param method         String that represents what we compare to get the data
	 * @param identification String that represents the username or mail
	 * @return Int that is the confirmation code, or -1 if the user doesn't exists
	 */
	public int getAC(String method, String identification) {
		try (ResultSet ac = statement
				.executeQuery("SELECT activation_code FROM users WHERE " + method + "='" + identification + "';")) {
			if (ac.next())
				return ac.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/**
	 * Updates the activation code
	 * 
	 * @param identification String that represents the username or mail
	 * @param activationCode Int that represents the new activation code
	 */
	public static void updateActivCode(String identification, int activationCode) {
		if (credIsUsername(identification)) {
			updateAC(activationCode, "username", identification);
		} else if (credIsMail(identification)) {
			updateAC(activationCode, "mail", identification);
		}
	}

	/**
	 * Reset the active status of the account to "not active" and changes the
	 * password
	 * 
	 * @param identification String that represents the username or mail
	 */
	public void resetCredentials(String identification) {
		if (credIsUsername(identification)) {
			deactivateAccount("username", identification);
			resetPasswd("username", identification);
		} else if (credIsMail(identification)) {
			deactivateAccount("mail", identification);
			resetPasswd("mail", identification);
		}
	}

	/**
	 * Retrieves the username and mail of the user
	 * 
	 * @param identification String that represents the username or mail
	 * @return String[] with the username and mail
	 */
	public String[] getUserInfo(String identification) {
		String[] info = new String[2];
		if (credIsUsername(identification)) {
			info[0] = identification;
			info[1] = getUserMail(identification);
		} else if (credIsMail(identification)) {
			info[0] = getUsername(identification);
			info[1] = identification;
		}
		return info;
	}

	/**
	 * Retrieves the separator the user uses for its CSV
	 * 
	 * @param username String that represents the username of the user
	 * @return String that represents the separator
	 */
	public String getSeparator(String username) {
		try (ResultSet user = getUserData("username", username)) {
			if (user.next()) {
				return user.getString("separator_char");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Sets the separator for the user
	 * 
	 * @param username  String that represents the username of the user
	 * @param separator String that represents the separator used
	 */
	public void setSeparator(String username, String separator) {
		try {
			statement.execute("update users set separator_char='" + separator + "' where username='" + username + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the user password
	 * 
	 * @param identification String that represents the username or mail
	 * @param passwd         String that represents the new password
	 */
	public void updatePasswd(String identification, String passwd) {
		if (credIsUsername(identification)) {
			try {
				statement.execute("UPDATE users SET passwd = '" + passwd + "', active = 0 WHERE username = '"
						+ identification + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (credIsMail(identification)) {
			try {
				statement.execute("UPDATE users SET passwd = '" + passwd + "', active = 0 WHERE mail = '"
						+ identification + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void populateUserFavs(User user, ArrayList<Integer> ids) {
		for (Integer id : ids) {
			try (ResultSet show = statement.executeQuery("SELECT * FROM shows WHERE id=" + id + ";")) {
				while (show.next()) {
					user.getFavorites()
							.add(new Show(show.getString(1), show.getString(2), show.getString(3), show.getString(4),
									show.getString(5), show.getString(6), show.getString(7), show.getString(8),
									show.getString(9), show.getString(10), show.getString(11), show.getString(12)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the user of the database
	 * 
	 * @param user   ResultSet of the user searched in the database
	 * @param passwd String that represents the password of the user
	 * @return Object 'User' with the necessary user data
	 */
	private User getUser(ResultSet user, String passwd) {

		try {
			if (user.next())
				if (comparePasswd(passwd, user.getString("passwd")))
					return new User(user.getString("username"), user.getString("mail"), user.getString("passwd"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves the mail of the user
	 * 
	 * @param username String that represents the username
	 * @return String that represents the mail
	 */
	private String getUserMail(String username) {
		try (ResultSet user = statement.executeQuery("SELECT mail FROM users WHERE username='" + username + "';")) {
			if (user.next()) {
				return user.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves the username of the user
	 * 
	 * @param mail String that represents the mail
	 * @return String that represents the username
	 */
	private String getUsername(String mail) {
		try (ResultSet user = statement.executeQuery("SELECT username FROM users WHERE mail='" + mail + "';")) {
			if (user.next()) {
				return user.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Compares two strings believed to be passwords
	 * 
	 * @param pass1 String that represents one of the passwords
	 * @param pass2 String that represents the other password
	 * @return True if the are equal, false if not
	 */
	private boolean comparePasswd(String pass1, String pass2) {
		return pass1.equals(pass2);
	}

	/**
	 * Updates the active condition in the database to "active"
	 * 
	 * @param method         String that represents what we compare to get the data
	 * @param identification String that represents the username or mail
	 * @return True if the account has been updated, false if not
	 */
	private boolean activateAccount(String method, String identification) {
		try {
			return !statement.execute("UPDATE users SET active = 1 WHERE " + method + " = '" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Updates the active condition in the database to "not active"
	 * 
	 * @param method         String that represents what we compare to get the data
	 * @param identification String that represents the username or mail
	 * @return True if the account has been updated, false if not
	 */
	private boolean deactivateAccount(String method, String identification) {
		try {
			return !statement.execute("UPDATE users SET active = 0 WHERE " + method + " = '" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Resets the password in the database
	 * 
	 * @param method         String that represents what we compare to get the data
	 * @param identification String that represents the username or mail
	 * @return True if the password has been updated, false if not
	 */
	private boolean resetPasswd(String method, String identification) {
		try {
			return !statement.execute("UPDATE users SET passwd = " + (Math.random() * (Math.random() * 99)) + " WHERE "
					+ method + " = '" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Returns the active status of the user from the database
	 * 
	 * @param method         String that represents what we compare to get the data
	 * @param identification String that represents the username or mail
	 * @return True if the account is active, false if not OR if the account doesn't
	 *         exists
	 */
	private boolean checkActive(String method, String identification) {
		try (ResultSet res = statement
				.executeQuery("SELECT active FROM users WHERE " + method + "='" + identification + "';")) {
			if (res.next())
				return res.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Updates the activation code in the database
	 * 
	 * @param code           Int that represents the new activation code
	 * @param method         String that represents what what we use to know where
	 *                       to put the data
	 * @param identification String that represents the username or mail
	 */
	private static void updateAC(int code, String method, String identification) {
		try {
			statement.execute(
					"UPDATE users SET activation_code=+" + code + " WHERE " + method + "='" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the user identification entered is the username
	 * 
	 * @param identification String that represents the username or mail
	 * 
	 * @return True if it represents the username, false if not
	 */
	private static boolean credIsUsername(String identification) {
		try (ResultSet user = statement.executeQuery("SELECT * FROM users WHERE username='" + identification + "';")) {
			if (user.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Checks if the user identification entered is the mail
	 * 
	 * @param identification String that represents the username or mail
	 * @return True if it represents the mail, false if not
	 */
	private static boolean credIsMail(String identification) {
		try (ResultSet user = statement.executeQuery("SELECT * FROM users WHERE mail='" + identification + "';")) {
			if (user.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Retrieves the user data from the database
	 * 
	 * @param method         String that represents what we compare to get the data
	 * @param identification String that represents the username or mail
	 * @return ResultSet with the user data
	 */
	private ResultSet getUserData(String method, String identification) {
		try {
			return statement.executeQuery("SELECT * FROM users WHERE " + method + "='" + identification + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}