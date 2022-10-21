package service.broker;

import java.util.*;

import com.sun.org.apache.xpath.internal.operations.Quo;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.core.ClientApplication;
import service.core.ClientInfo;
import service.core.Quotation;

/**
 * Implementation of the broker service that uses the Service Registry.
 * 
 * @author Rem
 *
 */
@RestController
public class LocalBrokerService {

	private Map<Integer, ClientApplication> applications = new HashMap<>();

	static List<String> services = new ArrayList<String>();//this will hold the services values
	private int seedId = 100;

	@RequestMapping(value="/applications",method= RequestMethod.POST)
	public ClientApplication getApplications(@RequestBody ClientInfo info){
		ClientApplication clientApplication = new ClientApplication();
		clientApplication.setQuotations(getQuotations(info)); //calling the getQuotations
		clientApplication.setClientInfo(info);
		clientApplication.setApplicationID(seedId);
		applications.put(seedId++,clientApplication);
		return clientApplication;
	}

	public ArrayList<Quotation> getQuotations(ClientInfo info) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<ClientInfo> request = new HttpEntity<>(info);
		ArrayList<Quotation> quotations = new ArrayList<Quotation>();
		String url = "";
		if(services.size()==0){//incase parameter is not passed and using without docker
			services.add("localhost:8081");
			services.add("localhost:8082");
			services.add("localhost:8083");
		}
		for (int i = 0; i < services.size(); i++) {
			url = "http://" + services.get(i)+ "/quotations";
			Quotation quotation = restTemplate.postForObject(url, request, Quotation.class);
			quotations.add(quotation);
		}
		return quotations;
	}

	@RequestMapping(value="/application/{application-number}",method= RequestMethod.GET)
	public ClientApplication getSpecificApplication(@PathVariable("application-number") int seedID){
		return applications.get(seedID);
	}

	@RequestMapping(value="/application",method= RequestMethod.GET)
	public List<ClientApplication> listOfApplication(){
		List<ClientApplication> listOfApplications = new ArrayList<ClientApplication>(applications.values());
		return listOfApplications;
	}
}
