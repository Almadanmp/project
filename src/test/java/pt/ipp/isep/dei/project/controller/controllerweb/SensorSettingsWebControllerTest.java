package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.SensorTypeDTO;
import pt.ipp.isep.dei.project.dto.mappers.AreaSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;
import pt.ipp.isep.dei.project.model.user.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
class SensorSettingsWebControllerTest {

    @Mock
    UserService userService;

    @Mock
    GeographicAreaRepository geographicAreaRepository;

    @Mock
    SensorTypeRepository sensorTypeRepository;

    @InjectMocks
    SensorSettingsWebController sensorSettingsWebController;

    @Autowired
    private MockMvc mockMvc;

    private long id;
    private AreaSensor validAreaSensor;
    private GeographicArea validGeographicArea;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorSettingsWebController).build();
        id = 1;
        validAreaSensor = new AreaSensor("RF12345", "Meteo station ISEP - rainfall", "rainfall", new Local(45, 45, 45), new Date());
        validGeographicArea = new GeographicArea("ISEP", "urban area", 300, 200,
                new Local(45, 45, 45));
        validGeographicArea.setId(id);
        System.out.println(Runtime.getRuntime().freeMemory());
    }

    @Test
    void seeIfRemoveAreaSensorWorks() throws Exception {
        // Arrange

        ArrayList<AreaSensor> areaSensorArrayList = new ArrayList<>();
        areaSensorArrayList.add(validAreaSensor);
        validGeographicArea.setAreaSensors(areaSensorArrayList);
        validGeographicArea.setLocation(new Local(45, 45, 45));

        String id2 = "RF12345";
        Mockito.doReturn(GeographicAreaMapper.objectToDTO(validGeographicArea)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.doReturn(true).when(this.geographicAreaRepository).removeSensorDTO(GeographicAreaMapper.objectToDTO(validGeographicArea), id2);
        Mockito.doNothing().when(this.geographicAreaRepository).updateAreaDTO(GeographicAreaMapper.objectToDTO(validGeographicArea));

        // Act & Assert
        this.mockMvc.perform(delete("/sensors/areas/1/sensors/RF12345", validAreaSensor))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRemoveAreaSensorWorksForNotFound() throws Exception {
        // Arrange

        ArrayList<AreaSensor> areaSensorArrayList = new ArrayList<>();
        areaSensorArrayList.add(validAreaSensor);
        validGeographicArea.setAreaSensors(areaSensorArrayList);
        validGeographicArea.setLocation(new Local(45, 45, 45));

        String id2 = "RF12345";
        Mockito.doReturn(GeographicAreaMapper.objectToDTO(validGeographicArea)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.doReturn(false).when(this.geographicAreaRepository).removeSensorDTO(GeographicAreaMapper.objectToDTO(validGeographicArea), id2);

        // Act & Assert
        this.mockMvc.perform(delete("/sensorsettings/areas/1/sensors/RF12345"))
                .andExpect(status().isNotFound());
    }

    @Test
    void seeIfCreateAreaSensorWorks() {

        // Arrange

        AreaSensor sensor2 = new AreaSensor("test", "test", "rainfall", new Local(45, 45, 45), new Date());
        List<AreaSensor> sensors = new ArrayList<>();
        sensors.add(validAreaSensor);
        validGeographicArea.setAreaSensors(sensors);
        AreaSensorDTO areaSensorDTO = AreaSensorMapper.objectToDTO(sensor2);

        Mockito.doReturn(GeographicAreaMapper.objectToDTO(validGeographicArea)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.doReturn(true).when(this.geographicAreaRepository).addSensorDTO(GeographicAreaMapper.objectToDTO(validGeographicArea), areaSensorDTO);

        // Perform

        ResponseEntity<Object> actualResult = sensorSettingsWebController.createAreaSensor(areaSensorDTO, id);

        // Assert

        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
    }

    @Test
    void seeIfGetGAWorks() throws Exception {

        // Arrange

        Mockito.doReturn(GeographicAreaMapper.objectToDTO(validGeographicArea)).when(this.geographicAreaRepository).getDTOById(id);

        // Perform

        this.mockMvc.perform(get("/sensors/areas/1"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfGetAreaSensorWorks() {
        //Arrange

        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();

        geographicAreaDTO.setId(12L);

        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();
        areaSensorDTO.setId("sensor");

        geographicAreaDTO.addSensor(areaSensorDTO);

        Mockito.when(geographicAreaRepository.getDTOById(geographicAreaDTO.getGeographicAreaId())).thenReturn(geographicAreaDTO);

        //Act

        AreaSensorDTO actualResult = sensorSettingsWebController.getAreaSensor(12L, "sensor");

        //Assert

        assertEquals(areaSensorDTO, actualResult);
    }


    @Test
    void seeIfCreateAreaSensorFailsWithConflict() {
        //Arrange

        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
        geographicAreaDTO.setId(12L);

        AreaSensorDTO areaSensorDTO2 = AreaSensorMapper.objectToDTO(validAreaSensor);

        Mockito.when(geographicAreaRepository.getDTOById(12L)).thenReturn(geographicAreaDTO);
        Mockito.when(geographicAreaRepository.sensorExists(areaSensorDTO2.getSensorId())).thenReturn(true);

        //Act

        ResponseEntity<Object> actualResult = sensorSettingsWebController.createAreaSensor(areaSensorDTO2, 12L);

        //Assert

        assertEquals(HttpStatus.CONFLICT, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsEmptyName() {

        // Arrange

        List<AreaSensor> sensors = new ArrayList<>();
        sensors.add(validAreaSensor);
        validGeographicArea.setAreaSensors(sensors);
        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();
        areaSensorDTO.setName("");
        areaSensorDTO.setId("RF1234");
        areaSensorDTO.setUnits("mm");
        areaSensorDTO.setTypeSensor("temperature");
        areaSensorDTO.setDateStartedFunctioning("2018-10-12");

        Mockito.doReturn(GeographicAreaMapper.objectToDTO(validGeographicArea)).when(this.geographicAreaRepository).getDTOById(id);

        // Perform

        ResponseEntity<Object> actualResult = sensorSettingsWebController.createAreaSensor(areaSensorDTO, id);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsWrongGA() {

        // Arrange

        List<AreaSensor> sensors = new ArrayList<>();
        sensors.add(validAreaSensor);
        validGeographicArea.setAreaSensors(sensors);
        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();

        // Perform
        assertThrows(IllegalArgumentException.class,
                () -> sensorSettingsWebController.createAreaSensor(areaSensorDTO, new Long(null)));

    }

    @Test
    void seeIfCreateAreaSensorFails2() {

        // Arrange

        AreaSensor sensor2 = new AreaSensor("test", "test", "rainfall", new Local(45, 45, 45), new Date());
        List<AreaSensor> sensors = new ArrayList<>();
        sensors.add(validAreaSensor);
        validGeographicArea.setAreaSensors(sensors);
        AreaSensorDTO areaSensorDTO = AreaSensorMapper.objectToDTO(sensor2);


        Mockito.doReturn(GeographicAreaMapper.objectToDTO(validGeographicArea)).when(this.geographicAreaRepository).getDTOById(id);
        Mockito.when(this.geographicAreaRepository.getDTOById(id)).thenThrow(new IllegalArgumentException());

        // Perform

        ResponseEntity<Object> actualResult = sensorSettingsWebController.createAreaSensor(areaSensorDTO, id);

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsNullId() {

        // Arrange

        List<AreaSensor> sensors = new ArrayList<>();
        sensors.add(validAreaSensor);
        validGeographicArea.setAreaSensors(sensors);
        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();
        areaSensorDTO.setName("RF1234");
        areaSensorDTO.setId(null);
        areaSensorDTO.setUnits("mm");
        areaSensorDTO.setTypeSensor("temperature");
        areaSensorDTO.setDateStartedFunctioning("2018-10-12");

        // Perform


        ResponseEntity<Object> actualResult = sensorSettingsWebController.createAreaSensor(areaSensorDTO, id);

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
    void seeIfGetSensorTypesWorks() {
        // Arrange

        SensorType sensorType = new SensorType("rain", "mm");

        sensorTypeRepository.add(sensorType);

        // Act

        ResponseEntity<Object> actualResult = sensorSettingsWebController.getSensorTypes();

        // Assert

        assertEquals(HttpStatus.OK, actualResult.getStatusCode());

    }

    @Test
    void seeIfAddSensorTypeWorks() {
        // Arrange

        List<SensorTypeDTO> emptyList = new ArrayList<>();
        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(emptyList);
        SensorTypeDTO typeToAdd = new SensorTypeDTO();
        typeToAdd.setName("rain");
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(typeToAdd, HttpStatus.OK);

        // Act

        ResponseEntity<Object> actualResult = sensorSettingsWebController.addSensorType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorTypeWorksDuplicate() {
        // Arrange

        List<SensorTypeDTO> repoList = new ArrayList<>();
        SensorTypeDTO typeInRepo = new SensorTypeDTO();
        typeInRepo.setName("rain");
        typeInRepo.setUnits("mm");
        repoList.add(typeInRepo);
        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(repoList);
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(typeInRepo, HttpStatus.CONFLICT);
        SensorTypeDTO typeToAdd = new SensorTypeDTO();
        typeToAdd.setName("rain");
        typeToAdd.setUnits("mm");

        // Act

        ResponseEntity<Object> actualResult = sensorSettingsWebController.addSensorType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorTypeWorksAlmostDuplicate() {
        // Arrange

        List<SensorTypeDTO> repoList = new ArrayList<>();
        SensorTypeDTO typeInRepo = new SensorTypeDTO();
        typeInRepo.setName("rain");
        typeInRepo.setUnits("mm");
        repoList.add(typeInRepo);
        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(repoList);
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(typeInRepo, HttpStatus.CONFLICT);
        SensorTypeDTO typeToAdd = new SensorTypeDTO();
        typeToAdd.setName("rain");
        typeToAdd.setUnits("mm2");

        // Act

        ResponseEntity<Object> actualResult = sensorSettingsWebController.addSensorType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorTypeWorksAlmostDuplicate2() {
        // Arrange

        List<SensorTypeDTO> repoList = new ArrayList<>();
        SensorTypeDTO typeInRepo = new SensorTypeDTO();
        typeInRepo.setName("rain");
        typeInRepo.setUnits("mm");
        repoList.add(typeInRepo);
        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(repoList);
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(typeInRepo, HttpStatus.CONFLICT);
        SensorTypeDTO typeToAdd = new SensorTypeDTO();
        typeToAdd.setName("rain2");
        typeToAdd.setUnits("mm");

        // Act

        ResponseEntity<Object> actualResult = sensorSettingsWebController.addSensorType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorTypeWorksInvalidInputEmpty() {
        // Arrange

        List<SensorTypeDTO> emptyList = new ArrayList<>();
        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(emptyList);
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        SensorTypeDTO typeToAdd = new SensorTypeDTO();
        typeToAdd.setName("");

        // Act

        ResponseEntity<Object> actualResult = sensorSettingsWebController.addSensorType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorTypeWorksInvalidInputNull() {
        // Arrange

        List<SensorTypeDTO> emptyList = new ArrayList<>();
        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(emptyList);
        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        SensorTypeDTO typeToAdd = new SensorTypeDTO();
        typeToAdd.setName(null);

        // Act

        ResponseEntity<Object> actualResult = sensorSettingsWebController.addSensorType(typeToAdd);

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}