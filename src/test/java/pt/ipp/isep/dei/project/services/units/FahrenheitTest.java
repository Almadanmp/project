package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
