package app.model.entity.cook.state;

import app.game.GameTimerManager;
import app.model.entity.State;
import app.model.entity.cook.Cook;
import app.model.entity.customer.Customer;
import app.utility.Timer;

public class CookCooking extends Timer implements State{

	private GameTimerManager timerManager = GameTimerManager.getInstance();
	
	private Cook sender;
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public CookCooking(Cook sender, Customer customer) {
		this.sender = sender;
		this.customer = customer;
		timerManager.register(this);
		super.start();
	}

	@Override
	public void change() {
		super.stop();
		timerManager.remove(this);
		sender.setState(new CookDone(sender, customer));
	}

	@Override
	public String getDescription() {
		return String.format("%s cooking for %s", sender.getName(), customer.getName());
	}

	@Override
	protected void timerOnTick() {
		if(getSeconds() >= 6 - sender.getSpeed()) change();
	}
}
