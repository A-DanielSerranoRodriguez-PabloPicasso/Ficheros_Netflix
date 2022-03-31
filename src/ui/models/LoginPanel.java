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
	private JButton btnRegister, btnLogin, btnVerify, btnForgotPasswd;

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
		btnVerify = new JButton("Verificar cuenta");
		btnVerify.setVisible(false);
		btnForgotPasswd = new JButton("I forgor");

		txtName = new JTextField(15);
		txtName.setMaximumSize(new Dimension(200, 25));

		pwdPasswd = new JPasswordField(15);
		pwdPasswd.setMaximumSize(new Dimension(200, 25));

		lblEmpty.setVisible(false);
		lblError.setVisible(false);

		addToBox(hbTitle, lblTitle);

		addToBox(hbName, lblName);
		addToBox(hbName, Box.createHorizontalStrut(20));
		addToBox(hbName, txtName);

		addToBox(hbPasswd, lblPasswd);
		addToBox(hbPasswd, Box.createHorizontalStrut(20));
		addToBox(hbPasswd, pwdPasswd);

		addToBox(hbError, lblError);
		addToBox(hbError, lblEmpty);

		addToBox(hbButtons, btnRegister);
		addToBox(hbButtons, Box.createHorizontalStrut(20));
		addToBox(hbButtons, btnLogin);
		addToBox(hbButtons, btnVerify);

		addToBox(vbData, hbName);
		addToBox(vbData, Box.createVerticalStrut(20));
		addToBox(vbData, hbPasswd);
		addToBox(vbData, Box.createVerticalStrut(40));
		addToBox(vbData, hbError);
		addToBox(vbData, Box.createVerticalStrut(20));
		addToBox(vbData, hbButtons);

		addVG();
		add(hbTitle);
		addVG();
		add(vbData);
		add(btnForgotPasswd);
		addVG();
	}

	public JButton getLoginBtn() {
		return btnLogin;
	}

	public JButton getRegisterBtn() {
		return btnRegister;
	}

	public JButton getVerifyBtn() {
		return btnVerify;
	}
	
	public JButton getForgotPasswd() {
		return btnForgotPasswd;
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