package pt.ipp.isep.dei.project.controller.controllerweb;

import com.google.gson.Gson;
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
import pt.ipp.isep.dei.project.dto.AddressAndLocalDTO;
import pt.ipp.isep.dei.project.dto.AddressDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.bridgeservices.HouseRoomService;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
class HouseConfigurationWebControllerTest {

    @Mock
    HouseRoomService houseRoomService;
    @Mock
    HouseRepository houseRepository;
    @Mock
    RoomRepository roomRepository;
    private RoomDTOWeb roomDTOWeb;
    private AddressAndLocalDTO addressAndLocalDTO;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private HouseConfigurationWebController webController;

    @BeforeEach
    void insertData() {
        MockitoAnnotations.initMocks(this);
        roomDTOWeb = new RoomDTOWeb();
        roomDTOWeb.setName("Name");
        roomDTOWeb.setWidth(2D);
        roomDTOWeb.setLength(4D);
        roomDTOWeb.setHeight(1D);
        roomDTOWeb.setFloor(1);

        LocalDTO localDTO = new LocalDTO();

        localDTO = new LocalDTO();
        localDTO.setAltitude(20);
        localDTO.setLongitude(20);
        localDTO.setLatitude(20);

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setNumber("431");
        addressDTO.setCountry("Portugal");
        addressDTO.setZip("4200-072");
        addressDTO.setTown("Porto");
        addressDTO.setStreet("rua carlos peixoto");

        addressAndLocalDTO = new AddressAndLocalDTO();
        addressAndLocalDTO.setLocal(localDTO);
        addressAndLocalDTO.setAddress(addressDTO);

        this.mockMvc = MockMvcBuilders.standaloneSetup(webController).build();

    }

