package PT.IPP.ISEP.DEI.Project.Model;

import java.util.List;

public class EnergyGrid {
    private String mName;
    private RoomList mListOfRooms;
    private double mTotalPower;
    private PowerSourceList mListPowerSources;
    private DeviceList mListDevices;



    public String getmName() {
        return mName;
    }

    public double getmTotalPower() {
        return mTotalPower;
    }

    public DeviceList getmListDevices() {
        return mListDevices;
    }

    public PowerSourceList getmListPowerSources() {
        return mListPowerSources;
    }

    public void setmListDevices(DeviceList mListDevices) {
        this.mListDevices = mListDevices;
    }

    public void setmListOfRooms(RoomList mListOfRooms) {
        this.mListOfRooms = mListOfRooms;
    }

    public RoomList getmListOfRooms () { return this.mListOfRooms;}

    public void setmListPowerSources(PowerSourceList mListPowerSources) {
        this.mListPowerSources = mListPowerSources;
    }

    public void setmTotalPower(double mTotalPower) {
        this.mTotalPower = mTotalPower;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean addRoom(Room room) {
        if (!(mListOfRooms.getListOfRooms().contains(room))) {
            mListOfRooms.getListOfRooms().add(room);
            return true;
        } else {
            return false;
        }
    }
}
