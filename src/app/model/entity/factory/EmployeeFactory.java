package app.model.entity.factory;

import app.model.entity.Employee;

public interface EmployeeFactory {
	public Employee makeEmployee(String name);
}
