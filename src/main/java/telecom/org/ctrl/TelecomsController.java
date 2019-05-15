package telecom.org.ctrl;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telecom.org.dm.Customer;

@RestController
@RequestMapping("/teleapp")
public class TelecomsController {
	
	@GetMapping("/telnumbers")
	Set<String> getTelephoneNumbers() {
		
				
		return null;
	}
	
	
	@GetMapping("/telnumbers/{custid}")
	Set<String> getTelephoneNumbers(@PathVariable Long custid) {
		
				
		return null;
	}
	
	
	
	@PutMapping("/telnumbers/{custid}/{telnumber}")
	Customer activateNumber(@PathVariable Long custid, @PathVariable String telnumber) {   // @RequestBody Customer c

		
		
		return null;
	}

	
}
