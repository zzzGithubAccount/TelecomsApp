package telecom.org.dm;
import java.util.Set;
import lombok.Data;

@Data 
public class Customer {

	private Long id;
	
	private String email;
	
	private Set<PhoneNumber> phoneNumbers;
	
	public Customer() {
		
	}
	
	public Customer(String email, Set<PhoneNumber> phoneNumbers) {
		this.email = email;
		this.phoneNumbers = phoneNumbers;
	}
	
	
	public Customer(Long id, String email, Set<PhoneNumber> phoneNumbers) {
		this(email, phoneNumbers);
		this.id = id;
	}
	
}
