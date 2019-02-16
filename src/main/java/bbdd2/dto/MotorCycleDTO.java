package bbdd2.dto;

import bbdd2.model.Product;

public class MotorCycleDTO extends ProductDTO {
	
	public MotorCycleDTO(Product product) {
		super(product);
	}
	
	public MotorCycleDTO() {
	
	}
	
	private String cc;
	
	public String getCc() {
		return cc;
	}
	
	public void setCc(String cc) {
		this.cc = cc;
	}
}
