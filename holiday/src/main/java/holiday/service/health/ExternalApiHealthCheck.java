package holiday.service.health;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;

public class ExternalApiHealthCheck extends HealthCheck {
	private WebTarget wTarget;
	private String apiKey;

	public ExternalApiHealthCheck(Client client, String targetUrl, String key) {
		wTarget = client.target(targetUrl);
		this.apiKey = key;
	}

	@Override
	protected Result check() throws Exception {
		wTarget = wTarget.queryParam("key", apiKey).queryParam("country", "US").queryParam("year", "2016")
				.queryParam("month", "01").queryParam("day", "01");

		Response response = wTarget.request().get();
		if (response.getStatus() == 200) {
			return Result.healthy("Sample request passed");
		} else {
			return Result.unhealthy("Sample request failed with code: " + response.getStatus());
		}
	}

}
