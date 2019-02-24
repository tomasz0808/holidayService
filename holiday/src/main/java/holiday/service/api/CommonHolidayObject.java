package holiday.service.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import holiday.service.CommonHolidayService;

/**
 * 
 * CommonHolidayObject class represents received JSON from client. Class is also
 * used to create JSON response to the client.
 * 
 * @param name1 - represents first country code
 * @param name2 - represents second country code 
 *  
 * @param date  - is the date from which to start to find next common holiday
 * 
 * @author Tomasz Scharmach
 *
 */

public class CommonHolidayObject {

	@JsonIgnore
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonHolidayService.class);

	@NotEmpty
	private String name1;

	@NotEmpty
	private String name2;

	@NotNull(message = "format yyyy-MM-dd is required")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public CommonHolidayObject(@JsonProperty("date") String date) {
		try {
			this.date = LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			LOGGER.error("Failed to parse date \n" + e.getMessage());
			e.getMessage();
		}
	}

	public String getName1() {
		return name1;
	}

	public String getName2() {
		return name2;
	}

	@JsonIgnore
	public void increaseDate(int numberOfDays) {
		this.date = this.date.plusDays(numberOfDays);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

}
