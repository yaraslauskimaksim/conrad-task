package de.conrad.speedcamcontrol.dataprovider.service;

import java.time.temporal.ChronoUnit;

import de.conrad.speedcamcontrol.dataprovider.configuration.properties.RoadIncidentGenerationProperties;
import de.conrad.speedcamcontrol.dataprovider.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoadIncidentGeneratorTest {

    @Mock
    private RoadIncidentGenerationProperties properties;

    @Mock
    private UUIDProvider uuidProvider;

    @Mock
    private InstantProvider instantProvider;

    @InjectMocks
    private RoadIncidentGenerator roadIncidentGenerator;

    @Test
    void shouldGenerateRoadIncident() {

        when(properties.getMaxSpeedValue()).thenReturn(TestDataProvider.MAX_SPEED);
        when(properties.getMinSpeedValue()).thenReturn(TestDataProvider.MIN_SPEED);
        when(properties.getDaysSubtractFromCurrentDate()).thenReturn(TestDataProvider.SUBTRACT_DAYS);
        when(properties.getObjectNumberLength()).thenReturn(TestDataProvider.OBJECT_NUMBER_LENGTH);
        when(uuidProvider.provideUuid()).thenReturn(TestDataProvider.UUID);
        when(instantProvider.provideNowInstant()).thenReturn(TestDataProvider.EVENT_TS);

        final var roadIncident = roadIncidentGenerator.generateRoadIncident();

        assertThat(roadIncident.getUuid()).isEqualTo(TestDataProvider.UUID);
        assertThat(roadIncident.getObjectSpeed()).isBetween(TestDataProvider.MIN_SPEED, TestDataProvider.MAX_SPEED);
        assertThat(roadIncident.getObjectNumber()).hasSizeLessThanOrEqualTo(TestDataProvider.OBJECT_NUMBER_LENGTH);
        assertThat(roadIncident.getEventTs()).isEqualTo(TestDataProvider.EVENT_TS);
        assertThat(roadIncident.getIncidentTs()).isBetween(TestDataProvider.EVENT_TS.minus(TestDataProvider.SUBTRACT_DAYS, ChronoUnit.DAYS), TestDataProvider.EVENT_TS);
    }
}