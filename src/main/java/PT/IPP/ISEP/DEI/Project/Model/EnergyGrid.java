package PT.IPP.ISEP.DEI.Project.Model;

public class EnergyGrid {
    private RoomList mListOfRooms;
    private double mTotalPower;
    private PowerSourceList mListPowerSources;
    private DeviceList mListDevices;

    public RoomList getmListOfRooms() {
        return mListOfRooms;
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

    public void setmListPowerSources(PowerSourceList mListPowerSources) {
        this.mListPowerSources = mListPowerSources;
    }

    public void setmTotalPower(double mTotalPower) {
        this.mTotalPower = mTotalPower;
    }
}
