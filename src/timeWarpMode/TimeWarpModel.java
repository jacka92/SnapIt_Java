package timeWarpMode;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Queue;

public class TimeWarpModel {


	//Initial and final coordinates
	private Point2D.Double start;
	private Point2D.Double end;

	//Control Points
	private Point2D.Double cp1 = new Point2D.Double(450, 450);
	private Point2D.Double cp2 = new Point2D.Double(50, 50);

	//Variables for calculation of firing events
	private static double timeElapsed;
	private int snaps =0;
	private double timeInterval;



	//Helper Functions
	private double bezierPoint(double y0, double y1, double y2, double y3, double t){
		double y = (1 - t) * (1 - t) * (1 - t) * y0 + 3 * (1 - t) * (1 - t) * t * y1 + 3 * (1 - t) * t * t * y2 + t * t * t * y3;
		return y;
	}


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

	public Point2D.Double getStart() {
		return start;
	}

	public void setStart(Point2D.Double start) {
		this.start = start;
	}

	public Point2D.Double getEnd() {
		return end;
	}

	public void setEnd(Point2D.Double end) {
		this.end = end;
	}

	public Point2D.Double getCp1() {
		return cp1;
	}

	public void setCp1(Point2D.Double cp1) {
		this.cp1 = cp1;
	}

	public Point2D.Double getCp2() {
		return cp2;
	}

	public void setCp2(Point2D.Double cp2) {
		this.cp2 = cp2;
	}

	public static double getTimeElapsed() {
		return timeElapsed;
	}

	public static void setTimeElapsed(double timeElapsed) {
		TimeWarpModel.timeElapsed = timeElapsed;
	}

	public int getSnaps() {
		return snaps;
	}

	public void setSnaps(int snaps) {
		this.snaps = snaps;
	}

	public double getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(double timeInterval) {
		this.timeInterval = timeInterval;
	}
	

}
