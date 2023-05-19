import com.hrp.dto.request.RegisterAdminRequestDto;
import com.hrp.repository.IAuthRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class SpringUnitIntegTestApplicationTests {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer()
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("user-service");


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IAuthRepository authRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);

    }

    @Test
    void shouldCreateUser() throws Exception {
        RegisterAdminRequestDto userRequest = getAdmin();
        String userRequestString = objectMapper.writeValueAsString(userRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/createadmin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, authRepository.findAll().size());
    }

    private RegisterAdminRequestDto getAdmin() {
        return RegisterAdminRequestDto.builder()
                .email("merhabalaaar")
                .name("surname kısmıbu")
                .address("adresi bu")
                .phone("telefon numarası")
                .email("pelin@test.com")
                .build();
    }
}
