package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.UserDAO;
import mail.MessageCreator;
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
				JLabel[] errs = loginPanel.getErrorLbls();
				String username = loginPanel.getUsername().getText(),
						passwd = new String(loginPanel.getPasswd().getPassword());
				
				if (username.isEmpty() || passwd.isEmpty()) {
					errs[0].setVisible(true);
					errs[1].setVisible(false);
				} else {
					if (new UserDAO().isActive(username)) {
						User user = new UserDAO().login(username, passwd);
						if (user != null) {
							errs[0].setVisible(false);
							errs[1].setVisible(false);
							System.exit(0);
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
					if (!uDao.usernameExists(userData[0]) && !uDao.mailExists(userData[1])) {
						if (userData[2].equals(userData[3])) {
							uDao.register(userData[0], userData[1], userData[2]);

							errs[0].setVisible(false);
							errs[1].setVisible(false);
							errs[2].setVisible(false);
							for (int i = 0, l = registerPanel.getTexts().length; i < l; i++) {
								registerPanel.getTexts()[i].setText("");
							}
							for (int i = 0, l = registerPanel.getPasswds().length; i < l; i++) {
								registerPanel.getPasswds()[i].setText("");
							}

							int rndm = (int) ((Math.random() * 100000) + 1);

							new MessageCreator().sendDefaultMessage();
							new ConfirmationCode(userData[1], rndm);

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
}
