package de.conrad.speedcamcontrol.processing.center.converter;

import de.conrad.speedcamcontrol.processing.center.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadIncidentEventToRoadIncidentMapperTest {

    @Test
    void shouldConvertRoadIncidentEventToRoadIncident() {
        final var converter = new RoadIncidentEventToRoadIncidentMapper();

        assertEquals(TestDataProvider.newRoadIncident(), converter.convert(TestDataProvider.roadIncidentEvent()));
    }
}