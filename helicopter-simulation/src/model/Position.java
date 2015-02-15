package model;

import com.jme3.math.Vector3f;

public class Position {
	public float x;
	public float y;
	public float altitude;

	public Position(float x, float y, float altitude) {
		this.x = x;
		this.y = y;
		this.altitude = altitude;
	}

	public Vector3f toVector3f() {
		return new Vector3f(this.x, this.y, this.altitude);
	}
}
