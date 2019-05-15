package telecom.org.dao;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import telecom.org.dm.Customer;
import telecom.org.dm.PhoneNumber;

@RunWith(SpringRunner.class)
public class TelNumbersFactoryTests {

	private ITelNumbersDao telNumbersFactory;
		
	@Before
	public void setup() {
		telNumbersFactory = new TelNumbersFactory();
	}
	
	
	
	@Test
	public void doTest() {
		Set<String> telNums = telNumbersFactory.getTelephoneNumbers();
		Set<String> custTelNums = telNumbersFactory.getTelephoneNumbers(3L);
		System.out.println("");
	}
	
	
	
	public void testGetTelephoneNumbers() {
		((TelNumbersFactory)telNumbersFactory).makeInitialDummyCustomers();
		
		Set<String> telNums = telNumbersFactory.getTelephoneNumbers();
		
		//  test empty set returned if none
		
		
	}
		
	
	public void testGetCustomerTelephoneNumbers() {
		Long custId = 3L;
		Set<String> custTelNums = telNumbersFactory.getTelephoneNumbers(custId);
	}
	
	
	public void testGetCustomerTelephoneNumbersNoSuchCustomer() {
		Long custId = 999L;
		Set<String> custTelNums = telNumbersFactory.getTelephoneNumbers(custId);
		
		// test throws CustomerNotFoundException
	}
	
	
	
	public void testActivateNumber() {
		Long custId = 1L;
		String telNum = "01484876745";
		Customer customer = telNumbersFactory.activateNumber(custId, telNum);
		Optional<PhoneNumber> number = customer.getPhoneNumbers().stream().filter(p->p.getNumber().equals(telNum)).findFirst();
	}
	
	
	public void testActivateNumberNoSuchCustomer() {
		// test throws CustomerNotFoundException
	}
	
	
	public void testActivateNumberNoSuchTelNumber() {
		// test throws NoSuchTelNumberException 
	}
	
	
	public void testActivateNumberTelNumberAlreadyActivated() {
		// test throws TelNumberAlreadyActivatedException
	}
	
	
}
