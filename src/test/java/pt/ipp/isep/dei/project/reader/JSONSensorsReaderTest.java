package pt.ipp.isep.dei.project.reader;

import org.eclipse.persistence.internal.oxm.record.json.JSONReader;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.HouseSensorDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JSONSensorsReaderTest {
    @Test
    void seeIfImportSensorsWorks(){
        // Arrange

        JSONSensorsReader reader = new JSONSensorsReader();

        List<HouseSensorDTO> expectedResult = new ArrayList<>();

        HouseSensorDTO rejectedFirstSensor = new HouseSensorDTO();
        rejectedFirstSensor.setId("TT12345OA");
        rejectedFirstSensor.setRoomID("B405");
        rejectedFirstSensor.setName("Temperature B405");
        rejectedFirstSensor.setDateStartedFunctioning("2016-11-15");
        rejectedFirstSensor.setTypeSensor("temperature");
        rejectedFirstSensor.setUnits("C");

        HouseSensorDTO firstDTO = new HouseSensorDTO();
        firstDTO.setId("TT12346OB");
        firstDTO.setRoomID("B106");
        firstDTO.setName("Temperature B106");
        firstDTO.setDateStartedFunctioning("2016-11-15");
        firstDTO.setTypeSensor("temperature");
        firstDTO.setUnits("C");

        HouseSensorDTO secondDTO = new HouseSensorDTO();
        secondDTO.setId("TT12334OA");
        secondDTO.setRoomID("B107");
        secondDTO.setName("Temperature B107");
        secondDTO.setDateStartedFunctioning("2017-11-15");
        secondDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        HouseSensorDTO thirdDTO = new HouseSensorDTO();
        thirdDTO.setId("TT1236AC");
        thirdDTO.setRoomID("B109");
        thirdDTO.setName("Temperature B109");
        thirdDTO.setDateStartedFunctioning( "2017-11-16");
        thirdDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        expectedResult.add(rejectedFirstSensor);
        expectedResult.add(firstDTO);
        expectedResult.add(secondDTO);
        expectedResult.add(thirdDTO);

        // Act

        List<HouseSensorDTO> actualResult = reader.importSensors("src/test/resources/sensorFiles/DataSet_sprint06_HouseSensors.json");

        // Assert

        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfImportSensorsWorksWithInvalidRoomID(){
        // Arrange

        JSONSensorsReader reader = new JSONSensorsReader();

        List<HouseSensorDTO> expectedResult = new ArrayList<>();

        HouseSensorDTO rejectedFirstSensor = new HouseSensorDTO();
        rejectedFirstSensor.setId("TT12345OA");
        rejectedFirstSensor.setRoomID("B405");
        rejectedFirstSensor.setName("Temperature B405");
        rejectedFirstSensor.setDateStartedFunctioning("2016-11-15");
        rejectedFirstSensor.setTypeSensor("temperature");
        rejectedFirstSensor.setUnits("C");

        HouseSensorDTO firstDTO = new HouseSensorDTO();
        firstDTO.setId("TT12346OB");
        firstDTO.setRoomID(null);
        firstDTO.setName("Temperature B106");
        firstDTO.setDateStartedFunctioning("2016-11-15");
        firstDTO.setTypeSensor("temperature");
        firstDTO.setUnits("C");

        HouseSensorDTO secondDTO = new HouseSensorDTO();
        secondDTO.setId("TT12334OA");
        secondDTO.setRoomID("B107");
        secondDTO.setName("Temperature B107");
        secondDTO.setDateStartedFunctioning("2017-11-15");
        secondDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        HouseSensorDTO thirdDTO = new HouseSensorDTO();
        thirdDTO.setId("TT1236AC");
        thirdDTO.setRoomID("B109");
        thirdDTO.setName("Temperature B109");
        thirdDTO.setDateStartedFunctioning( "2017-11-16");
        thirdDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        expectedResult.add(rejectedFirstSensor);
        expectedResult.add(secondDTO);
        expectedResult.add(thirdDTO);

        // Act

        List<HouseSensorDTO> actualResult = reader.importSensors("src/test/resources/sensorFiles/DataSet_sprint06_HouseSensorsWrongRoomID.json");

        // Assert

        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfImportSensorsWorksWithInvalidElements(){
        // Arrange

        JSONSensorsReader reader = new JSONSensorsReader();

        List<HouseSensorDTO> expectedResult = new ArrayList<>();

        HouseSensorDTO rejectedFirstSensor = new HouseSensorDTO();
        rejectedFirstSensor.setId("TT12345OA");
        rejectedFirstSensor.setRoomID("B405");
        rejectedFirstSensor.setName("Temperature B405");
        rejectedFirstSensor.setDateStartedFunctioning("2016-11-15");
        rejectedFirstSensor.setTypeSensor("temperature");
        rejectedFirstSensor.setUnits("C");

        HouseSensorDTO firstDTO = new HouseSensorDTO();
        firstDTO.setId("TT12346OB");
        firstDTO.setRoomID("B106");
        firstDTO.setName(null);
        firstDTO.setDateStartedFunctioning("2016-11-15");
        firstDTO.setTypeSensor("temperature");
        firstDTO.setUnits("C");

        HouseSensorDTO secondDTO = new HouseSensorDTO();
        secondDTO.setId(null);
        secondDTO.setRoomID("B107");
        secondDTO.setName("Temperature B107");
        secondDTO.setDateStartedFunctioning("2017-11-15");
        secondDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        HouseSensorDTO thirdDTO = new HouseSensorDTO();
        thirdDTO.setId("TT1236AC");
        thirdDTO.setRoomID("B109");
        thirdDTO.setName("Temperature B109");
        thirdDTO.setDateStartedFunctioning( "2017-11-16");
        thirdDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        expectedResult.add(rejectedFirstSensor);
        expectedResult.add(thirdDTO);

        // Act

        List<HouseSensorDTO> actualResult = reader.importSensors("src/test/resources/sensorFiles/DataSet_sprint06_HouseSensorsWrongElements.json");

        // Assert

        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfReadFileWorksNoFile(){
        JSONSensorsReader reader = new JSONSensorsReader();
        assertThrows(IllegalArgumentException.class,
                () -> reader.readFile("Wrong"));
    }

    @Test
    void seeIfGetElementsArrayWorksWrongFile(){
        JSONSensorsReader reader = new JSONSensorsReader();
        assertThrows(IllegalArgumentException.class,
                () -> reader.readFile("src/test/resources/sensorFiles/DataSet_sprint06_HouseSensorsWrongTag.json"));
    }
}
