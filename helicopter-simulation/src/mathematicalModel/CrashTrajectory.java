package mathematicalModel;
import helicopter.Conversions;
import helicopter.Helicopter;
import helicopter.Position;

import java.util.ArrayList;

import repository.HelicopterStats;
import repository.TrajectoryInformation;

/**
 * This class takes in trajectory information and calculates new positions,
 * then adds them to the trajectory information. It also saves the speed and
 * time of the position as a HelicopterStats.
 */
public class CrashTrajectory {

	public static void calculateThrowTrajectory(TrajectoryInformation trajectoryInformation) {
		
		Helicopter helicopter = trajectoryInformation.getHeli();
		
		//Converting degrees into radians.
		//The minus counteracts a correction introduced in the conversion method accounting to different representation of angles.
		double helicopterPitch = -Conversions.degreeToRadians(helicopter.getPitch());
		double helicopterHeading = Conversions.degreeToRadians(helicopter.getHeading());

		//Copy Arraylist with positions to manipulate it.
		ArrayList<HelicopterStats> trajectory = trajectoryInformation.getStats();
		int trajectoryLength = trajectory.size();
		HelicopterStats lastStats = trajectory.get(trajectoryLength - 1);
		Position lastPosition = lastStats.getPosition();
		
		int time = 1;

		//Calculates new positions and adds them to the copy of the arraylist.
		while (lastPosition.getY() >= 0) {
			trajectory.add(new HelicopterStats(ProjectileMotionCalculations.calculateNewPosition(time/100.0, helicopter,
					helicopterHeading, helicopterPitch), Conversions.unitsPSecondToMph(ProjectileMotionCalculations.calculateSpeed(time/100.0, 
					helicopterPitch, (helicopter.getSpeed()))), time/100.0));
			trajectoryLength++;
			lastStats = trajectory.get(trajectoryLength - 1);
			lastPosition = lastStats.getPosition();
			time += 1;
		}
		trajectoryInformation.setStats(trajectory);
	}
}