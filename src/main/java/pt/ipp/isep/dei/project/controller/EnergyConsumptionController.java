package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.LogList;

import java.util.Date;
import java.util.List;

/**
 * Controller class for Energy Consumption UI
 */

public class EnergyConsumptionController {

    /*
     * US705
     As a Power User, I want to know the total nominal power of a subset of rooms and/or devices of my choosing
     connected to a grid.
     */

    /**
     * Calls for the grid's method in the model to print its own rooms and devices.
     *
     * @param grid is the grid the user has already chosen.
     * @return builds a string from the Rooms and the Devices in that grid.
     */

    String buildRoomsAndDevicesString(EnergyGrid grid) {
        return grid.buildRoomsAndDevicesString();
    }

    /**
     * Calls for the room's method in the model to add all of its devices to a given deviceList.
     *
     * @param room       is the room where the devices we want to add are.
     * @param deviceList is the deviceList we want to add devices to.
     * @return true if devices were added.
     */

    public boolean addRoomDevicesToDeviceList(Room room, DeviceList deviceList) {
        return room.addRoomDevicesToDeviceList(deviceList);
    }

    /**
     * Calls for the roomList's method in the model to add a room to itself.
     *
     * @param room the room we want to add.
     * @param list the list we want to add the room to.
     * @return true if the room was added, false if it was already in the list.
     */

    public boolean addRoomToList(Room room, RoomList list) {
        return list.addRoom(room);
    }

    /**
     * Calls for the list's method in the model to add a device to itself.
     *
     * @param device the device we want to add to a list.
     * @param list   the list we want to add the device to.
     * @return true if the device was added, false if it was already in the list.
     */

    public boolean addDeviceToList(Device device, DeviceList list) {
        return list.addDevice(device);
    }

    /**
     * Calls for the grid's method in the model to calculate a total nominal power from a given list of devices.
     *
     * @param selectedDevices is the subset of devices we want to include in the calculation.
     * @return is the total nominal power of given devices.
     */

    public double getSelectionNominalPower(DeviceList selectedDevices) {
        return selectedDevices.getNominalPower();
    }

    /**
     * Calls for the room's method in the model to remove all of its devices from a given list.
     *
     * @param room       is the room that contains the devices we want to remove.
     * @param deviceList is the list we want to remove the devices from.
     * @return true if the devices were successfully removed, false if they were not on the list.
     */

    public boolean removeRoomDevicesFromDeviceList(Room room, DeviceList deviceList) {
        return room.removeRoomDevicesFromDeviceList(deviceList);
    }

    /**
     * Calls for the roomList's method in the model to remove a given room from itself.
     *
     * @param room     is the room we want to remove.
     * @param roomList is the list we want to remove it from.
     * @return true if the room was removed, false if it wasn't on the list.
     */

    public boolean removeRoomFromList(Room room, RoomList roomList) {
        return roomList.removeRoom(room);
    }

    /**
     * Calls for the deviceList's method in the model to remove a given device from itself.
     *
     * @param d          is the device we want to remove.
     * @param deviceList is the list we want to remove the device from.
     * @return true if the device was removed, false if it wasn't on the list.
     */

    public boolean removeDeviceFromList(Device d, DeviceList deviceList) {
        return deviceList.removeDevice(d);
    }


    /*US720 As a Power User [or Administrator], I want to know the total metered energy consumption of a device in a
     * given time interval, i.e. the sum of the energy consumption of the device in the interval.
     * Only metering periods fully contained in the interval will be included.
     * One cannot know the exact energy consumption of devices not connected to an energy meter.
     */

    public boolean getDeviceConsumptionInInterval(Device device, Date initialTime, Date finalTime) {
        if (device.countLogsInInterval(initialTime, finalTime) == 0) {
            System.out.println("This device has no energy consumption logs in the given interval.");
            return false;
        }
        System.out.println("Device: " + device.getName() + "\n" + "Between " + initialTime + " and " + finalTime + "\n" + "The total Energy Consumption for the given device is: " + device.getConsumptionWithinGivenInterval(initialTime, finalTime) + " kW/h.");
        return true;
    }


    /* US752
     * As a Regular User [or Power User], I want to estimate the total energy used in heating water in a given day,
     * given the cold-water temperature and the volume of water produced in each water heater.
     */

    /**
     * Gets a List of Devices from a House of the Water heater Type
     *
     * @param house user house
     * @return returns a list of water heaters from a house
     */
    public List<Device> getWaterHeaterDeviceList(House house) {
        return house.getDevicesOfGivenType("WaterHeater");
    }

    /**
     * gets a water heater name
     *
     * @param d water heater (device)
     * @return name(string)
     */
    public String getWHName(Device d) {
        return d.getName();
    }

    /**
     * Changes the configuration of the heater on selected attributes
     *
     * @param device               device to change
     * @param coldWaterTemperature value for the cold water temperature
     * @param volumeOfWaterToHeat  value for the amount of water to heat
     */
    public boolean configureWH(Device device, Double coldWaterTemperature, Double volumeOfWaterToHeat) {
        return (device.setAttributeValue("coldWaterTemperature", coldWaterTemperature) &&
                device.setAttributeValue("volumeOfWaterToHeat", volumeOfWaterToHeat));
    }

    /**
     * Get the estimate consumption on all water heaters available in the users house
     *
     * @param house user house
     * @return estimate energy consumption on the water heaters
     */
    public double getDailyWaterHeaterConsumption(House house) {
        return house.getDailyConsumptionByDeviceType("WaterHeater");
    }

    /**
     * Returns the sum of the values of nominal power of all the devices in the input energy grid
     *
     * @param grid the grid in which we want to get the total nominal power from
     * @return the value of the nominal power of all the devices in this grid
     */

    public double getTotalPowerFromGrid(EnergyGrid grid) {
        return grid.getNominalPowerFromRoomList();
    }

    /**
     * Returns the Log List for a Given device.
     *
     * @param device - given by UI
     * @return LogList
     */
    public LogList getLogListFromDevice(Device device) {
        return device.getLogList();
    }
}


