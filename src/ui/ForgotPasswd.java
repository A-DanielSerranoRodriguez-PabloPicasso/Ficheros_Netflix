package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.UserDAO;
import ui.models.FpPasswdPanel;
import ui.models.FpUserPanel;
import utils.EmailHelper;

public class ForgotPasswd {

	private JFrame frame;
	private FpUserPanel panel1;
	private FpPasswdPanel panel2;
	private String user;

	/**
	 * Create the application.
	 */
	public ForgotPasswd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setVisible(true);

		setUIcomponents();
		setUIbehaviour();
	}

	private void setUIcomponents() {
		panel1 = new FpUserPanel();
		panel2 = new FpPasswdPanel();
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(panel2);
	}

	private void setUIbehaviour() {
		UserDAO uDao = new UserDAO();
		JButton[] p1btns = panel1.getButtons();
		JButton[] p2btns = panel2.getButtons();

		p1btns[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		p1btns[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user = panel1.getText().getText();
				if (uDao.userExists(user)) {
					panel1.setVisible(false);
					panel2.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "El usuario o correo no existe");
				}
			}
		});

		p2btns[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);
				panel1.setVisible(true);
			}
		});

		p2btns[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel2.passwdMatch()) {
					EmailHelper emailHelper = new EmailHelper();
					int rndm = (int) ((Math.random() * 100000) + 1);
					uDao.updatePasswd(user, new String(panel2.getPasswd()));
					if (UserDAO.usernameExists(user)) {
						emailHelper.sendDefaultMessage(uDao.getMail(user), rndm);
					} else {
						emailHelper.sendDefaultMessage(user, rndm);
					}

					frame.dispose();
				} else
					JOptionPane.showMessageDialog(frame, "Las contras no coinciden");
			}
		});

		p2btns[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel2.passwdMatch()) {
					EmailHelper emailHelper = new EmailHelper();
					int rndm = (int) ((Math.random() * 100000) + 1);
					uDao.updatePasswd(user, new String(panel2.getPasswd()));
					if (UserDAO.usernameExists(user)) {
						emailHelper.sendDefaultMessage(uDao.getMail(user), rndm);
						new ConfirmationCode(user, uDao.getMail(user), rndm);
					} else {
						emailHelper.sendDefaultMessage(user, rndm);
						new ConfirmationCode(uDao.getUsername(user), user, rndm);
					}
					frame.dispose();

				} else
					JOptionPane.showMessageDialog(frame, "Las contras no coinciden");
			}
		});
	}

}
