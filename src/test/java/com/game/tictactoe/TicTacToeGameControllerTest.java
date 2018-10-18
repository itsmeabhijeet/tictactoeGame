/**
 * 
 */
package com.game.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.game.board.IBoardConsoleSystem;

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
		//arrange
		//act
		TicTacToeGameController controller = new TicTacToeGameController(null);
		//assert
		assertThat(errContent.toString()).contains("IO System was not set using SystemIO as default");
	}

	@Test
	public void constructorCalled_validMockedObject_ShouldReturnSameValidObject() throws Exception {
		//arrange
		//act
		TicTacToeGameController controller = new TicTacToeGameController(consoleSystem);
		//assert
		assertThat(controller.consoleSystem).isEqualTo(consoleSystem);
	}
	
	@Test
	public void constructorCalled_defaultConfigCalled_ShouldReturnDefaultConfiguration() throws Exception {
		TicTacToeGameController controller = new TicTacToeGameController(consoleSystem);
		controller.useDefaults();
		
		assertThat(controller.gameField).isNotNull();
		assertThat(controller.gameField.positions).hasSize(4);
		
	}

	@After
	public void tearDown() {
		System.setErr(sysErr);
	}
}
