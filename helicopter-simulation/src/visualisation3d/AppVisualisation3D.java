package visualisation3d;

import java.util.ArrayList;

import repository.HeliStats;
import model.Conversions;
import model.Helicopter;
import model.Population;
import model.Position;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.ActionListener;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class AppVisualisation3D extends SimpleApplication {

	private Helicopter3D heli3Dmodel;
	private int exec = 0;
	private boolean runFirstTime = true;
	private boolean isCrashed = false;
	private Helicopter helicopter;
	private Geometry map;
	private Geometry segmentGeometry;
	private Node path = new Node();
	private Node area = new Node();
	
	private ArrayList<HeliStats> statsArray = new ArrayList<HeliStats>();
	private ArrayList<BitmapText> hudText = new ArrayList<BitmapText>();

	private ArrayList<BitmapText> hudTextControls = new ArrayList<BitmapText>();
	double[] coordinates;
	String mapType;
	
	@Override
	public void simpleInitApp() {

		// Attach map tile 
		map = new Tile3D().getTile3D(assetManager, this.coordinates, this.mapType);
		rootNode.attachChild(map);
		
		// Attach path node to enable controls
		rootNode.attachChild(path);

		// Attach node for area affected
		rootNode.attachChild(area);

		// Simple sky
		rootNode.attachChild(new Sky3D().getSky(assetManager));

		// Creates helicopter
		heli3Dmodel=new Helicopter3D(assetManager, this.helicopter);
		heli3Dmodel.addToRootNode(rootNode);
		statsArray = heli3Dmodel.stats();
		
		// Attach HUD
		guiNode.attachChild(new HUD().createHUD(assetManager, guiFont, settings, hudText));
		rootNode.attachChild(guiNode);
		
		hudText.get(0).setText("Name: " + this.helicopter.getHelicopterType().getName());
		
		// Attach Control HUD
		guiNode.attachChild(new ControlHUD().createHUD(assetManager, guiFont, settings, hudTextControls));
		rootNode.attachChild(guiNode);
		
		//calculating people affected and printing it out in the console
		//MOST THINGS ARE CURRENTLY HARDCODED!!
		double peopleAffected = Population.calculatePeopleAffected(statsArray.get(0).getPosition(), statsArray.get(0).getPosition(), this.coordinates[0], this.coordinates[1], 50*Conversions.metersToUnit, 50*Conversions.metersToUnit);
		hudText.get(5).setText("People at risk: " + String.format("%.2f",peopleAffected));
		hudText.get(3).setText("Position: " + String.format("%.4f  %.4f", coordinates[0] , coordinates[1]));
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
		
		
		setDisplayFps(false);
		setDisplayStatView(false);
		
		
		initKeys();
	}

	private void initKeys() {
		inputManager.addMapping("Map", new KeyTrigger(KeyInput.KEY_M));
	    inputManager.addMapping("Path", new KeyTrigger(KeyInput.KEY_P));
	    inputManager.addMapping("Area", new KeyTrigger(KeyInput.KEY_O));
	    inputManager.addListener(actionListener, "Map", "Path", "Area");
	}

	private ActionListener actionListener = new ActionListener() { 
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("Map") && !keyPressed) {
				if (rootNode.hasChild(map)) rootNode.detachChild(map);
				else rootNode.attachChild(map);
			}
			if (name.equals("Path") && !keyPressed) {
				if (rootNode.hasChild(path)) rootNode.detachChild(path);
				else rootNode.attachChild(path);
			}
			if (name.equals("Area") && !keyPressed) {
				if (rootNode.hasChild(area)) rootNode.detachChild(area);
				else rootNode.attachChild(area);
			}
		}
	};
	
	private int arrayPos = 0;
	private Position previousPosition;
	

	@Override
	public void simpleUpdate(float tpf) {
		if (this.runFirstTime) {
			//for (Position posi : ap) System.out.println("X: " + posi.getX() + " Y: " + posi.getY()+ " Z: " + posi.getZ());
			runFirstTime = false;

			// sets the first position for line drawing as the first position specified in arrayPos
			// ignored the first time round
			previousPosition = statsArray.get(arrayPos).getPosition();
			
		}

		// gets current Position from the array of Helicopter
		Position currentPosition = statsArray.get(arrayPos).getPosition();
		
		// updates the textboxes with speed and height
		hudText.get(1).setText("Speed: " + String.format("%.2f", statsArray.get(arrayPos).getSpeed()) + " mph");
		hudText.get(2).setText("Height: " + String.format("%.2f", Conversions.metersToUnit*(statsArray.get(arrayPos).getPosition().getY()))+ " m");
		hudText.get(4).setText("Time: " + String.format("%.2f", statsArray.get(arrayPos).getTime())+ " s");
		// sets the Helicopter models position with values from trajectory
		// array
		heli3Dmodel.pos.setX(currentPosition.getX());
		heli3Dmodel.pos.setY(currentPosition.getY());
		heli3Dmodel.pos.setZ(currentPosition.getZ());
		
		// creates 3dpath and attaches it to path node so that can be toggled
		segmentGeometry = new Path3D().getPath3D(currentPosition, previousPosition, assetManager);
		if (segmentGeometry != null) path.attachChild(segmentGeometry);
		previousPosition = currentPosition;
		
		// update the position of the helicopter and get next position
		if (arrayPos < statsArray.size() - 1) arrayPos++; 
		else if (!this.isCrashed && this.exec == 0) {this.isCrashed = true; this.exec++;}
		heli3Dmodel.updatePosition();
		
		
		
		// creates the affected area
		if (this.isCrashed) {
			area.attachChild(new AffectedArea3D().getAffectedArea(assetManager, currentPosition));
			this.isCrashed = false;
		}
	}
	
	public AppVisualisation3D(Helicopter helicopter, double[] coordinates, String mapType){
		super();
		this.helicopter=helicopter;
		this.coordinates=coordinates;
		this.mapType=mapType;
	}

	

}
