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
	private String name, mail;

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setVisible(true);

		setUIcomponents();
		setUIbehaviour();
	}

	/**
	 * Sets the panels of the frame
	 */
	private void setUIcomponents() {
		panel1 = new FpUserPanel();
		panel2 = new FpPasswdPanel();
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(panel2);
	}

	/**
	 * Sets the behaviour of the components of the panels
	 */
	private void setUIbehaviour() {
		UserDAO uDao = new UserDAO();
		JButton[] p1btns = panel1.getButtons();
		JButton[] p2btns = panel2.getButtons();

		/**
		 * Exists the frame
		 */
		p1btns[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		/**
		 * Checks the user entered exists. If it does, moves to the next panel
		 */
		p1btns[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name = panel1.getText().getText();
				if (uDao.userExists(name)) {
					panel1.setVisible(false);
					panel2.setVisible(true);
					mail = uDao.getUserInfo(name)[1];
					name = uDao.getUserInfo(name)[0];
				} else {
					JOptionPane.showMessageDialog(frame, "El usuario o correo no existe");
				}
			}
		});

		/**
		 * Returns to the previous panel
		 */
		p2btns[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel2.setVisible(false);
				panel1.setVisible(true);
			}
		});

		/**
		 * Checks the entered passwords match. If they do, the panel is deleted, if not
		 * a message pops
		 */
		p2btns[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel2.passwdMatch()) {
					EmailHelper emailHelper = new EmailHelper();
					int rndm = (int) ((Math.random() * 100000) + 1);
					uDao.updatePasswd(name, new String(panel2.getPasswd()));
					emailHelper.verificationMessage(mail, rndm);

					frame.dispose();
				} else
					JOptionPane.showMessageDialog(frame, "Las contras no coinciden");
			}
		});

		/**
		 * Checks the entered passwords match. If they do, a confirmation email is sent
		 * to the mail's user, the panel is deleted and a confirmation panel is created,
		 * if not a message pops
		 */
		p2btns[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panel2.passwdMatch()) {
					EmailHelper emailHelper = new EmailHelper();
					int rndm = (int) ((Math.random() * 100000) + 1);
					uDao.updatePasswd(name, new String(panel2.getPasswd()));
					emailHelper.verificationMessage(mail, rndm);
					new ConfirmationCode(name, mail, rndm);
					frame.dispose();

				} else
					JOptionPane.showMessageDialog(frame, "Las contras no coinciden");
			}
		});
	}

}
