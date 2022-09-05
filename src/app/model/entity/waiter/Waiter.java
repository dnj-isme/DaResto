package app.model.entity.waiter;

import app.model.Restaurant;
import app.model.entity.Employee;
import app.model.entity.State;
import app.model.entity.waiter.state.WaiterIdle;

public class Waiter extends Employee {

	private Restaurant restaurant;
	private State waiter;
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public State getWaiter() {
		return waiter;
	}

	public void setWaiter(State waiter) {
		this.waiter = waiter;
	}

	public Waiter(String name) {
		super(name);
		this.restaurant = Restaurant.getInstance();
		this.waiter = new WaiterIdle(this);
	}
}
