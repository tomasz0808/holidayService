package holiday.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import holiday.service.health.ApiKeyHealthCheck;
import holiday.service.resources.HolidayResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HolidayApplication extends Application<HolidayConfiguration> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HolidayApplication.class);
	
    public static void main(final String[] args) throws Exception {
        new HolidayApplication().run(args);
    }

    @Override
    public String getName() {
        return "holiday";
    }

    @Override
    public void initialize(final Bootstrap<HolidayConfiguration> bootstrap) {

    }

    @Override
    public void run(final HolidayConfiguration configuration, final Environment environment) {
    	LOGGER.info("Registering REST resources");
    	final HolidayResource hResource = new HolidayResource(configuration.getApiKey());
    	environment.jersey().register(hResource);
    	
    	//register health check
    	final ApiKeyHealthCheck apiHealthCheck = new ApiKeyHealthCheck(configuration.getApiKey());
    	environment.healthChecks().register("API key check", apiHealthCheck);
    	
    	
    	
    }

}
