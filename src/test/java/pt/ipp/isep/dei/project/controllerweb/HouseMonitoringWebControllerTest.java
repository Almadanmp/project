package pt.ipp.isep.dei.project.controllerweb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
 class HouseMonitoringWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    HouseMonitoringWebController houseMonitoringWebController;

    @Test
     void getHighestAmplitudeSuccess() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(houseMonitoringWebController).build();

        mockMvc.perform(get("/houseMonitoring/highestAmplitude")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\n" +
                        " {\n" +
                        "\"initialDate\": \"2018-01-01\",\n" +
                        "\"endDate\": \"2019-01-01\"\n" +
                        " }"))
                .andExpect(status().isOk());
    }

    @Test
     void getHighestAmplitudeInvertedDates() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(houseMonitoringWebController).build();

        mockMvc.perform(get("/houseMonitoring/highestAmplitude")
                .content("\n" +
                        " {\n" +
                        "\"initialDate\": \"2019-01-01\",\n" +
                        "\"endDate\": \"2018-01-01\"\n" +
                        " }"))
                .andExpect(status().isBadRequest());
    }

    @Test
     void getHighestAmplitudeNoReadingsOnInterval() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(houseMonitoringWebController).build();

        mockMvc.perform(get("/houseMonitoring/highestAmplitude")
                .content("\n" +
                        " {\n" +
                        "\"initialDate\": \"2010-01-01\",\n" +
                        "\"endDate\": \"2011-01-01\"\n" +
                        " }"))
                .andExpect(status().isBadRequest());
    }
}
