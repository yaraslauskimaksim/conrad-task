package de.conrad.speedcamcontrol.dataprovider.converter;

import de.conrad.speedcamcontrol.dataprovider.kafka.model.RoadIncidentEvent;
import de.conrad.speedcamcontrol.dataprovider.model.RoadIncident;
import org.springframework.stereotype.Service;

@Service
public class RoadIncidentToRoadIncidentMapper implements Mapper<RoadIncident, RoadIncidentEvent> {

    @Override
    public RoadIncidentEvent convert(RoadIncident source) {
        return RoadIncidentEvent.builder()
            .objectNumber(source.getObjectNumber())
            .objectSpeed(String.valueOf(source.getObjectSpeed()))
            .incidentTs(source.getIncidentTs().toString())
            .eventTs(source.getEventTs().toString())
            .uuid(source.getUuid().toString())
            .build();
    }
}
