package visualisation3d;


import java.util.ArrayList;

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
		pic.setHeight(settings.getHeight()/5);
		pic.setPosition(0, 0);
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
	private void setTextLayer(AssetManager assetManager, BitmapFont guiFont, AppSettings settings, ArrayList<BitmapText> hudList){
		BitmapText speedText = new BitmapText(guiFont, false);          
		speedText.setSize(settings.getHeight()/45);      
		speedText.setColor(ColorRGBA.White);                            
		speedText.setText("Speed: " + " mph");             
		speedText.setLocalTranslation(0, speedText.getLineHeight()*5, 0); // position
		hud.attachChild(speedText);
		
		BitmapText altitudeText = new BitmapText(guiFont, false);          
		altitudeText.setSize(settings.getHeight()/45);
		altitudeText.setColor(ColorRGBA.White);
		altitudeText.setText("Height:");
		altitudeText.setLocalTranslation(0, altitudeText.getLineHeight()*4, 0); // position
		hud.attachChild(altitudeText);
		
		BitmapText posText = new BitmapText(guiFont, false);          
		posText.setSize(settings.getHeight()/45);
		posText.setColor(ColorRGBA.White);
		posText.setText("Position:");
		posText.setLocalTranslation(0, posText.getLineHeight()*3, 0); // position
		hud.attachChild(posText);
		
		BitmapText timeText = new BitmapText(guiFont, false);          
		timeText.setSize(settings.getHeight()/45);
		timeText.setColor(ColorRGBA.White);
		timeText.setText("");
		timeText.setLocalTranslation(0, timeText.getLineHeight()*2, 0); // position
		hud.attachChild(timeText);
		
		BitmapText popText = new BitmapText(guiFont, false);          
		popText.setSize(settings.getHeight()/45);
		popText.setColor(ColorRGBA.White);
		popText.setText("");
		popText.setLocalTranslation(0, popText.getLineHeight(), 0); // position
		hud.attachChild(popText);
		
		hudList.add(speedText);
		hudList.add(altitudeText);
		hudList.add(posText);
		hudList.add(timeText);
		hudList.add(popText);
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
	public Node createHUD(AssetManager assetManager, BitmapFont guiFont, AppSettings settings, ArrayList<BitmapText> hudList) {
		setBackgroundLayer(assetManager, settings);
		setTextLayer(assetManager, guiFont, settings, hudList);
		return hud;
	}
	
	

}
