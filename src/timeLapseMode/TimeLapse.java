package timeLapseMode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;

public class TimeLapse extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSlider hours, mins, cents, secs;
	JLabel hourCount, minCounts, secCount, centCounts;
	JSpinner spinner;

	private boolean isRunning = false;

	public int getHourSliderMils() {
		return hourSliderMils;
	}

	public int getMinSliderMils() {
		return minSliderMils;
	}

	public int getSecSliderMils() {
		return secSliderMils;
	}

	public int getCentSliderMils() {
		return centSliderMils;
	}

	int hourSliderMils;
	int minSliderMils;
	int secSliderMils;
	int centSliderMils;

	JSpinner timeSpanHours = new JSpinner(new SpinnerNumberModel(0.0, 0.0,
			999.0, 1));
	JSpinner timeSpanMins = new JSpinner(
			new SpinnerNumberModel(0.0, 0.0, 59, 1));
	JSpinner timeSpanSecs = new JSpinner(
			new SpinnerNumberModel(0.0, 0.0, 59, 1));
	JSpinner timeSpanCents = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 99,
			1));
	JSpinner snap = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 999.0, 1));

	Timer timer;

	static JLabel count = new JLabel("Taken so far : 0");
	static int counter = 0;
	JButton btnSave, btnView, btnStart;

	public TimeLapse() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(Box.createRigidArea(new Dimension(500, 80)));

		JLabel hourLabel = new JLabel("HH");
		hourLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel minLabel = new JLabel("MM");
		minLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel secLabel = new JLabel("SS");
		secLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel centsLabel = new JLabel(".xx");
		centsLabel.setHorizontalAlignment(JLabel.CENTER);

		JPanel timePane = new JPanel();
		timePane.setLayout(new GridLayout(2, 3));
		timePane.setBorder(BorderFactory.createLineBorder(Color.black));
		timePane.add(timeSpanHours);
		timePane.add(timeSpanMins);
		timePane.add(timeSpanSecs);
		timePane.add(timeSpanCents);
		timePane.add(hourLabel);
		timePane.add(minLabel);
		timePane.add(secLabel);
		timePane.add(centsLabel);
		timePane.setPreferredSize(new Dimension(300, 100));
		timePane.setMinimumSize(new Dimension(300, 100));
		timePane.setMaximumSize(new Dimension(300, 100));

		add(timePane);

		add(Box.createRigidArea(new Dimension(500, 50)));
		count.setHorizontalAlignment(JLabel.CENTER);
		count.setAlignmentX(CENTER_ALIGNMENT);
		add(count);

		

		btnView = new JButton("View Saved File");
		btnView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String readFileName = "Number of time lapse photos.html"; 
				String windowTitle = "Saved File";
				String noFileMessageDialogue = "File does not exist yet.";
				helper.HtmlHelper.readHtml(readFileName, windowTitle, noFileMessageDialogue);
			}
		});
		btnView.setPreferredSize(new Dimension(200, 50));
		btnView.setMaximumSize(new Dimension(200, 50));
		btnView.setMinimumSize(new Dimension(200, 50));
		btnView.setAlignmentX(CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(50, 5)));
		
		btnStart = new JButton("Start");
		btnStart.setPreferredSize(new Dimension(200, 50));
		btnStart.setMaximumSize(new Dimension(200, 50));
		btnStart.setMinimumSize(new Dimension(200, 50));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double timeInt = 0;
				int numSnaps = 0;

				timeInt += 3600 * (double) timeSpanHours.getValue();
				timeInt += 60 * (double) timeSpanMins.getValue();
				timeInt += (double) timeSpanSecs.getValue();
				timeInt += (double) timeSpanCents.getValue() / 100;

				numSnaps = (int) (double) snap.getValue();

				if (!isRunning) {
					btnStart.setText("Stop");
				} else {
					btnStart.setText("Start");
				}
				startLapsing(numSnaps, timeInt);
			}
		});

		btnSave = new JButton("Save Number of Snaps Taken");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = "Number of Photos Taken in a Time-Lapse";
				String h1 = "The number of photos taken in this time-lapse was: "
						+ Integer.toString(counter);
				String h2 = "This was saved on "
						+ new java.util.Date().toString();
				String writeFileName = "Number of time lapse photos.html";
				helper.HtmlHelper.writeHTML(title, h1, h2, writeFileName);
			}
		});
		btnSave.setPreferredSize(new Dimension(200, 50));
		btnSave.setMaximumSize(new Dimension(200, 50));
		btnSave.setMinimumSize(new Dimension(200, 50));
		btnSave.setAlignmentX(CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(50, 5)));
		

		JLabel snapLabel = new JLabel("Number of Snaps");
		snapLabel.setHorizontalAlignment(JLabel.CENTER);
		snapLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(snapLabel);

		snap.setPreferredSize(new Dimension(80, 50));
		snap.setMinimumSize(new Dimension(80, 50));
		snap.setMaximumSize(new Dimension(80, 50));
		add(snap);

		btnStart.setAlignmentX(CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(50, 20)));
		add(btnStart);
		add(Box.createRigidArea(new Dimension(50, 50)));

		add(btnSave);
		add(btnView);


	}

	private void startLapsing(int snaps, double interval) {

		if (interval < 0.15) {
			interval = 0.15;
		}
		if (!isRunning) {
			counter = 0;
			timer = new Timer((int) (interval * 1000), new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Toolkit.getDefaultToolkit().beep();
					counter++;
					count.setText("Taken so far : " + counter);
					if (snaps != 0) {
						if (counter == snaps) {
							timer.stop();
							timer = null;
							isRunning = false;
							btnStart.setText("Start");
						}
					}
				}
			});

			timer.start();
		} else {
			timer.stop();
			timer = null;
		}

		isRunning = !isRunning;
		count.setText("Taken so far : " + counter);
	}
	
	public void terminate(){
		if(timer != null){
			timer.stop();
			timer = null;
			isRunning=false;
			btnStart.setText("Start");
		}
	}

}
