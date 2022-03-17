package model;

import java.util.HashSet;
import java.util.Iterator;

import exception.IllegalNumberException;

public class Field {
	private int height;
	private int[] squares; 

	public Field(int height) {
		this.height = height;

		squares = new int[height * height];

		for (int i = 0; i < squares.length; i++) {
			squares[i] = 0;
		}
	}

	/**
	 * Get set of numbers of the neighbours
	 * @param squareNumber The number to get neighbours of
	 * @return Set of neighbour ids
	 */
	public HashSet<Integer> getNeighbours(int squareNumber) {
		HashSet<Integer> returnList = new HashSet<>();

		int[] offset = {-1, 0, 1};

		for (int i = 0; i < offset.length; i++) {

			for (int k = 0; k < offset.length; k++) {
				try {
					returnList.add(getSquareNumber(getX(squareNumber) + offset[i], getY(squareNumber) + offset[k]));

				} catch (IllegalNumberException e) {
					e.getMessage();
				}
			}
			
		}

		returnList.remove(squareNumber);

		return returnList;
	}
	
	public boolean isNeighbour(int squareNumber, int neighbour) {
		int[] offset = {getX(squareNumber) - getX(neighbour),
				getY(squareNumber) - getY(neighbour)};
		
		int trueCounter = 0;
		
		
		for (int i = 0; i < offset.length; i++) {
			switch (offset[i]) {
			case -1:
			case 0:
			case 1:
				trueCounter++;
				break;
			default:
				break;
			}
		}
		
		return (trueCounter == offset.length);
	}
	
	public int getNoOfEmptyFields() {
		int retVal = 0;
		
		for (int i = 0; i < squares.length; i++) {
			if (squares[i] == 0) {
				retVal++;
			}
		}
		
		return retVal;
	}

	/**
	 * Get array of longest route of connecting squares
	 * @param squareNumber The location number
	 * @return array of connecting squares
	 * @throws IllegalNumberException 
	 */
	public int[] getLongestConnectingSquares(int squareNumber) throws IllegalNumberException {
		int value = getSquareValue(squareNumber);
		
		HashSet<Integer> squares = new HashSet<>();

		// check if the value is not default
		if (value != 0) {
			// get all neighbours
			int[] neighbours = getArrayFromSet(getNeighbours(squareNumber));

			HashSet<Integer> thisSquares = null;

			for (int i = 0; i < neighbours.length; i++) {
				int neighbour = neighbours[i];
				
				int offset = neighbour - squareNumber;

				// iterate if the value continues to be the same as original one
				try { 
					thisSquares = (getLongestLineWithSameValues(squareNumber, offset));
					
					if (isInArray(squareNumber - offset, neighbours)) {
						Iterator<Integer> it = getLongestLineWithSameValues(squareNumber, -offset).iterator();
						
						while (it.hasNext()) {
							thisSquares.add(it.next());
						}
					}
					
				} catch (IllegalNumberException e) {
					e.getMessage();
				}

				// update max length
				if (thisSquares.size() > squares.size()) {
					squares = thisSquares;
				}
			}
			
		}

		return getArrayFromSet(squares);

	}
	
	private boolean isInArray(int number, int[] array) {
		boolean found = false;
		int index = 0;
		
		while (index < array.length && !found) {
			if (array[index] == number) {
				found = true;
			}
			else {
				index++;
			}
		}
		
		return found;
	}
	
	private int[] getArrayFromSet(HashSet<Integer> set) {
		int[] retVal = new int[set.size()];
		
		Iterator<Integer> it = set.iterator();
		
		int index = 0;
		
		while (it.hasNext()) {
			retVal[index] = it.next();
			
			index++;
		}
		
		return retVal;
	}
	
	private HashSet<Integer> getLongestLineWithSameValues(int squareNumber, int offset) 
			throws IllegalNumberException {
		
		int neighbour = squareNumber + offset;

		int value = getSquareValue(squareNumber);
		int neighbourValue = getSquareValue(neighbour);
		
		HashSet<Integer> squares = new HashSet<>();

		while (squares.size() < height && neighbourValue == value && isNeighbour(squareNumber, neighbour)) {
			
			squareNumber = neighbour;
			
			squares.add(squareNumber);

			neighbour = neighbour + offset;
			
			try {
				neighbourValue = getSquareValue(neighbour);

			} catch (IllegalNumberException e) {
				// break the loop
				neighbourValue = 0;
			}
		}

		return squares;
	}

	/**
	 * Get value of the square number
	 * @param squareNumber The id number of the square
	 * @throws IllegalNumberException
	 * @return value of the square as int
	 */
	public int getSquareValue(int squareNumber) throws IllegalNumberException {
		if (!checkNumberBoundaries(squareNumber, 0, squares.length)) {
			throw new IllegalNumberException(squareNumber);
		}
		
		return squares[squareNumber];
	}


	public boolean setSquareToValue(int squareNumber, int value) throws IllegalNumberException {
		boolean retVal = false;

		if (getSquareValue(squareNumber) == 0) {
			setSquareValue(squareNumber, value);

			retVal = true;
		}

		return retVal;
	}

	private void setSquareValue(int squareNumber, int value) {
		squares[squareNumber] = value;
	}

	public int getX(int squareNumber) {
		return squareNumber % height;
	}

	public int getY(int squareNumber) {
		return squareNumber / height;
	}

	public int getSquareNumber(int x, int y) throws IllegalNumberException {

		if (!checkNumberBoundaries(x, 0, height)) {
			throw new IllegalNumberException(x);

		} else if (!checkNumberBoundaries(y, 0, height)) {
			throw new IllegalNumberException(y);
		}

		return height * y + x;
	}

	public boolean checkNumberBoundaries(int number, int bottom, int top) {
		boolean retVal = false;

		if (bottom <= number && number < top) {
			retVal = true;
		}

		return retVal;
	}

	/**
	 * Add an element to the field
	 * @param squareNumber The square number that the element is added to
	 * @param elementNo The id number of the element
	 * @return True if the element was added
	 */
	public boolean addElement(int squareNumber, int elementNo) {
		boolean retVal = false;

		if (squares[squareNumber] == 0) {
			squares[squareNumber] = elementNo;

			retVal = true;
		}

		return retVal;
	}

	/**
	 * Get squares 
	 *
	 * @return squares as int array
	 */
	public int[] getSquares() {
		return squares.clone();
	}
	
	public int getHeight() {
		return height;
	}

	public int getLength(int squareNumber) throws IllegalNumberException {
		
		return getLongestConnectingSquares(squareNumber).length ;
	}
}
