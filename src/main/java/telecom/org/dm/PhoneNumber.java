package telecom.org.dm;
import lombok.Data;

@Data
public class PhoneNumber {
	
	private long serialVersionUID;
	
	private boolean activated = false;

	private String number;
	
	public PhoneNumber(String number) {
		this.number = number;
	}
	
	public PhoneNumber(String number, boolean activated) {
		this.number = number;
		this.activated = activated;
	}
	
}
