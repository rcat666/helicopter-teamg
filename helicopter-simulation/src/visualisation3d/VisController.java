package visualisation3d;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import map.MapHelper;

import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

public class VisController {

	public Texture2D updateMap() throws IOException{
		BufferedImage googleMapImage = MapHelper.mapImageWithOptions(40.714728, -73.998672, 14); // These option will have to parsed by the UI to another class
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
