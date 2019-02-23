package holiday.service;

import javax.ws.rs.client.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import holiday.service.health.ApiKeyHealthCheck;
import holiday.service.health.ExternalApiHealthCheck;
import holiday.service.resources.CommonHolidayResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Common Holiday service class
 * 
 * @author Tomasz Scharmach
 *
 */

public class CommonHolidayService extends Application<CommonHolidayConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonHolidayService.class);

	public static void main(final String[] args) throws Exception {
		new CommonHolidayService().run(args);
	}

	@Override
	public String getName() {
		return "Holiday";
	}

	@Override
	public void initialize(final Bootstrap<CommonHolidayConfiguration> bootstrap) {
	}

	@Override
	public void run(final CommonHolidayConfiguration conf, final Environment env) {
		// Jersey client to make external calls
		final Client client = new JerseyClientBuilder(env).using(conf.getJerseyClientConfiguration())
				.build(getName());

		// register resources
		final CommonHolidayResource hResource = new CommonHolidayResource(conf.getApiKey(), conf.getApiUrl(), client);
		env.jersey().register(hResource);
		LOGGER.info("Registering REST resources");

		// register health checks
		final ApiKeyHealthCheck apiHealthCheck = new ApiKeyHealthCheck(conf.getApiKey());
		env.healthChecks().register("API key no null", apiHealthCheck);
		LOGGER.info("Registering heath checks");
		
		final ExternalApiHealthCheck externalHealthCheck = new ExternalApiHealthCheck(client, conf.getApiUrl(), conf.getApiKey());
		env.healthChecks().register("External request passed", externalHealthCheck);

		env.jersey().register(new JsonProcessingExceptionMapper(true));
		LOGGER.info("Registering JSON Mapper Exceptions");
	}

}
