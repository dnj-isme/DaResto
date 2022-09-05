package app.model.entity.customer.state;

import app.game.GameTimerManager;
import app.model.entity.State;
import app.model.entity.customer.Customer;
import app.model.entity.waiter.Waiter;
import app.utility.Timer;

public class CustomerWaitOrder extends Timer implements State{

	private Customer sender;
	private Waiter waiter;
	private GameTimerManager timerManager = GameTimerManager.getInstance();
	
	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public CustomerWaitOrder(Customer sender) {
		this.sender = sender;
		timerManager.register(this);
		super.start();
	}
	
	@Override
	public void change() {
		super.stop();
		timerManager.remove(this);
		sender.setState(new CustomerOrdering(sender, waiter));
	}

	@Override
	protected void timerOnTick() {
		if(getTick() % getTicksPerSecond() * 4 == 0) {
			sender.reduceTolerance();
		}
	}

	@Override
	public String getDescription() {
		return String.format("%s is waiting for ordering food", sender.getName());
	}
	
}
