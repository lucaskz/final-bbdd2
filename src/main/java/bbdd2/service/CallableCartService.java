package bbdd2.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.dao.OptimisticLockingFailureException;
import bbdd2.dto.CartDTO;
import bbdd2.dto.ProductDTO;
import bbdd2.dto.StockDTO;
import bbdd2.exception.ConcurrentExampleException;
import bbdd2.model.AvailableStock;
import bbdd2.model.Cart;
import bbdd2.repository.CartRepository;

public class CallableCartService implements Callable<CartDTO>{
	
	public CallableCartService(StockService stockService, CartRepository cartRepository, CartDTO cartDTO){
		this.cartDTO = cartDTO;
		this.cartRepository = cartRepository;
		this.stockService = stockService;
	}
	
	private StockService stockService;

	private CartDTO cartDTO;
	
	private CartRepository cartRepository;
	
	@Override
	public CartDTO call() throws Exception {
		Cart cart = new Cart();
		for (StockDTO stockDTO : cartDTO.getProducts()) {
			AvailableStock stock;
			try{
				stock = stockService.decreaseAvailableProductStock(stockDTO.getProduct().getId(), stockDTO.getAmount());
			}catch (ConcurrentExampleException e){
				cart.getStocks().forEach(s -> stockService.restoreStock(s.getProduct(), s.getAmount()));
				throw new ConcurrentExampleException(e.getErrorMessage());
			}
			cart.addProduct(stock.getProduct(), stockDTO.getAmount());
		}
		return new CartDTO(saveCart(cart));
	}
	
	private Cart saveCart(Cart cart) {
		cart.getStocks().stream().filter(historicStock -> historicStock.getId() == null).forEach(historicStock -> {
			stockService.save(historicStock);
		});
		return this.cartRepository.save(cart);
	}
}
