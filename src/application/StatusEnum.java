package application;

public enum StatusEnum {
	BACK("back"), HOME("home");

	private final String op;

	StatusEnum(String op){
		this.op = op;
	}

	String getOperation(){
		return op;
	}
}
