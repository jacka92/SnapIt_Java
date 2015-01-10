package settingsMode;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsMode extends JPanel {
	private static final long serialVersionUID = -1909876781076670357L;

	JCheckBox recordActions;
	
	public SettingsMode(){
		super();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		recordActions = new JCheckBox("Record Actions ?");
		JLabel settingsLabel = new JLabel("<html><p>If the Record Options is checked below, the"
				+ " number of times a mode is used is recorded in a json file format, Click "
				+ " the button below to see a bar graph of the data</p></html>");
		settingsLabel.setMinimumSize(new Dimension(400,200));
		settingsLabel.setPreferredSize(new Dimension(400,200));
		settingsLabel.setMaximumSize(new Dimension(400,200));
		JButton showStats = new JButton("Show Stats");
		
		gc.fill = GridBagConstraints.NONE; 
		settingsLabel.setHorizontalAlignment(JLabel.CENTER);
		gc.anchor = GridBagConstraints.CENTER;
		
		gc.gridx=0; gc.gridy=0;
		gc.ipady = 50;
		add(settingsLabel,gc);
		
		gc.gridx=0; gc.gridy=1;
		add(recordActions,gc);
		
		
		gc.gridx=0; gc.gridy=2;
		gc.weighty = 0.1;
		showStats.setPreferredSize(new Dimension(100,10));
		showStats.setMaximumSize(new Dimension(100,10));
		showStats.setMinimumSize(new Dimension(100,10));
		add(showStats,gc);
		
		getJSON();
		
		recordActions.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				System.out.println(recordActions.isSelected());
				helper.JSONhandle.setEnabled(recordActions.isSelected());
			}
			
		});
		

		showStats.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				barchart.ShowGraph.showGraph(helper.JSONhandle.getTimeLapse(),
						helper.JSONhandle.getTimeWarp(),helper.JSONhandle.getSoundMode(), helper.JSONhandle.getSimpleMode());
			}
			
		});
		
	}
	
	public void getJSON(){
//		recordActions.set
		recordActions.setSelected(helper.JSONhandle.getEnabled());
	}

}
