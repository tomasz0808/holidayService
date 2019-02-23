package holiday.service.api;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Representation class, maps incoming client JSON request into POJO 
 * Used as a serialization class for Api response
 * @author Tomasz
 *
 */

public class ApiHolidayObject {
	
	@NotEmpty
	@Length(max = 6)
	private String name1;
	
	@NotEmpty
	@Length(max = 6)
	private String name2;
	
	@NotNull(message = "format yyyy-MM-dd is required")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public ApiHolidayObject(@JsonProperty("date") String date) {
		try {
			this.date = LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			e.getMessage();
		}
	}
	
	public String getName1() {
		return name1;
	}

	
	public String getName2() {
		return name2;
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
