package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;
import static org.testng.Assert.*;

public class AddressTest {

    @Test
    void testGetStreet() {
        //Arrange
        Address porto = new Address("Rua da Vida", "4440-616", "Valongo");
        //Act
        String expectedResult = "Rua da Vida";
        String actualResult = porto.getStreet();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetZip() {
        //Arrange
        Address porto = new Address("Rua da Vida", "4440-616", "Valongo");
        //Act
        String expectedResult = "4440-616";
        String actualResult = porto.getZip();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetTown() {
        //Arrange
        Address porto = new Address("Rua da Vida", "4440-616", "Valongo");
        //Act
        String expectedResult = "Valongo";
        String actualResult = porto.getTown();
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}
