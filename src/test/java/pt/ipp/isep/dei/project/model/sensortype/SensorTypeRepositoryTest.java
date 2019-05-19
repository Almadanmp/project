package pt.ipp.isep.dei.project.model.sensortype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

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
    private SensorTypeCrudeRepo sensorTypeCrudeRepo;
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

        Mockito.when(sensorTypeCrudeRepo.findById(areaName)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> sensorTypeRepository.getById(areaName));

        assertEquals("ERROR: There is no Sensor Type with the selected ID.", exception.getMessage());
    }

    @Test
    void seeIfSizeRepository() {

        SensorType sensorType = new SensorType("Temperature", "Celsius");

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(sensorType);

        int expectedResult = 1;

        Mockito.when(sensorTypeCrudeRepo.findAll()).thenReturn(sensorTypes);

        int result = sensorTypeRepository.size();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSizeRepositoryDoNotChange() {

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(null);

        int expectedResult = 1;

        Mockito.when(sensorTypeCrudeRepo.findAll()).thenReturn(sensorTypes);

        int result = sensorTypeRepository.size();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsEmptyFalse() {

        SensorType sensorType = new SensorType("Temperature", "Celsius");

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(sensorType);

        Mockito.when(sensorTypeCrudeRepo.findAll()).thenReturn(sensorTypes);

        assertFalse(sensorTypeRepository.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<SensorType> sensorTypes = new ArrayList<>();

        Mockito.when(sensorTypeCrudeRepo.findAll()).thenReturn(sensorTypes);

        assertTrue(sensorTypeRepository.isEmpty());
    }

    @Test
    void getAllAsString() {
        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(secondValidType);
        sensorTypes.add(firstValidType);

        Mockito.when(sensorTypeCrudeRepo.findAll()).thenReturn(sensorTypes);

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

        Mockito.when(sensorTypeCrudeRepo.findAll()).thenReturn(sensorTypes);

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

        boolean actualResult = sensorTypeCrudeRepo.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = sensorTypeCrudeRepo.equals(new WaterHeater(new WaterHeaterSpec())); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksForItself() {
        // Act

        boolean actualResult = sensorTypeCrudeRepo.equals(sensorTypeCrudeRepo); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddWorks() {
        //Arrange

        SensorType sensorType = new SensorType("Name", "celsius");
        Mockito.when(sensorTypeCrudeRepo.findByName("Name")).thenReturn(Optional.empty());

        //Act
        boolean actualResult = sensorTypeRepository.add(sensorType);

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddWorksWhenAreaTypeExistsInRepository() {
        //Arrange

        SensorType sensorType = new SensorType("Name", "Celsius");
        Mockito.when(sensorTypeCrudeRepo.findByName("Name")).thenReturn(Optional.of(sensorType));

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

        Mockito.when(sensorTypeCrudeRepo.findById(mockId)).thenReturn(Optional.of(areaType));

        SensorType result = sensorTypeRepository.getById(mockId);

        assertEquals(result.getName(), areaType.getName());
        assertEquals(result.getUnits(), areaType.getUnits());
    }
}
