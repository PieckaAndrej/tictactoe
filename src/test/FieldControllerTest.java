package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.FieldController;
import exception.IllegalNumberException;
import exception.SmallFieldException;

class FieldControllerTest {
	
	private FieldController fieldCtrl;
	
	@BeforeEach
	void setUp() {
		try {
			fieldCtrl = new FieldController(3);
		} catch (IllegalNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Test
	void testGetLenghtDiagonal() throws SmallFieldException {
		fieldCtrl.initField(3);
		int lenght = 0;
		
		int[] values = {0, 4, 8};
		
		try {
			for (int i = 0; i < values.length; i++) {
				fieldCtrl.addElement(values[i], 1);
			}
			
			for (int i = 0; i < values.length; i++) {
				lenght = fieldCtrl.getLength(values[i]);
				
				assertEquals(2, lenght);
			}
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testGetLenghtVertical() throws SmallFieldException {
		fieldCtrl.initField(3);
		int lenght = 0;
		
		int[] values = {0, 3, 6};
		
		try {
			for (int i = 0; i < values.length; i++) {
				fieldCtrl.addElement(values[i], 1);
			}
			
			for (int i = 0; i < values.length; i++) {
				lenght = fieldCtrl.getLength(values[i]);
				
				assertEquals(2, lenght);
			}
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
		
	}

	
	@Test
	void testGetLenghtHorizontal() throws SmallFieldException {
		fieldCtrl.initField(3);
		int lenght = 0;
		
		int[] values = {0, 1, 2};
		
		try {
			for (int i = 0; i < values.length; i++) {
				fieldCtrl.addElement(values[i], 1);
			}
			
			for (int i = 0; i < values.length; i++) {
				lenght = fieldCtrl.getLength(values[i]);
				
				assertEquals(2, lenght);
			}
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testGetLenghtVerticalOutOfBounds() throws SmallFieldException {
		fieldCtrl.initField(3);
		int lenght = 0;
		
		int[] values = {2, 3, 4};
		
		try {
			for (int i = 0; i < values.length; i++) {
				fieldCtrl.addElement(values[i], 1);
			}
			
			for (int i = 0; i < values.length; i++) {
				lenght = fieldCtrl.getLength(values[i]);
				
				System.out.println("Length" + lenght + " value" + values[i]);
				
				assertEquals(1, lenght);
			}
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testTwoValues() throws SmallFieldException {
		fieldCtrl.initField(3);
		int lenght = 0;
		
		int[] values1 = {0, 3};
		int[] values2 = {6, 7};
		
		try {
			for (int i = 0; i < values1.length; i++) {
				fieldCtrl.addElement(values1[i], 1);
			}
			
			for (int i = 0; i < values2.length; i++) {
				fieldCtrl.addElement(values2[i], 2);
			}
			
			for (int i = 0; i < values1.length; i++) {
				lenght = fieldCtrl.getLength(values1[i]);
				
				assertEquals(1, lenght);
			}
			
			for (int i = 0; i < values2.length; i++) {
				lenght = fieldCtrl.getLength(values2[i]);
				
				assertEquals(1, lenght);
			}
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPlayingPlayerOneWinning() throws SmallFieldException {
		fieldCtrl.initField(3);
		
		try {
			assertEquals(0, fieldCtrl.place(0));
			assertEquals(0, fieldCtrl.place(4));
			assertEquals(0, fieldCtrl.place(3));
			assertEquals(0, fieldCtrl.place(8));
			assertEquals(1, fieldCtrl.place(6));
			
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPlayingDraw() throws SmallFieldException {
		fieldCtrl.initField(3);
		
		try {
			assertEquals(0, fieldCtrl.place(4));
			assertEquals(0, fieldCtrl.place(0));
			assertEquals(0, fieldCtrl.place(8));
			assertEquals(0, fieldCtrl.place(2));
			assertEquals(0, fieldCtrl.place(6));
			assertEquals(0, fieldCtrl.place(7));
			assertEquals(0, fieldCtrl.place(1));
			assertEquals(0, fieldCtrl.place(3));
			assertEquals(-1, fieldCtrl.place(5));
			
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPlayingSameSquare() throws SmallFieldException {
		fieldCtrl.initField(3);
		
		try {
			assertEquals(0, fieldCtrl.place(0));
			assertEquals(0, fieldCtrl.place(4));
			assertEquals(0, fieldCtrl.place(1));
			assertEquals(-2, fieldCtrl.place(1));
			assertEquals(0, fieldCtrl.place(3));
			assertEquals(1, fieldCtrl.place(2));
			
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPlaying5x5() throws SmallFieldException {
		fieldCtrl.initField(5);
		
		try {
			assertEquals(0, fieldCtrl.place(0));
			assertEquals(0, fieldCtrl.place(4));
			assertEquals(0, fieldCtrl.place(1));
			assertEquals(-2, fieldCtrl.place(1));
			assertEquals(0, fieldCtrl.place(3));
			assertEquals(1, fieldCtrl.place(2));
			
			
		} catch (IllegalNumberException e) {
			e.printStackTrace();
		}
	}

}
