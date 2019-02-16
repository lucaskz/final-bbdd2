package bbdd2.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import bbdd2.dto.MotorCycleStockDTO;
import bbdd2.dto.StockDTO;
import bbdd2.exception.ConcurrentExampleException;
import bbdd2.model.AvailableStock;
import bbdd2.model.Car;
import bbdd2.model.MotorCycle;
import bbdd2.model.Product;
import bbdd2.model.Stock;
import bbdd2.repository.AvailableStockRepository;
import bbdd2.repository.ProductRepository;
import bbdd2.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private MotorCycleService motorCycleService;
	
	@Autowired
	private AvailableStockRepository availableStockRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CarService carService;
	
	//should be generic add product
	public AvailableStock addMotorCycleStock(MotorCycleStockDTO motorCycleStockDTO) {
		MotorCycle motorCycle = this.motorCycleService.findByName(motorCycleStockDTO.getProduct().getName());
		if (motorCycle == null) {
			motorCycle = new MotorCycle();
			motorCycle.setName(motorCycleStockDTO.getProduct().getName());
			motorCycle.setPrice(motorCycleStockDTO.getProduct().getPrice());
			motorCycle.setCc(motorCycleStockDTO.getProduct().getCc());
			motorCycle = this.productRepository.save(motorCycle);
		}
		return this.addProductStock(motorCycle, motorCycleStockDTO.getAmount());
	}
	
	public AvailableStock addCarStock(StockDTO stockDTO) {
		Car product = this.carService.findByName(stockDTO.getProduct().getName());
		if (product == null) {
			product = new Car();
			product.setName(stockDTO.getProduct().getName());
			product.setPrice(stockDTO.getProduct().getPrice());
			product = this.productRepository.save(product);
		}
		return this.addProductStock(product, stockDTO.getAmount());
	}
	
	private AvailableStock addProductStock(Product product, int amount) {
		AvailableStock stock = null;
		if (product != null) {
			stock = this.availableStockRepository.findStockByProduct(product);
			if (stock == null) {
				stock = new AvailableStock();
				stock.setProduct(product);
			}
			stock.setAmount(stock.getAmount() + amount);
			
			stock = availableStockRepository.save(stock);
		}
		return stock;
	}
	
	@Override
	public AvailableStock decreaseAvailableProductStock(String productId, int amount) throws ConcurrentExampleException {
		AvailableStock stock = null;
		if (productId != null) {
			Optional<Product> product = this.productRepository.findById(productId);
			if (product.isPresent()) {
				stock = this.availableStockRepository.findStockByProduct(product.get());
				if (stock != null && (stock.getAmount() - amount) >= 0) {
					stock.setAmount(stock.getAmount() - amount);
					try {
						stock = availableStockRepository.save(stock);
					} catch (OptimisticLockingFailureException e) {
						stock = this.availableStockRepository.findStockByProduct(product.get());
						if (stock.getAmount() - amount >= 0) {
							return this.decreaseAvailableProductStock(productId, amount);
						} else {
							throw new ConcurrentExampleException("Concurrent modification - Product already consumed!");
						}
					}
					
				} else {
					throw new ConcurrentExampleException("No stock available!");
				}
			}
		}
		return stock;
	}
	
	@Override
	public Collection<AvailableStock> getAvailableStock() {
		return this.availableStockRepository.findAll();
	}
	
	@Override
	public Stock restoreStock(Product product, int amount) {
		AvailableStock stock = availableStockRepository.findStockByProduct(product);
		if (stock != null) {
			stock.setAmount(stock.getAmount() + amount);
			availableStockRepository.save(stock);
		}
		return stock;
	}
	
	@Override
	public Stock save(Stock historicStock) {
		return stockRepository.save(historicStock);
	}
}
