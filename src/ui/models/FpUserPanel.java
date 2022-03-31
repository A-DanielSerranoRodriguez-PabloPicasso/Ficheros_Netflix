package ui.models;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FpUserPanel extends AbstractJPanel {
	private Box vbUser;
	private Box hbUser, hbUTitle, hbUText, hbButtons;
	private JLabel lblTitle;
	private JTextField txfUser;
	private JButton btnCancel, btnProceed;

	public FpUserPanel() {
		super();

		vbUser = defaultVB();

		hbUser = defaultHB();
		hbUText = defaultHB();
		hbUTitle = defaultHB();
		hbButtons = defaultHB();

		lblTitle = new JLabel("Introduzca su nombre de usuario o correo");

		txfUser = new JTextField(15);
		txfUser.setMaximumSize(new Dimension(100, 25));

		btnCancel = new JButton("Cancelar");
		btnProceed = new JButton("Continuar");

		addToBox(hbUTitle, lblTitle);
		addToBox(hbUText, txfUser);

		addToBox(hbButtons, btnCancel);
		addToBox(hbButtons, Box.createHorizontalStrut(10));
		addToBox(hbButtons, btnProceed);

		addToBox(vbUser, hbUTitle);
		addToBox(vbUser, Box.createVerticalStrut(40));
		addToBox(vbUser, hbUText);

		addToBox(hbUser, vbUser);

		addVG();
		add(hbUser);
		addVG();
		add(hbButtons);
		addVG();
	}

	public JButton[] getButtons() {
		JButton[] btns = { btnCancel, btnProceed };
		return btns;
	}
	
	public JTextField getText() {
		return txfUser;
	}
}
