package visualisation3d;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import map.Map;

import com.jme3.app.state.AbstractAppState;
import com.jme3.material.Material;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class VisController extends AbstractAppState implements ScreenController {

	private Nifty nifty;
	private Screen screen;
	private Material mat;
	private Node rootNode;

	@Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

	@Override
	public void onEndScreen(){	
	}

	@Override
	public void onStartScreen() {
	}
    
	
	
	public void updateMap() throws IOException{
		Image img = Toolkit.getDefaultToolkit().createImage(Map.recoverImage());
		BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bimg.createGraphics();
        Texture2D tex = createTexture(bimg, g2d);
        mat.setTexture("ColorMap", tex);
		Spatial s = rootNode.getChild(0+"");
        s.setMaterial(mat);
    }
    
	public Texture2D createTexture(BufferedImage img, Graphics2D g) {
        AWTLoader loader = new AWTLoader();
        Texture2D tex = new Texture2D(loader.load(img, true)); //changes to image parameter in buffer, affect the texture.
        tex.setMagFilter(Texture.MagFilter.Nearest);
        tex.setMinFilter(Texture.MinFilter.NearestNearestMipMap);
        return tex;
    }
}
