package pt.ipp.isep.dei.project.io.ui.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.*;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReaderJSONGeographicAreasTest {
    // Common testing artifacts for testing in this class.


    @Mock
    GeographicAreaRepository geographicAreaRepository;

    @Mock
    HouseRepository houseRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    EnergyGridRepo energyGridRepository;

    @Mock
    AreaTypeRepo areaTypeRepo;

    @Mock
    SensorTypeRepo sensorTypeRepo;

    private ReadingUtils readingUtils;
    private GeographicAreaService geographicAreaService;
    private RoomService roomService;

    private ReaderController ctrl;

    @BeforeEach
    void arrangeArtifacts() {
        roomService = new RoomService(roomRepository, sensorTypeRepo);
        geographicAreaService = new GeographicAreaService(geographicAreaRepository, areaTypeRepo, sensorTypeRepo);
        ctrl = new ReaderController();

    }

    @Test
    void seeIfReadFileWorks() {
        // Arrange

        //   GeographicAreaService expectedResult = new GeographicAreaService(geographicAreaRepository, areaTypeRepository);
        // GeographicAreaService actualResult = new GeographicAreaService(geographicAreaRepository, areaTypeRepository);
        //   AreaSensorService actualSensorService = new AreaSensorService(areaSensorRepository);

        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setName("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLocalDTO(new LocalDTO(41.178553, -8.608035, 111));


        // First Sensor in First Area

        AreaSensorDTO firstAreaFirstSensor = new AreaSensorDTO();
        firstAreaFirstSensor.setId("RF12345");
        firstAreaFirstSensor.setName("Meteo station ISEP - rainfall");
        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaFirstSensor.setTypeSensor("rainfall");
        firstAreaFirstSensor.setUnits("l/m2");
        firstAreaFirstSensor.setLocalDTO(new LocalDTO(41.179230, -8.606409, 125));


        // Second sensor in First Area

        AreaSensorDTO firstAreaSecondSensor = new AreaSensorDTO();
        firstAreaSecondSensor.setId("TT12346");
        firstAreaSecondSensor.setName("Meteo station ISEP - temperature");
        firstAreaSecondSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaSecondSensor.setTypeSensor("temperature");
        firstAreaSecondSensor.setUnits("C");
        firstAreaSecondSensor.setLocalDTO(new LocalDTO(41.179230, -8.606409, 125));

        // Second Area

        GeographicAreaDTO secondArea = new GeographicAreaDTO();
        secondArea.setName("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea("city");
        secondArea.setWidth(10.09);
        secondArea.setLength(3.30);
        secondArea.setLocalDTO(new LocalDTO(41.149935, -8.610857, 118));

        // First Sensor in Second Area

        AreaSensorDTO secondAreaFirstSensor = new AreaSensorDTO();
        secondAreaFirstSensor.setId("RF12334");
        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
        secondAreaFirstSensor.setTypeSensor("rainfall");
        secondAreaFirstSensor.setUnits("l/m2");
        secondArea.setLocalDTO(new LocalDTO(41.179230, -8.606409, 139));


        // Second Sensor in Second Area

        AreaSensorDTO secondAreaSecondSensor = new AreaSensorDTO();
        secondAreaSecondSensor.setId("TT1236A");
        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
        secondAreaSecondSensor.setTypeSensor("temperature");
        secondAreaSecondSensor.setUnits("C");
        secondAreaSecondSensor.setLocalDTO(new LocalDTO(41.179230, -8.606409, 139));


        // Populate expectedResult array

        GeographicArea areaOne = GeographicAreaMapper.dtoToObject(firstArea);
        GeographicArea areaTwo = GeographicAreaMapper.dtoToObject(secondArea);
        geographicAreaService.addAndPersistGA(areaOne);
        geographicAreaService.addAndPersistGA(areaTwo);
        firstAreaFirstSensor.setGeographicAreaID(areaOne.getId());
        firstAreaSecondSensor.setGeographicAreaID(areaOne.getId());
        secondAreaFirstSensor.setGeographicAreaID(areaTwo.getId());
        secondAreaSecondSensor.setGeographicAreaID(areaTwo.getId());

        AreaType city = new AreaType("city");
        AreaType urbanArea = new AreaType("urban area");

        Mockito.when(areaTypeRepo.findByName("urban area")).thenReturn(Optional.of(urbanArea));
        Mockito.when(areaTypeRepo.findByName("city")).thenReturn(Optional.of(city));

        SensorType rainfall = new SensorType("rainfall", "mm");
        SensorType temperature = new SensorType("temperature", "C");

        Mockito.when(sensorTypeRepo.findByName("rainfall")).thenReturn(Optional.of(rainfall));
        Mockito.when(sensorTypeRepo.findByName("temperature")).thenReturn(Optional.of(temperature));

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint04_GA.json");
        String absolutePath = fileToRead.getAbsolutePath();
        ReaderJSONGeographicAreas readerJSONGeographicAreas = new ReaderJSONGeographicAreas();

        double areasAdded = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(absolutePath, geographicAreaService);

        // Assert

        assertEquals(2, areasAdded);

        // Get one of the areas to  check its contents.

        //  GeographicArea actualArea = geographicAreaService.getAll().get(0);
        //   AreaSensorService firstAreaSensors = actualArea.getSensorList();


        GeographicArea expectedArea = new GeographicArea("ISEP", new AreaType("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 139));

        // Assert

        //  assertEquals(expectedArea, actualArea);
        //  assertEquals(actualArea.getSensorList(), firstAreaSensors);
    }


    @Test
    void seeIfReadFileWorksWrongPath() {
        // Arrange

        String invalidPath = ("invalidfilepath");
        ReaderJSONGeographicAreas readerJSONGeographicAreas = new ReaderJSONGeographicAreas();

        // Act

        double actualResult = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(invalidPath, geographicAreaService);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadFileWorksAndSkipsSensorsWithWrongDateFormat() {
        // Arrange
        //  Mapper mapper = new Mapper();

        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setName("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLocalDTO(new LocalDTO(41.178553, -8.608035, 111));

        // First Sensor in First Area

        AreaSensorDTO firstAreaFirstSensor = new AreaSensorDTO();
        firstAreaFirstSensor.setId("TT12346");
        firstAreaFirstSensor.setName("Meteo station ISEP - temperature");
        firstAreaFirstSensor.setDateStartedFunctioning("2016-11-15");
        firstAreaFirstSensor.setTypeSensor("temperature");
        firstAreaFirstSensor.setUnits("C");
        firstAreaFirstSensor.setLocalDTO(new LocalDTO(41.179230, -8.606409, 125));

        // Second Area

        GeographicAreaDTO secondArea = new GeographicAreaDTO();
        secondArea.setName("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea("city");
        secondArea.setWidth(10.09);
        secondArea.setLength(3.30);
        secondArea.setLocalDTO(new LocalDTO(41.149935, -8.610857, 118));


        // First Sensor in Second Area

        AreaSensorDTO secondAreaFirstSensor = new AreaSensorDTO();
        secondAreaFirstSensor.setId("RF12334");
        secondAreaFirstSensor.setName("Meteo station CMP - rainfall");
        secondAreaFirstSensor.setDateStartedFunctioning("2017-11-15");
        secondAreaFirstSensor.setTypeSensor("rainfall");
        secondAreaFirstSensor.setUnits("l/m2");
        secondAreaFirstSensor.setLocalDTO(new LocalDTO(41.179230, -8.606409, 139));


        // Second Sensor in Second Area

        AreaSensorDTO secondAreaSecondSensor = new AreaSensorDTO();
        secondAreaSecondSensor.setId("TT1236A");
        secondAreaSecondSensor.setName("Meteo station CMP - temperature");
        secondAreaSecondSensor.setDateStartedFunctioning("2017-11-16");
        secondAreaSecondSensor.setTypeSensor("temperature");
        secondAreaSecondSensor.setUnits("C");
        secondAreaSecondSensor.setLocalDTO(new LocalDTO(41.179230, -8.606409, 139));

        // Populate expectedResult array

        GeographicArea areaOne = GeographicAreaMapper.dtoToObject(firstArea);
        GeographicArea areaTwo = GeographicAreaMapper.dtoToObject(secondArea);
        geographicAreaService.addAndPersistGA(areaOne);
        geographicAreaService.addAndPersistGA(areaTwo);
        firstAreaFirstSensor.setGeographicAreaID(areaOne.getId());
        secondAreaFirstSensor.setGeographicAreaID(areaTwo.getId());
        secondAreaSecondSensor.setGeographicAreaID(areaTwo.getId());

        AreaType city = new AreaType("city");
        AreaType urbanArea = new AreaType("urban area");

        Mockito.when(areaTypeRepo.findByName("urban area")).thenReturn(Optional.of(urbanArea));
        Mockito.when(areaTypeRepo.findByName("city")).thenReturn(Optional.of(city));

        SensorType rainfall = new SensorType("rainfall", "mm");
        SensorType temperature = new SensorType("temperature", "C");

        Mockito.when(sensorTypeRepo.findByName("rainfall")).thenReturn(Optional.of(rainfall));
        Mockito.when(sensorTypeRepo.findByName("temperature")).thenReturn(Optional.of(temperature));

        //Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/InvalidJSONWrongDates.json");
        String absolutePath = fileToRead.getAbsolutePath();
        ReaderJSONGeographicAreas readerJSONGeographicAreas = new ReaderJSONGeographicAreas();
        double areasAdded = readerJSONGeographicAreas.readJSONFileAndAddGeoAreas(absolutePath, geographicAreaService);

        // Assert

        assertEquals(2, areasAdded);
        //  assertEquals(expectedResult, actualResult);

        // Get one of the areas to  check its contents.

        //    GeographicArea actualArea = actualResult.getAll().get(0);
        //  AreaSensorService firstAreaSensors = actualArea.getSensorList();

        GeographicArea expectedArea = new GeographicArea("ISEP", new AreaType("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 139));

        // Assert

        //     assertEquals(expectedArea, actualArea);
        //   assertEquals(actualArea.getSensorList(), firstAreaSensors);
    }
}
