package app.model.entity.customer;

public interface Subject {
	public void subscribe(Observer ob);
	public void unsubscribe (Observer ob);
	public void doAction();
	public void reset();
}
