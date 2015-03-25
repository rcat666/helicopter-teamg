package visualisation3d;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import map.MapTile;

import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

public class VisController {

	public Texture2D updateMap(double[] coordinates, String mapType) throws IOException {
		BufferedImage googleMapImage = MapTile.mapImageWithOptions(coordinates[0],coordinates[1], mapType); // These option will have to parsed by the UI from another class
		Graphics2D g2d = googleMapImage.createGraphics();
		Texture2D tex = createTexture(googleMapImage, g2d);
		return tex;
	}

	public Texture2D createTexture(BufferedImage img, Graphics2D g) {
		AWTLoader loader = new AWTLoader();
		Texture2D tex = new Texture2D(loader.load(img, true));
		tex.setMagFilter(Texture.MagFilter.Nearest);
		tex.setMinFilter(Texture.MinFilter.NearestNearestMipMap);
		return tex;
	}
}
