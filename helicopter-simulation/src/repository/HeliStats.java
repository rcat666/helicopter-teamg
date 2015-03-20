package repository;

import model.Position;

public class HeliStats {
	
	private Position position;
	private double speed;	
	private double time;
	
	public Position getPosition() {
		return position;
	}

	public double getSpeed() {
		return speed;
	}

	public double getTime() {
		return time;
	}
	
	public HeliStats(Position pos, double speed, double time){
		this.position=pos;
		this.speed=speed;
		this.time=time;
	}
	
	
}
