package exception;

public class SmallFieldException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3336179185716296105L;
	
	private int winningLength;
	private int fieldSize;
	
	public SmallFieldException(int winningLength, int fieldSize) {
		this.fieldSize = fieldSize;
		this.winningLength = winningLength;
	}

	public String getMessage() {
		return "Field is smaller than winning size, " + "winningLength: " + winningLength +
			" fieldSize: " + fieldSize;

	}
}
