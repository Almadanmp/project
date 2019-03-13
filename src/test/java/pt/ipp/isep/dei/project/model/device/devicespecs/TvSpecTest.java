package pt.ipp.isep.dei.project.model.device.devicespecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

class TvSpecTest {

    private TvSpec tvSpec;

    @BeforeEach
    void arrangeArtifacts() {
        tvSpec = new TvSpec();
    }

    @Test
    void seeIfGetAttributeNamesWorks() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Standby Power");

        List<String> actualResult = tvSpec.getAttributeNames();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAttributeValueWorks() {
        Object actualResult1 = tvSpec.getAttributeValue(TvSpec.STANDBY_POWER);

        assertEquals(0.0, actualResult1);
    }

    @Test
    void seeIfSetAttributeValueWorks() {
        tvSpec.setAttributeValue(TvSpec.STANDBY_POWER, 300D);

        Object actualResult1 = tvSpec.getAttributeValue(TvSpec.STANDBY_POWER);

        assertEquals(300D, actualResult1);
    }

    @Test
    void seeIfSetAttributeValueWorksWithInvalidValue() {
        boolean actualResult1 = tvSpec.setAttributeValue(TvSpec.STANDBY_POWER, NaN);

        assertFalse(actualResult1);
    }

    @Test
    void seeIfSetAttributeValueWorksWithInvalidAttributeName() {
        boolean actualResult1 = tvSpec.setAttributeValue("FakeAttribute", 22D);

        assertFalse(actualResult1);
    }


    @Test
    void seeIfGetAttributeUnitWorks() {

        Object actualResult1 = tvSpec.getAttributeUnit(TvSpec.STANDBY_POWER);

        assertEquals("W", actualResult1);
    }
}