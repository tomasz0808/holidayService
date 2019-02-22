package holiday.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;


public class HolidayConfiguration extends Configuration {
    
	//read API key from config yaml file
	@NotEmpty
    private String apiKey;
    
	@Valid
	@NotNull
	private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();
	
    @JsonProperty
    public String getApiKey() {
    	return apiKey;
    }
    
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }
    
//    
//    //method to change API key
//    @JsonProperty
//    public void changeApiKey(String newKey) {
//    	this.apiKey = newKey;
//    }
    
    
}
