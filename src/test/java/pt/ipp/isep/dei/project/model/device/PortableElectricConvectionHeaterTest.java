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

 class PortableElectricConvectionHeaterTest {

    @Test
     void getDeviceTypeTest() {
        //Arrange

        PortableElectricConvectionHeater d = new PortableElectricConvectionHeater();

        //Act

        String result = d.getType();

        //Act

        assertEquals("PortableElectricConvectionHeater", result);
    }

    @Test
    void seeIfAllMethodsThrowException() {
        //Arrange

        PortableElectricConvectionHeater portableElectricConvectionHeater = new PortableElectricConvectionHeater();

        //Act

        Throwable exception1 = assertThrows(UnsupportedOperationException.class,
                portableElectricConvectionHeater::getName);
        Throwable exception2 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeater.setName("empty"));
        Throwable exception3 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeater.setNominalPower(100));
        Throwable exception4 = assertThrows(UnsupportedOperationException.class,
                portableElectricConvectionHeater::getNominalPower);
        Throwable exception5 = assertThrows(UnsupportedOperationException.class,
                portableElectricConvectionHeater::isActive);
        Throwable exception6 = assertThrows(UnsupportedOperationException.class,
                portableElectricConvectionHeater::deactivate);
        Throwable exception7 = assertThrows(UnsupportedOperationException.class,
                portableElectricConvectionHeater::buildString);
        Throwable exception8 = assertThrows(UnsupportedOperationException.class,
                portableElectricConvectionHeater::getLogList);
        Throwable exception9 = assertThrows(UnsupportedOperationException.class,
                portableElectricConvectionHeater::isLogListEmpty);
        Throwable exception10 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeater.addLog(new Log(10,new GregorianCalendar().getTime(),new GregorianCalendar().getTime())));
        Throwable exception11 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeater.countLogsInInterval(new GregorianCalendar().getTime(),new GregorianCalendar().getTime()));
        Throwable exception12 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeater.getLogsInInterval(new GregorianCalendar().getTime(),new GregorianCalendar().getTime()));
        Throwable exception13 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeater.getConsumptionInInterval(new GregorianCalendar().getTime(),new GregorianCalendar().getTime()));
        Throwable exception14 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeater.getEnergyConsumption(20));


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
