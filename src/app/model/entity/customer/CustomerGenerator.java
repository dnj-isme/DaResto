package app.model.entity.customer;

import app.model.Restaurant;
import app.model.entity.Person;
import app.utility.Chair;
import app.utility.Utility;

public class CustomerGenerator implements Observer {

	private Restaurant restaurant;
	
	public CustomerGenerator(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	@Override
	public synchronized void action() {
		Chair emptyChair = restaurant.getEmptyChair();
		if(emptyChair == null) return;
		
		while(true) {
			String randName = Utility.generateName();
			if(Person.searchName(restaurant.getPersonList(), randName) == -1) {
				Customer newCustomer = new Customer(randName, Utility.random(10, 20));
				restaurant.getPersonList().add(newCustomer);
				emptyChair.setOccupant(newCustomer);
				break;
			}
		}
	}	
}
