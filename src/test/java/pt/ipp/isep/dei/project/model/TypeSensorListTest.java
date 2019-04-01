package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testng.Assert;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.model.sensor.TypeSensorList;

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
    private TypeSensorList validList;

    @BeforeEach
    void arrangeArtifacts() {
        firstTypeSensor = new TypeSensor("Temperature", "Celsius");
        secondTypeSensor = new TypeSensor("Rainfall", "l/m2");
        validList.add(firstTypeSensor);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void seeIfGetAllAsString() {
        // Arrange
        String expectedResult = "Invalid List - List is Empty\n";
        // Act
        TypeSensorList typeSensorList = new TypeSensorList();
        String actualResult = typeSensorList.buildString();
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

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void seeIfAddSensorTypeWorks() {
        // Act

        boolean actualResult1 = validList.add(secondTypeSensor);
        boolean actualResult2 = validList.add(new TypeSensor("Pressure", "Percentage"));
        boolean actualResult3 = validList.add(firstTypeSensor);


        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expectedResult1 = "---------------\n" +
                "0) Name: Temperature | Unit: Celsius\n" +
                "---------------\n";

        // Act

        String actualResult1 = validList.buildString();

        // Assert

        assertEquals(expectedResult1, actualResult1);
    }

    @Test
    void seeIfBuildStringWorksFalseEmpty() {
        // Arrange

        String expectedResult = "Invalid List - List is Empty\n";
        TypeSensorList testList = new TypeSensorList();

        // Act

        String actualResult = testList.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        TypeSensorList emptyList = new TypeSensorList();
        // Act

        boolean actualResult1 = emptyList.isEmpty();
        boolean actualResult2 = validList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange
        TypeSensorList tempSensorList = new TypeSensorList();

        // Act

        boolean actualResult = tempSensorList.equals(validList);

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

    @DirtiesContext
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

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
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


    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void seeIfGetElementsAsArrayWorks() {
        // Arrange
        TypeSensor[] expectedResult1 = new TypeSensor[0];
        TypeSensor[] expectedResult3 = new TypeSensor[2];
        expectedResult3[0] = firstTypeSensor;
        expectedResult3[1] = secondTypeSensor;
        TypeSensorList emptyList = new TypeSensorList();

        validList.add(secondTypeSensor);

        // Act

        TypeSensor[] actualResult1 = emptyList.getElementsAsArray();
        TypeSensor[] actualResult3 = validList.getElementsAsArray();

        // Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult3, actualResult3);
    }
}