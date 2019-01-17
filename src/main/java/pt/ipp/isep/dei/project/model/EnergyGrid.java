package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
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
    private String mStringSpacer = "---------------\n";

    public EnergyGrid() {
        this.mRoomList = new RoomList();
        this.mListPowerSources = new PowerSourceList();
        mNominalPower = 0;
    }


    public EnergyGrid(String houseGridDesignation, double maxContractedPower) {
        setName(houseGridDesignation);
        setNominalPower(maxContractedPower);
        this.mRoomList = new RoomList();
        this.mListPowerSources = new PowerSourceList();
    }


    public String getName() {
        return mName;
    }

    public RoomList getListOfRooms() {
        return mRoomList;
    }

    /**
     * @return a DeviceList containing all the devices in the energy grid list of rooms.
     */

    public DeviceList getDeviceListFromAllRooms() {
        DeviceList devices = new DeviceList();
        for (Room r : mRoomList.getRoomList()) {
            for (int i = 0; i < r.getDeviceList().size(); i++) {
                devices.addDevice(r.getDeviceList().get(i));
            }
        }
        return devices;
    }

    public double getNominalPower() {
        double result = 0;
        for (Room r : mRoomList.getRoomList()) {
            result += r.getNominalPower();
            mNominalPower = result;
        }
        return mNominalPower;
    }

    PowerSourceList getListPowerSources() {
        return mListPowerSources;
    }

    void setListPowerSources(PowerSourceList mListPowerSources) {
        this.mListPowerSources = mListPowerSources;
    }

    public void setRoomList(RoomList mListOfRooms) {
        this.mRoomList = mListOfRooms;
    }

    public boolean addPowerSource(PowerSource powerSource) {
        if (!(mListPowerSources.getPowerSourceList().contains(powerSource))) {
            return mListPowerSources.getPowerSourceList().add(powerSource);
        }
        return false;
    }

    private void setNominalPower(double nominalPower) {
        this.mNominalPower = nominalPower;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public boolean addRoomToAnEnergyGrid(Room room) {
        return this.mRoomList.addRoom(room);
    }

    public String buildGridString() {
        return "Energy Grid: " + this.mName + ", Max Power: " + this.getNominalPower();
    }

    public List<Room> getRoomList() {
        return this.mRoomList.getRoomList();
    }

    public List<Device> getDeviceList() {
        return this.mRoomList.getDeviceList();
    }

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
            result.append(counter).append(") ").append(d.getName()).append(", Type: ").append(d.getDeviceType().name()).append(", Power: ").append(d.getNominalPower()).append(".\n");
            counter++;
        }
        return result.toString();
    }

    public boolean removeRoom(Room room) {
        if (this.mRoomList.contains(room)) {
            this.mRoomList.removeRoom(room);
            return true;
        }
        return false;
    }

    public String buildDeviceListString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (Device d : this.getDeviceList()) {
            result.append(counter).append(") ").append(d.buildDeviceString());
            counter++;
        }
        return result.toString();
    }

    public String buildRoomListString() {
        return this.mRoomList.buildRoomsString();
    }


    /**
     * @param energyGrid - parameter which we will use to get all the devices present on all of its rooms.
     * @return a String with the device index, device type, device name and the room in which the device is contained.
     */

    public String printListOfDeviceByType(EnergyGrid energyGrid) {
        StringBuilder result = new StringBuilder(mStringSpacer);
        for (DeviceType d : DeviceType.values()) {
            for (int i = 0; i < energyGrid.getListOfRooms().getRoomList().size(); i++) {
                Room r = energyGrid.getListOfRooms().getRoomList().get(i);
                if (r != null){result.append(buildDeviceListInGridString(r, d));}
            }
        }
        result.append(mStringSpacer);
        return result.toString();
    }

    private String buildDeviceListInGridString(Room r, DeviceType d) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < r.getDeviceList().size(); x++) {
            if (d == r.getDeviceList().get(x).getDeviceType()) {
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
