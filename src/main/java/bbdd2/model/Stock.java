package bbdd2.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Stock implements Serializable {
	
	@DBRef
	private Product product;
	private int amount;
	@Id
	private String id;
	
	public Stock() {
	}
	
	public Stock(Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
