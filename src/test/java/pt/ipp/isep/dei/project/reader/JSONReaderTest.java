package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.Mapper;
import pt.ipp.isep.dei.project.dto.SensorDTO;
import pt.ipp.isep.dei.project.model.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONReaderTest {
    // Common testing artifacts for testing in this class.

    private JSONReader reader = new JSONReader();

    @Test
    void seeIfReadFileWorks(){
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList();
        Mapper mapper = new Mapper();
        GeographicAreaList actualResult = new GeographicAreaList();

        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setId("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLatitude(41.178553);
        firstArea.setLongitude(-8.608035);
        firstArea.setAltitude(111);

        // First Sensor in First Area

        SensorDTO firstAreaFirstSensor = new SensorDTO();
        firstAreaFirstSensor.setId("RF12345");
        firstAreaFirstSensor.setName("Meteo station ISEP - rainfall");
        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaFirstSensor.setTypeSensor("rainfall");
        firstAreaFirstSensor.setUnits("l/m2");
        firstAreaFirstSensor.setLatitude(41.179230);
        firstAreaFirstSensor.setLongitude(-8.606409);
        firstAreaFirstSensor.setAltitude(125);
        firstArea.addSensorDTO(firstAreaFirstSensor);

        // Second sensor in First Area

        SensorDTO firstAreaSecondSensor = new SensorDTO();
        firstAreaSecondSensor.setId("TT12346");
        firstAreaSecondSensor.setName("Meteo station ISEP - temperature");
        firstAreaSecondSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaSecondSensor.setTypeSensor("temperature");
        firstAreaSecondSensor.setUnits("C");
        firstAreaSecondSensor.setLatitude(41.179230);
        firstAreaSecondSensor.setLongitude(-8.606409);
        firstAreaSecondSensor.setAltitude(125);
        firstArea.addSensorDTO(firstAreaSecondSensor);

        // Second Area

        GeographicAreaDTO secondArea = new GeographicAreaDTO();
        secondArea.setId("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea("city");
        secondArea.setWidth(10.09);
        secondArea.setLength(3.30);
        secondArea.setLatitude(41.149935);
        secondArea.setLongitude(-8.610857);
        secondArea.setAltitude(118);

        // First Sensor in Second Area

        SensorDTO secondAreaFirstSensor = new SensorDTO();
        secondAreaFirstSensor.setId("RF12334");
        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
        secondAreaFirstSensor.setTypeSensor("rainfall");
        secondAreaFirstSensor.setUnits("l/m2");
        secondAreaFirstSensor.setLatitude(41.179230);
        secondAreaFirstSensor.setLongitude(-8.606409);
        secondAreaFirstSensor.setAltitude(139);
        secondArea.addSensorDTO(secondAreaFirstSensor);

        // Second Sensor in Second Area

        SensorDTO secondAreaSecondSensor = new SensorDTO();
        secondAreaSecondSensor.setId("TT1236A");
        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
        secondAreaSecondSensor.setTypeSensor("temperature");
        secondAreaSecondSensor.setUnits("C");
        secondAreaSecondSensor.setLatitude(41.179230);
        secondAreaSecondSensor.setLongitude(-8.606409);
        secondAreaSecondSensor.setAltitude(139);
        secondArea.addSensorDTO(secondAreaSecondSensor);

        // Populate expectedResult array

        GeographicArea areaOne = mapper.geographicAreaDTOToObject(firstArea);
        GeographicArea areaTwo = mapper.geographicAreaDTOToObject(secondArea);
        expectedResult.addGeographicArea(areaOne);
        expectedResult.addGeographicArea(areaTwo);

        // Act

        File fileToRead = new File("src/test/resources/DataSet_sprint04_GA.json");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = reader.readFile(absolutePath, actualResult);

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(2, areasAdded);

        // Get one of the areas to  check its contents.

        GeographicArea actualArea = actualResult.get(0);
        SensorList firstAreaSensors = actualArea.getSensorList();

        // Declare expected area / sensors.

        SensorList expectedSensors = new SensorList();
        expectedSensors.add(actualArea.getSensorList().get(0));
        expectedSensors.add(actualArea.getSensorList().get(1));

        GeographicArea expectedArea = new GeographicArea("ISEP", new TypeArea("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 139));

        // Assert

        assertEquals(expectedArea, actualArea);
        assertEquals(expectedSensors, firstAreaSensors);
    }

    @Test
    void seeIfReadFileWorksWrongPath(){
        // Arrange

        String invalidPath = ("invalidfilepath");

        // Act

        double actualResult = reader.readFile(invalidPath, new GeographicAreaList());

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadFileWorksAndSkipsSensorsWithWrongDateFormat(){
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList();
        GeographicAreaList actualResult = new GeographicAreaList();
        Mapper mapper = new Mapper();

        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setId("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLatitude(41.178553);
        firstArea.setLongitude(-8.608035);
        firstArea.setAltitude(111);

        // First Sensor in First Area

        SensorDTO firstAreaFirstSensor = new SensorDTO();
        firstAreaFirstSensor.setId("TT12346");
        firstAreaFirstSensor.setName("Meteo station ISEP - temperature");
        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaFirstSensor.setTypeSensor("temperature");
        firstAreaFirstSensor.setUnits("C");
        firstAreaFirstSensor.setLatitude(41.179230);
        firstAreaFirstSensor.setLongitude(-8.606409);
        firstAreaFirstSensor.setAltitude(125);
        firstArea.addSensorDTO(firstAreaFirstSensor);

        // Second Area

        GeographicAreaDTO secondArea = new GeographicAreaDTO();
        secondArea.setId("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea("city");
        secondArea.setWidth(10.09);
        secondArea.setLength(3.30);
        secondArea.setLatitude(41.149935);
        secondArea.setLongitude(-8.610857);
        secondArea.setAltitude(118);

        // First Sensor in Second Area

        SensorDTO secondAreaFirstSensor = new SensorDTO();
        secondAreaFirstSensor.setId("RF12334");
        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
        secondAreaFirstSensor.setTypeSensor("rainfall");
        secondAreaFirstSensor.setUnits("l/m2");
        secondAreaFirstSensor.setLatitude(41.179230);
        secondAreaFirstSensor.setLongitude(-8.606409);
        secondAreaFirstSensor.setAltitude(139);
        secondArea.addSensorDTO(secondAreaFirstSensor);

        // Second Sensor in Second Area

        SensorDTO secondAreaSecondSensor = new SensorDTO();
        secondAreaSecondSensor.setId("TT1236A");
        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
        secondAreaSecondSensor.setTypeSensor("temperature");
        secondAreaSecondSensor.setUnits("C");
        secondAreaSecondSensor.setLatitude(41.179230);
        secondAreaSecondSensor.setLongitude(-8.606409);
        secondAreaSecondSensor.setAltitude(139);
        secondArea.addSensorDTO(secondAreaSecondSensor);

        // Populate expectedResult array

        GeographicArea areaOne = mapper.geographicAreaDTOToObject(firstArea);
        GeographicArea areaTwo = mapper.geographicAreaDTOToObject(secondArea);
        expectedResult.addGeographicArea(areaOne);
        expectedResult.addGeographicArea(areaTwo);

        // Act

        File fileToRead = new File("src/test/resources/InvalidJSONWrongDates.json");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = reader.readFile(absolutePath, actualResult);

        // Assert

        assertEquals(2, areasAdded);
        assertEquals(expectedResult, actualResult);

        // Get one of the areas to  check its contents.

        GeographicArea actualArea = actualResult.get(0);
        SensorList firstAreaSensors = actualArea.getSensorList();

        // Declare expected area / sensors.

        SensorList expectedSensors = new SensorList();
        expectedSensors.add(firstAreaSensors.get(0));

        GeographicArea expectedArea = new GeographicArea("ISEP", new TypeArea("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 139));

        // Assert

        assertEquals(expectedArea, actualArea);
        assertEquals(expectedSensors, firstAreaSensors);
    }
}
