package telecom.org.dao;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import telecom.org.dm.Customer;
import telecom.org.dm.PhoneNumber;
import telecom.org.ex.CustomerNotFoundException;
import telecom.org.ex.NoSuchTelNumberException;
import telecom.org.ex.TelNumberAlreadyActivatedException;

@Component
public class TelNumbersFactory implements ITelNumbersDao {

	private static final Logger logger = LoggerFactory.getLogger(TelNumbersFactory.class);
	
	private static Set<Customer> customers;
	
	public TelNumbersFactory() {
		makeInitialDummyCustomers();
	}
	
	public void makeInitialDummyCustomers() {
		Set<PhoneNumber> phoneNumSet1 = 
				Stream.of(new PhoneNumber("07658678567"), new PhoneNumber("01484876745")).collect(Collectors.toSet());
		Set<PhoneNumber> phoneNumSet2 = 
				Stream.of(new PhoneNumber("0999765534"), new PhoneNumber("01262678678")).collect(Collectors.toSet());
		Set<PhoneNumber> phoneNumSet3 = 
				Stream.of(new PhoneNumber("+44786567456")).collect(Collectors.toSet());
		
		customers = Stream.of(new Customer(1L, "myemail@ddd.com", phoneNumSet1),
							  new Customer(2L, "myemail@ddd.com", phoneNumSet2),
						      new Customer(3L, "myemail@ddd.com", phoneNumSet3)).collect(Collectors.toSet());
		logger.info("created dummy customers");
	}
	
	
	public static final Set<Customer> getDummyCustomers() {
		return customers;
	}
	
	
	public Set<String> getTelephoneNumbers() {
		return customers.stream().flatMap(c->c.getPhoneNumbers().stream()).map(p->p.getNumber()).collect(Collectors.<String>toSet());
	}
		
	
	public Set<String> getTelephoneNumbers(Long customerId) throws CustomerNotFoundException {
		Optional<Customer> customer = 
				customers.stream().filter(c->c.getId().longValue() == customerId.longValue()).findFirst();
		customer.orElseThrow(() -> new CustomerNotFoundException(customerId));
		return customer.get().getPhoneNumbers().stream().map(p->p.getNumber()).collect(Collectors.<String>toSet());
	}

	
	public Customer activateNumber(Long customerId, String telnumber) 
			throws CustomerNotFoundException, NoSuchTelNumberException, TelNumberAlreadyActivatedException {
		Optional<Customer> customer = 
				customers.stream().filter(c->c.getId().longValue() == customerId.longValue()).findFirst();
		customer.orElseThrow(() -> new CustomerNotFoundException(customerId));
		Optional<PhoneNumber> phoneNumber = customer.get().getPhoneNumbers().stream().filter(p->p.getNumber().equals(telnumber)).findFirst();
		phoneNumber.orElseThrow(() -> new NoSuchTelNumberException(telnumber, customerId));
		
		if (phoneNumber.get().isActivated()) {
			throw new TelNumberAlreadyActivatedException(telnumber);
		}
		
		phoneNumber.get().setActivated(true);
		return customer.get();
	}
	
	
}
