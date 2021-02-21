package de.conrad.speedcamcontrol.dataprovider.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoadIncident {

    String objectNumber;

    int objectSpeed;

    Instant incidentTs;

    Instant eventTs;

    UUID uuid;
}
