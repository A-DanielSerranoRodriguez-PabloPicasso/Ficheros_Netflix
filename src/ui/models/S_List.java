package ui.models;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ShowsDAO;
import models.Show;
import models.User;
import utils.Almacen;
import utils.ShowFilter;

@SuppressWarnings("serial")
public class S_List extends AbstractJPanel {
	private User user;
	private ShowsDAO sDao;
	private String textFilter;
	private int page, totalPages, size, pos1, pos2, pos3, pos4, pos5;
	private ShowFilter filter = ShowFilter.Nada;

	private Box hbUpper, hbNav, hbOpt, hbFavs, hbMid;
	private Box vbUpper, vbMid;
	private Box hbShow1, hbShow2, hbShow3, hbShow4, hbShow5;
	private Box vbShow1, vbShow2, vbShow3, vbShow4, vbShow5;
	private Box hbS1T, hbS2T, hbS3T, hbS4T, hbS5T, hbS1D, hbS2D, hbS3D, hbS4D, hbS5D, hbS1M, hbS2M, hbS3M, hbS4M, hbS5M;
	private JLabel lblTitle, lblFilter;
	private JComboBox<ShowFilter> jcbFilter;
	private JTextField txtFilter;
	private JButton btnFilter, btnNextP, btnPreviousP, btnExportFavs, btnImportFavs;
	private JButton btnMore1, btnMore2, btnMore3, btnMore4, btnMore5;
	private JCheckBox chkFav1, chkFav2, chkFav3, chkFav4, chkFav5;

