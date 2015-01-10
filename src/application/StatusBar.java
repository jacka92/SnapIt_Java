package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel implements ActionListener{

	private static final long serialVersionUID = -801241456363105666L;
	
	private StatusListener listener;
	public StatusBar(){
		JLabel title = new JLabel("SnapIT");
		JButton btn = new JButton("Home");
		JButton back = new JButton("Back");
		add(title);
		add(btn);
		add(back);
		
		back.addActionListener(this);
		back.setName(StatusEnum.BACK.getOperation());
		btn.addActionListener(this);
		btn.setName(StatusEnum.HOME.getOperation());
	}
	
	public void setStatusListener(StatusListener listener){
		this.listener = listener;
	}

	public void actionPerformed(ActionEvent e) {
		if(listener !=null){
			listener.StatusPerformed(((JButton)e.getSource()).getName());
		}
	}
}
