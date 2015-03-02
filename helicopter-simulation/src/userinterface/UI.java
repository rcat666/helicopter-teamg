package userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//User interface class
public class UI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel xCoordLabel;
	private JLabel yCoordLabel;
	private JLabel speedLabel;
	private JLabel altitudeLabel;
	private JLabel pitchLabel;
	private JLabel angleLabel;
	
	private JSpinner xCoordSpinner;
	private JSpinner yCoordSpinner;
	private JSpinner speedSpinner;
	private JSpinner altitudeSpinner;
	private JSpinner pitchSpinner;
	private JSpinner angleSpinner;
	
	private JButton generateButton;
	private JButton resetButton;
	
	final int initial = 0;
	
	//User interface constructor
	public UI(String UITitle){
		super(UITitle);
		setResizable(false); //Window is not resizable       	
    }
	
	//Method to initialise UI with its components.
	private void initialiseUI(final Container display){
		
		//Creating JPanels to contain the components.
		final JPanel controlPanel = new JPanel();
		
		//Labels
		xCoordLabel = new JLabel("x-coordinates:");
		yCoordLabel = new JLabel("y-coordinates:");
		speedLabel = new JLabel("Speed (mph):     ");
		altitudeLabel = new JLabel("Altitude (m):    ");
		pitchLabel = new JLabel("Pitch (°):    ");
		angleLabel = new JLabel("Direction (°):    ");
		
		//Formatting the JSpinners with preset models.
		SpinnerModel xCoordModel =  new SpinnerNumberModel(0,-180,180,0.1);
		SpinnerModel yCoordModel =  new SpinnerNumberModel(0,-180,180,0.1);
		SpinnerModel speedLimits = new SpinnerNumberModel(0,0,500,0.1);
		SpinnerModel altitudeLimits = new SpinnerNumberModel(0,0,500,0.1);
		SpinnerModel pitchLimits = new SpinnerNumberModel(0,-90,90,0.1);
		SpinnerModel angleLimits = new SpinnerNumberModel(0,0,360,0.1);
		
		//JSpinners
		xCoordSpinner = new JSpinner(xCoordModel);
		yCoordSpinner = new JSpinner(yCoordModel);
		speedSpinner = new JSpinner(speedLimits);
		altitudeSpinner = new JSpinner(altitudeLimits);
		pitchSpinner = new JSpinner(pitchLimits);
		angleSpinner = new JSpinner(angleLimits);
		
		//Buttons
		generateButton = new JButton("Generate");
		resetButton = new JButton("Reset");
		
		//Button listeners
		generateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double xCoordinate = (double) xCoordSpinner.getValue();
				double yCoordinate = (double) yCoordSpinner.getValue();
				double speed = (double) speedSpinner.getValue();
				double altitude = (double) altitudeSpinner.getValue();
				double pitch = (double) pitchSpinner.getValue();
				double angle = (double) angleSpinner.getValue();
				System.out.println("xCoord: " + xCoordinate);
				System.out.println("yCoord: " + yCoordinate);
				System.out.println("Speed: " + speed + "mph");
				System.out.println("Altitude: " + altitude + "m");
				System.out.println("Speed: " + pitch + "°");
			}
		});
		
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
        c.insets = new Insets(10,5,5,5);
        c.gridx = 0;
        c.gridy = 6;
        controlPanel.add(generateButton, c);
        c.gridx = 1;
        c.gridy = 6;
        controlPanel.add(resetButton, c);
        
        display.add(controlPanel);     
		display.setVisible(true);	
	}
	
	//Method to create and display the UI
	private static void createAndShowGUI(){
		//Create user interface window
		UI window = new UI("Simulation");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set up button frame
		window.initialiseUI(window.getContentPane());
		
		//Display the window
		window.pack();
		window.setVisible(true);
	}
	
	//Main method
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable(){
    		public void run() {createAndShowGUI();}
    	});
    }

	
}


