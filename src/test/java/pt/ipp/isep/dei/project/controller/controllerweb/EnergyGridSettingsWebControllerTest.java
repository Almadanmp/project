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
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.mappers.EnergyGridMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMinimalMapper;
import pt.ipp.isep.dei.project.model.bridgeservices.EnergyGridRoomService;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
class EnergyGridSettingsWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EnergyGridRepository energyGridRepository;

    @Mock
    private EnergyGridRoomService energyGridRoomService;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private EnergyGridSettingsWebController energyGridSettingsWebController;


    @BeforeEach
    void setData() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void seeIfCreateEnergyGridWorks() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(any(EnergyGridDTO.class));

        this.mockMvc.perform(post("/gridSettings/grids")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"B building\",\n" +
                        "  \"houseID\": \"7\",\n" +
                        "  \"maxContractedPower\": \"45\"\n" +
                        "}"))
                .andExpect(status().isCreated());

    }

    @Test
    void seeIfCreateEnergyGridGenerates201() {
        // Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "7");
        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        HttpStatus expectedResult = HttpStatus.CREATED;

        // Act
        HttpStatus actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO).getStatusCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGridGeneratesBadRequestNullName() {
        // Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "7");
        validGrid.setName(null);
        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        HttpStatus expectedResult = HttpStatus.BAD_REQUEST;

        // Act
        HttpStatus actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO).getStatusCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGridGeneratesBadRequestNullHouseId() {
        // Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "7");
        validGrid.setHouseId(null);
        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        HttpStatus expectedResult = HttpStatus.BAD_REQUEST;

        // Act
        HttpStatus actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO).getStatusCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGridGeneratesBadRequestNullMaxCP() {
        // Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", null, "7");
        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        HttpStatus expectedResult = HttpStatus.BAD_REQUEST;

        // Act
        HttpStatus actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO).getStatusCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGridGeneratesBadRequestNullHouseIdAndMaxCP() {
        // Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", null, null);
        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        HttpStatus expectedResult = HttpStatus.BAD_REQUEST;

        // Act
        HttpStatus actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO).getStatusCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGridBadRequestNullMaxCPAndName() {
        // Arrange

        EnergyGrid validGrid = new EnergyGrid(null, null, "7");
        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        HttpStatus expectedResult = HttpStatus.BAD_REQUEST;

        // Act
        HttpStatus actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO).getStatusCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGridBadRequestIdAndName() {
        // Arrange

        EnergyGrid validGrid = new EnergyGrid(null, 45D, null);
        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        HttpStatus expectedResult = HttpStatus.BAD_REQUEST;

        // Act
        HttpStatus actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO).getStatusCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGridGeneratesConflict() {
        // Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "7");
        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(false).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        HttpStatus expectedResult = HttpStatus.CONFLICT;

        // Act
        HttpStatus actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO).getStatusCode();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfGetAllGridsDoesNotWork() throws Exception {
//        String uri = "/gridSettings/grids";
//        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(404, status);
//    }
//
//     Comentado por causa da dependencia de security - Rever
//    @Test
//    void seeIfDetachRoomFromGridWorksInvalid() throws Exception {
//        // Arrange
//
//        String URI = "/gridSettings/grids/B%20Building";
//
//        // Act
//
//        MvcResult actualResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON))
//                .andReturn();
//        int status = actualResult.getResponse().getStatus();
//
//        // Assert
//
//        assertEquals(404, status);
//    }

    // Comentado por causa da dependencia de security - Rever
//    @Test
//    void seeIfGetAllGridsDoesNotWork() throws Exception {
//        String uri = "/gridSettings/grids";
//        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(404, status);
//    }

    // Comentado por causa da dependencia de security - Rever
//    @Test
//    void seeIfDetachRoomFromGridWorksInvalid() throws Exception {
//        // Arrange
//
//        String URI = "/gridSettings/grids/B%20Building";
//
//        // Act
//
//        MvcResult actualResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON))
//                .andReturn();
//        int status = actualResult.getResponse().getStatus();
//
//        // Assert
//
//        assertEquals(404, status);
//    }

    @Test
    void seeIfDetachRoomFromGridThrowsException() {
        //Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "01");
        Room room = new Room("name", "description", 1, 10, 4, 3, "01");
        validGrid.addRoomId(room.getId());
        RoomDTOMinimal roomDTO = RoomMinimalMapper.objectToDtoWeb(room);

        Mockito.when(energyGridRoomService.removeRoomFromGrid(any(String.class), any(String.class))).thenThrow(new NoSuchElementException());

        //Act

        ResponseEntity<String> actualResult = energyGridSettingsWebController.detachRoomFromGrid(roomDTO, validGrid.getName());

        //Assert

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    void seeIfDetachRoomFromGridFails() {
        //Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "01");
        Room room = new Room("name", "description", 1, 10, 4, 3, "01");
        validGrid.addRoomId(room.getId());
        RoomDTOMinimal roomDTO = RoomMinimalMapper.objectToDtoWeb(room);

        Mockito.doReturn(false).when(energyGridRoomService).removeRoomFromGrid(any(String.class), any(String.class));

        //Act
        ResponseEntity<String> actualResult = energyGridSettingsWebController.detachRoomFromGrid(roomDTO, validGrid.getName());

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());

    }

    @Test
    void seeIfDetachRoomFromGridWorks() {
        //Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "01");
        Room room = new Room("name", "description", 1, 10, 4, 3, "01");
        validGrid.addRoomId(room.getId());
        RoomDTOMinimal roomDTO = RoomMinimalMapper.objectToDtoWeb(room);

        Mockito.doReturn(true).when(energyGridRoomService).removeRoomFromGrid(any(String.class), any(String.class));

        //Act
        ResponseEntity<String> actualResult = energyGridSettingsWebController.detachRoomFromGrid(roomDTO, validGrid.getName());

        //Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());

    }

    @Test
    void seeIfDetachRoomFromGridThrowsNoSuchElementException1() {
        //Arrange

        EnergyGrid validGrid = new EnergyGrid(null, 45D, "01");
        Room room = new Room("name", "description", 1, 10, 4, 3, "01");
        validGrid.addRoomId(room.getId());
        RoomDTOMinimal roomDTO = RoomMinimalMapper.objectToDtoWeb(room);

        //Act
        ResponseEntity<String> actualResult = energyGridSettingsWebController.detachRoomFromGrid(roomDTO, validGrid.getName());

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());

    }

    @Test
    void seeIfDetachRoomFromGridThrowsNoSuchElementException2() {
        //Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "01");
        Room room = new Room(null, "description", 1, 10, 4, 3, "01");
        validGrid.addRoomId(room.getId());
        RoomDTOMinimal roomDTO = RoomMinimalMapper.objectToDtoWeb(room);

        //Act
        ResponseEntity<String> actualResult = energyGridSettingsWebController.detachRoomFromGrid(roomDTO, validGrid.getName());

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());

    }

    @Test
    void seeIfGetAllGridsWorks() {
        //Arrange

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "01");
        EnergyGridDTO energyGridDTO1 = EnergyGridMapper.objectToDTO(validGrid);
        EnergyGrid validGrid2 = new EnergyGrid("Valid Grid 2", 20D, "7");
        EnergyGridDTO energyGridDTO2 = EnergyGridMapper.objectToDTO(validGrid2);
        List<EnergyGridDTO> energyGrids = new ArrayList<>();
        energyGrids.add(energyGridDTO1);
        energyGrids.add(energyGridDTO2);
        List<EnergyGrid> list = new ArrayList<>();
        list.add(validGrid);
        list.add(validGrid2);
        Mockito.when(energyGridRepository.getAllGrids()).thenReturn(list);

        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.getAllGrids();

        //Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    void seeIfAttachRoomToGridPostHttpStatusNotFoundRoomId() {
        //Arrange
        RoomDTO roomDto = new RoomDTO();
        roomDto.setDescription("Test");
        roomDto.setFloor(2);
        roomDto.setWidth(2);
        roomDto.setHeight(3);
        roomDto.setHouseId("ISEP");
        roomDto.setName("B107");
        roomDto.setLength(3);
        Mockito.doReturn(Optional.empty()).when(roomRepository).findRoomByID("B107");

        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.attachRoomToGrid(roomDto, "B building");

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    void seeIfAttachRoomToGridPostHTTPStatusConflict() {
        //Arrange
        RoomDTO roomDto = new RoomDTO();
        roomDto.setDescription("Test");
        roomDto.setFloor(2);
        roomDto.setWidth(2);
        roomDto.setHeight(3);
        roomDto.setHouseId("ISEP");
        roomDto.setName("B107");
        roomDto.setLength(3);
        Mockito.doReturn(false).when(energyGridRoomService).attachRoomToGrid("B107", "B building");
        Mockito.doReturn(Optional.of(RoomMapper.dtoToObject(roomDto))).when(roomRepository).findRoomByID("B107");

        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.attachRoomToGrid(roomDto, "B building");

        //Assert
        assertEquals(HttpStatus.CONFLICT, actualResult.getStatusCode());
    }

        @Test
    void seeIfGetRoomsWebDtoInGridWorks() {
        //Arrange
        List<RoomDTOMinimal> roomDTOMinimals = new ArrayList<>();
        RoomDTOMinimal roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setFloor(3);
        roomDTOMinimal.setLength(3);
        roomDTOMinimal.setWidth(3);
        roomDTOMinimal.setName("B107");
        roomDTOMinimal.setHeight(3);
        roomDTOMinimals.add(roomDTOMinimal);
        Mockito.doReturn(roomDTOMinimals).when(energyGridRoomService).getRoomsDtoWebInGrid("B building");

        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.getRoomsWebDtoInGrid("B building");

        //Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    void seeIfGetRoomsWebDtoInGridNotFoundGridId() {
        //Arrange
        List<RoomDTOMinimal> roomDTOMinimals = new ArrayList<>();
        RoomDTOMinimal roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setFloor(3);
        roomDTOMinimal.setLength(3);
        roomDTOMinimal.setWidth(3);
        roomDTOMinimal.setName("B107");
        roomDTOMinimal.setHeight(3);
        roomDTOMinimals.add(roomDTOMinimal);
        Mockito.doThrow(NullPointerException.class).when(energyGridRoomService).getRoomsDtoWebInGrid("B building");

        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.getRoomsWebDtoInGrid("B building");

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    void seeIfAttachRoomToGridPostHttpStatusNotFoundGridId() {
        //Arrange
        RoomDTO roomDto = new RoomDTO();
        roomDto.setDescription("Test");
        roomDto.setFloor(2);
        roomDto.setWidth(2);
        roomDto.setHeight(3);
        roomDto.setHouseId("ISEP");
        roomDto.setName("B107");
        roomDto.setLength(3);
        Mockito.doReturn(Optional.of(RoomMapper.dtoToObject(roomDto))).when(roomRepository).findRoomByID("B107");
        Mockito.doThrow(NoSuchElementException.class).when(energyGridRoomService).attachRoomToGrid("B107", "B building");

        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.attachRoomToGrid(roomDto, "B building");

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }


    @Test
    void seeIfAttachRoomToGridPostWorks() {
        //Arrange
        RoomDTO roomDto = new RoomDTO();
        roomDto.setDescription("Test");
        roomDto.setFloor(2);
        roomDto.setWidth(2);
        roomDto.setHeight(3);
        roomDto.setHouseId("ISEP");
        roomDto.setName("B107");
        roomDto.setLength(3);
        Mockito.doReturn(true).when(energyGridRoomService).attachRoomToGrid("B107", "B building");
        Mockito.doReturn(Optional.of(RoomMapper.dtoToObject(roomDto))).when(roomRepository).findRoomByID("B107");

        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.attachRoomToGrid(roomDto, "B building");

        //Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }
}
