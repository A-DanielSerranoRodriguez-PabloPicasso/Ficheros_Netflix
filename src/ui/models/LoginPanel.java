package ui.models;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginPanel extends AbstractJPanel {
	private Box hbTitle, hbName, hbPasswd, hbError, hbButtons;
	private Box vbData;
	private JLabel lblTitle, lblName, lblPasswd, lblError, lblEmpty;
	private JTextField txtName;
	private JPasswordField pwdPasswd;
	private JButton btnRegister, btnLogin;

	public LoginPanel() {
		super();

		hbTitle = defaultHB();
		hbName = defaultHB();
		hbPasswd = defaultHB();
		hbButtons = defaultHB();
		hbError = defaultHB();

		vbData = defaultVB();

		lblTitle = new JLabel("Nutflix");
		lblName = new JLabel("Nombre: ");
		lblPasswd = new JLabel("Contra: ");
		lblError = new JLabel("Usuario o contra incorrectos");
		lblEmpty = new JLabel("Campos vacios");

		btnRegister = new JButton("Registrarse");
		btnLogin = new JButton("Iniciar sesion");

		txtName = new JTextField(15);
		txtName.setMaximumSize(new Dimension(200, 25));

		pwdPasswd = new JPasswordField(15);
		pwdPasswd.setMaximumSize(new Dimension(200, 25));

		lblEmpty.setVisible(false);
		lblError.setVisible(false);

		addCorrectly(hbTitle, lblTitle);

		addCorrectly(hbName, lblName);
		addCorrectly(hbName, Box.createHorizontalStrut(20));
		addCorrectly(hbName, txtName);

		addCorrectly(hbPasswd, lblPasswd);
		addCorrectly(hbPasswd, Box.createHorizontalStrut(20));
		addCorrectly(hbPasswd, pwdPasswd);

		addCorrectly(hbError, lblError);
		addCorrectly(hbError, lblEmpty);

		addCorrectly(hbButtons, btnRegister);
		addCorrectly(hbButtons, Box.createHorizontalStrut(20));
		addCorrectly(hbButtons, btnLogin);

		addCorrectly(vbData, hbName);
		addCorrectly(vbData, Box.createVerticalStrut(20));
		addCorrectly(vbData, hbPasswd);
		addCorrectly(vbData, Box.createVerticalStrut(40));
		addCorrectly(vbData, hbError);
		addCorrectly(vbData, Box.createVerticalStrut(20));
		addCorrectly(vbData, hbButtons);

		addVG();
		add(hbTitle);
		addVG();
		add(vbData);
		addVG();
	}

	public JButton getLoginBtn() {
		return btnLogin;
	}

	public JButton getRegisterBtn() {
		return btnRegister;
	}

	public JTextField getUsername() {
		return txtName;
	}

	public JPasswordField getPasswd() {
		return pwdPasswd;
	}

	public JLabel[] getErrorLbls() {
		JLabel[] errs = { lblEmpty, lblError };
		return errs;
	}
}