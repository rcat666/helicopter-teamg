package mathematicalModel;

import java.util.ArrayList;

import repository.Trajectory;
import model.Conversions;
import model.Helicopter;
import model.Position;

public class CrashTrajectory {


	public static void calculateThrowTrajectory(Trajectory traj) {
		
		Helicopter helicopter=traj.getHeli();
		
		// converting degrees into radians
		// the minus counteracts a correction introduced in the conversion method accounting to different representation of angles
		double heliPitch = -Conversions.degreeToRadians(helicopter.getPitch());

		double heliHeading = helicopter.getHeading();

		
		// changing degrees to radians
		heliHeading = Conversions.degreeToRadians(heliHeading);

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
		
		System.out.println("CURRENT TIME  "+ time);
		//removes the last position as this is in the ground
		//trajectory.remove(trajectoryLength-1);
		
		//trajectory.add(ThrowCalculations.calculateFinalPos(Conversions.mphToUnitsPSecond(helicopter.getSpeed()),heliPitch,heliHeading));
		
		// sets Trajectories arraylist to the new calculated values
		traj.setTrajectory(trajectory);

	}

}
