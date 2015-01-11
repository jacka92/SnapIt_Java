/*
 * 
 */
package application;

// TODO: Auto-generated Javadoc
/**
 * The Enum StatusEnum.
 */
public enum StatusEnum {
	
	/** The back. */
	BACK("back"), 
 /** The home. */
 HOME("home");

	/** The op. */
	private final String op;

	/**
	 * Instantiates a new status enum.
	 *
	 * @param op the op
	 */
	StatusEnum(String op){
		this.op = op;
	}

	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	String getOperation(){
		return op;
	}
}
