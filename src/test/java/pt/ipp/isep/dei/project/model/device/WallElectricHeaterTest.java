package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.log.Log;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lamp Device tests class.
 */

class WallElectricHeaterTest {
    // Common testing artifacts in this class

    private WallElectricHeater validHeater;

    @BeforeEach
    void arrangeArtifacts() {
        validHeater = new WallElectricHeater();
    }

    @Test
    void seeIfGetDeviceTypeWorks() {
        // Act

        String result = validHeater.getType();

        //Act

        assertEquals("WallElectricHeater", result);
    }

    @Test
    void seeIfAllMethodsThrowException() {
        //Act

        Throwable exception1 = assertThrows(UnsupportedOperationException.class,
                validHeater::getName);
        Throwable exception2 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.setName("empty"));
        Throwable exception3 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.setNominalPower(100));
        Throwable exception4 = assertThrows(UnsupportedOperationException.class,
                validHeater::getNominalPower);
        Throwable exception5 = assertThrows(UnsupportedOperationException.class,
                validHeater::isActive);
        Throwable exception6 = assertThrows(UnsupportedOperationException.class,
                validHeater::deactivate);
        Throwable exception7 = assertThrows(UnsupportedOperationException.class,
                validHeater::buildString);
        Throwable exception8 = assertThrows(UnsupportedOperationException.class,
                validHeater::getLogList);
        Throwable exception9 = assertThrows(UnsupportedOperationException.class,
                validHeater::isLogListEmpty);
        Throwable exception10 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.addLog(new Log(10, new GregorianCalendar().getTime(), new GregorianCalendar().getTime())));
        Throwable exception11 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.countLogsInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception12 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.getLogsInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception13 = assertThrows(UnsupportedOperationException.class,
                () -> validHeater.getConsumptionInInterval(new GregorianCalendar().getTime(), new GregorianCalendar().getTime()));
        Throwable exception14 = assertThrows(UnsupportedOperationException.class,
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
        Assert.assertEquals("At the moment, this operation is not supported.", exception10.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception11.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception12.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception13.getMessage());
        Assert.assertEquals("At the moment, this operation is not supported.", exception14.getMessage());
    }
}
