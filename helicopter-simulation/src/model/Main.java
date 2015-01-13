package model;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;

public class Main extends SimpleApplication {

	public static void main(String[] args) {
		Main app = new Main();
		app.start();

	}

	@Override
	public void simpleInitApp() {
		Box b = new Box(256, 0.1f, 256); 
        Geometry geom = new Geometry("Box", b);  
        Material mat = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");  
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

}