	public S_List(User user) {
		ImageIcon plus = new ImageIcon("resources/img/plus.png");

		sDao = new ShowsDAO();
		sDao.fillShows(filter, null, 0);
		this.user = user;
		this.page = 0;
		this.size = Almacen.shows.size();
		this.totalPages = (int) (Math.ceil((double) size / (double) (8))) + 1;

		/*
		 * Horizontal Boxes are initialized
		 */
		hbUpper = defaultHB();
		hbMid = defaultHB();

		hbNav = defaultHB();
		hbOpt = defaultHB();
		hbFavs = defaultHB();

		hbShow1 = defaultHB();
		hbShow2 = defaultHB();
		hbShow3 = defaultHB();
		hbShow4 = defaultHB();
		hbShow5 = defaultHB();

		hbS1T = defaultHB();
		hbS2T = defaultHB();
		hbS3T = defaultHB();
		hbS4T = defaultHB();
		hbS5T = defaultHB();

		hbS1D = defaultHB();
		hbS2D = defaultHB();
		hbS3D = defaultHB();
		hbS4D = defaultHB();
		hbS5D = defaultHB();

		hbS1M = defaultHB();
		hbS2M = defaultHB();
		hbS3M = defaultHB();
		hbS4M = defaultHB();
		hbS5M = defaultHB();

		/*
		 * Vertical Boxes are initialized
		 */
		vbUpper = defaultVB();
		vbMid = defaultVB();

		vbShow1 = defaultVB();
		vbShow2 = defaultVB();
		vbShow3 = defaultVB();
		vbShow4 = defaultVB();
		vbShow5 = defaultVB();

		/*
		 * JButtons are initialized
		 */
		btnPreviousP = new JButton("<");
		btnNextP = new JButton(">");
		btnFilter = new JButton(new ImageIcon("resources/img/filter.png"));
		btnExportFavs = new JButton("Exportar favoritos");
		btnImportFavs = new JButton("Importar favoritos");
		btnMore1 = new JButton(plus);
		btnMore2 = new JButton(plus);
		btnMore3 = new JButton(plus);
		btnMore4 = new JButton(plus);
		btnMore5 = new JButton(plus);

		btnFilter.setBorder(BorderFactory.createEmptyBorder());
		btnFilter.setContentAreaFilled(false);
		btnMore1.setBorder(BorderFactory.createEmptyBorder());
		btnMore1.setContentAreaFilled(false);
		btnMore2.setBorder(BorderFactory.createEmptyBorder());
		btnMore2.setContentAreaFilled(false);
		btnMore3.setBorder(BorderFactory.createEmptyBorder());
		btnMore3.setContentAreaFilled(false);
		btnMore4.setBorder(BorderFactory.createEmptyBorder());
		btnMore4.setContentAreaFilled(false);
		btnMore5.setBorder(BorderFactory.createEmptyBorder());
		btnMore5.setContentAreaFilled(false);

		/*
		 * JComboBoxes are initialized
		 */
		jcbFilter = new JComboBox<ShowFilter>(new DefaultComboBoxModel<ShowFilter>(ShowFilter.values()));

		/*
		 * JLabels are initialized
		 */
		lblTitle = new JLabel("Pagina " + (page + 1) + " de " + totalPages);
		lblFilter = new JLabel("Filtro:");

		/*
		 * JTextFields are initialized
		 */
		txtFilter = new JTextField(15);
		txtFilter.setMaximumSize(new Dimension(100, 25));
		txtFilter.setEnabled(false);

		/*
		 * JCheckBoxes are initialized
		 */
		chkFav1 = new JCheckBox();
		chkFav2 = new JCheckBox();
		chkFav3 = new JCheckBox();
		chkFav4 = new JCheckBox();
		chkFav5 = new JCheckBox();

		updateContents();

		/*
		 * Horizontal Boxes components are assigned
		 */
		// ## Added to a Vertical Box
		addToBox(hbNav, btnPreviousP);
		addToBox(hbNav, defaultHS(80));
		addToBox(hbNav, lblTitle);
		addToBox(hbNav, defaultHS(80));
		addToBox(hbNav, btnNextP);

		addToBox(hbOpt, lblFilter);
		addToBox(hbOpt, jcbFilter);
		addToBox(hbOpt, defaultHS(40));
		addToBox(hbOpt, txtFilter);
		addToBox(hbOpt, btnFilter);

		addToBox(hbFavs, btnExportFavs);
		addToBox(hbFavs, defaultHS(10));
		addToBox(hbFavs, btnImportFavs);
		// ##

		addToBox(hbS1M, new JLabel("Ver mas:"));
		addToBox(hbS2M, new JLabel("Ver mas:"));
		addToBox(hbS3M, new JLabel("Ver mas:"));
		addToBox(hbS4M, new JLabel("Ver mas:"));
		addToBox(hbS5M, new JLabel("Ver mas:"));

		addToBox(hbS1M, btnMore1);
		addToBox(hbS2M, btnMore2);
		addToBox(hbS3M, btnMore3);
		addToBox(hbS4M, btnMore4);
		addToBox(hbS5M, btnMore5);

		addToBox(hbS1M, defaultHS(20));
		addToBox(hbS2M, defaultHS(20));
		addToBox(hbS3M, defaultHS(20));
		addToBox(hbS4M, defaultHS(20));
		addToBox(hbS5M, defaultHS(20));

		addToBox(hbS1M, new JLabel("Favorito:"));
		addToBox(hbS2M, new JLabel("Favorito:"));
		addToBox(hbS3M, new JLabel("Favorito:"));
		addToBox(hbS4M, new JLabel("Favorito:"));
		addToBox(hbS5M, new JLabel("Favorito:"));

		addToBox(hbS1M, chkFav1);
		addToBox(hbS2M, chkFav2);
		addToBox(hbS3M, chkFav3);
		addToBox(hbS4M, chkFav4);
		addToBox(hbS5M, chkFav5);

		addToBox(hbShow1, vbShow1);
		addToBox(hbShow2, vbShow2);
		addToBox(hbShow3, vbShow3);
		addToBox(hbShow4, vbShow4);
		addToBox(hbShow5, vbShow5);

		addToBox(hbUpper, vbUpper);
		addToBox(hbMid, vbMid);

		/*
		 * Vertical Boxes components are assigned
		 */
		addToBox(vbUpper, hbNav);
		addToBox(vbUpper, defaultVS(20));
		addToBox(vbUpper, hbOpt);
		addToBox(vbUpper, defaultVS(20));
		addToBox(vbUpper, hbFavs);

		addToBox(vbShow1, hbS1T);
		addToBox(vbShow2, hbS2T);
		addToBox(vbShow3, hbS3T);
		addToBox(vbShow4, hbS4T);
		addToBox(vbShow5, hbS5T);
		
		addToBox(vbShow1, defaultVS(20));
		addToBox(vbShow2, defaultVS(20));
		addToBox(vbShow3, defaultVS(20));
		addToBox(vbShow4, defaultVS(20));
		addToBox(vbShow5, defaultVS(20));

		addToBox(vbShow1, hbS1D);
		addToBox(vbShow2, hbS2D);
		addToBox(vbShow3, hbS3D);
		addToBox(vbShow4, hbS4D);
		addToBox(vbShow5, hbS5D);

		addToBox(vbShow1, defaultVS(20));
		addToBox(vbShow2, defaultVS(20));
		addToBox(vbShow3, defaultVS(20));
		addToBox(vbShow4, defaultVS(20));
		addToBox(vbShow5, defaultVS(20));

		addToBox(vbShow1, hbS1M);
		addToBox(vbShow2, hbS2M);
		addToBox(vbShow3, hbS3M);
		addToBox(vbShow4, hbS4M);
		addToBox(vbShow5, hbS5M);

		addToBox(vbMid, hbShow1);
		addToBox(vbMid, Box.createVerticalGlue());
		addToBox(vbMid, hbShow2);
		addToBox(vbMid, Box.createVerticalGlue());
		addToBox(vbMid, hbShow3);
		addToBox(vbMid, Box.createVerticalGlue());
		addToBox(vbMid, hbShow4);
		addToBox(vbMid, Box.createVerticalGlue());
		addToBox(vbMid, hbShow5);

		/*
		 * JFrame components are assigned
		 */
		add(Box.createVerticalStrut(40));
		add(hbUpper);
		addVG();
		add(hbMid);
		addVG();
	}

