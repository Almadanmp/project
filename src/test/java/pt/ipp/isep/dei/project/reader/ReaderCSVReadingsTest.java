package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ReaderCSVReadings test class.
 */

public class ReaderCSVReadingsTest {

    // Common artifacts for testing in this class.
    private ReaderCSVReadings validReader;
    private static final String validLocation1 = "src/test/resources/testCSV3.csv";
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

        String[] readings = new String[0];
        validList.add(readings);
        List<String[]> expectedResult = new ArrayList<>();

        // Act

        List<String[]> actualResult = validReader.readFile(validLocation1);

        //Assert
        assertEquals(expectedResult,actualResult);
    }
/*
    @Test
    void seeIfReadCSVWorks2() {

        //Arrange

        String[] readings = new String[0];
        //readings[0] = "RF12345";
        //readings[1] = "date";
        //readings[2] = "14.6";
        validList.add(readings);
        List<String[]> expectedResult = new ArrayList<>();
        //expectedResult.add(readings);

        // Act

        List<String[]> actualResult = validReader.readCSV(validLocation1);

        //Assert
        assertEquals(expectedResult,actualResult);
    }
*/
}