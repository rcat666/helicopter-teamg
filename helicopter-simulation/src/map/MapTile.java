package map;
import helicopter.Conversions;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * This class is to get the image for the map tile in the simulation.
 * Map images are retrieved from the MapQuest website.
 * The user provides the latitude, longitude and type of map to retrieve.
 * The website provides the image, with the centre being the latitude and longitude.
 */

public class MapTile {
	
	public static int mapSize = 3072;										//Resolution of map.
	private static String apikey= "Fmjtd%7Cluu82lurnq%2Cax%3Do5-94850w";	//API key.
	
	//Method to get map image from MapQuest website.
	public static BufferedImage mapImageWithOptions(double latitude, double longitude, String mapType) throws IOException {
		
		double[] boundary = Conversions.getBoundaries(latitude, longitude, Conversions.distance, Conversions.distance);
		
		//Creating the URL to access MapQuest website.
		String mapURLString =  "http://open.mapquestapi.com/staticmap/v4/getmap?key=" + apikey + "&bestfit=" 
				+ boundary[0] + "," + boundary[1] + "," + boundary[2]  
				+ "," + boundary[3] + "&size=" + mapSize + "," + mapSize 
				+ "&type=" + mapType + "&imagetype=jpeg&";
		URL mapURL = new URL(mapURLString);
		BufferedImage mapTile = ImageIO.read(mapURL);						//Retrieve image from the url created.
		return mapTile;
	}
}