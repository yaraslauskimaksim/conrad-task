package de.conrad.speedcamcontrol.dataprovider.testdata;

import java.time.Instant;
import java.util.UUID;

import de.conrad.speedcamcontrol.dataprovider.kafka.model.RoadIncidentEvent;
import de.conrad.speedcamcontrol.dataprovider.model.RoadIncident;

public class TestDataProvider {

    public static final int SUBTRACT_DAYS = 1;

    public static final int OBJECT_NUMBER_LENGTH = 5;

    public static final int MIN_SPEED = 1;

    public static final int MAX_SPEED = 10;

    public static final String OBJECT_NUMBER = "1";

    public static final int OBJECT_SPEED = 5;

    public static final Instant INCIDENT_TS = Instant.now();

    public static final Instant EVENT_TS = Instant.now();

    public static final UUID UUID = java.util.UUID.randomUUID();


    public static RoadIncidentEvent roadIncidentEvent() {
        return RoadIncidentEvent.builder()
            .objectNumber(OBJECT_NUMBER)
            .objectSpeed(String.valueOf(OBJECT_SPEED))
            .incidentTs(INCIDENT_TS.toString())
            .eventTs(EVENT_TS.toString())
            .uuid(UUID.toString())
            .build();
    }

    public static RoadIncident roadIncident() {
        return RoadIncident.builder()
            .objectNumber(OBJECT_NUMBER)
            .objectSpeed(OBJECT_SPEED)
            .incidentTs(INCIDENT_TS)
            .eventTs(EVENT_TS)
            .uuid(UUID)
            .build();
    }
}
