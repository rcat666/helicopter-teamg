package helicopter;

/**
 * This class is where the helicopter variables are set.
 * These variables include the model, position, altitude, heading, speed, attitude and pitch.
 */
public class Helicopter {
	
	//Variables for the helicopter.
	private HelicopterModel helicopterType;
	private double altitude;
    private double heading;
    private double speed;
    private double attitude;
    private Position pos;
    private double pitch;
	
    //Constructor for the helicopter.
    public Helicopter(HelicopterModel HelicopterType, double altitude, double heading, double speed, double attitude, double pitch) {
		this.helicopterType = HelicopterType;
		this.altitude = altitude;
		this.heading = heading;
		this.speed = speed;
		this.attitude = attitude;
		this.pos = new Position(0,(float) Conversions.metersToUnits(altitude),0);
		this.pitch = pitch;
	}
    
    //Methods to get the type of the helicopter and set the type of the helicopter.
    public HelicopterModel getHelicopterType() {return helicopterType;}
	public void setHelicopterType(HelicopterModel helicopterType) {this.helicopterType = helicopterType;}

	//Methods to get the altitude of the helicopter and set the altitude of the helicopter.
	public double getAltitude() {return altitude;}
	public void setAltitude(double altitude) {this.altitude = altitude;}

	//Methods to get the heading of the helicopter and set the heading of the helicopter.
	public double getHeading() {return heading;}
	public void setHeading(double heading) {this.heading = heading;}

	//Methods to get the speed of the helicopter and set the speed of the helicopter.
	public double getSpeed() {return speed;}
	public void setSpeed(double speed) {this.speed = speed;}

	//Methods to get the attitude of the helicopter and set the attitude of the helicopter.
	public double getAttitude() {return attitude;}
	public void setAttitude(double attitude) {this.attitude = attitude;}

	//Methods to get the position of the helicopter and set the position of the helicopter.
	public Position getPos() {return pos;}
	public void setPos(Position pos) {this.pos = pos;}
	
	//Methods to get pitch of the helicopter and set pitch of the helicopter.
	public double getPitch() {return pitch;}
	public void setPitch(double pitch) {this.pitch = pitch;}
    
}