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
		String mapType = "hyb";
		
		AppSettings settings = new AppSettings(true);
		settings.setTitle("Helicopter Simulation");
		settings.setSettingsDialogImage("Textures/dialog.jpg");
		app = new AppVisualisation3D(helicopter, coordinates, mapType);
		app.setSettings(settings);
		app.start();
	}
}