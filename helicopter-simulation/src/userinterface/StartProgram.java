package userinterface;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Class that contains the main method to begin the software.
 */

public class StartProgram extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel mainBlock;		//Block of text.
	private JButton startButton;	//Button to move onto the user input screen.
	
	//Constructor
	public StartProgram(String UITitle){
		super(UITitle);
		setResizable(false);
	}
	
	//Creates the welcoming screen
	private void initialiseUI(final Container display){
		final JPanel mainPanel = new JPanel();
		
		//Creates main information text using html.
		mainBlock = new JLabel ("<html><div style=\"text-align: center; font-family: Avenir; font-weight: bold; font-size: 52pt;\">Team G</div><div div style=\"text-align: center; font-size: 18pt; font-family: Avenir;\"><br>This software application is developed under<br> the supervision of Professor Chris Johnson.<br>The program is based on the works of Arnaud Prouzeau.<br> <br><div style=\"text-align: center; font-size: 12pt;\">Please be aware that not all satellite images are available for every location.<br>The trajectory is not based on complex formulae.<br>Please note that the helicopter size has been scaled for visual effect.<br>For help, hover over each component to view a tooltip.");
		
		//Creates start button and sets the font.
		startButton = new JButton("Start");
		startButton.setFont(new Font("Avenir", Font.BOLD, 15));
		
		//Action listener for clicking the start button.
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);							//Removes the welcome screen.
				dispose();								
				SwingUtilities.invokeLater(new Runnable(){	//Opens the user input screen.
		    		public void run() {try {
						UserInputWindow.createAndShowGUI();
					} catch (IOException e) {
						e.printStackTrace();
					}}
		    	});
			}
		});
		
		//Set layout of the window using a GridBagLayout.
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5,15,5,15);
		mainPanel.add(mainBlock, c);		//Add main block of text.
		c.gridx = 2;
		c.gridy = 2;
		c.insets=new Insets(0,200,0,200);
		mainPanel.add(startButton, c);		//Add start button.
		display.add(mainPanel);
		display.setVisible(true);			//Make screen visible.
	}
	
	//Creates and displays the welcome screen.
	private static void createAndShowGUI(){
		StartProgram window = new StartProgram("Helicopter Simulation");	//Title of window.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.initialiseUI(window.getContentPane());
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	//Main method to start the program.
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){public void run() {createAndShowGUI();}});
	}
}