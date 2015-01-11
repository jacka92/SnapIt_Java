/*
 * 
 */
package application;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class TimerMenu.
 */
public class TimerMenu extends JPanel implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -321640520366017219L;
	
	/** The time warp. */
	JButton timeWarp;
	
	/** The time lapse. */
	JButton timeLapse;
	
	/** The listener. */
	private MenuListener listener;
	
	/**
	 * Instantiates a new timer menu.
	 */
	public TimerMenu(){
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc  = new GridBagConstraints();
		gc.insets = new Insets(20,20,20,20);
		gc.anchor = GridBagConstraints.CENTER;
		
		timeLapse = new JButton();
		timeWarp = new JButton();
		
		Image timeLapseIm = new ImageIcon(this.getClass().getResource("/timeLapse.png")).getImage();
		timeLapse.setIcon(new ImageIcon(timeLapseIm));
		
		Image timeWarpIm = new ImageIcon(this.getClass().getResource("/timeWarp.png")).getImage();
		timeWarp.setIcon(new ImageIcon(timeWarpIm));
		
		timeLapse.setHorizontalAlignment(JButton.CENTER);
		timeLapse.setName(ModeEnum.TIMELAPSE.getMode());
		timeWarp.setHorizontalAlignment(JButton.CENTER);
		timeWarp.setName(ModeEnum.TIMEWARP.getMode());
		
		gc.gridx=0; gc.gridy=0;
		add(timeWarp,gc);
		
		gc.gridx=0; gc.gridy=1;
		add(timeLapse, gc);
		
		timeLapse.addActionListener(this);
		timeWarp.addActionListener(this);
	}
	
	/**
	 * Sets the menu listener.
	 *
	 * @param listener the new menu listener
	 */
	public void setMenuListener(MenuListener listener){
		this.listener = listener;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if(listener != null){
			listener.MenuPerformed(((JButton)e.getSource()).getName());
		}
		
		if(((JButton)e.getSource()).getName().equals(ModeEnum.TIMELAPSE.getMode())){
			helper.JSONhandle.increaseTimeLapse();
		}else if(((JButton)e.getSource()).getName().equals(ModeEnum.TIMEWARP.getMode())){
			helper.JSONhandle.increaseTimeWarp();
		}
	}

}
