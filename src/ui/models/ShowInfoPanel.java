package ui.models;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ShowInfoPanel extends AbstractJPanel {
	private Box hbText;
	private JLabel lblText;
	private JButton btnExit;

	public ShowInfoPanel() {
		hbText = defaultHB();
		lblText = new JLabel();
		btnExit = new JButton("Salir");
		addToBox(hbText, lblText);
		add(hbText);
		add(btnExit);
	}

	public void updateInfo(int i) {
		lblText.setText(i + "");
	}
	
	public JButton getBtnExit() {
		return btnExit;
	}
}
