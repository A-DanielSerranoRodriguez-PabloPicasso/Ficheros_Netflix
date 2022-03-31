package ui.models;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class FpPasswdPanel extends AbstractJPanel {
	private Box hbTitle, hbSubTitle, hbPasswd, hbPasswdConfirm, hbButtons;
	private JLabel lblTitle, lblSubTitle, lblPasswd, lblPasswdConfirm;
	private JPasswordField pswPasswd, pswPasswdConfirm;
	private JButton btnBack, btnConfirm, btnConfValid;

	public FpPasswdPanel() {
		hbTitle = defaultHB();
		hbSubTitle = defaultHB();
		hbPasswd = defaultHB();
		hbPasswdConfirm = defaultHB();
		hbButtons = defaultHB();

		lblTitle = new JLabel("Introduce tu nueva contra.");
		lblSubTitle = new JLabel("Tendras que volver a activar la cuenta una vez cambiada.");
		lblPasswd = new JLabel("Contra:");
		lblPasswdConfirm = new JLabel("Confirma contra:");

		pswPasswd = new JPasswordField(15);
		pswPasswd.setMaximumSize(new Dimension(100, 25));
		pswPasswdConfirm = new JPasswordField(15);
		pswPasswdConfirm.setMaximumSize(new Dimension(100, 25));

		btnBack = new JButton("Volver");
		btnConfirm = new JButton("Confirmar");
		btnConfValid = new JButton("Confirmar y validar");

		addToBox(hbTitle, lblTitle);
		addToBox(hbSubTitle, lblSubTitle);

		addToBox(hbPasswd, lblPasswd);
		addToBox(hbPasswd, Box.createHorizontalStrut(60));
		addToBox(hbPasswd, pswPasswd);

		addToBox(hbPasswdConfirm, lblPasswdConfirm);
		addToBox(hbPasswdConfirm, Box.createHorizontalStrut(10));
		addToBox(hbPasswdConfirm, pswPasswdConfirm);

		addToBox(hbButtons, btnBack);
		addToBox(hbButtons, Box.createHorizontalStrut(15));
		addToBox(hbButtons, btnConfirm);
		addToBox(hbButtons, Box.createHorizontalStrut(15));
		addToBox(hbButtons, btnConfValid);

		addVG();
		add(hbTitle);
		add(Box.createVerticalStrut(5));
		add(hbSubTitle);
		addVG();
		add(hbPasswd);
		addVG();
		add(hbPasswdConfirm);
		addVG();
		add(hbButtons);
		addVG();
	}

	public JPasswordField[] getPasswds() {
		JPasswordField[] passwds = { pswPasswd, pswPasswdConfirm };
		return passwds;
	}

	public JButton[] getButtons() {
		JButton[] btns = { btnBack, btnConfirm, btnConfValid };
		return btns;
	}

	public boolean passwdMatch() {
		return new String(pswPasswd.getPassword()).equals(new String(pswPasswdConfirm.getPassword()));
	}

	public char[] getPasswd() {
		return pswPasswd.getPassword();
	}
}
