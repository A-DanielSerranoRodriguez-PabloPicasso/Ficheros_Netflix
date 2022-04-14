package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.UserDAO;
import models.Show;
import models.User;
import ui.models.ShowInfoPanel;
import ui.models.ShowListPanel;
import utils.Almacen;
import utils.ShowFilter;

public class ShowList {
	private User usuario;
	private File favs;
	private FileWriter favsWriter;
	private Scanner scWriter;

	private JFrame frame;
	private ShowListPanel showPanel;
	private ShowInfoPanel showInfo;

	/**
	 * Create the application.
	 */
	public ShowList(User usuario) {
		this.usuario = usuario;
		favs = new File("exports/" + this.usuario.getName() + ".csv");
		try {
			favsWriter = new FileWriter(favs, true);
			scWriter = new Scanner(favs);
		} catch (IOException e) {
			e.printStackTrace();
		}

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(1200, 800));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setVisible(true);

		setUIcomponents();
		setUIbehaviour();
	}

	/**
	 * Sets the panels of the frame
	 */
	private void setUIcomponents() {
		this.showPanel = new ShowListPanel(usuario);
		this.showInfo = new ShowInfoPanel(usuario);
		frame.getContentPane().add(showPanel, "name_14215655555075");
		frame.getContentPane().add(showInfo);
	}

	/**
	 * Sets the behaviour of the panels components
	 */
	private void setUIbehaviour() {
		JButton[] btnsNav = showPanel.getBtnsNav(), btnsMore = showPanel.getBtnsShows();
		JButton btnFilter = showPanel.getBtnFilter();
		JCheckBox[] chksFav = showPanel.getChksFav();

		if (usuario.getSeparator() == null) {
			showPanel.getBtnsFavs()[1].setEnabled(false);
		}

		/*
		 * Listener that activates the filter search input if the filter selected is
		 * different from ShowFilter.Nada
		 */
		showPanel.getJcbFilter().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (showPanel.getJcbFilter().getSelectedItem() == ShowFilter.Nada)
					showPanel.getTxtFilter().setEnabled(false);
				else
					showPanel.getTxtFilter().setEnabled(true);
			}
		});

		/*
		 * Moves to the previous page
		 */
		btnsNav[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.prevousPage();
				frame.repaint();
			}
		});

		/**
		 * Moves to the next page
		 */
		btnsNav[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.nextPage();
				frame.repaint();
			}
		});

		/*
		 * Gets the shows searched for, with or without filter
		 */
		btnFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.setFilter((ShowFilter) showPanel.getJcbFilter().getSelectedItem(),
						showPanel.getTxtFilter().getText());
				frame.repaint();
			}
		});

		showInfo.getChkFav().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (showInfo.getChkFav().isSelected())
					saveFav(showInfo.getPosition());
				else
					removeFav(showInfo.getPosition());
			}
		});

		/*
		 * Makes the shows list visible, hiding the show details
		 */
		showInfo.getBtnExit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int position = showInfo.getPosition();
				JCheckBox chkFav = showInfo.getChkFav();

				if (chkFav.isSelected()) {
					if (position == showPanel.getM1pos())
						chksFav[0].setSelected(true);
					else if (position == showPanel.getM2pos())
						chksFav[1].setSelected(true);
					else if (position == showPanel.getM3pos())
						chksFav[2].setSelected(true);
					else if (position == showPanel.getM4pos())
						chksFav[3].setSelected(true);
					else if (position == showPanel.getM5pos())
						chksFav[4].setSelected(true);
				} else {
					if (position == showPanel.getM1pos())
						chksFav[0].setSelected(false);
					else if (position == showPanel.getM2pos())
						chksFav[1].setSelected(false);
					else if (position == showPanel.getM3pos())
						chksFav[2].setSelected(false);
					else if (position == showPanel.getM4pos())
						chksFav[3].setSelected(false);
					else if (position == showPanel.getM5pos())
						chksFav[4].setSelected(false);
				}

				showPanel.setVisible(true);
				showInfo.setVisible(false);
			}
		});

		/*
		 * Makes the show description visible, hiding the shows list
		 */
		btnsMore[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM1pos());
			}
		});

		btnsMore[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM2pos());
			}
		});

		btnsMore[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM3pos());
			}
		});

		btnsMore[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM4pos());
			}
		});

		btnsMore[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM5pos());
			}
		});
		/*
		 * End
		 */

		/*
		 * Adds or removes the shows from the favorite list, depending on if it is
		 * selected or not
		 */
		chksFav[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[0].isSelected())
					saveFav(showPanel.getM1pos());
				else
					removeFav(showPanel.getM1pos());
			}
		});

		chksFav[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[1].isSelected())
					saveFav(showPanel.getM2pos());
				else
					removeFav(showPanel.getM2pos());
			}
		});

		chksFav[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[2].isSelected())
					saveFav(showPanel.getM3pos());
				else
					removeFav(showPanel.getM3pos());
			}
		});

		chksFav[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[3].isSelected())
					saveFav(showPanel.getM4pos());
				else
					removeFav(showPanel.getM4pos());
			}
		});

		chksFav[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[4].isSelected())
					saveFav(showPanel.getM5pos());
				else
					removeFav(showPanel.getM5pos());
			}
		});
		/*
		 * End
		 */

		/*
		 * Exports the favorites to a csv file with the name of the user in the folder
		 * "exports"
		 */
		showPanel.getBtnsFavs()[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportFavs();
				showPanel.getBtnsFavs()[1].setEnabled(true);
			}
		});

		/*
		 * Imports the favorites of the user from a csv file in the folder "exports"
		 */
		showPanel.getBtnsFavs()[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				importFavs();
			}
		});
	}

	/**
	 * Adds the favorite to the user's ArrayList<Show>
	 * 
	 * @param position Int that represents the position in Almacen.shows of the show
	 *                 we want to add
	 */
	private void saveFav(int position) {
		usuario.addShow(Almacen.shows.get(position));
	}

	/**
	 * Removes the favorite to the user's ArrayList<Show>
	 * 
	 * @param position Int that represents the position in Almacen.shows of the show
	 *                 we want to remove
	 */
	private void removeFav(int position) {
		usuario.removeShow(Almacen.shows.get(position));
	}

	/**
	 * Updates the CSVWriter and Scanner of the file
	 */
	private void updateSrcFavs() {
		try {
			favsWriter = new FileWriter(favs, true);
			scWriter = new Scanner(favs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exports the favorites, deleting the file if it exists and creating a new one
	 * with all the favorites.
	 */
	private void exportFavs() {
		String[] opt = { ",", ";", "TAB" };
		int choice = JOptionPane.showOptionDialog(frame, "Elige el separador", null, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opt, 0);
		UserDAO uDao = new UserDAO();
		boolean export = true;

		switch (choice) {
		case 0:
			usuario.setSeparator(",");
			uDao.setSeparator(usuario.getName(), ",");
			break;

		case 1:
			usuario.setSeparator(";");
			uDao.setSeparator(usuario.getName(), ";");
			break;

		case 2:
			usuario.setSeparator("\t");
			uDao.setSeparator(usuario.getName(), "\t");
			break;

		default:
			export = false;
		}

		if (export) {
			if (favs.exists()) {
				try {
					favs.delete();
					favs.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			updateSrcFavs();
			for (Show s : usuario.getFavorites()) {
				String[] line = { s.getShow_id(), s.getTitle() };
				try {
					favsWriter.write(line[0] + usuario.getSeparator() + line[1] + "\n");
					favsWriter.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Imports the favorites, clearing the user's ArrayList<Show> and populating it
	 * with the new shows from the csv file
	 */
	private void importFavs() {
		if (usuario.getSeparator() != null) {
			ArrayList<Integer> ids = new ArrayList<>();
			usuario.getFavorites().clear();
			scWriter.useDelimiter(usuario.getSeparator());
			while (scWriter.hasNextLine()) {
				String line = scWriter.nextLine().replace("\"", "");
				ids.add(Integer.parseInt(line.split(usuario.getSeparator())[0]));
			}
			UserDAO.populateUserFavs(usuario, ids);
			showPanel.repaintFavs();
		}
	}
}
