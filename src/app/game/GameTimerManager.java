package app.game;

import java.util.Vector;

import app.utility.Timer;

public class GameTimerManager {
	private static GameTimerManager singleton;
	public static GameTimerManager getInstance() {
		if(singleton == null) singleton = new GameTimerManager();
		return singleton;
	}
	
	private Vector<Timer> list = new Vector<Timer>();
	
	public void switchState(GameState state) {
		for (Timer timer : list) {
			switch (state) {
			case STOP:
				timer.stop();
				break;
			case PAUSE:
				timer.pause();
				break;
			case PLAY:
				timer.resume();
				break;
			}
		}
	}
	
	public void register(Timer entity) {
		list.add(entity);
	}
	
	public void remove(Timer entity) {
		list.remove(entity);
	}
	
	public void clear() {
		list.clear();
	}
}
