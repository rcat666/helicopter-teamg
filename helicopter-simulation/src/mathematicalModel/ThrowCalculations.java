package mathematicalModel;

import model.Conversions;
import model.Helicopter;
import model.Position;

public class ThrowCalculations {
	final static double g=9.81; //this is in meter per second^2
	
	
	public static Position calculateNewPos(double time, Helicopter heli, double heliHeading, double heliPitch){
		float x = (float) (calculateX(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliHeading) + heli.getPos().getX());
		float y = (float) (calculateY(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliPitch) + heli.getPos().getY());
		float z = (float) (calculateZ(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliHeading) + heli.getPos().getZ());
		Position newPosition = new Position(x,y,z);
		return newPosition;
	}


	//Calculates the x component of Position at time t 
	public static double calculateX(double v, double t, double angle){
		double x=v*t*Math.cos(angle);
		return x;
	}

	//Calculates the y (ALTITUDE) component of Position at time t
	public static double calculateY(double v, double t, double angle){
		double y=v*t*Math.sin(angle)-Conversions.metersPerSquareSecondToUnitsPerSquareSeconds(g)/2.0*t*t;
		return y;
	}

	//Calculates the z component of Position at time t
	public static double calculateZ(double v, double t, double angle){
		double z=v*t*Math.sin(angle);
		return z;
	}
	
}
