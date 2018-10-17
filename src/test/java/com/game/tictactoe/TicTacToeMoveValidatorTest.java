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
		
		char[][] positions = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i!=0 && j!=0){
                	positions[i][j]='X';
                }
                assertTrue(TicTacToeMoveValidator.validateMovesPossible(positions));
            }
        }
	}
	
	@Test
	public void validateWinMove_onlyOneMovePlayed_ShouldReturnFalse() throws Exception {
		assertFalse(TicTacToeMoveValidator.validateWinMove('X', new char[3][3]));
	}
	
	@Test
	public void validateWinMove_ValidMoves_ShouldReturnTrue() throws Exception {
		char[][] fields = {
                {'X','X','X','X'},
                {' ','O','O','O'},
                {'W','W','W',' '},
                {'T','T','T',' '}
        };
        assertTrue(TicTacToeMoveValidator.validateWinMove('X', fields));
		
		
	}
	

}
