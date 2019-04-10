package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseSensorDTOTest {

    @Test
    void seeIfEqualsWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setName("Name1");

        HouseSensorDTO houseSensorDTO2 = new HouseSensorDTO();
        houseSensorDTO2.setName("Name1");

        HouseSensorDTO houseSensorDTO3 = new HouseSensorDTO();
        houseSensorDTO3.setName("Name2");

        //Act

        boolean actualResult1 = houseSensorDTO1.equals(houseSensorDTO1);
        boolean actualResult2 = houseSensorDTO1.equals(houseSensorDTO2);
        boolean actualResult3 = houseSensorDTO1.equals(houseSensorDTO3);
        boolean actualResult4 = houseSensorDTO1.equals(4D);

        //Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();

        //Assert
        assertEquals(1, houseSensorDTO1.hashCode());
    }
}