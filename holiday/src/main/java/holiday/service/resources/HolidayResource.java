package holiday.service.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import holiday.service.core.HolidayResponse;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class HolidayResource {
	private final String apiKey;
	
	public HolidayResource(String apiKey) {
		this.apiKey = apiKey;
		System.out.println(this.apiKey);
	
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed
	public HolidayResponse executeRequest() {
		HolidayResponse hResp = new HolidayResponse("EN", "RU");
		return hResp;
		
	}
	
	
}
