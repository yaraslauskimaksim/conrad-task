package de.conrad.speedcamcontrol.processing.center.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import de.conrad.speedcamcontrol.processing.center.configuration.properties.ProcessingCenterProperties;
import de.conrad.speedcamcontrol.processing.center.converter.RoadIncidentPersistenceToRoadIncidentMapper;
import de.conrad.speedcamcontrol.processing.center.converter.RoadIncidentToRoadIncidentPersistenceMapper;
import de.conrad.speedcamcontrol.processing.center.model.RoadIncident;
import de.conrad.speedcamcontrol.processing.center.persistence.RoadIncidentRepository;
import de.conrad.speedcamcontrol.processing.center.persistence.model.RoadIncidentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoadIncidentService {

    private final ProcessingCenterProperties processingCenterProperties;

    private final RoadIncidentRepository roadIncidentRepository;

    private final RoadIncidentToRoadIncidentPersistenceMapper roadIncidentToRoadIncidentPersistenceMapper;

    private final RoadIncidentPersistenceToRoadIncidentMapper roadIncidentPersistenceToRoadIncidentMapper;

    public void processRoadIncident(RoadIncident roadIncident) {
        log.info("converted roadIncident: {}", roadIncident);

        if (roadIncident.getObjectSpeed() > processingCenterProperties.getAllowedSpeedOnPublicRoad()) {
            roadIncidentRepository.save(roadIncidentToRoadIncidentPersistenceMapper.convert(roadIncident));
        }
    }

    public void updateRadIncident(long incidentId, boolean isApproved, BigInteger penalty) {
        final Optional<RoadIncidentEntity> roadIncident = roadIncidentRepository.findById(incidentId);

        roadIncident.ifPresent(roadIncidentEntity -> {
            roadIncidentEntity.setApproved(isApproved);
            if (isApproved) {
                roadIncidentEntity.setPenalty(penalty);
            }
            roadIncidentEntity.setViewed(true);

            roadIncidentRepository.save(roadIncidentEntity);
        });
    }

    public List<RoadIncident> getAllIncidents() {
        final Iterable<RoadIncidentEntity> roadIncidents = roadIncidentRepository.findAll();

        return StreamSupport.stream(roadIncidents.spliterator(), false)
            .map(roadIncidentPersistenceToRoadIncidentMapper::convert)
            .collect(Collectors.toList());
    }

    public List<RoadIncident> getAllIncidentsByObjectNumber(String objectNumber) {
        final List<RoadIncidentEntity> roadIncidents = roadIncidentRepository.findAllByObjectNumberOrderByIncidentTsDesc(objectNumber);

        return roadIncidents.stream()
            .map(roadIncidentPersistenceToRoadIncidentMapper::convert)
            .collect(Collectors.toList());
    }

    public List<RoadIncident> getAllUnviewedIncidents() {
        final List<RoadIncidentEntity> roadIncidents = roadIncidentRepository.findAllByViewedFalseOrderByIncidentTsDesc();

        return roadIncidents.stream()
            .map(roadIncidentPersistenceToRoadIncidentMapper::convert)
            .collect(Collectors.toList());
    }
}
