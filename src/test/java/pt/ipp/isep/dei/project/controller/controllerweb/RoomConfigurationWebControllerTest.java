package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.AddressLocalGeographicAreaIdDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.dto.SensorTypeDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMinimalMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.SensorTypeMapper;
import pt.ipp.isep.dei.project.model.bridgeservices.HouseRoomService;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;
import pt.ipp.isep.dei.project.model.user.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
class RoomConfigurationWebControllerTest {


    @Mock
    HouseRoomService houseRoomService;

    @Mock
    RoomRepository roomRepository;

    @Mock
    UserService userService;

    @Mock
    SensorTypeRepository sensorTypeRepository;

    @InjectMocks
    RoomsWebController roomConfigurationWebController;

    @Autowired
    private MockMvc mockMvc;

    private RoomDTOMinimal roomDTOMinimal;
    private RoomSensor validRoomSensor;
    private Room validRoom;
    private List<SensorTypeDTO> validTypeList;
    private SensorType validSensorType;
    private List<RoomDTOMinimal> validRoomMinimalDTOlist;
    private AddressLocalGeographicAreaIdDTO addressAndLocalDTO;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setName("Name");
        roomDTOMinimal.setWidth(2D);
        roomDTOMinimal.setLength(4D);
        roomDTOMinimal.setHeight(1D);
        roomDTOMinimal.setFloor(1);

        addressAndLocalDTO = new AddressLocalGeographicAreaIdDTO();

        addressAndLocalDTO.setNumber("431");
        addressAndLocalDTO.setCountry("Portugal");
        addressAndLocalDTO.setZip("4200-072");
        addressAndLocalDTO.setTown("Porto");
        addressAndLocalDTO.setStreet("rua carlos peixoto");

        addressAndLocalDTO.setAltitude(20);
        addressAndLocalDTO.setLongitude(20);
        addressAndLocalDTO.setLatitude(20);

        addressAndLocalDTO.setGeographicAreaId(2L);

        this.mockMvc = MockMvcBuilders.standaloneSetup(roomConfigurationWebController).build();

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

