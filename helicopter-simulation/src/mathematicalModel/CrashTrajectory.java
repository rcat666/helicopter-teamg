package mathematicalModel;
import helicopter.Conversions;
import helicopter.Helicopter;
import helicopter.Position;

import java.util.ArrayList;

import repository.HelicopterStats;
import repository.TrajectoryInformation;

/**
 * Creates an array consisting of points of the crash path.
 */
public class CrashTrajectory {

	public static void calculateThrowTrajectory(TrajectoryInformation trajectoryInformation) {
		
		Helicopter helicopter = trajectoryInformation.getHeli();
		
		// converting degrees into radians
		// the minus counteracts a correction introduced in the conversion method accounting to different representation of angles
		double helicopterPitch = -Conversions.degreeToRadians(helicopter.getPitch());
		double helicopterHeading = Conversions.degreeToRadians(helicopter.getHeading());

		// copy Arraylist with positions to manipulate it
		ArrayList<HelicopterStats> trajectory = trajectoryInformation.getStats();
		int trajectoryLength = trajectory.size();
		HelicopterStats lastStats = trajectory.get(trajectoryLength - 1);
		Position lastPos = lastStats.getPosition();
		
		int time = 1;

		// calculates new positions and adds them to the copy of the arraylist
		while (lastPos.getY() >= 0) {
			trajectory.add(new HelicopterStats(ThrowCalculations.calculateNewPos(time/100.0, helicopter,
					helicopterHeading, helicopterPitch), Conversions.unitsPSecondToMph(ThrowCalculations.calculateSpeed(time/100.0, helicopterPitch, (helicopter.getSpeed()))), time/100.0));
			trajectoryLength++;
			lastStats = trajectory.get(trajectoryLength - 1);
			lastPos = lastStats.getPosition();
			time += 1;
		}
		trajectoryInformation.setStats(trajectory);
	}
}