package holiday.service.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * Represents holidays for specified country
 * @author Tomasz
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseSingle {
	private String name;
	private String date;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
