package pt.ipp.isep.dei.project.controllercli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.mappers.AreaSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.AreaTypeMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudeRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GASettingsController tests class.
 */
@ExtendWith(MockitoExtension.class)
class GASettingsControllerTest {
    private GeographicArea firstValidArea;
    private GeographicArea secondValidArea;
    private AreaType typeCountry;
    private AreaType typeCity;
    private GeographicAreaDTO validGeographicAreaDTO;
    private AreaSensorDTO validAreaSensorDTO1;
    private AreaSensorDTO validAreaSensorDTO2;
    private AreaSensor validAreaSensor1;
    private AreaTypeRepository validAreaTypeRepository;
    private Date date; // Wed Nov 21 05:12:00 WET 2018

    @Mock
    private GeographicAreaRepository validGeographicAreaRepository;

    @Mock
    private GeographicAreaCrudeRepo geographicAreaCrudeRepo;

    @Mock
    AreaTypeCrudeRepo areaTypeCrudeRepo;

    @Mock
    SensorTypeCrudeRepo sensorTypeCrudeRepo;

    @InjectMocks
    private GASettingsController controller;

    @BeforeEach
    void arrangeArtifacts() {

        SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = day.parse("12-12-2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validAreaTypeRepository = new AreaTypeRepository(areaTypeCrudeRepo);
        typeCountry = new AreaType("Country");
        typeCity = new AreaType("City");
        firstValidArea = new GeographicArea("Portugal", typeCountry.getName(),
                2, 5, new Local(21, 33, 5));
        firstValidArea.setId(1L);
        secondValidArea = new GeographicArea("Portugal", typeCity.getName(),
                2, 5, new Local(21, 33, 5));
        secondValidArea.setId(2L);
        validAreaSensor1 = new AreaSensor("RF12345", "SensOne", "Temperature",
                new Local(31, 15, 3), date);
        AreaSensor validAreaSensor2 = new AreaSensor("TT12345", "SensTwo", "Temperature",
                new Local(21, 65, 3), date);
        firstValidArea.addSensor(validAreaSensor1);
        validGeographicAreaDTO = GeographicAreaMapper.objectToDTO(firstValidArea);
        validAreaSensorDTO1 = AreaSensorMapper.objectToDTO(validAreaSensor1);
        validAreaSensorDTO2 = AreaSensorMapper.objectToDTO(validAreaSensor2);
        validGeographicAreaRepository.addAndPersistGA(firstValidArea);
        validGeographicAreaRepository.addAndPersistGA(secondValidArea);
    }

    @Test
    void seeIfPrintGATypeListWorks() {
        // Arrange

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(typeCountry);
        areaTypes.add(typeCity);

        Mockito.when(areaTypeCrudeRepo.findAll()).thenReturn(areaTypes);

        String expectedResult = "---------------\n" +
                "Name: Country \n" +
                "Name: City \n" +
                "---------------\n";

        // Act

        String actualResult = controller.buildGATypeListString(validAreaTypeRepository);


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintGATypeListWorksIfListIsEmpty() {
        // Arrange

        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = controller.buildGATypeListString(validAreaTypeRepository);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfMatchGAByTypeAreaWorks() {
        // Arrange

        List<GeographicArea> expectedResult = new ArrayList<>();
        expectedResult.add(secondValidArea);

        // Act
        Mockito.when(validGeographicAreaRepository.getGeoAreasByType(typeCity.getName())).thenReturn(expectedResult);
        List<GeographicArea> actualResult = controller.matchGAByTypeArea(validGeographicAreaRepository, AreaTypeMapper.objectToDTO(typeCity));

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetTypeAreaName() {

        // Arrange

        String expectedResult = "City";

        // Act

        String actualResult = controller.getTypeAreaName(AreaTypeMapper.objectToDTO(typeCity));

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 001 TESTS

    @Test
    void seeIfCreateTypeAreaWorksEmptyList() {
        // Act

        boolean result = controller.createAndAddTypeAreaToList(validAreaTypeRepository, "City");

        // Assert

        assertTrue(result);
    }


    @Test
    void seeIfNewTAGDoesNotWorkWhenDuplicatedISAdded() {
        // Act

        boolean result = controller.createAndAddTypeAreaToList(validAreaTypeRepository, "Country");

        // Assert

        assertTrue(result);
    }

    //USER STORY 002 TESTS

    @Test
    void seeIfPrintTypeAreaListWorks() {
        // Arrange

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(typeCountry);

        String expectedResult = "---------------\n" +
                "Name: Country \n" +
                "---------------\n";
        Mockito.when(areaTypeCrudeRepo.findAll()).thenReturn(areaTypes);

        // Act

        String actualResult = controller.getTypeAreaList(validAreaTypeRepository);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintTypeAreaListWorksWithTwoTypes() {
        // Arrange

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(typeCountry);
        areaTypes.add(typeCity);

        Mockito.when(areaTypeCrudeRepo.findAll()).thenReturn(areaTypes);

        String expectedResult = "---------------\n" +
                "Name: Country \n" +
                "Name: City \n" +
                "---------------\n";

        // Act

        String actualResult = controller.getTypeAreaList(validAreaTypeRepository);

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfMatchGAByTypeCountry() {
        // Act

        List<GeographicArea> actualResult = controller.matchGAByTypeArea(validGeographicAreaRepository, AreaTypeMapper.objectToDTO(typeCountry));

        // Assert

        assertEquals(0, actualResult.size());
    }

    @Test
    void seeMatchGAByTypeNotInList() {
        // Act

        List<GeographicArea> actualResult = controller.matchGAByTypeArea(validGeographicAreaRepository, AreaTypeMapper.objectToDTO(typeCity));

        // Assert

        assertEquals(0, actualResult.size());
    }

    @Test
    void getTypeAreaName() {
        // Arrange

        String expectedResult = "City";
        String actualResult;

        // Act

        actualResult = controller.getTypeAreaName(AreaTypeMapper.objectToDTO(typeCity));

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeactivateSensor() {
        // Act

        boolean actualResult = controller.deactivateSensor(validAreaSensorDTO1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivateSensorWhenSecondInList() {
        // Act

        boolean actualResult = controller.deactivateSensor(validAreaSensorDTO2);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeactivatedSensorDoesntChange() {
        // Arrange

        validAreaSensor1.deactivateSensor();
        AreaSensorDTO areaSensorDTO = AreaSensorMapper.objectToDTO(validAreaSensor1);

        // Act

        boolean actualResult = controller.deactivateSensor(areaSensorDTO);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfPrintGAList() {
        // Arrange

        List<GeographicArea> list = new ArrayList<>();
        list.add(secondValidArea);
        secondValidArea.setId(0L);
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: City | Latitude: 21.0 | Longitude: 33.0\n" +
                "---------------\n";

        // Act
        Mockito.when(validGeographicAreaRepository.buildStringRepository(list)).thenReturn(expectedResult);
        String result = controller.buildGAListString(validGeographicAreaRepository, list);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetMotherAreaWorks() {
        // Act

        boolean actualResult = controller.setMotherArea(firstValidArea, secondValidArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfSetMotherAreaBreaks() {
        // Act

        boolean actualResult = controller.setMotherArea(firstValidArea, null);

        // Assert

        assertFalse(actualResult);
    }

//USER STORY 008 Tests

    @Test
    void seeIfItsContained() {
        // Arrange

        firstValidArea.setMotherArea(secondValidArea);

        // Act

        boolean actualResult = controller.isAreaContained(secondValidArea, firstValidArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfIndirectlyContained() {
        // Arrange

        GeographicArea grandDaughterGA = new GeographicArea("Porto", "Country",
                2, 4, new Local(21, 33, 5));
        grandDaughterGA.setMotherArea(secondValidArea);
        secondValidArea.setMotherArea(firstValidArea);

        // Act

        boolean actualResult = controller.isAreaContained(firstValidArea, grandDaughterGA);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfNotContained() {
        // Arrange

        GeographicArea grandDaughterGA = new GeographicArea("Oporto", "Country", 2, 4, new Local(21, 33, 5));
        grandDaughterGA.setMotherArea(secondValidArea);
        secondValidArea.setMotherArea(firstValidArea);

        // Act

        boolean actualResult = controller.isAreaContained(grandDaughterGA, firstValidArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeGAId() {
        // Act

        String actualResult = controller.getGeographicAreaId(firstValidArea);

        // Assert

        assertEquals("Portugal", actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDTO() {
        // Arrange

        GeographicAreaDTO expectedResult = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();
        expectedResult.setName("Joana");
        expectedResult.setLocalDTO(localDTO);
        localDTO.setLatitude(12);
        localDTO.setLongitude(13);
        localDTO.setAltitude(13);
        expectedResult.setTypeArea(typeCity.getName());

        // Act

        GeographicAreaDTO result = controller.createGeoAreaDTO("Joana", AreaTypeMapper.objectToDTO(typeCity),
                controller.createLocalDTO(12, 13, 13), 12, 13);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfCreateLocalDTO() {
        // Arrange

        LocalDTO result = controller.createLocalDTO(12, 13, 14);

        // Act

        assertNotNull(result);
    }


    @Test
    void seeIfInputAreaWorks() {
        // Arrange
        List<GeographicArea> geoAreas = new ArrayList<>();
        geoAreas.add(firstValidArea);
        GeographicAreaDTO expectedResult = validGeographicAreaDTO;

        // Act

        InputStream in = new ByteArrayInputStream(firstValidArea.getId().toString().getBytes());
        System.setIn(in);
        Mockito.when(validGeographicAreaRepository.getAll()).thenReturn(geoAreas);
        Mockito.when(validGeographicAreaRepository.get(firstValidArea.getId())).thenReturn(firstValidArea);
        GeographicAreaDTO actualResult = controller.getInputArea(validGeographicAreaRepository);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemoveSensorWorks() {
        // Act

        List<AreaSensor> actualResult = GeographicAreaMapper.dtoToObject(validGeographicAreaDTO).getAreaSensors();
        actualResult.remove(validAreaSensor1);

        controller.removeSensor(validAreaSensorDTO1, validGeographicAreaDTO, validGeographicAreaRepository);


        // Assert

        assertEquals(0, actualResult.size());
    }

    @Test
    void seeIfAddNewGeoAreaToListWorks() {
        // Act
        Mockito.when(validGeographicAreaRepository.addAndPersistGA(GeographicAreaMapper.dtoToObject(validGeographicAreaDTO))).thenReturn(true);
        boolean actualResult = controller.addNewGeoAreaToList(validGeographicAreaRepository, validGeographicAreaDTO);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddNewGeoAreaToListWorksFalseDuplicate() {
        // Arrange

        List<GeographicArea> mockedList = new ArrayList<>();
        mockedList.add(GeographicAreaMapper.dtoToObject(validGeographicAreaDTO));
        // Act

        boolean actualResult = controller.addNewGeoAreaToList(validGeographicAreaRepository, validGeographicAreaDTO);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfInputSensorWorks() {
        // Arrange

        InputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);

        // Act

        AreaSensorDTO actualResult = controller.getInputSensor(validGeographicAreaDTO);

        // Assert

        assertEquals(validAreaSensorDTO1, actualResult);
    }
}