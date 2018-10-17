package com.game.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.game.board.IBoardConsoleSystem;
import com.game.player.config.PlayerConfiguration;
import com.game.tictactoe.TicTacToeConfiguration;

/**
 * @author Abhijeet Gupta
 * This class has IConfigurationReader implementation using config.property file
 *
 */
public class PropertyFileConfigReader implements IConfigurationReader{

    public static final int DEFAULT_PLAYGROUND_SIZE = 4;
    public static final char DEFAULT_PLAYER_A_SYMBOL = 'X';
    public static final char DEFAULT_PLAYER_B_SYMBOL = 'O';
    public static final char DEFAULT_PLAYER_COMPUTER_SYMBOL = '@';
    private Properties properties;

    public PropertyFileConfigReader(Properties properties) {
        this.properties = properties;
    }

    @Override
    public TicTacToeConfiguration read(IBoardConsoleSystem consoleIO) {
        TicTacToeConfiguration configuration;
        if (properties == null) {
        	consoleIO.displayErrorMessage("Properties is not set will use defaults" +
                    " "+PropertyKeys.PLAYGROUND_SIZE+": "+DEFAULT_PLAYGROUND_SIZE+
                    " "+PropertyKeys.PLAYER_A_SYMBOL+": "+DEFAULT_PLAYER_A_SYMBOL+
                    " "+PropertyKeys.PLAYER_B_SYMBOL+": "+DEFAULT_PLAYER_B_SYMBOL+
                    " "+PropertyKeys.PLAYER_COMPUTER_SYMBOL+": "+DEFAULT_PLAYER_COMPUTER_SYMBOL);

            ArrayList<PlayerConfiguration> playerConfigurations = createDefaultPlayer();
            configuration = new TicTacToeConfiguration(DEFAULT_PLAYGROUND_SIZE, playerConfigurations);
        } else {
            int size = readPlaygroundSize(consoleIO);
            List<PlayerConfiguration> playerConfigurations = new ArrayList<>();
            playerConfigurations.add(
                    getPlayerConfiguration(consoleIO,
                            PlayerConfiguration.HUMAN,
                            PropertyKeys.PLAYER_A_SYMBOL,
                            DEFAULT_PLAYER_A_SYMBOL));
            playerConfigurations.add(
                    getPlayerConfiguration(consoleIO,
                            PlayerConfiguration.HUMAN,
                            PropertyKeys.PLAYER_B_SYMBOL,
                            DEFAULT_PLAYER_B_SYMBOL));
            playerConfigurations.add(
                    getPlayerConfiguration(consoleIO,
                            PlayerConfiguration.COMPUTER,
                            PropertyKeys.PLAYER_COMPUTER_SYMBOL,
                            DEFAULT_PLAYER_COMPUTER_SYMBOL));
            setDefaultPlayerSymbolsIfEqual(consoleIO, playerConfigurations);
            configuration = new TicTacToeConfiguration(size, playerConfigurations);
        }
        return configuration;
    }

    private ArrayList<PlayerConfiguration> createDefaultPlayer() {
        ArrayList<PlayerConfiguration> playerConfigurations = new ArrayList<>();
        playerConfigurations.add(
                new PlayerConfiguration(PlayerConfiguration.HUMAN, DEFAULT_PLAYER_A_SYMBOL));
        playerConfigurations.add(
                new PlayerConfiguration(PlayerConfiguration.HUMAN, DEFAULT_PLAYER_B_SYMBOL));
        playerConfigurations.add(
                new PlayerConfiguration(PlayerConfiguration.COMPUTER, DEFAULT_PLAYER_COMPUTER_SYMBOL));
        return playerConfigurations;
    }

    private void setDefaultPlayerSymbolsIfEqual(IBoardConsoleSystem consoleIO, List<PlayerConfiguration> playerConfigurations) {
        for (PlayerConfiguration pConfig :
                playerConfigurations) {
            for (PlayerConfiguration pConfigTest :
                    playerConfigurations) {
                if (pConfig != pConfigTest && pConfig.getPlayerSymbol() == pConfigTest.getPlayerSymbol()){
                    playerConfigurations.removeAll(playerConfigurations);
                    playerConfigurations.addAll(createDefaultPlayer());
                    consoleIO.displayErrorMessage("Players got the same symbol will use default symbols"+
                            " "+PropertyKeys.PLAYER_A_SYMBOL+": "+DEFAULT_PLAYER_A_SYMBOL+
                            " "+PropertyKeys.PLAYER_B_SYMBOL+": "+DEFAULT_PLAYER_B_SYMBOL+
                            " "+PropertyKeys.PLAYER_COMPUTER_SYMBOL+": "+DEFAULT_PLAYER_COMPUTER_SYMBOL);
                    return;
                }
            }
        }
    }


    PlayerConfiguration getPlayerConfiguration(IBoardConsoleSystem consoleIO, String playerType, String symbol, char defaultSymbol) {
        String property = properties.getProperty(symbol);
        if (property != null && !property.isEmpty() && property.length() == 1) {
            return new PlayerConfiguration(playerType, property.charAt(0));
        }
        consoleIO.displayErrorMessage(symbol+" is not a single character or missing using default value "+defaultSymbol);
        return new PlayerConfiguration(playerType, defaultSymbol);
    }

    int readPlaygroundSize(IBoardConsoleSystem consoleIO) {
        int size;
        try {
            size = Integer.parseInt(properties.getProperty(PropertyKeys.PLAYGROUND_SIZE));
        } catch (Exception e) {
        	consoleIO.displayErrorMessage(PropertyKeys.PLAYGROUND_SIZE + " is not a number or missing using default size "
                    + DEFAULT_PLAYGROUND_SIZE);
            size = DEFAULT_PLAYGROUND_SIZE;
        }
        return size;
    }
}

