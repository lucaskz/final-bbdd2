package bbdd2.repository;

import org.springframework.stereotype.Repository;
import bbdd2.model.AvailableStock;
import bbdd2.model.Product;

@Repository
public interface AvailableStockRepository extends BaseRepository<AvailableStock, String> {
	
	AvailableStock findStockByProduct(Product product);
	
}
