package holiday.service.client;

import java.time.LocalDate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import holiday.service.CommonHolidayService;

/**
 * Class is used to send requests to Holiday Api. It requests holidays list for
 * a specified year and country.
 * 
 * @author Tomasz Scharmach
 *
 */

public class ExternalCall {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonHolidayService.class);

	private WebTarget wTarget;

	public ExternalCall(Client client, String apiUrl) {
		this.wTarget = client.target(apiUrl);
	}

	public Response getHolidayByYear(String apiKey, String countryCode, LocalDate date) {
		wTarget = wTarget.queryParam("key", apiKey).queryParam("country", countryCode).queryParam("year",
				String.valueOf(date.getYear()));
		LOGGER.info("External call prepared");
		return wTarget.request().get();
	}
}
