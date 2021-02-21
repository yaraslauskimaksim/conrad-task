package de.conrad.speedcamcontrol.processing.center.converter;

import de.conrad.speedcamcontrol.processing.center.api.RoadIncidentApi;
import de.conrad.speedcamcontrol.processing.center.model.RoadIncident;
import org.springframework.stereotype.Service;

@Service
public class RoadIncidentToRoadIncidentApiMapper implements  Mapper<RoadIncident, RoadIncidentApi> {

    @Override
    public RoadIncidentApi convert(RoadIncident source) {
        return RoadIncidentApi.builder()
            .id(source.getId())
            .objectNumber(source.getObjectNumber())
            .objectSpeed(source.getObjectSpeed())
            .incidentTs(source.getIncidentTs().toString())
            .eventTs(source.getEventTs().toString())
            .isApproved(source.isApproved())
            .isViewed(source.isViewed())
            .penalty(source.getPenalty())
            .build();
    }
}
