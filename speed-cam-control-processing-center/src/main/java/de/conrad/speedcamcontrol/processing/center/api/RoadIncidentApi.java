package de.conrad.speedcamcontrol.processing.center.api;

import java.math.BigInteger;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoadIncidentApi {

    long id;

    String objectNumber;

    int objectSpeed;

    String incidentTs;

    String eventTs;

    boolean isViewed;

    boolean isApproved;

    BigInteger penalty;
}
