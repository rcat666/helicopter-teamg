package visualisation3d;

import java.util.ArrayList;

import repository.Trajectory;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

import mathematicalModel.CrashTrajectory;
import model.Helicopter;
import model.HelicopterType;
import model.Position;

public class Helicopter3D {

	public Position pos;

	private Spatial helicopter3DModel;

	/**
	 * This function is responsible for creating the top level 3D object that
	 * reprosents the helicopter
	 * 
	 * @param assetManager
	 */
	private void drawHelicopter(AssetManager assetManager) {
		//loading the helicopter model
		helicopter3DModel=assetManager.loadModel("Models/helicoptero_1.obj");
		//making it bigger
		helicopter3DModel.scale(5);
		//rotating it so that it looks into the right direction
		helicopter3DModel.rotate(0, (float) Math.PI, 0);

		Material helicopterMaterial = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");

		helicopterMaterial.setColor("Color", ColorRGBA.White);
		this.helicopter3DModel.setMaterial(helicopterMaterial);
	}

	/**
	 * The constructor to set up the helicopter instance, including the 3D
	 * representation
	 * ArrayList<Position> ap = new ArrayList<Position>();
		for (int i=200;i>0;i=i-2){
			ap.add(new Position(0,i,0));
		}
	 * @param assetManager
	 */
	public Helicopter3D(AssetManager assetManager) {
		this.drawHelicopter(assetManager);
		this.pos = new Position(0, 200, 0);
		this.updatePosition();
		
		//GERMAN TEMPORARY STUFF TO MAKE THINGS WORK
		

	}

	public static ArrayList<Position> positions(){
		Trajectory traject = new Trajectory(new Helicopter(null, 200, 0, 50, 0, new Position(0,200,0), 45));
		CrashTrajectory.calculateThrowTrajectory(traject);
		return traject.getTrajectory();
	}
	
	
	
	/**
	 * This function is responsible for adding the helicopter to the root node
	 * 
	 * @param rootNode
	 */
	public void addToRootNode(Node rootNode) {
		rootNode.attachChild(this.helicopter3DModel);
	}

	/**
	 * This method is responsible for setting the location of the object
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void updatePosition() {
		this.helicopter3DModel.setLocalTranslation(this.pos.toVector3f()); //position of object = top to bottom, zoom in / out, left to right 
	}

}
