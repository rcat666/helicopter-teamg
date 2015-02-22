package model;

import repository.FileInput;
import visualisation3d.AppVisualisation3D;

public class Main {
	
	private static AppVisualisation3D app;
	

	public static void main(String[] args) {
		
		Helicopter helicopter = FileInput.helicopterFromFile("./assets/Data/HelicopterDataSheet.csv");	//initiating helicopter with data from a file
		
		System.out.println(helicopter.toString());														//printing out information to check filereader
		
		app = new AppVisualisation3D(helicopter);
		app.start();
		
	}

}
