package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

import static org.testng.Assert.assertEquals;

/**
 * device tests class.
 */

class DishwasherDTTest {

    @Test
    void createDishwasherType() {
        DishwasherDT dt = new DishwasherDT();
        Device result = dt.createDeviceType();
        Device expectedResult = new Device(new DishwasherSpec());
        assertEquals(result, expectedResult);
    }

    @Test
    void getDeviceType() {
        DishwasherDT dt = new DishwasherDT();
        String result = dt.getDeviceType();
        String expectedResult = "Dishwasher";
        assertEquals(result, expectedResult);
    }
}
