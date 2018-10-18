/**
 * 
 */
package com.game.tictactoe;

/**
 * @author Abhijeet Gupta
 *
 */
public class TicTacToeMoveValidator {

	public static boolean validateMovesPossible(char[][] positions) {
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions[i].length; j++) {
				if (positions[i][j] == '\0') {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean validateWinMove(char symbol, char[][] positions) {
		return validateRowWin(symbol, positions) || validateColumnWin(symbol, positions)
				|| validateDiagonalWin(symbol, positions);
	}

	private static boolean validateDiagonalWin(char symbol, char[][] positions) {
		int count = 0;
		// check diagonal on positions (0,0), (1,1) ...
		for (int i = 0; i < positions.length; i++) {
			if (positions[i][i] == symbol)
				count++;
			else
				count = 0;
			if (count == positions.length) {
				return true;
			}
		}
		// check the other diagonal
		int i = 0;
		for (int j = positions.length-1; j >= 0; j--) {
			if (positions[i++][j] == symbol)
				count++;
			else
				count = 0;
			if (count == positions.length) {
				return true;
			}
		}
		return false;
	}

	private static boolean validateColumnWin(char symbol, char[][] positions) {
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions[i].length; j++) {
				int hitcount = 0;
				for (int k = 0; k < positions.length; k++) {
					if (positions[k][j] == symbol) {
						hitcount++;
					} else {
						hitcount = 0;
					}
					if (hitcount == positions.length) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean validateRowWin(char symbol, char[][] positions) {
		for (int i = 0; i < positions.length; i++) {
			int hitCount = 0;
			for (int j = 0; j < positions[i].length; j++) {
				if (positions[i][j] == symbol) {
					hitCount++;
				} else {
					hitCount = 0;
				}
				if (hitCount == positions.length) {
					return true;
				}
			}
		}
		return false;
	}
}
