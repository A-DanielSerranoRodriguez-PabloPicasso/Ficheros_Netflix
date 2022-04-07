package ui.models;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractJPanel extends JPanel {
	protected AbstractJPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	protected Box defaultHB() {
		Box hb = Box.createHorizontalBox();
		hb.add(Box.createHorizontalGlue());
		hb.add(Box.createHorizontalGlue());
		return hb;
	}

	protected Box defaultVB() {
		Box vb = Box.createVerticalBox();
		vb.add(Box.createVerticalGlue());
		vb.add(Box.createVerticalGlue());
		return vb;
	}

	protected Component defaultHS(int i) {
		Component hs = Box.createHorizontalStrut(i);
		hs.setMaximumSize(new Dimension(i, 0));
		return hs;
	}

	protected Component defaultVS(int i) {
		Component vs = Box.createVerticalStrut(i);
		vs.setMaximumSize(new Dimension(i, 0));
		return vs;
	}

	protected void addToBox(Box box, Component toAdd) {
		box.add(toAdd, box.getComponentCount() - 1);
	}

	protected void addVG() {
		add(Box.createVerticalGlue());
	}

	protected void addVS(int i) {
		add(defaultVS(i));
	}

}
