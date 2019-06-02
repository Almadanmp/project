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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.dto.SensorTypeDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMinimalMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.SensorTypeMapper;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
class RoomConfigurationWebControllerTest {

    @Mock
    RoomRepository roomRepository;

    @Mock
    SensorTypeRepository sensorTypeRepository;

    @InjectMocks
    RoomConfigurationWebController roomConfigurationWebController;

    @Autowired
    private MockMvc mockMvc;

    private RoomSensor validRoomSensor;
    private Room validRoom;
    private List<SensorTypeDTO> validTypeList;
    private SensorType validSensorType;
    private List<RoomDTOMinimal> validRoomMinimalDTOlist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(roomConfigurationWebController).build();
        validRoomSensor = new RoomSensor("RF12345", "Meteo station ISEP - rainfall", "rainfall", new Date());
        validRoom = new Room("Bedroom", "Cosy", 3, 2, 1, 5, "7");
        validTypeList = new ArrayList<>();
        validSensorType = new SensorType("temperature", "C");
        validTypeList.add(SensorTypeMapper.objectToDTO(validSensorType));
        validRoomMinimalDTOlist = new ArrayList<>();
        validRoomMinimalDTOlist.add(RoomMinimalMapper.objectToDtoWeb(validRoom));
    }

    @Test
    void seeIfRetrieveSensorTypesWorks() throws Exception {

        // Arrange

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);

        // Perform

        this.mockMvc.perform(get("/roomConfiguration/types"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveSensorTypesWorksEmptyList() throws Exception {

        // Arrange

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(new ArrayList<>());

        // Perform

        this.mockMvc.perform(get("/roomConfiguration/types"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveSensorTypesWorksNullList() throws Exception {

        // Arrange

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(null);

        // Perform

        this.mockMvc.perform(get("/roomConfiguration/types"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomsWorks() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getAllDTOWebInformation()).thenReturn(validRoomMinimalDTOlist);

        // Perform

        this.mockMvc.perform(get("/roomConfiguration/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomsWorksEmptyList() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getAllDTOWebInformation()).thenReturn(new ArrayList<>());

        // Perform

        this.mockMvc.perform(get("/roomConfiguration/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomsWorksNullList() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getAllDTOWebInformation()).thenReturn(null);

        // Perform

        this.mockMvc.perform(get("/roomConfiguration/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomSensorsWorks() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getRoomDTOByName(validRoom.getId())).thenReturn(RoomMapper.objectToDTO(validRoom));

        // Perform

        this.mockMvc.perform(get("/roomConfiguration/rooms/Bedroom/sensors"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomSensorsWorksEmptyRoom() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getRoomDTOByName(validRoom.getId())).thenReturn(RoomMapper.objectToDTO(new Room("test", "test", 0, 0, 0, 0, "test")));

        // Perform

        this.mockMvc.perform(get("/roomConfiguration/rooms/Bedroom/sensors"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRemoveRoomSensorWorks() throws Exception {

        // Arrange

        validRoom.addSensor(validRoomSensor);
        String id2 = "RF12345";

        Mockito.when(roomRepository.getRoomDTOByName("Bedroom")).thenReturn(RoomMapper.objectToDTO(validRoom));
        Mockito.when((roomRepository).removeSensorDTO(RoomMapper.objectToDTO(validRoom), id2)).thenReturn(true);


        // Perform

        this.mockMvc.perform(delete("/roomConfiguration/rooms/Bedroom/sensors/RF12345"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRemoveRoomSensorFails() throws Exception {

        // Arrange

        validRoom.addSensor(validRoomSensor);
        String id2 = "RF12345";

        Mockito.when(roomRepository.getRoomDTOByName("Bedroom")).thenReturn(RoomMapper.objectToDTO(validRoom));
        Mockito.when((roomRepository).removeSensorDTO(RoomMapper.objectToDTO(validRoom), id2)).thenReturn(true);


        // Perform

        this.mockMvc.perform(delete("/roomConfiguration/rooms/Bedroom/sensors/RF666"))
                .andExpect(status().isNotFound());
    }

    @Test
    void seeIfCreateAreaSensorWorks() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "temperature", new Date());
        List<RoomSensor> sensors = new ArrayList<>();
        sensors.add(validRoomSensor);
        validRoom.setRoomSensors(sensors);
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);

        List<String> names = new ArrayList<>();
        names.add("temperature");

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);
        Mockito.when(sensorTypeRepository.getTypeNames(validTypeList)).thenReturn(names);

        Mockito.doReturn(RoomMapper.objectToDTO(validRoom)).when(this.roomRepository).getRoomDTOByName("Bedroom");


        Mockito.doReturn(true).when(this.roomRepository).isRoomSensorDTOValid(roomSensorDTO);
        Mockito.doReturn(true).when(this.roomRepository).addSensorDTO(RoomMapper.objectToDTO(validRoom), roomSensorDTO);


        // Mockito.when(((RoomMapper.objectToDTO(validRoom)).addSensor(roomSensorDTO))).thenReturn(true);

        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "Bedroom");

        // Assert

        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsEmptyName() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "temperature", new Date());
        List<RoomSensor> sensors = new ArrayList<>();
        sensors.add(validRoomSensor);
        validRoom.setRoomSensors(sensors);
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);
        roomSensorDTO.setName("");

        List<String> names = new ArrayList<>();
        names.add("temperature");

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);
        Mockito.when(sensorTypeRepository.getTypeNames(validTypeList)).thenReturn(names);

        Mockito.doReturn(RoomMapper.objectToDTO(validRoom)).when(this.roomRepository).getRoomDTOByName("Bedroom");


        Mockito.doReturn(true).when(this.roomRepository).isRoomSensorDTOValid(roomSensorDTO);
        Mockito.doReturn(true).when(this.roomRepository).addSensorDTO(RoomMapper.objectToDTO(validRoom), roomSensorDTO);


        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "Bedroom");

        // Assert

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsNotFoundSensorType() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "wrong", new Date());
        List<RoomSensor> sensors = new ArrayList<>();
        sensors.add(validRoomSensor);
        validRoom.setRoomSensors(sensors);
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);

        List<String> names = new ArrayList<>();
        names.add("temperature");

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);
        Mockito.when(sensorTypeRepository.getTypeNames(validTypeList)).thenReturn(names);

        Mockito.doReturn(RoomMapper.objectToDTO(validRoom)).when(this.roomRepository).getRoomDTOByName("Bedroom");


        Mockito.doReturn(true).when(this.roomRepository).isRoomSensorDTOValid(roomSensorDTO);
        Mockito.doReturn(true).when(this.roomRepository).addSensorDTO(RoomMapper.objectToDTO(validRoom), roomSensorDTO);


        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "Bedroom");

        // Assert

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsSameSensor() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "temperature", new Date());
        List<RoomSensor> sensors = new ArrayList<>();
        sensors.add(validRoomSensor);
        validRoom.setRoomSensors(sensors);
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);

        List<String> names = new ArrayList<>();
        names.add("temperature");

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);
        Mockito.when(sensorTypeRepository.getTypeNames(validTypeList)).thenReturn(names);

        Mockito.doReturn(RoomMapper.objectToDTO(validRoom)).when(this.roomRepository).getRoomDTOByName("Bedroom");


        Mockito.doReturn(true).when(this.roomRepository).isRoomSensorDTOValid(roomSensorDTO);
        Mockito.doReturn(false).when(this.roomRepository).addSensorDTO(RoomMapper.objectToDTO(validRoom), roomSensorDTO);


        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "Bedroom");

        // Assert

        assertEquals(HttpStatus.CONFLICT, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsNullName() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "temperature", new Date());
        List<RoomSensor> sensors = new ArrayList<>();
        sensors.add(validRoomSensor);
        validRoom.setRoomSensors(sensors);
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);
        roomSensorDTO.setName(null);

        List<String> names = new ArrayList<>();
        names.add("temperature");

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);
        Mockito.when(sensorTypeRepository.getTypeNames(validTypeList)).thenReturn(names);

        Mockito.doReturn(RoomMapper.objectToDTO(validRoom)).when(this.roomRepository).getRoomDTOByName("Bedroom");


        //Mockito.doReturn(true).when(this.roomRepository).isRoomSensorDTOValid(roomSensorDTO);
        Mockito.doReturn(true).when(this.roomRepository).addSensorDTO(RoomMapper.objectToDTO(validRoom), roomSensorDTO);


        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "Bedroom");

        // Assert

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsNullDate() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "temperature", new Date());
        List<RoomSensor> sensors = new ArrayList<>();
        sensors.add(validRoomSensor);
        validRoom.setRoomSensors(sensors);
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);
        roomSensorDTO.setDateStartedFunctioning(null);

        List<String> names = new ArrayList<>();
        names.add("temperature");

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);
        Mockito.when(sensorTypeRepository.getTypeNames(validTypeList)).thenReturn(names);

        Mockito.doReturn(RoomMapper.objectToDTO(validRoom)).when(this.roomRepository).getRoomDTOByName("Bedroom");


        //Mockito.doReturn(true).when(this.roomRepository).isRoomSensorDTOValid(roomSensorDTO);
        Mockito.doReturn(true).when(this.roomRepository).addSensorDTO(RoomMapper.objectToDTO(validRoom), roomSensorDTO);


        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "Bedroom");

        // Assert

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsNullType() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "temperature", new Date());
        List<RoomSensor> sensors = new ArrayList<>();
        sensors.add(validRoomSensor);
        validRoom.setRoomSensors(sensors);
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);
        roomSensorDTO.setTypeSensor(null);

        List<String> names = new ArrayList<>();
        names.add("temperature");

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);
        Mockito.when(sensorTypeRepository.getTypeNames(validTypeList)).thenReturn(names);

        Mockito.doReturn(RoomMapper.objectToDTO(validRoom)).when(this.roomRepository).getRoomDTOByName("Bedroom");


        //Mockito.doReturn(true).when(this.roomRepository).isRoomSensorDTOValid(roomSensorDTO);
        Mockito.doReturn(true).when(this.roomRepository).addSensorDTO(RoomMapper.objectToDTO(validRoom), roomSensorDTO);


        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "Bedroom");

        // Assert

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateAreaSensorFailsNullSensorId() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "temperature", new Date());
        List<RoomSensor> sensors = new ArrayList<>();
        sensors.add(validRoomSensor);
        validRoom.setRoomSensors(sensors);
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);
        roomSensorDTO.setSensorId(null);

        List<String> names = new ArrayList<>();
        names.add("temperature");

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(validTypeList);
        Mockito.when(sensorTypeRepository.getTypeNames(validTypeList)).thenReturn(names);

        Mockito.doReturn(RoomMapper.objectToDTO(validRoom)).when(this.roomRepository).getRoomDTOByName("Bedroom");


        //Mockito.doReturn(true).when(this.roomRepository).isRoomSensorDTOValid(roomSensorDTO);
        Mockito.doReturn(true).when(this.roomRepository).addSensorDTO(RoomMapper.objectToDTO(validRoom), roomSensorDTO);


        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "Bedroom");

        // Assert

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }
    
    @Test
    void seeIfCreateRoomSensorFailsWrongRoom() {

        // Arrange

        RoomSensor sensor2 = new RoomSensor("test", "test", "temperature", new Date());
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(sensor2);

        Mockito.doThrow(IllegalArgumentException.class).when(this.roomRepository).getRoomDTOByName("name");

        // Perform

        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoomSensor(roomSensorDTO, "name");

        // Assert

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

}