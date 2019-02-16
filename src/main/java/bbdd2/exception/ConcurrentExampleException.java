package bbdd2.exception;

public class ConcurrentExampleException extends Exception {
	
	private int errorCode;
	private String errorMessage;
	
	public ConcurrentExampleException(String msg) {
		super(msg);
		this.errorMessage = msg;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public String toString() {
		return this.errorCode + " : " + this.getErrorMessage();
	}
}