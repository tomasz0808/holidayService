package holiday.service.client;

import java.time.LocalDate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class ExternalCall {

	private static final String RESOURCE_URL = "https://holidayapi.com/v1/holidays";
	private WebTarget wTarget;

	public ExternalCall(Client client) {
		this.wTarget = client.target(RESOURCE_URL);
	}

	public Response getHolidayByYear(String apiKey, String countryCode, LocalDate date) {
		wTarget = wTarget.queryParam("key", apiKey).queryParam("country", countryCode).queryParam("year",
				String.valueOf(date.getYear()));

		return wTarget.request().get();
	}

	public String executeCall() {
		String response = wTarget.request().get().readEntity(String.class);
		return response;
	}

}
