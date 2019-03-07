package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicespecs.TvSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TV Device tests class.
 */

class TVTest {

    // Common artifacts for testing in this class.
    private TV tv = new TV(new TvSpec());

    @BeforeEach
    void arrangeArtifacts() {
        tv.setName("Living Room TV");
        tv.setNominalPower(15D);
        tv.setAttributeValue(TvSpec.STANDBY_POWER, 15D);
        tv.addLog(new Log(1, new GregorianCalendar(2019, 1, 1).getTime(),
                new GregorianCalendar(2019, 1, 1).getTime()));
    }

    @Test
    void getAndSetName() {
        //Happy Case
        tv.setName("Room TV");
        String expectedResultHappy = "Room TV";
        String resultHappy = tv.getName();
        assertEquals(expectedResultHappy, resultHappy);

        //Set Name at Null
        tv.setName(null);
        String expectedResultNull = "TV";
        String resultNull = tv.getType();
        assertEquals(expectedResultNull, resultNull);

        //Set Name Happy
        tv.setName("Color TV");
        String expectedResultSetHappy = "Color TV";
        String resulSetHappy = tv.getName();
        assertEquals(expectedResultSetHappy, resulSetHappy);
    }

    @Test
    void getType() {
        String expectedResult = "TV";
        String result = tv.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void setNominalPowerHappyCase() {
        //Set NP Happy Case
        tv.setNominalPower(12D);
        double expectedResult = 12;
        double result = tv.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void setNominalPowerThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    tv.setNominalPower(-20);
                });
    }

    @Test
    void isActive() {
        boolean result = tv.isActive();
        assertTrue(result);
    }

    @Test
    void deactivateHappyCase() {
        boolean resultHappy = tv.deactivate();
        assertTrue(resultHappy);

        //Tests fail on deactivate (if device is already inactive)
        boolean resultFalse = tv.deactivate();
        assertFalse(resultFalse);
    }

    @Test
    void buildString() {
        String expectedResult = "The device Name is Living Room TV, and its NominalPower is 15.0 kW.\n";
        String result = tv.buildString();
        assertEquals(expectedResult, result);
    }
}
