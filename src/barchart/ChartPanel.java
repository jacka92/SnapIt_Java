/*
 * 
 */
package barchart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ChartPanel.
 */
public class ChartPanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -356807261186412072L;
	
	/** The values. */
	private double[] values;
	
	/** The names. */
	private String[] names;

	/**
	 * Instantiates a new chart panel.
	 *
	 * @param values the values
	 * @param names the names
	 */
	public ChartPanel(double[] values,String[] names ){
		this.values = values;
		this.names = names;
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		drawBar(g2d);
	}
	
	/**
	 * Draw bar.
	 *
	 * @param g2d the g2d
	 */
	public void drawBar(Graphics2D g2d){
		Dimension d = getSize();
		int height = d.height;
		int width = d.width;
		
		int numbBars = values.length;
		int widthBar = width/numbBars;
		
		double max = 0;
		for(int i=0; i<values.length; i++){
			if(max<values[i]){
				max = values[i];
			}
		}
		
		int MaxheightBar = (int) (height/max * max);
		int heightBar = (int) (height/max);
		for(int i=0; i<values.length ; i++){
			g2d.setColor(Color.getHSBColor(200*(1+i), 150*(1+i), 100*(1+i)));
			g2d.fillRect(0 + i*widthBar , MaxheightBar - (int)(heightBar *values[i]), widthBar, (int)(heightBar *values[i]));
			g2d.setColor(Color.black);
			g2d.drawRect(0 + i*widthBar, MaxheightBar - (int)(heightBar *values[i]), widthBar, (int)(heightBar *values[i]));
			g2d.drawString(names[i], i*widthBar + widthBar/3 , MaxheightBar -  (int)(heightBar*values[i]*0.5));
		}
	}
}
