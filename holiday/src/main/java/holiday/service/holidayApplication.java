package holiday.service;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class holidayApplication extends Application<holidayConfiguration> {

    public static void main(final String[] args) throws Exception {
        new holidayApplication().run(args);
    }

    @Override
    public String getName() {
        return "holiday";
    }

    @Override
    public void initialize(final Bootstrap<holidayConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final holidayConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
