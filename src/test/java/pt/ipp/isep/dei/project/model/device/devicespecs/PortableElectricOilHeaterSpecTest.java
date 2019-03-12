package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * PortableElectricOilHeaterSpec tests class.
 */

class PortableElectricOilHeaterSpecTest {

    private PortableElectricOilHeaterSpec validPortableElectricOilHeaterSpec = new PortableElectricOilHeaterSpec();

    @Test
    void seeAttributeMethods() {
        // Arrange

        Object expectedResult1 = 0;
        List<String> expectedResult2 = new ArrayList<>();

        // Act

        Object actualResult1 = validPortableElectricOilHeaterSpec.getAttributeValue("Nonexistent");
        List<String> actualResult2 = validPortableElectricOilHeaterSpec.getAttributeNames();
        Object actualResult3 = validPortableElectricOilHeaterSpec.getAttributeUnit("Nonexistent");
        boolean actualResult4 = validPortableElectricOilHeaterSpec.setAttributeValue("Nonexistent", 20D);

        // Assert

        Assert.assertEquals(expectedResult1, actualResult1);
        Assert.assertEquals(expectedResult2, actualResult2);
        Assert.assertEquals(false, actualResult3);
        Assert.assertFalse(actualResult4);
    }
}
