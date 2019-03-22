package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TypeAreaDTOTest {

    @Test
    public void seeIfEqualsWorks() {
        //Arrange

        TypeAreaDTO typeAreaDTO1 = new TypeAreaDTO();
        TypeAreaDTO typeAreaDTO2 = new TypeAreaDTO();
        TypeAreaDTO typeAreaDTO3 = new TypeAreaDTO();
        TypeAreaDTO typeAreaDTO4 = new TypeAreaDTO();

        typeAreaDTO1.setName("sensorDTO1");
        typeAreaDTO2.setName("sensorDTO1");
        typeAreaDTO3.setName("sensorDTO2");
        typeAreaDTO4.setName("sensorDTO2");

        //Assert

        assertEquals(typeAreaDTO1, typeAreaDTO1);
        assertEquals(typeAreaDTO1, typeAreaDTO2);
        assertNotEquals(typeAreaDTO1, typeAreaDTO3);
        assertNotEquals(typeAreaDTO1, typeAreaDTO4);
        assertNotEquals(typeAreaDTO1, 3D);
    }

    @Test
    public void seeIfHashCodeWorks() {
        //Arrange

        TypeAreaDTO typeAreaDTO1 = new TypeAreaDTO();

        //Act

        int actualResult = typeAreaDTO1.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }
}