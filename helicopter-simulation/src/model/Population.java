package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.jme3.scene.Geometry;

import visualisation3d.AffectedArea3D;

public class Population {
	/* first seven line of the glp15ag.asc file, these have been removed from the file
	 * to allow for easier access to the data in the file
	 * ncols         8640
	 * nrows         3432
	 * xllcorner     -180
	 * yllcorner     -58
	 * cellsize      0.0416666666667
	 * NODATA_value  -9999*/
	
	// reading the file and returning the relevant subset as a 2D array
	public static double[][] readPopulationData(int row1, int column1, int row2, int column2){
		double[][] informationHolder = new double[(row2-row1)+1][(column2-column1)+1];
		String currentLine;
		BufferedReader filereader = null;
		try {
            filereader = new BufferedReader(new FileReader("assets/Data/glp15ag.asc"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		int fileRow = 0;
		try{
			int informationHolderRow = 0;
			int informationHolderColumn = 0;
			
			while ((currentLine=filereader.readLine()) != null || fileRow < row1){
				if (fileRow >=row1 && fileRow <=row2){
					String[] data = currentLine.split(" ");
					informationHolderColumn = 0;
					for (int position = column1; position<=column2; position++){
						System.out.println(informationHolderRow+"  "+informationHolderColumn+"   "+position);
						informationHolder[informationHolderRow][informationHolderColumn] = Double.parseDouble(data[position]);
						informationHolderColumn++;
					}
					informationHolderRow++;
				}
				fileRow++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				filereader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return informationHolder;
	}
	
	//calculates the required rows and columns depending on the crash and affected area
	public static int[] getRequiredRowColumn(double crashLat, double crashLon, double affectedX, double affectedY){
		
		//get lat and long boundary of affected area 
		double[] boundary = Conversions.getBoundaries(crashLat, crashLon, affectedX, affectedY);
		double latTop = boundary[0];
		double lonWest = boundary[1];
		double latBottom = boundary[2];
		double lonEast = boundary[3];
		
		//get row and columns that include all cells required
		int[] row_columns1 = Conversions.getRowColumn(latTop, lonWest);
		int[] row_columns2 = Conversions.getRowColumn(latBottom, lonEast);
				
		int[] returner = {row_columns1[0], row_columns1[1], row_columns2[0], row_columns1[1]};
		return returner;		
	}
	
	// calculating the amount of people in the affected area given the data subset 
	public static double totalPopulation(double[][] information, double affectedX, double affectedY){
		//adding all the data in information tab together
		double population = 0;
		int rows = information.length;
		int columns = information[0].length;
		for (int rowPos=0; rowPos<rows; rowPos++){
			for ( int columnPos=0; columnPos<columns; columnPos++){
				population += information[rowPos][columnPos];
			}
		}

		return population;		
	}
	
	
	//calculating the people affected
	public static double calculatePeopleAffected(Position startingPos, Position lastPos, double startingLat, double startingLon, double affectedX, double affectedY){
		//calculating coords of crash site and making sure the are valid
		double latDistance = Conversions.getDegreeslat((lastPos.getX()-startingPos.getX()));
		double latitude= startingLat -latDistance;
		if(latitude>90) latitude = 90 - (latitude-90);
		if(latitude<-90) latitude = -90 + (latitude+90);
		
		double lonDistance = Conversions.getDegreesLon((lastPos.getY()-startingPos.getY()), latitude);
		double longitude = startingLon + lonDistance;
		if(longitude>180) longitude-=360;
		if(longitude<-180) longitude+=360;
		
		//get people affected
		int[] rowColumn = getRequiredRowColumn(latitude, longitude, affectedX, affectedY);
		double[][] dataSubset= readPopulationData(rowColumn[0], rowColumn[1], rowColumn[2], rowColumn[3]);
		double peopleAffected = totalPopulation(dataSubset, affectedX, affectedY);
		
		//calculating the area the ellipse encloses with A=PI*r1*r2
		double ellipseArea = Math.PI*(affectedX/2.0)*(affectedY/2.0);
		
		
		//getting lat and lon for area covered
		double latitudeWholeAreaLeftTop;
		double longitudeWholeAreaLeftTop;
		double latitudeWholeAreaRightBottom;
		double longitudeWholeAreaRightBottom;
		latitudeWholeAreaLeftTop = Conversions.getLat(rowColumn[0]);
		longitudeWholeAreaLeftTop = Conversions.getLon(rowColumn[1]);
		latitudeWholeAreaRightBottom = Conversions.getLat(rowColumn[2]+1);
		longitudeWholeAreaRightBottom = Conversions.getLon(rowColumn[3]+1);
			
		double areaTotalPopulation = Math.abs(
				Conversions.getDistanceLat(latitudeWholeAreaLeftTop, latitudeWholeAreaRightBottom)
				*Conversions.getDistanceLon(longitudeWholeAreaLeftTop,longitudeWholeAreaRightBottom,(latitudeWholeAreaLeftTop+latitudeWholeAreaRightBottom)/2.0));

		
		//calculate relation between total population area and actual affected area
		double relation =ellipseArea/areaTotalPopulation;

		//calculating actual affected population based on above relation
		peopleAffected=peopleAffected*relation;

		return peopleAffected;
		
	}
}
