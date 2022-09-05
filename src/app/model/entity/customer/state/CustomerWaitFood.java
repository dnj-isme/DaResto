package app.model.entity.customer.state;

import app.game.GameTimerManager;
import app.model.entity.State;
import app.model.entity.cook.Cook;
import app.model.entity.customer.Customer;
import app.model.entity.waiter.Waiter;
import app.utility.Timer;

public class CustomerWaitFood extends Timer implements State{
	private Customer sender;
	private Waiter waiter;
	private Cook cook;
	private GameTimerManager timerManager = GameTimerManager.getInstance();
	
	public void setCook(Cook cook) {
		this.cook = cook;
	}

	public CustomerWaitFood(Customer sender, Waiter waiter) {
		this.sender = sender;
		this.waiter = waiter;
		timerManager.register(this);
		super.start();
	}

	@Override
	public void change() {
		sender.setState(new CustomerWaitFoodCooked(sender, waiter, cook));
	}

	@Override
	protected void timerOnTick() {
		if(getTick() % getTicksPerSecond() * 4 == 0) {
			sender.reduceTolerance();
		}
	}

	@Override
	public String getDescription() {
		return String.format("%s is waiting order to sent to the cook", sender.getName());
	}
}
