package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FahrenheitTest {

    @Test
    void seeIfToDefaultTemperatureFahrenheitToFahrenheitWorks() {
        // Arrange

        TemperatureUnit unit = new Fahrenheit();
        double valueToConvert = 10;
        String defaultUnit = "Fahrenheit";
        double expectedResult = 10;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToDefaultTemperatureFahrenheitToCelsiusWorks() {
        // Arrange

        TemperatureUnit unit = new Fahrenheit();
        double valueToConvert = 50;
        String defaultUnit = "Celsius";
        double expectedResult = 10;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToDefaultTemperatureFahrenheitToKelvinWorks() {
        // Arrange

        TemperatureUnit unit = new Fahrenheit();
        double valueToConvert = 30;
        String defaultUnit = "Kelvin";
        double expectedResult = 272.0388888888889;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfBuildStringWorks(){
        // Arrange

        TemperatureUnit unit = new Fahrenheit();
        String expectedResult = "Fahrenheit";

        // Act

        String actualResult = unit.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToApplicationDefaultWorks() throws IOException {
        // Arrange

        TemperatureUnit unit = new Fahrenheit();
        double valueToConvert = 10;
        double expectedResult = -12.222222222222221;

        // Act

        double actualResult = unit.toApplicationDefault(valueToConvert);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToUserDefaultWorks() throws IOException {
        // Arrange

        TemperatureUnit unit = new Fahrenheit();
        double valueToConvert = 10;
        double expectedResult = -12.222222222222221;

        // Act

        double actualResult = unit.toUserDefault(valueToConvert);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void fahrenheitHashcodeDummyTest(){
        // Arrange

        Fahrenheit fahrenheit = new Fahrenheit();
        int expectedResult = 1;

        // Act

        int actualResult = fahrenheit.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfEqualsConditionsWork(){
        // Arrange

        Celsius celsius = new Celsius();
        Fahrenheit fahrenheit = new Fahrenheit();

        // Act

        boolean actualResult1 = fahrenheit.equals(celsius); // Necessary for sonarqube coverage
        boolean actualResult2 = fahrenheit.equals(null); // Necessary for sonarqube coverage
        boolean actualResult3 = fahrenheit.equals(new Fahrenheit());

        // Assert

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);

    }

    @Test
    void hashcodeDummyTest(){
        // Arrange

        Fahrenheit fahrenheit = new Fahrenheit();
        int expectedResult = 1;

        // Act

        int actualResult = fahrenheit.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

}
