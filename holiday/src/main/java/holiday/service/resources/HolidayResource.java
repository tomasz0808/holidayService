package holiday.service.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import holiday.service.api.InputObject;

@Path("/searchHoliday")
@Produces(MediaType.APPLICATION_JSON)
public class HolidayResource {
	
	private final String apiKey;
	
	public HolidayResource(String apiKey) {
		this.apiKey = apiKey;	
	}
	
	@POST
	@Timed
	public InputObject executeRequest(@NotNull @Valid InputObject request) {

//		ClientRequest hResp = new ClientRequest("EN", "RU");
		return request;
	}
	
	
}
