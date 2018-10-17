/**
 * 
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.game.board.TicTacToeConsoleSystem;
import com.game.configuration.IConfigurationReader;
import com.game.configuration.PropertyFileConfigReader;
import com.game.tictactoe.TicTacToeGameController;

/**
 * @author Abhijeet Gupta This is the starting point of the Game
 *
 */
public class PlayMe {

	public static void main(String[] args) {
		Properties properties = getProperties();
		IConfigurationReader configurationReader = new PropertyFileConfigReader(properties);
		TicTacToeConsoleSystem consoleSystem = new TicTacToeConsoleSystem();
		TicTacToeGameController gameController = new TicTacToeGameController(consoleSystem);
		while (true) {
			gameController.configureGame(configurationReader.read(consoleSystem));
			gameController.play();
			consoleSystem.displayInfoMessage("DO you want to Continue Playing [Y/N]");
			try {
				String input = consoleSystem.getUserInput();
				if (input.toUpperCase().contains("N")) {
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	@SuppressWarnings("resource")
	private static Properties getProperties() {
		Properties properties = new Properties();
		InputStream inputStream;

		try {
			// get a config file from the installation folder
			inputStream = new FileInputStream("resources/config.properties");
		} catch (Exception e) {
			// if no configuration file was found use the internal default
			System.out.println("config.properties file not found use defaults.");
			inputStream = PlayMe.class.getClassLoader().getResourceAsStream("config.properties");
		}

		try {
			properties.load(inputStream);
		} catch (IOException e1) {
			System.out.println("could not load properties");
		} catch (Exception e2) {
			System.out.println("Exception *** " + e2.getStackTrace().toString() + "** " + e2);
			System.out.println("could not load properties");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return properties;
	}
}
