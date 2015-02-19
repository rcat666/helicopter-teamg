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
	private float z;

	public Position(float x, float y, float z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}


	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
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
		return new Vector3f(this.getX(), this.getY(), this.getZ());
	}
	
	public boolean comparePosition(Position position2){
		return (this.getX()==position2.getX() && this.getY()==position2.getY() && this.getZ()==position2.getZ());
	}
}
