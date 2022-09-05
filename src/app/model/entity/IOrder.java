package app.model.entity;

public interface IOrder {
	public void sendOrder(Person target);
	public void receiveOrder();
}
