package visualisation3d;

import model.Conversions;
import model.Position;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class AffectedArea3D {
	
	private Geometry createAffectedArea(AssetManager assetManager, Position currentPosition){
		Box b = new Box((float)Conversions.metersToUnits(100),0.1f,(float)Conversions.metersToUnits(100)); //creates shape for holding texture
		Geometry geometry = new Geometry("Box", b);
		Material material = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
		material.setTexture("ColorMap", assetManager.loadTexture("Textures/affected_area.png")); //loads texture asset
		material.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		geometry.setQueueBucket(Bucket.Transparent); // jmonkey methods to enable transparency 
		geometry.setMaterial(material);
		
		// moves the shape to the position of the helicopter and y variables is set to 0.1 to avoid blurring while moving.
		geometry.setLocalTranslation(new Vector3f(currentPosition.getX(), 0.1f, currentPosition.getZ()));
		return geometry;
	}
	
	public Geometry getAffectedArea(AssetManager assetManager, Position currentPosition){
		return createAffectedArea(assetManager, currentPosition);
	}
}
