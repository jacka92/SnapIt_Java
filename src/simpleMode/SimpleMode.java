package simpleMode;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SimpleMode extends JPanel {
	private static final long serialVersionUID = -8036978541775479731L;

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
