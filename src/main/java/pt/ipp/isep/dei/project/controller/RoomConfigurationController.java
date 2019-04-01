package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

import java.util.List;

/**
 * Controller class for Room Configuration UI
 */


public class RoomConfigurationController {

    /**
     * Empty constructor.
     */

    public RoomConfigurationController() {
        /*
         * Builder RoomConfigurationController() with no parameters.
         * It will only be used in ui to apply methods on given inputs.
         */
    }

    /*US222 As a Power User, I want to deactivate a device, so that it is no longer used.
    Nevertheless, it should be possible to access its configuration and activity log.*/

    /**
     * This method receives the chosen device and returns a boolean true if it deactivates the device or false if it doesn't.
     *
     * @param device is the chosen device
     * @return true if device was deactivated false otherwise
     */
    public boolean deactivateDevice(Device device) {
        return device.deactivate();
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA */

    /**
     * This method receives a room and returns that room's total nominal power as a double
     *
     * @param room is the room to be tested
     * @return room's total nominal power (double)
     */
    public double getRoomNominalPower(Room room) {
        return room.getNominalPower();
    }

    /**
     * Method that returns the SensorList of the room.
     *
     * @param room is the room we want to choose.
     * @return SensorList of the Room.
     */
    public SensorList getRoomSensorList(Room room) {
        return room.getSensorList();
    }


    /* USER STORY 253 - As an Administrator, I want to addWithoutPersisting a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    /**
     * @param sensorList is the sensor list to print.
     * @return builds a string from given sensor list.
     */

    public String buildSensorListString(SensorList sensorList) {
        return sensorList.toString();
    }

    /**
     * @param room the room we want to print the list of devices from.
     * @return string with all the devices in the given room.
     */
    public String buildDeviceListString(Room room) {
        return room.buildDeviceListString();
    }

    /**
     * adds a given program to a program list.
     *
     * @param programList         - the list
     * @param newFixedTimeProgram -the program we want to addWithoutPersisting to the list
     */
    public void addProgramToProgramList(ProgramList programList, FixedTimeProgram newFixedTimeProgram) {
        programList.add(newFixedTimeProgram);
    }

    /**
     * sets a given nominal power to a device
     *
     * @param device       - the device
     * @param nominalPower - the value of the nominal power
     */
    public void setNominalPowerDevice(Device device, double nominalPower) {
        device.setNominalPower(nominalPower);
    }

    /**
     * removes device from a given room
     *
     * @param room   - room we want to removeGeographicArea the device from
     * @param device - device we want to removeGeographicArea from the room
     * @return - boolean
     */
    public boolean removeDevice(Room room, Device device) {
        return room.removeDevice(device);
    }

    /**
     * adds device to a given room
     *
     * @param room   - room we want to addWithoutPersisting the device to
     * @param device - device we want to addWithoutPersisting to the room
     * @return - boolean
     */
    public boolean addDevice(Room room, Device device) {
        return room.addDevice(device);
    }

    /**
     *This method returns a int that represents the size of the deviceList.
     * @param roomDTO is the room we want to get the device list size.
     * @param house is the house we want to get the device list size.
     * @return a int that represents the size of the deviceList.
     */
    public int getDeviceListSize(RoomDTO roomDTO, House house) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, house);
        return room.getDeviceListSize();
    }

    /**
     * Method that returns a device by it's index.
     * @param roomDTO is the room we want to get the device from.
     * @param house is the house we want to get the device from.
     * @param index is the index of the device in the device list
     * @return
     */
    public Device getDeviceByIndex(RoomDTO roomDTO, House house, int index) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, house);
        return room.getDeviceByIndex(index);
    }

    /**
     * creates a device through a chosen device type.
     *
     * @param deviceType - the type of the new device we wish to create.
     * @return - the device
     */
    public Device createDevice(DeviceType deviceType) {
        return deviceType.createDevice();
    }

    /**
     * gets a list of strings with the attribute names from a given device class.
     *
     * @param device - device from which we want to get the names of the attributes
     * @return - list of strings
     */
    public List<String> getAttributeNames(Device device) {
        return device.getAttributeNames();
    }

    /**
     * gets a list of strings with the attribute names from a given program.
     *
     * @param program - program from which we want to get the names of the attributes
     * @return - list of strings
     */
    public List<String> getProgramAttributeNames(FixedTimeProgram program) {
        return program.getAttributeNames();
    }

    /**
     * sets a given value of an attribute of a program
     *
     * @param program - the program we want to set the attribute value
     * @param i       - the indez of the attribute
     * @param value   - the value we want to set
     */
    public void setProgramAttributeValue(FixedTimeProgram program, int i, double value) {
        program.setAttributeValue(program.getAttributeNames().get(i), value);
    }

    /**
     * gets programlist from a programmable device
     *
     * @param device - programmable
     * @return - programlist
     */
    public ProgramList getProgramList(Programmable device) {
        return device.getProgramList();
    }

    /**
     * gets the units from the respective attributes in the program
     *
     * @param program - program
     * @param i       - index of the attribute
     * @return object (string with the unit)
     */
    public Object getProgramAttributeUnit(FixedTimeProgram program, int i) {
        return program.getAttributeUnit(program.getAttributeNames().get(i));
    }

    /**
     * gets the units from the respective attributes in the device
     *
     * @param device - device
     * @param i      - index of the attribute
     * @return object (string with the unit)
     */
    public Object getAttributeUnit(Device device, int i) {
        return device.getAttributeUnit(device.getAttributeNames().get(i));
    }

    /**
     * gets the value of the attribute from the program, in the respective indez
     *
     * @param program - program
     * @param i       - index of the attribute
     * @return object (value)
     */
    public Object getProgramAttributeValue(FixedTimeProgram program, int i) {
        return program.getAttributeValue(program.getAttributeNames().get(i));
    }

    /**
     * gets the value of the attribute from the device, in the respective indez
     *
     * @param device -device
     * @param i      - index of the attribute
     * @return object (value)
     */
    public Object getAttributeValue(Device device, int i) {
        return device.getAttributeValue(device.getAttributeNames().get(i));
    }

    /**
     * sets a given value of an attribute of a program
     *
     * @param device         - the device we want to set the attribute value
     * @param attributeName  - the name of the attribute
     * @param attributeValue - the value we want to set
     * @return true if the attributes were successfully added.
     */
    public boolean setAttributeValue(Device device, String attributeName, Object attributeValue) {
        return device.setAttributeValue(attributeName, attributeValue);
    }

    /**
     * sets the program name to a program
     *
     * @param program     - program we want to set the name to
     * @param programName - String name
     */
    public void setProgramName(FixedTimeProgram program, String programName) {
        program.setProgramName(programName);
    }

    /**
     * gets the device type of the device
     *
     * @param device - the device we want to get the type from
     * @return - string with the name of the type
     */
    public String getType(Device device) {
        return device.getType();
    }


    /**
     * sets a programlist to a programmable device
     *
     * @param device  - device we want to addWithoutPersisting the programlist to
     * @param program - programlist we want to addWithoutPersisting to the device
     */
    public void configureDeviceProgramList(Device device, ProgramList program) {
        String programList = "programList";
        device.setAttributeValue(programList, program);
    }

    /**
     * sets the device name
     *
     * @param name   - String we want to name the device with
     * @param device - device we want to name.
     */
    public void setDeviceName(String name, Device device) {
        device.setName(name);
    }

    /**
     * /**
     * Method to addWithoutPersisting a Sensor to the Room.
     * @param sensor is the sensor we want to addWithoutPersisting.
     * @param room is the room we want to addWithoutPersisting the sensor to.
     * @return if sensor was successfully added to the room, false otherwise.
     */
    public boolean addSensorToRoom(Sensor sensor, Room room) {
        return (room.addSensor(sensor));
    }
}
