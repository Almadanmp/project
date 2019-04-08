package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class AreaAreaSensorDTOTest {

    @Test
    void seeIfEqualsWorks() {
        //Arrange

        AreaSensorDTO areaSensorDTO1 = new AreaSensorDTO();
        AreaSensorDTO areaSensorDTO2 = new AreaSensorDTO();
        AreaSensorDTO areaSensorDTO3 = new AreaSensorDTO();
        AreaSensorDTO areaSensorDTO4 = new AreaSensorDTO();
        AreaSensorDTO areaSensorDTO5 = new AreaSensorDTO();

        areaSensorDTO1.setName("areaSensorDTO1");
        areaSensorDTO2.setName("areaSensorDTO1");
        areaSensorDTO3.setName("areaSensorDTO2");
        areaSensorDTO4.setName("areaSensorDTO1");
        areaSensorDTO5.setName("areaSensorDTO2");

        areaSensorDTO1.setId("01");
        areaSensorDTO2.setId("01");
        areaSensorDTO3.setId("02");
        areaSensorDTO4.setId("02");
        areaSensorDTO5.setId("01");


        //Assert

        assertEquals(areaSensorDTO1, areaSensorDTO1);
        assertEquals(areaSensorDTO1, areaSensorDTO2);
        assertNotEquals(areaSensorDTO1, areaSensorDTO3);
        assertNotEquals(areaSensorDTO1, areaSensorDTO4);
        assertNotEquals(areaSensorDTO1, areaSensorDTO5);
        assertNotEquals(areaSensorDTO1, 3D);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();

        //Act

        int actualResult = areaSensorDTO.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }
}