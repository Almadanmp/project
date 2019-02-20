package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.testng.Assert.*;

public class AddressTest {

    // Common testing artifacts for test class.

    private Address validAddress;

    @BeforeEach
    void arrangeArtifacts(){
        validAddress = new Address("Rua Dr. António Bernardino de Almeida",
                "4440-616", "Porto");
    }

    @Test
    void testGetStreet() {
        // Arrange

        String expectedResult = "Rua Dr. António Bernardino de Almeida";

        // Act

        String actualResult = validAddress.getStreet();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetZip() {
        // Arrange

        String expectedResult = "4440-616";

        // Act

        String actualResult = validAddress.getZip();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetTown() {

        // Arrange

        String expectedResult = "Porto";

        // Act

        String actualResult = validAddress.getTown();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
