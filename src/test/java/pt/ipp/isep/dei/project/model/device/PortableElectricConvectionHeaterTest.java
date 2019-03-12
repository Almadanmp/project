package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.devicespecs.PortableElectricConvectionHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lamp Device tests class.
 */

class PortableElectricConvectionHeaterTest {
    // Common testing artifacts for this class.

    private PortableElectricConvectionHeater validHeater;


    @BeforeEach
    void arrangeArtifacts(){
        validHeater = new PortableElectricConvectionHeater(new PortableElectricConvectionHeaterSpec());
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Act

        String result = validHeater.getType();

        // Act

        assertEquals("PortableElectricConvectionHeater", result);
    }

    @Test
    void seeIfAllMethodsThrowException() {
        // Act

        Throwable exception1 = assertThrows(UnsupportedOperationException.class,
                validHeater::isActive);
        Throwable exception2 = assertThrows(UnsupportedOperationException.class,
                validHeater::deactivate);
        Throwable exception3 = assertThrows(UnsupportedOperationException.class,
                validHeater::getLogList);
        Throwable exception4 = assertThrows(UnsupportedOperationException.class,
                validHeater::isLogListEmpty);
        Throwable exception5 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.addLog(new Log(10, new GregorianCalendar().getTime(), new GregorianCalendar().getTime())));
        Throwable exception6 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.countLogsInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception7 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.getLogsInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception8 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.getConsumptionInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception9 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.getEnergyConsumption(20));


        // Assert

        Assert.assertEquals("At the moment, this operation is not supported.", exception1.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception2.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception3.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception4.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception5.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception6.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception7.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception8.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception9.getMessage());
    }

    @Test
    void seeIfSettersAndGetters(){
        // Arrange

        validHeater.setName("Heater");
        validHeater.setNominalPower(200);

        // Act

        String actualResultName = validHeater.getName();
        double actualResultNominalPower = validHeater.getNominalPower();

        // Assert

        assertEquals("Heater", actualResultName);
        assertEquals(200, actualResultNominalPower);
    }

    @Test
    void seeStringBuild(){
        // Arrange

        validHeater.setName("Heater");
        validHeater.setNominalPower(200);
        String expectedResult = "The device Name is Heater, and its nominal power is 200.0 kW.\n";

        // Act

        String actualResult = validHeater.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


}
