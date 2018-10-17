/**
 * 
 */
package com.game.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Abhijeet Gupta
 *
 */
public class TicTacToeConsoleSystemTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private PrintStream sysOut;
	private PrintStream sysErr;
	private InputStream inputStream;

	TicTacToeConsoleSystem consoleSystem = null;

	@Before
	public void setup() {

		sysOut = System.out;
		System.setOut(new PrintStream(outContent));

		sysErr = System.err;
		System.setErr(new PrintStream(errContent));

		inputStream = System.in;

	}

	@Test
	public void displayErrorMessage_ErrorParameter_ShouldReturnError() throws Exception {
		// Arrange
		consoleSystem = new TicTacToeConsoleSystem();

		// Act
		consoleSystem.displayErrorMessage("Error");

		// Assert
		assertTrue(errContent.toString().contains("Error"));
	}

	@Test
	public void getUserInput_readFromBuffer_ShouldReturnSameInput() throws Exception {

		// Arrange
		consoleSystem = new TicTacToeConsoleSystem();

		// Act
		consoleSystem.bufferedReader = mock(BufferedReader.class);
		when(consoleSystem.bufferedReader.readLine()).thenReturn("input1", "input2");
		String input = consoleSystem.getUserInput();

		// Assert
		assertEquals("input1", input);

		input = consoleSystem.getUserInput();

		// Assert
		assertEquals("input2", input);
	}

	@Test
	public void displayInfoMessage_InfoParameterPassed_ShouldReturnInfo() throws Exception {
		// Arrange
		consoleSystem = new TicTacToeConsoleSystem();

		// Act
		consoleSystem.displayInfoMessage("Info");
		// Assert
		assertTrue(outContent.toString().contains("Info"));
	}

	@Test
	public void drawBoardStructure_Pass3_3Config_ShouldReturnSameConfigBoard() throws Exception {

		// Arrange
		consoleSystem = new TicTacToeConsoleSystem();

		char[][] fields = new char[3][3];
		ByteArrayOutputStream expectedOutput = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(expectedOutput);
		ps.println("   1   2   3   ");
		ps.println(" |---|---|---| ");
		ps.println("1|   |   |   |1");
		ps.println(" |---|---|---| ");
		ps.println("2|   |   |   |2");
		ps.println(" |---|---|---| ");
		ps.println("3|   |   |   |3");
		ps.println(" |---|---|---| ");
		ps.println("   1   2   3   ");

		// Act
		consoleSystem.drawBoardStructure(fields);
		// Assert
		assertEquals(expectedOutput.toString(), outContent.toString());
	}

	@After
	public void tearDown() {
		System.setOut(sysOut);
		System.setErr(sysErr);
		System.setIn(inputStream);
	}

}
