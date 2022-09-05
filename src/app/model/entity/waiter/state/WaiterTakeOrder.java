package app.model.entity.waiter.state;

import app.game.GameTimerManager;
import app.model.entity.State;
import app.model.entity.waiter.Waiter;
import app.utility.Timer;

public class WaiterTakeOrder extends Timer implements State {
	private Waiter sender;
	private GameTimerManager timerManager = GameTimerManager.getInstance();
	
	public WaiterTakeOrder(Waiter sender) {
		this.sender = sender;
		timerManager.register(this);
		super.start();
	}

	@Override
	public void change() {
		
	}

	@Override
	protected void timerOnTick() {
		if(getSeconds() >= 6 - sender.getSpeed()) change();
	}

	@Override
	public String getDescription() {
		return String.format("", null);
	}
}
