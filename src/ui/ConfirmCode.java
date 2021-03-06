package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import dao.UserDAO;
import ui.models.CC_Panel1;
import ui.models.CC_Panel2;
import utils.EmailHelper;

public class ConfirmCode {
	private JFrame frame;
	private CC_Panel1 panel1;
	private CC_Panel2 panel2;
	private String name, mail;

	/**
	 * Create the application.
	 */
	public ConfirmCode(String name, String mail, int code) {
		this.name = name;
		this.mail = mail;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(500, 700));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setTitle("Netflix - Confirmar Codigo");
		frame.setVisible(true);

		setUIcomponents();
	}

	/**
	 * Sets the panels of the frame
	 */
	private void setUIcomponents() {
		panel1 = new CC_Panel1(mail);
		panel2 = new CC_Panel2(name);
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(panel2);

		setUIbehaviour();
	}

	/**
	 * Sets the behaviour of the panels components
	 */
	private void setUIbehaviour() {
		JButton[] btnP1 = panel1.getButtons();
		JButton[] btnP2 = panel2.getButtons();

		/**
		 * Sends an email with the new confirmation code
		 */
		btnP1[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rndm = (int) ((Math.random() * 100000) + 1);
				new EmailHelper().verificationMessage(mail, rndm);
			}
		});

		/**
		 * Makes the second panel visible, which lets the user input the confirmation
		 * code
		 */
		btnP1[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
			}
		});

		/**
		 * Sends an email with the new confirmation code
		 */
		btnP2[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rndm = (int) ((Math.random() * 100000) + 1);
				new EmailHelper().verificationMessage(mail, rndm);
			}
		});

		/**
		 * Checks the code entered is the same as the confirmation code of the user
		 */
		btnP2[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO uDao = new UserDAO();
				if (uDao.activate(name, Integer.parseInt(panel2.getInput().getText()))) {
					if (!hasFile(name)) {
						createFile(name);
					}
					panel2.getErrors().setVisible(false);
					frame.dispose();
				} else {
					panel2.getErrors().setVisible(true);
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
