package repository;

import java.util.ArrayList;

import model.Helicopter;

/*
 * This class stores the trajectory of a specific helicopter instance.
 * 
 */

public class TrajectoryInformation {

	// helicopter instance this trajectory is for
	private final Helicopter heli;

	public Helicopter getHeli() {
		return heli;
	}

	// List of positions that make the trajectory
	private ArrayList<HeliStats> helistats;

	public ArrayList<HeliStats> getStats() {
		return helistats;
	}

	public void setStats(ArrayList<HeliStats> traj) {
		this.helistats = traj;
	}

	// instantiates an instance if there are no two points
	public TrajectoryInformation(Helicopter helicopter) {
		this.heli = helicopter;
		this.helistats = new ArrayList<HeliStats>();
		helistats.add(new HeliStats(heli.getPos(),heli.getSpeed(), 0.0));
	}

}
