package barchart;
import javax.swing.JFrame;

public class ShowGraph {
	public static void main(String... args){
		
	}
	
	static double[] values= new double[4];
	
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
