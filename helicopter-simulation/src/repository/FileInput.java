package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.Helicopter;
import model.HelicopterType;

/*
 * This file reads in a file and creates a helicopter instance from the data provided by the file.
 * 
 */

public class FileInput {
	
	//reads a file and saves each line as an element in ArrayList 
	private static ArrayList<String> getDataFromFile(String fileName){
		ArrayList<String> fileData=new ArrayList<String>();
		BufferedReader reader=null;
		String information;
		try{
			reader=new BufferedReader(new FileReader(fileName));
			while((information=reader.readLine()) !=null){
				fileData.add(information);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{
				if (reader!=null){
					reader.close();
				}
			}
			catch(IOException f){
				f.printStackTrace();
			}
		}
		return fileData;
	}
	
	//returns a Helicopter instance with data from a file 
	public static Helicopter helicopterFromFile(String fileName){
		ArrayList<String> data= getDataFromFile(fileName);
		//initiating all variables
		String name=null;
		double length = 0;
		double r_length = 0;
		double r_diam = 0;
		double height = 0;
		double weight = 0;
		double altitude = 0;
		double heading = 0;
		double speed = 0;
		double attitude = 0;
		double pitch = 0;
		
		//assigning values to the right variables
		for(String information:data){
			String[] thisLine= information.split(",");
			switch (thisLine[0]){
				case "name": 
					name = thisLine[1];
					break;
				case "length": 
					length = Double.parseDouble(thisLine[1]);
					break;
				case "rotor length": 
					r_length = Double.parseDouble(thisLine[1]);
					break;
				case "rotor diameter": 
					r_diam = Double.parseDouble(thisLine[1]);
					break;
				case "height": 
					height = Double.parseDouble(thisLine[1]);
					break;
				case "weight": 
					weight = Double.parseDouble(thisLine[1]);
					break;
				case "altitude": 
					altitude = Double.parseDouble(thisLine[1]);
					break;
				case "heading":
					heading = Double.parseDouble(thisLine[1]);
					break;
				case "speed": 
					speed = Double.parseDouble(thisLine[1]);
					break;
				case "attitude": 
					attitude = Double.parseDouble(thisLine[1]);
					break;
				case "pitch": 
					pitch = Double.parseDouble(thisLine[1]);
					break;
			}
		}
		return new Helicopter(new HelicopterType(name, length, r_length, r_diam, height, weight), altitude, heading, speed, attitude, pitch);
	}

	//returns a Helicoptertype instance with data from a file 
	public static HelicopterType helicopterTypeFromFile(String fileName){
		ArrayList<String> data= getDataFromFile(fileName);
		//initiating all variables
		String name=null;
		double length = 0;
		double r_length = 0;
		double r_diam = 0;
		double height = 0;
		double weight = 0;
		
		//assigning values to the right variables
		for(String information:data){
			String[] thisLine= information.split(",");
			switch (thisLine[0]){
				case "name": 
					name = thisLine[1];
					break;
				case "length": 
					length = Double.parseDouble(thisLine[1]);
					break;
				case "rotor length": 
					r_length = Double.parseDouble(thisLine[1]);
					break;
				case "rotor diameter": 
					r_diam = Double.parseDouble(thisLine[1]);
					break;
				case "height": 
					height = Double.parseDouble(thisLine[1]);
					break;
				case "weight": 
					weight = Double.parseDouble(thisLine[1]);
					break;
					}
		}
		return new HelicopterType(name, length, r_length, r_diam, height, weight);
	}

}
