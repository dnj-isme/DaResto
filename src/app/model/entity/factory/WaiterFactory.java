package app.model.entity.factory;

import app.model.entity.Employee;
import app.model.entity.waiter.Waiter;

public class WaiterFactory implements EmployeeFactory {
	
	private static WaiterFactory singleton;
	public static WaiterFactory getInstance() {
		if(singleton == null) singleton = new WaiterFactory();
		return singleton;
	}

	private WaiterFactory() { }

	@Override
	public Employee makeEmployee(String name) {
		return new Waiter(name);
	}
}
