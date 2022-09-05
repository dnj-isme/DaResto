package app.model.entity.cook.state;

import app.model.entity.State;
import app.model.entity.cook.Cook;
import app.model.entity.customer.Customer;

public class CookDone implements State{

	private Cook sender;
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public CookDone(Cook sender, Customer customer) {
		this.sender = sender;
	}
	
	@Override
	public void change() {
		sender.setState(new CookIdle(sender));
	}

	@Override
	public String getDescription() {
		return String.format("%s finished %s's order", sender.getName(), customer.getName());
	}
}
