package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomDTOWebTest {

    private RoomDTOWeb roomDTOWeb;

    @BeforeEach
    void arrangeArtifacts() {
        roomDTOWeb = new RoomDTOWeb();
        roomDTOWeb.setName("RoomName");
        roomDTOWeb.setFloor(1);
        roomDTOWeb.setHeight(20);
        roomDTOWeb.setLength(21);
        roomDTOWeb.setWidth(22);
    }

    @Test
    void seeIfGetNameWorks() {
        //Act

        String actualResult = roomDTOWeb.getName();

        //Assert

        assertEquals("RoomName", actualResult);
    }

    @Test
    void seeIfGetFloorWorks() {
        //Act

        int actualResult = roomDTOWeb.getFloor();

        //Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfGetHeightWorks() {
        //Act

        double actualResult = roomDTOWeb.getHeight();

        //Assert

        assertEquals(20, actualResult, 0.01);
    }

    @Test
    void seeIfGetLengthWorks() {
        //Act

        double actualResult = roomDTOWeb.getLength();

        //Assert

        assertEquals(21, actualResult, 0.01);
    }

    @Test
    void seeIfGetWidthWorks() {
        //Act

        double actualResult = roomDTOWeb.getWidth();

        //Assert

        assertEquals(22, actualResult, 0.01);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange

        RoomDTOWeb sameDTO = new RoomDTOWeb();
        sameDTO.setName("RoomName");

        RoomDTOWeb diffDTO = new RoomDTOWeb();
        diffDTO.setName("DiffName");

        //Act

        boolean actualResult1 = roomDTOWeb.equals(sameDTO);
        boolean actualResult2 = roomDTOWeb.equals(roomDTOWeb);
        boolean actualResult3 = roomDTOWeb.equals(diffDTO);
        boolean actualResult4 = roomDTOWeb.equals(2);
        boolean actualResult5 = roomDTOWeb.equals(null);

        //Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Assert
        assertEquals(1, roomDTOWeb.hashCode());
    }
}