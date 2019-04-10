package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class KelvinTest {

    @Test
    void seeIfToDefaultTemperatureKelvinToKelvinWorks(){
        // Arrange

        TemperatureUnit unit = new Kelvin();
        double valueToConvert = 10;
        String defaultUnit = "Kelvin";
        double expectedResult = 10;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToDefaultTemperatureKelvinToCelsiusWorks(){
        // Arrange

        TemperatureUnit unit = new Kelvin();
        double valueToConvert = 10;
        String defaultUnit = "Celsius";
        double expectedResult = -263.15;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToDefaultTemperatureKelvinToFahrenheitWorks(){
        // Arrange

        TemperatureUnit unit = new Kelvin();
        double valueToConvert = 10;
        String defaultUnit = "Fahrenheit";
        double expectedResult = -441.67;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfBuildStringWorks(){
        // Arrange

        TemperatureUnit unit = new Kelvin();
        String expectedResult = "Kelvin";

        // Act

        String actualResult = unit.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToApplicationDefaultWorks() throws IOException {
        // Arrange

        TemperatureUnit unit = new Kelvin();
        double valueToConvert = 10;
        double expectedResult = -263.15;

        // Act

        double actualResult = unit.toApplicationDefault(valueToConvert);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToUserDefaultWorks() throws IOException {
        // Arrange

        TemperatureUnit unit = new Kelvin();
        double valueToConvert = 10;
        double expectedResult = -263.15;

        // Act

        double actualResult = unit.toUserDefault(valueToConvert);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfEqualsConditionsWork(){
        // Arrange

        Celsius celsius = new Celsius();
        Kelvin kelvin = new Kelvin();

        // Act

        boolean actualResult1 = kelvin.equals(celsius); // Necessary for sonarqube coverage
        boolean actualResult2 = kelvin.equals(null); // Necessary for sonarqube coverage
        boolean actualResult3 = kelvin.equals(new Kelvin());

        // Assert

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);

    }

    @Test
    void hashcodeDummyTest(){
        // Arrange

        Kelvin kelvin = new Kelvin();
        int expectedResult = 1;

        // Act

        int actualResult = kelvin.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

}
