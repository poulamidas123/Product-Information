package JDBC2;

import java.io.Serializable;

//VALUE OBJECTS

public class Product implements Serializable {

	private String id, description;
	private double price;
	private int quantity;

	private static final long serialVersionUID = 1L;

	public Product() {
	}

	public Product(String id, String description, double price, int quantity) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(String id, double price, int quantity) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
