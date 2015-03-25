package helicopter;

/**
 * This class is for setting the model of the helicopter.
 * The model of a helicopter consists of its name, length, rotor length, rotor diameter, height and weight.
 */
public class HelicopterModel {
	
	//Information about the model of the helicopter.
	private String name;
	private double length;
	private double r_length;
	private double r_diam;
	private double height;
	private double weight;
	
	public HelicopterModel(String name, double length, double r_length,
			double r_diam, double height, double weight) {
		super();
		this.setName(name);
		this.setLength(length);
		this.setR_length(r_length);
		this.setR_diam(r_diam);
		this.setHeight(height);
		this.setWeight(weight);
	}
	
	//Methods to get the name of the helicopter model and set the name of the helicopter model.
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	//Methods to get the length of the helicopter model and set the length of the helicopter model.
	public double getLength() {return length;}
	public void setLength(double length) {this.length = length;}
	
	//Methods to get the rotor length of the helicopter model and set the rotor length of the helicopter model.
	public double getR_length() {return r_length;}
	public void setR_length(double r_length) {this.r_length = r_length;}

	//Methods to get the rotor diameter of the helicopter model and set the rotor diameter of the helicopter model.
	public double getR_diam() {return r_diam;}
	public void setR_diam(double r_diam) {this.r_diam = r_diam;}
	
	//Methods to get the height of the helicopter model and set the height of the helicopter model.
	public double getHeight() {return height;}
	public void setHeight(double height) {this.height = height;}
	
	//Methods to get the weight of the helicopter model and set the weight of the helicopter model.
	public double getWeight() {return weight;}
	public void setWeight(double weight) {this.weight = weight;}
	
	@Override
	public String toString(){
		return "Helicopter Name: "+name+ " length: "+ length + " rotor length: " + r_length + " rotor diameter: " + r_diam + " heigth: " + height + " weight " + weight;
	}
}