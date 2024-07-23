package net.zecher.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import net.zecher.backend.dto.AuthDto;
import net.zecher.backend.repo.UserRepo;
import net.zecher.backend.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BackendLoginTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void registerTest() throws Exception {
        var authDto = new AuthDto();
        authDto.setUserName("test");
        authDto.setPassword("1234");
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/register", authDto).contentType("application/json").content(authJson))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.is("true")));
        userRepo.deleteByUserName(authDto.getUserName());
    }

    @Test
    @Transactional
    void alreadyRegisteredTest() throws Exception {
        var authDto = new AuthDto();
        authDto.setUserName("test");
        authDto.setPassword("1234");
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/register", authDto).contentType("application/json").content(authJson))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.is("true")));
        mockMvc.perform(post("/register", authDto).contentType("application/json").content(authJson))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.is("false")));
        userRepo.deleteByUserName(authDto.getUserName());
    }

    @Test
    void emptyRegisterTest() throws Exception {
        var authDto = new AuthDto();
        authDto.setUserName("");
        authDto.setPassword("");
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/register", authDto).contentType("application/json").content(authJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void nullRegisterTest() throws Exception {
        var authDto = new AuthDto();
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/register", authDto).contentType("application/json").content(authJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    void loginTest() throws Exception {
        var authDto = new AuthDto();
        authDto.setUserName("test");
        authDto.setPassword("1234");
        userService.registerUser(authDto);
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/login", authDto).contentType("application/json").content(authJson))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.not(Matchers.emptyOrNullString())));
        userRepo.deleteByUserName(authDto.getUserName());
    }

    @Test
    void notRegisteredLoginTest() throws Exception {
        var authDto = new AuthDto();
        authDto.setUserName("test");
        authDto.setPassword("1234");
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/login", authDto).contentType("application/json").content(authJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void emptyLoginTest() throws Exception {
        var authDto = new AuthDto();
        authDto.setUserName("");
        authDto.setPassword("");
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/login", authDto).contentType("application/json").content(authJson))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void nullLoginTest() throws Exception {
        var authDto = new AuthDto();
        var objectMapper = new ObjectMapper();
        var authJson = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/login", authDto).contentType("application/json").content(authJson))
                .andExpect(status().is4xxClientError());
    }
}
