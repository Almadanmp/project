package pt.ipp.isep.dei.project.controllerweb;

import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
public class EnergyGridSettingsWebControllerTest {

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
    public void seeIfCreateEnergyGridWorks() throws Exception {

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
    public void seeIfCreateEnergyGridWorksMethod() {

        EnergyGrid validGrid = new EnergyGrid("Valid Grid", 45, "7");

        EnergyGridDTO energyGridDTO = EnergyGridMapper.objectToDTO(validGrid);

        Mockito.doReturn(true).when(energyGridRepository).createEnergyGrid(energyGridDTO);

        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);

        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
    }

    @Test
    public void seeIfGetRoomsWebDtoInGridWorks() {
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
    public void seeIfAttachRoomToGridPostWorks() {
        //Arrange
        RoomDTO roomDto = new RoomDTO();
        roomDto.setDescription("Test");
        roomDto.setFloor(2);
        roomDto.setWidth(2);
        roomDto.setHeight(3);
        roomDto.setHouseId("ISEP");
        roomDto.setName("B107");
        roomDto.setLength(3);
        Mockito.doReturn(true).when(energyGridRepository).attachRoomToGrid(roomDto, "B building");
        Mockito.doReturn(true).when(roomRepository).findRoomByIdPresent("B107");
        //Act
        ResponseEntity<String> actualResult = energyGridSettingsWebController.attachRoomToGrid(roomDto, "B building");
        //Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    public void seeIfAttachRoomToGridPostHTTPStatusConflict() {
        //Arrange
        RoomDTO roomDto = new RoomDTO();
        roomDto.setDescription("Test");
        roomDto.setFloor(2);
        roomDto.setWidth(2);
        roomDto.setHeight(3);
        roomDto.setHouseId("ISEP");
        roomDto.setName("B107");
        roomDto.setLength(3);
        Mockito.doReturn(false).when(energyGridRepository).attachRoomToGrid(roomDto, "B building");
        Mockito.doReturn(true).when(roomRepository).findRoomByIdPresent("B107");
        //Act
        ResponseEntity<String> actualResult = energyGridSettingsWebController.attachRoomToGrid(roomDto, "B building");
        //Assert
        assertEquals(HttpStatus.CONFLICT, actualResult.getStatusCode());
    }

    @Test
    public void seeIfAttachRoomToGridPostHttpStatusNotFoundGridId() {
        //Arrange
        RoomDTO roomDto = new RoomDTO();
        roomDto.setDescription("Test");
        roomDto.setFloor(2);
        roomDto.setWidth(2);
        roomDto.setHeight(3);
        roomDto.setHouseId("ISEP");
        roomDto.setName("B107");
        roomDto.setLength(3);
        Mockito.doReturn(true).when(roomRepository).findRoomByIdPresent("B107");
        Mockito.doThrow(NoSuchElementException.class).when(energyGridRepository).attachRoomToGrid(roomDto, "B building");
        //Act
        ResponseEntity<String> actualResult = energyGridSettingsWebController.attachRoomToGrid(roomDto, "B building");
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    public void seeIfAttachRoomToGridPostHttpStatusNotFoundRoomId() {
        //Arrange
        RoomDTO roomDto = new RoomDTO();
        roomDto.setDescription("Test");
        roomDto.setFloor(2);
        roomDto.setWidth(2);
        roomDto.setHeight(3);
        roomDto.setHouseId("ISEP");
        roomDto.setName("B107");
        roomDto.setLength(3);
        Mockito.doReturn(false).when(roomRepository).findRoomByIdPresent("B107");
        //Act
        ResponseEntity<String> actualResult = energyGridSettingsWebController.attachRoomToGrid(roomDto, "B building");
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }


//    @Test
//    public void seeIfAttachRoomToGridPostWorks() throws Exception {
//
//        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();
//
//        this.mockMvc.perform(post("/gridSettings/grids/B building")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\t\"name\": \"B102\",\n" +
//                        "\t\"description\": \"Reprographics Centre\",\n" +
//                        "\t\"floor\": \"1\",\n" +
//                        "\t\"width\": 7,\n" +
//                        "\t\"length\": 21,\n" +
//                        "\t\"height\": 3.5\n" +
//                        "}"))
//                .andExpect(status().isOk());
//    }

    @Test
    public void seeIfGetAllGridsDoesNotWork() throws Exception {
        String uri = "/gridSettings/grids";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void seeIfDetachRoomFromGridWorksInvalid() throws Exception {
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
    public void seeIfDeleteRoomFromGridWorks() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        Mockito.doReturn(true).when(energyGridRepository).removeRoomFromGrid(any(String.class), any(String.class));

        this.mockMvc.perform(delete("/gridSettings/grids/B building")
                .contentType(MediaType.TEXT_PLAIN)
                .content("B106"))
                .andExpect(status().isOk());
    }


//    @Test
//    public void seeIfCreateEnergyGridWorksMethod() {
//
//        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();
//        ResponseEntity<String> expectedResult = new ResponseEntity<>("Energy grid created and added to the house with success!",HttpStatus.CREATED);
//
//
//        EnergyGridDTO energyGridDTO = new EnergyGridDTO();
//        energyGridDTO.setName("B building");
//        energyGridDTO.setHouseID("7");
//        energyGridDTO.setMaxContractedPower(45);
//        energyGridDTO.setRoomDTOS(new ArrayList<>());
//        energyGridDTO.setPowerSourceDTOS(new ArrayList<>());
//        EnergyGrid energyGrid = EnergyGridMapper.dtoToObject(energyGridDTO);
//
//
//        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    public void mockTest(){
//        ResponseEntity<String> expectedResult = new ResponseEntity<>("Energy grid created and added to the house with success!",
//                HttpStatus.CREATED);
//
//        EnergyGridSettingsWebController energyGridSettingsWebController = new EnergyGridSettingsWebController();
//
//        EnergyGridDTO energyGridDTO = new EnergyGridDTO();
//        energyGridDTO.setName("EG1");
//        energyGridDTO.setMaxContractedPower(0);
//        energyGridDTO.setHouseID("01");
//        List<PowerSourceDTO> powerSourceDTOList = new ArrayList<>();
//        energyGridDTO.setPowerSourceDTOS(powerSourceDTOList);
//        List<RoomDTO> list = new ArrayList<>();
//        energyGridDTO.setRoomDTOS(list);
//        Mockito.when(mockRepo.save(EnergyGridMapper.dtoToObject(energyGridDTO))).thenReturn(EnergyGridMapper.dtoToObject(energyGridDTO));
//
//        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);
//
//        assertEquals(expectedResult, actualResult);
//    }
}