	/**
	 * Retrieves the filter combo box
	 * 
	 * @return JComboBox
	 */
	public JComboBox<ShowFilter> getJcbFilter() {
		return jcbFilter;
	}

	/**
	 * Retrieves the filter text field
	 * 
	 * @return JTextField
	 */
	public JTextField getTxtFilter() {
		return txtFilter;
	}

	/**
	 * Retrieves the filter button
	 * 
	 * @return JButton
	 */
	public JButton getBtnFilter() {
		return btnFilter;
	}

	/**
	 * Retrieves the navigation buttons
	 * 
	 * @return JButton[] with previous and next buttons
	 */
	public JButton[] getBtnsNav() {
		JButton[] nav = { btnPreviousP, btnNextP };
		return nav;
	}

	/**
	 * Retrieves the favorites buttons
	 * 
	 * @return JButton[] with the export and import buttons
	 */
	public JButton[] getBtnsFavs() {
		JButton[] btns = { btnExportFavs, btnImportFavs };
		return btns;
	}

	/**
	 * Retrieves the information buttons
	 * 
	 * @return JButton with the more 1, 2 ,3, 4 and 5 buttons
	 */
	public JButton[] getBtnsShows() {
		JButton[] btns = { btnMore1, btnMore2, btnMore3, btnMore4, btnMore5 };
		return btns;
	}

	/**
	 * Retrieves the check box to make a show favorite
	 * 
	 * @return JCheckBox[] with the check 1, 2, 3, 4 and 5 boxes
	 */
	public JCheckBox[] getChksFav() {
		JCheckBox[] chks = { chkFav1, chkFav2, chkFav3, chkFav4, chkFav5 };
		return chks;
	}

	/**
	 * Moves to the next page
	 */
	public void nextPage() {
		if (page * 8 < size) {
			page++;
			updateContents();
		} else {
			int oldSize = size;
			sDao.fillShows(filter, textFilter, size);
			if (oldSize != Almacen.shows.size()) {
				page++;
				updateContents();
			}
		}
	}

	/**
	 * Moves to the previous page
	 */
	public void prevousPage() {
		if (page > 0) {
			page--;
			updateContents();
		}
	}

	/**
	 * Updates the filter applied and the shows
	 * 
	 * @param filter ShowFilter that represents what filter is used
	 * @param what   String that represents what is searched with that filter
	 */
	public void setFilter(ShowFilter filter, String what) {
		this.filter = filter;
		this.textFilter = what;
		Almacen.clearShows();
		sDao.fillShows(filter, what, 0);
		page = 0;
		updateContents();
	}

	/**
	 * Retrieves the numeric position of the show
	 * 
	 * @return Int
	 */
	public int getM1pos() {
		return (pos1);
	}

	/**
	 * Retrieves the numeric position of the show
	 * 
	 * @return Int
	 */
	public int getM2pos() {
		return (pos2);
	}

	/**
	 * Retrieves the numeric position of the show
	 * 
	 * @return Int
	 */
	public int getM3pos() {
		return (pos3);
	}

	/**
	 * Retrieves the numeric position of the show
	 * 
	 * @return Int
	 */
	public int getM4pos() {
		return (pos4);
	}

	/**
	 * Retrieves the numeric position of the show
	 * 
	 * @return Int
	 */
	public int getM5pos() {
		return (pos5);
	}

