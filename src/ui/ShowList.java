package ui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import models.User;
import ui.models.ShowPanel;
import java.awt.CardLayout;

public class ShowList {
	private User usuario;

	private JFrame frame;
	private ShowPanel showPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowList window = new ShowList();
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
	public ShowList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(1200, 800));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		setUIcomponents();
	}
	
	private void setUIcomponents() {
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		this.showPanel = new ShowPanel(usuario);
		
		frame.getContentPane().add(showPanel, "name_14215655555075");
	}
}
