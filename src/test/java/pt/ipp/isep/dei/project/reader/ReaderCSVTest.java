package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ReaderCSV test class.
 */

class ReaderCSVTest {

    // Common artifacts for testing in this class.
    private ReaderCSV validReader;
    private static final String validLocation1 = "src/test/resources/readingsFiles/test3CSVReadings.csv";
    private static final String validLocation2 = "src/test/resources/readingsFiles/test4CSVReadings.csv";
    private static final String wrongLocation1 = "src/test/resources";
    private List<String[]> validList;

    @BeforeEach
    void arrangeArtifacts() {
        validReader = new ReaderCSV();
        validList = new ArrayList<>();

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
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfReadCSVWorksWithInvalidPath() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readFile(wrongLocation1));
    }
}