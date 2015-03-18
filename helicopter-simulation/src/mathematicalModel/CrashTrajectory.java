package mathematicalModel;

import java.util.ArrayList;

import repository.HeliStats;
import repository.TrajectoryInformation;
import model.Conversions;
import model.Helicopter;
import model.Position;

public class CrashTrajectory {


	public static void calculateThrowTrajectory(TrajectoryInformation traj) {
		
		Helicopter helicopter=traj.getHeli();
		
		// converting degrees into radians
		// the minus counteracts a correction introduced in the conversion method accounting to different representation of angles
		double heliPitch = -Conversions.degreeToRadians(helicopter.getPitch());

		double heliHeading = helicopter.getHeading();

		
		// changing degrees to radians
		heliHeading = Conversions.degreeToRadians(heliHeading);

		// copy Arraylist with positions to manipulate it
		ArrayList<HeliStats> trajectory = traj.getStats();
		int trajectoryLength = trajectory.size();
		HeliStats lastStats = trajectory.get(trajectoryLength - 1);
		Position lastPos = lastStats.getPosition();
		
		double time = 0.01;

		// calculates new positions and adds them to the copy of the arraylist
		while (lastPos.getY() >= 0) {
			trajectory.add(new HeliStats(ThrowCalculations.calculateNewPos(time, helicopter,
					heliHeading, heliPitch), Conversions.unitsPSecondToMph(ThrowCalculations.calculateSpeed(time, heliPitch, (helicopter.getSpeed())))));
			trajectoryLength++;
			lastStats = trajectory.get(trajectoryLength - 1);
			lastPos = lastStats.getPosition();
			time += 0.01;
		}
		
		System.out.println("CURRENT TIME  "+ time);
		//removes the last position as this is in the ground
		//trajectory.remove(trajectoryLength-1);
		
		//trajectory.add(ThrowCalculations.calculateFinalPos(Conversions.mphToUnitsPSecond(helicopter.getSpeed()),heliPitch,heliHeading));
		
		// sets Trajectories arraylist to the new calculated values
		traj.setStats(trajectory);

	}

}
