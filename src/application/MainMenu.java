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
 * The Class MainMenu.
 */
public class MainMenu extends JPanel implements ActionListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The timer menu. */
	JButton timerMenu = new JButton();
	
	/** The snap. */
	JButton snap = new JButton();
	
	/** The settings. */
	JButton settings = new JButton();
	
	/** The sound mode. */
	JButton soundMode = new JButton();
	
	/** The listener. */
	private MenuListener listener;
	
	/**
	 * Instantiates a new main menu.
	 */
	public MainMenu() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(20, 20, 20, 20);
		
		gc.gridx =0; gc.gridy =0;
		Image timer = new ImageIcon(this.getClass().getResource("/timer.png")).getImage();
		timerMenu.setIcon(new ImageIcon(timer));
		timerMenu.setHorizontalAlignment(JButton.CENTER);
		timerMenu.setName(ModeEnum.TIMERMENU.getMode());
		add(timerMenu, gc);
		
		gc.gridx =1; gc.gridy =0;
		Image simple = new ImageIcon(this.getClass().getResource("/simple.png")).getImage();
		snap.setIcon(new ImageIcon(simple));
		snap.setHorizontalAlignment(JButton.CENTER);
		snap.setName(ModeEnum.SIMPLEMODE.getMode());
		add(snap,gc);
		
		gc.gridx =0; gc.gridy =1;
		Image settingsI = new ImageIcon(this.getClass().getResource("/settings.png")).getImage();
		settings.setIcon(new ImageIcon(settingsI));
		settings.setHorizontalAlignment(JButton.CENTER);
		settings.setName(ModeEnum.SETTINGS.getMode());
		add(settings,gc);
		
		gc.gridx =1; gc.gridy =1;
		Image sound = new ImageIcon(this.getClass().getResource("/sound.png")).getImage();
		soundMode.setIcon(new ImageIcon(sound));
		soundMode.setHorizontalAlignment(JButton.CENTER);
		soundMode.setName(ModeEnum.SOUNDMODE.getMode());
		add(soundMode,gc);
		
		soundMode.addActionListener(this);
		timerMenu.addActionListener(this);
		settings.addActionListener(this);
		snap.addActionListener(this);
		
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
		
		if(((JButton)e.getSource()).getName().equals(ModeEnum.SIMPLEMODE.getMode())){
			helper.JSONhandle.increaseSimpleMode();
		}else if(((JButton)e.getSource()).getName().equals(ModeEnum.SOUNDMODE.getMode())){
			helper.JSONhandle.increaseSoundMode();
		}
		
		
	}
	

}
