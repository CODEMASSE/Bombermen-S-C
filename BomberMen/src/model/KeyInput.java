package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DropMode;

import conrolleur.GameClient;
import conrolleur.GameServer;

public class KeyInput extends KeyAdapter {

	private GameClient game;

	public KeyInput(GameClient game) {

		this.game = game;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int i = e.getKeyCode();

		switch (i) {
		case KeyEvent.VK_UP:

			for (Player p : game.getPlayers()) {

				p.move(Direction.UP);

			}

			break;

		case KeyEvent.VK_DOWN:
			for (Player p : game.getPlayers()) {

				p.move(Direction.DOWN);

			}

			break;

		case KeyEvent.VK_RIGHT:

			for (Player p : game.getPlayers()) {

				p.move(Direction.RIGHT);

			}

			break;

		case KeyEvent.VK_LEFT:

			for (Player p : game.getPlayers()) {

				p.move(Direction.LEFT);

			}

			break;

		default:
			break;
		}

	}

}
