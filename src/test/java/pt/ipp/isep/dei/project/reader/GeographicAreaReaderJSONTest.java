package pt.ipp.isep.dei.project.reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class GeographicAreaReaderJSONTest {

    // Common artifacts for testing in this class.
    private GeographicAreaReaderJSON validReader;
    private static final String validPath = "src/test/resources/geoAreaFiles/DataSet_sprint04_GA.json";
    private static final String validPathWithReadings = "src/test/resources/readingsFiles/test3JSONReadings.json";
    private static final String invalidPath = "src/test/resources";
    private JSONArray validJSONArray;


    @BeforeEach
    void arrangeArtifacts() {
        validReader = new GeographicAreaReaderJSON();
        validJSONArray = new JSONArray();
    }

    @Test
    void seeIfReadAreaSensorsFileWorksWithValidPath() {
        List<AreaSensorDTO> expectedResult = new ArrayList<>();

        AreaSensorDTO firstSensor = new AreaSensorDTO();
        firstSensor.setId("RF12345");
        firstSensor.setName("Meteo station ISEP - rainfall");
        firstSensor.setTypeSensor("rainfall");
        firstSensor.setDateStartedFunctioning("2016-11-15");
        firstSensor.setUnits("l/m2");
        LocalDTO local1 = new LocalDTO(41.179230,-8.606409,125);
        firstSensor.setLocalDTO(local1);

        AreaSensorDTO secondSensor = new AreaSensorDTO();
        secondSensor.setId("TT12346");
        secondSensor.setName("Meteo station ISEP - temperature");
        secondSensor.setTypeSensor("temperature");
        secondSensor.setDateStartedFunctioning("2016-11-15");
        secondSensor.setUnits("C");
        secondSensor.setLocalDTO(local1);

        expectedResult.add(firstSensor);
        expectedResult.add(secondSensor);

        List<AreaSensorDTO> result = validReader.readAreaSensorDTOS("src/test/resources/geoAreaFiles/DataSet_sprint04_GA_TEST_ONE_GA_TWO_SENSORS.json");

    //    assertEquals(expectedResult,result);
    }
}