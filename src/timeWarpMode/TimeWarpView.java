/*
 * 
 */
package timeWarpMode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeWarpView.
 */
public class TimeWarpView extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The bezier. */
	Curve bezier = new Curve();
	
	/** The counter. */
	private static JLabel counter = new JLabel("Snaps Taken:" + 0 );
	
	/** The start. */
	private static JButton start;
	
	/** The start content. */
	public static String startContent="Start";
	
	/** The snap. */
	private JSpinner snap;
	
	/** The time span hours. */
	private JSpinner timeSpanHours;
	
	/** The time span mins. */
	private JSpinner timeSpanMins;
	
	/** The time span secs. */
	private JSpinner timeSpanSecs;
	
	/** The elapsed time. */
	private JLabel elapsedTime;
	
	/**
	 * Instantiates a new time warp view.
	 */
	public TimeWarpView(){
		super();

		createGUI();

	}
	
	/**
	 * Creates the gui.
	 */
	private void createGUI(){
		
		
		//Add the Start Button
		start= new JButton(startContent);
		start.setPreferredSize(new Dimension(150,50));
		start.setMaximumSize(new Dimension(150,50));
		start.setMinimumSize(new Dimension(150,50));
		JLabel timeLabel = new JLabel("set time");
		JLabel snapsLabel = new JLabel("set no. of pictures");

		snap = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 999.0,1));
		timeSpanHours = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 999.0,1));
		timeSpanMins = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 59,1));
		timeSpanSecs = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 59,1));

		JLabel hourLabel = new JLabel("HH"); hourLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel minLabel = new JLabel("MM");  minLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel secLabel = new JLabel("SS");  secLabel.setHorizontalAlignment(JLabel.CENTER);

		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		elapsedTime = new JLabel("Time Elapsed: " + "0:0:0.00");
		elapsedTime.setHorizontalAlignment(JLabel.LEFT);
		elapsedTime.setAlignmentX(CENTER_ALIGNMENT);
		gc.gridx=1;
		gc.gridy=0;
		gc.anchor = GridBagConstraints.CENTER;
		add(elapsedTime, gc);

		gc.gridx =0;
		gc.gridy = 1;
		gc.gridwidth=3;
		gc.gridheight=3;
		gc.anchor = GridBagConstraints.NORTH;
		add(bezier, gc);

		gc.gridwidth=1;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.NONE;

		//time selection
		JPanel timePane = new JPanel();
		timePane.setLayout(new GridLayout(2,3));
		timePane.setBorder(BorderFactory.createLineBorder(Color.black));
		timePane.add(timeSpanHours);
		timePane.add(timeSpanMins);
		timePane.add(timeSpanSecs);
		timePane.add(hourLabel);
		timePane.add(minLabel);
		timePane.add(secLabel);
		
		

		gc.gridx =0;
		gc.gridy=5;
		add(timePane, gc);


		gc.gridx =1;
		gc.gridy=5;
		add(snap, gc);

		gc.weightx =1;
		gc.gridx =2;
		gc.gridy=5;
		gc.fill = GridBagConstraints.NONE;
		add(start, gc);
		
		gc.weightx =1;
		gc.gridx =2;
		gc.gridy=6;
		gc.fill = GridBagConstraints.NONE;
		add(counter, gc);

		gc.weightx =1;
		gc.gridx =0;
		gc.gridy=6;
		gc.fill = GridBagConstraints.NONE;
		add(timeLabel, gc);

		gc.weightx =1;
		gc.gridx =1;
		gc.gridy=6;
		add(snapsLabel, gc);

	}
	
	/**
	 * Gets the time interval.
	 *
	 * @return the time interval
	 */
	public double getTimeInterval(){
		double timeInt = 0;
		timeInt += 3600 *(double)timeSpanHours.getValue();
		timeInt += 60 *(double)timeSpanMins.getValue();
		timeInt += (double)timeSpanSecs.getValue();
		return timeInt;
		
	}
	
	/**
	 * Gets the num snaps.
	 *
	 * @return the num snaps
	 */
	public int getNumSnaps(){
		return (int)(double)snap.getValue();
	}
	
	/**
	 * Reset button.
	 *
	 * @param s the s
	 */
	public void resetButton(String s){
		start.setText(s);
	}
	
	/**
	 * Sets the time elapsed label.
	 *
	 * @param time the new time elapsed label
	 */
	public void setTimeElapsedLabel(double time){
		double totalSecs = time / 1000;
		int hours = (int)(totalSecs / 3600);
		int minutes = (int)(totalSecs % 3600) / 60;
		int seconds = (int)(totalSecs % 60);
		int cents = (int)(time%1000) / 10;
		
		String timeString = hours +":" + minutes + ":" + seconds + "." + cents;
		elapsedTime.setText("Time Elapsed: " + timeString);
	}
	
	
	/**
	 * Update counter.
	 *
	 * @param i the i
	 */
	public static void updateCounter(int i){
		counter.setText("Snaps Taken:" + i);
	}
	

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	public Dimension getPreferredSize(){
		return new Dimension(500,700);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getMinimumSize()
	 */
	public Dimension getMinimumSize(){
		return new Dimension(500,700);
	}
	
	/**
	 * Sets the time warp listener.
	 *
	 * @param listener the new time warp listener
	 */
	public void setTimeWarpListener(ActionListener listener){
		start.addActionListener(listener);
	}

}


