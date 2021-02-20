package de.conrad.speedcamcontrol.dataprovider.configuration.properties;

import lombok.Data;

@Data
public class RoadIncidentGenerationProperties {

    private int minSpeedValue = 1;

    private int maxSpeedValue = 10;

    private int daysSubtractFromCurrentDate = 10;

    private int objectNumberLength = 10;
}
