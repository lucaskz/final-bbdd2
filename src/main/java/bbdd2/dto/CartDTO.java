package bbdd2.dto;

import java.util.ArrayList;
import java.util.Collection;

import bbdd2.model.Cart;

public class CartDTO {
	
	public CartDTO() {
	}
	
	public CartDTO(Cart cart) {
		cart.getStocks().forEach(stock -> products.add(new StockDTO(stock)));
	}
	
	private Collection<StockDTO> products = new ArrayList<>();
	
	private double price;
	
	public Collection<StockDTO> getProducts() {
		return products;
	}
	
	public void setProducts(Collection<StockDTO> products) {
		this.products = products;
	}
	
	public double getPrice() {
		return products.stream().mapToDouble(stockDTO -> stockDTO.getProduct().getPrice() * stockDTO.getAmount()).sum();
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
}
