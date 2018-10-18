package com.game.player.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.awt.Point;

import org.junit.Test;

import com.game.board.IBoardConsoleSystem;

public class ComputerPlayerTest {

	@Test
	public void computerPlayer_constructorCalled_shouldReturnValidObject() {
		//Arrange
		//Act
		ComputerPlayer p = new ComputerPlayer('A');
		//assert
		assertEquals(p.getSymbol(), 'A');
	}

	@Test
	public void makeMove_validPostions_ShouldReturnAvalidNextMove() throws Exception {

		//Arrange
		//Act
		ComputerPlayer p = new ComputerPlayer('P');
		char[][] fields = new char[2][2];
		fields[0][0] = 'x';
		fields[1][0] = 'x';
		fields[1][1] = 'x';
		Point point = p.makeMove(mock(IBoardConsoleSystem.class), fields);
		//Assert
		assertEquals(new Point(0, 1), point);
	}
}
