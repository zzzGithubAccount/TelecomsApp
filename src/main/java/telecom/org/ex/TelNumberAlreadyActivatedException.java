package telecom.org.ex;

public class TelNumberAlreadyActivatedException extends RuntimeException {
    public TelNumberAlreadyActivatedException(String telNumber) {
	    super("Telephone number already activated:  " + telNumber);
	}
}


  




