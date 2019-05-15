package telecom.org.ex;

public class NoSuchTelNumberException extends RuntimeException {
	public NoSuchTelNumberException(String telNumber, Long customerId) {
	    super("Telephone number '" + telNumber + "' not found for customer: " + customerId);
	}
}


