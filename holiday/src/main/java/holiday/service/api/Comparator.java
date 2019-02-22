package holiday.service.api;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Comparator {
	
	private HolidayFull firstCountry;
	private HolidayFull secondCountry;
	
	private Map<String, List<HolidaySingle>> firstMap;
	private Map<String, List<HolidaySingle>> secondMap;
	private Map<String, List<HolidaySingle>> buff;
	private String commonKey;
	
	
	
	public Comparator(HolidayFull firstCountry, HolidayFull secondCountry) {
		this.firstMap = firstCountry.getHolidays();
		this.secondMap = secondCountry.getHolidays();
	}
	
	//find common holidays for given countries
	public void findCommon(String date) {
		buff = firstMap;
		buff.keySet().retainAll(secondMap.keySet());
		
		//check if there is common holiday
		if(!buff.isEmpty()) {
			//make sure that common keys are sorted in descending order
			TreeMap<String, List<HolidaySingle>> sortedCommonMap = new TreeMap<>(buff);	
			//get the next holiday date to given one
			Map.Entry<String, List<HolidaySingle>> holiday = sortedCommonMap.ceilingEntry(date);
			commonKey = holiday.getKey();
			
		} else {
			commonKey = null;
		}
	}
	
	public String getCommonKey() {
		return commonKey;
	}
	
	

}
