package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import static org.junit.Assert.*;

class HouseRoomServiceTest {

    private RoomDTOMinimal roomDTOMinimal;
    private Room room;

    @Mock
    HouseRepository houseRepository;
    @Mock
    RoomRepository roomRepository;
    @InjectMocks
    private HouseRoomService service;

    @BeforeEach
    void insertData() {
        roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setName("Name");
        roomDTOMinimal.setWidth(2D);
        roomDTOMinimal.setLength(4D);
        roomDTOMinimal.setHeight(1D);
        roomDTOMinimal.setFloor(1);

        room = new Room("Name", "", 1, 2D, 4D, 1D, "01");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void seeIfAddRoomDTOMinimalToHouseWorks() {
        //Arrange

        Mockito.doReturn("01").when(this.houseRepository).getHouseId();
        Mockito.doReturn(true).when(this.roomRepository).addRoomToCrudRepository(room);

        //Act

        boolean actualResult = service.addMinimalRoomDTOToHouse(roomDTOMinimal);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomDTOMinimalToHouseWorksWhenRoomExists() {
        //Arrange

        Mockito.doReturn("01").when(this.houseRepository).getHouseId();
        Mockito.doReturn(false).when(this.roomRepository).addRoomToCrudRepository(room);

        //Act

        boolean actualResult = service.addMinimalRoomDTOToHouse(roomDTOMinimal);

        //Assert
        assertFalse(actualResult);
    }
}