    @Test
    public void seeIfGetHouseRoomsWorks() {
        //Arrange
        List<RoomDTOWeb> roomDTOWebs = new ArrayList<>();
        roomDTOWebs.add(roomDTOWeb);

        Mockito.doReturn(roomDTOWebs).when(this.roomRepository).getAllRoomWebDTOs();

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(roomDTOWebs, HttpStatus.OK);

        //Act
        ResponseEntity<Object> actualResult = webController.getHouseRooms();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfCreateRoomWorks() {
        //Arrange
        Mockito.doReturn(true).when(this.houseRoomService).addRoomDTOWebToHouse(roomDTOWeb);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The room was successfully added.", HttpStatus.CREATED);

        //Act
        ResponseEntity<String> actualResult = webController.createRoom(roomDTOWeb);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfCreateRoomWorksIfRoomAlreadyExists() {
        //Arrange
        Mockito.doReturn(false).when(this.houseRoomService).addRoomDTOWebToHouse(roomDTOWeb);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The room you are trying to create already exists.", HttpStatus.CONFLICT);

        //Act
        ResponseEntity<String> actualResult = webController.createRoom(roomDTOWeb);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfCreateRoomWorksWithMvc() throws Exception {
        //Arrange
        Mockito.doReturn(true).when(this.houseRoomService).addRoomDTOWebToHouse(roomDTOWeb);

        Gson gson = new Gson();
        String requestJson = gson.toJson(roomDTOWeb);

        //Act

        this.mockMvc.perform(post("/houseSettings/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void seeIfCreateRoomWorksWhenRoomExistsWithMvc() throws Exception {
        //Arrange

        Mockito.doReturn(false).when(this.houseRoomService).addRoomDTOWebToHouse(roomDTOWeb);

        Gson gson = new Gson();
        String requestJson = gson.toJson(roomDTOWeb);

        //Act

        this.mockMvc.perform(post("/houseSettings/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void seeIfCreateRoomWorksWithMvcWhenRoomDTODimensionsAreInvalid() throws Exception {
        //Arrange

        RoomDTOWeb invalidDTO = new RoomDTOWeb();
        invalidDTO.setHeight(2D);
        invalidDTO.setLength(0.0D);
        invalidDTO.setWidth(4D);
        invalidDTO.setName("InvalidDimensionsRoom");
        invalidDTO.setFloor(1);

        //Arrange

        Gson gson = new Gson();
        String requestJson = gson.toJson(invalidDTO);

        //Act

        this.mockMvc.perform(post("/houseSettings/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void seeIfCreateRoomWorksWithMvcWhenRoomNameIsInvalid() throws Exception {
        //Arrange

        RoomDTOWeb invalidDTO = new RoomDTOWeb();
        invalidDTO.setHeight(2D);
        invalidDTO.setLength(5D);
        invalidDTO.setWidth(4D);
        invalidDTO.setName(null);
        invalidDTO.setFloor(1);

        //Arrange

        Gson gson = new Gson();
        String requestJson = gson.toJson(invalidDTO);

        //Act

        this.mockMvc.perform(post("/houseSettings/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void seeIfGetHouseRoomsWorksWithMvc() throws Exception {
        //Arrange

        RoomDTOWeb roomDTOWeb2 = new RoomDTOWeb();
        roomDTOWeb2.setHeight(2D);
        roomDTOWeb2.setLength(4D);
        roomDTOWeb2.setWidth(4D);
        roomDTOWeb2.setName("roomDTOWeb2");
        roomDTOWeb2.setFloor(1);

        List<RoomDTOWeb> expectedResult = new ArrayList<>();
        expectedResult.add(roomDTOWeb);
        expectedResult.add(roomDTOWeb2);

        Mockito.doReturn(expectedResult).when(this.roomRepository).getAllRoomWebDTOs();

        //Arrange

        Gson gson = new Gson();
        String requestJson = gson.toJson(expectedResult);

        //Act

        this.mockMvc.perform(get("/houseSettings/houseRooms").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void seeIfConfigureHouseLocationWorks() throws Exception {
        House validHouse = new House("01", new Address("rua carlos peixoto", "431",
                "4200-072", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());

        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(HouseMapper.objectToWithoutGridsDTO(validHouse));
        Mockito.when(houseRepository.updateHouseDTOWithoutGrids(HouseMapper.objectToWithoutGridsDTO(validHouse))).thenReturn(true);

        mockMvc.perform(put("/houseSettings/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"address\": {\n" +
                        "        \"street\": \"rua carlos peixoto\",\n" +
                        "        \"number\": \"431\",\n" +
                        "        \"zip\": \"4200-072\",\n" +
                        "        \"town\": \"Porto\",\n" +
                        "        \"country\": \"Portugal\"\n" +
                        "    },\n" +
                        "    \"local\": {\n" +
                        "        \"latitude\": 20,\n" +
                        "        \"longitude\": 20,\n" +
                        "        \"altitude\": 20\n" +
                        "    }}"))
                .andExpect(status().isOk());
    }

//    @Test
//    public void seeIfConfigureHouseLocationWorksFalse() throws Exception {
//        House validHouse = new House("01", new Address("rua carlos peixoto", "431",
//                "4200-072", "Porto", "Portugal"),
//                new Local(20, 20, 20), 60,
//                180, new ArrayList<>());
//
//        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(HouseMapper.objectToWithoutGridsDTO(validHouse));
//        Mockito.when(houseRepository.updateHouseDTOWithoutGrids(HouseMapper.objectToWithoutGridsDTO(validHouse))).thenReturn(false);
//
//        mockMvc.perform(put("/houseSettings/house")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"addressNOT\": {\n" +
//                        "        \"streetNOT\": \"rua carlos peixoto\",\n" +
//                        "        \"number\": \"431\",\n" +
//                        "        \"town\": \"Porto\",\n" +
//                        "        \"country\": \"Portugal\"\n" +
//                        "    }}"))
//                .andExpect(status().isBadRequest());
//    }

    @Test
    public void seeIfConfigureHouseLocalErrorWorks() {
        //Arrange

        House validHouse = new House("01", new Address("rua carlos peixoto", "431",
                "4200-072", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());

        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(HouseMapper.objectToWithoutGridsDTO(validHouse));
        Mockito.when(houseRepository.updateHouseDTOWithoutGrids(HouseMapper.objectToWithoutGridsDTO(validHouse))).thenReturn(false);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The house hasn't been altered. Please try again", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = webController.configureHouseLocation(addressAndLocalDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
     void seeIfConfigureHouseLocalWorks() {
        //Arrange

        House validHouse = new House("01", new Address("rua carlos peixoto", "431",
                "4200-072", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());

        Mockito.doReturn(HouseMapper.objectToWithoutGridsDTO(validHouse)).when(houseRepository).getHouseWithoutGridsDTO();
        Mockito.doReturn(true).when(houseRepository).updateHouseDTOWithoutGrids(HouseMapper.objectToWithoutGridsDTO(validHouse));

        //Act
        ResponseEntity<Object> actualResult = webController.configureHouseLocation(addressAndLocalDTO);

        //Assert
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    void seeIfRetrieveHouseWorks() {
        //Arrange

        House validHouse = new House("01", new Address("rua carlos peixoto", "431",
                "4200-072", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());

        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(HouseMapper.objectToWithoutGridsDTO(validHouse));

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(validHouse, HttpStatus.OK);

        //Act
        ResponseEntity<Object> actualResult = webController.retrieveHouse();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}