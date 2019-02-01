package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for Room Configuration UI
 */


public class RoomConfigurationController {

    private Room mRoom;
    private Sensor mSensor;
    private String mProgramList = "programList";

    /**
     * Empty constructor.
     */

    public RoomConfigurationController() {
        /*
         * Builder RoomConfigurationController() with no parameters.
         * It will only be used in ui to apply methods on given inputs.
         */
    }

    //  SHARED METHODS

    /**
     * @param roomName is the name of the room we want to look for.
     * @param house    is the house where we want to look for the room.
     * @return is the room in the House with the name that matches given string. Is null if no Room matches.
     */

    Room getRoomFromHouseByName(String roomName, House house) {
        for (Room r : house.getRoomList()) {
            if (r.getRoomName().equals(roomName)) {
                mRoom = r;
            }
        }
        return mRoom;
    }

    /**
     * @param house is the house we want to print the roomList from.
     * @return builds a string from the House's room list.
     */

    public String buildRoomListString(House house) {
        return house.buildRoomListString();
    }

    /**
     * @param room is the room we want to print.
     * @return builds a string from given room.
     */

    public String buildRoomString(Room room) {
        return room.buildRoomString();
    }

    /**
     * @param listOfIndexesOfRoom is a list of integers that represent positions in a list.
     * @param house               is the house where we want to get lists from.
     * @return builds a string from the individual elements in the House's RoomList that are contained in the positions
     * given by the list of indexes.
     */

    String buildRoomElementsByIndexString(List<Integer> listOfIndexesOfRoom, House house) {
        return house.buildRoomsByIndexString(listOfIndexesOfRoom);
    }

    /**
     * @param listOfIndexesOfDevice is a list of integers that represent positions in a list.
     * @param room                  is the room where we want to get the device list from.
     * @return builds a string from the individual elements in the DeviceList that are contained in the positions
     * given by the list of indexes.
     */

    String buildDeviceElementsByIndexString(List<Integer> listOfIndexesOfDevice, Room room) {
        return room.getObjectDeviceList().buildElementByIndexString(listOfIndexesOfDevice);
    }

    /**
     * @param input is the name of room we want to look for.
     * @param house is the house where we want to look for rooms.
     * @return is a list of integers representing positions in the house's roomList of rooms whose name matches
     * input string.
     */

    List<Integer> matchRoomIndexByString(String input, House house) {
        return house.matchRoomIndexByString(input);
    }


    /*US222 As a Power User, I want to deactivate a device, so that it is no longer used.
    Nevertheless, it should be possible to access its configuration and activity log.*/

    /**
     * This method receives the chosen device and returns a boolean true if it deactivates the device or false if it doesn't.
     *
     * @param device is the chosen device
     * @return
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


    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    /**
     * @param name is the name of the sensor we want to look for.
     * @param ga   is the ga where we want to see if the sensor exists.
     * @return is true if the geoArea contains a sensor with given name, false if it doesn't.
     */

    boolean checkIfGAContainsSensorByString(String name, GeographicArea ga) {
        for (Sensor s : ga.getSensorList().getSensorList()) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param sensorName is the name of the sensor we want to look for
     * @param ga         is the ga we want to look for the sensor in.
     * @return is the sensor in the Geo Area with the name that matches given string. Is null if no sensor matches.
     */

    Sensor getSensorFromGAByName(String sensorName, GeographicArea ga) {
        for (Sensor s : ga.getSensorList().getSensorList()) {
            if (s.getName().equals(sensorName))
                mSensor = s;
        }
        return mSensor;
    }

    /**
     * @param input is the name of sensor we want to look for.
     * @param slist is the sensorlist where we want to look for sensors.
     * @return is a list of integers representing positions in the sensorlist of sensors whose name matches
     * input string.
     */

    List<Integer> matchSensorIndexByString(String input, SensorList slist) {
        return slist.matchSensorIndexByString(input);
    }

    List<Integer> matchDeviceIndexByString(String input, Room room) {
        return room.getObjectDeviceList().matchDeviceIndexByString(input);
    }

    /**
     * @param sensorList is the sensor list to print.
     * @return builds a string from given sensor list.
     */

    public String buildSensorListString(SensorList sensorList) {
        return sensorList.buildSensorWholeListString(sensorList);
    }

    /**
     * @param sensor is sensor we want to print.
     * @return builds a string from given sensor.
     */

    String buildSensorString(Sensor sensor) {
        return sensor.buildSensorString();
    }

    /**
     * @param device the device we want to print.
     * @return string with the given device.
     */
    String buildDeviceString(Device device) {
        return device.buildDeviceString();
    }

    /**
     * @param room the room we want to print the list of devices from.
     * @return string with all the devices in the given room.
     */
    public String buildDeviceListString(Room room) {
        return room.buildDeviceListString();
    }

    public void setNominalPowerDevice(Device device, double nominalPower) {
        device.setNominalPower(nominalPower);
    }

    public boolean removeDevice(Room room, Device device) {
        return room.removeDevice(device);
    }

    public boolean addDevice(Room room, Device device) {
        return room.addDevice(device);
    }

    public Device createDevice(DeviceType deviceType) {
        return deviceType.createDeviceType();
    }


    public List<String> getAttributeNames(Device device) {
        return device.getAttributeNames();
    }

    public boolean setAttributeValue(Device device, String attributeName, Object attributeValue) {
        return device.setAttributeValue(attributeName, attributeValue);
    }

    public String getType(Device device) {
        return device.getType();
    }


    public Object getAttributeValueWashingMachine(Device device) {
        return device.getProgramList();
    }


    public void configureOneWashingMachineProgram(Device device, ProgramList program) {
        device.setAttributeValue(mProgramList, program);
    }


    /**
     * @param listOfIndexesOfSensor is a list of integers that represent positions in a list.
     * @param sensorList            is the sensorList where we want to get sensors from.
     * @return builds a string from the individual elements in the SensorList that are contained in the positions
     * given by the list of indexes.
     */

    String buildSensorElementsByIndexString(List<Integer> listOfIndexesOfSensor, SensorList sensorList) {
        return sensorList.buildElementsByIndexString(listOfIndexesOfSensor);
    }

    public String buildTypeListString(List<TypeSensor> typeList) {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));
        if (typeList.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < typeList.size(); i++) {
            TypeSensor aux = typeList.get(i);
            result.append(i).append(") Name: ").append(aux.getName()).append(" | ");
            result.append("Units: ").append(aux.getUnits()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    public boolean addSensorToRoom(Room room, Sensor sensor) {
        return (room.addSensor(sensor));
    }
}
