package pt.ipp.isep.dei.project.controller.controllerweb;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.bridgeservices.HouseRoomService;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HouseConfigurationWebControllerTest {

    @Mock
    HouseRoomService houseRoomService;
    @Mock
    HouseRepository houseRepository;
    private RoomDTOMinimal roomDTOMinimal;
    private AddressLocalGeographicAreaIdDTO addressAndLocalDTO;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private HouseWebController webController;

    @BeforeEach
    void insertData() {
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

        this.mockMvc = MockMvcBuilders.standaloneSetup(webController).build();

    }

    @Test
    void seeIfConfigureHouseLocalChangesAddress() {
        //Arrange

        House validHouse = new House("01", new Address("rua jose peixoto", "431",
                "4245-072", "Lisboa", "Portugal"),
                new Local(21, 25, 65), 60,
                180, new ArrayList<>());

        HouseWithoutGridsDTO validDTO = HouseMapper.objectToWithoutGridsDTO(validHouse);

        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(validDTO);
        Mockito.when(houseRepository.updateHouseDTOWithoutGrids(validDTO)).thenReturn(true);


        //Act
        webController.configureLocation(addressAndLocalDTO);

        //Assert
        assertEquals(validDTO.getAddress().getStreet(), addressAndLocalDTO.getStreet());
    }

    @Test
    void seeIfConfigureHouseLocalChangesLocal() {
        //Arrange

        House validHouse = new House("01", new Address("rua jose peixoto", "431",
                "4245-072", "Lisboa", "Portugal"),
                new Local(21, 25, 65), 60,
                180, new ArrayList<>());

        HouseWithoutGridsDTO validDTO = HouseMapper.objectToWithoutGridsDTO(validHouse);

        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(validDTO);
        Mockito.when(houseRepository.updateHouseDTOWithoutGrids(validDTO)).thenReturn(true);

        //Act
        webController.configureLocation(addressAndLocalDTO);

        //Assert
        assertEquals(validDTO.getAddress().getStreet(), addressAndLocalDTO.getStreet());
    }

    @Test
    void seeIfConfigureHouseLocalAddsLinkToDTO() {
        //Arrange

        House validHouse = new House("01", new Address("rua jose peixoto", "431",
                "4245-072", "Lisboa", "Portugal"),
                new Local(21, 25, 65), 60,
                180, new ArrayList<>());

        HouseWithoutGridsDTO validDTO = HouseMapper.objectToWithoutGridsDTO(validHouse);

        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(validDTO);
        Mockito.when(houseRepository.updateHouseDTOWithoutGrids(validDTO)).thenReturn(true);

        //Act

        webController.configureLocation(addressAndLocalDTO);
        Link link = validDTO.getLink("Click here to see the House updated");

        //Assert

        assertNotNull(link);
    }


    @Test
    public void seeIfConfigureHouseLocationWorks() throws Exception {
        House validHouse = new House("01", new Address("rua carlos peixoto", "431",
                "4200-072", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());

        Gson gson = new Gson();
        String requestJson = gson.toJson(addressAndLocalDTO);
        System.out.println(requestJson);

        Mockito.when(houseRepository.getHouseWithoutGridsDTO()).thenReturn(HouseMapper.objectToWithoutGridsDTO(validHouse));
        Mockito.when(houseRepository.updateHouseDTOWithoutGrids(HouseMapper.objectToWithoutGridsDTO(validHouse))).thenReturn(true);

        mockMvc.perform(put("/house/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

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
        ResponseEntity<Object> actualResult = webController.configureLocation(addressAndLocalDTO);

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
        ResponseEntity<Object> actualResult = webController.configureLocation(addressAndLocalDTO);

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
        ResponseEntity<Object> actualResult = webController.getHouse();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}