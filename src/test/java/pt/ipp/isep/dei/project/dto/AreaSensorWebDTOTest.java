package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaSensorWebDTOTest {

    @Test
    void seeIfGetAndSetIdWorks() {
        //Arrange
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setId("T1234");
        //Act
        String actualResult = areaSensorWebDTO.getId();
        //Assert
        assertEquals("T1234",actualResult);
    }

    @Test
    void seeIfGetAndSetNameWorks() {
        //Arrange
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setName("Sensor Temperature 1");
        //Act
        String actualResult = areaSensorWebDTO.getName();
        //Assert
        assertEquals("Sensor Temperature 1",actualResult);
    }

    @Test
    void seeIfGetAndSetTypeSensorWorks() {
        //Arrange
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setTypeSensor("Temperature");
        //Act
        String actualResult = areaSensorWebDTO.getType();
        //Assert
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

        AreaSensorWebDTO diffIdDTO = new AreaSensorWebDTO();
        diffIdDTO.setName("Sensor 1");
        diffIdDTO.setId("T123456");
        diffIdDTO.setTypeSensor("Rainfall");

        AreaSensorWebDTO diffNameDTO = new AreaSensorWebDTO();
        diffNameDTO.setName("Sensor 3");
        diffNameDTO.setId("T1234");
        diffNameDTO.setTypeSensor("Rainfall");

        //Act

        boolean actualResult1 = sameDTO.equals(sameDTO);
        boolean actualResult3 = sameDTO.equals(dto1);
        boolean actualResult2 = sameDTO.equals(diffDTO);
        boolean actualResult4 = sameDTO.equals(2);
        boolean actualResult5 = sameDTO.equals(null);
        boolean actualResult6 = sameDTO.equals(diffIdDTO);
        boolean actualResult7 = sameDTO.equals(diffNameDTO);

        //Assert

        assertTrue(actualResult1);
        assertTrue(actualResult3);
        assertFalse(actualResult2);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
        assertFalse(actualResult6);
        assertFalse(actualResult7);
    }
    @Test
    void seeIfHashcodeWorks() {
        //Arrange
        AreaSensorWebDTO dto1 = new AreaSensorWebDTO();
        dto1.setName("Sensor 1");
        dto1.setId("T1234");
        dto1.setTypeSensor("Temperature");
        //Assert
        assertEquals(1, dto1.hashCode());
    }

}