package model;

import visualisation3d.AppVisualisation3D;

public class Main {
	
	private static AppVisualisation3D app;
	

	public static void main(String[] args) {
		//initalising example helicopter to make the software run
		HelicopterType helitype= new HelicopterType("Super Puma AS332 L2", 16.79, 0.0, 16.2, 4.97, 4660.0);
		Helicopter helicopter=new Helicopter(helitype, 200, 0, 150, 0, new Position(0, 200, 0), 0);
		
		app = new AppVisualisation3D(helicopter);
		app.start();
		
	}

}
