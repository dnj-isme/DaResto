package app.game;

import java.util.Vector;

import app.model.Restaurant;
import app.model.entity.Employee;
import app.model.entity.Person;
import app.model.entity.cook.Cook;
import app.model.entity.factory.CookFactory;
import app.model.entity.factory.EmployeeFactory;
import app.model.entity.factory.WaiterFactory;
import app.model.entity.waiter.Waiter;
import app.utility.Chair;
import app.utility.Utility;

public class PauseMenu {
	
	private static PauseMenu singleton;
	public static PauseMenu getInstance() {
		if(singleton == null) singleton = new PauseMenu();
		return singleton;
	}
	
	private PauseMenu() {}
	
	private final int chairCost = 100;
	private final int waiterCost = 150;
	private final int cookCost = 200;
	private final int upgradeCost = 150;

	private GameTimerManager stateManager = GameTimerManager.getInstance();
	private Restaurant restaurant = Restaurant.getInstance();
	
	public void pause() {
		boolean stay = true;
		while(stay) {
			Utility.cls();
			
			System.out.println("Pause menu...");			
			System.out.println("");
			restaurant.printStatus();
			System.out.println("");
			System.out.println("1. Continue business");
			System.out.println("2. Upgrade restaurant");
			System.out.println("3. Close business");
			int input = Utility.getInt(">> ", 1, 3);
			
			switch (input) {
			case 1:
				stay = false;
				stateManager.switchState(GameState.PLAY);
				break;
			case 2:
				upgradeMenu();
				break;
			case 3: default:
				stay = false;
				stateManager.switchState(GameState.STOP);
				break;
			}
		}
	}

	private void upgradeMenu() {
		boolean stay = true;
		while(stay) {
			Utility.cls();
			System.out.println("Upgrade menu");			
			System.out.println("");
			restaurant.printStatus();
			System.out.println("");
			System.out.printf("1. Increase Restaurant’s Seat (Rp. %d)\n", restaurant.getChairs().size() * chairCost);
			System.out.println("2. Hire new employee");
			System.out.println("3. Upgrade Waiter (Rp. 150)");
			System.out.println("4. Upgrade Cook (Rp. 150)");
			System.out.println("5. Back to pause menu");
			int input = Utility.getInt(">> ", 1, 5);
			
			switch (input) {
			case 1:
				addSeat();
				break;
			case 2:
				newEmployee();
				break;
			case 3:
				upgradeWaiter();
				break;
			case 4:
				upgradeCook();
				break;
			case 5: default:
				stay = false;
				break;
			}
		}
	}
	
	private void addSeat() {
		Utility.cls();
		
		// validasi 1: chair > 13
		int size = restaurant.getChairs().size();
		if(size >= 13) {
			System.out.println("Can't add more chairs!");
			Utility.nextLine();
			return;
		}
		
		// validasi 2: money is not enough
		if(restaurant.getMoney() < size * chairCost) {
			System.out.println("Money isn't enough!");
			Utility.nextLine();
			return;
		}
		
		Chair newChair = new Chair(size + 1);
		restaurant.getChairs().add(newChair);
		restaurant.addMoney(-size * 100);
		System.out.println("Chair added successfully");
		System.out.println("Press ENTER to continue...");
		Utility.nextLine();
	}

	private void newEmployee() {		
		boolean stay = true;
		while(stay) {
			Utility.cls();
			restaurant.printStatus();
			System.out.println("");
			System.out.println("Hire new Employee");
			System.out.printf("1. Hire New Waiter (Rp. %d)\n", restaurant.getWaiters().size() * waiterCost );
			System.out.printf("2. Hire New Cook (Rp. %d)\n", restaurant.getCooks().size() * cookCost);
			System.out.println("3. Back to Upgrade Menu");
			int input = Utility.getInt(">> ", 1, 3);
			switch (input) {
			case 1:
				addWaiter();
				break;
			case 2:
				addCook();
				break;
			case 3: default:
				stay = false;
				break;
			}
		}
	}

	private void addWaiter() {
		Utility.cls();
		
		// validasi 1: waiter > 7
		int size = restaurant.getWaiters().size();
		if(size >= 7) {
			System.out.println("Can't add more waiters!");
			Utility.nextLine();
			return;
		}
		
		// validasi 2: money is not enough
		if(restaurant.getMoney() < size * waiterCost) {
			System.out.println("Money isn't enough!");
			Utility.nextLine();
			return;
		}
		
		EmployeeFactory factory = WaiterFactory.getInstance();
		while(true) {
			String name = Utility.generateName();
			if(Person.searchName(restaurant.getPersonList(), name) == -1) {
				Employee newWaiter = factory.makeEmployee(name);
				restaurant.getPersonList().add(newWaiter);
				break;
			}
		}
		restaurant.addMoney(-size * waiterCost);
		System.out.println("Waiter hired successfully");
		System.out.println("Press ENTER to continue...");
		Utility.nextLine();
	}

