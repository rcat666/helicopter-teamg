package model;

public class Position {
	private double x;
	private double y;
	private double altitude;

	public Position(double x, double y, double altitude) {
		this.x = x;
		this.y = y;
		this.altitude = altitude;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
}
