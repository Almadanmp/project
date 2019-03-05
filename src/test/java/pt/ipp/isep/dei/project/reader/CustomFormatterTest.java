package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

/**
 * CustomFormatter tests class.
 */

class CustomFormatterTest {

    // Common artifacts for testing in this class.
    private StreamHandler validHandler; // Handler of the type StreamHandler
    private CustomFormatter validFormatter; // Formatter with custom preferences
    private DateFormat validDateFormat;

    @BeforeEach
    void arrangeArtifacts() {
        validDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        validHandler = new StreamHandler();
        validFormatter = new CustomFormatter();
    }


    @Test
    void seeIfTailGetterWorks() {
        //Arrange
        String expectedResult = "";

        // Act
        String result = validFormatter.getTail(validHandler);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfTailGetterFailsWrongString() {
        //Arrange
        String expectedResult = "123";

        // Act
        String result = validFormatter.getTail(validHandler);

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfTailGetterFailsNull() {
        //Arrange
        String expectedResult = null;

        // Act
        String result = validFormatter.getTail(validHandler);

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfHeadGetterWorks() {
        //Arrange
        String expectedResult = "";

        // Act
        String result = validFormatter.getHead(validHandler);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfHeadGetterFailsWrongString() {
        //Arrange
        String expectedResult = "123";

        // Act
        String result = validFormatter.getHead(validHandler);

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfHeadGetterFailsNull() {
        //Arrange
        String expectedResult = null;

        // Act
        String result = validFormatter.getHead(validHandler);

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    void seeIfFormatWorks() {
        //Arrange
        LogRecord logRecord = new LogRecord(Level.WARNING, "test warning");
        String date = validDateFormat.format(new Date(logRecord.getMillis()));
        String expectedResult = date + " - [null.null] - [WARNING] - test warning\n";

        // Act
        String result = validFormatter.format(logRecord);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfFormatFailsWrongString() {
        //Arrange
        LogRecord logRecord = new LogRecord(Level.WARNING, "test warning");
        String date = validDateFormat.format(new Date(logRecord.getMillis()));
        String expectedResult = date + " - [null.null] - [INFO] - test warning\n";

        // Act
        String result = validFormatter.format(logRecord);

        //Assert
        assertNotEquals(expectedResult, result);
    }

}