package ui.models;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractJPanel extends JPanel {
	/**
	 * Creates a JPanel with a default BoxLayout following the Y_AXIS
	 */
	protected AbstractJPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	/**
	 * Creates an horizontal box with with 2 horizontal glues
	 * 
	 * @return Customized HorizontalBox
	 */
	protected Box defaultHB() {
		Box hb = Box.createHorizontalBox();
		hb.add(Box.createHorizontalGlue());
		hb.add(Box.createHorizontalGlue());
		return hb;
	}

	/**
	 * Creates a vertical box with with 2 vertical glues
	 * 
	 * @return Customized VerticalBox
	 */
	protected Box defaultVB() {
		Box vb = Box.createVerticalBox();
		vb.add(Box.createVerticalGlue());
		vb.add(Box.createVerticalGlue());
		return vb;
	}

	/**
	 * Creates an horizontal strut with the indicated length and no width
	 * 
	 * @param i Int that represents the length of the strut
	 * @return Customized HorizontalStrut
	 */
	protected Component defaultHS(int i) {
		Component hs = Box.createHorizontalStrut(i);
		hs.setMaximumSize(new Dimension(i, 0));
		return hs;
	}

	/**
	 * Creates a vertical strut with the indicated length and no width
	 * 
	 * @param i Int that represents the length of the strut
	 * @return Customized VerticalStrut
	 */
	protected Component defaultVS(int i) {
		Component vs = Box.createVerticalStrut(i);
		vs.setMaximumSize(new Dimension(i, 0));
		return vs;
	}

	/**
	 * Adds the component correctly in the custom horizontal/vertical box, setting
	 * it between the 2 horizontal/vertical glues
	 * 
	 * @param box   Custom horizontal/vertical box
	 * @param toAdd Component that will be added
	 */
	protected void addToBox(Box box, Component toAdd) {
		box.add(toAdd, box.getComponentCount() - 1);
	}

	/**
	 * Adds a vertical glue to the frame
	 */
	protected void addVG() {
		add(Box.createVerticalGlue());
	}

	/**
	 * Adds a vertical strut to the frame
	 * 
	 * @param i Int that represents the length of the strut
	 */
	protected void addVS(int i) {
		add(defaultVS(i));
	}

}
