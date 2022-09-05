package app.utility;

import app.model.entity.customer.Customer;

public class Chair {
	private Customer occupant;
	private int number;
	
	public Chair(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public Customer getOccupant() {
		return occupant;
	}

	public void setOccupant(Customer occupant) {
		this.occupant = occupant;
	}
	
	public boolean isOccupied() {
		return occupant != null;
	}
}
