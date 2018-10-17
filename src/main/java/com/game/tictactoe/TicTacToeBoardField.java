/**
 * 
 */
package com.game.tictactoe;

/**
 * @author Abhijeet Gupta
 *
 */
public class TicTacToeBoardField {

	protected char[][] positions;

	TicTacToeBoardField(int size) {
		if (size < 3) {
			throw new IllegalArgumentException("Size of the board must be larger or equal 3");
		}
		if (size > 10) {
			throw new IllegalArgumentException("Size of the board must be smaller or equal 10");
		}
		positions = new char[size][size];
	}

	char[][] getFields() {
		return positions.clone();
	}

	boolean setMove(int x, int y, char symbol) {
		boolean moveMade = false;
		if (x < positions.length && y < positions[x].length) {
			if (positions[x][y] == '\0') {
				positions[x][y] = symbol;
				moveMade = true;
			}
		}
		return moveMade;
	}
}
