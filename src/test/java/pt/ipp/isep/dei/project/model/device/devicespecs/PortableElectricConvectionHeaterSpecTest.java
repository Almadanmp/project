package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertEquals;

/**
 * PortableElectricConvectionHeaterSpec tests class.
 */

class PortableElectricConvectionHeaterSpecTest {

    @Test
    void seeIfAllMethodsThrowException() {
        //Arrange

        PortableElectricConvectionHeaterSpec portableElectricConvectionHeaterSpec = new PortableElectricConvectionHeaterSpec();

        //Act

        Throwable exception1 = assertThrows(UnsupportedOperationException.class,
                portableElectricConvectionHeaterSpec::getAttributeNames);
        Throwable exception2 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeaterSpec.getAttributeValue("empty"));
        Throwable exception3 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeaterSpec.getAttributeUnit("empty"));
        Throwable exception4 = assertThrows(UnsupportedOperationException.class,
                () -> portableElectricConvectionHeaterSpec.setAttributeValue("empty", new Object()));

        //Assert

        assertEquals("At the moment, this operation is not supported.", exception1.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception2.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception3.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception4.getMessage());
    }
}
