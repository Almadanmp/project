package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testng.Assert;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.repository.TypeSensorRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * TypeSensorList tests class
 */

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {MainUI.class},
        loader = AnnotationConfigContextLoader.class)
class TypeSensorListTest {
    // Common Testing Artifacts for this test class.

    private TypeSensor firstTypeSensor; // Is in the list.
    private TypeSensor secondTypeSensor; // Is not in the list.

    @Autowired
    TypeSensorRepository typeSensorRepository;

    @Autowired
    private TypeSensorList validList;

    @BeforeEach
    void arrangeArtifacts() {
        firstTypeSensor = new TypeSensor("Temperature", "Celsius");
        secondTypeSensor = new TypeSensor("Rainfall", "l/m2");
        validList.add(firstTypeSensor);
    }

    @Test
    void seeIfGetAllAsString() {
        // Arrange
        String expectedResult = "There are no Sensor Types Configured\n";
        // Act
        String actualResult = validList.getAllAsString();
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateTypeAreaWorks() {
        // Act
        TypeSensor type1 = validList.createTypeSensor("Temperature", "Celsius");
        // Assert
        Assert.assertEquals(type1, firstTypeSensor);
    }

    /*
        @Test
        void seeIfSizeRepository() {
            // Arrange

            testList1.addWithoutPersisting(firstTypeSensor);
            testList1.addWithoutPersisting(secondTypeSensor);
            int expectedResult = 2;
            // Act
            int actualResult = testList1.sizeRepository();
            // Assert
            assertEquals(expectedResult, actualResult);
        }
    */
    @Test
    void seeIfAddSensorTypeWorks() {
        // Arrange

        TypeSensorList testList = new TypeSensorList();
        testList.add(firstTypeSensor);

        // Act

        boolean actualResult1 = testList.add(secondTypeSensor);
        boolean actualResult2 = testList.add(new TypeSensor("Pressure", "Percentage"));
        boolean actualResult3 = testList.add(firstTypeSensor);


        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void testBuildString() {
        // Arrange

        String expectedResult1 = "---------------\n" +
                "0) Name: Temperature | Unit: Celsius\n" +
                "---------------\n";
        String expectedResult2 = "Invalid List - List is Empty\n";
        TypeSensorList emptyList = new TypeSensorList();
        TypeSensorList testList2 = new TypeSensorList();
        testList2.add(firstTypeSensor);
        // Act

        String actualResult1 = testList2.buildString();
        String actualResult2 = emptyList.buildString();

        // Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        TypeSensorList emptyList = new TypeSensorList();
        TypeSensorList twoElementsList = new TypeSensorList();

        twoElementsList.add(firstTypeSensor);
        twoElementsList.add(secondTypeSensor);

        // Act

        boolean actualResult1 = emptyList.isEmpty();
        boolean actualResult2 = validList.isEmpty();
        boolean actualResult3 = twoElementsList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange
        validList.add(firstTypeSensor);
        validList.add(secondTypeSensor);
        TypeSensorList testList = new TypeSensorList();
        testList.add(firstTypeSensor);
        testList.add(secondTypeSensor);


        // Act

        boolean actualResult = testList.equals(validList);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange
        validList.add(secondTypeSensor);
        TypeSensorList testList = new TypeSensorList();
        testList.add(firstTypeSensor);

        // Act

        boolean actualResult = testList.equals(validList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstanceOf() {
        //Act

        boolean actualResult = validList.equals(new RoomList()); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        // Act

        boolean actualResult = validList.equals(validList); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Act

        int expectedResult = 1;
        int actualResult = validList.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetElementWorksEmptyList() {
        // Arrange

        TypeSensorList emptyList = new TypeSensorList();

        // Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        // Assert

        assertEquals("The type sensor list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetElementWorks() {
        // Arrange

        validList.add(secondTypeSensor);

        // Act

        TypeSensor actualResult1 = validList.get(0);
        TypeSensor actualResult2 = validList.get(1);

        // Assert

        assertEquals(firstTypeSensor, actualResult1);
        assertEquals(secondTypeSensor, actualResult2);
    }

    @Test
    void seeIfSizeWorks() {
        // Arrange

        TypeSensorList emptyList = new TypeSensorList();
        validList.add(secondTypeSensor);

        // Act

        int actualResult1 = emptyList.size();
        int actualResult2 = validList.size();

        // Assert

        assertEquals(0, actualResult1);
        assertEquals(2, actualResult2);
    }

    @Test
    void seeIfGetElementsAsArrayWorks() {
        // Arrange
        TypeSensorList testList = new TypeSensorList();
        TypeSensor[] expectedResult1 = new TypeSensor[0];
        TypeSensor[] expectedResult2 = new TypeSensor[1];
        TypeSensor[] expectedResult3 = new TypeSensor[2];

        TypeSensorList emptyList = new TypeSensorList();
        TypeSensorList twoElementsList = new TypeSensorList();

        twoElementsList.add(firstTypeSensor);
        twoElementsList.add(secondTypeSensor);

        expectedResult2[0] = firstTypeSensor;
        expectedResult3[0] = firstTypeSensor;
        expectedResult3[1] = secondTypeSensor;

        testList.add(firstTypeSensor);
        // Act

        TypeSensor[] actualResult1 = emptyList.getElementsAsArray();
        TypeSensor[] actualResult2 = testList.getElementsAsArray();
        TypeSensor[] actualResult3 = twoElementsList.getElementsAsArray();

        // Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfIsEmptyRepositoryWorks(){
        // Arrange

        typeSensorRepository.deleteAll();

        // Act

        boolean result = validList.isEmptyRepository();

        // Assert

        assertTrue(result);
    }
}