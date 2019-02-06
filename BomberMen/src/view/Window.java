package view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import conrolleur.GameClient;
import model.KeyInput;
import model.Player;

public class Window  {

	  ArrayList<Player> players;

	  private GameClient gameClient;


	private JComponent jComponent = new JComponent() {


		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);

			for (Player player : players) {

				g.fillRect(player.getCoordinate().getX() , player.getCoordinate().getY(), 30, 30);

			}

			// };

		}
	};

	public Window(GameClient gameClient) {
		

		this.players=gameClient.getPlayers();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(jComponent);
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		frame.addKeyListener(new KeyInput());
		
		
	}



}
