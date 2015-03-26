package mathematicalModel;
import helicopter.Conversions;
import helicopter.Helicopter;
import helicopter.Position;

/**
 * This class contains all calculations for the projectile motion descent model.
 */
public class ProjectileMotionCalculations {
	
	final static double g=9.81; 	//This is in meter per second^2.
	
	//Method to calculate the new position.
	public static Position calculateNewPosition(double time, Helicopter heli, double heliHeading, double heliPitch){
		float x = -(float) (calculateX(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliHeading, heliPitch) + heli.getPos().getX());  // need to set to its negaitve value as the positive x lies in the south direction and we want the opposite
		float y = (float) (calculateY(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliPitch) + heli.getPos().getY());
		float z = (float) (calculateZ(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliHeading, heliPitch) + heli.getPos().getZ());
		Position newPosition = new Position(x,y,z);
		return newPosition;
	}

	//Calculates the x component of Position at time t.
	public static double calculateX(double v, double t, double headingAngle, double pitchAngle){
		double x=v*t*Math.cos(headingAngle)*Math.cos(pitchAngle);
		return x;
	}

	//Calculates the y (ALTITUDE) component of Position at time t.
	public static double calculateY(double v, double t, double angle){
		double y=v*t*Math.sin(angle)-Conversions.metersPerSquareSecondsToUnitsPerSquareSeconds(g)/2.0*t*t;
		return y;
	}

	//Calculates the z component of Position at time t.
	public static double calculateZ(double v, double t, double headingAngle,double pitchAngle){
		double z=v*t*Math.sin(headingAngle)*Math.cos(pitchAngle);
		return z;
	}
	
	//Return speed of helicopter at particular time.
	public static double calculateSpeed(double time, double heliPitch, double speed){

		double initialSpeed=Conversions.mphToUnitsPSecond(speed);
		double gConverted = Conversions.metersPerSquareSecondsToUnitsPerSquareSeconds(g);
		double speedCalculator = Math.sqrt(initialSpeed*initialSpeed + gConverted*gConverted *time*time -2*initialSpeed*gConverted*time*Math.sin(heliPitch));

		return speedCalculator;
	}
}