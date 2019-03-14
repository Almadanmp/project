package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class SensorDTOTest {

    @Test
    public void seeIfEqualsWorks() {
        //Arrange

        SensorDTO sensorDTO1 = new SensorDTO();
        SensorDTO sensorDTO2 = new SensorDTO();
        SensorDTO sensorDTO3 = new SensorDTO();
        SensorDTO sensorDTO4 = new SensorDTO();
        SensorDTO sensorDTO5 = new SensorDTO();

        sensorDTO1.setName("sensorDTO1");
        sensorDTO2.setName("sensorDTO1");
        sensorDTO3.setName("sensorDTO2");
        sensorDTO4.setName("sensorDTO1");
        sensorDTO5.setName("sensorDTO2");

        sensorDTO1.setId("01");
        sensorDTO2.setId("01");
        sensorDTO3.setId("02");
        sensorDTO4.setId("02");
        sensorDTO5.setId("01");


        //Assert

        assertEquals(sensorDTO1, sensorDTO1);
        assertEquals(sensorDTO1, sensorDTO2);
        assertNotEquals(sensorDTO1, sensorDTO3);
        assertNotEquals(sensorDTO1, sensorDTO4);
        assertNotEquals(sensorDTO1, sensorDTO5);
        assertNotEquals(sensorDTO1, 3D);
    }

    @Test
    public void seeIfHashCodeWorks() {
        //Arrange

        SensorDTO sensorDTO = new SensorDTO();

        //Act

        int actualResult = sensorDTO.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }
}