/*
 * 
 */
package application;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving status events.
 * The class that is interested in processing a status
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addStatusListener<code> method. When
 * the status event occurs, that object's appropriate
 * method is invoked.
 *
 * @see StatusEvent
 */
public interface StatusListener {
	
	/**
	 * Status performed.
	 *
	 * @param op the op
	 */
	public void StatusPerformed(String op);
}
