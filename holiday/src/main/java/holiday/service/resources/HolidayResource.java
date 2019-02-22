package holiday.service.resources;

import java.io.IOException;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import holiday.service.api.InputObject;
import holiday.service.api.ResponseFull;
import holiday.service.client.ExternalCall;

@Path("/searchHoliday")
@Produces(MediaType.APPLICATION_JSON)
public class HolidayResource {
	
	private final String apiKey;
	private ExternalCall client;
	private LocalDate date;
	
	private ObjectMapper mapper;
	
	private ResponseFull apiResponse;
	
	private boolean found = false;
	
	
	public HolidayResource(String apiKey, Client client) {
		this.apiKey = apiKey;	
		this.client = new ExternalCall(client);
		this.mapper = new ObjectMapper();
	}
	
	@POST
	@Timed
	public Response getHoliday(@NotNull @Valid InputObject input) throws JsonParseException, JsonMappingException, IOException {
		date = input.getDate();
		do {
			//get first country holidays in specified month
			client.getHolidayInMonth(apiKey, input.getName1(), date);
			
			
		}while(!found);
		//get first country holidays in specified month
		client.getHolidayInMonth(apiKey, input.getName1(), input.getDate());

		
		
		String resp = client.executeCall();
		
		
		apiResponse = mapper.readValue(resp, ResponseFull.class);
		apiResponse.getStatus();
		

		return null;
	}

	
	
}
