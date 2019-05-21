package pt.ipp.isep.dei.project.controllerweb;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
@ExtendWith(MockitoExtension.class)
class SensorSettingsWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    GeographicAreaRepository geographicAreaRepository;

    @InjectMocks
    SensorSettingsWebController sensorSettingsWebController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void seeIfRemoveAreaSensorWorks() throws Exception {
        // Arrange
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorSettingsWebController).build();
        GeographicArea geo1 = new GeographicArea();
        long id = 1;
        geo1.setId(id);
        geo1.setDescription("Campus do ISEP");
        geo1.setName("ISEP");
        geo1.setAreaTypeID("urban area");

        AreaSensor sensor1 = new AreaSensor("RF12345", "Meteo station ISEP - rainfall", "rainfall", new Local(45,45,45), new Date());
        ArrayList<AreaSensor> areaSensorArrayList = new ArrayList<>();
        areaSensorArrayList.add(sensor1);
        geo1.setAreaSensors(areaSensorArrayList);
        geo1.setLocation(new Local(45,45,45));

        String id2 = "RF12345";
        Mockito.doReturn(GeographicAreaMapper.objectToDTO(geo1)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.doReturn(true).when(this.geographicAreaRepository).removeSensorDTO(GeographicAreaMapper.objectToDTO(geo1), id2);
        Mockito.doNothing().when(this.geographicAreaRepository).updateAreaDTO(GeographicAreaMapper.objectToDTO(geo1));

        // Act & Assert
        this.mockMvc.perform(delete("/sensorsettings/areas/1/sensors/RF12345", sensor1))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRemoveAreaSensorWorksForNotFound() throws Exception {
        // Arrange
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorSettingsWebController).build();
        GeographicArea geo1 = new GeographicArea();
        long id = 1;
        geo1.setId(id);
        geo1.setDescription("Campus do ISEP");
        geo1.setName("ISEP");
        geo1.setAreaTypeID("urban area");

        AreaSensor sensor1 = new AreaSensor("RF1234", "Meteo station ISEP - rainfall", "rainfall", new Local(45,45,45), new Date());
        ArrayList<AreaSensor> areaSensorArrayList = new ArrayList<>();
        areaSensorArrayList.add(sensor1);
        geo1.setAreaSensors(areaSensorArrayList);
        geo1.setLocation(new Local(45,45,45));

        String id2 = "RF12345";
        Mockito.doReturn(GeographicAreaMapper.objectToDTO(geo1)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.doReturn(false).when(this.geographicAreaRepository).removeSensorDTO(GeographicAreaMapper.objectToDTO(geo1), id2);

        // Act & Assert
        this.mockMvc.perform(delete("/sensorsettings/areas/1/sensors/RF12345"))
                .andExpect(status().isNotFound());
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