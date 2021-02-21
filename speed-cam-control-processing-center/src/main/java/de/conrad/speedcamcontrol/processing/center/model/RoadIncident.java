package de.conrad.speedcamcontrol.processing.center.model;

import java.math.BigInteger;
import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoadIncident {

    private long id;

    private String objectNumber;

    private int objectSpeed;

    private Instant incidentTs;

    private Instant eventTs;

    private boolean isViewed;

    private boolean isApproved;

    private BigInteger penalty;
}
