package repository;

import helicopter.Helicopter;

import java.util.ArrayList;

/**
 * This class stores the trajectory of a specific helicopter instance.
 * 
 */

public class TrajectoryInformation {

	// instantiates an instance if there are no two points
	public TrajectoryInformation(Helicopter helicopter) {
		this.heli = helicopter;
		this.helistats = new ArrayList<HelicopterStats>();
		helistats.add(new HelicopterStats(heli.getPos(),heli.getSpeed(), 0.0));
	}
		
	// helicopter instance this trajectory is for
	private final Helicopter heli;

	public Helicopter getHeli() {return heli;}

	// List of positions that make the trajectory
	private ArrayList<HelicopterStats> helistats;

	public ArrayList<HelicopterStats> getStats() {return helistats;}

	public void setStats(ArrayList<HelicopterStats> traj) {this.helistats = traj;}

	
}