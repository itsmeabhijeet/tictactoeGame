/**
 * 
 */
package com.game.tictactoe;

import org.junit.Test;

/**
 * @author Abhijeet Gupta
 *
 */
public class TicTacToeBoardFieldTest {

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void constructore_InvalidSizeLessThan3_shouldReturnError() {

		//Assert
	
		TicTacToeBoardField field= new TicTacToeBoardField(2);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void constructore_InvalidSizeMoreThan10_shouldReturnError() {

		TicTacToeBoardField field= new TicTacToeBoardField(11);
	}

}
