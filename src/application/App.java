/*
 * 
 */
package application;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


// TODO: Auto-generated Javadoc
/**
 * The Class App.
 */
public class App {

	/**
	 * The main method.
	 *
	 * @param argv the arguments
	 */
	public static void main(String...argv){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				JFrame frame = new MainFrame("SnapIT");
				frame.setSize(700, 700);
				frame.setMinimumSize(new Dimension(600,700));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}	
		});
	}
}
