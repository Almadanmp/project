package pt.ipp.isep.dei.project.controllerweb;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SensorSettingsWebControllerTest.class)
@Configuration
@ComponentScan
@EnableWebMvc
@AutoConfigureMockMvc
class SensorSettingsWebControllerTest {

    @InjectMocks
    private WebApplicationContext webApplicationContext;

    @InjectMocks
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @InjectMocks
    SensorSettingsWebController sensorSettingsWebController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

//    @Test
//    public void testHello() throws Exception {
//        mockMvc.perform(
//                get("/areas"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void test_get_all_success() throws Exception {
//        List<GeographicAreaDTO> users = Arrays.asList(
//                new GeographicAreaDTO());
//        Mockito.when(sensorSettingsWebController.retrieveAllGeographicAreas()).thenReturn(users);
//        mockMvc.perform(get("sensorsettings/areas"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//
//    }
//

}