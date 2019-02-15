package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.LogList;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.*;

/**
 * Class that represents an Energy Grid present in a House.
 */

public class EnergyGrid implements Metered {
    private String mName;
    private RoomList mRoomList;
    private PowerSourceList mListPowerSources;
    private double mMaxContractedPower;

    public EnergyGrid(String name, double maxContractedPower) {
        this.mRoomList = new RoomList();
        this.mListPowerSources = new PowerSourceList();
        this.mName = name;
        this.mMaxContractedPower = maxContractedPower;
    }

    /**
     * Getter for name of the energy grid.
     *
     * @return returns attribute name of the energy grid.
     */

    public String getName() {
        return mName;
    }

    /**
     * Getter for list of rooms that are contained in the energy grid.
     *
     * @return returns a list with all the rooms contained in this energy grid.
     */

    public RoomList getListOfRooms() {
        return mRoomList;
    }

    public double getMaxContractedPower() {
        return mMaxContractedPower;
    }

    /**
     * Method accesses the sum of nominal powers of all rooms and devices connected to a grid..
     *
     * @return is the sum of nominal powers of all rooms and devices connected to a grid.
     */

    public double getNominalPower() {
        double result = 0;
        for (Room r : mRoomList.getList()) {
            result += r.getNominalPower();
        }
        return result;
    }

    /**
     * Getter for the list of power sources connected to the energy grid.
     *
     * @return the list of power sources connected to the energy grid.
     */
    PowerSourceList getListPowerSources() {
        return mListPowerSources;
    }

    /**
     * Setter for List of power sources connected to the energy grid.
     *
     * @param mListPowerSources List of power sources.
     */
    void setListPowerSources(PowerSourceList mListPowerSources) {
        this.mListPowerSources = mListPowerSources;
    }

    public boolean setMaxContractedPower(double power){
        if (power < 0){
            return false;
        }
        this.mMaxContractedPower = power;
        return true;
    }

    /**
     * Setter for the list of rooms connected the energy grid.
     *
     * @param mListOfRooms List of rooms in the energy grid.
     */
    public void setRoomList(RoomList mListOfRooms) {
        this.mRoomList = mListOfRooms;
    }

    /**
     * Setter for a power source to the energy grid.
     *
     * @param powerSource power source to be added to the energy grid.
     * @return returns true case the power source to be added is already associated to the grid.
     */
    public boolean addPowerSource(PowerSource powerSource) {
        if (!(mListPowerSources.getPowerSourceList().contains(powerSource))) {
            return mListPowerSources.getPowerSourceList().add(powerSource);
        }
        return false;
    }

    /**
     * Setter for the name of the energy grid.
     *
     * @param mName name of the energy grid.
     */

    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * Setter for a new room to be added to the energy grid.
     *
     * @param room Room to be added to the list of rooms in the energy grid.
     * @return returns true if the room is actually added to the energy grid.
     */
    public boolean addRoomToAnEnergyGrid(Room room) {
        return this.mRoomList.addRoom(room);
    }

    /**
     * Creates a string displaying the name of the grid and respective max nominal power.
     *
     * @return returns a string displaying the name of the grid and respective max nominal power.
     */
    public String buildGridString() {
        return "Energy Grid: " + this.mName + ", Max Power: " + this.getMaxContractedPower();
    }

    /**
     * Getter for the list of rooms related to the energy grid as an array of rooms.
     *
     * @return a list of rooms related to the energy grid.
     */
    public List<Room> getRoomList() {
        return this.mRoomList.getList();
    }

    /**
     * Getter for the list of devices related to the energy grid as an array od devices.
     *
     * @return a list of devices related to the energy grid.
     */

    public List<DeviceTemporary> getDeviceList() {
        return this.mRoomList.getDeviceList();
    }

