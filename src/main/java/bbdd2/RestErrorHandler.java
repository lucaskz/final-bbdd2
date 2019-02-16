package bbdd2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import bbdd2.exception.ConcurrentExampleException;

@ControllerAdvice
public class RestErrorHandler {
	
	@ExceptionHandler(ConcurrentExampleException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Object processValidationError(ConcurrentExampleException ex) {
		String result = ex.getErrorMessage();
		System.out.println("###########"+result);
		return ex;
	}
}