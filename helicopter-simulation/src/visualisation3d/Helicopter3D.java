package visualisation3d;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import model.Position;

public class Helicopter3D {

	public float altitude;
	public double heading;
	public double pitch;
	public double speed;
	public Position pos;

	private Geometry geoObject;

	/**
	 * This function is responsible for creating the top level 3D object that
	 * reprosents the helicopter
	 * 
	 * @param assetManager
	 */
	private void drawHelicopter(AssetManager assetManager) {
		Sphere cube1Mesh = new Sphere(10, 10, 10);// size of object
		this.geoObject = new Geometry("My Textured Box", cube1Mesh);
		Material cube1Mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");

		cube1Mat.setColor("Color", ColorRGBA.White);
		this.geoObject.setMaterial(cube1Mat);
	}

	/**
	 * The constructor to set up the helicopter instance, including the 3D
	 * representation
	 * 
	 * @param assetManager
	 */
	public Helicopter3D(AssetManager assetManager) {
		this.drawHelicopter(assetManager);
		this.pos = new Position(128, 128, 50);
	}

	/**
	 * This function is responsible for adding the helicopter to the root node
	 * 
	 * @param rootNode
	 */
	public void addToRootNode(Node rootNode) {
		rootNode.attachChild(this.geoObject);
	}

	/**
	 * This method is responsible for setting the location of the object
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void updatePosition() {
		//position of object = top to bottom, zoom in / out, left to right
		this.geoObject.setLocalTranslation(this.pos.toVector3f()); 
	}

}