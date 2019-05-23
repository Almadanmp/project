package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaSensorWebDTOTest {

    @Test
    void seeIfGetAndSetIdWorks() {
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setId("T1234");
        String actualResult = areaSensorWebDTO.getId();
        assertEquals("T1234",actualResult);
    }

    @Test
    void seeIfGetAndSetNameWorks() {
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setName("Sensor Temperature 1");
        String actualResult = areaSensorWebDTO.getName();
        assertEquals("Sensor Temperature 1",actualResult);
    }

    @Test
    void seeIfGetAndSetTypeSensorWorks() {
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setTypeSensor("Temperature");
        String actualResult = areaSensorWebDTO.getType();
        assertEquals("Temperature",actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange
        AreaSensorWebDTO dto1 = new AreaSensorWebDTO();
        dto1.setName("Sensor 1");
        dto1.setId("T1234");
        dto1.setTypeSensor("Temperature");

        AreaSensorWebDTO sameDTO = new AreaSensorWebDTO();
        sameDTO.setName("Sensor 1");
        sameDTO.setId("T1234");
        sameDTO.setTypeSensor("Temperature");

        AreaSensorWebDTO diffDTO = new AreaSensorWebDTO();
        diffDTO.setName("Sensor2");
        diffDTO.setId("T123");
        diffDTO.setTypeSensor("Rainfall");

        //Act

        boolean actualResult1 = sameDTO.equals(sameDTO);
        boolean actualResult3 = sameDTO.equals(dto1);
        boolean actualResult2 = sameDTO.equals(diffDTO);
        boolean actualResult4 = sameDTO.equals(2);
        boolean actualResult5 = sameDTO.equals(null);

        //Assert

        assertTrue(actualResult1);
        assertTrue(actualResult3);
        assertFalse(actualResult2);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }
    @Test
        //Assert

    void seeIfHashcodeWorks() {
        AreaSensorWebDTO dto1 = new AreaSensorWebDTO();
        dto1.setName("Sensor 1");
        dto1.setId("T1234");
        dto1.setTypeSensor("Temperature");
        assertEquals(1, dto1.hashCode());
    }

}