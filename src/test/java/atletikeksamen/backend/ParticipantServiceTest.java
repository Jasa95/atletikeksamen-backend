package atletikeksamen.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ParticipantServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        // Clear existing data and create dummy data
        mockMvc.perform(delete("/participants"))
                .andExpect(status().isNoContent());

        String participant1 = "{\"name\": \"John Doe\", \"gender\": \"male\", \"age\": 25, \"club\": \"Club A\"}";
        String participant2 = "{\"name\": \"Jane Smith\", \"gender\": \"female\", \"age\": 30, \"club\": \"Club B\"}";
        mockMvc.perform(post("/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(participant1))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(participant2))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldCreateParticipant() throws Exception {
        String participantJson = "{\"name\": \"Alice Johnson\", \"gender\": \"female\", \"age\": 22, \"club\": \"Club C\"}";
        mockMvc.perform(post("/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(participantJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetParticipantById() throws Exception {
        mockMvc.perform(get("/participants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void shouldUpdateParticipant() throws Exception {
        String participantUpdateJson = "{\"name\": \"John Smith\", \"gender\": \"male\", \"age\": 26, \"club\": \"Club B\"}";
        mockMvc.perform(put("/participants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(participantUpdateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Smith"));
    }

    @Test
    public void shouldDeleteParticipant() throws Exception {
        mockMvc.perform(delete("/participants/1"))
                .andExpect(status().isNoContent());
    }

}