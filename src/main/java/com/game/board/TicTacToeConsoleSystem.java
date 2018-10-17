package com.game.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Abhijeet Gupta
 * implementation class of IBoardConsoleSystem
 *
 */
public class TicTacToeConsoleSystem implements IBoardConsoleSystem {

	protected BufferedReader bufferedReader;

	public TicTacToeConsoleSystem() {
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		bufferedReader = new BufferedReader(inputStreamReader);
	}

	@Override
	public void displayErrorMessage(String message) {
		System.err.println(message);
	}

	@Override
	public void drawBoardStructure(char[][] fieldValues) {
		drawColumnNumbers(fieldValues.length);
		for (int i = 0; i < fieldValues.length; i++) {
			drawRowBorder(fieldValues.length);
			drawRowFields(fieldValues.length, i, fieldValues);
		}
		drawRowBorder(fieldValues.length);
		drawColumnNumbers(fieldValues.length);
	}

	@Override
	public String getUserInput() throws IOException {
		String line = bufferedReader.readLine();
		return line;
	}

	@Override
	public void displayInfoMessage(String message) {
		System.out.println(message);
	}

	private void drawRowFields(int columns, int rowNumber, char[][] fieldValues) {
		StringBuilder line = new StringBuilder();
		line.append(rowNumber + 1);
		for (int i = 0; i < columns; i++) {
			char fieldValue = fieldValues[i][rowNumber];
			if (fieldValue == '\u0000') {
				fieldValue = ' ';
			}
			line.append("| ").append(fieldValue).append(" ");
		}
		line.append("|").append(rowNumber + 1);
		System.out.println(line);
	}

	private void drawRowBorder(int columns) {
		StringBuilder line = new StringBuilder(" ");
		for (int i = 0; i < columns; i++) {
			line.append("|---");
		}
		line.append("| ");
		System.out.println(line);
	}

	private void drawColumnNumbers(int columns) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < columns; i++) {
			line.append("   ").append(i + 1);
		}
		line.append("   ");
		System.out.println(line);
	}

}
