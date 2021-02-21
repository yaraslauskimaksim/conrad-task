package de.conrad.speedcamcontrol.processing.center.converter;

import de.conrad.speedcamcontrol.processing.center.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadIncidentToRoadIncidentApiMapperTest {

    @Test
    void shouldConvertRoadIncidentToRoadIncidentApiIncident() {
        final var converter = new RoadIncidentToRoadIncidentApiMapper();

        assertEquals(TestDataProvider.roadIncidentApi(), converter.convert(TestDataProvider.roadIncident()));
    }
}