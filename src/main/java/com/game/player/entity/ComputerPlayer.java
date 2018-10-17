/**
 * 
 */
package com.game.player.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.game.board.IBoardConsoleSystem;

/**
 * @author Abhijeet Gupta
 *
 */
public class ComputerPlayer extends Player {
	

    public ComputerPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public Point makeMove(IBoardConsoleSystem consoleSystem, char[][] positions) {
        List<Point> freePoints = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                if(positions[i][j] == '\0') {
                    freePoints.add(new Point(i,j));
                }
            }
        }
        Collections.shuffle(freePoints);
        return freePoints.get(0);
    }


}
