package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.model.room.Room;

import static org.junit.jupiter.api.Assertions.*;

class RoomMinimalMapperTest {

    @Test
    void seeIfDTOToObjectWorks() {
        //Arrange

        RoomDTOMinimal roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setName("RoomName");
        roomDTOMinimal.setWidth(20);
        roomDTOMinimal.setLength(21);
        roomDTOMinimal.setHeight(22);
        roomDTOMinimal.setFloor(1);


        Room expectedResult = new Room("RoomName", "", 1, 21, 22, 20, null);

        //Act

        Room actualResult = RoomMinimalMapper.dtoToObject(roomDTOMinimal);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}