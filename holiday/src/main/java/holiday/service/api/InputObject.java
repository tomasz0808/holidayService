package holiday.service.api;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Representation class, maps incoming client JSON request into POJO 
 * @author Tomasz
 *
 */

public class InputObject {
	
	@NotEmpty
	@Length(max = 6)
	private String name1;
	
	@NotEmpty
	@Length(max = 6)
	private String name2;
	
	@NotNull(message = "format yyyy-MM-dd is required")
	private LocalDate date;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		
//	public InputObject() {
//		// TODO Auto-generated constructor stub
//	}
	
	@JsonCreator
	public InputObject(@JsonProperty("name1") String firstCode, @JsonProperty("name2") String secondCode, @JsonProperty("date") String date) {
		this.name1 = firstCode;
		this.name2 = secondCode;
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

}
