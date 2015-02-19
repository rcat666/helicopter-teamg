package visualisation3d;

import java.io.IOException;
import java.util.ArrayList;

import model.Position;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

public class AppVisualisation3D extends SimpleApplication {

	private Helicopter3D h;
	private boolean runFirstTime = true;

	@Override
	public void simpleInitApp() {

		// Creates a box, assigned to a geometry object, with a material, a
		// colour and attaches it to the node for vis
		Box b = new Box(256, 0.1f, 256);
		Geometry geom = new Geometry("Box", b);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		try {
			mat.setTexture("ColorMap", getMapTexture());
		} catch (IOException e) {

		}
		geom.setMaterial(mat);
		rootNode.attachChild(geom);
		
        
/*		//Simple sky
		Texture west = assetManager.loadTexture("Textures/TropicalSunnyDayRight2048.png");
		Texture east = assetManager.loadTexture("Textures/TropicalSunnyDayLeft2048.png");
		Texture north = assetManager.loadTexture("Textures/TropicalSunnyDayFront2048.png");
		Texture south = assetManager.loadTexture("Textures/TropicalSunnyDayBack2048.png");
		Texture up = assetManager.loadTexture("Textures/TropicalSunnyDayUp2048.png");
		Texture down = assetManager.loadTexture("Textures/TropicalSunnyDayDown2048.png");

		Spatial sky = SkyFactory.createSky(assetManager, west, east, north, south, up, down);
		rootNode.attachChild(sky);*/
		
        
		h = new Helicopter3D(assetManager);
		h.addToRootNode(rootNode);

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
		flyCam.setMoveSpeed(100);
		flyCam.setDragToRotate(true);
	}
    
	private int arrayPos=0;
	private ArrayList<Position> ap=new ArrayList<Position>();
	private Position previousPosition;
	
	@Override
	public void simpleUpdate(float tpf) {	
		if(this.runFirstTime){
			ap = Helicopter3D.positions();
			runFirstTime = false;
			
			//sets the first position for line drawing as (0,0,0) and will be ignored the first time round
			previousPosition=new Position(0,0,0);
		}
		
		//gets current Position from the array of Helicopter
		Position currentPosition=ap.get(arrayPos);
		
		//updates the Helicopter models position with values from trajectory array 
		h.pos.setX(currentPosition.getX());
		h.pos.setY(currentPosition.getY());
		h.pos.setZ(currentPosition.getZ());
		
		//checks if the position has changed before drawing a line and checks previousPosition is not (0,0,0)
		//this check is performed to avoid drawing lines at the end of the program when the Helicopter has already crashed
		if (!currentPosition.comparePosition(previousPosition) && !previousPosition.comparePosition(new Position(0,0,0))){

			//draws(??) a line between previous and current Position
			Line pathSegment= new Line(previousPosition.toVector3f(),currentPosition.toVector3f());			//where line starts and ends
			pathSegment.setLineWidth(3);																	//width of line
			Geometry segmentGeometry = new Geometry("Bullet", pathSegment);									//Specifying that it is a line
			Material segmentMaterial =new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");		
			segmentMaterial.setColor("Color", ColorRGBA.Red);												//set line colour to red	
			segmentGeometry.setMaterial(segmentMaterial);
			rootNode.attachChild(segmentGeometry);
		}
		//sets the updating previous Position to current Position, so that a new line can be drawn from here.
		previousPosition=currentPosition;	
		
		if (arrayPos < ap.size()-1) arrayPos++;
		h.updatePosition();
		
	}
	
	public VisController getVisualController() {
		return new VisController();
	}

	public Texture getMapTexture() throws IOException {
		return getVisualController().updateMap();
	}

}
