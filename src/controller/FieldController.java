package controller;

import exception.IllegalNumberException;
import exception.SmallFieldException;
import model.Field;

public class FieldController {
	private Field field;
	private int winningLenght;
	
	private int numberOfPlayers;
	private int currentPlayer;

	public FieldController(int winningLenght) throws IllegalNumberException {
		setWinningLenght(winningLenght);
		
		numberOfPlayers = 2;
		currentPlayer = 0;
	}

	public void initField(int size) throws SmallFieldException {
		if (size < winningLenght) {
			throw new SmallFieldException(winningLenght, size);
		}
		
		field = new Field(size);
	}
	
	/**
	 * Place an element to the field
	 * @param squareNumber The location of placed element
	 * @return -2 if the square is already occupied 
	 * 		-1 if the result is draw
	 * 		0 if the element was placed
	 * 		other number is an id of the player who won
	 * @throws IllegalNumberException
	 */
	public int place(int squareNumber) throws IllegalNumberException {
		int retVal = -2;
		
		if (addElement(squareNumber, currentPlayer + 1)) {
			retVal = 0;
			
			if (getLength(squareNumber) == winningLenght - 1) {
				retVal = currentPlayer + 1;
			} else {
				if (field.getNoOfEmptyFields() == 0) {
					retVal = -1;
				}
			}
			
			currentPlayer = (currentPlayer + 1) % numberOfPlayers;
		}
		
		return retVal;
	}

	public boolean addElement(int squareNumber, int value) throws IllegalNumberException {
		return field.setSquareToValue(squareNumber, value);
	}

	public int getLength(int squareNumber) throws IllegalNumberException {
		return field.getLength(squareNumber);
	}
	
	public int[] getLine(int squareNumber) throws IllegalNumberException {
		return field.getLongestConnectingSquares(squareNumber);
	}

	/**
	 * Get winningLenght.
	 *
	 * @return winningLenght as int.
	 */
	public int getWinningLenght() {
	    return winningLenght;
	}

	/**
	 * Set winningLenght.
	 *
	 * @param winningLenght the value to set.
	 * @throws IllegalNumberException 
	 */
	public void setWinningLenght(int winningLenght) throws IllegalNumberException {
		if (winningLenght < 3) {
			throw new IllegalNumberException(winningLenght);
		}
	    this.winningLenght = winningLenght;
	}

	/**
	 * Get field.
	 *
	 * @return field as Field.
	 */
	public Field getField() {
	    return field;
	}
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void setNumberOfPlayers(int numberOfPlayers) throws IllegalNumberException {
		if (numberOfPlayers < 2) {
			throw new IllegalNumberException(numberOfPlayers);
		}
		
		this.numberOfPlayers = numberOfPlayers;
	}
	
	public int getSize() {
		return field.getHeight();
	}
}
