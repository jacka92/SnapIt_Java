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


public class Curve extends JComponent implements MouseMotionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4610052069056742687L;

	CubicCurve2D curve = new CubicCurve2D.Double();

	//Initial and final coordinates
	Point2D.Double start = new Point2D.Double(50, 450);
	Point2D.Double end = new Point2D.Double(450,50);

	//Control Points
	Point2D.Double cp1 = new Point2D.Double(450, 450);
	Point2D.Double cp2 = new Point2D.Double(50, 50);
	Point2D.Double selected;

	Ellipse2D ep1;
	Ellipse2D ep2;

	public Curve(){
		super();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);

	}

	public Dimension getPreferredSize()
	{
		return new Dimension(500, 500);
	}

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

	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

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

	public void mouseReleased(MouseEvent e) {
		selected = null;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}





}
