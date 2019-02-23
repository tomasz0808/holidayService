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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import holiday.service.CommonHolidayService;
import holiday.service.api.CommonHolidayObject;
import holiday.service.api.HolidayApiMultiple;
import holiday.service.api.HolidayComparator;
import holiday.service.client.ExternalCall;

/**
 * 
 * Resource class, path - "/searchHoliday". This class consumes JSON, searches
 * for same holiday and returns JSON with response.
 * 
 * @param apiKey- Holiday Api key
 * 
 * @author tomasz
 *
 */

@Path("/searchHoliday")
@Produces(MediaType.APPLICATION_JSON)
public class CommonHolidayResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonHolidayService.class);

	private final String apiKey;
	private ExternalCall client;
	private LocalDate date;

	private ObjectMapper mapper;

	private HolidayApiMultiple firstCountry;
	private HolidayApiMultiple secondCountry;

	private CommonHolidayObject responseObj;

	private HolidayComparator hComparator;

	public CommonHolidayResource(String apiKey, Client client) {
		this.apiKey = apiKey;
		this.client = new ExternalCall(client);
		this.mapper = new ObjectMapper();
	}

	@POST
	@Timed
	public Response getHoliday(@NotNull @Valid CommonHolidayObject input)
			throws JsonParseException, JsonMappingException, IOException {
		// find common holiday AFTER given date
		input.increaseDate(1);
		date = input.getDate();
		// get holidays in specified year for the first country code
		Response response = client.getHolidayByYear(apiKey, input.getName1(), date);

		// check if request to HolidayApi was successful
		if (response.getStatus() == 200) {
			firstCountry = mapper.readValue(response.readEntity(String.class), HolidayApiMultiple.class);
			firstCountry.getStatus();
		} else {
			LOGGER.warn("Holiday Api returned code: " + response.getStatus() + "for the first country");
			return response;
		}

		// get holidays in specified year for the second country code
		response = client.getHolidayByYear(apiKey, input.getName2(), date);
		// check if request to HolidayApi was successful
		if (response.getStatus() == 200) {
			secondCountry = mapper.readValue(response.readEntity(String.class), HolidayApiMultiple.class);
			secondCountry.getStatus();
		} else {
			LOGGER.warn("Holiday Api returned code: " + response.getStatus() + "for the second country");
			return response;
		}

		hComparator = new HolidayComparator(firstCountry, secondCountry);
		hComparator.findCommon(date.toString());

		String commonDate = hComparator.getCommonKey();

		//create proper response object
		if (commonDate != null) {
			responseObj = new CommonHolidayObject(commonDate);
			responseObj.setName1(firstCountry.getHolidaysNames(commonDate));
			responseObj.setName2(secondCountry.getHolidaysNames(commonDate));
		} else {
			responseObj = new CommonHolidayObject(date.toString());
			responseObj.setName1("No common holidays found after give date in year " + date.getYear());
			responseObj.setName2("Please change input date and try again");
		}
		return Response.ok().entity(responseObj).build();
	}
}
