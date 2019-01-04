package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class EnergyGrid {
    private String mName;
    private double mMaxPower = 0;
    private RoomList mListOfRooms;
    private PowerSourceList mListPowerSources;
    private DeviceList mListDevices;

    EnergyGrid() {
    }


    public EnergyGrid(String houseGridDesignation, double maxContractedPower) {
        setName(houseGridDesignation);
        setMaxPower(maxContractedPower);
    }

    EnergyGrid(String houseGridDesignation, double maxContractedPower, DeviceList deviceList) {
        setName(houseGridDesignation);
        setMaxPower(maxContractedPower);
        this.mListDevices = deviceList;
    }

    EnergyGrid(String houseGridDesignation, double maxContractedPower, DeviceList deviceList, RoomList roomList) {
        setName(houseGridDesignation);
        setMaxPower(maxContractedPower);
        this.mListDevices = deviceList;
        this.mListOfRooms = roomList;
    }

    public String getName() {
        return mName;
    }

    public RoomList getmListOfRooms() {
        return mListOfRooms;
    }

    double getMaxPower() {
        return mMaxPower;
    }

    double getTotalPower() {
        double sum = 0;
        for (Device d : mListDevices.getDeviceList()) {
            sum = +d.getmTotalPowerDevice();
        }
        return sum;
    }

    public PowerSourceList getmListPowerSources() {
        return mListPowerSources;
    }

    public void setListPowerSources(PowerSourceList mListPowerSources) {
        this.mListPowerSources = mListPowerSources;
    }

    void setListDevices(DeviceList mListDevices) {
        this.mListDevices = mListDevices;
    }

    public void setListOfRooms(RoomList mListOfRooms) {
        this.mListOfRooms = mListOfRooms;
    }

    public boolean addPowerSource(PowerSource powerSource) {
        if (!(mListPowerSources.getPowerSourceList().contains(powerSource))) {
            return mListPowerSources.getPowerSourceList().add(powerSource);
        }
        return false;
    }

    void setMaxPower(double mMaxPower) {
        this.mMaxPower = mMaxPower;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public boolean addRoomToAEnergyGrid(Room room) {
        if (this.mListOfRooms.addRoom(room)) {
            return true;
        } else {
            return false;
        }
    }

    public String printGrid() {
        return "Energy Grid: " + this.mName + ", Max Power: " + this.getMaxPower();
    }

    public boolean removeRoom(Room room) {
        if (this.mListOfRooms.getListOfRooms().contains(room)) {
            this.mListOfRooms.getListOfRooms().remove(room);
            return true;
        }
        return false;
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
        return Objects.hash(mName);
    }
}
