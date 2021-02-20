package de.conrad.speedcamcontrol.processing.center.converter;

import java.time.Instant;

import de.conrad.speedcamcontrol.processing.center.kafka.model.RoadIncidentEvent;
import de.conrad.speedcamcontrol.processing.center.model.RoadIncident;
import de.conrad.speedcamcontrol.processing.center.service.UUIDProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadIncidentEventToRoadIncidentMapper implements Mapper<RoadIncidentEvent, RoadIncident> {

    private final UUIDProvider uuidProvider;

    @Override
    public RoadIncident convert(RoadIncidentEvent source) {
        return RoadIncident.builder()
            .objectNumber(source.getObjectNumber())
            .objectSpeed(Integer.parseInt(source.getObjectSpeed()))
            .incidentTs(Instant.parse(source.getIncidentTs()))
            .eventTs(Instant.parse(source.getEventTs()))
            .uuid(uuidProvider.getUuidFromString(source.getUuid()))
            .build();
    }
}