	private void addCook() {
		Utility.cls();
		
		// validasi 1: cook > 7
		int size = restaurant.getCooks().size();
		if(size >= 7) {
			System.out.println("Can't add more cooks!");
			Utility.nextLine();
			return;
		}
		
		// validasi 2: money is not enough
		if(restaurant.getMoney() < size * cookCost) {
			System.out.println("Money isn't enough!");
			Utility.nextLine();
			return;
		}
		
		EmployeeFactory factory = CookFactory.getInstance();
		while(true) {
			String name = Utility.generateName();
			if(Person.searchName(restaurant.getPersonList(), name) == -1) {
				Employee newCook = factory.makeEmployee(name);
				restaurant.getPersonList().add(newCook);
				break;
			}
		}
		restaurant.addMoney(-size * cookCost);
		System.out.println("Waiter hired successfully");
		System.out.println("Press ENTER to continue...");
		Utility.nextLine();
	}

	private void upgradeWaiter() {
		Vector<Waiter> waiterList = restaurant.getWaiters();
		
		boolean stay = true;
		while(stay) {
			Utility.cls();
			
			System.out.println("Upgrade Waiter menu");
			System.out.println("");
			restaurant.printStatus();
			System.out.println("");
			System.out.println("Choose Waiter (0 to exit)");
			for (int i = 0; i < waiterList.size(); i++) {
				Waiter waiter = waiterList.get(i);
				System.out.printf("%d. %s; Speed: %d\n", i + 1, waiter.getName(), waiter.getSpeed());
			}
			int input = Utility.getInt(">> ", 0, waiterList.size());
			
			if(input == 0) return;
			else upgradeWaiter(waiterList.get(input - 1));
		}
	}
	
	private void upgradeWaiter(Waiter waiter) {
		// validasi 1: speed > 5
		if(waiter.getSpeed() >= 5) {
			System.out.println("Can't add more speed!");
			Utility.nextLine();
			return;
		}
		
		// validasi 2: money is not enough
		if(restaurant.getMoney() < upgradeCost) {
			System.out.println("Money isn't enough!");
			Utility.nextLine();
			return;
		}
		
		waiter.addSpeed();
		restaurant.addMoney(-upgradeCost);
		System.out.println("Employee upgraded successfully");
		System.out.println("Press ENTER to continue...");
		Utility.nextLine();
	}

	private void upgradeCook() {
		Vector<Cook> cookList = restaurant.getCooks();
		
		boolean stay = true;
		while(stay) {
			Utility.cls();
			
			System.out.println("Upgrade Cook menu");
			System.out.println("");
			restaurant.printStatus();
			System.out.println("");
			System.out.println("Choose Waiter (0 to exit)");
			for (int i = 0; i < cookList.size(); i++) {
				Cook cook = cookList.get(i);
				System.out.printf("%d. %s; Speed: %d; Skill: %d\n", i + 1, cook.getName(), cook.getSpeed(), cook.getSkill());
			}
			int input = Utility.getInt(">> ", 0, cookList.size());
			
			if(input == 0) return;
			else upgradeCook(cookList.get(input - 1));
		}
	}
	
	private void upgradeCook(Cook cook) {
		// validasi 1: money is not enough
		if(restaurant.getMoney() < upgradeCost) {
			System.out.println("Money isn't enough!");
			Utility.nextLine();
			return;
		}
		
		boolean valid = false;
		String input = "";
		System.out.println("Choose what to upgrade [speed | skill]");
		
		do {
			System.out.print(">> ");
			input = Utility.nextLine().toLowerCase();
			valid = input.equals("speed") || input.equals("skill");
		} while (!valid);
		
		switch(input) {
		case "speed":
			if(cook.getSpeed() >= 5) {
				System.out.println("Can't add more speed!");
				Utility.nextLine();
				return;
			}
			cook.addSpeed();
			break;
		case "skill":
			if(cook.getSkill() >= 5) {
				System.out.println("Can't add more skill!");
				Utility.nextLine();
				return;
			}
			cook.addSkill();
			break;			
		default:
			System.out.println("Error occured...");
			break;
		}
		
		restaurant.addMoney(-upgradeCost);
		System.out.println("Employee upgraded successfully");
		System.out.println("Press ENTER to continue...");
		Utility.nextLine();
	}
}
