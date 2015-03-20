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

public class WelcomeScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel mainblock;
	private JButton startButton;
	
	public WelcomeScreen(String UITitle){
		super(UITitle);
		setResizable(false);
	}
	
	private void initialiseUI(final Container display){
		final JPanel mainPanel = new JPanel();
		mainblock = new JLabel ("<html><div style=\"text-align: center; font-family: Avenir; font-weight: bold; font-size: 52pt;\">Team G</div><div div style=\"text-align: center; font-size: 18pt; font-family: Avenir;\"><br>This software application is developed under<br> the supervision of Professor Chris Johnson.<br>The program is based on the works of Arnaud Prouzeau.<br> <br><div style=\"text-align: center; font-size: 12pt;\">Please be aware that not all satellite images are available for every location.<br>The trajectory is not based on complex formulae.<br>Please note that the helicopter size has been scaled for visual effect.<br>For help, hover over each component to view a tooltip.");
		
		startButton = new JButton("Start");
		startButton.setFont(new Font("Avenir", Font.BOLD, 15));
		
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				SwingUtilities.invokeLater(new Runnable(){
		    		public void run() {try {
						MainMenu.createAndShowGUI();
					} catch (IOException e) {
						e.printStackTrace();
					}}
		    	});
			}
			
		});
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5,15,5,15);
		mainPanel.add(mainblock, c);
		c.gridx = 2;
		c.gridy = 2;
		c.insets=new Insets(0,200,0,200);
		mainPanel.add(startButton, c);
		display.add(mainPanel);
		display.setVisible(true);
	}
	private static void createAndShowGUI(){
		WelcomeScreen window = new WelcomeScreen("Helicopter Simulation");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.initialiseUI(window.getContentPane());
		
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
    		public void run() {createAndShowGUI();}
    	});
	}
}
