package bbdd2.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public abstract class Product implements Serializable {
	
	@Id
	String id;
	
	private String name;
	
	protected double price;
	
	public abstract double getPrice();
	
	public String getId() {
		return id;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
