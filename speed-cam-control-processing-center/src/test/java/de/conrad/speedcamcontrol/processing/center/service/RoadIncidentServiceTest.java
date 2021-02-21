package de.conrad.speedcamcontrol.processing.center.service;

import java.util.List;
import java.util.Optional;

import de.conrad.speedcamcontrol.processing.center.configuration.properties.ProcessingCenterProperties;
import de.conrad.speedcamcontrol.processing.center.converter.RoadIncidentPersistenceToRoadIncidentMapper;
import de.conrad.speedcamcontrol.processing.center.converter.RoadIncidentToRoadIncidentPersistenceMapper;
import de.conrad.speedcamcontrol.processing.center.persistence.RoadIncidentRepository;
import de.conrad.speedcamcontrol.processing.center.testdata.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoadIncidentServiceTest {

    @Mock
    private ProcessingCenterProperties processingCenterProperties;

    @Mock
    private RoadIncidentRepository roadIncidentRepository;

    @Mock
    private RoadIncidentToRoadIncidentPersistenceMapper roadIncidentToRoadIncidentPersistenceMapper;

    @Mock
    private RoadIncidentPersistenceToRoadIncidentMapper roadIncidentPersistenceToRoadIncidentMapper;

    @InjectMocks
    private RoadIncidentService roadIncidentService;

    @Test
    void shouldUpdateRadIncident() {
        final var roadIncidentEntity = TestDataProvider.roadIncidentEntity();

        final var expectedRoadIncidentEntity = TestDataProvider.roadIncidentEntity();
        expectedRoadIncidentEntity.setApproved(true);
        expectedRoadIncidentEntity.setPenalty(TestDataProvider.PENALTY);
        expectedRoadIncidentEntity.setViewed(true);

        when(roadIncidentRepository.findById(TestDataProvider.INCIDENT_ID)).thenReturn(Optional.ofNullable(roadIncidentEntity));

        roadIncidentService.updateRadIncident(TestDataProvider.INCIDENT_ID, true, TestDataProvider.PENALTY);

        verify(roadIncidentRepository).findById(TestDataProvider.INCIDENT_ID);
        verify(roadIncidentRepository).save(expectedRoadIncidentEntity);
    }

    @Test
    void shouldUpdateAndSetPenaltyIfApprovedRadIncidentWhen() {
        final var roadIncidentEntity = TestDataProvider.roadIncidentEntity();

        final var expectedRoadIncidentEntity = TestDataProvider.roadIncidentEntity();
        expectedRoadIncidentEntity.setViewed(true);

        when(roadIncidentRepository.findById(TestDataProvider.INCIDENT_ID)).thenReturn(Optional.ofNullable(roadIncidentEntity));

        roadIncidentService.updateRadIncident(TestDataProvider.INCIDENT_ID, false, null);

        verify(roadIncidentRepository).findById(TestDataProvider.INCIDENT_ID);
        verify(roadIncidentRepository).save(expectedRoadIncidentEntity);
    }

    @Test
    void shouldNotUpdateRadIncidentWhenIncidentIsNotExist() {

        when(roadIncidentRepository.findById(TestDataProvider.INCIDENT_ID)).thenReturn(Optional.ofNullable(null));

        roadIncidentService.updateRadIncident(TestDataProvider.INCIDENT_ID, true, TestDataProvider.PENALTY);

        verify(roadIncidentRepository).findById(TestDataProvider.INCIDENT_ID);
        verifyNoMoreInteractions(roadIncidentRepository);
    }

    @Test
    void shouldProcessRoadIncident() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var roadIncidentEntity = TestDataProvider.newRoadIncidentEntity();

        when(processingCenterProperties.getAllowedSpeedOnPublicRoad()).thenReturn(2);
        when(roadIncidentToRoadIncidentPersistenceMapper.convert(roadIncident)).thenReturn(roadIncidentEntity);

        roadIncidentService.processRoadIncident(roadIncident);

        verify(roadIncidentRepository).save(roadIncidentEntity);
    }

    @Test
    void shouldNotProcessRoadIncidentWhenObjectSpeedIsAllowed() {
        final var roadIncident = TestDataProvider.roadIncident();

        when(processingCenterProperties.getAllowedSpeedOnPublicRoad()).thenReturn(10);

        roadIncidentService.processRoadIncident(roadIncident);

        verifyNoInteractions(roadIncidentRepository);
    }

    @Test
    void shouldGetAllIncidents() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var roadIncidentEntity = TestDataProvider.roadIncidentEntity();

        when(roadIncidentRepository.findAll()).thenReturn(List.of(roadIncidentEntity));
        when(roadIncidentPersistenceToRoadIncidentMapper.convert(roadIncidentEntity)).thenReturn(roadIncident);

        final var roadIncidents = roadIncidentService.getAllIncidents();

        assertEquals(List.of(roadIncident), roadIncidents);
    }

    @Test
    void shouldGetAllIncidentsWhenIncidentsIsEmpty() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var roadIncidentEntity = TestDataProvider.roadIncidentEntity();

        when(roadIncidentRepository.findAll()).thenReturn(List.of(roadIncidentEntity));
        when(roadIncidentPersistenceToRoadIncidentMapper.convert(roadIncidentEntity)).thenReturn(roadIncident);

        final var roadIncidents = roadIncidentService.getAllIncidents();

        assertEquals(List.of(roadIncident), roadIncidents);
    }

    @Test
    void shouldGetAllUnviewedIncidents() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var roadIncidentEntity = TestDataProvider.roadIncidentEntity();

        when(roadIncidentRepository.findAllByViewedFalseOrderByIncidentTsDesc()).thenReturn(List.of(roadIncidentEntity));
        when(roadIncidentPersistenceToRoadIncidentMapper.convert(roadIncidentEntity)).thenReturn(roadIncident);

        final var roadIncidents = roadIncidentService.getAllUnviewedIncidents();

        assertEquals(List.of(roadIncident), roadIncidents);
    }

    @Test
    void shouldGetAllUnviewedIncidentsWhenIncidentsIsEmpty() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var roadIncidentEntity = TestDataProvider.roadIncidentEntity();

        when(roadIncidentRepository.findAllByViewedFalseOrderByIncidentTsDesc()).thenReturn(List.of(roadIncidentEntity));
        when(roadIncidentPersistenceToRoadIncidentMapper.convert(roadIncidentEntity)).thenReturn(roadIncident);

        final var roadIncidents = roadIncidentService.getAllUnviewedIncidents();

        assertEquals(List.of(roadIncident), roadIncidents);
    }

    @Test
    void shouldGetAllIncidentsByObjectNumber() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var roadIncidentEntity = TestDataProvider.roadIncidentEntity();

        when(roadIncidentRepository.findAllByObjectNumberOrderByIncidentTsDesc(TestDataProvider.OBJECT_NUMBER)).thenReturn(List.of(roadIncidentEntity));
        when(roadIncidentPersistenceToRoadIncidentMapper.convert(roadIncidentEntity)).thenReturn(roadIncident);

        final var roadIncidents = roadIncidentService.getAllIncidentsByObjectNumber(TestDataProvider.OBJECT_NUMBER);

        assertEquals(List.of(roadIncident), roadIncidents);
    }

    @Test
    void shouldGgtAllIncidentsByObjectNumberWhenIncidentsIsEmpty() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var roadIncidentEntity = TestDataProvider.roadIncidentEntity();

        when(roadIncidentRepository.findAllByObjectNumberOrderByIncidentTsDesc(TestDataProvider.OBJECT_NUMBER)).thenReturn(List.of(roadIncidentEntity));
        when(roadIncidentPersistenceToRoadIncidentMapper.convert(roadIncidentEntity)).thenReturn(roadIncident);

        final var roadIncidents = roadIncidentService.getAllIncidentsByObjectNumber(TestDataProvider.OBJECT_NUMBER);

        assertEquals(List.of(roadIncident), roadIncidents);
    }
}