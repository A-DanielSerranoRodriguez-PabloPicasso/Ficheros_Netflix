package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ui.models.ConfirmPanel1;
import ui.models.ConfirmPanel2;

public class ConfirmationCode {
	private JFrame frame;
	private ConfirmPanel1 panel1;
	private ConfirmPanel2 panel2;

	/**
	 * Create the application.
	 */
	public ConfirmationCode(String mail, int number) {
		initialize(mail, number);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String mail, int number) {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(500, 700));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setVisible(true);

		setUIcomponents(mail, number);
	}

	private void setUIcomponents(String mail, int number) {
		panel1 = new ConfirmPanel1(mail);
		panel2 = new ConfirmPanel2();
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(panel2);
		
		setUIbehaviour(number);
	}

	private void setUIbehaviour(int number) {
		JButton[] btnP1 = panel1.getButtons();
//		JButton[] btnP2 = panel2.getButtons();
		
		btnP1[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		btnP1[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
			}
		});

	}
}
