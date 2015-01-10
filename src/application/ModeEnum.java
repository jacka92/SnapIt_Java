package application;

public enum  ModeEnum {
	TIMEWARP("timeWarp"),TIMELAPSE("timeLapse"),TIMERMENU("timerMenu"), 
	SOUNDMODE("soundMode"), SIMPLEMODE("simpleMode"), SETTINGS("settingsMode"),
	MAINMENU("main");
	
	private final String modeName;
	
	ModeEnum(String modeName){
		this.modeName = modeName;
	}
	
	String getMode(){
		return modeName;
	}
}
