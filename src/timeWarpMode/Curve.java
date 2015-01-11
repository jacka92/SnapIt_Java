/*
 * 
 */
package timeWarpMode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;


// TODO: Auto-generated Javadoc
/**
 * The Class Curve.
 */
public class Curve extends JComponent implements MouseMotionListener, MouseListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4610052069056742687L;

	/** The curve. */
	CubicCurve2D curve = new CubicCurve2D.Double();

	//Initial and final coordinates
	/** The start. */
	Point2D.Double start = new Point2D.Double(50, 450);
	
	/** The end. */
	Point2D.Double end = new Point2D.Double(450,50);

	//Control Points
	/** The cp1. */
	Point2D.Double cp1 = new Point2D.Double(450, 450);
	
	/** The cp2. */
	Point2D.Double cp2 = new Point2D.Double(50, 50);
	
	/** The selected. */
	Point2D.Double selected;

	/** The ep1. */
	Ellipse2D ep1;
	
	/** The ep2. */
	Ellipse2D ep2;

	/**
	 * Instantiates a new curve.
	 */
	public Curve(){
		super();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);

	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension(500, 500);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);     

		g.drawRect(50, 50, 400, 400);
		g.setColor(Color.WHITE);
		g.fillRect(50, 50, 400, 400);
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D)g;

		ep1 = new Ellipse2D.Double(cp1.x-20, cp1.y-20, 20, 20);
		ep2 = new Ellipse2D.Double(cp2.x, cp2.y, 20, 20);

		// draw CubicCurve2D.Double with set coordinates
		curve.setCurve(start, cp1, cp2, end);
		g2.draw(curve);

		g2.setColor(Color.RED);
		g2.fill(ep1);
		g2.setColor(Color.GREEN);
		g2.fill(ep2);
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent e) {
	
		if(selected==cp1){
			if(e.getX() < 450 && e.getY() < 450 && e.getX()>55 && e.getY() > 55){
				selected.x = e.getX()+10;
				selected.y = e.getY()+10;
				this.repaint();
			}
		}else if(selected==cp2){
			if(e.getX() < 450 && e.getY() < 450 && e.getX() > 55 && e.getY() > 55){
				cp2.x = e.getX()-10;
				cp2.y = e.getY()-10;
				this.repaint();
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		if(Math.abs(e.getX() - (int)cp1.x) < 30 && Math.abs(e.getY() - (int)cp1.y) < 30){
			if(e.getX() < 450 && e.getY() < 450 && e.getX()>55 && e.getY() > 55){
				selected = cp1;
			}
		}else if (Math.abs(e.getX() - (int)cp2.x) < 30 && Math.abs(e.getY() - (int)cp2.y) < 30){
			if(e.getX() < 450 && e.getY() < 450 && e.getX() > 55 && e.getY() > 55){
				this.repaint();
				selected = cp2;
			}
		}else{
			selected=null;
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
		selected = null;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {

	}





}
