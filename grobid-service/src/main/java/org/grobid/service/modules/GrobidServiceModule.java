package org.grobid.service.modules;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Binder;
import com.google.inject.Provides;
import com.hubspot.dropwizard.guicier.DropwizardAwareModule;
import org.grobid.service.GrobidRestService;
import org.grobid.service.GrobidServiceConfiguration;
import org.grobid.service.process.GrobidRestProcessAdmin;
import org.grobid.service.resources.HealthResource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class GrobidServiceModule extends DropwizardAwareModule<GrobidServiceConfiguration> {


    @Override
    public void configure(Binder binder) {
        binder.bind(HealthResource.class);
        binder.bind(GrobidRestService.class);
        binder.bind(GrobidRestProcessAdmin.class);
    }

    @Provides
    protected ObjectMapper getObjectMapper() {
        return getEnvironment().getObjectMapper();
    }

    @Provides
    protected MetricRegistry provideMetricRegistry() {
        return getMetricRegistry();
    }

    //for unit tests
    protected MetricRegistry getMetricRegistry() {
        return getEnvironment().metrics();
    }

    @Provides
    Client provideClient() {
        return ClientBuilder.newClient();
    }

}
