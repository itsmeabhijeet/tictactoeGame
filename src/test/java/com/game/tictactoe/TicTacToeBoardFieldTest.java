/**
 * 
 */
package com.game.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Abhijeet Gupta
 *
 */
public class TicTacToeBoardFieldTest {

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void constructore_InvalidSizeLessThan3_shouldReturnError() {

		// Assert

		TicTacToeBoardField field = new TicTacToeBoardField(2);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void constructore_InvalidSizeMoreThan10_shouldReturnError() {

		TicTacToeBoardField field = new TicTacToeBoardField(11);
	}

	@Test
	public void setMove_invalidMoves_ShouldReturnFalse() throws Exception {

		//Arrange
		TicTacToeBoardField ticTacToeGameField = new TicTacToeBoardField(3);
		//Act
		boolean set = ticTacToeGameField.setMove(3, 3, 'X');
		//Assert
		assertFalse(set);
	}

	@Test
	public void setMove_validMove_ShouldReturnTrue() throws Exception {
		TicTacToeBoardField ticTacToeGameField = new TicTacToeBoardField(3);
		boolean set = ticTacToeGameField.setMove(0, 0, 'X');
		assertEquals(ticTacToeGameField.positions[0][0], 'X');
		assertTrue(set);
	}
	
	@Test
	public void constructorCalled_ValidBoardSize_ShouldReturnSameSize() throws Exception {
		TicTacToeBoardField ticTacToeGameField = new TicTacToeBoardField(5);
        assertNotNull(ticTacToeGameField.getFields());
        assertEquals(5, ticTacToeGameField.getFields().length);
	}

}
