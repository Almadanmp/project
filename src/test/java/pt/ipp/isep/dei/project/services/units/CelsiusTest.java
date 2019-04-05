package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CelsiusTest {

    @Test
    void seeIfToDefaultTemperatureCelsiusToCelsiusWorks(){
        // Arrange

        TemperatureUnit unit = new Celsius();
        double valueToConvert = 10;
        String defaultUnit = "Celsius";
        double expectedResult = 10;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToDefaultTemperatureCelsiusToFahrenheitWorks(){
        // Arrange

        TemperatureUnit unit = new Celsius();
        double valueToConvert = 10;
        String defaultUnit = "Fahrenheit";
        double expectedResult = 50;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToDefaultTemperatureCelsiusToKelvinWorks(){
        // Arrange

        TemperatureUnit unit = new Celsius();
        double valueToConvert = 10;
        String defaultUnit = "Kelvin";
        double expectedResult = 283.15;

        // Act

        double actualResult = UnitHelper.toDefaultTemperatureUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfBuildStringWorks(){
        // Arrange

        TemperatureUnit unit = new Celsius();
        String expectedResult = "Celsius";

        // Act

        String actualResult = unit.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToApplicationDefaultWorks() throws IOException {
        // Arrange

        TemperatureUnit unit = new Celsius();
        double valueToConvert = 10;
        double expectedResult = 10;

        // Act

        double actualResult = unit.toApplicationDefault(valueToConvert);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToUserDefaultWorks() throws IOException {
        // Arrange

        TemperatureUnit unit = new Celsius();
        double valueToConvert = 10;
        double expectedResult = 10;

        // Act

        double actualResult = unit.toUserDefault(valueToConvert);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

}
