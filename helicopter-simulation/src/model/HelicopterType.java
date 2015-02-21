package model;

public class HelicopterType {
	private String name;
	private double length;
	private double r_length;
	private double r_diam;
	private double height;
	private double weight;
	
	
	
	public HelicopterType(String name, double length, double r_length,
			double r_diam, double height, double weight) {
		super();
		this.setName(name);
		this.setLength(length);
		this.setR_length(r_length);
		this.setR_diam(r_diam);
		this.setHeight(height);
		this.setWeight(weight);
	}



	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}



	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}



	public double getR_length() {
		return r_length;
	}

	public void setR_length(double r_length) {
		this.r_length = r_length;
	}



	public double getR_diam() {
		return r_diam;
	}

	public void setR_diam(double r_diam) {
		this.r_diam = r_diam;
	}



	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}



	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString(){
		return "Helicopter Name: "+name+ " length: "+ length + " rotor length: " + r_length + " rotor diameter: " + r_diam + " heigth: " + height + " weight " + weight;
	}

}
