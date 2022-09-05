package app.model;

import java.util.Vector;

import app.model.entity.Employee;
import app.model.entity.Person;
import app.model.entity.cook.Cook;
import app.model.entity.customer.Customer;
import app.model.entity.factory.CookFactory;
import app.model.entity.factory.EmployeeFactory;
import app.model.entity.factory.WaiterFactory;
import app.model.entity.waiter.Waiter;
import app.utility.Chair;
import app.utility.Utility;

public class Restaurant {
	
	private static Restaurant singleton;
	public static Restaurant getInstance() {
		if(singleton == null) singleton = new Restaurant();
		return singleton;
	}

	private Restaurant() { }
	
	private String name;
	private int score;
	private int money;
	
	private Vector<Person> personList = new Vector<>();
	
	private Vector<Chair> seats = new Vector<>();
	
	public int getMoney() {
		return money;
	}
	public String getName() {
		return name;
	}
	public Vector<Person> getPersonList() {
		return personList;
	}
	public Vector<Chair> getChairs() {
		return seats;
	}
	public synchronized Chair getEmptyChair() {
		for (Chair chair : seats) {
			if(!chair.isOccupied()) return chair;
		}
		return null;
	}
	public Vector<Waiter> getWaiters() {
		Vector<Waiter> output = new Vector<Waiter>();
		for (Person person : personList) {
			if(person.getClass() == Waiter.class) output.add((Waiter) person);
		}
		return output;
	}
	public Vector<Cook> getCooks() {
		Vector<Cook> output = new Vector<Cook>();
		for (Person person : personList) {
			if(person.getClass() == Cook.class) output.add((Cook) person);
		}
		return output;
	}
	public Vector<Customer> getCustomers() {
		Vector<Customer> output = new Vector<Customer>();
		for (Person person : personList) {
			if(person.getClass() == Cook.class) output.add((Customer) person);
		}
		return output;
	}
	
	public synchronized void printStatus() {
		System.out.printf("Restaurant '%s'\n", name);
		System.out.println("Status");
		System.out.printf("Money\t: Rp. %d\n", money);
		System.out.printf("Score\t: %d Points\n", score);
		System.out.printf("Size\t: %d seats\n", seats.size());
	}
	
	public synchronized void printPeople() {
		System.out.println("Chair");
		for (Chair chair : seats) {
			Customer occupant = chair.getOccupant();
			if(occupant == null)
				System.out.printf("%d. [EMPTY]\n", chair.getNumber());
			else System.out.printf("%d. %s (tolerance: %d)\n", chair.getNumber(), occupant.getName(), occupant.getTolerance());
		}
		
		System.out.println("");
		System.out.println("Waiter");
		for (Person person : getWaiters()) {
			System.out.printf("%s\n", person.getName());
		}

		System.out.println("");
		System.out.println("Cook");
		for (Person person : getCooks()) {
			if(person.getClass() == Cook.class) {
				System.out.printf("%s\n", person.getName());
			}
		}
	}
	
	public void reset() {
		personList.clear();
		seats.clear();
		this.name = "";
		this.score = 0;
		this.money = 1300;
	}
	
	public void generate(String name) {
		reset();
		this.name = name;
		
		EmployeeFactory factory = CookFactory.getInstance(); 
		for(int i = 0; i < 2;) {
			String randomName = Utility.generateName();
			if(Person.searchName(personList, randomName) == -1) {
				Employee newEmployee = factory.makeEmployee(randomName);
				personList.add(newEmployee);
				i++;
			}
		}
		
		factory = WaiterFactory.getInstance();
		for(int i = 0; i < 2;) {
			String randomName = Utility.generateName();
			if(Person.searchName(personList, randomName) == -1) {
				Employee newEmployee = factory.makeEmployee(randomName);
				personList.add(newEmployee);
				i++;
			}
		}
		
		for(int i = 0; i < 4; i++) {
			seats.add(new Chair(i + 1));
		}
	}

	public void addMoney(int amount) {
		this.money = Math.max(0, money + amount);
	}
}
