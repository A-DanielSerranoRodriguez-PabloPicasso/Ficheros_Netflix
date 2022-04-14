package ui.models;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import models.Show;
import models.User;
import utils.Almacen;

@SuppressWarnings("serial")
public class ShowInfoPanel extends AbstractJPanel {
	private int position;
	private User usuario;

	private Box hbTitle, hbDir, hbRatDur, hbCountFech, hbListed, hbCast, hbDesc, hbFav, hbExit;
	private JLabel lblTitle, lblDir, lblRating, lblDur, lblCount, lblFech, lblListed, lblCast, lblDesc, lblFav;
	private JCheckBox chkFav;
	private JButton btnExit;

	public ShowInfoPanel(User usuario) {
		this.usuario = usuario;

		/*
		 * Horizontal Boxes are initialized
		 */
		hbTitle = defaultHB();
		hbDir = defaultHB();
		hbRatDur = defaultHB();
		hbCountFech = defaultHB();
		hbListed = defaultHB();
		hbCast = defaultHB();
		hbDesc = defaultHB();
		hbFav = defaultHB();
		hbExit = defaultHB();

		/*
		 * JLabels are initialized
		 */
		lblTitle = new JLabel();
		lblDir = new JLabel();
		lblRating = new JLabel();
		lblDur = new JLabel();
		lblCount = new JLabel();
		lblFech = new JLabel();
		lblListed = new JLabel();
		lblCast = new JLabel();
		lblDesc = new JLabel();
		lblFav = new JLabel("Favorito");

		/*
		 * JButtons are initialized
		 */
		btnExit = new JButton("Salir");

		/*
		 * JCheckBoxes are initialized
		 */
		chkFav = new JCheckBox();

		/*
		 * Horizontal Boxes components are assigned
		 */
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

		addToBox(hbFav, lblFav);
		addToBox(hbFav, chkFav);

		addToBox(hbExit, btnExit);

		/*
		 * JFrame components are assigned
		 */
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
		add(hbFav);
		addVG();
		add(hbExit);
	}

	/**
	 * Retrieves the exit button of the panel
	 * 
	 * @return JButton
	 */
	public JButton getBtnExit() {
		return btnExit;
	}

	/**
	 * Retrieves the favorites checkbox of the panel
	 * 
	 * @return JCheckBox
	 */
	public JCheckBox getChkFav() {
		return chkFav;
	}

	/**
	 * Retrieves the position of the show in "Almacen.shows"
	 * 
	 * @return Int
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Updates the show information of the panel
	 * 
	 * @param i (Int) Position of the show in "Almacen.shows"
	 */
	public void updateInfo(int i) {
		position = i;
		Show s = Almacen.shows.get(position);

		lblTitle.setText(s.getTitle());
		lblDir.setText(s.getDirector());
		lblRating.setText(s.getRating());
		lblDur.setText(s.getDuration());
		lblCount.setText(s.getCountry());
		lblFech.setText(s.getRelease_year());
		lblListed.setText(s.getListed_in());
		lblCast.setText(s.getCast());
		lblDesc.setText(s.getDescription());

		boolean found = false;
		for (Show show : usuario.getFavorites())
			if (!found)
				if (show.getShow_id().equals(s.getShow_id())) {
					chkFav.setSelected(true);
					found = true;
				} else
					chkFav.setSelected(false);
	}
}
