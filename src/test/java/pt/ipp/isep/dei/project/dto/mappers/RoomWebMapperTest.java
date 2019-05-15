package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.model.room.Room;

import static org.junit.jupiter.api.Assertions.*;

class RoomWebMapperTest {

    @Test
    void seeIfDTOToObjectWorks() {
        //Arrange

        RoomDTOWeb roomDTOWeb = new RoomDTOWeb();
        roomDTOWeb.setName("RoomName");
        roomDTOWeb.setWidth(20);
        roomDTOWeb.setLength(21);
        roomDTOWeb.setHeight(22);
        roomDTOWeb.setFloor(1);


        Room expectedResult = new Room("RoomName", "", 1, 21, 22, 20, null);

        //Act

        Room actualResult = RoomWebMapper.dtoToObject(roomDTOWeb);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}