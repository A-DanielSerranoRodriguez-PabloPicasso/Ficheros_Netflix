package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import dao.UserDAO;
import ui.models.ConfirmPanel1;
import ui.models.ConfirmPanel2;
import utils.EmailHelper;

public class ConfirmationCode {
	private JFrame frame;
	private ConfirmPanel1 panel1;
	private ConfirmPanel2 panel2;
	private String name, mail;

	/**
	 * Create the application.
	 */
	public ConfirmationCode(String name, String mail, int code) {
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
		frame.setVisible(true);

		setUIcomponents();
	}

	private void setUIcomponents() {
		panel1 = new ConfirmPanel1(mail);
		panel2 = new ConfirmPanel2(name);
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(panel2);

		setUIbehaviour();
	}

	private void setUIbehaviour() {
		JButton[] btnP1 = panel1.getButtons();
		JButton[] btnP2 = panel2.getButtons();

		btnP1[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rndm = (int) ((Math.random() * 100000) + 1);
				EmailHelper.sendDefaultMessage(mail, rndm);
			}
		});

		btnP1[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
			}
		});

		btnP2[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rndm = (int) ((Math.random() * 100000) + 1);
				EmailHelper.sendDefaultMessage(mail, rndm);
			}
		});

		btnP2[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO uDao = new UserDAO();
				if (uDao.registerCheck(name, Integer.parseInt(panel2.getInput().getText()))) {
					uDao.updateActiveStatus(name);
					panel2.getErrors().setVisible(false);
					frame.dispose();
				} else {
					panel2.getErrors().setVisible(true);
				}
			}
		});

	}
}
