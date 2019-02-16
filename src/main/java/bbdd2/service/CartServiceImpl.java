package bbdd2.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import bbdd2.dto.CartDTO;
import bbdd2.dto.ResultDTO;
import bbdd2.model.Cart;
import bbdd2.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	private final int PAGE_SIZE = 3;
	
	@Autowired
	StockService stockService;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	TaskExecutor taskExecutor;
	
	@Override
	public Collection<ResultDTO> sellProduct(CartDTO cartDTO, boolean concurrent) throws Exception {
		Collection<ResultDTO> result = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<CallableCartService> callables = new ArrayList<>();
		
		callables.add(new CallableCartService(this.stockService, this.cartRepository, cartDTO));
		if (concurrent) {
			callables.add(new CallableCartService(this.stockService, this.cartRepository, cartDTO));
		}
		executor.invokeAll(callables).stream().map(future -> {
			ResultDTO resultDTO = new ResultDTO();
			try {
				resultDTO.setCartDTO(future.get());
			} catch (Exception e) {
				resultDTO.setError(e.getMessage());
			}
			return resultDTO;
		}).forEach(resultDTO -> result.add(resultDTO));
		
		return result;
	}
	
	@Override
	public List<Cart> getCarts() {
		return this.cartRepository.findAll();
	}
	
	@Override
	public Collection<List<Cart>> getPaginatedCarts() {
		List<Cart> carts = this.getCarts();
		Collection<List<Cart>> paginatedCarts = new ArrayList<>();
		IntStream.range(0, (carts.size() + PAGE_SIZE - 1) / PAGE_SIZE)
				.mapToObj(i -> carts.subList(i * PAGE_SIZE, Math.min(PAGE_SIZE * (i + 1), carts.size())))
				.forEach( subCart ->  paginatedCarts.add(subCart));
		return paginatedCarts;
	}
}
