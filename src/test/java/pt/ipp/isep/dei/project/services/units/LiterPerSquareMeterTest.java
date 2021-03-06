package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LiterPerSquareMeterTest {

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
    void seeIfBuildStringWorks(){
        // Arrange

        RainfallUnit unit = new LiterPerSquareMeter();
        String expectedResult = "LiterPerSquareMeter";

        // Act

        String actualResult = unit.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToMillimeterWorks(){
        // Arrange

        RainfallUnit unit = new LiterPerSquareMeter();
        double expectedResult = 12;

        // Act

        double actualResult = unit.toMillimeter(12);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfToLiterPerSquareMeterWorks(){
        // Arrange

        RainfallUnit unit = new LiterPerSquareMeter();
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
        RainfallUnit unit = new LiterPerSquareMeter();

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
        RainfallUnit unit = new LiterPerSquareMeter();

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

        boolean actualResult1 = literPerSquareMeter.equals(millimeter); // Necessary for sonarqube coverage
        boolean actualResult2 = literPerSquareMeter.equals(null); // Necessary for sonarqube coverage
        boolean actualResult3 = literPerSquareMeter.equals(new LiterPerSquareMeter());

        // Assert

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);

    }

    @Test
    void hashcodeDummyTest(){
        // Arrange

        LiterPerSquareMeter literPerSquareMeter = new LiterPerSquareMeter();
        int expectedResult = 1;

        // Act

        int actualResult = literPerSquareMeter.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

}
