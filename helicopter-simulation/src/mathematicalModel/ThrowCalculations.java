package mathematicalModel;

import model.Position;

public class ThrowCalculations {
	final static double g=9.81;
	
	
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
	}
	
	//Calculates the z(altitude) component of Position at time t
	public static double calculateZ(double v, double t, double angleOmega){
		double z=v*t*Math.sin(angleOmega)-g/2.0*t*t;
		return z;
	}	
	
	
}
