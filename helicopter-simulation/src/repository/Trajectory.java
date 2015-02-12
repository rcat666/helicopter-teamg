package repository;

import java.util.ArrayList;

import model.Helicopter;
import model.Position;

/*
 * This class stores the trajectory of a specific helicopter instance.
 * 
 */


public class Trajectory {
	
	//helicopter instance this trajectory is for 
	private final Helicopter heli;
	
	//List of positions that make the trajectory
	private ArrayList<Position> traj;
	
	
	public ArrayList<Position> getTrajectory() {
		return traj;
	}

	public void setTrajectory(ArrayList<Position> traj) {
		this.traj = traj;
	}

	//instantiates an instance with two given positions.
	public Trajectory(Helicopter helicopter, Position startingPosition, Position malfunctionPosition){
		this.heli=helicopter;
		this.traj=new ArrayList<Position>();
		this.traj.add(startingPosition);
		this.traj.add(malfunctionPosition);
	}
	
	//instantiates an instance if there are no two points
	public Trajectory(Helicopter helicopter){
		this.heli=helicopter;
		this.traj=new ArrayList<Position>();
	}
	
}
