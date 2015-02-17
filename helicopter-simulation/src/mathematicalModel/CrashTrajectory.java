package mathematicalModel;

import java.util.ArrayList;

import repository.Trajectory;
import model.Helicopter;
import model.Position;

public class CrashTrajectory {

	/*
	 * //creates the trajectory of a crashing helicopter given only two points
	 * public void calculateTrajectoryPoints(Helicopter heli, Trajectory
	 * heliTrajectory) throws Exception{ try { ArrayList<Position>
	 * trajectory2=heliTrajectory.getTrajectory(); int lSize=
	 * trajectory2.size(); Position last=trajectory2.get(lSize-1); Position
	 * secondToLast=trajectory2.get(lSize-2);
	 * 
	 * //calculating angles needed for calculating position later on double
	 * chi=Math
	 * .atan((last.getY()-secondToLast.getY())/(last.getX()-secondToLast.
	 * getX())); double
	 * psi=Math.atan((last.getX()-secondToLast.getX())/(last.getY
	 * ()-secondToLast.getY())); //calculates distance between last to positions
	 * to calculate last angle double
	 * distance=Math.sqrt(Math.pow((last.getX()-secondToLast.getX()),
	 * 2)+Math.pow((last.getY()-secondToLast.getY()),
	 * 2)+Math.pow((last.getAltitude()-secondToLast.getAltitude()), 2)); double
	 * omega
	 * =Math.asin((last.getAltitude()-secondToLast.getAltitude())/distance);
	 * double time=1.0; while (last.getAltitude()>0){ //adds new calculated
	 * positions trajectory2.add(ThrowCalculations.calculateNewPos(time,
	 * heli.getSpeed(), last, omega, psi, chi)); lSize++;
	 * last=trajectory2.get(lSize-1); time++; }
	 * heliTrajectory.setTrajectory(trajectory2);
	 * 
	 * } // if there are less than two position this should catch the error
	 * catch (Exception e) {
	 * System.err.println("At least two positions are needed for this calculation."
	 * ); }
	 * 
	 * }
	 */

	public static void calculateThrowTrajectory(Trajectory traj) {
		
		Helicopter helicopter=traj.getHeli();
		
		// converting degrees into radians
		double heliPitch = helicopter.getPitch() * (Math.PI / 180);

		double heliHeading = helicopter.getHeading();

		// if heading is bigger than 180 degrees, change it to negative degree
		// values
		// this is done by subtracting 180 from the heading and using its
		// negative value
		if (heliHeading > 180)
			heliHeading = -(heliHeading - 180);

		// changing degrees to radians
		heliHeading = heliHeading * (Math.PI / 180);

		// copy Arraylist with positions to manipulate it
		ArrayList<Position> trajectory = traj.getTrajectory();
		int trajectoryLength = trajectory.size();
		Position lastPos = trajectory.get(trajectoryLength - 1);

		double time = 0.01;

		// calculates new positions and adds them to the copy of the arraylist
		while (lastPos.getY() >= 0) {
			trajectory.add(ThrowCalculations.calculateNewPos(time, helicopter,
					heliHeading, heliPitch));
			trajectoryLength++;
			lastPos = trajectory.get(trajectoryLength - 1);
			time=time+0.01;
		}

		// sets Trajectories arraylist to the new calculated values
		traj.setTrajectory(trajectory);

	}

}
