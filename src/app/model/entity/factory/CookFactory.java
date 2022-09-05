package app.model.entity.factory;

import app.model.entity.Employee;
import app.model.entity.cook.Cook;

public class CookFactory implements EmployeeFactory {
	
	private static CookFactory singleton;
	public static CookFactory getInstance() {
		if(singleton == null) singleton = new CookFactory();
		return singleton;
	}

	private CookFactory() { }
	
	@Override
	public Employee makeEmployee(String name) {
		return new Cook(name);
	}
}
	