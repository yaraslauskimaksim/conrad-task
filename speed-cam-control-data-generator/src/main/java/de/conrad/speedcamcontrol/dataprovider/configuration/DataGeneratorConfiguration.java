package de.conrad.speedcamcontrol.dataprovider.configuration;

import de.conrad.speedcamcontrol.dataprovider.configuration.properties.RoadIncidentGenerationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataGeneratorConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "data-generation-service")
    public RoadIncidentGenerationProperties roadIncidentGenerationProperties() {
        return new RoadIncidentGenerationProperties();
    }
}
