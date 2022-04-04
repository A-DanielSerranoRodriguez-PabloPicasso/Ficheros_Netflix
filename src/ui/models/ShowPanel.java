package ui.models;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import dao.ShowsDAO;
import models.User;
import utils.Almacen;
import utils.ShowFilter;

@SuppressWarnings("serial")
public class ShowPanel extends AbstractJPanel {
	private User user;
	private int page, position;

	private Box hbUpper, vbUpper, hbNav, hbOpt, hbMid, hbBottom;
	private JLabel lblTitle, lblShows, lblFilter, lblStitle, lblStype, lblSdirector, lblScountry, lblSdateAdded,
			lblSdateReleased, lblSrating, lblSduration, lblSlistedIn, lblSdescription;
	private JComboBox<ShowFilter> jcbFilter;
	private JButton btnFilter, btnNextP, btnPreviousP;
	private JCheckBox chkFav;

	public ShowPanel(User user) {
		this.user = user;
		this.page = 0;
		this.position = 0;

		hbMid = defaultHB();
		createShows();

		hbUpper = defaultHB();
		vbUpper = defaultVB();
		hbNav = defaultHB();
		btnPreviousP = new JButton("<");
		lblTitle = new JLabel("Pagina " + (page + 1) + " de " + Almacen.shows.size() / 8);
		btnNextP = new JButton(">");
		addToBox(hbNav, btnPreviousP);
		addToBox(hbNav, lblTitle);
		addToBox(hbNav, btnNextP);
		addToBox(vbUpper, hbNav);

		hbOpt = defaultHB();
		lblShows = new JLabel("Shows");
		lblFilter = new JLabel("Filtro:");
		jcbFilter = new JComboBox<ShowFilter>(new DefaultComboBoxModel<ShowFilter>(ShowFilter.values()));
		btnFilter = new JButton(new ImageIcon("resources/img/filter.png"));
		btnFilter.setBorder(BorderFactory.createEmptyBorder());
		btnFilter.setContentAreaFilled(false);
		addToBox(hbOpt, lblShows);
		addToBox(hbOpt, lblFilter);
		addToBox(hbOpt, jcbFilter);
		addToBox(hbOpt, btnFilter);
		addToBox(vbUpper, hbOpt);

		addToBox(hbUpper, vbUpper);

		add(Box.createVerticalStrut(40));
		add(hbUpper);
		addVG();
		add(hbMid);
		addVG();
	}

	protected void createShows() {
		new ShowsDAO().fillShows(page * 8);
		for (int i = page; i < 5; i++) {
			Box hbShow, vbShow, hbTitle;
			JLabel lblTitle = new JLabel(Almacen.shows.get(i * (page + 1)).getTitle());

			hbShow = defaultHB();
			vbShow = defaultVB();
			hbTitle = defaultHB();

			addToBox(hbTitle, lblTitle);

			addToBox(vbShow, hbTitle);
			addToBox(hbShow, vbShow);
			addToBox(hbMid, hbShow);
			addToBox(hbMid, Box.createVerticalGlue());
		}
	}

	protected JButton[] getNav() {
		JButton[] nav = { btnPreviousP, btnNextP };
		return nav;
	}
}
