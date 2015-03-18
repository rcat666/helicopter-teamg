package repository;

import model.Position;

public class HeliStats {
	
	private Position position;
	private double speed;	
	
	public Position getPosition() {
		return position;
	}

	public double getSpeed() {
		return speed;
	}
	
	public HeliStats(Position pos, double speed){
		this.position=pos;
		this.speed=speed;
	}
	
	
}
