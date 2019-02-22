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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import holiday.service.api.HolidayFull;
import holiday.service.api.InputObject;
import holiday.service.client.ExternalCall;

@Path("/searchHoliday")
@Produces(MediaType.APPLICATION_JSON)
public class HolidayResource {
	
	private final String apiKey;
	private ExternalCall client;
	private LocalDate date;
	
	private ObjectMapper mapper;
	
	private HolidayFull firstCountry;
	private HolidayFull secondCountry;

	
	
	public HolidayResource(String apiKey, Client client) {
		this.apiKey = apiKey;	
		this.client = new ExternalCall(client);
		this.mapper = new ObjectMapper();
	}
	
	@POST
	@Timed
	public Response getHoliday(@NotNull @Valid InputObject input) throws JsonParseException, JsonMappingException, IOException {
		date = input.getDate();
		//get holidays in specified year for the first country code
		Response response = client.getHolidayByYear(apiKey, input.getName1(), date);
		
		//check if request to HolidayApi was successful
		if(response.getStatus() == 200) {
			firstCountry = mapper.readValue(response.readEntity(String.class), HolidayFull.class);
			firstCountry.getStatus();
		} else {
			return response;
		}
		
		// get holidays in specified year for the second country code
		response = client.getHolidayByYear(apiKey, input.getName2(), date);
		// check if request to HolidayApi was successful
		if (response.getStatus() == 200) {
			secondCountry = mapper.readValue(response.readEntity(String.class), HolidayFull.class);
			secondCountry.getStatus();
		} else {
			return response;
		}
		
		return null;
	}

	
	
}
