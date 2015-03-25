package helicopter;
import com.jme3.math.Vector3f;
/**
 * This class will produce a three-dimensional vector from coordinates x,y and z.
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
	
	//Methods to get x coordinate and set x coordinate.
	public float getX() {return x;}
	public void setX(float x) {this.x = x;}
	
	//Methods to get y coordinate and set y coordinate.
	public float getY() {return y;}
	public void setY(float y) {this.y = y;}
	
	//Methods to get z coordinate and set z coordinate.
	public float getZ() {return z;}
	public void setZ(float z) {this.z = z;}
	
	//Create 3D vector.
	public Vector3f toVector3f() {
		return new Vector3f(this.getX(), this.getY(), this.getZ());
	}
	
	//Compare positions.
	public boolean comparePosition(Position position2){
		return (this.getX()==position2.getX() && this.getY()==position2.getY() && this.getZ()==position2.getZ());
	}
}