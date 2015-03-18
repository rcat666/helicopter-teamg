package model;

public class Conversions {

	/**
	 * This class provides multiple static methods to convert between real life
	 * units and units used for the calculations.
	 * 
	 * @author mph
	 */


	//average earth radius in m
	public final static double earthRadius = 6371000;
	//half the distance of the map side length that we want to display
	public final static double distance = 2000; 
	// defining the conversion rate of meters to units used in calculations
	// conversion rate is calculate by the size of the area displayed and 
	// the number of pixel it is projected on. (in this case 521 pixel) 
	public final static double metersToUnit = (distance*2)/512;
	
	
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
	
	public static double unitsPSecondToMph(double givenSpeed){
		double newSpeed= givenSpeed;
		
		newSpeed=newSpeed*60;
		newSpeed=newSpeed*60;
		
		newSpeed = newSpeed*metersToUnit;
		newSpeed = newSpeed/1.609344;
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
	
	//getting degrees of certain distance (in km) on the surface of that sphere
	//degree calculation for latitude at same longitude
	public static double getDegreeslat(double requiredDistance){
		return (180*requiredDistance)/(Math.PI*earthRadius);		
	}
	
	//degree calculation for longitude for different latitudes
	public static double getDegreesLon(double requiredDistance, double lat){
		return (180*requiredDistance)/(Math.PI*(Math.sin(Math.toRadians(90-lat))*earthRadius));		
	}
	
	//calculate the two coords that define the boundaryBox
	//after each new coord is calculated check that the are not over the boundaries
	//this returns an array of double of the format lat, lon of top left corner and 
	//lat long of bottom right corner
	public static double[] getBoundaries(double lat, double lon, double requiredDistanceLat, double requiredDistanceLon){
		double latTop = lat + getDegreeslat(requiredDistanceLat);
		if (latTop>90) latTop = 90 - (latTop -90);
		
		double latBottom = lat - getDegreeslat(requiredDistanceLat);
		if (latBottom<-90) latBottom = -90 + (latBottom + 90);
		
		double lonWest = lon - getDegreesLon(requiredDistanceLon,latTop);
		if (lonWest<-180) lonWest+=360;
		
		double lonEast = lon + getDegreesLon(requiredDistanceLon,latBottom);
		if (lonEast>180) lonEast-=360;
		
		double[] boundary = new double[] {latTop,lonWest,latBottom,lonEast};
		return boundary;
	}
	
	//methods to get row and column from population data with given coords
	//converting the decimal coord into the degree format of the form degree, min, sec
	public static int [] convertCoordDecToDegree(double coord){
		int coordDegree;
		int coordMin;
		int coordSec;
		
		coordSec= (int) Math.round( coord * 3600);				//converting coord into seconds
		coordDegree = coordSec / 3600;	
		coordSec = Math.abs(coordSec % 3600);
		coordMin = coordSec / 60;
		coordSec = coordSec % 60;
		
		int[] newformat = {coordDegree, coordMin, coordSec};
		return newformat;
	}
	
	//gets row and column of coords in population data file
	public static int[] getRowColumn(double lat, double lon){
		int[] convertedLat = convertCoordDecToDegree(lat);
		int latDegree = convertedLat[0];
		int latMin = convertedLat[1];
		int latSec = convertedLat[2];
		
		int[] convertedLon = convertCoordDecToDegree(lon);
		int lonDegree = convertedLon[0];
		int lonMin = convertedLon[1];
		int lonSec = convertedLon[2];
		
		//if degree is negative change min to negative aswell
		if (latDegree<0) latMin=-latMin;
		if (lonDegree<0) lonMin=-lonMin;
		
		//calculate which column and row the coords min and sec point towards
		int rowMin = (int) Math.round(((latMin + ( latSec / 60 )) / 2.5));  //2.5 is the resolution of population data
		int columnMin = (int) Math.round(((lonMin + ( lonSec / 60 )) / 2.5));
		
		//calculate row and column
		int row = -(latDegree * 24) - rowMin + 85 * 24;			//24 is the inverse of the cell size and 85 is the to rows langitude
		//check if the requested line is out of bounds, else set to first line
		if ( row > 3431) row = 0; 								//3431 is the amount of line is the file
		
		int column = (lonDegree * 24) + columnMin + 180 * 24;	//columns start at -180 degrees
		//check if the requested column is out of bounds, else set to first column
		if (column >= 8640) column = 0;
		
		int[] rowColumn = {row, column};
		return rowColumn;
	}
	
	//calculating lat and lon based on the rows and columns number from the data
	public static double getLat(int row){
		return -2.5 * (row - 2040)/60;		//2040 is the row corresponding to latitude 0
	}
	public static double getLon(int column){
		return 2.5*(column-4320)/60;		//4320 is the column corresponding to longitude 0
	}
	
	//calculating the distance between to coords
	//the previous formulas ahve been rearranged
	public static double getDistanceLat(double startingLat, double stopingLat){
		return ((stopingLat-startingLat)*Math.PI*earthRadius)/180.0;
	}
	public static double getDistanceLon(double startingLon, double stopingLon, double lat){
		return ((stopingLon-startingLon)*Math.PI*earthRadius*Math.sin(Math.toRadians(90-lat)))/180.0;
	}
}
