package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void seeIfGetApplicationTemperatureConfigWorks() throws IOException {
        // Arrange

        String expectedResult = "Celsius";

        // Act

        String actualResult = UnitHelper.getApplicationTemperatureConfig();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetUserTemperatureConfigWorks() throws IOException {
        // Arrange

        String expectedResult = "Celsius";

        // Act

        String actualResult = UnitHelper.getUserTemperatureConfig();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetApplicationRainfallConfigWorks() throws IOException {
        // Arrange

        String expectedResult = "Millimeter";

        // Act

        String actualResult = UnitHelper.getApplicationRainfallConfig();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetUserRainfallConfigWorks() throws IOException {
        // Arrange

        String expectedResult = "Millimeter";

        // Act

        String actualResult = UnitHelper.getUserRainfallConfig();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetApplicationTemperatureDefaultWorks(){
        // Arrange

        String propFileName = "invalid_path";

        // Act

        Throwable exception = assertThrows(IOException.class, () -> UnitHelper.getApplicationTemperatureDefault(propFileName));

        // Assert

        assertEquals("ERROR: Unable to process configuration file.", exception.getMessage());

    }

    @Test
    void seeIfGetApplicationRainfallDefaultWorks(){
        // Arrange

        String propFileName = "invalid_path";

        // Act

        Throwable exception = assertThrows(IOException.class, () -> UnitHelper.getApplicationRainfallDefault(propFileName));

        // Assert

        assertEquals("ERROR: Unable to process configuration file.", exception.getMessage());

    }

    @Test
    void seeIfGetUserTemperatureDefaultWorks(){
        // Arrange

        String propFileName = "invalid_path";

        // Act

        Throwable exception = assertThrows(IOException.class, () -> UnitHelper.getUserTemperatureDefault(propFileName));

        // Assert

        assertEquals("ERROR: Unable to process configuration file.", exception.getMessage());

    }

    @Test
    void seeIfGetUserRainfallDefaultWorks(){
        // Arrange

        String propFileName = "invalid_path";

        // Act

        Throwable exception = assertThrows(IOException.class, () -> UnitHelper.getUserRainfallDefault(propFileName));

        // Assert

        assertEquals("ERROR: Unable to process configuration file.", exception.getMessage());

    }

    @Test
    void seeIfConvertToSystemDefaultTemperatureWorks() throws IOException {
        // Arrange

        double expectedResult = -223.14999999999998;
        double valueToConvert = 50;
        Unit unit = new Kelvin();

        // Act

        double actualResult = UnitHelper.convertToSystemDefault(valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConvertToSystemDefaultRainfallWorks() throws IOException {
        // Arrange

        double expectedResult = 50;
        double valueToConvert = 50;
        Unit unit = new LiterPerSquareMeter();

        // Act

        double actualResult = UnitHelper.convertToSystemDefault(valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfConvertToSystemDefaultNullUnitWorks() throws IOException {
//        // Arrange
//
//        double expectedResult = 50;
//        double valueToConvert = 50;
//
//        // Act
//
//        double actualResult = UnitHelper.convertToSystemDefault(valueToConvert, );
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

    @Test
    void seeIfConvertToUserDefaultTemperatureWorks() throws IOException {
        // Arrange

        double expectedResult = -223.14999999999998;
        double valueToConvert = 50;
        Unit unit = new Kelvin();

        // Act

        double actualResult = UnitHelper.convertToUserDefault(valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConvertToUserDefaultRainfallWorks() throws IOException {
        // Arrange

        double expectedResult = 50;
        double valueToConvert = 50;
        Unit unit = new LiterPerSquareMeter();

        // Act

        double actualResult = UnitHelper.convertToUserDefault(valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

}
