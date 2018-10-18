/**
 * 
 */
package com.game.configuration;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.game.board.IBoardConsoleSystem;
import com.game.tictactoe.TicTacToeConfiguration;

/**
 * @author Abhijeet Gupta
 *
 */
public class PropertyFileConfigReaderTest {

	@Mock
	private IBoardConsoleSystem consoleSystem;

	@Before
	public void setUp() {
		consoleSystem = mock(IBoardConsoleSystem.class);
	}

	@Test
	public void read_ValidInput_ShouldReturnSameData() {
		// Arrange
		Properties properties = new Properties();
		properties.setProperty(PropertyKeys.PLAYGROUND_SIZE, "3");
		properties.setProperty(PropertyKeys.PLAYER_A_SYMBOL, "A");
		properties.setProperty(PropertyKeys.PLAYER_B_SYMBOL, "B");
		properties.setProperty(PropertyKeys.PLAYER_COMPUTER_SYMBOL, "C");

		// Act
		PropertyFileConfigReader configReader = new PropertyFileConfigReader(properties);
		TicTacToeConfiguration config = configReader.read(consoleSystem);

		// Assert
		assertEquals(3, config.getFieldSize());
	}

	@Test
	public void read_passNullConfiguration_shouldPrintOnceErrorMessage() throws Exception {

		//Arrange
		PropertyFileConfigReader configReader = new PropertyFileConfigReader(null);
		//Act
		@SuppressWarnings("unused")
		TicTacToeConfiguration config = configReader.read(consoleSystem);

		//Assert
		verify(consoleSystem, atLeastOnce()).displayErrorMessage(any());
	}

	@Test
	public void read_emptyPropertFile_ShouldReturnDefaultBoardSize() throws Exception {
		//Arrange
		Properties properties = new Properties();
		//Act
		PropertyFileConfigReader reader = new PropertyFileConfigReader(properties);
		int playgroundSize = reader.readPlaygroundSize(consoleSystem);
		//Assert
		assertEquals(PropertyFileConfigReader.DEFAULT_PLAYGROUND_SIZE, playgroundSize);
		verify(consoleSystem, atLeastOnce()).displayErrorMessage(any());
	}

	@Test
	public void read_wrongBoardSizeSet_shouldReturnErrorMessage() throws Exception {

		Properties properties = new Properties();
		properties.setProperty(PropertyKeys.PLAYGROUND_SIZE, "XL");
		PropertyFileConfigReader reader = new PropertyFileConfigReader(properties);
		int playgroundSize = reader.readPlaygroundSize(consoleSystem);
		assertEquals(PropertyFileConfigReader.DEFAULT_PLAYGROUND_SIZE, playgroundSize);
		verify(consoleSystem, atLeastOnce()).displayErrorMessage(any());

	}

	@SuppressWarnings("unused")
	@Test
	public void read_playerWithSameName_ShouldReturnErrorMessage() throws Exception {

		Properties properties = new Properties();
		properties.setProperty(PropertyKeys.PLAYER_A_SYMBOL, "A");
		properties.setProperty(PropertyKeys.PLAYER_B_SYMBOL, "A");
		PropertyFileConfigReader reader = new PropertyFileConfigReader(properties);
		TicTacToeConfiguration configuration = reader.read(consoleSystem);

		verify(consoleSystem, atLeastOnce()).displayErrorMessage(any());

	}

}
