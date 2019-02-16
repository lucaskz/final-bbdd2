package bbdd2.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import bbdd2.model.Product;
import bbdd2.model.Stock;

@Repository
public interface StockRepository extends BaseRepository<Stock , String> {
	
}
