package holiday.service;

import javax.ws.rs.client.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import holiday.service.health.ApiKeyHealthCheck;
import holiday.service.resources.HolidaySearchResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
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
    	//Jersey client to make external calls
    	final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
//    	environment.jersey().register(new ExternalCall(client));
    	
    	//register resources
    	LOGGER.info("Registering REST resources");
    	final HolidaySearchResource hResource = new HolidaySearchResource(configuration.getApiKey(), client);
    	environment.jersey().register(hResource);
    	
    	//register health check
    	LOGGER.info("Registering heath check");
    	final ApiKeyHealthCheck apiHealthCheck = new ApiKeyHealthCheck(configuration.getApiKey());
    	environment.healthChecks().register("API key check", apiHealthCheck);
    	
    	
    	
    	environment.jersey().register(new JsonProcessingExceptionMapper(true));
    }

}
