package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;
import com.jme3.math.ColorRGBA;

public class Main extends SimpleApplication {
	
	private Material mat;
	
	
	

	public static void main(String[] args) {
		Main app = new Main();
		app.start();
		
	}

	@Override
	public void simpleInitApp() {
		Box b = new Box(256, 0.1f, 256); 
        Geometry geom = new Geometry("Box", b);  
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");  
        mat.setColor("Color", ColorRGBA.Blue);   
        geom.setMaterial(mat);                  
        rootNode.attachChild(geom);
	/*	
	}
	
	public void setCameraAndLight(int camSpeed) {*/
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);
        cam.setLocation(new Vector3f(256,256, 50));
        cam.lookAt(new Vector3f(0,0,-10), Vector3f.UNIT_Y);
        flyCam.setEnabled(true);
        flyCam.setMoveSpeed(100);
        flyCam.setDragToRotate(true);
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
