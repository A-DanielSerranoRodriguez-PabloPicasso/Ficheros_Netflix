package ui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Waiting {

	private JFrame frame;
	private Component verticalGlue_1, verticalGlue_2;
	private Box horizontalBox;
	private JLabel lblLoading;

	/**
	 * Create the application.
	 */
	public Waiting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(300, 150));
		frame.setMaximumSize(new Dimension(0, 0));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setVisible(false);
		
		setUIcomponents();
	}

	private void setUIcomponents() {
		verticalGlue_1 = Box.createVerticalGlue();
		frame.getContentPane().add(verticalGlue_1);

		horizontalBox = Box.createHorizontalBox();
		frame.getContentPane().add(horizontalBox);

		lblLoading = new JLabel("Creando la base de datos");
		horizontalBox.add(lblLoading);

		verticalGlue_2 = Box.createVerticalGlue();
		frame.getContentPane().add(verticalGlue_2);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	public void dispose() {
		frame.dispose();
	}

}
