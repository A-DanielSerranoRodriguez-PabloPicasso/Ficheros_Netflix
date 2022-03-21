package ui.models;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RegisterPanel extends AbstractJPanel {
	private Box hbTitle, hbName, hbMail, hbPasswd, hbConfirmPwd, hbError, hbButtons;
	private Box vbData;
	private JLabel lblTitle, lblName, lblMail, lblPasswd, lblConfirmPwd, lblEmpty, lblUserExists, lblPasswdNotMatch;
	private JTextField txtName, txtMail;
	private JPasswordField pwdPasswd, pwdConfirmPwd;
	private JButton btnCancel, btnRegister;

	public RegisterPanel() {
		super();

		hbTitle = defaultHB();
		hbName = defaultHB();
		hbMail = defaultHB();
		hbPasswd = defaultHB();
		hbConfirmPwd = defaultHB();
		hbButtons = defaultHB();
		hbError = defaultHB();

		vbData = defaultVB();

		lblTitle = new JLabel("Registrate en Nutflix");
		lblName = new JLabel("Nombre: ");
		lblMail = new JLabel("Correo electronico: ");
		lblPasswd = new JLabel("Contra: ");
		lblConfirmPwd = new JLabel("Repite contra: ");
		lblEmpty = new JLabel("Campos vacios");
		lblUserExists = new JLabel("El usuario o correo ya existen");
		lblPasswdNotMatch = new JLabel("Las contras no coinciden");

		txtName = new JTextField(15);
		txtName.setMaximumSize(new Dimension(200, 25));

		txtMail = new JTextField(15);
		txtMail.setMaximumSize(new Dimension(200, 25));

		pwdPasswd = new JPasswordField(15);
		pwdPasswd.setMaximumSize(new Dimension(200, 25));

		pwdConfirmPwd = new JPasswordField(15);
		pwdConfirmPwd.setMaximumSize(new Dimension(200, 25));

		btnCancel = new JButton("Cancelar");
		btnRegister = new JButton("Registrarse");

		lblEmpty.setVisible(false);
		lblUserExists.setVisible(false);
		lblPasswdNotMatch.setVisible(false);

		addCorrectly(hbTitle, lblTitle);

		addCorrectly(hbName, lblName);
		addCorrectly(hbName, Box.createHorizontalStrut(87));
		addCorrectly(hbName, txtName);

		addCorrectly(hbMail, lblMail);
		addCorrectly(hbMail, Box.createHorizontalStrut(10));
		addCorrectly(hbMail, txtMail);

		addCorrectly(hbPasswd, lblPasswd);
		addCorrectly(hbPasswd, Box.createHorizontalStrut(93));
		addCorrectly(hbPasswd, pwdPasswd);

		addCorrectly(hbConfirmPwd, lblConfirmPwd);
		addCorrectly(hbConfirmPwd, Box.createHorizontalStrut(43));
		addCorrectly(hbConfirmPwd, pwdConfirmPwd);

		addCorrectly(hbError, lblEmpty);
		addCorrectly(hbError, lblUserExists);
		addCorrectly(hbError, lblPasswdNotMatch);

		addCorrectly(hbButtons, btnCancel);
		addCorrectly(hbButtons, Box.createHorizontalStrut(30));
		addCorrectly(hbButtons, btnRegister);

		addCorrectly(vbData, hbName);
		addCorrectly(vbData, Box.createVerticalStrut(20));
		addCorrectly(vbData, hbMail);
		addCorrectly(vbData, Box.createVerticalStrut(20));
		addCorrectly(vbData, hbPasswd);
		addCorrectly(vbData, Box.createVerticalStrut(20));
		addCorrectly(vbData, hbConfirmPwd);
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

	public JButton getCancelBtn() {
		return btnCancel;
	}

	public JButton getRegisterBtn() {
		return btnRegister;
	}

	public JTextField[] getTexts() {
		JTextField[] txts = { txtName, txtMail };
		return txts;
	}

	public JPasswordField[] getPasswds() {
		JPasswordField[] passwds = { pwdPasswd, pwdConfirmPwd };
		return passwds;
	}

	public JLabel[] getErrorLbls() {
		JLabel[] errs = { lblEmpty, lblUserExists, lblPasswdNotMatch };
		return errs;
	}
}
