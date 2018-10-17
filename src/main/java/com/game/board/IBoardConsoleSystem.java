/**
 * 
 */
package com.game.board;

import java.io.IOException;

/**
 * @author Abhijeet Gupta This interface is used for communication of the Program
 *         to console output like printing the board structure in console output.
 *
 */
public interface IBoardConsoleSystem {

	void drawBoardStructure(char[][] positions);

	String getUserInput() throws IOException;

	void displayErrorMessage(String message);

	void displayInfoMessage(String message);

}
