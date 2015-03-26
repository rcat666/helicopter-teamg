package visualisation3d;


import java.util.ArrayList;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;

public class ControlHUD {
	
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
		pic.setWidth((float) (settings.getWidth()/3.5));
		pic.setHeight(settings.getHeight()/5);
		pic.setPosition((float) (settings.getWidth()-settings.getWidth()/3.5), 0 );
		hud.attachChild(pic);
	}
	
	/**
	 * Creates the text line in the box, notices the posistion line.
	 * Each line of text needs to be at the height of the 0 - the heights of the line above them
	 * hence the * factor.
	 * 
	 * @param assetManager
	 * @param guiFont
	 * @param settings
	 */
	private void setTextLayer(AssetManager assetManager, BitmapFont guiFont, AppSettings settings, ArrayList<BitmapText> hudList){
		BitmapText title = new BitmapText(guiFont, false);          
		title.setSize(settings.getHeight()/45);      
		title.setColor(ColorRGBA.White);                            
		title.setText("Controls:");             
		title.setLocalTranslation(settings.getWidth()- title.getLineWidth(), title.getLineHeight()*5, 0); // position
		hud.attachChild(title);
		
		BitmapText wasd = new BitmapText(guiFont, false);          
		wasd.setSize(settings.getHeight()/45);
		wasd.setColor(ColorRGBA.White);
		wasd.setText("W,A,S,D: move around.");
		wasd.setLocalTranslation(settings.getWidth()- wasd.getLineWidth(), wasd.getLineHeight()*4, 0); // position
		hud.attachChild(wasd);
		
		BitmapText mapRemove = new BitmapText(guiFont, false);          
		mapRemove.setSize(settings.getHeight()/45);
		mapRemove.setColor(ColorRGBA.White);
		mapRemove.setText("M : Toggle map on and off");
		mapRemove.setLocalTranslation(settings.getWidth()- mapRemove.getLineWidth(), mapRemove.getLineHeight()*3, 0); // position
		hud.attachChild(mapRemove);
		
		BitmapText pathRemove = new BitmapText(guiFont, false);          
		pathRemove.setSize(settings.getHeight()/45);
		pathRemove.setColor(ColorRGBA.White);
		pathRemove.setText("P : Toggle path on and off.");
		pathRemove.setLocalTranslation(settings.getWidth()- pathRemove.getLineWidth(), pathRemove.getLineHeight()*2, 0); // position
		hud.attachChild(pathRemove);
		
		BitmapText areaRemove = new BitmapText(guiFont, false);          
		areaRemove.setSize(settings.getHeight()/45);
		areaRemove.setColor(ColorRGBA.White);
		areaRemove.setText("O : Toggle affected area on and off");
		areaRemove.setLocalTranslation(settings.getWidth()- areaRemove.getLineWidth(), areaRemove.getLineHeight(), 0); // position
		hud.attachChild(areaRemove);
		
		hudList.add(title);
		hudList.add(wasd);
		hudList.add(mapRemove);
		hudList.add(pathRemove);
		hudList.add(areaRemove);
	}
	
	/**
	 * Constructor, return a hud node that can be attached to the guiNode or the rootNode
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