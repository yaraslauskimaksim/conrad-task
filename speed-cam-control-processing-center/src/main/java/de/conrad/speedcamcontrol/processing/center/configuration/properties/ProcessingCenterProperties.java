package de.conrad.speedcamcontrol.processing.center.configuration.properties;

import lombok.Data;

@Data
public class ProcessingCenterProperties {

    private int allowedSpeedOnPublicRoad = Integer.MAX_VALUE;
}
