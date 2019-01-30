package pt.ipp.isep.dei.project.model.device.devicetypes;

import org.junit.jupiter.api.Test;

/**
 * device tests class.
 */

public class DishwasherDTTest {

    @Test
    public void createDishwasherType() {
        DishwasherDT dt = new DishwasherDT();
        dt.createDevice();
    }
}
