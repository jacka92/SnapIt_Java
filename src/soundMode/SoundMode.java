package soundMode;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SoundMode extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel ear;
	static helper.Recorder recorder = new helper.Recorder();
	boolean inRecordingMode = false;
	static Integer sliderVal = new Integer(50);
	
	///Volume Bars
	static ArrayList<javax.swing.JLabel> vols;
	
	Image green = new ImageIcon(this.getClass().getResource("/greenVol.png")).getImage();
	Image yellow = new ImageIcon(this.getClass().getResource("/YelloVol.png")).getImage();
	Image orange = new ImageIcon(this.getClass().getResource("/orangeVol.png")).getImage();
	Image red = new ImageIcon(this.getClass().getResource("/redVol.png")).getImage();
	Image mic = new ImageIcon(this.getClass().getResource("/mic.png")).getImage();

	static JSlider slider;
	private JButton Start;
	private JButton help;
	static Timer timer;
	static int time = 100;

	public SoundMode() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS ));

		
		JPanel empty = new JPanel();
		empty.setPreferredSize(new Dimension(150,60));
		empty.setMaximumSize(new Dimension(150,60));
		empty.setMinimumSize(new Dimension(150,60));
		add(empty);
		
		
		///Timer will call method to map volume to visuals
		timer = new Timer(time, taskPerformer);
		timer.setRepeats(true);
		help = new JButton("Help");
		help.setPreferredSize(new Dimension(150,50));
		help.setMaximumSize(new Dimension(150,50));
		help.setMinimumSize(new Dimension(150,50));
		help.setAlignmentX(CENTER_ALIGNMENT);
		add(help);
		
		ear = new JLabel();
		ear.setPreferredSize(new Dimension(500,300));
		ear.setMaximumSize(new Dimension(500,300));
		ear.setMinimumSize(new Dimension(500,300));
		ear.setAlignmentX(CENTER_ALIGNMENT);
		ear.setHorizontalAlignment(JLabel.CENTER);
		add(ear);
		ear.setIcon(new ImageIcon(mic));


		vols = new ArrayList<javax.swing.JLabel>();
		for(int i =0; i<10; i++){
			vols.add(new JLabel());
		}
		
		JPanel meter = new JPanel();
		meter.setPreferredSize(new Dimension(300,50));
		meter.setMaximumSize(new Dimension(300,50));
		meter.setMinimumSize(new Dimension(300,50));
		meter.setAlignmentX(CENTER_ALIGNMENT);
		meter.setLayout(new GridLayout(0,10));
		setBars(meter);
		add(meter);
		setBarsInvisible();

		
		///Slider sets threshold above which beep will be called for Snap
		slider = new JSlider();
		slider.setPreferredSize(new Dimension(300,50));
		slider.setMaximumSize(new Dimension(300,50));
		slider.setMinimumSize(new Dimension(300,50));
		slider.setAlignmentX(CENTER_ALIGNMENT);
		add(slider);
		
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				sliderVal = (source.getValue());		
			}
		});

		Start = new JButton("Start Listening");
		Start.setPreferredSize(new Dimension(150,50));
		Start.setMaximumSize(new Dimension(150,50));
		Start.setMinimumSize(new Dimension(150,50));
		Start.setAlignmentX(CENTER_ALIGNMENT);
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!inRecordingMode){	
					inRecordingMode = true;
					Start.setText("Stop Listening");
					recorder.start();
					timer.start();
				}else{
					setBarsInvisible();
					Start.setText("Start Listening");
					timer.stop();
					recorder.finish();
					inRecordingMode = false;
				}
			}
		});
		add(Start);

		
	}
	
	public static  void setBarsInvisible(){
		for(int i=0;i<vols.size();i++){
			
			vols.get(i).setVisible(false);
		}
	}
	
	public void setBars(JPanel meter){
		for(int i=0;i<vols.size()/2;i++){
			vols.get(i).setIcon(new ImageIcon(green));
			meter.add(vols.get(i));
		}
		
		for(int i=vols.size()/2;i<vols.size()/2 + vols.size()/4;i++){
			vols.get(i).setIcon(new ImageIcon(orange));
			meter.add(vols.get(i));
		}
		
		for(int i=vols.size()/2 + vols.size()/4; i< vols.size();i++){
			vols.get(i).setIcon(new ImageIcon(red));
			meter.add(vols.get(i));
		}
		
	}

	static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			setBarsInvisible();
			Integer i = new Integer(recorder.getRMS()*2);
			
			for(int j=0;j<vols.size();j++){
				if(j*6 < i){
					vols.get(j).setVisible(true);
					if(vols.get(j).isVisible() && j*10 > sliderVal){
						Toolkit.getDefaultToolkit().beep();
					}
				}
			}
			
		}
	};
	
	public void terminate(){
		if(inRecordingMode){
			setBarsInvisible();
			Start.setText("Start Listening");
			timer.stop();
			recorder.finish();
			inRecordingMode = false;
		}
	}
	
}
