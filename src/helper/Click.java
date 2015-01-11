/*
 * 
 */
package helper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


// TODO: Auto-generated Javadoc
/**
 * The Class Click.
 */
public class Click {

	
	/** The click. */
	private static Click click = new Click();
	
	/**
	 * Play sounds.
	 */
	public void playSounds(){
		try{
			FileInputStream fis = new FileInputStream("sounds/snap.mp3");
			Player playMP3 = new Player(fis);
		    playMP3.play();
		}
		catch(FileNotFoundException | JavaLayerException exc){
		    exc.printStackTrace();
		    System.out.println("Failed to play the file.");
		}
	}
	
	/**
	 * Snap.
	 */
	public static void snap(){
		click.playSounds();
	}
}
