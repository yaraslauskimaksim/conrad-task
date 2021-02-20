package de.conrad.speedcamcontrol.dataprovider.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

import de.conrad.speedcamcontrol.dataprovider.configuration.properties.RoadIncidentGenerationProperties;
import de.conrad.speedcamcontrol.dataprovider.converter.RoadIncidentToRoadIncidentMapper;
import de.conrad.speedcamcontrol.dataprovider.kafka.producer.RoadIncidentPublisher;
import de.conrad.speedcamcontrol.dataprovider.model.RoadIncident;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadIncidentDataProviderService {

    private final RoadIncidentGenerationProperties properties;

    private final RoadIncidentPublisher publisher;

    private final UUIDProvider uuidProvider;

    private final InstantProvider instantProvider;

    private final RoadIncidentToRoadIncidentMapper mapper;

    public void generateRoadIncidentData(int countOfIncident) {
        for (int i = 0; i < countOfIncident; i++) {
            var now = instantProvider.provideNowInstant();

            long startMillis = now.minus(properties.getDaysSubtractFromCurrentDate(), ChronoUnit.DAYS).toEpochMilli();
            long endMillis = now.toEpochMilli();
            long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
            int objectSpeed = ThreadLocalRandom.current().nextInt(properties.getMinSpeedValue(), properties.getMaxSpeedValue());
            var roadIncident = RoadIncident.builder()
                .objectNumber(RandomStringUtils.randomAlphanumeric(properties.getObjectNumberLength()))
                .objectSpeed(objectSpeed)
                .uuid(uuidProvider.provideUuid())
                .incidentTs(Instant.ofEpochMilli(randomMillisSinceEpoch))
                .eventTs(Instant.now())
                .build();

            publisher.publish(mapper.convert(roadIncident));
        }
    }
}
