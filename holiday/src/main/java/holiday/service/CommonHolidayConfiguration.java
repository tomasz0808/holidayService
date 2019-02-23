package holiday.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

/**
 * Configuration class for CommonHoliday web service
 * 
 * @author Tomasz Scharmach
 *
 */

public class CommonHolidayConfiguration extends Configuration {

	// API key from config yaml file
	@NotEmpty
	private String apiKey;
	
	@NotEmpty
	private String apiUrl;

	@Valid
	@NotNull
	private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

	@JsonProperty
	public String getApiKey() {
		return apiKey;
	}
	
	@JsonProperty
	public String getApiUrl() {
		return apiUrl;
	}

	@JsonProperty("jerseyClient")
	public JerseyClientConfiguration getJerseyClientConfiguration() {
		return jerseyClient;
	}
}
