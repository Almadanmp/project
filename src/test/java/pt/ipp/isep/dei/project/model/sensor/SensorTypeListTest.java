//package pt.ipp.isep.dei.project.model.sensor;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.annotation.DirtiesContext;
//import pt.ipp.isep.dei.project.model.RoomList;
//import pt.ipp.isep.dei.project.repository.SensorTypeRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * TypeSensorList tests class
// */
//
//@ExtendWith(MockitoExtension.class)
//class SensorTypeListTest {
//    // Common Testing Artifacts for this test class.
//
//    @Mock
//    private SensorTypeRepository sensorTypeRepository;
//
//    private SensorType firstSensorType; // Is in the list.
//    private SensorType secondSensorType; // Is not in the list.
//
//    private SensorTypeService validList;
//
//    @BeforeEach
//    void arrangeArtifacts() {
//        MockitoAnnotations.initMocks(this);
//        validList = new SensorTypeService(sensorTypeRepository);
//        firstSensorType = new SensorType("Temperature", "Celsius");
//        secondSensorType = new SensorType("Rainfall", "l/m2");
//        validList.add(firstSensorType);
//    }
//
//    @Test
//    void seeIfGetAllAsString() {
//        // Arrange
//        String expectedResult = "Invalid List - List is Empty\n";
//        // Act
//        SensorTypeService sensorTypeList = new SensorTypeService(sensorTypeRepository);
//        String actualResult = sensorTypeList.buildStringRepository();
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfCreateTypeAreaWorks() {
//        // Act
//        SensorType type1 = validList.createTypeSensor("Temperature", "Celsius");
//        // Assert
//        assertEquals(type1, firstSensorType);
//    }
//
//    @Test
//    void seeIfAddSensorTypeWorks() {
//        // Act
//
//        List<SensorType> sensorTypes = new ArrayList<>();
//        SensorTypeService service = new SensorTypeService(sensorTypeRepository);
//        boolean actualResult1 = service.add(secondSensorType);
//        boolean actualResult2 = service.add(new SensorType("Pressure", "Percentage"));
//        boolean actualResult3 = service.add(firstSensorType);
//
//        // Assert
//
//        assertTrue(actualResult1);
//        assertTrue(actualResult2);
//        //assertFalse(actualResult3);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void seeIfBuildStringWorks() {
//        // Arrange
//        List<SensorType> sensorTypes = new ArrayList<>();
//        Mockito.when(sensorTypeRepository.findAll()).thenReturn(sensorTypes);
//        String expectedResult1 = "---------------\n" +
//                "0) Name: Temperature | Unit: Celsius\n" +
//                "---------------\n";
//
//        // Act
//
//        String actualResult1 = validList.buildStringRepository();
//
//        // Assert
//
//        assertEquals(expectedResult1, actualResult1);
//    }
//
//    @Test
//    void seeIfBuildStringWorksFalseEmpty() {
//        // Arrange
//
//        String expectedResult = "Invalid List - List is Empty\n";
//        SensorTypeService testList = new SensorTypeService(sensorTypeRepository);
//
//        // Act
//
//        String actualResult = testList.buildStringRepository();
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfIsEmptyWorks() {
//        // Arrange
//
//        SensorTypeService emptyList = new SensorTypeService(sensorTypeRepository);
//        // Act
//
//        boolean actualResult1 = emptyList.isEmpty();
//        boolean actualResult2 = validList.isEmpty();
//
//        // Assert
//
//        assertTrue(actualResult1);
//        assertFalse(actualResult2);
//    }
//
//    @Test
//    void seeIfEqualsWorksFalse() {
//        // Arrange
//        SensorTypeService tempSensorList = new SensorTypeService(sensorTypeRepository);
//
//        // Act
//
//        boolean actualResult = tempSensorList.equals(validList);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }
//
//    @Test
//    void seeIfEqualsWorksNotAnInstanceOf() {
//        //Act
//
//        boolean actualResult = validList.equals(new RoomList()); // Needed for Sonarqube testing purposes.
//
//        // Assert
//
//        assertFalse(actualResult);
//    }
//
//    @Test
//    void seeIfEqualsWorksOnItself() {
//        // Act
//
//        boolean actualResult = validList.equals(validList); // Needed for Sonarqube testing purposes.
//
//        // Assert
//
//        assertTrue(actualResult);
//    }
//
//    @Test
//    void hashCodeDummyTest() {
//        // Act
//
//        int expectedResult = 1;
//        int actualResult = validList.hashCode();
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
////    @Test
////    void seeIfGetElementWorksEmptyList() {
////        // Arrange
////
////        SensorTypeService emptyList = new SensorTypeService(sensorTypeRepository);
////
////        // Act
////
////        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
////
////        // Assert
////
////        assertEquals("The type sensor list is empty.", exception.getMessage());
////    }
//
////    @DirtiesContext
////    @Test
////    void seeIfGetElementWorks() {
////        // Arrange
////
////        validList.add(secondSensorType);
////
////        // Act
////
////        SensorType actualResult1 = validList.get(0);
////        SensorType actualResult2 = validList.get(1);
////
////        // Assert
////
////        assertEquals(firstSensorType, actualResult1);
////        assertEquals(secondSensorType, actualResult2);
////    }
//
//
//    @Test
//    void seeIfSizeWorks() {
//        // Arrange
//
//        SensorTypeService emptyList = new SensorTypeService(sensorTypeRepository);
//        validList.add(secondSensorType);
//
//        // Act
//
//        int actualResult1 = emptyList.size();
//        int actualResult2 = validList.size();
//
//        // Assert
//
//        assertEquals(0, actualResult1);
//        assertEquals(2, actualResult2);
//    }
//
//
////    @Test
////    void seeIfGetElementsAsArrayWorks() {
////        // Arrange
////        SensorType[] expectedResult1 = new SensorType[0];
////        SensorType[] expectedResult3 = new SensorType[2];
////        expectedResult3[0] = firstSensorType;
////        expectedResult3[1] = secondSensorType;
////        SensorTypeService emptyList = new SensorTypeService(sensorTypeRepository);
////
////        validList.add(secondSensorType);
////
////        // Act
////
////        SensorType[] actualResult1 = emptyList.getElementsAsArray();
////        SensorType[] actualResult3 = validList.getElementsAsArray();
////
////        // Assert
////
////        assertArrayEquals(expectedResult1, actualResult1);
////        assertArrayEquals(expectedResult3, actualResult3);
////    }
//}