package pt.ipp.isep.dei.project.controllerweb;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import pt.ipp.isep.dei.project.repository.HouseCrudRepo;

import java.util.ArrayList;

import static org.junit.Assert.*;


@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
public class HouseConfigurationWebControllerTest {

    private RoomDTOWeb roomDTOWeb;
    private AddressAndLocalDTO addressAndLocalDTO;

    @Autowired
    private MockMvc mockMvc;
    @Mock
    HouseRoomService houseRoomService;
    @Mock
    HouseRepository houseRepository;

    @InjectMocks
    private HouseConfigurationWebController webController;

    @Before
    public void insertData() {
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
    public void seeIfCreateRoomWorks() {
        //Arrange
        Mockito.doReturn(true).when(this.houseRoomService).addRoomDTOWebToHouse(roomDTOWeb);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The room was successfully added.", HttpStatus.OK);

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
                .andExpect(status().isOk());
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
    public void seeIfIsRoomDTOWebValidWorksWhenDtoIsValid() throws Exception {
        //Act

        boolean actualResult = webController.isRoomDTOWebValid(roomDTOWeb);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    public void seeIfIsRoomDTOWebValidWorksWhenNameIsNull() throws Exception {
        //Arrange

        roomDTOWeb.setName(null);

        //Act

        boolean actualResult = webController.isRoomDTOWebValid(roomDTOWeb);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    public void seeIfIsRoomDTOWebValidWorksWhenWidthIsInvalid() throws Exception {
        //Arrange

        roomDTOWeb.setWidth(0.0);

        //Act

        boolean actualResult = webController.isRoomDTOWebValid(roomDTOWeb);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    public void seeIfIsRoomDTOWebValidWorksWhenLengthIsInvalid() throws Exception {
        //Arrange

        roomDTOWeb.setLength(0.0);

        //Act

        boolean actualResult = webController.isRoomDTOWebValid(roomDTOWeb);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    public void seeIfIsRoomDTOWebValidWorksWhenHeightIsInvalid() throws Exception {
        //Arrange

        roomDTOWeb.setHeight(0.0);

        //Act

        boolean actualResult = webController.isRoomDTOWebValid(roomDTOWeb);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    public void seeIfConfigureHouseLocationWorks() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(webController).build();

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

    @Test
    public void seeIfConfigureHouseLocationWorksFalse() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(webController).build();

        House validHouse = new House("01", new Address("rua carlos peixoto", "431",
                "4200-072", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());

        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(HouseMapper.objectToWithoutGridsDTO(validHouse));
        Mockito.when(houseRepository.updateHouseDTOWithoutGrids(HouseMapper.objectToWithoutGridsDTO(validHouse))).thenReturn(false);

        mockMvc.perform(put("/houseSettings/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"addressNOT\": {\n" +
                        "        \"streetNOT\": \"rua carlos peixoto\",\n" +
                        "        \"number\": \"431\",\n" +
                        "        \"town\": \"Porto\",\n" +
                        "        \"country\": \"Portugal\"\n" +
                        "    }}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void seeIfConfigureHouseLocalErrorWorks() {
        //Arrange

        mockMvc = MockMvcBuilders.standaloneSetup(webController).build();

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
    public void seeIfConfigureHouseLocalWorks() {
        //Arrange

        House validHouse = new House("01", new Address("rua carlos peixoto", "431",
                "4200-072", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());

        Mockito.doReturn(HouseMapper.objectToWithoutGridsDTO(validHouse)).when(houseRepository).getHouseWithoutGridsDTO();
        Mockito.doReturn(true).when(houseRepository).updateHouseDTOWithoutGrids(HouseMapper.objectToWithoutGridsDTO(validHouse));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The house has been altered.", HttpStatus.OK);

        //Act
        ResponseEntity<Object> actualResult = webController.configureHouseLocation(addressAndLocalDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


}
