package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class AreaTypeDTOTest {

    @Test
    public void seeIfEqualsWorks() {
        //Arrange

        AreaTypeDTO typeAreaDTO1 = new AreaTypeDTO();
        AreaTypeDTO typeAreaDTO2 = new AreaTypeDTO();
        AreaTypeDTO typeAreaDTO3 = new AreaTypeDTO();
        AreaTypeDTO typeAreaDTO4 = new AreaTypeDTO();

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

        AreaTypeDTO typeAreaDTO1 = new AreaTypeDTO();

        //Act

        int actualResult = typeAreaDTO1.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }
}