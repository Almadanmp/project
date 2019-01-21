package pt.ipp.isep.dei.project.model;

import java.util.List;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.Objects;

/**
 * Class that represents an Energy Grid present in a House.
 */

public class EnergyGrid implements Metered {
    private String mName;
    private double mNominalPower;
        private RoomList mRoomList;
        private PowerSourceList mListPowerSources;

    public EnergyGrid() {
            this.mRoomList = new RoomList();
            this.mListPowerSources = new PowerSourceList();
            this.mNominalPower = 0;
    }

    /**
     * Getter for name of the energy grid.
     * @return returns attribute name of the energy grid.
     */

    public String getName() {
        return mName;
    }

    /**
     * Getter for list of rooms that are contained in the energy grid.
     * @return returns a list with all the rooms contained in this energy grid.
     */

    public RoomList getListOfRooms() {
        return mRoomList;
    }

    /**
     * Getter for list of devices that are contained in all the rooms of this energy grid.
     * @return a DeviceList containing all the devices in the energy grid list of rooms.
     */
    public DeviceList getDeviceListFromAllRooms() {
        DeviceList devices = new DeviceList();
        for (Room r : mRoomList.getList()) {
            for (int i = 0; i < r.getDeviceList().size(); i++) {
                devices.addDevice(r.getDeviceList().get(i));
            }
        }
        return devices;
    }

    /**
     * Getter for the total nominal power value in every room contained in the grid.
     * @return a the total nominal power value in every room contained in the grid.
     */
    public double getRoomListNominalPower() {
        double result = 0;
        for (Room r : mRoomList.getList()) {
            result += r.getRoomListNominalPower();
        }
        return result;
    }

    public double getNominalPower(){
        return this.mNominalPower;
    }

    /**
     * Getter for the list of power sources connected to the energy grid.
     * @return the list of power sources connected to the energy grid.
     */
    PowerSourceList getListPowerSources() {
        return mListPowerSources;
    }

    /**
     * Setter for List of power sources connected to the energy grid.
     * @param mListPowerSources List of power sources.
     */
    void setListPowerSources(PowerSourceList mListPowerSources) {
        this.mListPowerSources = mListPowerSources;
    }

    /**
     * Setter for the list of rooms connected the energy grid.
     * @param mListOfRooms List of rooms in the energy grid.
     */
    public void setRoomList(RoomList mListOfRooms) {
        this.mRoomList = mListOfRooms;
    }

    /**
     * Setter for a power source to the energy grid.
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
     * Setter for the value of nominal power in the energy grid.
     * @param nominalPower value of nominal power in the energy grid.
     */
    public void setNominalPower(double nominalPower) {
        this.mNominalPower = nominalPower;
    }

    /**
     * Setter for the name of the energy grid.
     * @param mName name of the energy grid.
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * Setter for a new room to be added to the energy grid.
     * @param room Room to be added to the list of rooms in the energy grid.
     * @return returns true if the room is actually added to the energy grid.
     */
    public boolean addRoomToAnEnergyGrid(Room room) {
        return this.mRoomList.addRoom(room);
    }

    /**
     * Creates a string displaying the name of the grid and respective max nominal power.
     * @return returns a string displaying the name of the grid and respective max nominal power.
     */
    public String buildGridString() {
        return "Energy Grid: " + this.mName + ", Max Power: " + this.getNominalPower();
    }

    /**
     * Getter for the list of rooms related to the energy grid as an array of rooms.
     * @return a list of rooms related to the energy grid.
     */
    public List<Room> getRoomList() {
        return this.mRoomList.getList();
    }

    /**
     * Getter for the list of devices related to the energy grid as an array od devices.
     * @return a list of devices related to the energy grid.
     */
    public List<Device> getDeviceList() {
        return this.mRoomList.getDeviceList();
    }

    /**
     * Creates a string displaying the name of the rooms and the devices connected to the energy grid.
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
        List<Device> deviceList = this.getDeviceList();
        for (Device d : deviceList) {
            result.append(counter).append(") ").append(d.getName()).append(", Type: ").append(d.getType().name()).append(", Power: ").append(d.getRoomListNominalPower()).append(".\n");
            counter++;
        }
        return result.toString();
    }

    /**
     * Method to remove a room from the energy grid.
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
     * @return returns a string displaying the name of the devices in the energy grid.
     */
    public String buildDeviceListString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (Device d : this.getDeviceList()) {
            result.append(counter).append(") ").append(d.buildDeviceString());
            counter++;
        }
        return result.toString();
    }

    /**
     * Creates a string displaying the rooms in the energy grid and the room's attributes.
     * @return returns a string displaying the rooms in the energy grid and the room's attributes.
     */
    public String buildRoomListString() {
        return this.mRoomList.buildRoomsString();
    }


    /**
     * Creates a String with the device index, device type, device name and the room in which the device is contained.
     * @param energyGrid energy grid that we want to see which devices will be printed.
     * @return a String with the device index, device type, device name and the room in which the device is contained.
     */
    public String buildListOfDeviceByTypeString(EnergyGrid energyGrid) {
        String stringSpacer = "---------------\n";
        StringBuilder result = new StringBuilder(stringSpacer);
        for (DeviceType d : DeviceType.values()) {
            for (int i = 0; i < energyGrid.getListOfRooms().getList().size(); i++) {
                Room r = energyGrid.getListOfRooms().getList().get(i);
                if (r != null) {
                    result.append(buildDeviceListInGridString(r, d));
                }
            }
        }
        result.append(stringSpacer);
        return result.toString();
    }

    /**
     * Creates a string that displays the device type and in which room it is contained.
     * @param r room where we want to see the devices.
     * @param d type of the devices.
     * @return a string that displays the device type and in which room it is contained.
     */
    private String buildDeviceListInGridString(Room r, DeviceType d) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < r.getDeviceList().size(); x++) {
            if (d == r.getDeviceList().get(x).getType()) {
                Device device = r.getDeviceList().get(x);
                result.append("device type: ").append(d).append(" | ");
                result.append(device.getName()).append(" | ");
                result.append("Room: ").append(r.getRoomName()).append(" | \n");
            }
        }
        return result.toString();
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
