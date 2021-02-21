package de.conrad.speedcamcontrol.processing.center.persistence.model;

import java.math.BigInteger;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "road_incidents")
@Data
@NoArgsConstructor
public class RoadIncidentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;

    private String objectNumber;

    private int objectSpeed;

    private Instant incidentTs;

    private Instant eventTs;

    private boolean viewed;

    private boolean approved;

    private BigInteger penalty;

    public RoadIncidentEntity(String objectNumber, int objectSpeed, Instant incidentTs, Instant eventTs, boolean viewed, boolean approved, BigInteger penalty) {
        this.objectNumber = objectNumber;
        this.objectSpeed = objectSpeed;
        this.incidentTs = incidentTs;
        this.eventTs = eventTs;
        this.viewed = viewed;
        this.approved = approved;
        this.penalty = penalty;
    }
}
