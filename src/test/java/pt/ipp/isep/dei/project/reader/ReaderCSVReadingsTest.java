package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ReaderCSVReadings test class.
 */

class ReaderCSVReadingsTest {

    // Common artifacts for testing in this class.
    private ReaderCSVReadings validReader;
    private static final String validLocation1 = "src/test/resources/test3CSVReadings.csv";
    private static final String validLocation2 = "src/test/resources/test4CSVReadings.csv";
    private static final String wrongLocation1 = "src/test/resources";
    private List<String[]> validList;

    @BeforeEach
    void arrangeArtifacts() {
        validReader = new ReaderCSVReadings();
        validList = new ArrayList<>();

    }

    @Test
    void seeIfReadCSVWorks() {

        //Arrange

        String[] readings = new String[3];
        readings[0] = "Sensor1";
        readings[1] = "2020-12-30T14:00:00+00:00";
        readings[2] = "16.5";
        validList.add(readings);

        // Act

        List<String[]> actualResult = validReader.readFile(validLocation2);

        //Assert
        assertArrayEquals(validList.toArray(),actualResult.toArray());
    }

    @Test
    void seeIfReadCSVWorksWhenFileHasOnlyOneLine() {

        //Arrange

        String[] readings = new String[0];
        validList.add(readings);
        List<String[]> expectedResult = new ArrayList<>();

        // Act

        List<String[]> actualResult = validReader.readFile(validLocation1);

        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfReadCSVWorksWithInvalidPath() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readFile(wrongLocation1));
    }
}