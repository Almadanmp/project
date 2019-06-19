package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomSensorDTOMinimalTest {

    @Test
    void seeIfSetUnitWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();
        houseSensorDTO1.setUnits("Unit");

        //Act

        String actualResult1 = houseSensorDTO1.getUnits();

        //Assert
        assertEquals("Unit", actualResult1);
    }

    @Test
    void seeIfSetRoomIdWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();
        houseSensorDTO1.setRoomID("Id");

        //Act

        String actualResult1 = houseSensorDTO1.getRoomID();

        //Assert
        assertEquals("Id", actualResult1);
    }

    @Test
    void seeIfSetTypeSensorWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();
        houseSensorDTO1.setTypeSensor("Type");

        //Act

        String actualResult1 = houseSensorDTO1.getType();

        //Assert
        assertEquals("Type", actualResult1);
    }

    @Test
    void seeIfSetActiveWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();
        houseSensorDTO1.setActive(true);

        //Act

        boolean actualResult1 = houseSensorDTO1.getActive();

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfSetInactiveWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();
        houseSensorDTO1.setActive(false);

        //Act

        boolean actualResult1 = houseSensorDTO1.getActive();

        //Assert

        assertFalse(actualResult1);
    }


    @Test
    void seeIfEqualsWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();
        houseSensorDTO1.setName("Name1");

        RoomSensorDTOMinimal houseSensorDTO2 = new RoomSensorDTOMinimal();
        houseSensorDTO2.setName("Name1");

        RoomSensorDTOMinimal houseSensorDTO3 = new RoomSensorDTOMinimal();
        houseSensorDTO3.setName("Name2");

        //Act

        boolean actualResult1 = houseSensorDTO1.equals(houseSensorDTO1);
        boolean actualResult2 = houseSensorDTO1.equals(houseSensorDTO2);
        boolean actualResult3 = houseSensorDTO1.equals(houseSensorDTO3);
        boolean actualResult4 = houseSensorDTO1.equals(4D);

        //Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();

        //Assert
        assertEquals(1, houseSensorDTO1.hashCode());
    }

    @Test
    void seeIfSetIdWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();
        houseSensorDTO1.setSensorId("Id");

        //Act

        String actualResult1 = houseSensorDTO1.getSensorID();

        //Assert
        assertEquals("Id", actualResult1);
    }

    @Test
    void seeIfSetDateWorks() {
        //Arrange

        RoomSensorDTOMinimal houseSensorDTO1 = new RoomSensorDTOMinimal();
        houseSensorDTO1.setDateStartedFunctioning("2/2/2018");

        //Act

        String actualResult1 = houseSensorDTO1.getDateStartedFunctioning();

        //Assert
        assertEquals("2/2/2018", actualResult1);
    }
}