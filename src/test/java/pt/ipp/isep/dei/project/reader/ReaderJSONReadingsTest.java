package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReaderJSONReadingsTest {

    // Common artifacts for testing in this class.
    private ReaderJSONReadings validReader;
    private static final String validPathNoReadings = "src/test/resources/test1JSONReadings.json";
    private static final String invalidPath = "src/test/resources";
    private JSONArray validJSONArray;

    @BeforeEach
    void arrangeArtifacts() {
        validReader = new ReaderJSONReadings();
        validJSONArray = new JSONArray();
    }


    @Test
    void seeIfReadFileWorksWithEmptyJSONArray() {
        //Act

        JSONArray actualResult1 = validReader.readFile(validPathNoReadings);

        //Assert

        JSONAssert.assertEquals(validJSONArray, actualResult1, JSONCompareMode.LENIENT);
    }

    @Test
    void seeIfReadFileWorksWithWrongPath() {
        //Act

        JSONArray actualResult1 = validReader.readFile(invalidPath);

        //Assert

        JSONAssert.assertEquals(validJSONArray, actualResult1, JSONCompareMode.LENIENT);
    }
}