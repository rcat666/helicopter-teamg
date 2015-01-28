package mathematicalModel;

import java.util.ArrayList;

import model.Helicopter;
import model.Position;

public class CrashTrajectory{
	
	//creates the trajectory of a crashing helicopter
	public void calculateTrajectory(Helicopter heli) throws Exception{
		try {
			ArrayList<Position> trajectory2=heli.getTrajectory();
			int lSize= trajectory2.size();
			Position last=trajectory2.get(lSize-1);
			Position secondToLast=trajectory2.get(lSize-2);
			
			//calculating  angles needed for calculating position later on
			double chi=Math.atan((last.getY()-secondToLast.getY())/(last.getX()-secondToLast.getX()));
			double psi=Math.atan((last.getX()-secondToLast.getX())/(last.getY()-secondToLast.getY()));
			//calculates distance between last to positions to calculate last angle
			double distance=Math.sqrt(Math.pow((last.getX()-secondToLast.getX()), 2)+Math.pow((last.getY()-secondToLast.getY()), 2)+Math.pow((last.getAltitude()-secondToLast.getAltitude()), 2));
			double omega=Math.asin((last.getAltitude()-secondToLast.getAltitude())/distance);
			double time=1.0;
			while (last.getAltitude()>0){
				//adds new calculated positions
				trajectory2.add(ThrowCalculations.calculateNewPos(time, heli.getSpeed(), last, omega, psi, chi));
				lSize++;
				last=trajectory2.get(lSize-1);
				time++;
			}
			heli.setTrajectory(trajectory2);
			
		}
		// if there are less than two position this should catch the error
		catch (Exception e) {
			System.err.println("At least two positions are needed for this calculation.");
		}
	
	}
	
}
