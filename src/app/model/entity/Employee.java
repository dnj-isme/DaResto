package app.model.entity;

public abstract class Employee extends Person {
	
	private int speed;
	
	public int getSpeed() {
		return speed;
	}

	public void addSpeed() {
		speed++;
	}
	
	public Employee(String name) {
		super(name);
		this.speed = 1;
	}
}