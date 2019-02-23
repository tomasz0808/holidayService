package holiday.service.api;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import holiday.service.CommonHolidayService;

/**
 * 
 * Class used to compare two countries holidays dates.
 * 
 * @param firstMap  - holidays map for first country
 * @param secondMap - holidays map for second country
 * @param commonMap - common holiday dates for both countries
 * @param commonKey - String key, representing next holiday date for both
 *                  countries
 * 
 * @author Tomasz Scharmach
 *
 */

public class HolidayComparator {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonHolidayService.class);

	private Map<String, List<HolidayApiSingle>> firstMap;
	private Map<String, List<HolidayApiSingle>> secondMap;
	private Map<String, List<HolidayApiSingle>> commonMap;
	private String commonKey;

	public HolidayComparator(HolidayApiMultiple firstCountry, HolidayApiMultiple secondCountry) {
		this.firstMap = firstCountry.getHolidays();
		this.secondMap = secondCountry.getHolidays();
	}

	// find common holidays for given countries
	public void findCommon(String date) {
		commonMap = firstMap;
		commonMap.keySet().retainAll(secondMap.keySet());

		// check if there is common holiday at all
		if (!commonMap.isEmpty()) {
			// make sure that common keys are sorted in descending order
			TreeMap<String, List<HolidayApiSingle>> sortedCommonMap = new TreeMap<>(commonMap);

			// find key greater ore equal to given one
			Map.Entry<String, List<HolidayApiSingle>> holiday = sortedCommonMap.ceilingEntry(date);
			// check if there is common holiday after given date
			if (holiday != null)
				commonKey = holiday.getKey();
			else {
				LOGGER.info("No common holidays after given date");
				commonKey = null;
			}
		} else {
			LOGGER.info("No common holidays for whole year");
			commonKey = null;
		}
	}

	public String getCommonKey() {
		return commonKey;
	}
}
