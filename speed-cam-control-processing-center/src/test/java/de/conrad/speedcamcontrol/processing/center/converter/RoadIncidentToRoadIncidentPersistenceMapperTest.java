package de.conrad.speedcamcontrol.processing.center.converter;

import de.conrad.speedcamcontrol.processing.center.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadIncidentToRoadIncidentPersistenceMapperTest {

    @Test
    void shouldConvertRoadIncidentToRoadIncidentPersistence() {
        final var converter = new RoadIncidentToRoadIncidentPersistenceMapper();

        assertEquals(TestDataProvider.newRoadIncidentEntity(), converter.convert(TestDataProvider.roadIncident()));
    }
}