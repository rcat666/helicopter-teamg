package visualisation3d;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import map.Map;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
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
	private AppVisualisation3D app;

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (AppVisualisation3D) (SimpleApplication) app;
	}
	
	
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
    
	@Override
	public void update(float tpf){
		try {
			System.out.println("fuck");
			this.updateMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateMap() throws IOException{
		Image img = Toolkit.getDefaultToolkit().createImage(Map.recoverImage());
		System.out.println("fuck");
		BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bimg.createGraphics();
        Texture2D tex = createTexture(bimg, g2d);
        mat.setTexture("ColorMap", tex);
		Spatial s = rootNode.getChild(0+"");
        s.setMaterial(mat);
    }
    
	public Texture2D createTexture(BufferedImage img, Graphics2D g) {
        AWTLoader loader = new AWTLoader();
        Texture2D tex = new Texture2D(loader.load(img, true)); 
        tex.setMagFilter(Texture.MagFilter.Nearest);
        tex.setMinFilter(Texture.MinFilter.NearestNearestMipMap);
        return tex;
    }
}
