package bbdd2.service;

import java.util.Collection;
import java.util.List;

import bbdd2.dto.CartDTO;
import bbdd2.dto.ResultDTO;
import bbdd2.model.Cart;

public interface CartService {
	
	Collection<ResultDTO> sellProduct(CartDTO cartDTO, boolean concurrent) throws Exception;
	
	List<Cart> getCarts();
	
	Collection<List<Cart>> getPaginatedCarts();
}
