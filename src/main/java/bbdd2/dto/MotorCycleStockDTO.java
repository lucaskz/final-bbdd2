package bbdd2.dto;

public class MotorCycleStockDTO extends StockDTO{
	
	private MotorCycleDTO product;
	
	public MotorCycleStockDTO(){
	
	}
	
	@Override
	public MotorCycleDTO getProduct() {
		return product;
	}
	
	public void setProduct(MotorCycleDTO product) {
		this.product = product;
	}
}