	/**
	 * Updates the contents of the view
	 */
	private void updateContents() {
		calcPosition();

		lblTitle.setText("Pagina " + (page + 1) + " de " + totalPages);
		hbS1T.removeAll();
		hbS1D.removeAll();
		hbS2T.removeAll();
		hbS2D.removeAll();
		hbS3T.removeAll();
		hbS3D.removeAll();
		hbS4T.removeAll();
		hbS4D.removeAll();
		hbS5T.removeAll();
		hbS5D.removeAll();

		if (size == 0) {
			addToBox(hbS1T, new JLabel("No hay coincidencias"));
			hbS1M.setVisible(false);
			hbS2M.setVisible(false);
			hbS3M.setVisible(false);
			hbS4M.setVisible(false);
			hbS5M.setVisible(false);
		} else if (pos5 < size) {
			addToBox(hbS5T, new JLabel(getShow(pos5).getTitle()));
			addToBox(hbS5D, new JLabel("Pais: " + getShow(pos5).getCountry()));
			addToBox(hbS5D, new JLabel("Director: " + getShow(pos5).getDirector()));
			addToBox(hbS5D, new JLabel("Salida: " + getShow(pos5).getRelease_year()));
			addToBox(hbS4T, new JLabel(getShow(pos4).getTitle()));
			addToBox(hbS4D, new JLabel("Pais: " + getShow(pos4).getCountry()));
			addToBox(hbS4D, new JLabel("Director: " + getShow(pos4).getDirector()));
			addToBox(hbS4D, new JLabel("Salida: " + getShow(pos4).getRelease_year()));
			addToBox(hbS3T, new JLabel(getShow(pos3).getTitle()));
			addToBox(hbS3D, new JLabel("Pais: " + getShow(pos3).getCountry()));
			addToBox(hbS3D, new JLabel("Director: " + getShow(pos3).getDirector()));
			addToBox(hbS3D, new JLabel("Salida: " + getShow(pos3).getRelease_year()));
			addToBox(hbS2T, new JLabel(getShow(pos2).getTitle()));
			addToBox(hbS2D, new JLabel("Pais: " + getShow(pos2).getCountry()));
			addToBox(hbS2D, new JLabel("Director: " + getShow(pos2).getDirector()));
			addToBox(hbS2D, new JLabel("Salida: " + getShow(pos2).getRelease_year()));
			addToBox(hbS1T, new JLabel(getShow(pos1).getTitle()));
			addToBox(hbS1D, new JLabel("Pais: " + getShow(pos1).getCountry()));
			addToBox(hbS1D, new JLabel("Director: " + getShow(pos1).getDirector()));
			addToBox(hbS1D, new JLabel("Salida: " + getShow(pos1).getRelease_year()));
			hbS1M.setVisible(true);
			hbS2M.setVisible(true);
			hbS3M.setVisible(true);
			hbS4M.setVisible(true);
			hbS5M.setVisible(true);
		} else if (pos4 < size) {
			addToBox(hbS4T, new JLabel(getShow(pos4).getTitle()));
			addToBox(hbS4D, new JLabel("Pais: " + getShow(pos4).getCountry()));
			addToBox(hbS4D, new JLabel("Director: " + getShow(pos4).getDirector()));
			addToBox(hbS4D, new JLabel("Salida: " + getShow(pos4).getRelease_year()));
			addToBox(hbS3T, new JLabel(getShow(pos3).getTitle()));
			addToBox(hbS3D, new JLabel("Pais: " + getShow(pos3).getCountry()));
			addToBox(hbS3D, new JLabel("Director: " + getShow(pos3).getDirector()));
			addToBox(hbS3D, new JLabel("Salida: " + getShow(pos3).getRelease_year()));
			addToBox(hbS2T, new JLabel(getShow(pos2).getTitle()));
			addToBox(hbS2D, new JLabel("Pais: " + getShow(pos2).getCountry()));
			addToBox(hbS2D, new JLabel("Director: " + getShow(pos2).getDirector()));
			addToBox(hbS2D, new JLabel("Salida: " + getShow(pos2).getRelease_year()));
			addToBox(hbS1T, new JLabel(getShow(pos1).getTitle()));
			addToBox(hbS1D, new JLabel("Pais: " + getShow(pos1).getCountry()));
			addToBox(hbS1D, new JLabel("Director: " + getShow(pos1).getDirector()));
			addToBox(hbS1D, new JLabel("Salida: " + getShow(pos1).getRelease_year()));
			hbS1M.setVisible(true);
			hbS2M.setVisible(true);
			hbS3M.setVisible(true);
			hbS4M.setVisible(true);
			hbS5M.setVisible(false);
		} else if (pos3 < size) {
			addToBox(hbS3T, new JLabel(getShow(pos3).getTitle()));
			addToBox(hbS3D, new JLabel("Pais: " + getShow(pos3).getCountry()));
			addToBox(hbS3D, new JLabel("Director: " + getShow(pos3).getDirector()));
			addToBox(hbS3D, new JLabel("Salida: " + getShow(pos3).getRelease_year()));
			addToBox(hbS2T, new JLabel(getShow(pos2).getTitle()));
			addToBox(hbS2D, new JLabel("Pais: " + getShow(pos2).getCountry()));
			addToBox(hbS2D, new JLabel("Director: " + getShow(pos2).getDirector()));
			addToBox(hbS2D, new JLabel("Salida: " + getShow(pos2).getRelease_year()));
			addToBox(hbS1T, new JLabel(getShow(pos1).getTitle()));
			addToBox(hbS1D, new JLabel("Pais: " + getShow(pos1).getCountry()));
			addToBox(hbS1D, new JLabel("Director: " + getShow(pos1).getDirector()));
			addToBox(hbS1D, new JLabel("Salida: " + getShow(pos1).getRelease_year()));
			hbS1M.setVisible(true);
			hbS2M.setVisible(true);
			hbS3M.setVisible(true);
			hbS4M.setVisible(false);
			hbS5M.setVisible(false);
		} else if (pos2 < size) {
			addToBox(hbS2T, new JLabel(getShow(pos2).getTitle()));
			addToBox(hbS2D, new JLabel("Pais: " + getShow(pos2).getCountry()));
			addToBox(hbS2D, new JLabel("Director: " + getShow(pos2).getDirector()));
			addToBox(hbS2D, new JLabel("Salida: " + getShow(pos2).getRelease_year()));
			addToBox(hbS1T, new JLabel(getShow(pos1).getTitle()));
			addToBox(hbS1D, new JLabel("Pais: " + getShow(pos1).getCountry()));
			addToBox(hbS1D, new JLabel("Director: " + getShow(pos1).getDirector()));
			addToBox(hbS1D, new JLabel("Salida: " + getShow(pos1).getRelease_year()));
			hbS1M.setVisible(true);
			hbS2M.setVisible(true);
			hbS3M.setVisible(false);
			hbS4M.setVisible(false);
			hbS5M.setVisible(false);
		} else if (pos1 < size) {
			addToBox(hbS1T, new JLabel(getShow(pos1).getTitle()));
			addToBox(hbS1D, new JLabel("Pais: " + getShow(pos1).getCountry()));
			addToBox(hbS1D, new JLabel("Director: " + getShow(pos1).getDirector()));
			addToBox(hbS1D, new JLabel("Salida: " + getShow(pos1).getRelease_year()));
			hbS1M.setVisible(true);
			hbS2M.setVisible(false);
			hbS3M.setVisible(false);
			hbS4M.setVisible(false);
			hbS5M.setVisible(false);
		}

		repaint();
	}

