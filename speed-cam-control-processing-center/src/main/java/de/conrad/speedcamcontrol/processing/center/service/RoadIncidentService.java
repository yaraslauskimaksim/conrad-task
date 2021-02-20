package de.conrad.speedcamcontrol.processing.center.service;

import de.conrad.speedcamcontrol.processing.center.configuration.properties.ProcessingCenterProperties;
import de.conrad.speedcamcontrol.processing.center.model.RoadIncident;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoadIncidentService {

    private final ProcessingCenterProperties processingCenterProperties;

    public void processRoadIncident(RoadIncident roadIncident) {
        log.info("converted roadIncident: {}", roadIncident);
    }
}
