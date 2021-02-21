package de.conrad.speedcamcontrol.dataprovider.service;

import de.conrad.speedcamcontrol.dataprovider.converter.RoadIncidentToRoadIncidentEventMapper;
import de.conrad.speedcamcontrol.dataprovider.kafka.producer.RoadIncidentPublisher;
import de.conrad.speedcamcontrol.dataprovider.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoadIncidentDataProviderServiceTest {

    @Mock
    private RoadIncidentGenerator roadIncidentGenerator;

    @Mock
    private RoadIncidentPublisher publisher;

    @Mock
    private RoadIncidentToRoadIncidentEventMapper mapper;

    @InjectMocks
    private RoadIncidentDataProviderService roadIncidentDataProviderService;

    @Test
    void shouldGenerateRoadIncidentData() {

        final var roadIncident = TestDataProvider.roadIncident();
        final var expectedEvent = TestDataProvider.roadIncidentEvent();

        when(roadIncidentGenerator.generateRoadIncident()).thenReturn(roadIncident);
        when(mapper.convert(any())).thenReturn(expectedEvent);

        roadIncidentDataProviderService.generateRoadIncidentData(1);

        verify(publisher).publish(expectedEvent);
    }
}