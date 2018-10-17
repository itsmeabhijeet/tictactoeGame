/**
 * 
 */
package com.game.player.factory;

import com.game.player.config.PlayerConfiguration;
import com.game.player.entity.ComputerPlayer;
import com.game.player.entity.HumanPlayer;
import com.game.player.entity.Player;

/**
 * @author Abhijeet Gupta
 *
 */
public class PlayerFactory {
	


    public static Player getPlayer(PlayerConfiguration playerConfig) {
        switch (playerConfig.getPlayerType()) {
            case PlayerConfiguration.HUMAN:
                return new HumanPlayer(playerConfig.getPlayerSymbol());

            case PlayerConfiguration.COMPUTER:
                return new ComputerPlayer(playerConfig.getPlayerSymbol());
        }
        return null;
    }


}
