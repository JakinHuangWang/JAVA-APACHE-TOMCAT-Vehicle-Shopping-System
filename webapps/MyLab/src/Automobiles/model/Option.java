package model;

public class Option implements java.io.Serializable {
	private String name;
	private float price;
	
	protected Option() {
		name = new String();
		price = 0;
	}	
	protected Option(String name) {
		this.name = name;
		price = 0;
	}	
	public Option(String name, float price) {
		this.name = name;
		this.price = price;
	}
	synchronized public String getName() {
		return name;
	}
	synchronized protected void setName(String name) {
		this.name = name;
	}
	synchronized protected float getPrice() {
		return price;
	}
	synchronized protected void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return this.name + " $" + Float.toString(this.price) + '\n';
	}
}