package holiday.service.api;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.dropwizard.jackson.Jackson;

public class CommonHolidayObjectTest {
	
	private static ObjectMapper MAPPER = Jackson.newObjectMapper();
	
	@Test
	public void serializeToJSON() throws Exception{
		//serialize object to JSON response
		final CommonHolidayObject testObject = new CommonHolidayObject("2018-01-01");
		testObject.setName1("Nowy Rok");
		testObject.setName2("New Year's Day");
		
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/TestSerialization.json"), CommonHolidayObject.class));
		
		assertThat(MAPPER.writeValueAsString(testObject)).isEqualTo(expected);	
	}	
	
	@Test
	public void deserializesFromJSON() throws Exception {
		//deserialize ISON containing 2 country codes and date
		final CommonHolidayObject testObject = new CommonHolidayObject("2017-12-31");
		testObject.setName1("PL");
		testObject.setName2("US");

		final CommonHolidayObject expected = MAPPER.readValue(fixture("fixtures/TestDeserialization.json"), CommonHolidayObject.class);
		
		//check values independently
		assertThat(testObject.getName1()).isEqualTo(expected.getName1());
		assertThat(testObject.getName2()).isEqualTo(expected.getName2());
		assertThat(testObject.getDate().toString()).isEqualTo(expected.getDate().toString());
	}
}
