package pt.ipp.isep.dei.project.dto;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SensorDTOTest {

    @Test
    public void testEquals() {
        //Arrange

        SensorDTO sensorDTO1 = new SensorDTO();
        SensorDTO sensorDTO2 = new SensorDTO();
        SensorDTO sensorDTO3 = new SensorDTO();

        sensorDTO1.setName("sensorDTO1");
        sensorDTO2.setName("sensorDTO1");
        sensorDTO3.setName("sensorDTO2");

        sensorDTO1.setId("01");
        sensorDTO2.setId("01");
        sensorDTO3.setId("02");

        //Act

        boolean actualResult1 = sensorDTO1.equals(sensorDTO1);
        boolean actualResult2 = sensorDTO1.equals(sensorDTO2);
        boolean actualResult3 = sensorDTO1.equals(sensorDTO3);
        boolean actualResult4 = sensorDTO1.equals(2D);

        //Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    public void testHashCode() {
        //Arrange

        SensorDTO sensorDTO = new SensorDTO();

        //Act

        int actualResult = sensorDTO.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }
}