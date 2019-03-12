package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * PortableElectricConvectionHeaterSpec tests class.
 */

class PortableElectricConvectionHeaterSpecTest {

    private PortableElectricConvectionHeaterSpec validPortableElectricConvectionHeaterSpec = new PortableElectricConvectionHeaterSpec();

    @Test
    void seeAttributeMethods() {
        // Arrange

        Object expectedResult1 = 0;
        List<String> expectedResult2 = new ArrayList<>();

        // Act

        Object actualResult1 = validPortableElectricConvectionHeaterSpec.getAttributeValue("Nonexistent");
        List<String> actualResult2 = validPortableElectricConvectionHeaterSpec.getAttributeNames();
        Object actualResult3 = validPortableElectricConvectionHeaterSpec.getAttributeUnit("Nonexistent");
        boolean actualResult4 = validPortableElectricConvectionHeaterSpec.setAttributeValue("Nonexistent", 20D);

        // Assert

        Assert.assertEquals(expectedResult1, actualResult1);
        Assert.assertEquals(expectedResult2, actualResult2);
        Assert.assertEquals(false, actualResult3);
        Assert.assertFalse(actualResult4);
    }
}
