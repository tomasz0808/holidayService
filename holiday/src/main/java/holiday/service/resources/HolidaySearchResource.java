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

import holiday.service.api.Comparator;
import holiday.service.api.HolidayApiMultiple;
import holiday.service.api.ApiHolidayObject;
import holiday.service.client.ExternalCall;

@Path("/searchHoliday")
@Produces(MediaType.APPLICATION_JSON)
public class HolidaySearchResource {
	
	private final String apiKey;
	private ExternalCall client;
	private LocalDate date;
	
	private ObjectMapper mapper;
	
	private HolidayApiMultiple firstCountry;
	private HolidayApiMultiple secondCountry;
	
	private ApiHolidayObject responseObj; 
	
	private Comparator hComparator; 

	
	
	public HolidaySearchResource(String apiKey, Client client) {
		this.apiKey = apiKey;	
		this.client = new ExternalCall(client);
		this.mapper = new ObjectMapper();
	}
	
	@POST
	@Timed
	public Response getHoliday(@NotNull @Valid ApiHolidayObject input) throws JsonParseException, JsonMappingException, IOException {
		//find common holiday AFTER given date
		input.increaseDate(1);
		date =  input.getDate();
		//get holidays in specified year for the first country code
		Response response = client.getHolidayByYear(apiKey, input.getName1(), date);
		
		//check if request to HolidayApi was successful
		if(response.getStatus() == 200) {
			firstCountry = mapper.readValue(response.readEntity(String.class), HolidayApiMultiple.class);
			firstCountry.getStatus();
		} else {
			return response;
		}
		
		// get holidays in specified year for the second country code
		response = client.getHolidayByYear(apiKey, input.getName2(), date);
		// check if request to HolidayApi was successful
		if (response.getStatus() == 200) {
			secondCountry = mapper.readValue(response.readEntity(String.class), HolidayApiMultiple.class);
			secondCountry.getStatus();
		} else {
			return response;
		}
		
		hComparator = new Comparator(firstCountry, secondCountry);
		hComparator.findCommon(date.toString());
		
		String commonDate = hComparator.getCommonKey();
		
		if(commonDate != null) {
			//set response object
			responseObj = new ApiHolidayObject(commonDate);
			responseObj.setName1(firstCountry.getHolidaysNames(commonDate));
			responseObj.setName2(secondCountry.getHolidaysNames(commonDate));
		} else {
			responseObj = new ApiHolidayObject(date.toString());
			responseObj.setName1("No common holidays found after give date in year "+date.getYear());	
			responseObj.setName2("Please change input date and try again");	
		}
		return Response.ok().entity(responseObj).build();
	}

	
	
}
