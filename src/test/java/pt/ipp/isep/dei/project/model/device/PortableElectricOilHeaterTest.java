package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.PortableElectricOilHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lamp Device tests class.
 */

class PortableElectricOilHeaterTest {
    // Common testing artifacts for this class.

    private PortableElectricOilHeater validHeater;

    @BeforeEach
    void arrangeArtifacts() {
        validHeater = new PortableElectricOilHeater(new PortableElectricOilHeaterSpec());
    }

    @Test
    void seeSettersAndGetters(){
        // Arrange

        validHeater.setName("NewHeater");
        validHeater.setNominalPower(35);

        // Act

        String actualResult1 = validHeater.getName();
        double actualResult2 = validHeater.getNominalPower();

        // Assert

        assertEquals("NewHeater", actualResult1);
        assertEquals(35,actualResult2);
    }

    @Test
    void seeBuildString(){
        // Arrange

        validHeater.setName("NewHeater");
        validHeater.setNominalPower(35);
        String expectedResult = "The device Name is NewHeater, and its NominalPower is 35.0 kW.\n";

        // Act

        String actualResult = validHeater.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Act

        String result = validHeater.getType();

        // Act

        assertEquals("PortableElectricOilHeater", result);
    }

    @Test
    void seeIfDeviceIsActiveBothConditions(){
        // Act

        boolean actualResult1 = validHeater.isActive();
        boolean actualResult2 = validHeater.deactivate();
        validHeater.deactivate();
        boolean actualResult3 = validHeater.deactivate();
        boolean actualResult4 = validHeater.isActive();

        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeEmptyLogList(){
        // Act

        boolean actualResult = validHeater.isLogListEmpty();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAllMethodsThrowException() {
        // Act


        Throwable exception1 = assertThrows(UnsupportedOperationException.class,
                validHeater::getLogList);
        Throwable exception2 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.addLog(new Log(10, new GregorianCalendar().getTime(), new GregorianCalendar().getTime())));
        Throwable exception3 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.countLogsInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception4 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.getLogsInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception5 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.getConsumptionInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception6 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.getEnergyConsumption(20));


        // Assert

        assertEquals("At the moment, this operation is not supported.", exception1.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception2.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception3.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception4.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception5.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception6.getMessage());
    }

    @Test
    void seeAttributeMethods() {
        // Arrange

        Object expectedResult1 = 0;
        List<String> expectedResult2 = new ArrayList<>();

        // Act

        Object actualResult1 = validHeater.getAttributeValue("Nonexistent");
        List<String> actualResult2 = validHeater.getAttributeNames();
        Object actualResult3 = validHeater.getAttributeUnit("Nonexistent");
        boolean actualResult4 = validHeater.setAttributeValue("Nonexistent", 20D);

        // Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(false, actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfEqualsWorks() {

        PortableElectricOilHeater portableElectricOilHeater = new PortableElectricOilHeater(new PortableElectricOilHeaterSpec());
        PortableElectricOilHeater portableElectricOilHeater1 = new PortableElectricOilHeater(new PortableElectricOilHeaterSpec());

        portableElectricOilHeater.setName("PortableElectricOilHeater1");
        portableElectricOilHeater1.setName("PortableElectricOilHeater2");

        boolean actualResult1 = validHeater.equals(portableElectricOilHeater);
        boolean actualResult2 = validHeater.equals(portableElectricOilHeater1);
        boolean actualResult3 = validHeater.equals(20D); // Necessary for Sonarqube testing
        boolean actualResult4 = validHeater.equals(validHeater); // Necessary for Sonarqube testing
        boolean actualResult5 = validHeater.equals(null); // Necessary for Sonarqube testing

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertTrue(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validHeater.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
