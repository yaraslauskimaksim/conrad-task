package de.conrad.speedcamcontrol.processing.center.converter;

import de.conrad.speedcamcontrol.processing.center.model.RoadIncident;
import de.conrad.speedcamcontrol.processing.center.persistence.model.RoadIncidentEntity;
import org.springframework.stereotype.Service;

@Service
public class RoadIncidentToRoadIncidentPersistenceMapper implements Mapper<RoadIncident, RoadIncidentEntity> {

    @Override
    public RoadIncidentEntity convert(RoadIncident source) {
        return new RoadIncidentEntity(
            source.getObjectNumber(),
            source.getObjectSpeed(),
            source.getIncidentTs(),
            source.getEventTs(),
            source.isApproved(),
            source.isViewed(),
            source.getPenalty()
        );
    }
}
