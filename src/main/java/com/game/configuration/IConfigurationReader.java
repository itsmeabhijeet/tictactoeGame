/**
 * 
 */
package com.game.configuration;

import com.game.board.IBoardConsoleSystem;
import com.game.tictactoe.TicTacToeConfiguration;

/**
 * @author Abhijeet Gupta This interface is used to read configuration of the
 *         board and players. Different class can implement it based on the way
 *         configuration is passed like property file or yml file or may be REST
 *         call.
 *
 */
public interface IConfigurationReader {

	TicTacToeConfiguration read(IBoardConsoleSystem consoleIO);

}
