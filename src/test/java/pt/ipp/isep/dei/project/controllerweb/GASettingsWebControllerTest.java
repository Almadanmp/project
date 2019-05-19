package pt.ipp.isep.dei.project.controllerweb;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
@WebMvcTest
class GASettingsWebControllerTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private GASettingsWebController gaSettingsWebController;

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