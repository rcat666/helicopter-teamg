package visualisation3d;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class AppVisualisation3D extends SimpleApplication {
	
	private VisController visualController;

	@Override
	public void simpleInitApp() {
		
		this.visualController = new VisController();
		Box b = new Box(256, 0.1f, 256); 
        Geometry geom = new Geometry("Box", b);  
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");  
        mat.setColor("Color", ColorRGBA.Blue);   
        geom.setMaterial(mat);                  
        rootNode.attachChild(geom);
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
	
	@Override
    public void simpleUpdate(float tpf) {
        this.visualController.setEnabled(true);
    }
}
