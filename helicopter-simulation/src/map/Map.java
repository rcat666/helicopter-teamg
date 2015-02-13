package map;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Map {
	
	/*
	 * Method to recover a image file and save to a jpg.
	 * TODO: Implement parameters for url string so that user can input their own coords
	 */
	public static String recoverImage() throws IOException{
		URL url = new URL("https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=12&size=400x400&format=jpg");
		String dest = "Tiles/xy.jpg";
		File f = new File(dest);
		FileUtils.copyURLToFile(url, f);
		return dest;
	}
}
