package model;

import com.jme3.system.AppSettings;

import repository.FileInput;
import visualisation3d.AppVisualisation3D;

public class Main {
	
	private static AppVisualisation3D app;
	

	public static void main(String[] args) {
		
		Helicopter helicopter = FileInput.helicopterFromFile("./assets/Data/HelicopterDataSheet.csv");	//initiating helicopter with data from a file
		
		System.out.println(helicopter.toString());														//printing out information to check filereader
		
		double[] coordinates = {55.8580, -4.2590};
		
		AppSettings setting = new AppSettings(true);
		setting.setTitle("Helicopter Simulation");
		setting.setSettingsDialogImage("Textures/dialog.jpg");
		String mapType = "hyb";
		app = new AppVisualisation3D(helicopter, coordinates, mapType);
		app.setSettings(setting);
		app.start();
		
	}

}
