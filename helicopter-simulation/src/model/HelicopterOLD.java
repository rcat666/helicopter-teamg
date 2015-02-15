package model;

/**
 * 
 * @author Roberto
 */
public class HelicopterOLD {
	private HelicopterType helicopterType;
	private double altitude;
    private double heading;
    private double speed;
    private double attitude;
    private Position pos;
    private double pitch;
	
    
    public HelicopterOLD(HelicopterType HelicopterType, double altitude,
			double heading, double speed, double attitude, Position pos, double pitch) {
		this.helicopterType = HelicopterType;
		this.altitude = altitude;
		this.heading = heading;
		this.speed = speed;
		this.attitude = attitude;
		this.pos = new Position(0,0,0);
		this.pitch = pitch;
	}


    public HelicopterType getHelicopterType() {
		return helicopterType;
	}

	public void setHelicopterType(HelicopterType helicopterType) {
		this.helicopterType = helicopterType;
	}


	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}


	public double getHeading() {
		return heading;
	}


	public void setHeading(double heading) {
		this.heading = heading;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public double getAttitude() {
		return attitude;
	}


	public void setAttitude(double attitude) {
		this.attitude = attitude;
	}


	public Position getPos() {
		return pos;
	}


	public void setPos(Position pos) {
		this.pos = pos;
	}


	public double getPitch() {
		return pitch;
	}


	public void setPitch(double pitch) {
		this.pitch = pitch;
	}
    
    

    
}
