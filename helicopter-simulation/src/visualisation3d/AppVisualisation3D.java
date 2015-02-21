package visualisation3d;

import java.util.ArrayList;

import model.Helicopter;
import model.Position;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Line;

public class AppVisualisation3D extends SimpleApplication {

	private Helicopter3D heli3Dmodel;
	private boolean runFirstTime = true;
	private Helicopter helicopter;

	@Override
	public void simpleInitApp() {

		// Attach map tile 
		rootNode.attachChild(new Tile3D().getTile3D(assetManager));

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
	}

	private int arrayPos = 0;
	private ArrayList<Position> ap = new ArrayList<Position>();
	private Position previousPosition;

	@Override
	public void simpleUpdate(float tpf) {
		if (this.runFirstTime) {
			ap = heli3Dmodel.positions();
			for (Position posi : ap) System.out.println("X: " + posi.getX()+ " Y: " + posi.getY()+ " Z: " + posi.getZ());
			runFirstTime = false;

			// sets the first position for line drawing as (0,0,0) and will be
			// ignored the first time round
			previousPosition = new Position(0, 0, 0);
		}

		// gets current Position from the array of Helicopter
		Position currentPosition = ap.get(arrayPos);

		// updates the Helicopter models position with values from trajectory
		// array
		heli3Dmodel.pos.setX(currentPosition.getX());
		heli3Dmodel.pos.setY(currentPosition.getY());
		heli3Dmodel.pos.setZ(currentPosition.getZ());

		// checks if the position has changed before drawing a line and checks
		// previousPosition is not (0,0,0)
		// this check is performed to avoid drawing lines at the end of the
		// program when the Helicopter has already crashed
		// TODO: Replace this code with call to Path3D.
		if (!currentPosition.comparePosition(previousPosition)
				&& !previousPosition.comparePosition(new Position(0, 0, 0))) {

			// draws(??) a line between previous and current Position
			Line pathSegment = new Line(previousPosition.toVector3f(),
					currentPosition.toVector3f()); // where line starts and ends
			pathSegment.setLineWidth(3); // width of line
			Geometry segmentGeometry = new Geometry("Bullet", pathSegment); // Specifying that it is a line
			Material segmentMaterial = new Material(assetManager,
					"Common/MatDefs/Misc/Unshaded.j3md");
			segmentMaterial.setColor("Color", ColorRGBA.Red); // set line colour
																// to red
			segmentGeometry.setMaterial(segmentMaterial);
			rootNode.attachChild(segmentGeometry);
		}
		// sets the updating previous Position to current Position, so that a
		// new line can be drawn from here.
		previousPosition = currentPosition;

		if (arrayPos < ap.size() - 1)
			arrayPos++;
		heli3Dmodel.updatePosition();

	}
	
	public AppVisualisation3D(Helicopter helicopter){
		super();
		this.helicopter=helicopter;
	}

	

}
