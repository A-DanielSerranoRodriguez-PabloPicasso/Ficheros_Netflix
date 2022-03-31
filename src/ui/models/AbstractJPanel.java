package ui.models;

import java.awt.Component;

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

	protected void addToBox(Box box, Component toAdd) {
		box.add(toAdd, box.getComponentCount() - 1);
	}

	protected void addVG() {
		add(Box.createVerticalGlue());
	}
}
