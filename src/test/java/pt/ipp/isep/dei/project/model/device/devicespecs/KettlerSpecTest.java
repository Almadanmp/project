package pt.ipp.isep.dei.project.model.device.devicespecs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

class KettlerSpecTest {

    private KettlerSpec kettlerSpec;

    @BeforeEach
    void arrangeArtifacts() {
        kettlerSpec = new KettlerSpec();
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        //Arrange

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Cold Water Temperature");
        expectedResult.add("Volume Water To Heat");
        expectedResult.add("Performance Ratio");

        //Act

        List<String> actualResult = this.kettlerSpec.getAttributeNames();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        //Act

        Object actualResult1 = this.kettlerSpec.getAttributeValue(KettlerSpec.COLD_WATER_TEMP);
        Object actualResult2 = this.kettlerSpec.getAttributeValue(KettlerSpec.VOLUME_WATER);
        Object actualResult3 = this.kettlerSpec.getAttributeValue(KettlerSpec.PERFORMANCE_RATIO);

        // Assert

        assertEquals(0.0, actualResult1);
        assertEquals(0.0, actualResult2);
        assertEquals(0.95, actualResult3);
    }

    @Test
    void seeIfSetAttributeValueWorks() {
        //Act

        boolean actualResult1 = this.kettlerSpec.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 100D);
        boolean actualResult2 = this.kettlerSpec.setAttributeValue(KettlerSpec.VOLUME_WATER, 200D);
        boolean actualResult3 = this.kettlerSpec.setAttributeValue(KettlerSpec.PERFORMANCE_RATIO, 0.99);

        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertTrue(actualResult3);
    }

    @Test
    void seeIfGetAttributeUnitWorks() {
        //Act

        Object actualResult1 = this.kettlerSpec.getAttributeUnit(KettlerSpec.COLD_WATER_TEMP);
        Object actualResult2 = this.kettlerSpec.getAttributeUnit(KettlerSpec.VOLUME_WATER);
        Object actualResult3 = this.kettlerSpec.getAttributeUnit(KettlerSpec.PERFORMANCE_RATIO);

        // Assert

        assertEquals("ºC", actualResult1);
        assertEquals("L", actualResult2);
        assertEquals("", actualResult3);
    }

    @Test
    void seeIfSetAttributeValueWorksWithInvalidValue() {
        //Act

        boolean actualResult1 = this.kettlerSpec.setAttributeValue(KettlerSpec.COLD_WATER_TEMP, 100);
        boolean actualResult2 = this.kettlerSpec.setAttributeValue(KettlerSpec.VOLUME_WATER, 200);
        boolean actualResult3 = this.kettlerSpec.setAttributeValue(KettlerSpec.PERFORMANCE_RATIO, "invalid type");
        boolean actualResult4 = this.kettlerSpec.setAttributeValue(KettlerSpec.PERFORMANCE_RATIO, -0.99);
        boolean actualResult5 = this.kettlerSpec.setAttributeValue(KettlerSpec.PERFORMANCE_RATIO, 1.45);

        // Assert

        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfGetAttributeUnitThrowsIllegalArgument() {
        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.getAttributeUnit("")); //diff hashcode

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.getAttributeUnit("\0" + KettlerSpec.COLD_WATER_TEMP)); //same hashcode, diff strings

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.getAttributeUnit("\0" + KettlerSpec.VOLUME_WATER)); //same hashcode, diff strings

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.getAttributeUnit("\0" + KettlerSpec.PERFORMANCE_RATIO)); //same hashcode, diff strings
    }

    @Test
    void seeIfGetAttributeValueThrowsIllegalArgument() {
        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.getAttributeValue("")); //diff hashcode

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.getAttributeValue("\0" + KettlerSpec.COLD_WATER_TEMP)); //same hashcode, diff strings

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.getAttributeValue("\0" + KettlerSpec.VOLUME_WATER)); //same hashcode, diff strings

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.getAttributeValue("\0" + KettlerSpec.PERFORMANCE_RATIO)); //same hashcode, diff strings

    }

    @Test
    void seeIfSetAttributeValueThrowsIllegalArgument() {
        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.setAttributeValue("", 200D)); //diff hashcode

        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.setAttributeValue("\0" + KettlerSpec.COLD_WATER_TEMP, 200D)); //same hashcode, diff strings

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.setAttributeValue("\0" + KettlerSpec.VOLUME_WATER, 200D)); //same hashcode, diff strings

        assertThrows(IllegalArgumentException.class,
                () -> this.kettlerSpec.setAttributeValue("\0" + KettlerSpec.PERFORMANCE_RATIO, 200D)); //same hashcode, diff strings
    }

    @Test
    void seeIfIsRatioWorks() {
        //Act

        boolean actualResult1 = this.kettlerSpec.isARatio(0.0);
        boolean actualResult2 = this.kettlerSpec.isARatio(0.90);
        boolean actualResult3 = this.kettlerSpec.isARatio(1.0);
        boolean actualResult4 = this.kettlerSpec.isARatio(1.50);
        boolean actualResult5 = this.kettlerSpec.isARatio(-0.50);

        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertTrue(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }


}