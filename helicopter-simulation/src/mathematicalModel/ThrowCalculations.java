package mathematicalModel;
import helicopter.Conversions;
import helicopter.Helicopter;
import helicopter.Position;

public class ThrowCalculations {
	final static double g=9.81; //this is in meter per second^2
	
	public static Position calculateNewPos(double time, Helicopter heli, double heliHeading, double heliPitch){
		float x = -(float) (calculateX(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliHeading, heliPitch) + heli.getPos().getX());  // need to set to its negaitve value as the positive x lies in the south direction and we want the opposite
		float y = (float) (calculateY(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliPitch) + heli.getPos().getY());
		float z = (float) (calculateZ(Conversions.mphToUnitsPSecond(heli.getSpeed()), time, heliHeading, heliPitch) + heli.getPos().getZ());
		Position newPosition = new Position(x,y,z);
		return newPosition;
	}

	//Calculates the x component of Position at time t 
	public static double calculateX(double v, double t, double headingAngle, double pitchAngle){
		double x=v*t*Math.cos(headingAngle)*Math.cos(pitchAngle);
		return x;
	}

	//Calculates the y (ALTITUDE) component of Position at time t
	public static double calculateY(double v, double t, double angle){
		double y=v*t*Math.sin(angle)-Conversions.metersPerSquareSecondsToUnitsPerSquareSeconds(g)/2.0*t*t;
		return y;
	}

	//Calculates the z component of Position at time t
	public static double calculateZ(double v, double t, double headingAngle,double pitchAngle){
		double z=v*t*Math.sin(headingAngle)*Math.cos(pitchAngle);
		return z;
	}
	
	public static Position calculateFinalPos(double v, double anglePitch, double angleHeading){
		/* Calculating the time when y=0
		 * this done by setting y=0 and getting the following formula
		 * t^2*(-g/2)+-v*t*sin(pitch)=0
		 * using the quadratic formula we receive
		 * t=-(2*b)/(2*a)=-(2*v*sin(pitch))/(2*(-g/2))
		 *this is one solution the other would return 0 
		 */
		double t=-(2*v*Math.sin(anglePitch))/(2*(-g/2));
		System.out.println("TIME OF CRASH  " + t);
		
		float x=(float) -calculateX(Conversions.mphToUnitsPSecond(v), t, angleHeading,anglePitch);
		float z=(float) calculateZ(Conversions.mphToUnitsPSecond(v), t, angleHeading,anglePitch);
		
		return new Position(x,0,z);
	}
	
	//return speed of helicopter at particular time
	public static double calculateSpeed(double time, double heliPitch, double speed){

		double speed0=Conversions.mphToUnitsPSecond(speed);
		double gConverted = Conversions.metersPerSquareSecondsToUnitsPerSquareSeconds(g);
		double speedCalculator = Math.sqrt(speed0*speed0 + gConverted*gConverted *time*time -2*speed0*gConverted*time*Math.sin(heliPitch));

		return speedCalculator;
		}
}