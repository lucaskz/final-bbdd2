package bbdd2.service;

import java.util.Collection;

import bbdd2.model.MotorCycle;
import bbdd2.model.Product;

public interface ProductService {

	Product save(Product product);
	
	MotorCycle newMotorCycle(String name);
	
	Collection<Product> getProducts();
	
	MotorCycle findMotorCycleByName(String name);
	
	Product findProductByName(String name);
}
