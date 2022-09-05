package app.model.entity.customer.state;

import app.game.GameTimerManager;
import app.model.entity.State;
import app.model.entity.customer.Customer;
import app.utility.Timer;

public class CustomerWaitFoodArrived extends Timer implements State{
	private Customer sender;
	private GameTimerManager timerManager = GameTimerManager.getInstance();
	
	public CustomerWaitFoodArrived(Customer sender) {
		this.sender = sender;
		timerManager.register(this);
		super.start();
	}

	@Override
	public void change() {
		
	}

	@Override
	protected void timerOnTick() {
		
	}

	@Override
	public String getDescription() {
		return null;
	}
}
