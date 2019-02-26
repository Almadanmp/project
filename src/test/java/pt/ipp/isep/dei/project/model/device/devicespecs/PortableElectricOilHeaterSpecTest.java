package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertEquals;

/**
 * PortableElectricOilHeaterSpec tests class.
 */

class PortableElectricOilHeaterSpecTest {

    @Test
    void seeIfAllMethodsThrowException() {
        //Arrange

        PortableElectricOilHeaterSpec portableElectricOilHeaterSpec = new PortableElectricOilHeaterSpec();

        //Act

        Throwable exception1 = assertThrows(UnsupportedOperationException.class,
                portableElectricOilHeaterSpec::getAttributeNames);
        Throwable exception2 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeaterSpec.getAttributeValue("empty"));
        Throwable exception3 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeaterSpec.getAttributeUnit("empty"));
        Throwable exception4 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricOilHeaterSpec.setAttributeValue("empty", new Object()));

        //Assert

        assertEquals("At the moment, this operation is not supported.", exception1.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception2.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception3.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception4.getMessage());
    }
}
