package holiday.service.health;

import com.codahale.metrics.health.HealthCheck;

public class ApiKeyHealthCheck extends HealthCheck {
	private final String apiKey;
	
	public ApiKeyHealthCheck(String apiKey) {
		this.apiKey = apiKey;
	}
	
	@Override
	protected Result check() throws Exception {
		String testApiKey = "111aaa-aaa222bbb-ccc333ddd";
		final String testKey = String.format(apiKey, testApiKey);
		if(!testKey.contains(testApiKey)) {
			return Result.unhealthy("API Key error in YAML file.");
		}
		return Result.healthy();
	}

}
