package de.conrad.speedcamcontrol.processing.center.converter;

import de.conrad.speedcamcontrol.processing.center.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadIncidentPersistenceToRoadIncidentMapperTest {

    @Test
    void shouldConvertRoadIncidentPersistenceToRoadIncident() {
        final var converter = new RoadIncidentPersistenceToRoadIncidentMapper();

        assertEquals(TestDataProvider.roadIncident(), converter.convert(TestDataProvider.roadIncidentEntity()));
    }
}