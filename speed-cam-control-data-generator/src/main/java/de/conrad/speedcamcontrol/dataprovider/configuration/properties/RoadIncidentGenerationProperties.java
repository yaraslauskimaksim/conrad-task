package de.conrad.speedcamcontrol.dataprovider.configuration.properties;

import lombok.Data;

@Data
public class RoadIncidentGenerationProperties {

    private int minSpeedValue = Integer.MIN_VALUE;

    private int maxSpeedValue = Integer.MAX_VALUE;

    private int daysSubtractFromCurrentDate = Integer.MAX_VALUE;

    private int objectNumberLength = Integer.MAX_VALUE;
}
