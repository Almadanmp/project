package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeDTOTest {

    @Test
    void seeIfEqualsWorks() {
        //Arrange

        SensorTypeDTO typeAreaDTO1 = new SensorTypeDTO();
        SensorTypeDTO typeAreaDTO2 = new SensorTypeDTO();
        SensorTypeDTO typeAreaDTO3 = new SensorTypeDTO();
        SensorTypeDTO typeAreaDTO4 = new SensorTypeDTO();

        typeAreaDTO1.setName("sensorDTO1");
        typeAreaDTO2.setName("sensorDTO1");
        typeAreaDTO3.setName("sensorDTO2");
        typeAreaDTO4.setName("sensorDTO2");

        Double actualResult = 3D;

        //Assert

        assertEquals(typeAreaDTO1, typeAreaDTO1);
        assertEquals(typeAreaDTO1, typeAreaDTO2);
        assertNotEquals(typeAreaDTO1, typeAreaDTO3);
        assertNotEquals(typeAreaDTO1, typeAreaDTO4);
        assertNotEquals(typeAreaDTO1, actualResult);
    }

    @Test
     void seeIfHashCodeWorks() {
        //Arrange

        SensorTypeDTO typeAreaDTO1 = new SensorTypeDTO();

        //Act

        int actualResult = typeAreaDTO1.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }

    @Test
    void setUnits() {
        //Arrange

        SensorTypeDTO typeAreaDTO = new SensorTypeDTO();
        typeAreaDTO.setUnits("mm");

        //Act

        String actualResult = typeAreaDTO.getUnits();

        //Assert
        assertEquals("mm", actualResult);
    }
}