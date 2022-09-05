package app.model.entity;

import java.util.Vector;

public abstract class Person {
	private String name;
	
	public Person(String name) {
		this.name = name;
	}
	
//	private Phase phase;
//
//	public Phase getPhase() {
//		return phase;
//	}
//
//	public void setPhase(Phase phase) {
//		this.phase = phase;
//	}

	public String getName() {
		return this.name;
	}
	
	public static int searchName(Vector<Person> list, String search) {
		for (int i = 0; i < list.size(); i++) {
			Person person = list.get(i);
			if(person.getName().equals(search)) return i;
		}
		return -1;
	}
}