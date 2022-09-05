package app.model.entity.customer;

import java.util.Vector;

public class GeneratorFetcher implements Subject{
	
	private static GeneratorFetcher singleton;
	public static GeneratorFetcher getInstance() {
		if(singleton == null) singleton = new GeneratorFetcher();
		return singleton;
	}
	
	private GeneratorFetcher() {}
	
	private Vector<Observer> observers = new Vector<Observer>();
	
	@Override
	public void subscribe(Observer ob) {
		observers.add(ob);
	}

	@Override
	public void unsubscribe(Observer ob) {
		observers.remove(ob);
	}

	@Override
	public void doAction() {
		for (Observer observer : observers) {
			observer.action();
		}
	}

	@Override
	public void reset() {
		observers.clear();
	}
}
