package app.model.entity.waiter.state;

import java.util.Vector;

import app.model.Restaurant;
import app.model.entity.State;
import app.model.entity.cook.Cook;
import app.model.entity.cook.state.CookIdle;
import app.model.entity.waiter.Waiter;
import app.utility.Timer;

public class WaiterWaitCook extends Timer implements State {
	private Waiter sender;
	private Vector<Cook> cookList;
	
	public WaiterWaitCook(Waiter sender) {
		cookList = Restaurant.getInstance().getCooks();
		this.sender = sender;
	}

	@Override
	public void change() {
		
	}

	@Override
	protected void timerOnTick() {
		if(getTick() % getTicksPerSecond() == 0) {
			for (Cook cook : cookList) {
				if(cook.getState().getClass() == CookIdle.class) {
					change();
				}
			}
		}
	}

	@Override
	public String getDescription() {
		return null;
	}
}
