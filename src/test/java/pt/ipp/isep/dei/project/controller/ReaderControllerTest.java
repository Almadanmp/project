package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.energy.EnergyGridService;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.reader.ReaderXMLGeoArea;
import pt.ipp.isep.dei.project.repository.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * ReaderController test class.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReaderControllerTest {

    // Common artifacts for testing in this class.

    private GeographicAreaService validGeographicAreaService;
    private EnergyGridService energyGridService;
    private ReaderXMLGeoArea validReaderXMLGeoArea;
    private Date validDate1 = new Date();
    private Date validDate2 = new Date();
    private Date validDate3 = new Date();
    private Date validDate4 = new Date();
    private ReaderController readerController;
    private AreaSensor validAreaSensor1;
    private RoomSensor validRoomSensor1;
    private ReadingUtils readingUtils;
    private GeographicAreaService geographicAreaService;
    private RoomService roomService;
    private GeographicArea validGeographicArea;


    private static final String validLogPath = "dumpFiles/dumpLogFile.html";
    private static final String invalidLogPath = "./resoursagfdgs/logs/logOut.log"; //Não apagar p.f.

    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());

    @Mock
    AreaSensorRepository areaSensorRepository;

    @Mock
    GeographicAreaRepository geographicAreaRepository;

    @Mock
    HouseRepository houseRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    EnergyGridRepository energyGridRepository;

    @Mock
    RoomSensorRepository roomSensorRepository;

    @Mock
    AreaTypeRepository areaTypeRepository;

    @Mock
    SensorTypeRepository sensorTypeRepository;


    @BeforeEach
    void arrangeArtifacts() {
        energyGridService = new EnergyGridService(energyGridRepository);
        geographicAreaService = new GeographicAreaService(this.geographicAreaRepository, areaTypeRepository, sensorTypeRepository);
        this.roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        readerController = new ReaderController();
        validReaderXMLGeoArea = new ReaderXMLGeoArea();
        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            validDate1 = validSdf.parse("2016-11-15");
            validDate2 = validSdf.parse("2016-11-15");
            validDate3 = validSdf.parse("2017-11-15");
            validDate4 = validSdf.parse("2017-11-16");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validGeographicArea = new GeographicArea("ISEP", new AreaType("urban area"), 0.249, 0.261,
                new Local(41.178553, -8.608035, 111));
        GeographicArea validGeographicArea2 = new GeographicArea("Porto", new AreaType("city"), 3.30, 10.09,
                new Local(41.149935, -8.610857, 118));
        GeographicArea emptyGeographicArea = new GeographicArea("Lisbon", new AreaType("city"), 0.299, 0.291,
                new Local(41.178553, 8.608035, 117));
        validAreaSensor1 = new AreaSensor("RF12345", "Meteo station ISEP - rainfall", new SensorType("rain", "mm"),
                new Local(41.179230, -8.606409, 125),
                validDate1, 6008L);
        AreaSensor validAreaSensor2 = new AreaSensor("TT12346", "Meteo station ISEP - temperature", new SensorType("rain2", "mm2"),
                new Local(41.179230, -8.606409, 125),
                validDate2, 6008L);
        AreaSensor validAreaSensor3 = new AreaSensor("RF12334", "Meteo station CMP - rainfall", new SensorType("rain2", "mm2"),
                new Local(41.179230, -8.606409, 139),
                validDate3, 6008L);
        AreaSensor validAreaSensor4 = new AreaSensor("TT1236A", "Meteo station CMP - temperature", new SensorType("rain2", "mm2"),
                new Local(41.179230, -8.606409, 139),
                validDate4, 6008L);
        validGeographicAreaService = new GeographicAreaService(geographicAreaRepository, areaTypeRepository, sensorTypeRepository);
        validRoomSensor1 = new RoomSensor("SensorID1", "SensorOne", new SensorType("Temperature", "C"), validDate1, "Room1");
    }

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    @BeforeEach
    void setUpOutput() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        logger.setLevel(Level.FINE);
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    void seeIfAddReadingsToRoomSensorsWorks() {
        // Arrange

        List<ReadingDTO> readingDTOS = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        Room room = new Room("Room1", "Description", 1, 1, 1, 1, "House", "EnergyGrid");
        rooms.add(room);
        room.addSensor(validRoomSensor1);

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setSensorId("SensorID1");
        readingDTO1.setValue(20D);
        readingDTO1.setUnit("C");
        readingDTO1.setDate(validDate4);
        readingDTOS.add(readingDTO1);

        ReadingDTO readingDTO2 = new ReadingDTO();
        readingDTO2.setSensorId("SensorID1");
        readingDTO2.setValue(20D);
        readingDTO2.setUnit("C");
        readingDTO2.setDate(validDate1);
        readingDTOS.add(readingDTO2);

        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        // Act

        int actualResult = readerController.addReadingsToRoomSensors(readingDTOS, validLogPath, roomService);

        // Assert

        assertEquals(2, actualResult);
    }

    @Test
    void seeIfAddReadingsToGeographicAreaSensorsWorks() {
        // Arrange

        List<ReadingDTO> readingDTOS = new ArrayList<>();
        List<GeographicArea> geographicAreas = new ArrayList<>();
        geographicAreas.add(validGeographicArea);
        validGeographicArea.addSensor(validAreaSensor1);

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setSensorId("RF12345");
        readingDTO1.setValue(20D);
        readingDTO1.setUnit("C");
        readingDTO1.setDate(validDate1);
        readingDTOS.add(readingDTO1);

        ReadingDTO readingDTO2 = new ReadingDTO();
        readingDTO2.setSensorId("RF12345");
        readingDTO2.setValue(20D);
        readingDTO2.setUnit("C");
        readingDTO2.setDate(validDate3);
        readingDTOS.add(readingDTO2);

        Mockito.when(geographicAreaRepository.findAll()).thenReturn(geographicAreas);

        // Act

        int actualResult = readerController.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath, geographicAreaService);

        // Assert

        assertEquals(2, actualResult);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWrongPath() {
        // Arrange
        // Act

        File fileToRead = new File("src/test/resources/readingsFiles/test1XMLReadings.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaService);

        // Assert

        assertEquals(0, areasAdded);
    }


    @Test
    void seeIfReadFileXMLGeoAreaWorksWithoutGeoAreas() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_no_GAs.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        AreaType city = new AreaType("city");
        AreaType urbanArea = new AreaType("urban area");

        Mockito.when(areaTypeRepository.findByName("urban area")).thenReturn(Optional.of(urbanArea));
        Mockito.when(areaTypeRepository.findByName("city")).thenReturn(Optional.of(city));

        SensorType rainfall = new SensorType("rainfall", "mm");
        SensorType temperature = new SensorType("temperature", "C");

        Mockito.when(sensorTypeRepository.findByName("rainfall")).thenReturn(Optional.of(rainfall));
        Mockito.when(sensorTypeRepository.findByName("temperature")).thenReturn(Optional.of(temperature));

        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaService);

        // Assert

        assertEquals(0, areasAdded);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWrongPathNotXml() {
        // Arrange
        // Act

        File fileToRead = new File("src/test/resources/readingsFiles/test1XMLReadings.json");
        String absolutePath = fileToRead.getAbsolutePath();

        // Assert

        assertThrows(IllegalArgumentException.class,() -> validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaService));
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorks() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaService);

        AreaType city = new AreaType("city");
        AreaType urbanArea = new AreaType("urban area");

        Mockito.when(areaTypeRepository.findByName("urban area")).thenReturn(Optional.of(urbanArea));
        Mockito.when(areaTypeRepository.findByName("city")).thenReturn(Optional.of(city));

        SensorType rainfall = new SensorType("rainfall", "mm");
        SensorType temperature = new SensorType("temperature", "C");

        Mockito.when(sensorTypeRepository.findByName("rainfall")).thenReturn(Optional.of(rainfall));
        Mockito.when(sensorTypeRepository.findByName("temperature")).thenReturn(Optional.of(temperature));

        // Assert

        assertEquals(0, areasAdded);

    }


    @Test
    void seeIfReadFileXMLGeoAreaWorksWithAnotherDateFormat() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_wrong_date.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        AreaType city = new AreaType("city");
        AreaType urbanArea = new AreaType("urban area");

        Mockito.when(areaTypeRepository.findByName("urban area")).thenReturn(Optional.of(urbanArea));
        Mockito.when(areaTypeRepository.findByName("city")).thenReturn(Optional.of(city));

        SensorType rainfall = new SensorType("rainfall", "mm");
        SensorType temperature = new SensorType("temperature", "C");

        Mockito.when(sensorTypeRepository.findByName("rainfall")).thenReturn(Optional.of(rainfall));
        Mockito.when(sensorTypeRepository.findByName("temperature")).thenReturn(Optional.of(temperature));

        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaService);

        // Assert

        assertEquals(2, areasAdded);
    }


    @Test
    void seeIfReadFileXMLGeoAreaWorksWithOneGeoArea() {
        // Arrange

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_one_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        AreaType city = new AreaType("city");
        AreaType urbanArea = new AreaType("urban area");

        Mockito.when(areaTypeRepository.findByName("urban area")).thenReturn(Optional.of(urbanArea));
        Mockito.when(areaTypeRepository.findByName("city")).thenReturn(Optional.of(city));

        SensorType rainfall = new SensorType("rainfall", "mm");
        SensorType temperature = new SensorType("temperature", "C");

        Mockito.when(sensorTypeRepository.findByName("rainfall")).thenReturn(Optional.of(rainfall));
        Mockito.when(sensorTypeRepository.findByName("temperature")).thenReturn(Optional.of(temperature));

        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaService);

        // Assert

        assertEquals(1, areasAdded);
    }


    @Test
    void seeIfReadAndAddAreasWorks() {
        // Arrange

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

        Mockito.when(areaTypeRepository.findByName("urban area")).thenReturn(Optional.of(urbanArea));
        Mockito.when(areaTypeRepository.findByName("city")).thenReturn(Optional.of(city));

        SensorType rainfall = new SensorType("rainfall", "mm");
        SensorType temperature = new SensorType("temperature", "C");

        Mockito.when(sensorTypeRepository.findByName("rainfall")).thenReturn(Optional.of(rainfall));
        Mockito.when(sensorTypeRepository.findByName("temperature")).thenReturn(Optional.of(temperature));

        // Act

        File fileToRead = new File("src/test/resources/geoAreaFiles/DataSet_sprint05_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();

        double areasAdded = validReaderXMLGeoArea.readFileXMLAndAddAreas(absolutePath, geographicAreaService);

        // Assert

        assertEquals(2, areasAdded);
    }


    //   @Test
//    void addReadingsToGeographicAreaSensorsWorks() { //TODO TERESA revisitar este teste
//        //Arrange
//        List<ReadingDTO> readingDTOS = new ArrayList<>();
//
//        ReadingDTO readingDTO1 = new ReadingDTO();
//        readingDTO1.setSensorId("TT");
//        readingDTO1.setUnit("C");
//        readingDTO1.setValue(2D);
//        readingDTO1.setDate(validDate1);
//
//        ReadingDTO readingDTO2 = new ReadingDTO();
//        readingDTO2.setSensorId("TT");
//        readingDTO2.setUnit("C");
//        readingDTO2.setValue(2D);
//        readingDTO2.setDate(validDate3);
//
//        readingDTOS.add(readingDTO1);
//        readingDTOS.add(readingDTO2);
//
//        AreaSensor sensor = new AreaSensor("TT", "Sensor", new SensorType(), new Local(2, 2, 2), validDate1, 2L);
//
//        Mockito.when(areaSensorRepository.findById("TT")).thenReturn(Optional.of(sensor));
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, "TT")).thenReturn((null));
//
//        //Act
//
//        int actualResult = readerController.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath);
//
//        //Assert
//
//        assertEquals(2, actualResult);
//    }

