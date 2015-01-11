/*
 * 
 */
package simpleMode;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMode.
 */
public class SimpleMode extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8036978541775479731L;

	/**
	 * Instantiates a new simple mode.
	 */
	public SimpleMode(){
		JButton simple = new JButton("Snap");
		setLayout(new GridBagLayout());
		simple.setPreferredSize(new Dimension(150,50));
		simple.setMaximumSize(new Dimension(150,50));
		simple.setMinimumSize(new Dimension(150,50));
		add(simple);
		
		simple.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				helper.Click.snap();
			}
		});
	}

}
