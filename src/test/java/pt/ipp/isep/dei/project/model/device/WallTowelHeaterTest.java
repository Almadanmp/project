package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.devicespecs.WallTowelHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.testng.Assert.*;

public class WallTowelHeaterTest {

    // Common artifacts for testing in this class.
    private WallTowelHeater validWTHeater = new WallTowelHeater(new WallTowelHeaterSpec());

    @Test
    void testGetDeviceType() {
        // Arrange
        String expectedResult = "WallTowelHeater";
        // Act
        String actualResult = validWTHeater.getType();
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testSetGetName() {
        // Arrange
        validWTHeater.setName("wTH1");
        validWTHeater.setName("Aquece Toalhas");
        String expectedResult = "Aquece Toalhas";
        // Act
        String actualResult = validWTHeater.getName();
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testSetGetNominalPower() {
        // Arrange
        validWTHeater.setNominalPower(20);
        double expectedResult = 20;
        // Act
        double actualResult = validWTHeater.getNominalPower();
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testEqualsOverrideToSameObject() {
        // Arrange
        validWTHeater.setName("WTHeater1");
        validWTHeater.setNominalPower(10.0);
        // Act
        boolean actualResult = validWTHeater.equals(validWTHeater);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void testEqualsOverrideToDifferentObject() {
        // Arrange
        validWTHeater.setName("WTHeater1");
        validWTHeater.setNominalPower(40);
        WallTowelHeater wTHeater2 = new WallTowelHeater(new WallTowelHeaterSpec());
        wTHeater2.setName("WTHeater2");
        wTHeater2.setNominalPower(45);
        // Act
        boolean actualResult = validWTHeater.equals(wTHeater2);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void testEqualsOverrideToNullObject() {
        // Arrange
        validWTHeater.setName("WallTowelHeater");
        // Act
        boolean actualResult = validWTHeater.equals(null);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void testPrintDevice() {
        // Arrange
        validWTHeater.setName("Toalha Quentinha 3000");
        validWTHeater.setNominalPower(100);
        String expectedResult = "The device name is Toalha Quentinha 3000, and its nominal power is 100.0 kW.\n";
        // Act
        String actualResult = validWTHeater.buildString();
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testHashCode() {
        // Arrange
        int expectedResult = 1;
        // Act
        int actualResult = validWTHeater.hashCode();
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testSetGetAttributeValue() {
        // Arrange
        validWTHeater.setAttributeValue("Anything", 10);
        Integer expectedResult = 0;
        // Act
        Object actualResult = validWTHeater.getAttributeValue("Anything");
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testGetAttributeUnit() {
        // Arrange
        validWTHeater.setAttributeValue("Anything", 10);
        boolean expectedResult = false;
        // Act
        Object actualResult = validWTHeater.getAttributeUnit("Anything");
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testGetAttributeNames() {
        // Arrange
        validWTHeater.setAttributeValue("Anything", 10);
        List<String> expectedResult = new ArrayList<>();
        // Act
        Object actualResult = validWTHeater.getAttributeNames();
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testDeactivateReturnTrue() {
        // Act
        boolean actualResult = validWTHeater.deactivate();
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void testDeactivateReturnFalse() {
        // Arrange
        validWTHeater.deactivate();
        // Act
        boolean actualResult = validWTHeater.deactivate();
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void testGetEnergyConsumption() {
        // Arrange
        validWTHeater.setNominalPower(20.0);
        double expectedResult = 40.0;
        // Act
        double actualResult = validWTHeater.getEnergyConsumption(2);
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testGetLogList() {
        // Arrange
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        validWTHeater.addLog(log);
        LogList expectedResult = new LogList();
        expectedResult.addLog(log);
        // Act
        LogList actualResult = validWTHeater.getLogList();
        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testAddLog() {
        // Arrange
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        // Act
        boolean actualResult = validWTHeater.addLog(log);
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void testAddLogReturnFalse() {
        // Arrange
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        validWTHeater.addLog(log);
        // Act
        boolean actualResult = validWTHeater.addLog(log);
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void testAddLogToInactive() {
        // Arrange
        Log log = new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime());
        validWTHeater.deactivate();
        // Act
        boolean actualResult = validWTHeater.addLog(log);
        // Assert
        assertFalse(actualResult);
    }
}
