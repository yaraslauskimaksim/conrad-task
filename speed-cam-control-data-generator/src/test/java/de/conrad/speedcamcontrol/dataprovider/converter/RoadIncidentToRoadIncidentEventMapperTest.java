package de.conrad.speedcamcontrol.dataprovider.converter;

import de.conrad.speedcamcontrol.dataprovider.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadIncidentToRoadIncidentEventMapperTest {

    @Test
    void shouldConvertRoadIncidentToRoadIncidentEvent() {
        final var converter = new RoadIncidentToRoadIncidentEventMapper();

        final var roadIncident = TestDataProvider.roadIncident();
        final var expected = TestDataProvider.roadIncidentEvent();

        assertEquals(expected, converter.convert(roadIncident));
    }
}