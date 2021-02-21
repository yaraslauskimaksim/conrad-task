package de.conrad.speedcamcontrol.processing.center.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.conrad.speedcamcontrol.processing.center.converter.RoadIncidentToRoadIncidentApiMapper;
import de.conrad.speedcamcontrol.processing.center.service.RoadIncidentService;
import de.conrad.speedcamcontrol.processing.center.testdata.TestDataProvider;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ProcessingCenterController.class})
class ProcessingCenterControllerTest {

    private static final String ROOT_PATH = "/public/v1/incidents";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoadIncidentService roadIncidentService;

    @MockBean
    private RoadIncidentToRoadIncidentApiMapper roadIncidentToRoadIncidentApiMapper;

    @Test
    @SneakyThrows
    void shouldGetAllIncidents() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var expectedResponse = TestDataProvider.roadIncidentApi();

        when(roadIncidentService.getAllIncidents()).thenReturn(List.of(roadIncident));
        when(roadIncidentToRoadIncidentApiMapper.convert(roadIncident)).thenReturn(expectedResponse);

        mockMvc.perform(
            get(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(List.of(expectedResponse))));
    }

    @Test
    @SneakyThrows
    void shouldGetAllUnviewedIncidents() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var expectedResponse = TestDataProvider.roadIncidentApi();

        when(roadIncidentService.getAllUnviewedIncidents()).thenReturn(List.of(roadIncident));
        when(roadIncidentToRoadIncidentApiMapper.convert(roadIncident)).thenReturn(expectedResponse);

        mockMvc.perform(
            get(ROOT_PATH + "/unviewed")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(List.of(expectedResponse))));
    }

    @Test
    @SneakyThrows
    void shouldGetAllIncidentsByObjectNumber() {
        final var roadIncident = TestDataProvider.roadIncident();
        final var expectedResponse = TestDataProvider.roadIncidentApi();

        when(roadIncidentService.getAllIncidentsByObjectNumber(TestDataProvider.OBJECT_NUMBER)).thenReturn(List.of(roadIncident));
        when(roadIncidentToRoadIncidentApiMapper.convert(roadIncident)).thenReturn(expectedResponse);

        mockMvc.perform(
            get(ROOT_PATH + "/")
                .param("objectNumber", TestDataProvider.OBJECT_NUMBER)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(List.of(expectedResponse))));
    }

    @Test
    @SneakyThrows
    void shouldCreateRoadIncident() {
        final var roadIncidentUpdateRequest = TestDataProvider.roadIncidentUpdateRequest();

        mockMvc.perform(
            put(ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roadIncidentUpdateRequest)))
            .andExpect(status().isOk());
    }
}