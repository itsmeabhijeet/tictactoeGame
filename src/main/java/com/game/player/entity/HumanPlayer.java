/**
 * 
 */
package com.game.player.entity;

import java.awt.Point;
import java.io.IOException;

import com.game.board.IBoardConsoleSystem;

/**
 * @author Abhijeet Gupta
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(char symbol) {
		super(symbol);
	}

	@Override
	public Point makeMove(IBoardConsoleSystem consoleSystem, char[][] positions) throws IOException {
		String input = consoleSystem.getUserInput();
		input = input.trim();
		String[] inputString = input.split(",");
		Point result;
		try {
			int x = Integer.parseInt(inputString[0]) - 1;
			int y = Integer.parseInt(inputString[1]) - 1;
			result = new Point(x, y);
			if (x < positions.length && y < positions[x].length) {
				if (positions[x][y] != '\0') {
					consoleSystem.displayErrorMessage("field is already occupied, please choose a different one");
					result = makeMove(consoleSystem, positions);
				}
			} else {
				consoleSystem.displayErrorMessage("field is not in reach, please choose a different one");
				result = makeMove(consoleSystem, positions);
			}
		} catch (NumberFormatException e) {
			consoleSystem.displayErrorMessage("wrong input format please enter [row],[column] i.e. 2,3");
			result = makeMove(consoleSystem, positions);
		} catch (ArrayIndexOutOfBoundsException e1) {
			consoleSystem.displayErrorMessage("wrong input format please enter [row],[column] i.e. 2,3");
			result = makeMove(consoleSystem, positions);
		}
		return result;
	}

}
