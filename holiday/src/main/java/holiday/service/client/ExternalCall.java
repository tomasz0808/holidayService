package holiday.service.client;

import java.time.LocalDate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;



public class ExternalCall {
	
	private static final String RESOURCE_URL = "https://holidayapi.com/v1/holidays";
	private Client client;
	private WebTarget wTarget;
	
	public ExternalCall(Client client) {
		this.client = client;	
		this.wTarget = client.target(RESOURCE_URL);
	}

	
	public void getHolidayInMonth(String apiKey, String countryCode, LocalDate date) {
		//query params must be chained together as they return a new target instance
		wTarget=wTarget.queryParam("key", apiKey)
				.queryParam("country", countryCode)
				.queryParam("year", String.valueOf(date.getYear()))
				.queryParam("month", String.valueOf(date.getMonthValue()));			
	}
	

	
	public String executeCall() {
		String response = wTarget.request().get().readEntity(String.class);
		return response;
	}

}
