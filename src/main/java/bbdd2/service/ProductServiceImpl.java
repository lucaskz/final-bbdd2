package bbdd2.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bbdd2.model.MotorCycle;
import bbdd2.model.Product;
import bbdd2.repository.MotorCycleRepository;
import bbdd2.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MotorCycleRepository motorCycleRepository;
	
	@Override
	public Product save(Product product) {
		return this.productRepository.save(product);
	}
	
	@Override
	public MotorCycle newMotorCycle(String name) {
		MotorCycle product = new MotorCycle();
		product.setName(name);
		this.productRepository.save(product);
		return product;
	}
	
	@Override
	public Collection<Product> getProducts() {
		return StreamSupport.stream(this.productRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	@Override
	public MotorCycle findMotorCycleByName(String name) {
		return this.motorCycleRepository.findByName(name);
	}
	
	@Override
	public Product findProductByName(String name) {
		return this.productRepository.findProductByName(name);
	}
}
