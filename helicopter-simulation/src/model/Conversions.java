package model;

public class Conversions {

	/**
	 * This class provides multiple static methods to convert between real life
	 * units and units used for the calculations.
	 * 
	 * @author mph
	 */

	// defining the conversion rate of meters to units used in calculations as 1
	// unit are 10 m
	public final static double metersToUnit = 4.42188;

	
	public static double mphToUnitsPSecond(double givenSpeed) {

		double newSpeed = givenSpeed;

		// converting mph into kmph
		newSpeed = (double) (newSpeed * 1.609344);

		// converting kmph into meters per hour
		newSpeed = newSpeed * (double) (1000);

		// converting meters per hour into units per hour
		newSpeed = newSpeed / metersToUnit;

		// converting units per hour into units per minute
		newSpeed = newSpeed / (double) (60);

		// converting units per minute into units per seconds
		newSpeed = newSpeed / (double) (60);

		return newSpeed;
	}

	
	public static double metersToUnits(double distanceMeters) {
		return distanceMeters / metersToUnit;
	}


	public static double kilometersToUnits(double distanceKilometer) {

		// convert kilometer into meters
		double distanceMeters = distanceKilometer / (double) (1000);

		return Conversions.metersToUnits(distanceMeters);
	}

	
	public static double metersPerSquareSecondToUnitsPerSquareSeconds(double acceleration) {

		return acceleration / 10;
	}
	
	//converting angles from degree into radians
	//this includes converting angles bigger than 180° into the respective minus angle
	public static double degreeToRadians(double angleToConvert){
		double angle;
		//check if angle is larger than 180° and change to its counterpart if it is
		//it also switches the negative value with the positive value to account for physical models angle going around counter-clockwise and reality clockwise
		if(angleToConvert>180) angle=(angleToConvert-180);
		else angle=-angleToConvert;
		return angle* (Math.PI / 180);
	}
}
