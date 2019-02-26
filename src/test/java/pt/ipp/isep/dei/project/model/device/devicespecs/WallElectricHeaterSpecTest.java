package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertEquals;

/**
 * WallElectricHeaterSpec tests class.
 */

class WallElectricHeaterSpecTest {

    @Test
    void seeIfAllMethodsThrowException() {
        //Arrange

        WallElectricHeaterSpec wallElectricHeaterSpec = new WallElectricHeaterSpec();

        //Act

        Throwable exception1 = assertThrows(UnsupportedOperationException.class,
                wallElectricHeaterSpec::getAttributeNames);
        Throwable exception2 = assertThrows(UnsupportedOperationException.class,
                () -> wallElectricHeaterSpec.getAttributeValue("empty"));
        Throwable exception3 = assertThrows(UnsupportedOperationException.class,
                () -> wallElectricHeaterSpec.getAttributeUnit("empty"));
        Throwable exception4 = assertThrows(UnsupportedOperationException.class,
                () -> wallElectricHeaterSpec.setAttributeValue("empty", new Object()));

        //Assert

        assertEquals("At the moment, this operation is not supported.", exception1.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception2.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception3.getMessage());
        assertEquals("At the moment, this operation is not supported.", exception4.getMessage());
    }
}
