package de.conrad.speedcamcontrol.dataprovider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.conrad.speedcamcontrol.dataprovider.api.RoadIncidentGenerationRequest;
import de.conrad.speedcamcontrol.dataprovider.service.RoadIncidentDataProviderService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({SpeedCameraDataGeneratorController.class})
class SpeedCameraDataGeneratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoadIncidentDataProviderService roadIncidentDataProviderService;

    @Test
    @SneakyThrows
    void shouldCreateRoadIncident() {
        final var request = new RoadIncidentGenerationRequest(1);

        mockMvc.perform(
            post("/internal/v1/generate-road-incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk());
    }
}