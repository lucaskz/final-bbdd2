package bbdd2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Cart implements Serializable {
	
	@Id
	private String id;
	
	@DBRef
	private Collection<Stock> stocks = new ArrayList<>();
	
	public Cart() {
	}
	
	public void addProduct(Product product, Integer amount) {
		Optional<Stock> stock = stocks.stream().filter(s -> s.getProduct().getId().equals(product.getId())).findFirst();
		if (!stock.isPresent()) {
			stock = Optional.of(new Stock(product));
			stocks.add(stock.get());
		}
		stock.ifPresent(s -> s.setAmount(s.getAmount() + amount));
	}
	
	public double getPrice(){
		return stocks.stream().mapToDouble(value -> value.getProduct().getPrice()).sum();
	}
	
	public Collection<Stock> getStocks() {
		return stocks;
	}
	
	public void setStocks(Collection<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
