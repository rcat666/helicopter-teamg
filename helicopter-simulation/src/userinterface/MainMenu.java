package userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Conversions;
import model.Helicopter;
import model.Position;
import repository.FileInput;
import visualisation3d.AppVisualisation3D;

import com.jme3.system.AppSettings;

//User interface class
public class MainMenu extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel xCoordLabel;
	private JLabel yCoordLabel;
	private JLabel speedLabel;
	private JLabel altitudeLabel;
	private JLabel pitchLabel;
	private JLabel angleLabel;
	private JLabel mapTypeLabel;
	
	private JSpinner xCoordSpinner;
	private JSpinner yCoordSpinner;
	private JSpinner speedSpinner;
	private JSpinner altitudeSpinner;
	private JSpinner pitchSpinner;
	private JSpinner angleSpinner;
	
	private JButton generateButton;
	private JButton resetButton;
	
	private static AppVisualisation3D app;
	
	final int initial = 0;
	
	//User interface constructor
	public MainMenu(String UITitle){
		super(UITitle);
		setResizable(false); //Window is not resizable       	
    }
	
	//Method to initialise UI with its components.
	private void initialiseUI(final Container display){
		
		//Creating JPanels to contain the components.
		final JPanel controlPanel = new JPanel();
		
		//Labels
		xCoordLabel = new JLabel("Latitude:");
		xCoordLabel.setFont(new Font("Avenir", Font.BOLD, 15));
		yCoordLabel = new JLabel("Longitude:");
		yCoordLabel.setFont(new Font("Avenir", Font.BOLD, 15));
		speedLabel = new JLabel("Speed (mph):     ");
		speedLabel.setFont(new Font("Avenir", Font.BOLD, 15));
		altitudeLabel = new JLabel("Altitude (meters):    ");
		altitudeLabel.setFont(new Font("Avenir", Font.BOLD, 15));
		pitchLabel = new JLabel("Pitch (°):    ");
		pitchLabel.setFont(new Font("Avenir", Font.BOLD, 15));
		angleLabel = new JLabel("Direction (°):    ");
		angleLabel.setFont(new Font("Avenir", Font.BOLD, 15));
		mapTypeLabel = new JLabel("Map type:    ");
		mapTypeLabel.setFont(new Font("Avenir", Font.BOLD, 15));
		
		//Formatting the JSpinners with preset models.
		SpinnerModel xCoordModel =  new SpinnerNumberModel(55.8580,-180,180,0.0001);
		SpinnerModel yCoordModel =  new SpinnerNumberModel(-4.2590,-180,180,0.0001);
		SpinnerModel speedLimits = new SpinnerNumberModel(0,0,500,0.1);
		SpinnerModel altitudeLimits = new SpinnerNumberModel(0,0,20000,0.1);
		SpinnerModel pitchLimits = new SpinnerNumberModel(0,-90,90,0.1);
		SpinnerModel angleLimits = new SpinnerNumberModel(0,0,360,0.1);
		
		//JSpinners
		xCoordSpinner = new JSpinner(xCoordModel);
		xCoordSpinner.setEditor(new JSpinner.NumberEditor(xCoordSpinner, "0.0000"));
		xCoordSpinner.setFont(new Font("Avenir", Font.BOLD, 15));
		yCoordSpinner = new JSpinner(yCoordModel);
		yCoordSpinner.setEditor(new JSpinner.NumberEditor(yCoordSpinner, "0.0000"));
		yCoordSpinner.setFont(new Font("Avenir", Font.BOLD, 15));
		speedSpinner = new JSpinner(speedLimits);
		speedSpinner.setFont(new Font("Avenir", Font.BOLD, 15));
		altitudeSpinner = new JSpinner(altitudeLimits);
		altitudeSpinner.setFont(new Font("Avenir", Font.BOLD, 15));
		pitchSpinner = new JSpinner(pitchLimits);
		pitchSpinner.setFont(new Font("Avenir", Font.BOLD, 15));
		angleSpinner = new JSpinner(angleLimits);
		angleSpinner.setFont(new Font("Avenir", Font.BOLD, 15));
		
		xCoordSpinner.setToolTipText("<html>Enter the <b><u>longitude</u></b> here, to 4 decimal places.<br><b>Positive</b> values are north.<br><b>Negative</b> values are south.");
		yCoordSpinner.setToolTipText("<html>Enter the <u><b>latitude</b></u> here, to 4 decimal places.<br><b>Positive</b> values are west.<br><b>Negative</b> values are east.");
		speedSpinner.setToolTipText("<html>Enter the <u><b>speed</b></u> of the helicopter here.<br>The speed is measured in miles per hour.<br>Must be greater than or equal to 0.");
		altitudeSpinner.setToolTipText("<html>Enter the <u><b>altitude</b></u> of the helicopter here.<br>The altitude is measured in meters.<br>Must be greater than or equal to 0.");
		pitchSpinner.setToolTipText("<html>Enter the <u><b>pitch</b></u> of the helicopter here.<br>The pitch is measured in degrees.<br>Must be between -90 and 90.");
		angleSpinner.setToolTipText("<html>Enter the <u><b>direction</b></u> of the helicopter here.<br>The heading is measured in degrees.<br>Must be between 0 and 360.");
		
		//Pulldown menu
		String[] mapTypes = {"Map","Satellite","Hybrid"};
		final JComboBox mapTypeList = new JComboBox(mapTypes);
		mapTypeList.setFont(new Font("Avenir", Font.BOLD, 15));
		
		//Buttons
		generateButton = new JButton("Generate");
		generateButton.setFont(new Font("Avenir", Font.BOLD, 15));
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Avenir", Font.BOLD, 15));
		
		generateButton.setToolTipText("Begin simulation");
		resetButton.setToolTipText("Resets all the fields to 0.");
		mapTypeList.setToolTipText("<html>Choose a map type to run the simulation in.<br><b>Map</b> shows a map with roads and names.<br><b>Satellite</b> shows satellite images.<br<b>Hybrid</b> shows a combination of the map and satellite images.");
		
		//Button listeners
		generateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double xCoordinate = (double) xCoordSpinner.getValue();
				double yCoordinate = (double) yCoordSpinner.getValue();
				double speed = (double) speedSpinner.getValue();
				double altitude = (double) altitudeSpinner.getValue();
				double pitch = (double) pitchSpinner.getValue();
				double angle = (double) angleSpinner.getValue();
				String mapTypeValue = (String) mapTypeList.getSelectedItem();
				String mapType = null;
				
				if (mapTypeValue.equals("Map"))mapType="map";
				else if (mapTypeValue.equals("Satellite"))mapType = "sat";
				else if (mapTypeValue.equals("Hybrid"))mapType = "hyb";
				else if (mapTypeValue.equals(null))mapType = "map";
				
				Helicopter helicopter = FileInput.helicopterFromFile("./assets/Data/HelicopterDataSheet.csv");	//initiating helicopter with data from a file
				helicopter.setAltitude(altitude);
				helicopter.setAttitude(0);
				helicopter.setHeading(angle);
				helicopter.setPitch(pitch);
				helicopter.setSpeed(speed);
				helicopter.setPos(new Position(0,(float) Conversions.metersToUnits(altitude),0));
				
				double[] coordinates = {xCoordinate, yCoordinate};
				
				AppSettings setting = new AppSettings(true);
				setting.setTitle("TP3 Project");
				setting.setSettingsDialogImage("Textures/dialog.jpg");
				app = new AppVisualisation3D(helicopter, coordinates, mapType);
				app.setSettings(setting);
				new Thread(new Runnable(){
					public void run(){app.start();}}).start();
			}}
		);
		
		//Reset button will reset all JSpinner values to their original value.
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				xCoordSpinner.setValue((double)0);
				yCoordSpinner.setValue((double)0);
				speedSpinner.setValue((double)0);
				altitudeSpinner.setValue((double)0);
				pitchSpinner.setValue((double)0);
				angleSpinner.setValue((double)0);}
		});
		
		//Create grid bag layout for panel
		controlPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
        c.insets = new Insets(0,4,0,0);
        controlPanel.add(xCoordLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        controlPanel.add(yCoordLabel, c);
        c.gridx = 0;
        c.gridy = 1;
        controlPanel.add(xCoordSpinner,c);
        c.gridx = 1;
        c.gridy = 1;
        controlPanel.add(yCoordSpinner, c);
        c.insets = new Insets(10,4,0,0);
        c.gridx = 0;
        c.gridy = 2;
        controlPanel.add(speedLabel,c);
        c.gridx = 1;
        c.gridy = 2;
        controlPanel.add(speedSpinner, c);
        c.insets = new Insets(0,4,0,0);
        c.gridx = 0;
        c.gridy = 3;
        controlPanel.add(altitudeLabel,c);
        c.gridx = 1;
        c.gridy = 3;
        controlPanel.add(altitudeSpinner, c);
        c.gridx = 0;
        c.gridy = 4;
        controlPanel.add(pitchLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        controlPanel.add(pitchSpinner, c);
        c.gridx = 0;
        c.gridy = 5;
        controlPanel.add(angleLabel, c);
        c.gridx = 1;
        c.gridy = 5;
        controlPanel.add(angleSpinner, c);
        c.gridx = 0;
        c.gridy = 6;
        controlPanel.add(mapTypeLabel, c);
        c.gridx = 1;
        c.gridy = 6;
        controlPanel.add(mapTypeList,c);
        c.insets = new Insets(10,5,5,5);
        c.gridx = 0;
        c.gridy = 7;
        controlPanel.add(generateButton, c);
        c.gridx = 1;
        c.gridy = 7;
        controlPanel.add(resetButton, c);
        
        display.add(controlPanel);     
		display.setVisible(true);	
	}
	
	//Method to create and display the UI
	protected static void createAndShowGUI(){
		//Create user interface window
		MainMenu window = new MainMenu("TP3 Project");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set up button frame
		window.initialiseUI(window.getContentPane());
		
		//Display the window
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}


