package exception;

public class IllegalNumberException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -621468938910633357L;
	private int number;

	public IllegalNumberException(int number) {
		this.number = number;
	}

	public String getMessage() {
		return "Illegal number: " + number;
	}
}
