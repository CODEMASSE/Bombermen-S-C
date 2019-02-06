package model;

import conrolleur.GameServer;

public class Player {

	private Coordinate coordinate;
	private GameServer game;
	private  String name;
	//private boolean stop=false;
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Player(GameServer game, Coordinate coordinate,String name) {
		this.name=name;
		this.game=game;
		this.coordinate=coordinate;
		
		
		
		// TODO Auto-generated constructor stub
	}
	

	public void move(Direction direction) {
     
		int currentX =this.coordinate.getX();
		int currentY=this.coordinate.getY();
		
		System.out.println(direction);
		switch (direction) {
		case UP:
			

			coordinate.setY(currentY-20);
			
			break;
		case DOWN:

			coordinate.setY(currentY+20);
			
			break;
		case RIGHT:
			coordinate.setX(currentX+20);
			
			break;
			
		case LEFT:
			coordinate.setX(currentX-20);
			break;

		default:
			break;
			
			
			
		}
		

		//notifications des observateur de player
		game.notifyAllObservers();
		
		
	}

	public String getName() {
		return name;
	}

	public void setName (String name){

		this.name =name;

	}
}
