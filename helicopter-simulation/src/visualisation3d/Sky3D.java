package visualisation3d;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

public class Sky3D {
	
	private Spatial createSky3D(AssetManager assetManager){
		Texture west = assetManager.loadTexture("Textures/TropicalSunnyDayRight2048.png");
		Texture east = assetManager.loadTexture("Textures/TropicalSunnyDayLeft2048.png");
		Texture north = assetManager.loadTexture("Textures/TropicalSunnyDayFront2048.png");
		Texture south = assetManager.loadTexture("Textures/TropicalSunnyDayBack2048.png");
		Texture up = assetManager.loadTexture("Textures/TropicalSunnyDayUp2048.png");
		Texture down = assetManager.loadTexture("Textures/TropicalSunnyDayDown2048.png");
		Spatial sky = SkyFactory.createSky(assetManager, west, east, north, south, up, down);
		return sky;
	}
	
	public Spatial getSky(AssetManager assetManager){
		return this.createSky3D(assetManager);
	}
		
}
