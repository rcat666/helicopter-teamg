package visualisation3d;


import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;

public class HUD {
	
	private Node hud = new Node();
	
	
	/**
	 * Creates the transparent box holding the information
	 * 
	 * @param assetManager asset manager from the app
	 * @param settings settings from the app to produce appropriate output
	 */
	private void setBackgroundLayer(AssetManager assetManager, AppSettings settings){
		Picture pic = new Picture("HUD Picture");
		pic.setImage(assetManager, "Textures/HUD.png", true);
		pic.setWidth(settings.getWidth()/3);
		pic.setHeight(settings.getHeight()/3);
		pic.setPosition(0, settings.getHeight()-settings.getHeight()/3);
		hud.attachChild(pic);
	}
	
	/**
	 * Creates the text line in the box, notices the posistion line!
	 * Each line of text needs to be at the height of the 0 - the heights of the line above them
	 * hence the * factor.
	 * 
	 * @param assetManager
	 * @param guiFont
	 * @param settings
	 */
	private void setTextLayer(AssetManager assetManager, BitmapFont guiFont, AppSettings settings){
		BitmapText hudText = new BitmapText(guiFont, false);          
		hudText.setSize(guiFont.getCharSet().getRenderedSize());      
		hudText.setColor(ColorRGBA.White);                            
		hudText.setText("Speed: " + " mph");             
		hudText.setLocalTranslation(0, settings.getHeight(), 0); // position
		hud.attachChild(hudText);
		
		BitmapText hudText1 = new BitmapText(guiFont, false);          
		hudText1.setSize(guiFont.getCharSet().getRenderedSize());
		hudText1.setColor(ColorRGBA.White);
		hudText1.setText("Height:");
		hudText1.setLocalTranslation(0, settings.getHeight()-hudText.getLineHeight(), 0); // position
		hud.attachChild(hudText1);
		
		BitmapText hudText2 = new BitmapText(guiFont, false);          
		hudText2.setSize(guiFont.getCharSet().getRenderedSize());
		hudText2.setColor(ColorRGBA.White);
		hudText2.setText("Position:");
		hudText2.setLocalTranslation(0, settings.getHeight()-hudText.getLineHeight()*2, 0); // position
		hud.attachChild(hudText2);
	}
	
	/**
	 * Constructor, return a hud node that can be attached to the guiNode or the rootNode.
	 * !!It is preferred to attach these elements to the guiNode for rendering properties!!
	 * 
	 * @param assetManager
	 * @param guiFont
	 * @param settings
	 * @return
	 */
	public Node createHUD(AssetManager assetManager, BitmapFont guiFont, AppSettings settings) {
		setBackgroundLayer(assetManager, settings);
		setTextLayer(assetManager, guiFont, settings);
		return hud;
	}
	
	

}
