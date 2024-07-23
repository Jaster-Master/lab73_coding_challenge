package net.zecher.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import net.zecher.backend.dto.AuthDto;
import net.zecher.backend.dto.TrafficSignObservationDto;
import net.zecher.backend.repo.TrafficSignObservationRepo;
import net.zecher.backend.repo.UserRepo;
import net.zecher.backend.service.TrafficSignObservationService;
import net.zecher.backend.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BackendObservationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TrafficSignObservationRepo observationRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private TrafficSignObservationService observationService;

    private String token;

    @BeforeEach
    @Transactional
    void login() throws Exception {
        var authDto = new AuthDto();
        authDto.setUserName("test");
        authDto.setPassword("1234");
        userService.registerUser(authDto);
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/register", authDto).contentType("application/json").content(authJson));
        var response = mockMvc.perform(post("/login", authDto)
                        .contentType("application/json")
                        .content(authJson))
                .andReturn();
        token = response.getResponse().getContentAsString();
    }

    @AfterEach
    @Transactional
    void deleteCreatedData() {
        userRepo.deleteByUserName("test");
        observationRepo.deleteAll();
    }

    @Test
    @Transactional
    void addObservationTest() throws Exception {
        var trafficSignObservationDto = new TrafficSignObservationDto();
        trafficSignObservationDto.setLatitude(48.117233008160746);
        trafficSignObservationDto.setLongitude(11.382950238335614);
        trafficSignObservationDto.setHeading(264);
        trafficSignObservationDto.setType(ObservationType.SPEED_LIMIT);
        trafficSignObservationDto.setValue("100");
        var objectMapper = new ObjectMapper();
        var observationJson = objectMapper.writeValueAsString(trafficSignObservationDto);
        mockMvc.perform(post("/api/traffic-sign-observations", trafficSignObservationDto)
                        .contentType("application/json")
                        .content(observationJson)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("\"latitude\":48.117233008160746,\"longitude\":11.382950238335614,\"heading\":264,\"type\":\"SPEED_LIMIT\",\"value\":\"100\"}")));
    }

    @Test
    @Transactional
    void getEmptyObservationsTest() throws Exception {
        mockMvc.perform(get("/api/traffic-sign-observations")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.is("[]")));
    }

    @Test
    @Transactional
    void getObservationsTest() throws Exception {
        var trafficSignObservationDto = new TrafficSignObservationDto();
        trafficSignObservationDto.setLatitude(48.117233008160746);
        trafficSignObservationDto.setLongitude(11.382950238335614);
        trafficSignObservationDto.setHeading(264);
        trafficSignObservationDto.setType(ObservationType.SPEED_LIMIT);
        trafficSignObservationDto.setValue("100");
        observationService.addTrafficSignObservation(trafficSignObservationDto);
        mockMvc.perform(get("/api/traffic-sign-observations")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.not("[]")));
    }
}
