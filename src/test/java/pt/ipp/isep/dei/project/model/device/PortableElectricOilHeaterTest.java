package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.log.Log;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lamp Device tests class.
 */

 class PortableElectricOilHeaterTest {

    @Test
     void getDeviceTypeTest() {
        //Arrange

        PortableElectricOilHeater d = new PortableElectricOilHeater();

        //Act

        String result = d.getType();

        //Act

        assertEquals("PortableElectricOilHeater", result);
    }

    @Test
    void seeIfAllMethodsThrowException() {
        //Arrange

        PortableElectricOilHeater portableElectricOilHeater = new PortableElectricOilHeater();

        //Act

        Throwable exception1 = assertThrows(UnsupportedOperationException.class,
                portableElectricOilHeater::getName);
        Throwable exception2 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeater.setName("empty"));
        Throwable exception3 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeater.setNominalPower(100));
        Throwable exception4 = assertThrows(UnsupportedOperationException.class,
                portableElectricOilHeater::getNominalPower);
        Throwable exception5 = assertThrows(UnsupportedOperationException.class,
                portableElectricOilHeater::isActive);
        Throwable exception6 = assertThrows(UnsupportedOperationException.class,
                portableElectricOilHeater::deactivate);
        Throwable exception7 = assertThrows(UnsupportedOperationException.class,
                portableElectricOilHeater::buildString);
        Throwable exception8 = assertThrows(UnsupportedOperationException.class,
                portableElectricOilHeater::getLogList);
        Throwable exception9 = assertThrows(UnsupportedOperationException.class,
                portableElectricOilHeater::isLogListEmpty);
        Throwable exception10 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeater.addLog(new Log(10,new GregorianCalendar().getTime(),new GregorianCalendar().getTime())));
        Throwable exception11 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeater.countLogsInInterval(new GregorianCalendar().getTime(),new GregorianCalendar().getTime()));
        Throwable exception12 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeater.getLogsInInterval(new GregorianCalendar().getTime(),new GregorianCalendar().getTime()));
        Throwable exception13 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeater.getConsumptionInInterval(new GregorianCalendar().getTime(),new GregorianCalendar().getTime()));
        Throwable exception14 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeater.getEnergyConsumption(20));


        //Assert

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
