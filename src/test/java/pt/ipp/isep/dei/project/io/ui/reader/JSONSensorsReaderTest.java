package pt.ipp.isep.dei.project.io.ui.reader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JSONSensorsReaderTest {

    @Mock
    private SensorTypeRepository sensorTypeRepository;


    @Test
    void seeIfImportSensorsWorks() {
        // Arrange

        JSONSensorsReader reader = new JSONSensorsReader();

        List<RoomSensorDTO> expectedResult = new ArrayList<>();

        RoomSensorDTO rejectedFirstSensor = new RoomSensorDTO();
        rejectedFirstSensor.setSensorId("TT12345OA");
        rejectedFirstSensor.setRoomID("B405");
        rejectedFirstSensor.setName("Temperature B405");
        rejectedFirstSensor.setDateStartedFunctioning("2016-11-15");
        rejectedFirstSensor.setTypeSensor("temperature");
        rejectedFirstSensor.setUnits("C");

        RoomSensorDTO firstDTO = new RoomSensorDTO();
        firstDTO.setSensorId("TT12346OB");
        firstDTO.setRoomID("B106");
        firstDTO.setName("Temperature B106");
        firstDTO.setDateStartedFunctioning("2016-11-15");
        firstDTO.setTypeSensor("temperature");
        firstDTO.setUnits("C");

        RoomSensorDTO secondDTO = new RoomSensorDTO();
        secondDTO.setSensorId("TT12334OA");
        secondDTO.setRoomID("B107");
        secondDTO.setName("Temperature B107");
        secondDTO.setDateStartedFunctioning("2017-11-15");
        secondDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        RoomSensorDTO thirdDTO = new RoomSensorDTO();
        thirdDTO.setSensorId("TT1236AC");
        thirdDTO.setRoomID("B109");
        thirdDTO.setName("Temperature B109");
        thirdDTO.setDateStartedFunctioning("2017-11-16");
        thirdDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        expectedResult.add(rejectedFirstSensor);
        expectedResult.add(firstDTO);
        expectedResult.add(secondDTO);
        expectedResult.add(thirdDTO);

        // Act
        List<RoomSensorDTO> actualResult = reader.importSensors("src/test/resources/houseSensorFiles/DataSet_sprint06_HouseSensors.json", sensorTypeRepository);

        // Assert

        assertEquals(expectedResult, actualResult);
    }



    @Test
    void seeIfImportSensorsWorksWithInvalidRoomID() {
        // Arrange

        JSONSensorsReader reader = new JSONSensorsReader();

        List<RoomSensorDTO> expectedResult = new ArrayList<>();

        RoomSensorDTO rejectedFirstSensor = new RoomSensorDTO();
        rejectedFirstSensor.setSensorId("TT12345OA");
        rejectedFirstSensor.setRoomID("B405");
        rejectedFirstSensor.setName("Temperature B405");
        rejectedFirstSensor.setDateStartedFunctioning("2016-11-15");
        rejectedFirstSensor.setTypeSensor("temperature");
        rejectedFirstSensor.setUnits("C");

        RoomSensorDTO firstDTO = new RoomSensorDTO();
        firstDTO.setSensorId("TT12346OB");
        firstDTO.setRoomID(null);
        firstDTO.setName("Temperature B106");
        firstDTO.setDateStartedFunctioning("2016-11-15");
        firstDTO.setTypeSensor("temperature");
        firstDTO.setUnits("C");

        RoomSensorDTO secondDTO = new RoomSensorDTO();
        secondDTO.setSensorId("TT12334OA");
        secondDTO.setRoomID("B107");
        secondDTO.setName("Temperature B107");
        secondDTO.setDateStartedFunctioning("2017-11-15");
        secondDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        RoomSensorDTO thirdDTO = new RoomSensorDTO();
        thirdDTO.setSensorId("TT1236AC");
        thirdDTO.setRoomID("B109");
        thirdDTO.setName("Temperature B109");
        thirdDTO.setDateStartedFunctioning("2017-11-16");
        thirdDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        expectedResult.add(rejectedFirstSensor);
        expectedResult.add(secondDTO);
        expectedResult.add(thirdDTO);

        // Act
        List<RoomSensorDTO> actualResult = reader.importSensors("src/test/resources/houseSensorFiles/DataSet_sprint06_HouseSensorsWrongRoomID.json", sensorTypeRepository);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfImportSensorsWorksWithInvalidElements() {
        // Arrange

        JSONSensorsReader reader = new JSONSensorsReader();

        List<RoomSensorDTO> expectedResult = new ArrayList<>();

        RoomSensorDTO rejectedFirstSensor = new RoomSensorDTO();
        rejectedFirstSensor.setSensorId("TT12345OA");
        rejectedFirstSensor.setRoomID("B405");
        rejectedFirstSensor.setName("Temperature B405");
        rejectedFirstSensor.setDateStartedFunctioning("2016-11-15");
        rejectedFirstSensor.setTypeSensor("temperature");
        rejectedFirstSensor.setUnits("C");

        RoomSensorDTO firstDTO = new RoomSensorDTO();
        firstDTO.setSensorId("TT12346OB");
        firstDTO.setRoomID("B106");
        firstDTO.setName(null);
        firstDTO.setDateStartedFunctioning("2016-11-15");
        firstDTO.setTypeSensor("temperature");
        firstDTO.setUnits("C");


        RoomSensorDTO secondDTO = new RoomSensorDTO();
        secondDTO.setSensorId(null);
        secondDTO.setRoomID("B107");
        secondDTO.setName("Temperature B107");
        secondDTO.setDateStartedFunctioning("2017-11-15");
        secondDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        RoomSensorDTO thirdDTO = new RoomSensorDTO();
        thirdDTO.setSensorId("TT1236AC");
        thirdDTO.setRoomID("B109");
        thirdDTO.setName("Temperature B109");
        thirdDTO.setDateStartedFunctioning("2017-11-16");
        thirdDTO.setTypeSensor("temperature");
        secondDTO.setUnits("C");

        expectedResult.add(rejectedFirstSensor);
        expectedResult.add(thirdDTO);

        // Act
        List<RoomSensorDTO> actualResult = reader.importSensors("src/test/resources/houseSensorFiles/DataSet_sprint06_HouseSensorsWrongElements.json", sensorTypeRepository);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfReadFileWorksNoFile() {
        JSONSensorsReader reader = new JSONSensorsReader();
        assertThrows(IllegalArgumentException.class,
                () -> reader.readFile("Wrong"));
    }

    @Test
    void seeIfGetElementsArrayWorksWrongFile() {
        JSONSensorsReader reader = new JSONSensorsReader();
        assertThrows(IllegalArgumentException.class,
                () -> reader.readFile("src/test/resources/houseSensorFiles/DataSet_sprint06_HouseSensorsWrongTag.json"));
    }
}
