package de.conrad.speedcamcontrol.processing.center.testdata;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

import de.conrad.speedcamcontrol.processing.center.api.RoadIncidentApi;
import de.conrad.speedcamcontrol.processing.center.api.RoadIncidentUpdateRequest;
import de.conrad.speedcamcontrol.processing.center.kafka.model.RoadIncidentEvent;
import de.conrad.speedcamcontrol.processing.center.model.RoadIncident;
import de.conrad.speedcamcontrol.processing.center.persistence.model.RoadIncidentEntity;

public class TestDataProvider {

    public static final String OBJECT_NUMBER = "1";

    public static final int OBJECT_SPEED = 5;

    public static final Instant INCIDENT_TS = Instant.now();

    public static final Instant EVENT_TS = Instant.now();

    public static final UUID UUID = java.util.UUID.randomUUID();

    public static final boolean IS_VIEWED = false;

    public static final boolean IS_APPROVED = false;

    public static final BigInteger PENALTY = BigInteger.TEN;

    public static final long NEW_INCIDENT_ID = 0;

    public static final long INCIDENT_ID = 1;

    public static RoadIncidentEvent roadIncidentEvent() {
        return RoadIncidentEvent.builder()
            .objectNumber(OBJECT_NUMBER)
            .objectSpeed(String.valueOf(OBJECT_SPEED))
            .incidentTs(INCIDENT_TS.toString())
            .eventTs(EVENT_TS.toString())
            .uuid(UUID.toString())
            .build();
    }

    public static RoadIncidentUpdateRequest roadIncidentUpdateRequest() {
        return RoadIncidentUpdateRequest.builder()
            .id(INCIDENT_ID)
            .isApproved(true)
            .penalty(PENALTY)
            .build();
    }

    public static RoadIncidentApi roadIncidentApi(){
        return RoadIncidentApi.builder()
            .id(INCIDENT_ID)
            .objectNumber(OBJECT_NUMBER)
            .objectSpeed(OBJECT_SPEED)
            .incidentTs(INCIDENT_TS.toString())
            .eventTs(EVENT_TS.toString())
            .penalty(PENALTY)
            .isApproved(IS_APPROVED)
            .isViewed(IS_VIEWED)
            .build();
    }

    public static RoadIncident roadIncident() {
        return RoadIncident.builder()
            .id(INCIDENT_ID)
            .objectNumber(OBJECT_NUMBER)
            .objectSpeed(OBJECT_SPEED)
            .incidentTs(INCIDENT_TS)
            .eventTs(EVENT_TS)
            .penalty(PENALTY)
            .isApproved(IS_APPROVED)
            .isViewed(IS_VIEWED)
            .build();
    }

    public static RoadIncident newRoadIncident() {
        return RoadIncident.builder()
            .id(NEW_INCIDENT_ID)
            .objectNumber(OBJECT_NUMBER)
            .objectSpeed(OBJECT_SPEED)
            .incidentTs(INCIDENT_TS)
            .eventTs(EVENT_TS)
            .isApproved(IS_APPROVED)
            .isViewed(IS_VIEWED)
            .build();
    }

    public static RoadIncidentEntity newRoadIncidentEntity() {
        return new RoadIncidentEntity(
            OBJECT_NUMBER,
            OBJECT_SPEED,
            INCIDENT_TS,
            EVENT_TS,
            IS_VIEWED,
            IS_APPROVED,
            PENALTY);
    }

    public static RoadIncidentEntity roadIncidentEntity() {
        final var roadIncidentEntity = newRoadIncidentEntity();
        roadIncidentEntity.setId(INCIDENT_ID);
        return roadIncidentEntity;
    }
}
