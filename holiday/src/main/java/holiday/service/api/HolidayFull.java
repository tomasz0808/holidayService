package holiday.service.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Representation class, maps JSON respond from Holiday api into POJO 
 * @author Tomasz
 *
 */

public class HolidayFull {
	private long status;
	private Map<String, List<HolidaySingle>> holidays;

	@JsonProperty("status")
	public long getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(long value) {
		this.status = value;
	}

	@JsonProperty("holidays")
	public Map<String, List<HolidaySingle>> getHolidays() {
		return holidays;
	}

	@JsonProperty("holidays")
	public void setHolidays(Map<String, List<HolidaySingle>> value) {
		this.holidays = value;
	}
}
