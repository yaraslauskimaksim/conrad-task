package de.conrad.speedcamcontrol.dataprovider.kafka.producer;


import de.conrad.speedcamcontrol.dataprovider.kafka.model.RoadIncidentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoadIncidentPublisher {

    private final StreamBridge streamBridge;

    public void publish(RoadIncidentEvent roadIncidentEvent) {
        log.info("road incident message {}.", roadIncidentEvent);
        streamBridge.send("roadIncidentOut", MessageBuilder
            .withPayload(roadIncidentEvent)
            .setHeader(KafkaHeaders.MESSAGE_KEY, roadIncidentEvent.getObjectNumber() + roadIncidentEvent.getIncidentTs())
            .build());
    }
}
