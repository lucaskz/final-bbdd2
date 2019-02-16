package bbdd2.repository;

import org.springframework.stereotype.Repository;
import bbdd2.model.Product;

@Repository
public interface ProductRepository extends BaseRepository<Product, String> {
	
	Product findProductByName(String name);
}
