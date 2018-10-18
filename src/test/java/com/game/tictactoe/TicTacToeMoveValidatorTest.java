/**
 * 
 */
package com.game.tictactoe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Abhijeet Gupta
 *
 */
public class TicTacToeMoveValidatorTest {

	@Test
	public void validateMovesPossible_positionsEmpty_ShouldReturnTrue() {
		// Arrange
		// Act
		char[][] positions = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != 0 && j != 0) {
					positions[i][j] = 'X';
				}
				// Assert
				assertTrue(TicTacToeMoveValidator.validateMovesPossible(positions));
			}
		}
	}

	@Test
	public void validateWinMove_onlyOneMovePlayed_ShouldReturnFalse() throws Exception {
		assertFalse(TicTacToeMoveValidator.validateWinMove('X', new char[3][3]));
	}

	@Test
	public void validateWinMove_ValidDataSetAtDiagonal_ShouldRetunrTrue() throws Exception {
		char[][] fieldsDiagonalRight = { { 'X', 'O', ' ', ' ' }, { 'W', 'X', 'O', ' ' }, { ' ', 'W', 'X', 'O' },
				{ ' ', ' ', 'W', 'X' } };
		assertTrue(TicTacToeMoveValidator.validateWinMove('X', fieldsDiagonalRight));
	}
	
	@Test
	public void validateWinMove_ValidDataSetAtColumn_ShouldReturnTrue() throws Exception {
		char[][] fields = {
                {'X',' ','W',' '},
                {'X','O','W','T'},
                {'X','O','W','T'},
                {'X','O',' ','T'}
        };
        assertTrue(TicTacToeMoveValidator.validateWinMove('X', fields));
	}

	@Test
	public void validateMovePossible_AllPositionsOccupied_ShouldReturnFalse() throws Exception {
		char[][] fields = new char[2][2];
		fields[0][0] = 'X';
		fields[1][0] = 'X';
		fields[0][1] = 'X';
		fields[1][1] = 'X';
		assertFalse(TicTacToeMoveValidator.validateMovesPossible(fields));
	}

	@Test
	public void validateWinMove_ValidMovesOnRow_ShouldReturnTrue() throws Exception {
		char[][] fields = { { 'X', 'X', 'X', 'X' }, { ' ', 'O', 'O', 'O' }, { 'W', 'W', 'W', ' ' },
				{ 'T', 'T', 'T', ' ' } };
		assertTrue(TicTacToeMoveValidator.validateWinMove('X', fields));

	}

}
