package settingsMode;

import java.awt.GridLayout;
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
		
		setLayout(new GridLayout(6,0));
		
		recordActions = new JCheckBox("Record Actions ?");
		JLabel settingsLabel = new JLabel("This is settings Page");
		JButton showStats = new JButton("Show Stats");
		
		add(settingsLabel);
		add(recordActions);
		add(showStats);
		
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
