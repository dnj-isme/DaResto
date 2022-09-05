package app.model.entity.cook.state;

import app.model.entity.State;
import app.model.entity.cook.Cook;
import app.model.entity.customer.Customer;

public class CookIdle implements State {
	
	private Cook sender;
	private Customer customer;
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CookIdle(Cook sender) {
		this.sender = sender;
	}

	@Override
	public void change() {
		sender.setState(new CookCooking(sender, customer));
	}

	@Override
	public String getDescription() {
		return String.format("%s waiting for order", sender.getName());
	}
}
