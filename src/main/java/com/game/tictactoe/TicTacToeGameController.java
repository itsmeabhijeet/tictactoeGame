/**
 * 
 */
package com.game.tictactoe;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.game.board.IBoardConsoleSystem;
import com.game.board.TicTacToeConsoleSystem;
import com.game.player.config.PlayerConfiguration;
import com.game.player.entity.Player;
import com.game.player.factory.PlayerFactory;

/**
 * @author Abhijeet Gupta
 *
 */
public class TicTacToeGameController {

	IBoardConsoleSystem consoleSystem;
	TicTacToeBoardField gameField;
	List<Player> players;

	public TicTacToeGameController(IBoardConsoleSystem consoleIO) {
		this.consoleSystem = consoleIO;
		if (consoleIO == null) {
			this.consoleSystem = new TicTacToeConsoleSystem();
			this.consoleSystem.displayErrorMessage("IO System was not set using SystemIO as default");
		}
	}

	public void configureGame(TicTacToeConfiguration configuration) {
		if (configuration != null) {
			gameField = new TicTacToeBoardField(configuration.getFieldSize());
			players = new ArrayList<>();
			for (Iterator<PlayerConfiguration> it = configuration.getPlayers(); it.hasNext();) {
				PlayerConfiguration playerConfig = it.next();
				players.add(PlayerFactory.getPlayer(playerConfig));
			}
		} else {
			consoleSystem.displayErrorMessage("Game configuration not set configure now with defaults");
			useDefaults();
		}
	}

	void useDefaults() {
		gameField = new TicTacToeBoardField(4);
		players = new ArrayList<>();
		players.add(PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.HUMAN, 'X')));
		players.add(PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.HUMAN, 'O')));
		players.add(PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.COMPUTER, '@')));
	}

	public void play() {
		if (gameField == null || players == null || players.isEmpty()) {
			consoleSystem.displayErrorMessage("Game configuration not set configure now with defaults");
			useDefaults();
		}
		Collections.shuffle(players, new Random(System.currentTimeMillis()));

		Iterator<Player> playerIterator = players.iterator();

		while (TicTacToeMoveValidator.validateMovesPossible(gameField.getFields())) {
			this.consoleSystem.drawBoardStructure(gameField.getFields());
			if (!playerIterator.hasNext()) {
				playerIterator = players.iterator();
			}
			Player currentPlayer = playerIterator.next();
			if (!turn(currentPlayer)) {
				return;
			}
			if (TicTacToeMoveValidator.validateWinMove(currentPlayer.getSymbol(), gameField.getFields())) {
				consoleSystem.displayInfoMessage(
						"Congratulations the player " + currentPlayer.getSymbol() + " won the game");
				return;
			}
		}
		consoleSystem.displayInfoMessage("Draw nobody won the game");
	}

	boolean turn(Player currentPlayer) {
		try {
			Point move;
			do {
				consoleSystem.displayInfoMessage("Player " + currentPlayer.getSymbol() + " make your move");
				move = currentPlayer.makeMove(consoleSystem, gameField.getFields());
			} while (!gameField.setMove(move.x, move.y, currentPlayer.getSymbol()));
		} catch (IOException e) {
			consoleSystem.displayErrorMessage(
					"Player " + currentPlayer.getSymbol() + " has problems with input the game will " + "be canceled");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
