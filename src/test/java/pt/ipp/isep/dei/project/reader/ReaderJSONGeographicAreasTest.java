package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ReaderJSONGeographicAreasTest {
    // Common testing artifacts for testing in this class.

    @Mock
    GeographicAreaRepository geographicAreaRepository;

    @Mock
    AreaSensorRepository areaSensorRepository;

    AreaSensorService areaSensorService;


    GeographicAreaList geographicAreaList;

    private ReaderController ctrl;

    @BeforeEach
    void arrangeArtifacts() {
        areaSensorService = new AreaSensorService(areaSensorRepository);

        geographicAreaList = new GeographicAreaList(geographicAreaRepository);

        ctrl = new ReaderController(areaSensorService);


    }

    //TODO who made this tests please check if they still apply
    @Test
    void seeIfReadFileWorks() {
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList(geographicAreaRepository);
        //Mapper mapper = new Mapper();
        GeographicAreaList actualResult = new GeographicAreaList(geographicAreaRepository);

//        // First Area
//
//        GeographicAreaDTO firstArea = new GeographicAreaDTO();
//        firstArea.setName("ISEP");
//        firstArea.setDescription("Campus do ISEP");
//        firstArea.setAreaType("urban area");
//        firstArea.setWidth(0.261);
//        firstArea.setLength(0.249);
//        firstArea.setLatitude(41.178553);
//        firstArea.setLongitude(-8.608035);
//        firstArea.setAltitude(111);
//
//        // First Sensor in First Area
//
//        AreaSensorDTO firstAreaFirstSensor = new AreaSensorDTO();
//        firstAreaFirstSensor.setId("RF12345");
//        firstAreaFirstSensor.setName("Meteo station ISEP - rainfall");
//        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
//        firstAreaFirstSensor.setSensorType("rainfall");
//        firstAreaFirstSensor.setUnits("l/m2");
//        firstAreaFirstSensor.setLatitude(41.179230);
//        firstAreaFirstSensor.setLongitude(-8.606409);
//        firstAreaFirstSensor.setAltitude(125);
//        firstArea.addSensorDTO(firstAreaFirstSensor);
//
//        // Second sensor in First Area
//
//        AreaSensorDTO firstAreaSecondSensor = new AreaSensorDTO();
//        firstAreaSecondSensor.setId("TT12346");
//        firstAreaSecondSensor.setName("Meteo station ISEP - temperature");
//        firstAreaSecondSensor.setDateStartedFunctioning("2016-11-15");
//        firstAreaSecondSensor.setSensorType("temperature");
//        firstAreaSecondSensor.setUnits("C");
//        firstAreaSecondSensor.setLatitude(41.179230);
//        firstAreaSecondSensor.setLongitude(-8.606409);
//        firstAreaSecondSensor.setAltitude(125);
//        firstArea.addSensorDTO(firstAreaSecondSensor);
//
//        // Second Area
//
//        GeographicAreaDTO secondArea = new GeographicAreaDTO();
//        secondArea.setName("Porto");
//        secondArea.setDescription("City of Porto");
//        secondArea.setAreaType("city");
//        secondArea.setWidth(10.09);
//        secondArea.setLength(3.30);
//        secondArea.setLatitude(41.149935);
//        secondArea.setLongitude(-8.610857);
//        secondArea.setAltitude(118);
//
//        // First Sensor in Second Area
//
//        AreaSensorDTO secondAreaFirstSensor = new AreaSensorDTO();
//        secondAreaFirstSensor.setId("RF12334");
//        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
//        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
//        secondAreaFirstSensor.setSensorType("rainfall");
//        secondAreaFirstSensor.setUnits("l/m2");
//        secondAreaFirstSensor.setLatitude(41.179230);
//        secondAreaFirstSensor.setLongitude(-8.606409);
//        secondAreaFirstSensor.setAltitude(139);
//        secondArea.addSensorDTO(secondAreaFirstSensor);
//
//        // Second Sensor in Second Area
//
//        AreaSensorDTO secondAreaSecondSensor = new AreaSensorDTO();
//        secondAreaSecondSensor.setId("TT1236A");
//        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
//        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
//        secondAreaSecondSensor.setSensorType("temperature");
//        secondAreaSecondSensor.setUnits("C");
//        secondAreaSecondSensor.setLatitude(41.179230);
//        secondAreaSecondSensor.setLongitude(-8.606409);
//        secondAreaSecondSensor.setAltitude(139);
//        secondArea.addSensorDTO(secondAreaSecondSensor);
//
//        // Populate expectedResult array
//
//        GeographicArea areaOne = mapper.geographicAreaDTOToObject(firstArea);
//        GeographicArea areaTwo = mapper.geographicAreaDTOToObject(secondArea);
//        expectedResult.addAndPersistGA(areaOne);
//        expectedResult.addAndPersistGA(areaTwo);

        // Act

//        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint04_GA.json");
//        String absolutePath = fileToRead.getAbsolutePath();
//        ReaderJSONGeographicAreas readerJSONGeographicAreas = new ReaderJSONGeographicAreas();
//        double areasAdded = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(absolutePath, actualResult, areaSensorList);
//        // Assert
//
////        assertEquals(expectedResult, actualResult);
//        assertEquals(2, areasAdded);
//
//        // Get one of the areas to  check its contents.
//
//        GeographicArea actualArea = actualResult.get(0);
//        AreaSensorList firstAreaSensors = actualArea.getSensorList();
//
//
//        GeographicArea expectedArea = new GeographicArea("ISEP", new AreaType("urban area"), 0.249,
//                0.261, new Local(41.178553, -8.608035, 139));
//
//        // Assert
//
//        assertEquals(expectedArea, actualArea);
//        assertEquals(actualArea.getSensorList(), firstAreaSensors);
//    }
    }

    @Test
    void seeIfReadFileWorksWrongPath() {
        // Arrange

        String invalidPath = ("invalidfilepath");
        ReaderJSONGeographicAreas readerJSONGeographicAreas = new ReaderJSONGeographicAreas();

        // Act

        double actualResult = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(invalidPath, new GeographicAreaList(geographicAreaRepository), areaSensorService);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadFileWorksAndSkipsSensorsWithWrongDateFormat() {
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList(geographicAreaRepository);
        GeographicAreaList actualResult = new GeographicAreaList(geographicAreaRepository);
        //  Mapper mapper = new Mapper();

//        // First Area
//
//        GeographicAreaDTO firstArea = new GeographicAreaDTO();
//        firstArea.setName("ISEP");
//        firstArea.setDescription("Campus do ISEP");
//        firstArea.setAreaType("urban area");
//        firstArea.setWidth(0.261);
//        firstArea.setLength(0.249);
//        firstArea.setLatitude(41.178553);
//        firstArea.setLongitude(-8.608035);
//        firstArea.setAltitude(111);
//
//        // First Sensor in First Area
//
//        AreaSensorDTO firstAreaFirstSensor = new AreaSensorDTO();
//        firstAreaFirstSensor.setId("TT12346");
//        firstAreaFirstSensor.setName("Meteo station ISEP - temperature");
//        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
//        firstAreaFirstSensor.setSensorType("temperature");
//        firstAreaFirstSensor.setUnits("C");
//        firstAreaFirstSensor.setLatitude(41.179230);
//        firstAreaFirstSensor.setLongitude(-8.606409);
//        firstAreaFirstSensor.setAltitude(125);
//        firstArea.addSensorDTO(firstAreaFirstSensor);
//
//        // Second Area
//
//        GeographicAreaDTO secondArea = new GeographicAreaDTO();
//        secondArea.setName("Porto");
//        secondArea.setDescription("City of Porto");
//        secondArea.setAreaType("city");
//        secondArea.setWidth(10.09);
//        secondArea.setLength(3.30);
//        secondArea.setLatitude(41.149935);
//        secondArea.setLongitude(-8.610857);
//        secondArea.setAltitude(118);
//
//        // First Sensor in Second Area
//
//        AreaSensorDTO secondAreaFirstSensor = new AreaSensorDTO();
//        secondAreaFirstSensor.setId("RF12334");
//        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
//        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
//        secondAreaFirstSensor.setSensorType("rainfall");
//        secondAreaFirstSensor.setUnits("l/m2");
//        secondAreaFirstSensor.setLatitude(41.179230);
//        secondAreaFirstSensor.setLongitude(-8.606409);
//        secondAreaFirstSensor.setAltitude(139);
//        secondArea.addSensorDTO(secondAreaFirstSensor);
//
//        // Second Sensor in Second Area
//
//        AreaSensorDTO secondAreaSecondSensor = new AreaSensorDTO();
//        secondAreaSecondSensor.setId("TT1236A");
//        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
//        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
//        secondAreaSecondSensor.setSensorType("temperature");
//        secondAreaSecondSensor.setUnits("C");
//        secondAreaSecondSensor.setLatitude(41.179230);
//        secondAreaSecondSensor.setLongitude(-8.606409);
//        secondAreaSecondSensor.setAltitude(139);
//        secondArea.addSensorDTO(secondAreaSecondSensor);
//
//        // Populate expectedResult array
//
//        GeographicArea areaOne = mapper.geographicAreaDTOToObject(firstArea);
//        GeographicArea areaTwo = mapper.geographicAreaDTOToObject(secondArea);
//        expectedResult.addAndPersistGA(areaOne);
//        expectedResult.addAndPersistGA(areaTwo);

        // Act

//        File fileToRead = new File("src/test/resources/geoAreaFiles/InvalidJSONWrongDates.json");
//        String absolutePath = fileToRead.getAbsolutePath();
//        ReaderJSONGeographicAreas readerJSONGeographicAreas = new ReaderJSONGeographicAreas();
//        double areasAdded = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(absolutePath, actualResult, areaSensorList);
//
//        // Assert
//
//        assertEquals(2, areasAdded);
//        //  assertEquals(expectedResult, actualResult);
//
//        // Get one of the areas to  check its contents.
//
//        GeographicArea actualArea = actualResult.get(0);
//        AreaSensorList firstAreaSensors = actualArea.getSensorList();
//
//        // Declare expected area / sensors.
//
//        AreaSensorList expectedSensors = new AreaSensorList();
//        expectedSensors.add(firstAreaSensors.get(0));
//
//        GeographicArea expectedArea = new GeographicArea("ISEP", new AreaType("urban area"), 0.249,
//                0.261, new Local(41.178553, -8.608035, 139));
//
//        // Assert
//
//        assertEquals(expectedArea, actualArea);
//        assertEquals(expectedSensors, firstAreaSensors);
    }
}
