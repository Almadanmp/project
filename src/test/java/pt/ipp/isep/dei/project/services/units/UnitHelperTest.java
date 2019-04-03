package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertEquals;

class UnitHelperTest {

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
    void seeIfToDefaultRainfallMillimeterToMillimeterWorks() {
        // Arrange

        RainfallUnit unit = new Millimeter();
        double valueToConvert = 10;
        String defaultUnit = "Millimeter";
        double expectedResult = 10;

        // Act

        double actualResult = UnitHelper.toDefaultRainfallUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToDefaultRainfallMillimeterToLiterPerSquareMeterWorks() {
        // Arrange

        RainfallUnit unit = new Millimeter();
        double valueToConvert = 10;
        String defaultUnit = "LiterPerSquareMeter";
        double expectedResult = 10;

        // Act

        double actualResult = UnitHelper.toDefaultRainfallUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToDefaultRainfallLiterPerSquareMeterToLiterPerSquareMeterWorks() {
        // Arrange

        RainfallUnit unit = new LiterPerSquareMeter();
        double valueToConvert = 10;
        String defaultUnit = "LiterPerSquareMeter";
        double expectedResult = 10;

        // Act

        double actualResult = UnitHelper.toDefaultRainfallUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToDefaultRainfallLiterPerSquareMeterToMillimeterWorks() {
        // Arrange

        RainfallUnit unit = new LiterPerSquareMeter();
        double valueToConvert = 10;
        String defaultUnit = "Millimeter";
        double expectedResult = 10;

        // Act

        double actualResult = UnitHelper.toDefaultRainfallUnit(defaultUnit, valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

}
