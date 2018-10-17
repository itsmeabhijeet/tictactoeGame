/**
 * 
 */
package com.game.player.entity;

import java.awt.Point;
import java.io.IOException;

import com.game.board.IBoardConsoleSystem;

/**
 * @author Abhijeet Gupta
 *
 */
public abstract class Player {
	
	private char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Point makeMove(IBoardConsoleSystem consoleIO, char[][] fields) throws IOException;

}
