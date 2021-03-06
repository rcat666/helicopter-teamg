package visualisation3d;
import java.io.IOException;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

public class Tile3D {	
	
	/**
	 * Creates a box, assigned to a geometry object, with a material, a colour and attaches it to the node for vis		
	 * @param assetManager
	 * @return
	 */
	private Geometry createTile3D(AssetManager assetManager, double[] coordinates, String mapType){
		Box tile = new Box(256, 0.1f, 256);
		Geometry geometry = new Geometry("Box", tile);
		Material material = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
		try {
			material.setTexture("ColorMap", getMapTexture(coordinates, mapType));
		} catch (IOException e) {
			new Exception("Couldn't load map");
		}
		geometry.setMaterial(material);
		return geometry;
	}
	
	private Texture getMapTexture(double[] coordinates, String mapType) throws IOException {
		return new VisController().updateMap(coordinates, mapType);
	}
	
	public Geometry getTile3D(AssetManager assetManager, double[] coordinates, String mapType){
		return this.createTile3D(assetManager,coordinates, mapType);
	}
}