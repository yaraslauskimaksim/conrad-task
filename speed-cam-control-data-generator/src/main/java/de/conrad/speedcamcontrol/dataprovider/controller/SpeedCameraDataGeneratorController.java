package de.conrad.speedcamcontrol.dataprovider.controller;

import javax.validation.Valid;

import de.conrad.speedcamcontrol.dataprovider.api.RoadIncidentGenerationRequest;
import de.conrad.speedcamcontrol.dataprovider.service.RoadIncidentDataProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("internal/v1")
@Validated
@RequiredArgsConstructor
public class SpeedCameraDataGeneratorController {

    private final RoadIncidentDataProviderService roadIncidentDataProviderService;

    @PostMapping(("/generate-road-incidents"))
    public void createRoadIncident(@RequestBody @Valid RoadIncidentGenerationRequest roadIncidentGenerationRequest) {
        log.info("createRoadIncident request: {}", roadIncidentGenerationRequest);
        roadIncidentDataProviderService.generateRoadIncidentData(roadIncidentGenerationRequest.getCountOfIncident());
    }
}
