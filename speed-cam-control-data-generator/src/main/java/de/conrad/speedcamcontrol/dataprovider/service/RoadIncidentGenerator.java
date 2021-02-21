package de.conrad.speedcamcontrol.dataprovider.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

import de.conrad.speedcamcontrol.dataprovider.configuration.properties.RoadIncidentGenerationProperties;
import de.conrad.speedcamcontrol.dataprovider.model.RoadIncident;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadIncidentGenerator {

    private final RoadIncidentGenerationProperties properties;

    private final UUIDProvider uuidProvider;

    private final InstantProvider instantProvider;

    public RoadIncident generateRoadIncident() {
        var now = instantProvider.provideNowInstant();

        long startMillis = now.minus(properties.getDaysSubtractFromCurrentDate(), ChronoUnit.DAYS).toEpochMilli();
        long endMillis = now.toEpochMilli();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
        int objectSpeed = ThreadLocalRandom.current().nextInt(properties.getMinSpeedValue(), properties.getMaxSpeedValue());
        return RoadIncident.builder()
            .objectNumber(RandomStringUtils.randomAlphanumeric(properties.getObjectNumberLength()))
            .objectSpeed(objectSpeed)
            .uuid(uuidProvider.provideUuid())
            .incidentTs(Instant.ofEpochMilli(randomMillisSinceEpoch))
            .eventTs(now)
            .build();
    }
}
