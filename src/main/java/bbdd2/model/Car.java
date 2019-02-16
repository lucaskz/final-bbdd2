package bbdd2.model;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.Document;

@Persistent
@Document(collection = "product")
public class Car extends Product {
	
	public Car() {
	}
	
	@Override
	public double getPrice() {
		return this.price;
	}
}
