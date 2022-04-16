package ui.models;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FP_User extends AbstractJPanel {
	private Box vbUser;
	private Box hbUser, hbUTitle, hbUText, hbButtons;
	private JLabel lblTitle;
	private JTextField txfUser;
	private JButton btnCancel, btnProceed;

	public FP_User() {
		/**
		 * Vertical Boxes are initialized
		 */
		vbUser = defaultVB();

		/**
		 * Horizontal Boxes are initialized
		 */
		hbUser = defaultHB();
		hbUText = defaultHB();
		hbUTitle = defaultHB();
		hbButtons = defaultHB();

		/**
		 * JLabels are initialized
		 */
		lblTitle = new JLabel("Introduzca su nombre de usuario o correo");

		/**
		 * JTextFields are initialized
		 */
		txfUser = new JTextField(15);
		txfUser.setMaximumSize(new Dimension(100, 25));

		/**
		 * JButtons are initialized
		 */
		btnCancel = new JButton("Cancelar");
		btnProceed = new JButton("Continuar");

		/**
		 * The components of the Horizontal Boxes are assigned
		 */
		addToBox(hbUTitle, lblTitle);
		addToBox(hbUText, txfUser);

		addToBox(hbButtons, btnCancel);
		addToBox(hbButtons, Box.createHorizontalStrut(10));
		addToBox(hbButtons, btnProceed);

		addToBox(hbUser, vbUser);

		/**
		 * The components of the Vertical Boxes are assigned
		 */
		addToBox(vbUser, hbUTitle);
		addToBox(vbUser, Box.createVerticalStrut(40));
		addToBox(vbUser, hbUText);

		/**
		 * The components of the JFrame are assigned
		 */
		addVG();
		add(hbUser);
		addVG();
		add(hbButtons);
		addVG();
	}

	/**
	 * Retrieves the buttons of the panel
	 * 
	 * @return JButton[] with the cancel and proceed buttons
	 */
	public JButton[] getButtons() {
		JButton[] btns = { btnCancel, btnProceed };
		return btns;
	}

	/**
	 * Retrieves the text field of the panel
	 * 
	 * @return JTextField
	 */
	public JTextField getText() {
		return txfUser;
	}
}
