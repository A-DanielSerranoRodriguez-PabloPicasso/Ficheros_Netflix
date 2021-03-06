package ui.models;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CC_Panel2 extends AbstractJPanel {
	private Box hbText, hbInput, hbButtons, hbErrors;
	private JLabel lblText, lblError;
	private JTextField txfText;
	private JButton btnResend, btnConfirm;

	public CC_Panel2(String name) {
		/**
		 * Horizontal Boxes are initialized
		 */
		hbText = defaultHB();
		hbInput = defaultHB();
		hbButtons = defaultHB();
		hbErrors = defaultHB();

		/**
		 * JLabels are initialized
		 */
		lblText = new JLabel(name + ", introduce el codigo que te hemos eviado");
		lblError = new JLabel("Codgio erroneo");
		lblError.setVisible(false);

		/**
		 * JTextFields are initialized
		 */
		txfText = new JTextField();
		txfText.setMaximumSize(new Dimension(100, 25));
		txfText.setColumns(15);

		/**
		 * JButtons are initialized
		 */
		btnResend = new JButton("Re-enviar codigo");
		btnConfirm = new JButton("Confirmar");

		/**
		 * The components of the Horizontal Boxes are assigned
		 */
		addToBox(hbText, lblText);
		addToBox(hbInput, txfText);
		addToBox(hbErrors, lblError);
		addToBox(hbButtons, btnResend);
		addToBox(hbButtons, Box.createHorizontalStrut(10));
		addToBox(hbButtons, btnConfirm);

		/**
		 * The components of the JFrame are assigned
		 */
		addVG();
		add(hbText);
		add(Box.createVerticalStrut(40));
		add(hbInput);
		addVG();
		add(hbErrors);
		addVG();
		add(hbButtons);
		addVG();
	}

	/**
	 * Retrieves the buttons of the panel
	 * 
	 * @return JButton[] with the re-send and the confirm buttons
	 */
	public JButton[] getButtons() {
		JButton[] btns = { btnResend, btnConfirm };
		return btns;
	}

	/**
	 * Retrieves the error label of the panel
	 * 
	 * @return JLabel with the error
	 */
	public JLabel getErrors() {
		return lblError;
	}

	/**
	 * Retrieves the text field of the panel
	 * 
	 * @return JTextField
	 */
	public JTextField getInput() {
		return txfText;
	}
}
