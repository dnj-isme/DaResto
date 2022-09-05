package app.model.entity.waiter.state;

import app.model.entity.State;
import app.model.entity.waiter.Waiter;

public class WaiterIdle implements State {
	
	private Waiter sender;
	
	public WaiterIdle(Waiter sender) {
		this.sender = sender;
	}

	@Override
	public void change() {
		
	}

	@Override
	public String getDescription() {
		return null;
	}
}
