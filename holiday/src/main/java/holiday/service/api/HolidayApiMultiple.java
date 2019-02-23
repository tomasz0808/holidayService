package holiday.service.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Representation class, maps JSON respond from Holiday api containing holidays
 * (for selected country for whole year) into POJO
 * 
 * @author Tomasz
 *
 */

public class HolidayApiMultiple {
	private long status;
	private Map<String, List<HolidayApiSingle>> holidays;

	@JsonProperty("status")
	public long getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(long value) {
		this.status = value;
	}

	@JsonProperty("holidays")
	public Map<String, List<HolidayApiSingle>> getHolidays() {
		return holidays;
	}

	@JsonProperty("holidays")
	public void setHolidays(Map<String, List<HolidayApiSingle>> value) {
		this.holidays = value;
	}
	
	@JsonIgnore
	public String getHolidaysNames(String dateKey) {
		String names ="";
		List<HolidayApiSingle> holidaysInDay = holidays.get(dateKey);
		//get all holidays names for a given day, as there might be more than one
		for(HolidayApiSingle holiday : holidaysInDay) {
			names += holiday.getName()+", ";
		}
	
		//remove trailing " ," from string
		return names.substring(0, names.length()-2);	
	}
	
}
