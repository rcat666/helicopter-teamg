package map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import model.Conversions;


public class MapHelper {
	
	public static int mapSize = 3072;
	private static String apikey= "Fmjtd%7Cluu82lurnq%2Cax%3Do5-94850w";
	
	public static BufferedImage mapImageWithOptions(double lat, double longitude, String mapType) throws IOException {
		double[] boundary = Conversions.getBoundaries(lat, longitude, Conversions.distance, Conversions.distance);
		String mapURLString =  "http://open.mapquestapi.com/staticmap/v4/getmap?key=" + apikey + "&bestfit=" + boundary[0] + "," + boundary[1] + "," + boundary[2]  + "," + boundary[3] + "&size=" + mapSize + "," + mapSize + "&type=" + mapType + "&imagetype=jpeg&";
		URL mapURL = new URL(mapURLString);
		BufferedImage mapImg = ImageIO.read(mapURL);
		return mapImg;
	}
	
	
}


