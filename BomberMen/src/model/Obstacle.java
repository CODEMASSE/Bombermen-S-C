package model;

public class Obstacle {
	
	private Coordinate coordinate;
	private TypeObstacles obstacle;
	 
	public Obstacle(Coordinate coordinate, TypeObstacles obstacle) {
		
		this.coordinate = coordinate;
		this.obstacle = obstacle;
	}

	public enum TypeObstacles {
		DESTRUCTIBLE,INDUSTRUCTIBLE,BONUS,BOMB
		
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public TypeObstacles getObstacle() {
		return obstacle;
	}

	public void setObstacle(TypeObstacles obstacle) {
		this.obstacle = obstacle;
	}
	
	

}
