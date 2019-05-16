package telecom.org.ctrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import telecom.org.dao.ITelNumbersDao;
import telecom.org.dm.Customer;
import telecom.org.ex.CustomerNotFoundException;


@RunWith(MockitoJUnitRunner.class)
public class TelecomsControllerTests {
	
	private MockMvc mvc;
	
	@Mock
	private ITelNumbersDao telNumberDao;
		
	@InjectMocks
	private TelecomsController telecomsController;
	
	private JacksonTester<Set<String>> jsonTelNumList;
	
	private JacksonTester<Customer> jsonCustomer;
	
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper()); // maybe remove this....
		mvc = MockMvcBuilders.standaloneSetup(telecomsController)
                .setControllerAdvice(new TelecomsControllerAdvice())
                .build();
	}

	 
	@Test
	public void testGetTelephoneNumbers() throws Exception { 
		Set<String> dummyTelNos = Stream.of("99998", "01776567456", "++357987986587").collect(Collectors.toSet());
		
		// given
		given(telNumberDao.getTelephoneNumbers()) 
	        .willReturn(dummyTelNos);
		
		// when
        MockHttpServletResponse response = mvc.perform(
                get("/teleapp/telnumbers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonTelNumList.write(dummyTelNos).getJson());
	}
	
	
	@Test
	public void testGetCustomerTelephoneNumbers() throws Exception {
		long customerId = 4l;
		Set<String> dummyTelNos = Stream.of("99998", "++357987986587").collect(Collectors.toSet());
		
		// given
		given(telNumberDao.getTelephoneNumbers(customerId)) 
	        .willReturn(dummyTelNos);
		
		// when
        MockHttpServletResponse response = mvc.perform(
                get("/teleapp/telnumbers/" + customerId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonTelNumList.write(dummyTelNos).getJson());
	}
	

	@Test  
	public void testGetCustomerTelephoneNumbersWithBadId() throws Exception {
		long invalidCustomerId = 10l;
		
		// given
		given(telNumberDao.getTelephoneNumbers(invalidCustomerId)) 
			.willThrow(new CustomerNotFoundException(invalidCustomerId));
		
		// when
        MockHttpServletResponse response = mvc.perform(
                get("/teleapp/telnumbers/" + invalidCustomerId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).contains("Could not find customer: " + invalidCustomerId);
	}
	
	
}
