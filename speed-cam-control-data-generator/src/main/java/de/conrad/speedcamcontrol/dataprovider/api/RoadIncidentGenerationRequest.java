package de.conrad.speedcamcontrol.dataprovider.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoadIncidentGenerationRequest {

    @Min(1)
    @Max(Integer.MAX_VALUE)
    private int countOfIncident;
}
