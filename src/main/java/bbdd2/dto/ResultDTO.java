package bbdd2.dto;

public class ResultDTO {
	
	private CartDTO cartDTO;
	
	private String error;
	
	public ResultDTO(){}
	
	public ResultDTO(String error){
		this.error = error;
	}
	
	public ResultDTO(CartDTO cartDTO){
		this.cartDTO = cartDTO;
	}
	
	public CartDTO getCartDTO() {
		return cartDTO;
	}
	
	public void setCartDTO(CartDTO cartDTO) {
		this.cartDTO = cartDTO;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
}
