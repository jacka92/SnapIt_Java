/*
 * 
 */
package barchart;
import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class ShowGraph.
 */
public class ShowGraph {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String... args){
		
	}
	
	/** The values. */
	static double[] values= new double[4];
	
	/**
	 * Show graph.
	 *
	 * @param timeLapse the time lapse
	 * @param timeWarp the time warp
	 * @param SoundMode the sound mode
	 * @param SimpleMode the simple mode
	 */
	public static void showGraph(int timeLapse, int timeWarp, int SoundMode, int SimpleMode){
		values[0] = timeLapse;
		values[1] = timeWarp;
		values[2] = SoundMode;
		values[3] = SimpleMode;
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				gui();
			}
		});
		
	}
	
	
	/**
	 * Gui.
	 */
	public static void gui(){
		JFrame frame=new JFrame();
		frame.setSize(600,300);
		
		
		String[] names= new String[4];
		
		names[0] = "TimeLapse";

		names[1] = "TimeWarp";
		
		names[2] = "SoundMode";
		
		names[3] = "SimpleMode";
		

		frame.getContentPane().add(new ChartPanel(values, names));
		frame.setVisible(true);
		
	}

}
