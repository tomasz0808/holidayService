package holiday.service.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Class used to deserialize Holiday Api JSON response. This class represents
 * single holiday details for specified country and date
 * 
 * @param name          - name of the holiday
 * @param date          - date of the holiday
 * @param observed      - date of the holiday
 * @param holidayPublic - informs if this holiday is a public holiday
 * 
 * @author Tomasz Scharmach
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidayApiSingle {
	private String name;
	private String date;
	private String observed;
	private boolean holidayPublic;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String value) {
		this.name = value;
	}

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(String value) {
		this.date = value;
	}

	@JsonProperty("observed")
	public String getObserved() {
		return observed;
	}

	@JsonProperty("observed")
	public void setObserved(String value) {
		this.observed = value;
	}

	@JsonProperty("public")
	public boolean getHolidayPublic() {
		return holidayPublic;
	}

	@JsonProperty("public")
	public void setHolidayPublic(boolean value) {
		this.holidayPublic = value;
	}
}
