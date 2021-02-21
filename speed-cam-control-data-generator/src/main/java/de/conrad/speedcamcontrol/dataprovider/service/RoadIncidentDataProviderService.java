package de.conrad.speedcamcontrol.dataprovider.service;

import de.conrad.speedcamcontrol.dataprovider.converter.RoadIncidentToRoadIncidentEventMapper;
import de.conrad.speedcamcontrol.dataprovider.kafka.producer.RoadIncidentPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadIncidentDataProviderService {

    private final RoadIncidentGenerator roadIncidentGenerator;

    private final RoadIncidentToRoadIncidentEventMapper mapper;

    private final RoadIncidentPublisher publisher;

    public void generateRoadIncidentData(int countOfIncident) {
        for (int i = 0; i < countOfIncident; i++) {
            publisher.publish(mapper.convert(roadIncidentGenerator.generateRoadIncident()));
        }
    }
}
