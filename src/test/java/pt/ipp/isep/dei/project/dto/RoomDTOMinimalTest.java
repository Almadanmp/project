package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomDTOMinimalTest {

    private RoomDTOMinimal roomDTOMinimal;

    @BeforeEach
    void arrangeArtifacts() {
        roomDTOMinimal = new RoomDTOMinimal();
        roomDTOMinimal.setName("RoomName");
        roomDTOMinimal.setFloor(1);
        roomDTOMinimal.setHeight(20);
        roomDTOMinimal.setLength(21);
        roomDTOMinimal.setWidth(22);
    }

    @Test
    void seeIfIsNameValidWorksInCaseOfInvalid() {
        //Arrange

        roomDTOMinimal.setName(null);

        //Act

        boolean actualResult = roomDTOMinimal.isNameValid();

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfIsNameValidWorks() {
        //Act

        boolean actualResult = roomDTOMinimal.isNameValid();

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAreDimensionAreValidWorks() {
        //Act

        boolean actualResult = roomDTOMinimal.areDimensionsValid();

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAreDimensionAreValidWorksWhenHeightIsNot() {
        //Arrange

        roomDTOMinimal.setHeight(0D);

        //Act

        boolean actualResult = roomDTOMinimal.areDimensionsValid();

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAreDimensionAreValidWorksWhenLengthIsNot() {
        //Arrange

        roomDTOMinimal.setLength(0D);

        //Act

        boolean actualResult = roomDTOMinimal.areDimensionsValid();

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAreDimensionAreValidWorksWhenWidthIsNot() {
        //Arrange

        roomDTOMinimal.setWidth(0D);

        //Act

        boolean actualResult = roomDTOMinimal.areDimensionsValid();

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetNameWorks() {
        //Act

        String actualResult = roomDTOMinimal.getName();

        //Assert

        assertEquals("RoomName", actualResult);
    }

    @Test
    void seeIfGetFloorWorks() {
        //Act

        int actualResult = roomDTOMinimal.getFloor();

        //Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfGetHeightWorks() {
        //Act

        double actualResult = roomDTOMinimal.getHeight();

        //Assert

        assertEquals(20, actualResult, 0.01);
    }

    @Test
    void seeIfGetLengthWorks() {
        //Act

        double actualResult = roomDTOMinimal.getLength();

        //Assert

        assertEquals(21, actualResult, 0.01);
    }

    @Test
    void seeIfGetWidthWorks() {
        //Act

        double actualResult = roomDTOMinimal.getWidth();

        //Assert

        assertEquals(22, actualResult, 0.01);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange

        RoomDTOMinimal sameDTO = new RoomDTOMinimal();
        sameDTO.setName("RoomName");

        RoomDTOMinimal diffDTO = new RoomDTOMinimal();
        diffDTO.setName("DiffName");

        //Act

        boolean actualResult1 = roomDTOMinimal.equals(sameDTO);
        boolean actualResult2 = roomDTOMinimal.equals(roomDTOMinimal);
        boolean actualResult3 = roomDTOMinimal.equals(diffDTO);
        boolean actualResult4 = roomDTOMinimal.equals(2);
        boolean actualResult5 = roomDTOMinimal.equals(null);

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
        assertEquals(1, roomDTOMinimal.hashCode());
    }
}