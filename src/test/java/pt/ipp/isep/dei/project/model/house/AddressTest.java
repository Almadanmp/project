package pt.ipp.isep.dei.project.model.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.sensorType.SensorType;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    // Common testing artifacts for test class.

    private Address validAddress;

    @BeforeEach
    void arrangeArtifacts() {
        validAddress = new Address("Rua Dr. António Bernardino de Almeida","431",
                "4440-616", "Porto","Portugal");
    }

    @Test
    void seeIfGetStreet() {
        // Arrange
        String expectedResult = "Rua Dr. António Bernardino de Almeida";

        // Act
        String actualResult = validAddress.getStreet();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNumber(){
        // Arrange

        String expectedResult = "431";

        // Act

        String actualResult = validAddress.getNumber();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCountry(){
        // Arrange

        Address address = new Address();
        address.setCountry("Portugal");
        String expectedResult = "Portugal";

        // Act

        String actualResult = validAddress.getCountry();

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

    @Test
    void testGetStreetFromSetter() {
        // Arrange
        String expectedResult = "Rua Dr. António Bernardino Almeida";

        // Act
        validAddress.setStreet("Rua Dr. António Bernardino Almeida");
        String actualResult = validAddress.getStreet();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetStreetFromSetterFailing() {
        // Arrange
        String expectedResult = "Rua Dr. António Bernardino Almeida";

        // Act
        validAddress.setStreet("Rua Dr. António Bernardino de Almeida");
        String actualResult = validAddress.getStreet();

        // Assert
        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void testGetZipFromSetter() {
        // Arrange
        String expectedResult = "4440-616";

        // Act
        validAddress.setZip("4440-616");
        String actualResult = validAddress.getZip();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetZipFromSetterFailing() {
        // Arrange
        String expectedResult = "4440-616";

        // Act
        validAddress.setZip("5400");
        String actualResult = validAddress.getZip();

        // Assert
        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void testGetTownFromSetter() {
        // Arrange
        String expectedResult = "Chaves";

        // Act
        validAddress.setTown("Chaves");
        String actualResult = validAddress.getTown();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetTownFromSetterFailing() {
        // Arrange
        String expectedResult = "Chaves";

        // Act
        validAddress.setTown("Lisbon");
        String actualResult = validAddress.getTown();

        // Assert
        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetNumberWorks() {
        // Act

        validAddress.setNumber("300");
        String actualResult = validAddress.getNumber();

        // Assert

        assertEquals("300", actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        //Assert
        assertEquals(validAddress, validAddress);
    }

    @Test
    void seeIfEqualsFailsDifferentObject() {
        //Assert
        assertNotEquals(validAddress, new SensorType("Rain", "mm"));
    }

    @Test
    void seeIfEqualsFailsNullObject() {
        //Arrange
        Object obj = null;
        Address address = null;

        //Assert
        assertNotEquals(validAddress, obj);
        assertNotEquals(validAddress, address);
    }

    @Test
    void seeIfEqualsWorksSameParameters() {
        //Arrange
        Address sameAddress = new Address("Rua Dr. António Bernardino de Almeida","431",
                "4440-616", "Porto","Portugal");

        //Assert
        assertEquals(sameAddress, validAddress);
    }

    @Test
    void seeIfEqualsFailsWithDifferentTown() {
        //Arrange
        Address sameAddress = new Address("Rua Dr. António Bernardino de Almeida","431",
                "4440-616", "Lisbon","Portugal");

        //Assert
        assertNotEquals(sameAddress, validAddress);
    }

    @Test
    void seeIfEqualsFailsWithDifferentZip() {
        //Arrange
        Address sameAddress = new Address("Rua Dr. António Bernardino de Almeida","431",
                "4440-666", "Porto","Portugal");

        //Assert
        assertNotEquals(sameAddress, validAddress);
    }

    @Test
    void seeIfEqualsFailsWithDifferentStreet() {
        //Arrange
        Address sameAddress = new Address("Rua Dr. José Bernardino de Almeida","431",
                "4440-616", "Porto","Portugal");

        //Assert
        assertNotEquals(sameAddress, validAddress);
    }

    @Test
    public void hashCodeDummyTest() {
        //Arrange
        int expectedResult = 1;

        // Act
        int actualResult = validAddress.hashCode();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}
