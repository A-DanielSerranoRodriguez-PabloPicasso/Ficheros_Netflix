package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.UserDAO;
import models.User;
import ui.models.LoginPanel;
import ui.models.RegisterPanel;
import utils.EmailHelper;

public class Launcher {

	private JFrame frame;
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;

	/**
	 * Create the application.
	 */
	public Launcher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(600, 400));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setVisible(true);

		setUIcomponents();
		setUIbehaviour();
	}

	/**
	 * Sets the panels of the frame
	 */
	private void setUIcomponents() {
		loginPanel = new LoginPanel();
		registerPanel = new RegisterPanel();
		frame.getContentPane().add(loginPanel);
		frame.getContentPane().add(registerPanel);
	}

	/**
	 * Sets the behaviour of the panels components
	 */
	private void setUIbehaviour() {
		/**
		 * Logs in the user. If the user's account is not activated, it will pop a
		 * message. If it doesn't exists, an error message will be visible
		 */
		loginPanel.getLoginBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO uDao = new UserDAO();
				JLabel[] errs = loginPanel.getErrorLbls();
				String username = loginPanel.getUsername().getText(),
						passwd = new String(loginPanel.getPasswd().getPassword());

				if (username.isEmpty() || passwd.isEmpty()) {
					errs[0].setVisible(true);
					errs[1].setVisible(false);
				} else {
					User user = uDao.login(username, passwd);
					if (uDao.userExists(username)) {
						if (uDao.isActive(username)) {
							if (user != null) {
								errs[0].setVisible(false);
								errs[1].setVisible(false);
								if (!hasFile(username)) {
									createFile(username);
								}
								frame.dispose();
								new ShowList(user);
							} else {
								errs[0].setVisible(false);
								errs[1].setVisible(true);
							}
						} else {
							JOptionPane.showMessageDialog(frame, "Activa tu cuenta");
							loginPanel.getVerifyBtn().setVisible(true);
							loginPanel.getLoginBtn().setVisible(false);
							errs[0].setVisible(false);
							errs[1].setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Usuario no encontrado");
						errs[0].setVisible(false);
						errs[1].setVisible(false);
					}
				}
			}
		});

		/**
		 * If the user exists, a confirmation code panel will be created
		 */
		loginPanel.getVerifyBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO uDao = new UserDAO();
				String text = loginPanel.getUsername().getText(),
						passwd = new String(loginPanel.getPasswd().getPassword());
				User user = uDao.login(text, passwd);

				if (user != null) {
					new ConfirmationCode(user.getName(), user.getEmail(), uDao.getAC("username", user.getName()));
				}

				loginPanel.getVerifyBtn().setVisible(false);
				loginPanel.getLoginBtn().setVisible(true);
			}
		});

		/**
		 * Makes the register panel visible, hiding the login panel
		 */
		loginPanel.getRegisterBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				registerPanel.setVisible(true);
			}
		});

		/**
		 * A new frame will be created for reseting the password
		 */
		loginPanel.getForgotPasswd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ForgotPasswd();
			}
		});

		/**
		 * Makes the login panel visible, hiding the register panel
		 */
		registerPanel.getCancelBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				registerPanel.setVisible(false);
			}
		});

		/**
		 * Checks all the inputs are filled. Once done, checks the user and email aren't
		 * already registered in the database. Then, checks the passwords match. If they
		 * do, the user is registered and a confirmation frame is created. If something
		 * fails, an error is displayed
		 */
		registerPanel.getRegisterBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel[] errs = registerPanel.getErrorLbls();
				String[] userData = { registerPanel.getTexts()[0].getText(), registerPanel.getTexts()[1].getText(),
						new String(registerPanel.getPasswds()[0].getPassword()),
						new String(registerPanel.getPasswds()[1].getPassword()) };

				if (userData[0].isEmpty() || userData[1].isEmpty() || userData[2].isEmpty() || userData[3].isEmpty()) {
					errs[0].setVisible(true);
					errs[1].setVisible(false);
					errs[2].setVisible(false);
				} else {
					UserDAO uDao = new UserDAO();
					if (!uDao.userExists(userData[0]) && !uDao.userExists(userData[1])) {
						if (userData[2].equals(userData[3])) {
							int rndm = (int) ((Math.random() * 100000) + 1);

							uDao.register(userData[0], userData[1], userData[2], rndm);

							errs[0].setVisible(false);
							errs[1].setVisible(false);
							errs[2].setVisible(false);
							for (int i = 0, l = registerPanel.getTexts().length; i < l; i++) {
								registerPanel.getTexts()[i].setText("");
							}
							for (int i = 0, l = registerPanel.getPasswds().length; i < l; i++) {
								registerPanel.getPasswds()[i].setText("");
							}

							new EmailHelper().sendDefaultMessage(userData[1], rndm);
							new ConfirmationCode(userData[0], userData[1], rndm);

							registerPanel.setVisible(false);
							loginPanel.setVisible(true);
							loginPanel.getUsername().setText(userData[0]);
						} else {
							errs[0].setVisible(false);
							errs[1].setVisible(false);
							errs[2].setVisible(true);
						}
					} else {
						errs[0].setVisible(false);
						errs[1].setVisible(true);
						errs[2].setVisible(false);
					}
				}
			}
		});
	}

	/**
	 * Checks the user has a favorites files in the "exports" folder
	 * 
	 * @param username String that represents the username of the user
	 * @return True if the file exists
	 */
	private boolean hasFile(String username) {
		File file = new File("exports/" + username + ".csv");
		return file.exists();
	}

	/**
	 * Creates a favorites file in the "exports" folder
	 * 
	 * @param username String that represents the username of the user
	 */
	private void createFile(String username) {
		try {
			new File("exports/" + username + ".csv").createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
