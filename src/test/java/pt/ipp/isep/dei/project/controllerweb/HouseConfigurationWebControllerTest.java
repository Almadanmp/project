package pt.ipp.isep.dei.project.controllerweb;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HouseConfigurationWebController.class)
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
public class HouseConfigurationWebControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private Room room;
    private RoomDTOWeb roomDTOWeb;
    @Mock
    HouseRepository houseRepository;
    @Mock
    RoomRepository roomRepository;
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

        room = new Room("Name", "", 1, 2D, 4D, 1D, "01");

        this.mockMvc = MockMvcBuilders.standaloneSetup(webController).build();
    }

    @Test
    public void seeIfCreateRoomWorks() {
        //Arrange
        House validHouse = new House("01", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);

        Mockito.when(houseRepository.getHouseId()).thenReturn(validHouse.getId());
        Mockito.when(roomRepository.addRoomToCrudRepository(room)).thenReturn(true);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The room was successfully added.", HttpStatus.OK);

        //Act
        ResponseEntity<String> actualResult = webController.createRoom(roomDTOWeb);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfCreateRoomWorksIfRoomAlreadyExists() {
        //Arrange
        House validHouse = new House("01", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);

        List<Room> roomList = new ArrayList<>();
        roomList.add(room);

        Mockito.when(houseRepository.getHouseId()).thenReturn(validHouse.getId());
        Mockito.when(roomRepository.addRoomToCrudRepository(room)).thenReturn(false);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The room you are trying to create already exists.", HttpStatus.CONFLICT);

        //Act
        ResponseEntity<String> actualResult = webController.createRoom(roomDTOWeb);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfCreateRoomWorksWithMvc() throws Exception {
        //Arrange
        House validHouse = new House("01", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);

        Mockito.when(houseRepository.getHouseId()).thenReturn(validHouse.getId());
        Mockito.when(roomRepository.addRoomToCrudRepository(room)).thenReturn(true);

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
        House validHouse = new House("01", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);

        List<Room> roomList = new ArrayList<>();
        roomList.add(room);

        Mockito.when(houseRepository.getHouseId()).thenReturn(validHouse.getId());
        Mockito.when(roomRepository.addRoomToCrudRepository(room)).thenReturn(false);

        Gson gson = new Gson();
        String requestJson = gson.toJson(roomDTOWeb);

        //Act

        this.mockMvc.perform(post("/houseSettings/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isConflict());
    }
}