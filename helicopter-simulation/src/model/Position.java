package model;

import com.jme3.math.Vector3f;
/**
 * Class to produce a three-dimensional vector from a triple x,y,z of coordinates 
 * @author Roberto
 *
 */
public class Position {

	private float x;
	private float y;
	private float altitude;

	public Position(float x, float y, float altitude) {
		this.setX(x);
		this.setY(y);
		this.setAltitude(altitude);
	}


	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Vector3f toVector3f() {
		return new Vector3f(this.getX(), this.getY(), this.getAltitude());
	}
}
