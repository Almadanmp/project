package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * WallElectricHeaterSpec tests class.
 */

class WallElectricHeaterSpecTest {

    private WallElectricHeaterSpec validWallElectricHeaterSpec = new WallElectricHeaterSpec();

    @Test
    void seeAttributeMethods() {
        // Arrange

        Object expectedResult1 = 0;
        List<String> expectedResult2 = new ArrayList<>();

        // Act

        Object actualResult1 = validWallElectricHeaterSpec.getAttributeValue("Nonexistent");
        List<String> actualResult2 = validWallElectricHeaterSpec.getAttributeNames();
        Object actualResult3 = validWallElectricHeaterSpec.getAttributeUnit("Nonexistent");
        boolean actualResult4 = validWallElectricHeaterSpec.setAttributeValue("Nonexistent", 20D);

        // Assert

        Assert.assertEquals(expectedResult1, actualResult1);
        Assert.assertEquals(expectedResult2, actualResult2);
        Assert.assertEquals(false, actualResult3);
        Assert.assertFalse(actualResult4);
    }
}
