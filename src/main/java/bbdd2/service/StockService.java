package bbdd2.service;

import java.util.Collection;

import bbdd2.dto.MotorCycleStockDTO;
import bbdd2.dto.StockDTO;
import bbdd2.exception.ConcurrentExampleException;
import bbdd2.model.AvailableStock;
import bbdd2.model.Product;
import bbdd2.model.Stock;

public interface StockService {
	
	AvailableStock addMotorCycleStock(MotorCycleStockDTO motorCycleStockDTO);
	
	AvailableStock decreaseAvailableProductStock(String product, int amount) throws ConcurrentExampleException;
	
	Collection<AvailableStock> getAvailableStock();
	
	Stock restoreStock(Product product, int amount);
	
	Stock save(Stock historicStock);
	
	AvailableStock addCarStock(StockDTO stockDTO);
}
