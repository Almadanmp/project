package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.mappers.AreaSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
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

        AreaSensor sensor1 = new AreaSensor("RF12345", "Meteo station ISEP - rainfall", "rainfall", new Local(45, 45, 45), new Date());
        ArrayList<AreaSensor> areaSensorArrayList = new ArrayList<>();
        areaSensorArrayList.add(sensor1);
        geo1.setAreaSensors(areaSensorArrayList);
        geo1.setLocation(new Local(45, 45, 45));

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

        AreaSensor sensor1 = new AreaSensor("RF1234", "Meteo station ISEP - rainfall", "rainfall", new Local(45, 45, 45), new Date());
        ArrayList<AreaSensor> areaSensorArrayList = new ArrayList<>();
        areaSensorArrayList.add(sensor1);
        geo1.setAreaSensors(areaSensorArrayList);
        geo1.setLocation(new Local(45, 45, 45));

        String id2 = "RF12345";
        Mockito.doReturn(GeographicAreaMapper.objectToDTO(geo1)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.doReturn(false).when(this.geographicAreaRepository).removeSensorDTO(GeographicAreaMapper.objectToDTO(geo1), id2);

        // Act & Assert
        this.mockMvc.perform(delete("/sensorsettings/areas/1/sensors/RF12345"))
                .andExpect(status().isNotFound());
    }

    @Test
    void seeIfCreateAreaSensorWorks() throws Exception {

        // Arrange
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorSettingsWebController).build();
        long id = 1;
        GeographicArea firstValidArea = new GeographicArea("ISEP", "urban area", 300, 200,
                new Local(45, 45, 45));
        firstValidArea.setId(id);
        firstValidArea.setDescription("Campus do ISEP");

        AreaSensor sensor1 = new AreaSensor("RF12345", "Meteo station ISEP - rainfall", "rainfall", new Local(45, 45, 45), new Date());
        AreaSensor sensor2 = new AreaSensor("test", "test", "rainfall", new Local(45, 45, 45), new Date());
        List<AreaSensor> sensors = new ArrayList<>();
        sensors.add(sensor1);
        firstValidArea.setAreaSensors(sensors);
        AreaSensorDTO areaSensorDTO = AreaSensorMapper.objectToDTO(sensor2);


        Mockito.doReturn(GeographicAreaMapper.objectToDTO(firstValidArea)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.doReturn(true).when(this.geographicAreaRepository).addSensorDTO(GeographicAreaMapper.objectToDTO(firstValidArea), areaSensorDTO);
        Mockito.doNothing().when(this.geographicAreaRepository).updateAreaDTO(GeographicAreaMapper.objectToDTO(firstValidArea));

        // Perform

        this.mockMvc.perform(post("/sensorsettings/areas/1/sensors").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"sensorId\": \"test\",\n" +
                        "  \"name\": \"test\",\n" +
                        "  \"typeSensor\": \"temperature\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfCreateAreaSensorFailAddingAlreadyExistingSensor() throws Exception {

        // Arrange
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorSettingsWebController).build();
        long id = 1;
        GeographicArea firstValidArea = new GeographicArea("ISEP", "urban area", 300, 200,
                new Local(45, 45, 45));
        firstValidArea.setId(id);
        firstValidArea.setDescription("Campus do ISEP");

        AreaSensor sensor1 = new AreaSensor("RF12345", "Meteo", "rainfall", new Local(45, 45, 45), new Date());
        List<AreaSensor> sensors = new ArrayList<>();
        sensors.add(sensor1);
        firstValidArea.setAreaSensors(sensors);
        AreaSensorDTO areaSensorDTO = AreaSensorMapper.objectToDTO(sensor1);


        Mockito.doReturn(GeographicAreaMapper.objectToDTO(firstValidArea)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.doReturn(false).when(this.geographicAreaRepository).addSensorDTO(GeographicAreaMapper.objectToDTO(firstValidArea), areaSensorDTO);

        // Perform

        this.mockMvc.perform(post("/sensorsettings/areas/1/sensors").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"sensorId\": \"RF12345\",\n" +
                        "  \"name\": \"Meteo\",\n" +
                        "  \"typeSensor\": \"temperature\"\n" +
                        "}"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void seeIfRetrieveGAWorks() throws Exception {

        // Arrange
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorSettingsWebController).build();
        long id = 1;
        GeographicArea firstValidArea = new GeographicArea("ISEP", "urban area", 300, 200,
                new Local(45, 45, 45));
        firstValidArea.setId(id);
        firstValidArea.setDescription("Campus do ISEP");


        Mockito.doReturn(GeographicAreaMapper.objectToDTO(firstValidArea)).when(this.geographicAreaRepository).getDTOById(id);

        // Perform

        this.mockMvc.perform(get("/sensorsettings/areas/1"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllSensorsWorks() throws Exception {

        // Arrange
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorSettingsWebController).build();
        long id = 1;
        GeographicArea firstValidArea = new GeographicArea("ISEP", "urban area", 300, 200,
                new Local(45, 45, 45));
        firstValidArea.setId(id);
        firstValidArea.setDescription("Campus do ISEP");


        Mockito.doReturn(GeographicAreaMapper.objectToDTO(firstValidArea)).when(this.geographicAreaRepository).getDTOById(id);

        // Perform

        this.mockMvc.perform(get("/sensorsettings/areas/1/sensors"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllGeographicAreasWorks() throws Exception {

        // Arrange
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorSettingsWebController).build();
        long id = 1;
        GeographicArea firstValidArea = new GeographicArea("ISEP", "urban area", 300, 200,
                new Local(45, 45, 45));
        firstValidArea.setId(id);
        firstValidArea.setDescription("Campus do ISEP");

        // Perform

        this.mockMvc.perform(get("/sensorsettings/areas"))
                .andExpect(status().isOk());
    }

}