package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import models.Show;
import models.User;
import ui.models.ShowInfoPanel;
import ui.models.ShowListPanel;
import utils.Almacen;
import utils.ShowFilter;

public class ShowList {
	private User usuario;

	private JFrame frame;
	private ShowListPanel showPanel;
	private ShowInfoPanel showInfo;
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowList window = new ShowList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowList() {
		usuario = new User("dani", "dani", "dani");
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
				// TODO Auto-generated method stub
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM1pos());
			}
		});

		btnsMore[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM2pos());
			}
		});

		btnsMore[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM3pos());
			}
		});

		btnsMore[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM4pos());
			}
		});

		btnsMore[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showPanel.setVisible(false);
				showInfo.setVisible(true);
				showInfo.updateInfo(showPanel.getM5pos());
			}
		});

		chksFav[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[0].isSelected())
					usuario.getFavorites().add(Almacen.shows.get(showPanel.getM1pos()));

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

		chksFav[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[1].isSelected())
					usuario.getFavorites().add(Almacen.shows.get(showPanel.getM2pos()));

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

		chksFav[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[2].isSelected())
					usuario.getFavorites().add(Almacen.shows.get(showPanel.getM3pos()));

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

		chksFav[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[3].isSelected())
					usuario.getFavorites().add(Almacen.shows.get(showPanel.getM4pos()));

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

		chksFav[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chksFav[4].isSelected())
					usuario.getFavorites().add(Almacen.shows.get(showPanel.getM5pos()));

				for (Show s : usuario.getFavorites())
					System.out.println(s.getTitle());
			}
		});

	}
}
