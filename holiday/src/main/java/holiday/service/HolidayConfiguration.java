package holiday.service;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class HolidayConfiguration extends Configuration {
    
	//read API key from config yaml file
	@NotEmpty
    private String apiKey;
    
    @JsonProperty
    public String getApiKey() {
    	return apiKey;
    }
//    
//    //method to change API key
//    @JsonProperty
//    public void changeApiKey(String newKey) {
//    	this.apiKey = newKey;
//    }
    
    
}
