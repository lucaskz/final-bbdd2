package bbdd2.model;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.Document;

@Persistent
@Document(collection = "product")
public class MotorCycle extends Product {
	
	private String cc;
	
	public MotorCycle() {
	}
	
	public double getPrice() {
		return this.price + this.price * 0.10;
	}
	
	public String getCc() {
		return cc;
	}
	
	public void setCc(String cc) {
		this.cc = cc;
	}
}
