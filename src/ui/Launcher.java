package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import dao.UserDAO;
import models.User;
import ui.models.LoginPanel;
import ui.models.RegisterPanel;

public class Launcher {

	private JFrame frame;
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher window = new Launcher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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

		setUIcomponents();
		setUIbehaviour();
	}

	private void setUIcomponents() {
		loginPanel = new LoginPanel();
		registerPanel = new RegisterPanel();
		frame.getContentPane().add(loginPanel);
		frame.getContentPane().add(registerPanel);
	}

	private void setUIbehaviour() {
		loginPanel.getLoginBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = loginPanel.getUsername().getText(),
						passwd = new String(loginPanel.getPasswd().getPassword());

				if (username.isEmpty() || passwd.isEmpty()) {
					loginPanel.getErrorLbls()[0].setVisible(true);
					loginPanel.getErrorLbls()[1].setVisible(false);
				} else {
					User user = new UserDAO().login(username, passwd);
					if (user != null) {
						loginPanel.getErrorLbls()[0].setVisible(false);
						loginPanel.getErrorLbls()[1].setVisible(false);
						System.exit(0);
					} else {
						loginPanel.getErrorLbls()[0].setVisible(false);
						loginPanel.getErrorLbls()[1].setVisible(true);
					}
				}
			}
		});

		loginPanel.getRegisterBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				registerPanel.setVisible(true);
			}
		});

		registerPanel.getCancelBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				registerPanel.setVisible(false);
			}
		});

		registerPanel.getRegisterBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = registerPanel.getTexts()[0].getText(), mail = registerPanel.getTexts()[1].getText(),
						passwd = new String(registerPanel.getPasswds()[0].getPassword()),
						confirmPasswd = new String(registerPanel.getPasswds()[1].getPassword());

				if (username.isEmpty() || mail.isEmpty() || passwd.isEmpty() || confirmPasswd.isEmpty()) {
					registerPanel.getErrorLbls()[0].setVisible(true);
					registerPanel.getErrorLbls()[1].setVisible(false);
					registerPanel.getErrorLbls()[2].setVisible(false);
				} else {
					UserDAO uDao = new UserDAO();
					if (!uDao.usernameExists(username) && !uDao.mailExists(mail)) {
						if (passwd.equals(confirmPasswd)) {
							uDao.register(username, mail, confirmPasswd);

							registerPanel.getErrorLbls()[0].setVisible(false);
							registerPanel.getErrorLbls()[1].setVisible(false);
							registerPanel.getErrorLbls()[2].setVisible(false);
							for (int i = 0, l = registerPanel.getTexts().length; i < l; i++) {
								registerPanel.getTexts()[i].setText("");
							}
							for (int i = 0, l = registerPanel.getPasswds().length; i < l; i++) {
								registerPanel.getPasswds()[i].setText("");
							}
							registerPanel.setVisible(false);

							loginPanel.setVisible(true);
							loginPanel.getUsername().setText(username);
						} else {
							registerPanel.getErrorLbls()[0].setVisible(false);
							registerPanel.getErrorLbls()[1].setVisible(false);
							registerPanel.getErrorLbls()[2].setVisible(true);
						}
					} else {
						registerPanel.getErrorLbls()[0].setVisible(false);
						registerPanel.getErrorLbls()[1].setVisible(true);
						registerPanel.getErrorLbls()[2].setVisible(false);
					}
				}
			}
		});
	}
}
