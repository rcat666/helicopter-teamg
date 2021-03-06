package repository;
import helicopter.Position;

/**
 * This class contains helicopter statistics.
 * This is used in TrajectoryInformation.
 */
public class HelicopterStats {
	
	private Position position;
	private double speed;	
	private double time;
	
	public HelicopterStats(Position pos, double speed, double time){
		this.position=pos;
		this.speed=speed;
		this.time=time;
	}	
	
	public Position getPosition() {return position;}
	public double getSpeed() {return speed;}
	public double getTime() {return time;}

}