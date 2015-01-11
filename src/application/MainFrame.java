/*
 * 
 */
package application;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import timeWarpMode.*;
import settingsMode.*;
import simpleMode.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MainFrame.
 */
public class MainFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The is time warp. */
	boolean isTimeWarp = false;
	
	/** The current mode. */
	private String currentMode;

	/**
	 * Instantiates a new main frame.
	 *
	 * @param s the s
	 */
	public MainFrame(String s){
		super(s);


		StatusBar statusBar = new StatusBar();

		this.setLayout(new BorderLayout());

		JPanel mode = new JPanel();
		mode.setLayout(new CardLayout());

		MainMenu main = new MainMenu();
		TimeWarpView view = new TimeWarpView();
		TimeWarpModel model = new TimeWarpModel();

		TimeWarpController timeWarp = new TimeWarpController(model,view);

		soundMode.SoundMode soundMode = new soundMode.SoundMode();
		timeLapseMode.TimeLapse timeLapse = new timeLapseMode.TimeLapse();

		TimerMenu timerMenu = new TimerMenu();
		SettingsMode settingsMode = new SettingsMode();
		SimpleMode simpleMode = new SimpleMode();

		mode.add(main, ModeEnum.MAINMENU.getMode());
		mode.add(view, ModeEnum.TIMEWARP.getMode());
		mode.add(soundMode, ModeEnum.SOUNDMODE.getMode());
		mode.add(timeLapse, ModeEnum.TIMELAPSE.getMode());
		mode.add(timerMenu, ModeEnum.TIMERMENU.getMode());
		mode.add(settingsMode, ModeEnum.SETTINGS.getMode());
		mode.add(simpleMode, ModeEnum.SIMPLEMODE.getMode());

		getContentPane().add(statusBar, BorderLayout.NORTH);
		getContentPane().add(mode, BorderLayout.CENTER);

		CardLayout cards = (CardLayout) mode.getLayout();
		cards.show(mode, ModeEnum.MAINMENU.getMode());

		statusBar.setStatusListener(new StatusListener(){
			public void StatusPerformed(String op) {
				
				soundMode.terminate();
				timeLapse.terminate();
				timeWarp.terminate();
				if(op.equals(StatusEnum.HOME.getOperation())){
					cards.show(mode,ModeEnum.MAINMENU.getMode());
					currentMode = ModeEnum.MAINMENU.getMode();
				}else if(op.equals(StatusEnum.BACK.getOperation())){
					if(currentMode.equals(ModeEnum.TIMEWARP.getMode()) || currentMode.equals(ModeEnum.TIMELAPSE.getMode())){
						cards.show(mode, ModeEnum.TIMERMENU.getMode());
						currentMode = ModeEnum.TIMERMENU.getMode();
					}else{
						cards.show(mode, ModeEnum.MAINMENU.getMode());
						currentMode = ModeEnum.MAINMENU.getMode();
					}
				}
			}
		});

		timerMenu.setMenuListener(new MenuListener(){
			public void MenuPerformed(String menu) {
				cards.show(mode, menu);
				currentMode = menu;
			}
		});

		main.setMenuListener(new MenuListener(){
			public void MenuPerformed(String menu) {
				cards.show(mode, menu);
				currentMode = menu;
			}
		});


	}

	/* (non-Javadoc)
	 * @see java.awt.Container#getMinimumSize()
	 */
	public Dimension getMinimumSize(){
		return new Dimension(700,700);
	}


}