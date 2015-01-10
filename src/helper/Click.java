package helper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class Click {

	
	private static Click click = new Click();
	
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
	
	public static void snap(){
		click.playSounds();
	}
}
