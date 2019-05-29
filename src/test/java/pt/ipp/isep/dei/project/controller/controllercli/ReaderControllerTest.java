//package pt.ipp.isep.dei.project.controller.controllercli;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
//import pt.ipp.isep.dei.project.dto.HouseDTO;
//import pt.ipp.isep.dei.project.dto.ReadingDTO;
//import pt.ipp.isep.dei.project.dto.RoomDTO;
//import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
//import pt.ipp.isep.dei.project.io.ui.reader.ReaderXML;
//import pt.ipp.isep.dei.project.model.Local;
//import pt.ipp.isep.dei.project.model.areatype.AreaType;
//import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
//import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
//import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
//import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
//import pt.ipp.isep.dei.project.model.house.House;
//import pt.ipp.isep.dei.project.model.house.HouseRepository;
//import pt.ipp.isep.dei.project.model.room.Room;
//import pt.ipp.isep.dei.project.model.room.RoomRepository;
//import pt.ipp.isep.dei.project.model.room.RoomSensor;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
///**
// * ReaderController test class.
// */
//@ExtendWith(MockitoExtension.class)
//class ReaderControllerTest {
//
//    private static final String validLogPath = "dumpFiles/dumpLogFile.html";
//    private static final String invalidLogPath = "./resoursagfdgs/logs/logOut.log"; //Não apagar p.f.
//    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());
//    private final InputStream systemIn = System.in;
//    private final PrintStream systemOut = System.out;
//
//    // Common artifacts for testing in this class.
//    @Mock
//    private EnergyGridRepository energyGridRepository;
//    private Date validDate1 = new Date();
//    private Date validDate3 = new Date();
//    private Date validDate4 = new Date();
//    private RoomSensor validRoomSensor1;
//
//    @Mock
//    private HouseRepository houseRepository;
//    @Mock
//    private RoomRepository roomRepository;
//    @Mock
//    private AreaTypeRepository areaTypeRepository;
//    @Mock
//    private GeographicAreaRepository geographicAreaRepository;
//    @InjectMocks
//    private ReaderController readerController;
//
//    @BeforeEach
//    void arrangeArtifacts() {
//        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            validDate1 = validSdf.parse("2016-11-15");
//            validDate3 = validSdf.parse("2017-11-15");
//            validDate4 = validSdf.parse("2017-11-16");
//        } catch (ParseException c) {
//            c.printStackTrace();
//        }
//        validRoomSensor1 = new RoomSensor("SensorID1", "SensorOne", "Temperature", validDate1);
//    }
//
//    @BeforeEach
//    void setUpOutput() {
//        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(testOut));
//        logger.setLevel(Level.FINE);
//    }
//
//    @AfterEach
//    void restoreSystemInputOutput() {
//        System.setIn(systemIn);
//        System.setOut(systemOut);
//    }
//
//    @Test
//    void seeIfAddGeoAreaNodeListToListWorks() {
//        // Arrange
//
//        AreaType areaType = new AreaType("urban area");
//        GeographicArea geographicArea = new GeographicArea("ISEP", "urban area", 0.249, 0.261,
//                new Local(41.178553, -8.608035, 111.0));
//        geographicArea.setDescription("Campus do ISEP");
//        Local local = new Local(41.178553, -8.608035, 111.0);
//
//        ReaderXML reader = new ReaderXML();
//        Document doc = reader.readFile("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_one_GA.xml");
//        doc.getDocumentElement().normalize();
//        NodeList nodeListToTest = doc.getElementsByTagName("geographical_area");
//
//        Mockito.when(areaTypeRepository.getAreaTypeByName("urban area")).thenReturn(areaType);
//        Mockito.when(geographicAreaRepository.createGA("ISEP", "urban area", 0.261, 0.249, local)).thenReturn(geographicArea);
//        Mockito.when(geographicAreaRepository.addAndPersistGA(geographicArea)).thenReturn(true);
//
//        // Act
//
//        int actualResult = readerController.addGeoAreaNodeListToList(nodeListToTest, geographicAreaRepository, areaTypeRepository);
//
//        // Assert
//
//        assertEquals(1, actualResult);
//    }
//
//    @Test
//    void seeIfAddGeoAreaNodeListToListWorksWhenFileIsWrong() {
//        // Arrange
//
//        ReaderXML reader = new ReaderXML();
//        Document doc = reader.readFile("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_no_GAs.xml");
//        doc.getDocumentElement().normalize();
//        NodeList nodeListToTest = doc.getElementsByTagName("geographical_area");
//
//        // Act
//
//        int actualResult = readerController.addGeoAreaNodeListToList(nodeListToTest, geographicAreaRepository, areaTypeRepository);
//
//        // Assert
//
//        assertEquals(0, actualResult);
//    }
//
//    @Test
//    void seeIfAddGeoAreaNodeListToListWorksWhenHasWrongParameters() {
//        // Arrange
//
//        AreaType areaType = new AreaType("urban area");
//        GeographicArea geographicArea = new GeographicArea("ISEP", "urban area", 0.249, 0.261,
//                new Local(41.178553, -8.608035, 111.0));
//        geographicArea.setDescription("Campus do ISEP");
//        Local local = new Local(41.178553, -8.608035, 111.0);
//
//        ReaderXML reader = new ReaderXML();
//        Document doc = reader.readFile("src/test/resources/geoAreaFiles/DataSet_sprint05_GA_test_one_GA.xml");
//        doc.getDocumentElement().normalize();
//        NodeList nodeListToTest = doc.getElementsByTagName("geographical_area");
//
//        Mockito.when(areaTypeRepository.getAreaTypeByName("urban area")).thenReturn(areaType);
//        Mockito.when(geographicAreaRepository.createGA("ISEP", "urban area", 0.261, 0.249, local)).thenReturn(geographicArea);
//        Mockito.when(geographicAreaRepository.addAndPersistGA(geographicArea)).thenReturn(false);
//
//        // Act
//
//        int actualResult = readerController.addGeoAreaNodeListToList(nodeListToTest, geographicAreaRepository, areaTypeRepository);
//
//        // Assert
//
//        assertEquals(0, actualResult);
//    }
//
//    @Test
//    void seeIfAddReadingsToRoomSensorsWorks() {
//        // Arrange
//
//        List<ReadingDTO> readingDTOS = new ArrayList<>();
//        Room room = new Room("Room1", "Description", 1, 1, 1, 1, "House");
//        room.addSensor(validRoomSensor1);
//        ReadingDTO readingDTO1 = new ReadingDTO();
//        readingDTO1.setSensorId("SensorID1");
//        readingDTO1.setValue(20D);
//        readingDTO1.setUnit("C");
//        readingDTO1.setDate(validDate4);
//        readingDTOS.add(readingDTO1);
//
//        ReadingDTO readingDTO2 = new ReadingDTO();
//        readingDTO2.setSensorId("SensorID1");
//        readingDTO2.setValue(20D);
//        readingDTO2.setUnit("C");
//        readingDTO2.setDate(validDate1);
//        readingDTOS.add(readingDTO2);
//
//        // Act
//        Mockito.when(roomRepository.addReadingsToRoomSensors(readingDTOS, validLogPath)).thenReturn(2);
//        int actualResult = readerController.addReadingsToRoomSensors(readingDTOS, validLogPath, roomRepository);
//
//        // Assert
//
//        assertEquals(2, actualResult);
//    }
//
//    @Test
//    void seeIfAddRoomsToRepositoryWorks() {
//        // Arrange
//
//        Room room = new Room("room1", "Single Bedroom", 19, 23456789, 5, 3, "HouseID");
//        RoomDTO roomDTO1 = RoomMapper.objectToDTO(room);
//
//        List<RoomDTO> roomDTOS = new ArrayList<>();
//        roomDTOS.add(roomDTO1);
//
//        List<EnergyGridDTO> energyGridDTOS = new ArrayList<>();
//
//        EnergyGridDTO energyGridDTO1 = new EnergyGridDTO();
//        energyGridDTO1.setHouseID("HouseID");
//        energyGridDTO1.setName("ValidGrid1");
//        energyGridDTO1.setMaxContractedPower(20D);
//        energyGridDTO1.setRoomDTOS(roomDTOS);
//        energyGridDTO1.setPowerSourceDTOS(new ArrayList<>());
//
//        energyGridDTOS.add(energyGridDTO1);
//
//        Mockito.when(roomRepository.addRoomDTOWithoutSensorsAndDevicesToCrudRepository(roomDTO1)).thenReturn(true);
//
//        // Act
//
//        readerController.addRoomsToRepository(energyGridDTOS, "NewHouseID");
//
//        // Assert
//
//        assertEquals("NewHouseID", roomDTO1.getHouseId());
//    }
//
//    @Test
//    void seeIfAddRoomsToRepositoryWorksWhenNotAdded() {
//        // Arrange
//
//        Room room = new Room("room1", "Single Bedroom", 19, 23456789, 5, 3, "HouseID");
//        RoomDTO roomDTO1 = RoomMapper.objectToDTO(room);
//
//        List<RoomDTO> roomDTOS = new ArrayList<>();
//        roomDTOS.add(roomDTO1);
//
//        List<EnergyGridDTO> energyGridDTOS = new ArrayList<>();
//
//        EnergyGridDTO energyGridDTO1 = new EnergyGridDTO();
//        energyGridDTO1.setHouseID("HouseID");
//        energyGridDTO1.setName("ValidGrid1");
//        energyGridDTO1.setMaxContractedPower(20D);
//        energyGridDTO1.setRoomDTOS(roomDTOS);
//        energyGridDTO1.setPowerSourceDTOS(new ArrayList<>());
//
//        energyGridDTOS.add(energyGridDTO1);
//
//        Mockito.when(roomRepository.addRoomDTOWithoutSensorsAndDevicesToCrudRepository(roomDTO1)).thenReturn(false);
//
//        // Act
//
//        readerController.addRoomsToRepository(energyGridDTOS, "NewHouseID");
//
//        // Assert
//
//        assertEquals("NewHouseID", roomDTO1.getHouseId());
//    }
//
//    @Test
//    void seeIfAddGridsToRepositoryWorks() {
//        // Arrange
//
//        List<EnergyGridDTO> energyGridDTOS = new ArrayList<>();
//
//        EnergyGridDTO energyGridDTO1 = new EnergyGridDTO();
//        energyGridDTO1.setHouseID("HouseID");
//        energyGridDTO1.setName("ValidGrid1");
//        energyGridDTO1.setMaxContractedPower(20D);
//        energyGridDTO1.setRoomDTOS(new ArrayList<>());
//        energyGridDTO1.setPowerSourceDTOS(new ArrayList<>());
//
//        energyGridDTOS.add(energyGridDTO1);
//
//        Mockito.when(energyGridRepository.createEnergyGridWithNameRoomsAndPowerSources(energyGridDTO1)).thenReturn(true);
//
//        // Act
//
//        readerController.addGridsToRepository(energyGridDTOS, "NewHouseID");
//
//        // Assert
//
//        assertEquals("NewHouseID", energyGridDTO1.getHouseID());
//    }
//
//    @Test
//    void seeIfAddGridsToRepositoryWorksWhenIsNotAdded() {
//        // Arrange
//
//        List<EnergyGridDTO> energyGridDTOS = new ArrayList<>();
//
//        EnergyGridDTO energyGridDTO1 = new EnergyGridDTO();
//        energyGridDTO1.setHouseID("HouseID");
//        energyGridDTO1.setName("ValidGrid1");
//        energyGridDTO1.setMaxContractedPower(20D);
//        energyGridDTO1.setRoomDTOS(new ArrayList<>());
//        energyGridDTO1.setPowerSourceDTOS(new ArrayList<>());
//
//        energyGridDTOS.add(energyGridDTO1);
//
//        Mockito.when(energyGridRepository.createEnergyGridWithNameRoomsAndPowerSources(energyGridDTO1)).thenReturn(false);
//
//        // Act
//
//        readerController.addGridsToRepository(energyGridDTOS, "NewHouseID");
//
//        // Assert
//
//        assertEquals("NewHouseID", energyGridDTO1.getHouseID());
//    }
//
//
//    @Test
//    void seeIfAddReadingsToGeographicAreaSensorsWorks() {
//        // Arrange
//
//        List<ReadingDTO> readingDTOS = new ArrayList<>();
//
//        ReadingDTO readingDTO1 = new ReadingDTO();
//        readingDTO1.setSensorId("RF12345");
//        readingDTO1.setValue(20D);
//        readingDTO1.setUnit("C");
//        readingDTO1.setDate(validDate1);
//        readingDTOS.add(readingDTO1);
//
//        ReadingDTO readingDTO2 = new ReadingDTO();
//        readingDTO2.setSensorId("RF12345");
//        readingDTO2.setValue(20D);
//        readingDTO2.setUnit("C");
//        readingDTO2.setDate(validDate3);
//        readingDTOS.add(readingDTO2);
//
//        Mockito.when(geographicAreaRepository.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath)).thenReturn(2);
//        // Act
//        int actualResult = readerController.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath, geographicAreaRepository);
//
//        // Assert
//
//        assertEquals(2, actualResult);
//    }
//
//    @Test
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
//        //Act
//        Mockito.when(geographicAreaRepository.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath)).thenReturn(2);
//        int actualResult = readerController.addReadingsToGeographicAreaSensors(readingDTOS, validLogPath, geographicAreaRepository);
//
//        //Assert
//
//        assertEquals(2, actualResult);
//    }
//
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
//        //Act
//        Mockito.when(roomRepository.addReadingsToRoomSensors(readingDTOS, validLogPath)).thenReturn(2);
//        int actualResult = readerController.addReadingsToRoomSensors(readingDTOS, validLogPath, roomRepository);
//
//        //Assert
//
//        assertEquals(2, actualResult);
//    }
//
//    @Test
//    void seeIfReadJSONAndDefineHouseWorks() {
//        //Arrange
//        List<String> deviceTypes = new ArrayList<>();
//        House house = new House("01", new Local(0, 0, 0), 15, 15, deviceTypes);
//        String filePath = "src/test/resources/houseFiles/DataSet_sprint06_House.json";
//
//        HouseDTO houseDTO = new HouseDTO();
//
//        houseRepository.updateHouse(houseDTO);
//
//        //Act
//
//        boolean actualResult = readerController.readJSONAndDefineHouse(house, filePath);
//        //Assert
//
//        assertTrue(actualResult);
//    }
//
//    @Test
//    void seeIfReadJSONAndDefineHouseThrowsException() {
//        List<String> deviceTypes = new ArrayList<>();
//        House house = new House("01", new Local(0, 0, 0), 15, 15, deviceTypes);
//        String filePath = "src/test/resources/readingsFiles/DataSet_sprint05_SensorData.json";
//        assertThrows(IllegalArgumentException.class,
//                () -> readerController.readJSONAndDefineHouse(house, filePath));
//
//    }
//}
