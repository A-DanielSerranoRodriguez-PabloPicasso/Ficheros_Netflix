package ui.models;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ConfirmPanel1 extends AbstractJPanel {
	private JLabel lblTitle, lblText1, lblText2;
	private JButton btnVerify, btnResend;
	private Box hbTitle, hbTexts, hbText1, hbText2, hbButtons;
	private Box vbTexts;

	public ConfirmPanel1(String mail) {
		super();

		hbTitle = defaultHB();
		hbButtons = defaultHB();
		hbText1 = defaultHB();
		hbText2 = defaultHB();
		hbTexts = defaultHB();

		vbTexts = defaultVB();

		lblTitle = new JLabel("Confirma tu registro");
		lblText1 = new JLabel("Hemos enviado un correo a " + mail);
		lblText2 = new JLabel("Si le ha llegado, continue la verificacion.");

		btnVerify = new JButton("Verificar");
		btnResend = new JButton("Re-enviar codigo");

		addCorrectly(hbTitle, lblTitle);
		addCorrectly(hbText1, lblText1);
		addCorrectly(hbText2, lblText2);
		addCorrectly(hbButtons, btnResend);
		addCorrectly(hbButtons, Box.createHorizontalStrut(10));
		addCorrectly(hbButtons, btnVerify);

		addCorrectly(vbTexts, hbText1);
		addCorrectly(vbTexts, Box.createVerticalStrut(15));
		addCorrectly(vbTexts, hbText2);

		addCorrectly(hbTexts, vbTexts);

		addVG();
		add(hbTitle);
		addVG();
		add(hbTexts);
		add(Box.createVerticalStrut(40));
		add(hbButtons);
		addVG();
	}

	public JButton[] getButtons() {
		JButton[] btns = { btnResend, btnVerify };
		return btns;
	}
}
