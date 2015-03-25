package userinterface;
import helicopter.Helicopter;
import helicopter.HelicopterModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import repository.FileInput;
import visualisation3d.AppVisualisation3D;
import com.jme3.system.AppSettings;

/**
 * Create the window for the user inputs.
 * @author finlaymaciver
 *
 */

public class UserInputWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel xCoordLabel;
	private JLabel yCoordLabel;
	private JLabel speedLabel;
	private JLabel altitudeLabel;
	private JLabel pitchLabel;
	private JLabel angleLabel;
	private JLabel mapTypeLabel;
	private JLabel helicopterTypeLabel;
	
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
	public UserInputWindow(String UITitle){
		super(UITitle);
		setResizable(false); //Window is not resizable       	
    }
	
	//Method to initialise UI with its components.
	private void initialiseUI(final Container display) throws IOException{
		
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
		helicopterTypeLabel = new JLabel("Helicopter model:    ");
		helicopterTypeLabel.setFont(new Font("Avenir", Font.BOLD, 15));
		
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
		yCoordSpinner.setToolTipText("<html>Enter the <u><b>latitude</b></u> here, to 4 decimal places.<br><b>Positive</b> values are east.<br><b>Negative</b> values are west.");
		speedSpinner.setToolTipText("<html>Enter the <u><b>speed</b></u> of the helicopter here.<br>The speed is measured in miles per hour.<br>Must be greater than or equal to 0.");
		altitudeSpinner.setToolTipText("<html>Enter the <u><b>altitude</b></u> of the helicopter here.<br>The altitude is measured in meters.<br>Must be greater than or equal to 0.");
		pitchSpinner.setToolTipText("<html>Enter the <u><b>pitch</b></u> of the helicopter here.<br>The pitch is measured in degrees.<br>Must be between -90 and 90.");
		angleSpinner.setToolTipText("<html>Enter the <u><b>direction</b></u> of the helicopter here.<br>The heading is measured in degrees.<br>Must be between 0 and 360.");
		
		//Pulldown menu
		String[] mapTypes = {"Map","Satellite","Hybrid"};
		final JComboBox mapTypeList = new JComboBox(mapTypes);
		mapTypeList.setFont(new Font("Avenir", Font.BOLD, 15));
		
		String[] helicopterTypes = {"Bell 204B", "Super Puma AS332L2", "Sikorsky S76C++", "Sikorsky S64C", "Kamov Ka27", "Apache AH1"};
		final JComboBox helicopterTypeList = new JComboBox(helicopterTypes);
		helicopterTypeList.setFont(new Font("Avenir", Font.BOLD, 15));
		
		//Buttons
		generateButton = new JButton("Generate");
		generateButton.setFont(new Font("Avenir", Font.BOLD, 15));
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Avenir", Font.BOLD, 15));
		generateButton.setToolTipText("Begin simulation");
		resetButton.setToolTipText("Resets all the fields to 0.");
		mapTypeList.setToolTipText("<html>Choose a map type to run the simulation in.<br><b>Map</b> shows a map with roads and names.<br><b>Satellite</b> shows satellite images.<br<b>Hybrid</b> shows a combination of the map and satellite images.");
		helicopterTypeList.setToolTipText("<html>Choose a helicopter type to run the simulation with.");

		//Image for compass
		final BufferedImage picture = ImageIO.read(new File("assets/Textures/compass.png"));
		final JLabel picLabel = new JLabel(new ImageIcon(picture));
		
		//Image for pitch
		BufferedImage pitchPicture = ImageIO.read(new File("assets/Textures/Pitchicon.png"));
		JLabel pitchPicLabel = new JLabel(new ImageIcon(pitchPicture));
		
		//JSpinner listener
		angleSpinner.addChangeListener(new ChangeListener(){
			
			//Change image rotation
			@Override
			public void stateChanged(ChangeEvent e) {
				 double angle = (double) angleSpinner.getValue();
				 picLabel.setIcon(new ImageIcon(rotateImage(picture,angle)));
			}});
		
		
		//Button listeners
		generateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double xCoordinate = (double) xCoordSpinner.getValue();
				double yCoordinate = (double) yCoordSpinner.getValue();
				double speed = (double) speedSpinner.getValue();
				double altitude = (double) altitudeSpinner.getValue();
				double pitch = (double) pitchSpinner.getValue();
				double direction = (double) angleSpinner.getValue();
				String mapTypeValue = (String) mapTypeList.getSelectedItem();
				String mapType = null;
				
				if (mapTypeValue.equals("Map"))mapType="map";
				else if (mapTypeValue.equals("Satellite"))mapType = "sat";
				else if (mapTypeValue.equals("Hybrid"))mapType = "hyb";
				else if (mapTypeValue.equals(null))mapType = "map";
				
				String helicopterFile = null;
				String helicopterTypeValue = (String) helicopterTypeList.getSelectedItem();
				if (helicopterTypeValue.equals("Bell 204B")){helicopterFile = "Bell204B.csv";}
				else if (helicopterTypeValue.equals("Super Puma AS332L2")){helicopterFile = "SuperPumaAS332L2.csv";}
				else if (helicopterTypeValue.equals("Sikorsky S76C++")){helicopterFile = "SikorskyS76C++.csv";}
				else if (helicopterTypeValue.equals("Sikorsky S64C")){helicopterFile = "SikorskyS64C.csv";}
				else if (helicopterTypeValue.equals("Kamov Ka27")){helicopterFile = "KamovKa27.csv";}
				else if (helicopterTypeValue.equals("Apache AH1")){helicopterFile = "ApacheAH1.csv";}
				
				HelicopterModel helicopterType = FileInput.helicopterTypeFromFile("./assets/Data/" + helicopterFile);	//initiating helicopter with data from a file
				Helicopter helicopter = new Helicopter(helicopterType, altitude, direction, speed, 0, pitch) ;
				
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
		GridBagConstraints constraintsOfLayout = new GridBagConstraints();
		
		constraintsOfLayout.fill = GridBagConstraints.HORIZONTAL;
		constraintsOfLayout.gridx = 0;
		constraintsOfLayout.gridy = 0;
        constraintsOfLayout.insets = new Insets(0,4,0,0);
        controlPanel.add(xCoordLabel, constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 0;
        controlPanel.add(yCoordLabel, constraintsOfLayout);
        constraintsOfLayout.gridx = 0;
        constraintsOfLayout.gridy = 1;
        controlPanel.add(xCoordSpinner,constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 1;
        controlPanel.add(yCoordSpinner, constraintsOfLayout);
        constraintsOfLayout.insets = new Insets(10,4,0,0);
        constraintsOfLayout.gridx = 0;
        constraintsOfLayout.gridy = 2;
        controlPanel.add(speedLabel,constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 2;
        controlPanel.add(speedSpinner, constraintsOfLayout);
        constraintsOfLayout.insets = new Insets(0,4,0,0);
        constraintsOfLayout.gridx = 0;
        constraintsOfLayout.gridy = 3;
        controlPanel.add(altitudeLabel,constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 3;
        controlPanel.add(altitudeSpinner, constraintsOfLayout);
        
        constraintsOfLayout.gridx = 0;
        constraintsOfLayout.gridy = 4;
        controlPanel.add(pitchLabel, constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 4;
        constraintsOfLayout.insets = new Insets(0,-120,0,0);
        controlPanel.add(pitchPicLabel ,constraintsOfLayout);
        constraintsOfLayout.gridx = 2;
        constraintsOfLayout.gridy = 4;
        controlPanel.add(pitchSpinner, constraintsOfLayout);
        
        constraintsOfLayout.insets = new Insets(0,4,0,0);
        constraintsOfLayout.gridx = 0;
        constraintsOfLayout.gridy = 5;
        controlPanel.add(angleLabel, constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 5;
        constraintsOfLayout.insets = new Insets(0,-120,0,0);
        controlPanel.add(picLabel, constraintsOfLayout);
        constraintsOfLayout.gridx = 2;
        constraintsOfLayout.gridy = 5;
        controlPanel.add(angleSpinner, constraintsOfLayout);
        constraintsOfLayout.insets = new Insets(0,4,0,0);
        constraintsOfLayout.gridx = 0;
        constraintsOfLayout.gridy = 6;
        controlPanel.add(helicopterTypeLabel, constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 6;
        controlPanel.add(helicopterTypeList, constraintsOfLayout);
        constraintsOfLayout.gridx = 0;
        constraintsOfLayout.gridy = 7;
        controlPanel.add(mapTypeLabel, constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 7;
        controlPanel.add(mapTypeList,constraintsOfLayout);
        constraintsOfLayout.insets = new Insets(10,5,5,5);
        constraintsOfLayout.gridx = 0;
        constraintsOfLayout.gridy = 8;
        controlPanel.add(generateButton, constraintsOfLayout);
        constraintsOfLayout.gridx = 1;
        constraintsOfLayout.gridy = 8;
        controlPanel.add(resetButton, constraintsOfLayout);
        
        display.add(controlPanel);     
		display.setVisible(true);	
	}
	
	//Method to create and display the UI
	protected static void createAndShowGUI() throws IOException{
		//Create user interface window
		UserInputWindow window = new UserInputWindow("TP3 Project");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set up button frame
		window.initialiseUI(window.getContentPane());
		
		//Display the window
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	//Method to rotate the compass image
	public static BufferedImage rotateImage(BufferedImage imageToRotate, double rotationAngle){
		
		//Calculations of sine and cosine of desired angle.
		double sineOfAngle = Math.abs(Math.sin(Math.toRadians(rotationAngle))); 
		double cosineOfAngle = Math.abs(Math.cos(Math.toRadians(rotationAngle)));
		int heightOfPicture = imageToRotate.getHeight();
		int widthOfPicture = imageToRotate.getWidth();
	    int widthOfNewPicture = (int)Math.floor((widthOfPicture*cosineOfAngle)+(heightOfPicture*sineOfAngle));
	    int heightOfNewPicture = (int)Math.floor((heightOfPicture*cosineOfAngle)+(widthOfPicture*sineOfAngle));
	    
	    //Setting up graphics environment.
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gd.getDefaultConfiguration();
	    
	    //Creating the new image.
	    BufferedImage rotatedImage = gc.createCompatibleImage(widthOfNewPicture, heightOfNewPicture, Transparency.TRANSLUCENT);
	    Graphics2D drawImage = rotatedImage.createGraphics();
	    drawImage.translate((widthOfNewPicture-widthOfPicture)/2, (heightOfNewPicture-heightOfPicture)/2);
	    drawImage.rotate(Math.toRadians(rotationAngle), widthOfPicture/2, heightOfPicture/2);
	    drawImage.drawRenderedImage(imageToRotate, null);
	    drawImage.dispose();
	    return rotatedImage;
	}
}