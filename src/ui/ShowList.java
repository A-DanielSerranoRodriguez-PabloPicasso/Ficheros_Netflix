package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import com.opencsv.CSVWriter;

import models.Show;
import models.User;
import ui.models.ShowInfoPanel;
import ui.models.ShowListPanel;
import utils.Almacen;
import utils.ShowFilter;

public class ShowList {
	private User usuario;
	private File favs, favsAux;
	private CSVWriter favsWriter, favsAuxWriter;
	private Scanner scWriter, scAux;

	private JFrame frame;
	private ShowListPanel showPanel;
	private ShowInfoPanel showInfo;

	/**
	 * Create the application.
	 */
	public ShowList(User usuario) {
		this.usuario = usuario;
		favs = new File("favorites/" + this.usuario.getName() + ".csv");
		favsAux = new File("favorites/" + this.usuario.getName() + "-aux.csv");
		try {
			favsWriter = new CSVWriter(new FileWriter(favs));
			favsAuxWriter = new CSVWriter(new FileWriter(favsAux));
			scWriter = new Scanner(favs);
			scAux = new Scanner(favsAux);
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

	private void setUIcomponents() {
		this.showPanel = new ShowListPanel(usuario);
		frame.getContentPane().add(showPanel, "name_14215655555075");
		this.showInfo = new ShowInfoPanel();
		frame.getContentPane().add(showInfo);
	}

	private void setUIbehaviour() {
		JButton[] btnsNav = showPanel.getBtnsNav(), btnsMore = showPanel.getBtnsShows();
		JButton btnFilter = showPanel.getBtnFilter();
		JCheckBox[] chksFav = showPanel.getChksFav();

		showPanel.getJcbFilter().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (showPanel.getJcbFilter().getSelectedItem() == ShowFilter.Nada)
					showPanel.getTxtFilter().setEnabled(false);
				else
					showPanel.getTxtFilter().setEnabled(true);
			}
		});

		btnsNav[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.prevousPage();
				frame.repaint();
			}
		});

		btnsNav[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.nextPage();
				frame.repaint();
			}
		});

		btnFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.setFilter((ShowFilter) showPanel.getJcbFilter().getSelectedItem(),
						showPanel.getTxtFilter().getText());
			}
		});

		showInfo.getBtnExit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPanel.setVisible(true);
				showInfo.setVisible(false);
			}
		});

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

		chksFav[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[0].isSelected())
					saveFav(showPanel.getM1pos());
				else
					removeFav(showPanel.getM1pos());

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

		chksFav[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[1].isSelected())
					saveFav(showPanel.getM2pos());
				else
					removeFav(showPanel.getM2pos());

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

		chksFav[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[2].isSelected())
					saveFav(showPanel.getM3pos());
				else
					removeFav(showPanel.getM3pos());

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

		chksFav[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[3].isSelected())
					saveFav(showPanel.getM4pos());
				else
					removeFav(showPanel.getM4pos());

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

		chksFav[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[4].isSelected())
					saveFav(showPanel.getM5pos());
				else
					removeFav(showPanel.getM5pos());

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});
	}

	private void saveFav(int position) {
		Show s = Almacen.shows.get(position);
		String[] line = { s.getShow_id(), s.getTitle() };
		favsWriter.writeNext(line);
		try {
			favsWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void removeFav(int position) {
		Show s = Almacen.shows.get(position);

		favsAux.delete();
		try {
			favsAux.createNewFile();
			favsAuxWriter = new CSVWriter(new FileWriter(favsAux));
			scAux = new Scanner(favsAux);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		while (scWriter.hasNextLine()) {
			String[] line = scWriter.nextLine().replace("\"", "").split(",");
			if (!s.getShow_id().equals(line[0])) {
				favsAuxWriter.writeNext(line);
			}
			try {
				favsAuxWriter.flush();
			} catch (IOException e1) {
				e1.printStackTrace();

			}
		}
		favs.delete();
		try {
			favs.createNewFile();
			favsWriter = new CSVWriter(new FileWriter(favs));
			scWriter = new Scanner(favs);
			while (scAux.hasNextLine()) {
				String[] line = scAux.nextLine().replace("\"", "").split(",");
				favsWriter.writeNext(line);
				favsWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
