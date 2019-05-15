package telecom.org.ctrl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import telecom.org.ex.*;

@ControllerAdvice  
public class TelecomsControllerAdvice {

	@ResponseBody                                      
	@ExceptionHandler(CustomerNotFoundException.class)  
	@ResponseStatus(HttpStatus.NOT_FOUND)               
	String customerNotFoundHandler(CustomerNotFoundException e) {
		return e.getMessage();
	}

	
	@ResponseBody                                      
	@ExceptionHandler(TelNumberAlreadyActivatedException.class)  
	@ResponseStatus(HttpStatus.NOT_MODIFIED)               
	String telNumberAlreadyActivatedHandler(TelNumberAlreadyActivatedException e) {
		return e.getMessage();
	}

	
	@ResponseBody                                      
	@ExceptionHandler(NoSuchTelNumberException.class)  
	@ResponseStatus(HttpStatus.NOT_FOUND)               
	String noSuchTelNumberHandler(NoSuchTelNumberException e) {
		return e.getMessage();
	}

	
}

