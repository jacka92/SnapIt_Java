/*
 * 
 */
package application;

// TODO: Auto-generated Javadoc
/**
 * The Enum ModeEnum.
 */
public enum  ModeEnum {
	
	/** The timewarp. */
	TIMEWARP("timeWarp"),
/** The timelapse. */
TIMELAPSE("timeLapse"),
/** The timermenu. */
TIMERMENU("timerMenu"), 
	
	/** The soundmode. */
	SOUNDMODE("soundMode"), 
 /** The simplemode. */
 SIMPLEMODE("simpleMode"), 
 /** The settings. */
 SETTINGS("settingsMode"),
	
	/** The mainmenu. */
	MAINMENU("main");
	
	/** The mode name. */
	private final String modeName;
	
	/**
	 * Instantiates a new mode enum.
	 *
	 * @param modeName the mode name
	 */
	ModeEnum(String modeName){
		this.modeName = modeName;
	}
	
	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	String getMode(){
		return modeName;
	}
}
