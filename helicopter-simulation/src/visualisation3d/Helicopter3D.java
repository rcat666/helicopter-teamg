package visualisation3d;

import java.util.ArrayList;

import repository.HeliStats;
import repository.TrajectoryInformation;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import mathematicalModel.CrashTrajectory;
import model.Conversions;
import model.Helicopter;
import model.Position;

public class Helicopter3D {

	public Position pos;

	private Spatial helicopter3DModel;
	
	private Helicopter helicopter;

	/**
	 * This function is responsible for creating the top level 3D object that
	 * represents the helicopter
	 * 
	 * @param assetManager
	 */
	private void createHelicopter3D(AssetManager assetManager, Helicopter helicopter) {
		helicopter3DModel = assetManager.loadModel("Models/helicopter.obj");
		
		//get length and height of 3D model (x and y axis)
		BoundingBox heliBounding=(BoundingBox) helicopter3DModel.getWorldBound();
		
		
		helicopter3DModel.scale((float) getScalingFactor(heliBounding,helicopter));
		helicopter3DModel.rotate(0, (float) Conversions.degreeToRadians(helicopter.getHeading()), 0); // rotate the model to the correct direction

		Material helicopterMaterial = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");

		helicopterMaterial.setColor("Color", ColorRGBA.White);
		this.helicopter3DModel.setMaterial(helicopterMaterial);
	
		
	}

	/**
	 * The constructor to set up the helicopter instance, including the 3D
	 * representation
	 * 
	 */
	public Helicopter3D(AssetManager assetManager, Helicopter helicopter) {
		this.helicopter=helicopter;
		this.createHelicopter3D(assetManager, helicopter);
		this.pos = helicopter.getPos();
		this.updatePosition();
	}

	public ArrayList<HeliStats> stats() {
		TrajectoryInformation traject = new TrajectoryInformation(this.helicopter);
		CrashTrajectory.calculateThrowTrajectory(traject);
		return traject.getStats();
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
		this.helicopter3DModel.setLocalTranslation(this.pos.toVector3f());
	}
	
	//method to get the scaling factor of the helicopter model based on the heights and length of the used model and the real helicopter data
	public double getScalingFactor(BoundingBox heliBounding,Helicopter helicopter){
		double modelLength=heliBounding.getXExtent()*2.0;									//getExtent returns distance from center of box and we want the whole length and height
		double modelHeight=heliBounding.getYExtent()*2.0;
		
		//get actual length, height and rotor diameter of helicopter and convert to units
		double actualLength = Conversions.metersToUnits(helicopter.getHelicopterType().getLength());
		double actualHeight = Conversions.metersToUnits(helicopter.getHelicopterType().getHeight());
		
		double scalingFactorLength = actualLength/modelLength;
		double scalingFactorHeight = actualHeight/modelHeight;
		
		return (scalingFactorLength+scalingFactorHeight)/2.0;				//getting the average scaling factor from the scaling factors for the height and length
	}

}
