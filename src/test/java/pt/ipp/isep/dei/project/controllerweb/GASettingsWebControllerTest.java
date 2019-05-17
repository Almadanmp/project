package pt.ipp.isep.dei.project.controllerweb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudeRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
@WebMvcTest
class GASettingsWebControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private GASettingsWebController gaSettingsWebController = new GASettingsWebController();

    @Test
    void createGeoAreaDTO() throws Exception {

        this.mvc = MockMvcBuilders.standaloneSetup(gaSettingsWebController).build();

        mvc.perform(post("/geographic_area_settings/areas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": 66,\n" +
                        "  \"name\": \"Gaia\",\n" +
                        "  \"typeArea\": \"urban area\",\n" +
                        "  \"length\": 500,\n" +
                        "  \"width\": 100,\n" +
                        "  \"localDTO\": {\n" +
                        "    \"latitude\": 41,\n" +
                        "    \"longitude\": -8,\n" +
                        "    \"altitude\": 100,\n" +
                        "    \"id\": 0\n" +
                        "  },\n" +
                        "  \"description\": \"glorious city\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllGeoAreasDTO() {
    }
}