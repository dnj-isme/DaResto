package app.model.entity.customer.state;

import app.model.entity.State;
import app.model.entity.customer.Customer;
import app.model.entity.waiter.Waiter;

public class CustomerOrdering implements State{
	private Customer sender;
	private Waiter waiter;

	public CustomerOrdering(Customer sender, Waiter waiter) {
		super();
		this.sender = sender;
		this.waiter = waiter;
	}
	
	public Waiter getWaiter() {
		return waiter;
	}

	@Override
	public void change() {
		sender.setState(new CustomerWaitFood(sender, waiter));
	}

	@Override
	public String getDescription() {
		return String.format("%s is ordering food with %s", sender.getName(), waiter.getWaiter());
	}
}
