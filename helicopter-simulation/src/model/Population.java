package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Population {
	
	// reading the file
	public double[][] readPopulationData(){
		try {
            BufferedReader br = new BufferedReader(new FileReader("assets/Data/glp15ag.asc"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return null;
	}
	
}
