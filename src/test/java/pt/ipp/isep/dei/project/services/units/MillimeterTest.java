package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MillimeterTest {

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
    void seeIfBuildStringWorks(){
        // Arrange

        RainfallUnit unit = new Millimeter();
        String expectedResult = "Millimeter";

        // Act

        String actualResult = unit.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToMillimeterWorks(){
        // Arrange

        RainfallUnit unit = new Millimeter();
        double expectedResult = 12;

        // Act

        double actualResult = unit.toMillimeter(12);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToLiterPerSquareMeterWorks(){
        // Arrange

        RainfallUnit unit = new Millimeter();
        double expectedResult = 12;

        // Act

        double actualResult = unit.toLiterPerSquareMeter(12);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToApplicationDefaultWorks() throws IOException {
        // Arrange

        double expectedResult = 20;
        double valueToConvert = 20;
        RainfallUnit unit = new Millimeter();

        // Act

        double actualResult = unit.toApplicationDefault(valueToConvert);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToUserDefaultWorks() throws IOException {
        // Arrange

        double expectedResult = 20;
        double valueToConvert = 20;
        RainfallUnit unit = new Millimeter();

        // Act

        double actualResult = unit.toUserDefault(valueToConvert);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfEqualsConditionsWork(){
        // Arrange

        LiterPerSquareMeter literPerSquareMeter = new LiterPerSquareMeter();
        Millimeter millimeter = new Millimeter();

        // Act

        boolean actualResult1 = millimeter.equals(literPerSquareMeter); // Necessary for sonarqube coverage
        boolean actualResult2 = millimeter.equals(null); // Necessary for sonarqube coverage
        boolean actualResult3 = millimeter.equals(new Millimeter());

        // Assert

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);

    }

    @Test
    void hashcodeDummyTest(){
        // Arrange

        Millimeter millimeter = new Millimeter();
        int expectedResult = 1;

        // Act

        int actualResult = millimeter.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

}
