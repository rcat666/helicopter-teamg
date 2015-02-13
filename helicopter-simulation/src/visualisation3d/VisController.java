package visualisation3d;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import map.Map;

import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

public class VisController {

	public Texture2D updateMap() throws IOException{
		Image img = Toolkit.getDefaultToolkit().createImage(Map.recoverImage());
		BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bimg.createGraphics();
        Texture2D tex = createTexture(bimg, g2d);
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
