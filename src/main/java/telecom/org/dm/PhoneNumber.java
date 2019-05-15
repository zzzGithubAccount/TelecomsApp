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

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setSerialVersionUID(long serialVersionUID) {
		this.serialVersionUID = serialVersionUID;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
	
	
}
