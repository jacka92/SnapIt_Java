/*
 * 
 */
package timeWarpMode;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Queue;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeWarpModel.
 */
public class TimeWarpModel {


	//Initial and final coordinates
	/** The start. */
	private Point2D.Double start;
	
	/** The end. */
	private Point2D.Double end;

	//Control Points
	/** The cp1. */
	private Point2D.Double cp1 = new Point2D.Double(450, 450);
	
	/** The cp2. */
	private Point2D.Double cp2 = new Point2D.Double(50, 50);

	//Variables for calculation of firing events
	/** The time elapsed. */
	private static double timeElapsed;
	
	/** The snaps. */
	private int snaps =0;
	
	/** The time interval. */
	private double timeInterval;



	//Helper Functions
	/**
	 * Bezier point.
	 *
	 * @param y0 the y0
	 * @param y1 the y1
	 * @param y2 the y2
	 * @param y3 the y3
	 * @param t the t
	 * @return the double
	 */
	private double bezierPoint(double y0, double y1, double y2, double y3, double t){
		double y = (1 - t) * (1 - t) * (1 - t) * y0 + 3 * (1 - t) * (1 - t) * t * y1 + 3 * (1 - t) * t * t * y2 + t * t * t * y3;
		return y;
	}


	/**
	 * Gets the t.
	 *
	 * @param y0 the y0
	 * @param y1 the y1
	 * @param y2 the y2
	 * @param y3 the y3
	 * @param targetY the target y
	 * @param start the start
	 * @param end the end
	 * @return the t
	 */
	private double getT(double y0, double y1, double y2, double y3, double targetY, double start, double end){
		double tempT = (start + end) / 2;
		double tempY = bezierPoint(y0, y1, y2, y3, tempT);

		while (Math.abs(tempY - targetY) / targetY > 0.001){
			if (tempY > targetY){
				end = tempT;
			}
			else{
				start = tempT;
			}

			tempT = (start + end) / 2;
			tempY = bezierPoint(y0, y1, y2, y3, tempT);
		}

		return tempT;
	}

	/**
	 * Gets the snapping queue.
	 *
	 * @param P0 the p0
	 * @param P1 the p1
	 * @param P2 the p2
	 * @param P3 the p3
	 * @param numSnaps the num snaps
	 * @param totalTime the total time
	 * @return the snapping queue
	 */
	public Queue<Double> getSnappingQueue(Point2D P0, Point2D P1, Point2D P2, Point2D P3, int numSnaps, double totalTime){
		Queue<Double> pending = new LinkedList<Double>();

		double rangeY = P3.getY() - P0.getY();
		double rangeX = P3.getX() - P0.getX();

		for (int i = 0; i < numSnaps; i++) {
			double y = rangeY * (1.0 * i / (numSnaps - 1));

			double t = getT(P0.getY(), P1.getY(), P2.getY(), P3.getY(), y, 0, 1);

			double x = (1 - t) * (1 - t) * (1 - t) * P0.getX() + 3 * (1 - t) * (1 - t) * t * P1.getX() + 3 * (1 - t) * t * t * P2.getX() + t * t * t * P3.getX();

			double time = (x / rangeX) * totalTime;
			pending.add(time);

		}
		return pending;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public Point2D.Double getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(Point2D.Double start) {
		this.start = start;
	}

	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public Point2D.Double getEnd() {
		return end;
	}

	/**
	 * Sets the end.
	 *
	 * @param end the new end
	 */
	public void setEnd(Point2D.Double end) {
		this.end = end;
	}

	/**
	 * Gets the cp1.
	 *
	 * @return the cp1
	 */
	public Point2D.Double getCp1() {
		return cp1;
	}

	/**
	 * Sets the cp1.
	 *
	 * @param cp1 the new cp1
	 */
	public void setCp1(Point2D.Double cp1) {
		this.cp1 = cp1;
	}

	/**
	 * Gets the cp2.
	 *
	 * @return the cp2
	 */
	public Point2D.Double getCp2() {
		return cp2;
	}

	/**
	 * Sets the cp2.
	 *
	 * @param cp2 the new cp2
	 */
	public void setCp2(Point2D.Double cp2) {
		this.cp2 = cp2;
	}

	/**
	 * Gets the time elapsed.
	 *
	 * @return the time elapsed
	 */
	public static double getTimeElapsed() {
		return timeElapsed;
	}

	/**
	 * Sets the time elapsed.
	 *
	 * @param timeElapsed the new time elapsed
	 */
	public static void setTimeElapsed(double timeElapsed) {
		TimeWarpModel.timeElapsed = timeElapsed;
	}

	/**
	 * Gets the snaps.
	 *
	 * @return the snaps
	 */
	public int getSnaps() {
		return snaps;
	}

	/**
	 * Sets the snaps.
	 *
	 * @param snaps the new snaps
	 */
	public void setSnaps(int snaps) {
		this.snaps = snaps;
	}

	/**
	 * Gets the time interval.
	 *
	 * @return the time interval
	 */
	public double getTimeInterval() {
		return timeInterval;
	}

	/**
	 * Sets the time interval.
	 *
	 * @param timeInterval the new time interval
	 */
	public void setTimeInterval(double timeInterval) {
		this.timeInterval = timeInterval;
	}
	

}