	/**
	 * Calculates the numbers for all of the new components
	 */
	private void calcPosition() {
		pos1 = 0 + (page * 5);
		pos2 = 1 + (page * 5);
		pos3 = 2 + (page * 5);
		pos4 = 3 + (page * 5);
		pos5 = 4 + (page * 5);
		size = Almacen.shows.size();
		totalPages = (int) (Math.ceil((double) size / (double) (8))) + 1;

		chkFav1.setSelected(false);
		chkFav2.setSelected(false);
		chkFav3.setSelected(false);
		chkFav4.setSelected(false);
		chkFav5.setSelected(false);

		repaintFavs();
	}

	/**
	 * Makes sure the favorites shows are checked
	 */
	public void repaintFavs() {
		for (Show userShow : user.getFavorites()) {
			for (int i = page * 5; i < (page * 5 + 5); i++) {
				if (Almacen.shows.get(i).getShow_id().equals(userShow.getShow_id())) {
					switch (i % 5) {
					case 0:
						chkFav1.setSelected(true);
						break;

					case 1:
						chkFav2.setSelected(true);
						break;

					case 2:
						chkFav3.setSelected(true);
						break;

					case 3:
						chkFav4.setSelected(true);
						break;

					case 4:
						chkFav5.setSelected(true);
						break;
					}
				}
			}
		}
	}

	/**
	 * Retrieves the show from "Almacen.shows"
	 * 
	 * @param i (Int) Position of the show to retrieve
	 * @return Show
	 */
	private Show getShow(int i) {
		return Almacen.shows.get(i);
	}
}
