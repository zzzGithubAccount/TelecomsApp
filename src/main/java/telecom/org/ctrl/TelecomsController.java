package telecom.org.ctrl;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telecom.org.dao.ITelNumbersDao;
import telecom.org.dm.Customer;

@RestController
@RequestMapping("/teleapp")
public class TelecomsController {
	
	private static final Logger logger = LoggerFactory.getLogger(TelecomsController.class);
	
	private final ITelNumbersDao telNumbersFactory;
	
	
	TelecomsController(ITelNumbersDao telNumbersFactory) {
		this.telNumbersFactory = telNumbersFactory;
	}
	

	@GetMapping("/telnumbers")
	Set<String> getTelephoneNumbers() {
		Set<String> telNumbers = telNumbersFactory.getTelephoneNumbers();
		logger.info("retrieved all telephone numbers: ");
		telNumbers.stream().forEach(t->logger.info("msg: " + t));
		return telNumbers;	
	}
	
	
	@GetMapping("/telnumbers/{customerId}")
	Set<String> getTelephoneNumbers(@PathVariable Long customerId) {
		logger.info("Customer id = " + customerId);
		Set<String> telNumbers = telNumbersFactory.getTelephoneNumbers(customerId);
		logger.info("retrieved telephone numbers for customer: " + customerId);
		telNumbers.stream().forEach(t->logger.info("msg: " + t));
		return telNumbers;
	}
	
	
	@PutMapping("/telnumbers/{customerId}/{telnumber}")
	Customer activateNumber(@PathVariable Long customerId, @PathVariable String telnumber) {   // @RequestBody Customer c
		logger.info("Preparing to activate telphone number " + telnumber + " for customer id " + customerId);
		Customer customer = telNumbersFactory.activateNumber(customerId, telnumber);
		logger.info("Updated customer with activated telephone number: " + customer.toString());
		return customer;
	}

	
}
