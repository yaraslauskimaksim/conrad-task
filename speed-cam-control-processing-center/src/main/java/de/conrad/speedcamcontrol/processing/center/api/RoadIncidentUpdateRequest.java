package de.conrad.speedcamcontrol.processing.center.api;

import java.math.BigInteger;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoadIncidentUpdateRequest {

    long id;

    boolean isApproved;

    BigInteger penalty;
}
