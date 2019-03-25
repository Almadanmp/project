package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.junit.jupiter.api.Assertions.assertThrows;


class ReaderJSONReadingsTest {

    // Common artifacts for testing in this class.
    private ReaderJSONReadings validReader;
    private static final String validPathNoReadings = "src/test/resources/test1JSONReadings.json";
    private static final String validPathWithReadings = "src/test/resources/test3JSONReadings.json";
    private static final String invalidPath = "src/test/resources";
    private JSONArray validJSONArray;


    @BeforeEach
    void arrangeArtifacts() {
        validReader = new ReaderJSONReadings();
        validJSONArray = new JSONArray();
    }

    @Test
    void seeIfReadFileWorksWithValidPathWithReadings() {
        //Arrange

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id", "TT12346");
        jsonObject1.put("timestamp/date", "2018-12-30T02:00:00+00:00");
        jsonObject1.put("value", "57.2");
        jsonObject1.put("unit", "F");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id", "TT12346");
        jsonObject2.put("timestamp/date", "2018-12-30T02:00:00+00:00");
        jsonObject2.put("value", "56.66");
        jsonObject2.put("unit", "F");

        validJSONArray.put(jsonObject1);
        validJSONArray.put(jsonObject2);

        System.out.println(validJSONArray);


        //Act

        JSONArray actualResult1 = validReader.readFile(validPathWithReadings);

        //Assert

        JSONAssert.assertEquals(validJSONArray, actualResult1, JSONCompareMode.LENIENT);
    }

    @Test
    void seeIfReadFileWorksWithEmptyJSONArray() {
        //Act

        JSONArray actualResult1 = validReader.readFile(validPathNoReadings);

        //Assert

        JSONAssert.assertEquals(validJSONArray, actualResult1, JSONCompareMode.LENIENT);
    }

    @Test
    void seeIfReadFileThrowsExceptionWithWrongPath() {
        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> validReader.readFile(invalidPath));

    }

    @Test
    void seeIfGetJSONArrayFromFileThrowsException() {
        //Arrange

        JSONObject jsonObject = new JSONObject();

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> validReader.getJSONArrayFromFile(jsonObject));

    }
}