package holiday.service.core;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HolidayResponse {
	
	@Length(max = 6)
	private String firstCode;
	
	@Length(max = 6)
	private String secondCode;
	
	
	public HolidayResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public HolidayResponse(String firstCode, String secondCode) {
		this.firstCode = firstCode;
		this.secondCode = secondCode;
	}
	
	@JsonProperty
	public String getFirstCode() {
		return firstCode;

	}

	@JsonProperty
	public String getSecondCode() {
		return secondCode;

	}

	
	
	
	
}
