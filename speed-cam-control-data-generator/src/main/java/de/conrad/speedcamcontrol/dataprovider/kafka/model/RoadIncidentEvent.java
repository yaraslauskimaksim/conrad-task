package de.conrad.speedcamcontrol.dataprovider.kafka.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoadIncidentEvent {

    String objectNumber;

    String objectSpeed;

    String incidentTs;

    String eventTs;

    String uuid;
}
