package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class EnergyGrid implements Metered {
    private String mName;
    private double mNominalPower;
    private RoomList mRoomList;
    private PowerSourceList mListPowerSources;

    EnergyGrid() {
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

    public double getNominalPower() {
        double result = 0;
        for (Room r: mRoomList.getRoomList()){
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

    public void setListOfRooms(RoomList mListOfRooms) {
        this.mRoomList = mListOfRooms;
    }

    public boolean addPowerSource(PowerSource powerSource) {
        if (!(mListPowerSources.getPowerSourceList().contains(powerSource))) {
            return mListPowerSources.getPowerSourceList().add(powerSource);
        }
        return false;
    }

    void setNominalPower(double nominalPower) {
        this.mNominalPower = nominalPower;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public boolean addRoomToAnEnergyGrid(Room room) {
        return this.mRoomList.addRoom(room);
    }

    public String printGrid() {
        return "Energy Grid: " + this.mName + ", Max Power: " + this.getNominalPower();
    }

    public boolean removeRoom(Room room) {
        if (this.mRoomList.contains(room)) {
            this.mRoomList.removeRoom(room);
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
        return 1;
    }
}
