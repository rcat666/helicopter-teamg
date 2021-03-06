package visualisation3d;
import helicopter.Position;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Line;

/**
 * This class draws the path of the helicopter within the simulation.
 */
public class Path3D {

	private Geometry createPath3D(Position currentPosition, Position previousPosition, AssetManager assetManager){
		Line pathSegment = null;
		Geometry segmentGeometry = null;
		Material segmentMaterial = null;
		if (!isSamePosition(currentPosition, previousPosition)){
			pathSegment = new Line(previousPosition.toVector3f(), currentPosition.toVector3f()); // where line starts and ends
			pathSegment.setLineWidth(3);
			segmentGeometry = new Geometry("Bullet", pathSegment); // Specifying that it is a line
			segmentMaterial = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
			segmentMaterial.setColor("Color", ColorRGBA.Red);
			segmentGeometry.setMaterial(segmentMaterial);
		}
		return segmentGeometry;
	}
	
	public Geometry getPath3D(Position currentPosition, Position previousPosition, AssetManager assetManager){
		return createPath3D(currentPosition, previousPosition, assetManager);
	}
	
	private boolean isSamePosition(Position currentPosition, Position previousPosition){
		return currentPosition.comparePosition(previousPosition);
	}
}