package de.conrad.speedcamcontrol.processing.center.converter;

import de.conrad.speedcamcontrol.processing.center.model.RoadIncident;
import de.conrad.speedcamcontrol.processing.center.persistence.model.RoadIncidentEntity;
import org.springframework.stereotype.Service;

@Service
public class RoadIncidentPersistenceToRoadIncidentMapper implements Mapper<RoadIncidentEntity, RoadIncident>{

    @Override
    public RoadIncident convert(RoadIncidentEntity source) {
        return RoadIncident.builder()
            .id(source.getId())
            .objectNumber(source.getObjectNumber())
            .objectSpeed(source.getObjectSpeed())
            .incidentTs(source.getIncidentTs())
            .eventTs(source.getEventTs())
            .isViewed(source.isViewed())
            .isApproved(source.isApproved())
            .penalty(source.getPenalty())
            .build();
    }
}
