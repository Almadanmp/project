package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeAreaDTOTest {

    @Test
    void setID() {
        //Arrange

        TypeAreaDTO typeAreaDTO = new TypeAreaDTO();
        typeAreaDTO.setID(2L);

        //Act

        Long actualResult = typeAreaDTO.getID();

        //Assert
        assertEquals(2L, actualResult, 0.01);
    }
}