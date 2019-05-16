package telecom.org.dao;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import telecom.org.dm.Customer;
import telecom.org.dm.PhoneNumber;
import telecom.org.ex.CustomerNotFoundException;
import telecom.org.ex.NoSuchTelNumberException;
import telecom.org.ex.TelNumberAlreadyActivatedException;


@RunWith(SpringRunner.class)
public class TelNumbersFactoryTests {

	private ITelNumbersDao telNumberDao;
	
	@Before
	public void setup() {
		telNumberDao = new TelNumbersFactory();
	}
	
	
	@Test
	public void testGetTelephoneNumbers() {
		List<String> expTelNums = Stream.of("07658678567", "0999765534", "+44786567456", 
				"01484876745", "01262678678").collect(Collectors.toList()); 
		List<String> actualNums = telNumberDao.getTelephoneNumbers().stream().collect(Collectors.toList());;
		Assert.assertEquals(expTelNums.size(), actualNums.size());		
		expTelNums.sort((o1, o2)->o1.compareTo(o2));
		actualNums.sort((o1, o2)->o1.compareTo(o2));
		for (int i = 0; i < expTelNums.size(); i++) {
			Assert.assertEquals(expTelNums.get(i), actualNums.get(i));
		}
	}
		
	
	public void testGetCustomerTelephoneNumbers() {
		String expTelNum = "+44786567456";
		Long custId = 3L;
		Set<String> actualTelNums = telNumberDao.getTelephoneNumbers(custId);
		Assert.assertEquals(1, actualTelNums.size());
		Assert.assertTrue(actualTelNums.contains(expTelNum)); 
	}

	
	@Test(expected = CustomerNotFoundException.class)
	public void testGetCustomerTelephoneNumbersNoSuchCustomer() {
		Long custId = 999L;
		Set<String> custTelNums = telNumberDao.getTelephoneNumbers(custId);
		Assert.fail("CustomerNotFoundException was not thrown");
	}
	
	
	public void testActivateNumber() {
		Long custId = 1L;
		String telNum = "01484876745";
		Customer customer = telNumberDao.activateNumber(custId, telNum);
		Optional<PhoneNumber> number = customer.getPhoneNumbers().stream().filter(p->p.getNumber().equals(telNum)).findFirst();
		Assert.assertTrue(number.get().isActivated());
	}
	
	
	@Test(expected = CustomerNotFoundException.class)
	public void testActivateNumberNoSuchCustomer() {
		Long custId = 999L;
		String telNum = "01484876745";
		Customer customer = telNumberDao.activateNumber(custId, telNum);
		Assert.fail("CustomerNotFoundException was not thrown");
	}
	
	
	@Test(expected = NoSuchTelNumberException.class)
	public void testActivateNumberNoSuchTelNumber() {
		Long custId = 1L;
		String telNum = "0999991484876745";
		Customer customer = telNumberDao.activateNumber(custId, telNum);
		Assert.fail("NoSuchTelNumberException was not thrown");
	}
	
	
	@Test(expected = TelNumberAlreadyActivatedException.class)
	public void testActivateNumberTelNumberAlreadyActivated() {
		// first activate a phone number
		Long custId = 1L;
		String telNum = "01484876745";
		Customer customer = telNumberDao.activateNumber(custId, telNum);
		Optional<PhoneNumber> number = customer.getPhoneNumbers().stream().filter(p->p.getNumber().equals(telNum)).findFirst();
		Assert.assertTrue(number.get().isActivated());
		
		// now try activating it even though it is already activated
		customer = telNumberDao.activateNumber(custId, telNum);
		Assert.fail("TelNumberAlreadyActivatedException was not thrown");
	}
	
	
}
