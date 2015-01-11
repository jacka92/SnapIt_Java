/*
 * 
 */
package timeWarpMode;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;


// TODO: Auto-generated Javadoc
/**
 * The Class TimeWarpController.
 */
public class TimeWarpController {

	/** The model. */
	TimeWarpModel model;
	
	/** The view. */
	TimeWarpView view;

	/**
	 * Instantiates a new time warp controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public TimeWarpController(TimeWarpModel model, TimeWarpView view){
		this.view = view;
		this.model = model;

		try{
		this.view.setTimeWarpListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setModelParameters();
				snap(model.getSnaps(), model.getTimeInterval());
			}
		});
		}catch(Exception e){System.out.println("I was here");}

	}
	
	/**
	 * Sets the model parameters.
	 */
	public void setModelParameters(){
		//set bezier parameters from view to model
		model.setCp1(view.bezier.cp1);
		model.setCp2(view.bezier.cp2);
		model.setStart(view.bezier.start);
		model.setEnd(view.bezier.end);
		model.setTimeInterval(view.getTimeInterval());
		model.setSnaps(view.getNumSnaps());
		
	}

	/** The timer. */
	Timer timer;
	
	/** The pending q. */
	private Queue<Double> pendingQ;

	/**
	 * Start snapping.
	 */
	private void startSnapping()
	{

		Point2D P0 = new Point2D.Double(model.getStart().getX(), model.getStart().getY());
		Point2D P3 = new Point2D.Double(model.getEnd().getX(), model.getEnd().getY());

		Point2D P1 = new Point2D.Double(model.getCp1().getX(), model.getCp1().getY());
		Point2D P2 = new Point2D.Double(model.getCp2().getX(), model.getCp2().getY());

		int interval = (int) (model.getTimeInterval() * 1000);

		//transform origin
		double P0y = 450 - P0.getY();  double P0x = P0.getX();
		double P1y = 450 - P1.getY();  double P1x = P1.getX();
		double P2y = 450 - P2.getY();  double P2x = P2.getX();
		double P3y = 400 - P3.getY();  double P3x = P3.getX();

		P0.setLocation(P0x, P0y);
		P1.setLocation(P1x, P1y);
		P2.setLocation(P2x, P2y);
		P3.setLocation(P3x, P3y);

		//compute pending tasks and start the timer
		pendingQ = model.getSnappingQueue(P0, P1, P2, P3, model.getSnaps(), interval);
		timer.schedule(new SnapTask(), 0, 150);
	}


	/**
	 * Snap.
	 *
	 * @param snaps the snaps
	 * @param interval the interval
	 * @return true, if successful
	 */
	public boolean snap(int snaps, double interval){
		TimeWarpModel.setTimeElapsed(0);
		
		if(timer == null){
			timer = new Timer();
			startSnapping();
			view.resetButton("Stop");
		}else{
			timer.cancel();
			timer = null;
			view.resetButton("Start");
		}
		return true;
	}
	
	/**
	 * Terminate.
	 */
	public void terminate(){
		if(timer!=null){
			timer.cancel();
			timer = null;
			view.resetButton("Start");
		}	
	}

	/**
	 * The Class SnapTask.
	 */
	class SnapTask extends TimerTask {

		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run() {

			TimeWarpModel.setTimeElapsed(TimeWarpModel.getTimeElapsed()+150);
			double timeElapsed = TimeWarpModel.getTimeElapsed();
			view.setTimeElapsedLabel(timeElapsed);
			int snaps = model.getSnaps();
			int snapsRemaining = pendingQ.size();

			if (snapsRemaining != 0){
				double timeOfSnap = pendingQ.peek();
				if (timeOfSnap <= timeElapsed){
					pendingQ.poll();
//					System.out.println("Click!! " + pendingQ.size() + " Fired at " + timeElapsed );
					TimeWarpView.updateCounter(snaps - pendingQ.size());
					Toolkit.getDefaultToolkit().beep();
				}
			}else{
				timeElapsed=0;
				if(timer != null){
					timer.cancel();
					timer = null;
					view.resetButton("Start");
				}

			}

		}
	}
}
