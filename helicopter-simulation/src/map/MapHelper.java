package map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class MapHelper {
	
	static int mapSize = 640;
	
	public static BufferedImage mapImageWithOptions(double lat, double longitude, int zoom) throws IOException {
		String mapURLString = "https://maps.googleapis.com/maps/api/staticmap?center=" + Double.toString(lat) + "," + Double.toString(longitude) + "&zoom=" + Integer.toString(zoom) + "&size=" + mapSize + "x" + mapSize + "&scale=2&maptype=satellite&format=jpg";
		URL mapURL = new URL(mapURLString);
		BufferedImage mapImg = ImageIO.read(mapURL);
		return mapImg;
	}
	
	
}