//    @Test
//    void seeIfAddReadingsToHouseSensorsWorks() {
//        //Arrange
//        List<ReadingDTO> readingDTOS = new ArrayList<>();
//
//        ReadingDTO readingDTO1 = new ReadingDTO();
//        readingDTO1.setSensorId("TT");
//        readingDTO1.setUnit("C");
//        readingDTO1.setValue(2D);
//        readingDTO1.setDate(validDate1);
//
//        ReadingDTO readingDTO2 = new ReadingDTO();
//        readingDTO2.setSensorId("TT");
//        readingDTO2.setUnit("C");
//        readingDTO2.setValue(2D);
//        readingDTO2.setDate(validDate3);
//
//        readingDTOS.add(readingDTO1);
//        readingDTOS.add(readingDTO2);
//
//        RoomSensor sensor = new RoomSensor("TT", "Sensor", new SensorType("temperature", "C"), validDate1, "B104");
//
//        Mockito.when(houseSensorRepository.findById("TT")).thenReturn(Optional.of(sensor));
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, "TT")).thenReturn((null));
//
//        //Act
//
//        int actualResult = readerController.addReadingsToRoomSensors(readingDTOS, validLogPath);
//
//        //Assert
//
//        assertEquals(2, actualResult);
//    }

    @Test
    void seeIfReadJSONAndDefineHouseWorks() {
        List<String> deviceTypes = new ArrayList<>();
        House house = new House("01", new Local(0, 0, 0), 15, 15, deviceTypes);
        String filePath = "src/test/resources/houseFiles/DataSet_sprint06_House.json";
        assertTrue(readerController.readJSONAndDefineHouse(house, filePath, energyGridService, houseRepository, roomService));
    }

    @Test
    void seeIfReadJSONAndDefineHouseThrowsException() {
        List<String> deviceTypes = new ArrayList<>();
        House house = new House("01", new Local(0, 0, 0), 15, 15, deviceTypes);
        String filePath = "src/test/resources/readingsFiles/DataSet_sprint05_SensorData.json";
        assertThrows(IllegalArgumentException.class,
                () -> readerController.readJSONAndDefineHouse(house, filePath, energyGridService, houseRepository, roomService));

    }
}
