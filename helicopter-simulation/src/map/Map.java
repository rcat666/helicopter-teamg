package map;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Map {
	//https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=12&size=400x400
	public static void saveImage(URL url, String dest) throws IOException{
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(dest);
		byte[] b = new byte[2048];
        int length = is.read(b);
        while (length != -1) os.write(b, 0, length);
        os.close();
	}
	
	public static String recoverImage() throws IOException{
		URL url = new URL("https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=12&size=400x400");
		String dest = "Tiles/xy.jpg";
		saveImage(url, dest);
		return dest;
	}
}
