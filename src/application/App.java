package application;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class App {

	public static void main(String...argv){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				JFrame frame = new MainFrame("SnapIT");
				frame.setSize(600, 700);
				frame.setMinimumSize(frame.getMinimumSize());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}	
		});
	}
}
