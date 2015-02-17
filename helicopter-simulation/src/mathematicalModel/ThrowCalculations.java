package mathematicalModel;

import model.Helicopter;
import model.Position;

public class ThrowCalculations {
	final static double g=9.81;
	
	
	public static Position calculateNewPos(double time, Helicopter heli, double heliHeading, double heliPitch){
		float x = (float) (calculateX(heli.getSpeed(), time, heliHeading) + heli.getPos().getX());
		float y = (float) (calculateY(heli.getSpeed(), time, heliPitch) + heli.getPos().getY());
		float z = (float) (calculateZ(heli.getSpeed(), time, heliHeading) + heli.getPos().getZ());
		Position newPosition= new Position(x,y,z);
		return newPosition;
	}


	//Calculates the x component of Position at time t 
	public static double calculateX(double v, double t, double angle){
		double x=v*t*Math.cos(angle);
		return x;
	}

	//Calculates the y (ALTITUDE) component of Position at time t
	public static double calculateY(double v, double t, double angle){
		double y=v*t*Math.sin(angle)-g/2.0*t*t;
		return y;
	}

	//Calculates the z component of Position at time t
	public static double calculateZ(double v, double t, double angle){
		double z=v*t*Math.sin(angle);
		return z;
	}
	
	/*  This way might not be needed any longer, delete if certain! 
	//calculate new position based on old position and time past since failure
	
	public static Position calculateNewPos(double time, double velocity, Position last, double angleOmega, double anglePsi, double angleChi){
		double x = calculateX(velocity, time, angleChi)+last.getX();
		double y = calculateY(velocity, time, anglePsi)+last.getY();
		double z = calculateZ(velocity, time, angleOmega)+last.getAltitude();
		Position newPosition= new Position(x,y,z);
		return newPosition;
	}

	//Calculates the x component of Position at time t 
	public static double calculateX(double v, double t, double angleChi){
		double x=v*t*Math.cos(angleChi);
		return x;
	}
	
	//Calculates the y component of Position at time t
	public static double calculateY(double v, double t, double anglePsi){
		double y=v*t*Math.cos(anglePsi);
		return y;
	}*/
	
}
