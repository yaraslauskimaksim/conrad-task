package de.conrad.speedcamcontrol.processing.center.kafka.consuner;

import java.util.function.Consumer;

import de.conrad.speedcamcontrol.processing.center.converter.RoadIncidentEventToRoadIncidentMapper;
import de.conrad.speedcamcontrol.processing.center.kafka.model.RoadIncidentEvent;
import de.conrad.speedcamcontrol.processing.center.service.RoadIncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoadIncidentEventConsumer implements Consumer<RoadIncidentEvent> {

    private final RoadIncidentService roadIncidentService;

    private final RoadIncidentEventToRoadIncidentMapper mapper;

    @Override
    public void accept(RoadIncidentEvent roadIncidentEvent) {
        log.info("roadIncidentEvent message: {}", roadIncidentEvent);
        roadIncidentService.processRoadIncident(mapper.convert(roadIncidentEvent));
    }
}
