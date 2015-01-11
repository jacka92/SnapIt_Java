/*
 * 
 */
package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusBar.
 */
public class StatusBar extends JPanel implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -801241456363105666L;
	
	/** The listener. */
	private StatusListener listener;
	
	/**
	 * Instantiates a new status bar.
	 */
	public StatusBar(){
		JLabel title = new JLabel("SnapIT");
		JButton btn = new JButton("Home");
		JButton back = new JButton("Back");
		add(title);
		add(btn);
		add(back);
		
		back.addActionListener(this);
		back.setName(StatusEnum.BACK.getOperation());
		btn.addActionListener(this);
		btn.setName(StatusEnum.HOME.getOperation());
	}
	
	/**
	 * Sets the status listener.
	 *
	 * @param listener the new status listener
	 */
	public void setStatusListener(StatusListener listener){
		this.listener = listener;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if(listener !=null){
			listener.StatusPerformed(((JButton)e.getSource()).getName());
		}
	}
}
