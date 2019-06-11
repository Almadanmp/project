package pt.ipp.isep.dei.project.model.sensortype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.SensorTypeDTO;
import pt.ipp.isep.dei.project.dto.mappers.SensorTypeMapper;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.repository.SensorTypeCrudRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorTypeService tests class.
 */
@ExtendWith(MockitoExtension.class)
class SensorTypeRepositoryTest {
    // Common testing artifacts for this class.

    private SensorType firstValidType;
    private SensorType secondValidType;
    @Mock
    private SensorTypeCrudRepo sensorTypeCrudRepo;
    @InjectMocks
    private SensorTypeRepository sensorTypeRepository;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        firstValidType = new SensorType("Temperature", "Celsius");
        secondValidType = new SensorType("Rainfall", "l/m2");
    }


    @Test
    void seeIfCreateAreaType() {
        SensorType expectedResult = new SensorType("Movement", "Celsius");

        SensorType result = sensorTypeRepository.createTypeSensor("Movement", "Celsius");

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfGetTypeAreaByIdRepositoryNull() {
        String areaName = null;

        Mockito.when(sensorTypeCrudRepo.findById(areaName)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> sensorTypeRepository.getById(areaName));

        assertEquals("ERROR: There is no Sensor Type with the selected ID.", exception.getMessage());
    }

    @Test
    void seeIfSizeRepository() {

        SensorType sensorType = new SensorType("Temperature", "Celsius");

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(sensorType);

        int expectedResult = 1;

        Mockito.when(sensorTypeCrudRepo.findAll()).thenReturn(sensorTypes);

        int result = sensorTypeRepository.size();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSizeRepositoryDoNotChange() {

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(null);

        int expectedResult = 1;

        Mockito.when(sensorTypeCrudRepo.findAll()).thenReturn(sensorTypes);

        int result = sensorTypeRepository.size();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsEmptyFalse() {

        SensorType sensorType = new SensorType("Temperature", "Celsius");

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(sensorType);

        Mockito.when(sensorTypeCrudRepo.findAll()).thenReturn(sensorTypes);

        assertFalse(sensorTypeRepository.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<SensorType> sensorTypes = new ArrayList<>();

        Mockito.when(sensorTypeCrudRepo.findAll()).thenReturn(sensorTypes);

        assertTrue(sensorTypeRepository.isEmpty());
    }

    @Test
    void getAllAsString() {
        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(secondValidType);
        sensorTypes.add(firstValidType);

        Mockito.when(sensorTypeCrudRepo.findAll()).thenReturn(sensorTypes);

        String expectedResult = "---------------\n" +
                "Name: Rainfall | Unit: l/m2 \n" +
                "Name: Temperature | Unit: Celsius \n" +
                "---------------\n";

        String result = sensorTypeRepository.buildString();

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllAsStringEmpty() {
        List<SensorType> sensorTypes = new ArrayList<>();

        Mockito.when(sensorTypeCrudRepo.findAll()).thenReturn(sensorTypes);

        String expectedResult = "Invalid List - List is Empty\n";

        String result = sensorTypeRepository.buildString();

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        List<SensorType> testList = new ArrayList<>();
        testList.add(secondValidType);

        // Act

        boolean actualResult = sensorTypeCrudRepo.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = sensorTypeCrudRepo.equals(new WaterHeater(new WaterHeaterSpec())); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksForItself() {
        // Act

        boolean actualResult = sensorTypeCrudRepo.equals(sensorTypeCrudRepo); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddWorks() {
        //Arrange

        SensorType sensorType = new SensorType("Name", "celsius");
        Mockito.when(sensorTypeCrudRepo.findByName("Name")).thenReturn(Optional.empty());

        //Act
        boolean actualResult = sensorTypeRepository.add(sensorType);

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddWorksWhenAreaTypeExistsInRepository() {
        //Arrange

        SensorType sensorType = new SensorType("Name", "Celsius");
        Mockito.when(sensorTypeCrudRepo.findByName("Name")).thenReturn(Optional.of(sensorType));

        //Act
        boolean actualResult = sensorTypeRepository.add(sensorType);

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddWorksWhenAreaTypeAlreadyExistsInRepositoryFalse() {

        //Act
        boolean actualResult = sensorTypeRepository.add(firstValidType);

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetTypeAreaByIdRepository() {
        String mockId = "temperature";

        SensorType areaType = new SensorType("temperature", "C");

        sensorTypeRepository.add(areaType);

        Mockito.when(sensorTypeCrudRepo.findById(mockId)).thenReturn(Optional.of(areaType));

        SensorType result = sensorTypeRepository.getById(mockId);

        assertEquals(result.getName(), areaType.getName());
        assertEquals(result.getUnits(), areaType.getUnits());
    }

    @Test
    void seeIfGetAreaTypeByNameWorks() {
        //Arrange

        Mockito.when(sensorTypeCrudRepo.findByName(firstValidType.getName())).thenReturn(Optional.of(firstValidType));

        //Act

        SensorType actualResult = sensorTypeRepository.getTypeSensorByName(firstValidType.getName(), "cº");

        //Assert

        assertEquals(firstValidType, actualResult);
    }

    @Test
    void seeIfGetAreaTypeByNameNoMatches() {
        //Arrange

        Mockito.when(sensorTypeCrudRepo.findByName(firstValidType.getName())).thenReturn(Optional.empty());

        //Act

        SensorType actualResult = sensorTypeRepository.getTypeSensorByName(firstValidType.getName(), "cº");

        //Assert

        assertNull(actualResult);
    }

    @Test
    void seeIfGetTypeNamesWorks() {
        List<SensorTypeDTO> sensorTypeDTOS = new ArrayList<>();
        sensorTypeDTOS.add(SensorTypeMapper.objectToDTO(secondValidType));
        sensorTypeDTOS.add(SensorTypeMapper.objectToDTO(firstValidType));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Rainfall");
        expectedResult.add("Temperature");

        List<String> result = sensorTypeRepository.getTypeNames(sensorTypeDTOS);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAllSensorTypeDTOWorks() {
        List<SensorTypeDTO> expectedResult = new ArrayList<>();
        expectedResult.add(SensorTypeMapper.objectToDTO(secondValidType));
        expectedResult.add(SensorTypeMapper.objectToDTO(firstValidType));

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(firstValidType);
        sensorTypes.add(secondValidType);

        Mockito.when(sensorTypeCrudRepo.findAll()).thenReturn(sensorTypes);

        List<SensorTypeDTO> result = sensorTypeRepository.getAllSensorTypeDTO();

        assertEquals(expectedResult.size(), result.size());
    }
    @Test
    void seeIfGetAllSensorTypeDTOWorksEmptyList() {
        List<SensorTypeDTO> expectedResult = new ArrayList<>();


        Mockito.when(sensorTypeCrudRepo.findAll()).thenReturn(null);

        List<SensorTypeDTO> result = sensorTypeRepository.getAllSensorTypeDTO();

        assertEquals(expectedResult, result);
    }
}
