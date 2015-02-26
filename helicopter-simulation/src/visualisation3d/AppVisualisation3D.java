package visualisation3d;

import java.util.ArrayList;

import model.Helicopter;
import model.Position;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.ActionListener;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
//import com.jme3.scene.Node;

public class AppVisualisation3D extends SimpleApplication {

	private Helicopter3D heli3Dmodel;
	private boolean runFirstTime = true;
	//private boolean mapAttached = true;
	//private boolean pathAttached = true;
	private Helicopter helicopter;
	private Geometry map;
	private Geometry segmentGeometry;
	//private Node path;
	@Override
	public void simpleInitApp() {

		// Attach map tile 
		map = new Tile3D().getTile3D(assetManager);
		rootNode.attachChild(map);

		// Simple sky
		rootNode.attachChild(new Sky3D().getSky(assetManager));

		//Creates helicopter
		heli3Dmodel=new Helicopter3D(assetManager, this.helicopter);
		heli3Dmodel.addToRootNode(rootNode);

		// Creates a camera in specified location, looking at a specific point,
		// enables it,
		// set its moving speed and property to rotate by mouse drag
		DirectionalLight sun = new DirectionalLight();
		sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
		rootNode.addLight(sun);
		AmbientLight al = new AmbientLight();
		al.setColor(ColorRGBA.White.mult(1.3f));
		rootNode.addLight(al);

		cam.setLocation(new Vector3f(322.57492f, 283.93198f, -344.94318f));
		cam.lookAt(new Vector3f(0, 0, -10), Vector3f.UNIT_Y);

		flyCam.setEnabled(true);
		flyCam.setMoveSpeed(300);
		flyCam.setDragToRotate(true);
		
		initKeys();
	}

	private void initKeys() {
		inputManager.addMapping("Map", new KeyTrigger(KeyInput.KEY_M));
	    inputManager.addMapping("Path", new KeyTrigger(KeyInput.KEY_P));
	    
	    inputManager.addListener(actionListener, "Map", "Path");
	}

	private ActionListener actionListener = new ActionListener() { 
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("Map") && !keyPressed) {
				rootNode.detachChild(map); 
			}
			if (name.equals("Path") && !keyPressed) {
				rootNode.detachChild(segmentGeometry);
			}
		}
	};
	
	private int arrayPos = 0;
	private ArrayList<Position> ap = new ArrayList<Position>();
	private Position previousPosition;

	@Override
	public void simpleUpdate(float tpf) {
		if (this.runFirstTime) {
			ap = heli3Dmodel.positions();
			for (Position posi : ap) System.out.println("X: " + posi.getX()+ " Y: " + posi.getY()+ " Z: " + posi.getZ());
			runFirstTime = false;

			// sets the first position for line drawing as the first position specified in arrayPos
			// ignored the first time round
			previousPosition = ap.get(arrayPos);
		}

		// gets current Position from the array of Helicopter
		Position currentPosition = ap.get(arrayPos);

		// updates the Helicopter models position with values from trajectory
		// array
		heli3Dmodel.pos.setX(currentPosition.getX());
		heli3Dmodel.pos.setY(currentPosition.getY());
		heli3Dmodel.pos.setZ(currentPosition.getZ());
		
		
		segmentGeometry = new Path3D().getPath3D(currentPosition, previousPosition, assetManager);
		if (segmentGeometry != null) rootNode.attachChild(segmentGeometry);
		//path.attachChild(segmentGeometry);
		previousPosition = currentPosition;

		if (arrayPos < ap.size() - 1) arrayPos++;
		heli3Dmodel.updatePosition();

	}
	
	public AppVisualisation3D(Helicopter helicopter){
		super();
		this.helicopter=helicopter;
	}

	

}
