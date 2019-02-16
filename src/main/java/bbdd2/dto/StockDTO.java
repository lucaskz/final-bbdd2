package bbdd2.dto;

import bbdd2.model.Stock;

public class StockDTO {
	
	private int amount;
	
	private ProductDTO product;
	
	public StockDTO(){}
	
	public StockDTO(Stock stock){
		this.amount = stock.getAmount();
		this.product = new ProductDTO(stock.getProduct());
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public ProductDTO getProduct() {
		return product;
	}
	
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
}
