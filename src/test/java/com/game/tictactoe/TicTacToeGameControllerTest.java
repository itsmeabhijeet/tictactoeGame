/**
 * 
 */
package com.game.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.game.board.IBoardConsoleSystem;
import com.game.player.config.PlayerConfiguration;

/**
 * @author Abhijeet Gupta
 *
 */
public class TicTacToeGameControllerTest {

	private IBoardConsoleSystem consoleSystem;
	private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private PrintStream sysErr;

	@Before
	public void setUp() {
		sysErr = System.err;
		System.setErr(new PrintStream(errContent));
		consoleSystem = mock(IBoardConsoleSystem.class);
	}

	@SuppressWarnings("unused")
	@Test
	public void constructorCalled_NullValuePassed_ShouldReturnErrorMessage() throws Exception {
		// arrange
		// act
		TicTacToeGameController controller = new TicTacToeGameController(null);
		// assert
		assertThat(errContent.toString()).contains("IO System was not set using SystemIO as default");
	}

	@Test
	public void constructorCalled_validMockedObject_ShouldReturnSameValidObject() throws Exception {
		// arrange
		// act
		TicTacToeGameController controller = new TicTacToeGameController(consoleSystem);
		// assert
		assertThat(controller.consoleSystem).isEqualTo(consoleSystem);
	}

	@Test
	public void constructorCalled_defaultConfigCalled_ShouldReturnDefaultConfiguration() throws Exception {
		TicTacToeGameController controller = new TicTacToeGameController(consoleSystem);
		controller.useDefaults();

		assertThat(controller.gameField).isNotNull();
		assertThat(controller.gameField.positions).hasSize(4);
	}

	@Test
	public void constructorCalled_ValidDataPassed_ShouldReturnSameSizeOFPlayers() throws Exception {

		// Arrange
		// Act
		TicTacToeGameController controller = new TicTacToeGameController(consoleSystem);
		ArrayList<PlayerConfiguration> players = new ArrayList<>();
		players.add(new PlayerConfiguration(PlayerConfiguration.HUMAN, 'H'));
		players.add(new PlayerConfiguration(PlayerConfiguration.COMPUTER, 'C'));
		controller.configureGame(new TicTacToeConfiguration(3, players));
		/// Assert
		assertThat(controller.gameField).isNotNull();
		assertThat(controller.players).isNotNull().hasSize(2);
	}

	@Test
	public void constructorCalled_NullData_ShouldReturnDefaultValues() throws Exception {

		// Arrange
		TicTacToeGameController controller = new TicTacToeGameController(consoleSystem);
		// Act
		controller.configureGame(null);
		// Assert
		assertThat(controller.players).hasSize(3);

	}

	@After
	public void tearDown() {
		System.setErr(sysErr);
	}
}
