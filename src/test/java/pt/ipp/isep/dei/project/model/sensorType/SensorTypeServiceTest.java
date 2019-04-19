package pt.ipp.isep.dei.project.model.sensorType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorTypeService tests class.
 */
@ExtendWith(MockitoExtension.class)
class SensorTypeServiceTest {
    // Common testing artifacts for this class.

    private SensorType firstValidType;
    private SensorType secondValidType;

    @Mock
    private SensorTypeRepository sensorTypeRepository;

    private SensorTypeService sensorTypeService;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        sensorTypeService = new SensorTypeService(this.sensorTypeRepository);
        firstValidType = new SensorType("Temperature", "Celsius");
        secondValidType = new SensorType("Rainfall", "l/m2");
    }


    @Test
    void seeIfCreateAreaType() {
        SensorType expectedResult = new SensorType("Movement", "Celsius");

        SensorType result = sensorTypeService.createTypeSensor("Movement", "Celsius");

        assertEquals(expectedResult, result);
    }


//    @Test
//    void seeIfAddAreaType() {
//        SensorType sensorType = new SensorType("Temperature","Celsius");
//
//        Mockito.when(sensorTypeRepository.findByName(sensorType.getName())).thenReturn(sensorType);
//
//        assertTrue(sensorTypeService.add(sensorType));
//    }

    @Test
    void seeIfGetTypeAreaByIdRepository() {
        long mockId = 1234;

        SensorType sensorType = new SensorType("Temperature", "Celsius");
        sensorType.getId();

        Mockito.when(sensorTypeRepository.findById(mockId)).thenReturn(Optional.of(sensorType));

        SensorType result = sensorTypeService.getById(mockId);

        assertEquals(result.getId(), sensorType.getId());
        assertEquals(result.getName(), sensorType.getName());
    }

    @Test
    void seeIfGetTypeAreaByIdRepositoryNull() {
        long mockId = 1234;

        Mockito.when(sensorTypeRepository.findById(mockId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> sensorTypeService.getById(mockId));

        assertEquals("ERROR: There is no Sensor Type with the selected ID.", exception.getMessage());
    }

    @Test
    void seeIfSizeRepository() {

        SensorType sensorType = new SensorType("Temperature", "Celsius");

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(sensorType);

        Mockito.when(sensorTypeRepository.findAll()).thenReturn(sensorTypes);

        int result = sensorTypeService.size();

        assertEquals(result, 1);
    }

    @Test
    void seeIfSizeRepositoryDoNotChange() {

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(null);

        Mockito.when(sensorTypeRepository.findAll()).thenReturn(sensorTypes);

        int result = sensorTypeService.size();

        assertEquals(result, 1);
    }

    @Test
    void seeIfIsEmptyFalse() {

        SensorType sensorType = new SensorType("Temperature", "Celsius");

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(sensorType);

        Mockito.when(sensorTypeRepository.findAll()).thenReturn(sensorTypes);

        assertFalse(sensorTypeService.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<SensorType> sensorTypes = new ArrayList<>();

        Mockito.when(sensorTypeRepository.findAll()).thenReturn(sensorTypes);

        assertTrue(sensorTypeService.isEmpty());
    }

    @Test
    void getAllAsString() {
        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(secondValidType);
        sensorTypes.add(firstValidType);

        Mockito.when(sensorTypeRepository.findAll()).thenReturn(sensorTypes);

        String expectedResult = "---------------\n" +
                "0) Name: Rainfall | Unit: l/m2 \n" +
                "0) Name: Temperature | Unit: Celsius \n" +
                "---------------\n";

        String result = sensorTypeService.buildString();

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllAsStringEmpty() {
        List<SensorType> sensorTypes = new ArrayList<>();

        Mockito.when(sensorTypeRepository.findAll()).thenReturn(sensorTypes);

        String expectedResult = "Invalid List - List is Empty\n";

        String result = sensorTypeService.buildString();

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        List<SensorType> testList = new ArrayList<>();
        testList.add(secondValidType);

        // Act

        boolean actualResult = sensorTypeService.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = sensorTypeService.equals(new RoomService()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksForItself() {
        // Act

        boolean actualResult = sensorTypeService.equals(sensorTypeService); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }


}
