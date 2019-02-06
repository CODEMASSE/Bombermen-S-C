package app;

import conrolleur.GameServer;
import view.Window;

public class App {

	public static void main(String[] args) {

		GameServer game = new GameServer();

		Window window = new Window(game);
		game.registerObserver(window);
	}

}