        this.mockMvc.perform(get("/rooms/types"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveSensorTypesWorksEmptyList() throws Exception {

        // Arrange

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(new ArrayList<>());

        // Perform

        this.mockMvc.perform(get("/rooms/types"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveSensorTypesWorksNullList() throws Exception {

        // Arrange

        Mockito.when(sensorTypeRepository.getAllSensorTypeDTO()).thenReturn(null);

        // Perform

        this.mockMvc.perform(get("/rooms/types"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomsWorks() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getAllRoomsAsMinimalDTOs()).thenReturn(validRoomMinimalDTOlist);

        // Perform

        this.mockMvc.perform(get("/rooms/"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomsWorksEmptyList() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getAllRoomsAsMinimalDTOs()).thenReturn(new ArrayList<>());

        // Perform

        this.mockMvc.perform(get("/rooms/"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomsWorksNullList() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getAllRoomsAsMinimalDTOs()).thenReturn(null);

        // Perform

        this.mockMvc.perform(get("/rooms/"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomSensorsWorks() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getRoomDTOByName(validRoom.getId())).thenReturn(RoomMapper.objectToDTO(validRoom));

        // Perform

        this.mockMvc.perform(get("/rooms/Bedroom/sensors"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfRetrieveAllRoomSensorsWorksEmptyRoom() throws Exception {

        // Arrange

        Mockito.when(roomRepository.getRoomDTOByName(validRoom.getId())).thenReturn(RoomMapper.objectToDTO(new Room("test", "test", 0, 0, 0, 0, "test")));

        // Perform

        this.mockMvc.perform(get("/rooms/Bedroom/sensors"))
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

        this.mockMvc.perform(delete("/rooms/Bedroom/sensors/RF12345"))
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


        Mockito.doReturn(true).when(this.roomRepository).roomSensorDTOIsValid(roomSensorDTO);
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


        Mockito.doReturn(true).when(this.roomRepository).roomSensorDTOIsValid(roomSensorDTO);
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


        Mockito.doReturn(true).when(this.roomRepository).roomSensorDTOIsValid(roomSensorDTO);
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


        Mockito.doReturn(true).when(this.roomRepository).roomSensorDTOIsValid(roomSensorDTO);
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


        //Mockito.doReturn(true).when(this.roomRepository).roomSensorDTOIsValid(roomSensorDTO);
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


        //Mockito.doReturn(true).when(this.roomRepository).roomSensorDTOIsValid(roomSensorDTO);
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


        //Mockito.doReturn(true).when(this.roomRepository).roomSensorDTOIsValid(roomSensorDTO);
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


        //Mockito.doReturn(true).when(this.roomRepository).roomSensorDTOIsValid(roomSensorDTO);
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

    @Test
    public void seeIfCreateRoomWorks() {
        //Arrange
        Mockito.doReturn(true).when(this.houseRoomService).addMinimalRoomDTOToHouse(roomDTOMinimal);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(roomDTOMinimal, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoom(roomDTOMinimal);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfCreateRoomAddsLink() {
        //Arrange
        Mockito.doReturn(true).when(this.houseRoomService).addMinimalRoomDTOToHouse(roomDTOMinimal);

        Mockito.when(userService.getUsernameFromToken()).thenReturn("ADMIN");

        //Act
        roomConfigurationWebController.createRoom(roomDTOMinimal);
        Link link = roomDTOMinimal.getLink("Delete the created room.");

        //Assert
        assertNotNull(link);
    }

    @Test
    public void seeIfCreateRoomWorksIfRoomAlreadyExists() {
        //Arrange
        Mockito.doReturn(false).when(this.houseRoomService).addMinimalRoomDTOToHouse(roomDTOMinimal);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("The room you are trying to create already exists.", HttpStatus.CONFLICT);

        //Act
        ResponseEntity<Object> actualResult = roomConfigurationWebController.createRoom(roomDTOMinimal);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfConfigureRoomWorks() {
        //Arrange
        Mockito.doReturn(true).when(this.roomRepository).configureRoom(roomDTOMinimal, "Kitchen");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(roomDTOMinimal, HttpStatus.OK);

        //Act
        ResponseEntity<Object> actualResult = roomConfigurationWebController.configureRoom("Kitchen", roomDTOMinimal);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfConfigureRoomWorksWhenItDoestNotExist() {
        //Arrange
        Mockito.doReturn(false).when(this.roomRepository).configureRoom(roomDTOMinimal, "21");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("The room you are trying to edit does not exist in the database.", HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> actualResult = roomConfigurationWebController.configureRoom("21", roomDTOMinimal);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfConfigureRoomWorksWhenDimensionAreInvalid() {
        //Arrange

        roomDTOMinimal.setLength(0D);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("The room you entered has invalid parameters.", HttpStatus.UNPROCESSABLE_ENTITY);

        //Act
        ResponseEntity<Object> actualResult = roomConfigurationWebController.configureRoom("21", roomDTOMinimal);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeleteRoomWorks() {
        //Arrange
        Mockito.when(this.roomRepository.deleteRoom(roomDTOMinimal)).thenReturn(true);
        Mockito.when(userService.getUsernameFromToken()).thenReturn("ADMIN");


        ResponseEntity<Object> expectedResult = new ResponseEntity<>(roomDTOMinimal, HttpStatus.OK);

        //Act
        ResponseEntity<Object> actualResult = roomConfigurationWebController.deleteRoom(roomDTOMinimal);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeleteRoomWorksIfRoomAlreadyExists() {
        //Arrange
        Mockito.when(this.roomRepository.deleteRoom(roomDTOMinimal)).thenReturn(false);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("The room you are trying to delete does not exist in the database.", HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> actualResult = roomConfigurationWebController.deleteRoom(roomDTOMinimal);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}