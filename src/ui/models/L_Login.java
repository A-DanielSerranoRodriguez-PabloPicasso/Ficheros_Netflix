package ui.models;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class L_Login extends AbstractJPanel {
	private Box hbName, hbPasswd, hbError, hbButtons;
	private Box vbData;
	private JLabel lblName, lblPasswd, lblError, lblEmpty;
	private JTextField txtName;
	private JPasswordField pwdPasswd;
	private JButton btnRegister, btnLogin, btnVerify, btnForgotPasswd;

	public L_Login() {
		/**
		 * Horizontal Boxes are initialized
		 */
		hbName = defaultHB();
		hbPasswd = defaultHB();
		hbButtons = defaultHB();
		hbError = defaultHB();

		/**
		 * Vertical Boxes are initialized
		 */
		vbData = defaultVB();

		/**
		 * JLabels are initialized
		 */
		lblName = new JLabel("Nombre: ");
		lblPasswd = new JLabel("Contra: ");
		lblError = new JLabel("Usuario o contra incorrectos");
		lblEmpty = new JLabel("Campos vacios");

		/**
		 * JButtons are initialized
		 */
		btnRegister = new JButton("Registrarse");
		btnLogin = new JButton("Iniciar sesion");
		btnVerify = new JButton("Verificar cuenta");
		btnVerify.setVisible(false);
		btnForgotPasswd = new JButton("I forgor");

		/**
		 * JTextFields are initialized
		 */
		txtName = new JTextField(15);
		txtName.setMaximumSize(new Dimension(200, 25));

		/**
		 * JPasswordFields are initialized
		 */
		pwdPasswd = new JPasswordField(15);
		pwdPasswd.setMaximumSize(new Dimension(200, 25));

		/**
		 * JLables are initialized
		 */
		lblEmpty.setVisible(false);
		lblError.setVisible(false);

		/**
		 * The components of the Horizontal Boxes are assigned
		 */
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

		/**
		 * The components of the Vertical Box are assigned
		 */
		addToBox(vbData, hbName);
		addToBox(vbData, Box.createVerticalStrut(20));
		addToBox(vbData, hbPasswd);
		addToBox(vbData, Box.createVerticalStrut(40));
		addToBox(vbData, hbError);
		addToBox(vbData, Box.createVerticalStrut(20));
		addToBox(vbData, hbButtons);

		/**
		 * The components of the JFrame are assigned
		 */
		addVG();
		add(vbData);
		add(btnForgotPasswd);
		addVG();
	}

	/**
	 * Retrieves the buttons of the panel
	 * 
	 * @return JButton[] with the login, register, verify and forgot password
	 *         buttons
	 */
	public JButton[] getButtons() {
		JButton[] btns = { btnLogin, btnRegister, btnVerify, btnForgotPasswd };
		return btns;
	}

	/**
	 * Retrieves the text field of the panel
	 * 
	 * @return JTextField
	 */
	public JTextField getTxtField() {
		return txtName;
	}

	/**
	 * Retrieves the password field of the panel
	 * 
	 * @return JPasswordField
	 */
	public JPasswordField getPasswd() {
		return pwdPasswd;
	}

	/**
	 * Retrieves the error labels of the panel
	 * 
	 * @return JLabel[] with the empty and common error labels
	 */
	public JLabel[] getErrorLbls() {
		JLabel[] errs = { lblEmpty, lblError };
		return errs;
	}
}