package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.TvSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TV Device tests class.
 */

class TVTest {

    // Common artifacts for testing in this class.

    private TV validTV;

    @BeforeEach
    void arrangeArtifacts() {
        validTV = new TV(new TvSpec());
        validTV.setName("Living Room TV");
        validTV.setNominalPower(15D);
        validTV.setAttributeValue(TvSpec.STANDBY_POWER, 15D);
        validTV.addLog(new Log(1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(),
                new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime()));
    }

    @Test
    void seeIfGetSetNameWork() {
        // Happy Case

        validTV.setName("Room TV");
        String expectedResultHappy = "Room TV";
        String resultHappy = validTV.getName();
        assertEquals(expectedResultHappy, resultHappy);

        // Set Name at Null

        validTV.setName(null);
        String expectedResultNull = "TV";
        String resultNull = validTV.getType();
        assertEquals(expectedResultNull, resultNull);
    }

    @Test
    void seeIfGetTypeWorks() {
        // Arrange

        String expectedResult = "TV";

        // Act

        String result = validTV.getType();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNominalPowerWorksHappyCase() {
        // Arrange

        validTV.setNominalPower(12D);
        double expectedResult = 12;

        // Act

        double result = validTV.getNominalPower();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetNominalPowerWorksNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    validTV.setNominalPower(-20);
                });
    }

    @Test
    void seeIfIsActiveWorks() {
        // Act

        boolean result = validTV.isActive();

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfDeactivateWorks() {
        // Act

        boolean resultHappy = validTV.deactivate();

        // Assert

        assertTrue(resultHappy);

        //Test fails on deactivate (if device is already inactive)

        // Act

        boolean resultFalse = validTV.deactivate();

        // Assert
        assertFalse(resultFalse);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expectedResult = "The device Name is Living Room TV, and its NominalPower is 15.0 kW.\n";

        // Act

        String result = validTV.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }
}
