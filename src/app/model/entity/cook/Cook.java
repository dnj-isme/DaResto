package app.model.entity.cook;

import app.model.Restaurant;
import app.model.entity.Employee;
import app.model.entity.State;
import app.model.entity.cook.state.CookIdle;

public class Cook extends Employee {
	
	private int skill;
	private Restaurant restaurant;
	private State state;
	
	public int getSkill() {
		return skill;
	}

	public void addSkill() {
		skill++;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return state;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public Cook(String name) {
		super(name);
		this.restaurant = Restaurant.getInstance();
		this.skill = 1;
		this.state = new CookIdle(this);
	}
}
