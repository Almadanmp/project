package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeographicAreaWebDTOTest {

    @Test
    void seeIfGetAndSetIdWorks() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        geographicAreaWebDTO.setId(4L);
        //Act
        Long actualResult = geographicAreaWebDTO.getId();
        assertEquals(4L, actualResult, 0.001);
    }

    @Test
    void seeIfGetAndSetNameWorks() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        geographicAreaWebDTO.setName("Porto");
        //Act
        String actualResult = geographicAreaWebDTO.getName();
        //Assert
        assertEquals("Porto", actualResult);
    }

    @Test
    void seeIfGetAndSetDescriptionWorks() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        geographicAreaWebDTO.setDescription("Porto District");
        //Act
        String actualResult = geographicAreaWebDTO.getDescription();
        //Assert
        assertEquals("Porto District", actualResult);
    }

    @Test
    void seeIfSetAndGetSensorDtoListWorks() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        List<AreaSensorWebDTO> list = new ArrayList<>();
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        list.add(areaSensorWebDTO);
        geographicAreaWebDTO.setSensorDTOList(list);
        //Act
        List<AreaSensorWebDTO> actualResult = geographicAreaWebDTO.getSensorDTOs();
        //Assert
        assertEquals(list, actualResult);
    }

    @Test
    void seeIfGetAndSetTypeAreaWorks() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        geographicAreaWebDTO.setTypeArea("District");
        //Act
        String actualResult = geographicAreaWebDTO.getTypeArea();
        //Assert
        assertEquals("District", actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange
        GeographicAreaWebDTO dto1 = new GeographicAreaWebDTO();
        dto1.setName("Porto");
        dto1.setId(4L);
        dto1.setTypeArea("District");

        GeographicAreaWebDTO sameDTO = new GeographicAreaWebDTO();
        sameDTO.setName("Porto");
        sameDTO.setId(4L);
        sameDTO.setTypeArea("District");


        GeographicAreaWebDTO diffDTO = new GeographicAreaWebDTO();
        diffDTO.setName("Gaia");
        diffDTO.setId(5L);
        diffDTO.setTypeArea("City");

        GeographicAreaWebDTO diffTypeDTO = new GeographicAreaWebDTO();
        diffTypeDTO.setName("Porto");
        diffTypeDTO.setId(5L);
        diffTypeDTO.setTypeArea("City");

        GeographicAreaWebDTO diffNameDTO = new GeographicAreaWebDTO();
        diffNameDTO.setName("Chaves");
        diffNameDTO.setId(5L);
        diffNameDTO.setTypeArea("District");


        //Act

        boolean actualResult1 = sameDTO.equals(sameDTO);
        boolean actualResult3 = sameDTO.equals(dto1);
        boolean actualResult2 = sameDTO.equals(diffDTO);
        boolean actualResult4 = sameDTO.equals(2);
        boolean actualResult5 = sameDTO.equals(null);
        boolean actualResult6 = sameDTO.equals(diffTypeDTO);
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
        GeographicAreaWebDTO dto1 = new GeographicAreaWebDTO();
        //Assert
        assertEquals(1, dto1.hashCode());
    }

    @Test
    void seeIfAddSensorDoesntWork() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        List<AreaSensorWebDTO> list = new ArrayList<>();
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setName("Sensor 1");
        areaSensorWebDTO.setId("T1234");
        list.add(areaSensorWebDTO);
        geographicAreaWebDTO.setSensorDTOList(list);
        //Act
        boolean actualResult = geographicAreaWebDTO.addSensor(areaSensorWebDTO);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfAddSensorWorks() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setName("Sensor 1");
        areaSensorWebDTO.setId("T1234");
        //Act
        boolean actualResult = geographicAreaWebDTO.addSensor(areaSensorWebDTO);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfRemoveSensorWorks() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        List<AreaSensorWebDTO> list = new ArrayList<>();
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setName("Sensor 1");
        areaSensorWebDTO.setId("T1234");
        list.add(areaSensorWebDTO);
        geographicAreaWebDTO.setSensorDTOList(list);
        //Act
        boolean actualResult = geographicAreaWebDTO.removeSensor(areaSensorWebDTO.getId());
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfRemoveSensorDoesntWork() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setName("Sensor 1");
        areaSensorWebDTO.setId("T1234");
        //Act
        boolean actualResult = geographicAreaWebDTO.removeSensor(areaSensorWebDTO.getId());
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfRemoveSensorFails2() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        List<AreaSensorWebDTO> list = new ArrayList<>();
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setName("Sensor 1");
        areaSensorWebDTO.setId("T12345");
        list.add(areaSensorWebDTO);
        geographicAreaWebDTO.setSensorDTOList(list);
        //Act
        boolean actualResult = geographicAreaWebDTO.removeSensor(areaSensorWebDTO.getId());
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfRemoveSensorFails3() {
        //Arrange
        GeographicAreaWebDTO geographicAreaWebDTO = new GeographicAreaWebDTO();
        List<AreaSensorWebDTO> list = new ArrayList<>();
        AreaSensorWebDTO areaSensorWebDTO = new AreaSensorWebDTO();
        areaSensorWebDTO.setName("Sensor 1");
        areaSensorWebDTO.setId("T12345");
        list.add(areaSensorWebDTO);
        geographicAreaWebDTO.setSensorDTOList(list);
        //Act
        boolean actualResult = geographicAreaWebDTO.removeSensor("T123457");
        //Assert
        assertFalse(actualResult);
    }

}