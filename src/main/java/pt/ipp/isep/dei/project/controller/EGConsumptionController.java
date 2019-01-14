package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

import java.util.List;

public class EGConsumptionController {


    /*
     * US705
     As a Power User, I want to know the total nominal power of a subset of rooms and/or devices of my choosing
     connected to a grid.
     */

    public String printRoomsAndDevices(EnergyGrid grid){
        return grid.printRoomsAndDevices();
    }

    public double getSumNominalPowerByIndex (List<Integer> indexes, EnergyGrid grid){
        return grid.getSumNominalPowerByIndex(indexes);
    }


    /**
     * US752
     * As a Regular User [or Power User], I want to estimate the total energy used in heating water in a given day,
     * given the cold-water temperature and the volume of water produced in each water heater.
     */
    public double getDailyHouseConsumptionWaterHeater(House house) {
        return house.getDailyHouseConsumptionPerType(DeviceType.WATER_HEATER);
    }

}

