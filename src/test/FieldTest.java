package test;

import static org.junit.Assert.assertEquals;

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

}
