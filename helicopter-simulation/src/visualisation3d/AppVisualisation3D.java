package visualisation3d;

import java.io.IOException;

import model.Helicopter;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

public class AppVisualisation3D extends SimpleApplication {
	
	public VisController visualController;
	
	@Override
	public void simpleInitApp() {
		
		//Creates a box, assigned to a geometry object, with a material, a colour and attaches it to the node for vis 
		this.visualController = this.getVisualController();
		Box b = new Box(256, 256, 0.1f); 
        Geometry geom = new Geometry("Box", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        try {
			mat.setTexture("ColorMap", getMapTexture());
		} catch (IOException e) {
			
		}
		geom.setMaterial(mat);
        rootNode.attachChild(geom);
        
        
        
//        Material metal = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        metal.setColor("Color", ColorRGBA.White);
        
        //Spatial helicopter = assetManager.loadModel("Models/A340/A-340.obj");
        //helicopter.setMaterial(metal);
        //rootNode.attachChild(helicopter);
        
        Helicopter h = new Helicopter(assetManager);
        
        
       h.addToRootNode(rootNode);
        
       h.pos.x = 200;
       h.updatePosition();
        
        //Creates a camera in specified location, looking at a specific point, enables it, 
        //set its moving speed and property to rotate by mouse drag
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);
        
        cam.setLocation(new Vector3f(256,256, 100));
        cam.lookAt(new Vector3f(0,0,0), Vector3f.UNIT_Z);
        
        flyCam.setEnabled(true);
        flyCam.setMoveSpeed(100);
        flyCam.setDragToRotate(true);
    }
	
	@Override
	public void simpleUpdate(float tpf){
		
	}
	
	
	public VisController getVisualController() {
		return new VisController();
	}

	public Texture getMapTexture() throws IOException {
		return getVisualController().updateMap();
	}

}
