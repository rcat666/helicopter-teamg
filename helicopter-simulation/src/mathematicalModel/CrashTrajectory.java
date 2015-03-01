package mathematicalModel;

import java.util.ArrayList;

import repository.Trajectory;
import model.Helicopter;
import model.Position;

public class CrashTrajectory {

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
			time += 0.01;
		}

		// sets Trajectories arraylist to the new calculated values
		traj.setTrajectory(trajectory);

	}

}
