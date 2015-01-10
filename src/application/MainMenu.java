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



public class MainMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	JButton timerMenu = new JButton();
	JButton snap = new JButton();
	JButton settings = new JButton();
	JButton soundMode = new JButton();
	
	private MenuListener listener;
	
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
	
	public void setMenuListener(MenuListener listener){
		this.listener = listener;
	}
	
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
