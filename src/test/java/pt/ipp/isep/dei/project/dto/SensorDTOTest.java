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

        //Assert

        assertEquals(sensorDTO1, sensorDTO1);
        assertEquals(sensorDTO1, sensorDTO2);
        assertNotEquals(sensorDTO1, sensorDTO3);
        assertNotEquals(sensorDTO1, 3D);
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