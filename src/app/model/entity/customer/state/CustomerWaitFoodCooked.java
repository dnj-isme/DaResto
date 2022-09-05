package app.model.entity.customer.state;

import app.game.GameTimerManager;
import app.model.entity.State;
import app.model.entity.cook.Cook;
import app.model.entity.customer.Customer;
import app.model.entity.waiter.Waiter;
import app.utility.Timer;

public class CustomerWaitFoodCooked extends Timer implements State{
	private Customer sender;
	private Waiter waiter;
	private Cook cook;
	private GameTimerManager timerManager = GameTimerManager.getInstance();
	
	public CustomerWaitFoodCooked(Customer sender, Waiter waiter, Cook cook) {
		this.sender = sender;
		this.waiter = waiter;
		this.cook = cook;
		timerManager.register(this);
		super.start();
	}

	@Override
	public void change() {
		sender.setState(new CustomerWaitFoodArrived(sender, waiter));
	}

	@Override
	protected void timerOnTick() {
		if(getTick() % getTicksPerSecond() * 4 == 0) {
			sender.reduceTolerance();
		}
	}

	@Override
	public String getDescription() {
		return String.format("%s is waiting food is cooked by %s", sender.getName(), cook.getName());
	}
}
