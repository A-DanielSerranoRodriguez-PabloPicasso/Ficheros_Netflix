package ui.models;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

import models.Show;
import utils.Almacen;

@SuppressWarnings("serial")
public class ShowInfoPanel extends AbstractJPanel {
	private Box hbTitle, hbDir, hbRatDur, hbCountFech, hbListed, hbCast, hbDesc, hbExit;
	private JLabel lblTitle, lblDir, lblRating, lblDur, lblCount, lblFech, lblListed, lblCast, lblDesc;
	private JButton btnExit;

	@SuppressWarnings("static-access")
	public ShowInfoPanel() {
		hbTitle = defaultHB();
		hbDir = defaultHB();
		hbRatDur = defaultHB();
		hbCountFech = defaultHB();
		hbListed = defaultHB();
		hbCast = defaultHB();
		hbDesc = defaultHB();
		hbExit = defaultHB();

		lblTitle = new JLabel();
		lblDir = new JLabel();
		lblRating = new JLabel();
		lblDur = new JLabel();
		lblCount = new JLabel();
		lblFech = new JLabel();
		lblListed = new JLabel();
		lblCast = new JLabel();
		lblDesc = new JLabel();

		btnExit = new JButton("Salir");

		addToBox(hbTitle, lblTitle);
		addToBox(hbDir, lblDir);
		addToBox(hbRatDur, lblRating);
		addToBox(hbRatDur, defaultHS(40));
		addToBox(hbRatDur, lblDur);
		addToBox(hbCountFech, lblCount);
		addToBox(hbCountFech, defaultHS(40));
		addToBox(hbCountFech, lblFech);
		addToBox(hbListed, lblListed);
		addToBox(hbCast, lblCast);
		addToBox(hbDesc, lblDesc);
		addToBox(hbExit, btnExit);

		addVG();
		add(hbTitle);
		addVS(40);
		add(hbDir);
		addVS(40);
		add(hbRatDur);
		addVS(40);
		add(hbCountFech);
		addVS(40);
		add(hbListed);
		addVS(40);
		add(hbCast);
		addVS(40);
		add(hbDesc);
		addVG();
		add(hbExit);
	}

	public void updateInfo(int i) {
		Show s = Almacen.shows.get(i);
		lblTitle.setText(s.getTitle());
		lblDir.setText(s.getDirector());
		lblRating.setText(s.getRating());
		lblDur.setText(s.getDuration());
		lblCount.setText(s.getCountry());
		lblFech.setText(s.getRelease_year());
		lblListed.setText(s.getListed_in());
		lblCast.setText(s.getCast());
		lblDesc.setText(s.getDescription());
	}

	public JButton getBtnExit() {
		return btnExit;
	}
}
