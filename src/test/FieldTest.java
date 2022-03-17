package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.IllegalNumberException;
import model.Field;

class FieldTest {
	private Field field;
	
	@BeforeEach
	void setUp() {
		field = new Field(3);
	}

	@Test
	void testGetSquareNumber() {
		int squareNumber = 7;
		
		int x = field.getX(squareNumber);
		int y = field.getY(squareNumber);
		
		System.out.println(x + " " + y);
		
		
		try {
			assertEquals(squareNumber, field.getSquareNumber(x, y));
		} catch (IllegalNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testGetNeighbours() {
		int squareNumber = 0;
		
		HashSet<Integer> neighbours = field.getNeighbours(squareNumber);
		
		for (int element : neighbours) {
			System.out.println(element);
		}
		
	}
	
	@Test
	void testIsNeighbour3and0shouldReturnTrue() {
		int squareNumber = 0;
		int neighbour = 3;
		
		boolean result = field.isNeighbour(squareNumber, neighbour);
		
		assertTrue(result);
		
	}
	
	@Test
	void testIsNeighbour0and1shouldReturnTrue() {
		int squareNumber = 0;
		int neighbour = 1;
		
		boolean result = field.isNeighbour(squareNumber, neighbour);
		
		assertTrue(result);
		
	}
	
	@Test
	void testIsNeighbour2and3shouldReturnFalse() {
		int squareNumber = 2;
		int neighbour = 3;
		
		boolean result = field.isNeighbour(squareNumber, neighbour);
		
		assertFalse(result);
		
	}

}
