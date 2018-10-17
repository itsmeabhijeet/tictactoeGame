/**
 * 
 */
package com.game.player.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.game.player.config.PlayerConfiguration;
import com.game.player.entity.ComputerPlayer;
import com.game.player.entity.HumanPlayer;
import com.game.player.entity.Player;

/**
 * @author Abhijeet Gupta
 *
 */
public class PlayerFactoryTest {

	@Test
	public void getPlayer_ValidHumanPlayerConfiguration_ShouldReturnHumanPlayer() {

		Player player = PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.HUMAN, 'P'));
		assertThat(player).isInstanceOf(HumanPlayer.class).hasFieldOrPropertyWithValue("symbol", 'P');
	}

	@Test
	public void getPlayer_ValidComputerPlayerConfiguration_ShouldReturnComputerPlayer() throws Exception {
		Player player = PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.COMPUTER, 'C'));
		assertThat(player).isInstanceOf(ComputerPlayer.class).hasFieldOrPropertyWithValue("symbol", 'C');

	}
}