    /**
     * Creates a string displaying the name of the rooms and the devices connected to the energy grid.
     *
     * @return returns a string displaying the name of the rooms and the devices connected to the energy grid.
     */
    public String buildRoomsAndDevicesString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        List<Room> roomList = this.getRoomList();
        for (Room r : roomList) {
            result.append(counter).append(") ").append(r.getRoomName()).append(".\n");
            counter++;
        }
        List<DeviceTemporary> deviceList = this.getDeviceList();
        for (DeviceTemporary d : deviceList) {
            result.append(counter).append(") ").append(d.getName()).append(", Type: ").append(d.getType()).append(", Power: ").append(d.getNominalPower()).append(".\n");
            counter++;
        }
        return result.toString();
    }

    /**
     * Method to remove a room from the energy grid.
     *
     * @param room the room we want to remove from the energy grid.
     * @return returns true if the room is successfully removed from the energy grid.
     */
    public boolean removeRoom(Room room) {
        if (this.mRoomList.contains(room)) {
            this.mRoomList.removeRoom(room);
            return true;
        }
        return false;
    }

    /**
     * Creates a string displaying the name of the devices in the energy grid.
     *
     * @return returns a string displaying the name of the devices in the energy grid.
     */
    public String buildDeviceListString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (DeviceTemporary d : this.getDeviceList()) {
            result.append(counter).append(") ").append(d.buildDeviceString());
            counter++;
        }
        return result.toString();
    }

    /**
     * Creates a string displaying the rooms in the energy grid and the room's attributes.
     *
     * @return returns a string displaying the rooms in the energy grid and the room's attributes.
     */
    public String buildRoomListString() {
        return this.mRoomList.buildRoomsString();
    }


    /**
     * Creates a String with the device index, device type, device name and the room in which the device is contained.
     *
     * @param energyGrid energy grid that we want to see which devices will be printed.
     * @return a String with the device index, device type, device name and the room in which the device is contained.
     */
    public String buildListOfDeviceByTypeString(EnergyGrid energyGrid, House house) {
        String stringSpacer = "---------------\n";
        StringBuilder result = new StringBuilder(stringSpacer);
        for (DeviceType d : house.getmDeviceTypeList()) {
            for (int i = 0; i < energyGrid.getListOfRooms().getList().size(); i++) {
                Room r = energyGrid.getListOfRooms().getList().get(i);
                if (r != null) {
                    result.append(buildDeviceListInGridString(r, d.getDeviceType()));
                }
            }
        }
        result.append(stringSpacer);
        return result.toString();
    }

    /**
     * Creates a string that displays the device type and in which room it is contained.
     *
     * @param r room where we want to see the devices.
     * @param d type of the devices.
     * @return a string that displays the device type and in which room it is contained.
     */
    private String buildDeviceListInGridString(Room r, String d) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < r.getDeviceList().size(); x++) {
            if (d.equals(r.getDeviceList().get(x).getType())) {
                DeviceTemporary device = r.getDeviceList().get(x);
                result.append("DeviceTemporary type: ").append(d).append(" | ");
                result.append("DeviceTemporary name: ").append(device.getName()).append(" | ");
                result.append("Nominal power: ").append(device.getNominalPower()).append(" | ");
                result.append("Room: ").append(r.getRoomName()).append(" | \n");
            }
        }
        return result.toString();
    }

    /**
     * US722 As a Power User [or Administrator], I want the sum of the energy consumption of all energy-metered rooms
     * in the grid in the interval.
     *
     * @param initialDate for metering period.
     * @param finalDate   for metering period.
     * @return total metered energy consumption of a grid in a given time interval.
     */
    public double getGridConsumptionInInterval(Date initialDate, Date finalDate) {
        double gridConsumption = 0;
        for (Room r : this.getRoomList()) {
            gridConsumption += r.getConsumptionInInterval(initialDate, finalDate);
        }
        return gridConsumption;
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        LogList result = new LogList();
        for (Room r: this.getRoomList()){
            LogList tempList = r.getLogsInInterval(startDate,endDate);
            result.addLogList(tempList);
        }
        return result;
    }

    @Override
    public double getEnergyConsumption(float time) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyGrid eg = (EnergyGrid) o;
        return Objects.equals(mName, eg.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
