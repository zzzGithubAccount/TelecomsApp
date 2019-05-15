package telecom.org.dao;
import java.util.Set;
import org.springframework.web.bind.annotation.PathVariable;
import telecom.org.dm.Customer;

public interface ITelNumbersDao {
	
	Set<String> getTelephoneNumbers();
	
	Set<String> getTelephoneNumbers(@PathVariable Long id);
	
	Customer activateNumber(@PathVariable Long custid, @PathVariable String telnumber);
	
}
