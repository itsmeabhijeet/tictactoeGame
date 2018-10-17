/**
 * 
 */
package com.game.player.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.game.board.IBoardConsoleSystem;

/**
 * @author Abhijeet Gupta
 *
 */
public class HumanPlayerTest {

	@Test
	public void HumanPlayer_constructorCalledWithValidInput_ShouldReturnValidData() {

		HumanPlayer p = new HumanPlayer('P');
		assertEquals(p.getSymbol(), 'P');
	}
	
	@Test
	public void makemove_invalidInput_ShouldReturnErrorMessage() throws Exception {
		
		IBoardConsoleSystem mockConsole = mock(IBoardConsoleSystem.class);
        HumanPlayer humanPlayer = new HumanPlayer('P');
        when(mockConsole.getUserInput()).thenReturn("invalid","1,1");
        humanPlayer.makeMove(mockConsole, new char[1][1]);
        verify(mockConsole, times(2)).getUserInput();
        verify(mockConsole).displayErrorMessage("wrong input format please enter [column],[row] i.E. 2,3");
	}
	
	@Test
	public void makemove_moveMadeOnOccupiedPosition_shouldReturnErrorMessage() throws Exception {
		IBoardConsoleSystem mockConsole = mock(IBoardConsoleSystem.class);
        HumanPlayer humanPlayer = new HumanPlayer('X');
        char[][] fields = new char[2][2];
        fields[0][0] = 'X';
        when(mockConsole.getUserInput()).thenReturn("1,1","1,2");
        humanPlayer.makeMove(mockConsole, fields);
        verify(mockConsole, times(2)).getUserInput();
        verify(mockConsole).displayErrorMessage("field is already occupied, please choose a different one");
		
	}

}