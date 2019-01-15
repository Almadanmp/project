package pt.ipp.isep.dei.project.model.devicetypes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WashingMachineTest {

    @Test
    public void getTypeTest() {
        WashingMachine washingMachine = new WashingMachine();
        DeviceType expectedResult = DeviceType.WASHING_MACHINE;
        DeviceType result = washingMachine.getType();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getConsumptionTest() {
        WashingMachine washingMachine = new WashingMachine();
        double expectedResult = 0;
        double result = washingMachine.getConsumption();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetNominalPower() {
        WashingMachine washingMachine = new WashingMachine(5);
        double expectedResult = 6;
        washingMachine.getNominalPower();
        washingMachine.setNominalPower(6);
        double result = washingMachine.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetCapacity() {
        WashingMachine washingMachine = new WashingMachine(5);
        double expectedResult = 6;
        washingMachine.getCapacity();
        washingMachine.setCapacity(6);
        double result = washingMachine.getCapacity();
        assertEquals(expectedResult, result);
    }
}
