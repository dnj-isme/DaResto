package app.model.entity.customer;

import app.model.Restaurant;
import app.model.entity.Person;
import app.model.entity.State;
import app.model.entity.customer.state.CustomerWaitOrder;

public class Customer extends Person {

	private int tolerance;
	private Restaurant restaurant;
	private State state;
	
	public Customer(String name, int tolerance) {
		super(name);
		this.tolerance = tolerance;
		this.restaurant = Restaurant.getInstance();
		this.state = new CustomerWaitOrder(this);
	}

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void reduceTolerance() {
		this.tolerance = Math.max(0, this.tolerance - 1);
	}
}
