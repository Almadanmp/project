package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import static org.junit.Assert.*;

class HouseRoomServiceTest {

    private RoomDTOWeb roomDTOWeb;
    private Room room;

    @Mock
    HouseRepository houseRepository;
    @Mock
    RoomRepository roomRepository;
    @InjectMocks
    private HouseRoomService service;

    @BeforeEach
    void insertData() {
        roomDTOWeb = new RoomDTOWeb();
        roomDTOWeb.setName("Name");
        roomDTOWeb.setWidth(2D);
        roomDTOWeb.setLength(4D);
        roomDTOWeb.setHeight(1D);
        roomDTOWeb.setFloor(1);

        room = new Room("Name", "", 1, 2D, 4D, 1D, "01");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void seeIfAddRoomDTOWebToHouseWorks() {
        //Arrange

        Mockito.doReturn("01").when(this.houseRepository).getHouseId();
        Mockito.doReturn(true).when(this.roomRepository).addRoomToCrudRepository(room);

        //Act

        boolean actualResult = service.addRoomDTOWebToHouse(roomDTOWeb);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomDTOWebToHouseWorksWhenRoomExists() {
        //Arrange

        Mockito.doReturn("01").when(this.houseRepository).getHouseId();
        Mockito.doReturn(false).when(this.roomRepository).addRoomToCrudRepository(room);

        //Act

        boolean actualResult = service.addRoomDTOWebToHouse(roomDTOWeb);

        //Assert
        assertFalse(actualResult);
    }
}