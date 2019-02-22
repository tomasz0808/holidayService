package holiday.service.resources;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;

import holiday.service.api.InputObject;
import holiday.service.client.ExternalCall;

@Path("/searchHoliday")
@Produces(MediaType.APPLICATION_JSON)
public class HolidayResource {
	
	private final String apiKey;
	private ExternalCall client;
	private LocalDate date;
	
	public HolidayResource(String apiKey, Client client) {
		this.apiKey = apiKey;	
		this.client = new ExternalCall(client);
	}
	
	@POST
	@Timed
	public Response executeRequest(@NotNull @Valid InputObject request) {
		this.date = request.getDate();
		createCall(request.getName1());
		Response resp = client.executeCall();
		
		

//		ClientRequest hResp = new ClientRequest("EN", "RU");
		return resp;
	}

	private void createCall(String countryCode) {
		client.setRequestParams(apiKey, countryCode, date);
		
	}
	
	
}
