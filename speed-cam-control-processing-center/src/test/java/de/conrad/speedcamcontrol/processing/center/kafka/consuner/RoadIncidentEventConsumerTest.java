package de.conrad.speedcamcontrol.processing.center.kafka.consuner;

import de.conrad.speedcamcontrol.processing.center.converter.RoadIncidentEventToRoadIncidentMapper;
import de.conrad.speedcamcontrol.processing.center.service.RoadIncidentService;
import de.conrad.speedcamcontrol.processing.center.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoadIncidentEventConsumerTest {

    @Mock
    private RoadIncidentService roadIncidentService;

    @Mock
    private RoadIncidentEventToRoadIncidentMapper mapper;

    @InjectMocks
    private RoadIncidentEventConsumer roadIncidentEventConsumer;

    @Test
    void shouldConsumeRoadIncidentEvent() {
        final var roadIncidentEvent = TestDataProvider.roadIncidentEvent();
        final var expectedRoadIncident = TestDataProvider.newRoadIncident();

        when(mapper.convert(roadIncidentEvent)).thenReturn(expectedRoadIncident);

        roadIncidentEventConsumer.accept(roadIncidentEvent);

        verify(roadIncidentService).processRoadIncident(expectedRoadIncident);
    }
}