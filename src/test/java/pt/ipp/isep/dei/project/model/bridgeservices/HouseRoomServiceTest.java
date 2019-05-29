package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.mappers.RoomMinimalMapper;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import static org.junit.jupiter.api.Assertions.*;

class HouseRoomServiceTest {

    @Mock
    HouseRepository houseRepository;
    @Mock
    RoomRepository roomRepository;
    private RoomDTOMinimal roomDTOMinimal;
    private Room room;
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
        Room convertedRoom = RoomMinimalMapper.dtoToObject(roomDTOMinimal);
        assertNull(convertedRoom.getHouseID());

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

    @Test
    void seeIfUpdateHouseIDWorks() {
        //Arrange

        Mockito.doReturn("01").when(this.houseRepository).getHouseId();
        Room roomToUpdate = RoomMinimalMapper.dtoToObject(roomDTOMinimal);

        // Act

        Room updatedRoom = service.updateHouseID(roomToUpdate);
        String actualResult = updatedRoom.getHouseID();

        // Assert

        assertEquals("01", actualResult);
    }
}