package ui.models;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ConfirmPanel2 extends AbstractJPanel {
	private Box hbText, hbInput, hbButtons, hbErrors;
	private JLabel lblText, lblError;
	private JTextField txfText;
	private JButton btnResend, btnConfirm;

	public ConfirmPanel2(String name) {
		hbText = defaultHB();
		hbInput = defaultHB();
		hbButtons = defaultHB();
		hbErrors = defaultHB();

		lblText = new JLabel(name + ", introduce el codigo que te hemos eviado");
		lblError = new JLabel("Codgio erroneo");
		lblError.setVisible(false);

		txfText = new JTextField();
		txfText.setMaximumSize(new Dimension(100, 25));
		txfText.setColumns(15);

		btnResend = new JButton("Re-enviar codigo");
		btnConfirm = new JButton("Confirmar");

		addCorrectly(hbText, lblText);
		addCorrectly(hbInput, txfText);
		addCorrectly(hbErrors, lblError);
		addCorrectly(hbButtons, btnResend);
		addCorrectly(hbButtons, Box.createHorizontalStrut(10));
		addCorrectly(hbButtons, btnConfirm);

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

	public JButton[] getButtons() {
		JButton[] btns = { btnResend, btnConfirm };
		return btns;
	}

	public JLabel getErrors() {
		return lblError;
	}

	public JTextField getInput() {
		return txfText;
	}
}
