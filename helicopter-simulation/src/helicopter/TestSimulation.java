package helicopter;
import com.jme3.system.AppSettings;
import repository.FileInput;
import visualisation3d.AppVisualisation3D;

/**
 * This class is for testing the simulation with set variables.
 */
public class TestSimulation {
	
	private static AppVisualisation3D app;
	
	public static void main(String[] args) {
		
		//Variables used for testing the simulation.
		double[] coordinates = {55.8580, -4.2590};
		Helicopter helicopter = FileInput.helicopterFromFile("./assets/Data/HelicopterDataSheet.csv");	//Initiating helicopter with data from a file.
		String mapType = "hyb";		//Sets map type to retrieve to hybrid.
		
		AppSettings settings = new AppSettings(true);
		settings.setTitle("Helicopter Simulation");						//Sets title of simulation window.
		settings.setSettingsDialogImage("Textures/dialog.jpg");			//Sets the jMonkey settings window to contain the University of Glasgow logo.
		app = new AppVisualisation3D(helicopter, coordinates, mapType);
		app.setSettings(settings);
		app.start();													//Starts jMonkey engine.
	}
}