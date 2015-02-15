package map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class MapHelper {
	
	static int mapSize = 640;
	
	public static BufferedImage mapImageWithOptions(double lat, double longitude, int zoom) throws IOException {
		String mapAPIURLString = "https://maps.googleapis.com/maps/api/staticmap?center=" + Double.toString(lat) + "," + Double.toString(longitude) + "&zoom=" + Integer.toString(zoom) + "&size=" + mapSize + "x" + mapSize + "&format=jpg";
		URL mapURL = new URL(mapAPIURLString);
		BufferedImage mapImg = ImageIO.read(mapURL);
		return mapImg;
	}
	
	
}



//public class Map {
//	
//	/*
//	 * Method to recover a image file and save to a jpg.
//	 * TODO: Implement parameters for url string so that user can input their own coords
//	 */
//	public static String recoverImage() throws IOException{
//		
//		URL url = new URL("https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=12&size=400x400&format=jpg");
//		String dest = "Tiles/xy.jpg";
//		File f = new File(dest);
//		FileUtils.copyURLToFile(url, f);
//		return dest;
//	}
//}
