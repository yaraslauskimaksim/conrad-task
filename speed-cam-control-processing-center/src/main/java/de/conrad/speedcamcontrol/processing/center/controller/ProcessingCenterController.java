package de.conrad.speedcamcontrol.processing.center.controller;

import java.util.List;
import java.util.stream.Collectors;

import de.conrad.speedcamcontrol.processing.center.api.RoadIncidentApi;
import de.conrad.speedcamcontrol.processing.center.api.RoadIncidentUpdateRequest;
import de.conrad.speedcamcontrol.processing.center.converter.RoadIncidentToRoadIncidentApiMapper;
import de.conrad.speedcamcontrol.processing.center.service.RoadIncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("public/v1/incidents")
@Validated
@RequiredArgsConstructor
public class ProcessingCenterController {

    private final RoadIncidentService roadIncidentService;

    private final RoadIncidentToRoadIncidentApiMapper roadIncidentToRoadIncidentApiMapper;

    @GetMapping
    public List<RoadIncidentApi> getAllIncidents() {
        log.info("getAllIncidents");

        return roadIncidentService.getAllIncidents().stream()
            .map(roadIncidentToRoadIncidentApiMapper::convert)
            .collect(Collectors.toList());
    }

    @GetMapping("/unviewed")
    public List<RoadIncidentApi> getAllUnviewedIncidents() {
        log.info("getAllUnviewedIncidents");

        return roadIncidentService.getAllUnviewedIncidents().stream()
            .map(roadIncidentToRoadIncidentApiMapper::convert)
            .collect(Collectors.toList());
    }

    @GetMapping("/")
    public List<RoadIncidentApi> getAllIncidentsByObjectNumber(@RequestParam String objectNumber) {
        log.info("getAllIncidentsByObjectNumber objectNumber:{}", objectNumber);

        return roadIncidentService.getAllIncidentsByObjectNumber(objectNumber).stream()
            .map(roadIncidentToRoadIncidentApiMapper::convert)
            .collect(Collectors.toList());
    }

    @PutMapping
    public void createRoadIncident(@RequestBody RoadIncidentUpdateRequest roadIncidentUpdateRequest) {
        log.info("roadIncidentGenerationRequest request: {}", roadIncidentUpdateRequest);
        roadIncidentService.updateRadIncident(
            roadIncidentUpdateRequest.getId(),
            roadIncidentUpdateRequest.isApproved(),
            roadIncidentUpdateRequest.getPenalty())
        ;
    }
}
