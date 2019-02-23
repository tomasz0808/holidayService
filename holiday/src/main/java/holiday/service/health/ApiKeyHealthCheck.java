package holiday.service.health;

import com.codahale.metrics.health.HealthCheck;

public class ApiKeyHealthCheck extends HealthCheck {
	private final String apiKey;
	
	public ApiKeyHealthCheck(String apiKey) {
		this.apiKey = apiKey;
	}
	
	@Override
	protected Result check() throws Exception {
		if(apiKey.isEmpty() || apiKey == null) {
			return Result.unhealthy("Api key is empty");
		} else {
			return Result.healthy();
		}
	}
}
