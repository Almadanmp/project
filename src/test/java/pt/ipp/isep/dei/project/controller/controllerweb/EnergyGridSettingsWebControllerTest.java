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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.dto.mappers.EnergyGridMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
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

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "7");

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
    }

    @Test
     void seeIfGetRoomsWebDtoInGridWorks() {
        //Arrange
        List<RoomDTOWeb> roomDTOWebs = new ArrayList<>();
        RoomDTOWeb roomDTOWeb = new RoomDTOWeb();
        roomDTOWeb.setFloor(3);
        roomDTOWeb.setLength(3);
        roomDTOWeb.setWidth(3);
        roomDTOWeb.setName("B107");
        roomDTOWeb.setHeight(3);
        roomDTOWebs.add(roomDTOWeb);
        Mockito.doReturn(roomDTOWebs).when(energyGridRepository).getRoomsDtoWebInGrid("B building");
        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.getRoomsWebDtoInGrid("B building");
        //Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
     void seeIfGetRoomsWebDtoInGridNotFoundGridId() {
        //Arrange
        List<RoomDTOWeb> roomDTOWebs = new ArrayList<>();
        RoomDTOWeb roomDTOWeb = new RoomDTOWeb();
        roomDTOWeb.setFloor(3);
        roomDTOWeb.setLength(3);
        roomDTOWeb.setWidth(3);
        roomDTOWeb.setName("B107");
        roomDTOWeb.setHeight(3);
        roomDTOWebs.add(roomDTOWeb);
        Mockito.doThrow(NullPointerException.class).when(energyGridRepository).getRoomsDtoWebInGrid("B building");
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
        Mockito.doThrow(NoSuchElementException.class).when(energyGridRepository).attachRoomToGrid("B107", "B building");
        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.attachRoomToGrid("B107", "B building");
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
        Mockito.doReturn(true).when(energyGridRepository).attachRoomToGrid("B107", "B building");
        Mockito.doReturn(Optional.of(RoomMapper.dtoToObject(roomDto))).when(roomRepository).findRoomByID("B107");
        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.attachRoomToGrid("B107", "B building");
        //Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
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
        Mockito.doReturn(false).when(energyGridRepository).attachRoomToGrid("B107", "B building");
        Mockito.doReturn(Optional.of(RoomMapper.dtoToObject(roomDto))).when(roomRepository).findRoomByID("B107");
        //Act
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.attachRoomToGrid("B107", "B building");
        //Assert
        assertEquals(HttpStatus.CONFLICT, actualResult.getStatusCode());
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
        ResponseEntity<Object> actualResult = energyGridSettingsWebController.attachRoomToGrid("B107", "B building");
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateEnergyGridGenerates409() {

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, "7");

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(false).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.CONFLICT, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateEnergyGridGenerates400NullName() {

        EnergyGrid validGrid = new EnergyGrid(null, 45D, "7");

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateEnergyGridGenerates400NullHouseID() {

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45D, null);

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
    void seeIfCreateEnergyGridGenerates400NullMaxContractedPower() {

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", null, "7");

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
    }

    @Test
    void seeIfGetAllGridsDoesNotWork() throws Exception {
        String uri = "/gridSettings/grids";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    void seeIfDetachRoomFromGridWorksInvalid() throws Exception {
        // Arrange

        String URI = "/gridSettings/grids/B%20Building";

        // Act

        MvcResult actualResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = actualResult.getResponse().getStatus();

        // Assert

        assertEquals(404, status);
    }

    @Test
    void seeIfDeleteRoomFromGridWorks() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        Mockito.doReturn(true).when(energyGridRepository).removeRoomFromGrid(any(String.class), any(String.class));

        this.mockMvc.perform(delete("/gridSettings/grids/B building")
                .contentType(MediaType.TEXT_PLAIN)
                .content("B106"))
                .andExpect(status().isOk());
    }

    @Test
    void seeIfDeleteRoomFromGridWorksInvalidGrid() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        Mockito.doThrow(NoSuchElementException.class).when(energyGridRepository).removeRoomFromGrid(any(String.class), any(String.class));

        this.mockMvc.perform(delete("/gridSettings/grids/invalid")
                .contentType(MediaType.TEXT_PLAIN)
                .content("invalid"))
                .andExpect(status().isNotFound());
    }

    @Test
    void seeIfDeleteRoomFromGridWorksInvalidRoom() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        Mockito.doReturn(false).when(energyGridRepository).removeRoomFromGrid(any(String.class), any(String.class));

        this.mockMvc.perform(delete("/gridSettings/grids/invalid")
                .contentType(MediaType.TEXT_PLAIN)
                .content("invalid"))
                .andExpect(status().isNotFound());
    }
}
