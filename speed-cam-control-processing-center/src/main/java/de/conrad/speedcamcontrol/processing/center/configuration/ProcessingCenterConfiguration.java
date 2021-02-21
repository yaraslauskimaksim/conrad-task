package de.conrad.speedcamcontrol.processing.center.configuration;

import de.conrad.speedcamcontrol.processing.center.configuration.properties.ProcessingCenterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessingCenterConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "processing-center-service")
    public ProcessingCenterProperties roadIncidentGenerationProperties() {
        return new ProcessingCenterProperties();
    }
}
