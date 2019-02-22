package holiday.service.api;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Representation class, maps JSON respond from Holiday api into POJO 
 * @author Tomasz
 *
 */

public class ResponseFull {
	
	private String status;
	private List<ResponseSingle> holidays;
	
	public ResponseFull() {
		holidays = new ArrayList<ResponseSingle>();
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setHolidays(List<ResponseSingle> holidays) {
		this.holidays = holidays;
	}
	
	public List<ResponseSingle> getHolidays() {
		return holidays;
	}

}
