package PT.IPP.ISEP.DEI.Project.Model;

public class EnergyGrid {
    private String mName;
    private RoomList mListOfRooms;
    private double mTotalPower;
    private PowerSourceList mListPowerSources;
    private DeviceList mListDevices;

public EnergyGrid(){}

public EnergyGrid(String name, RoomList listOfRooms, double totalPower, PowerSourceList listPowerSources, DeviceList deviceList){
    setmName(name);
    setmListOfRooms(listOfRooms);
    setmTotalPower(totalPower);
    setmListPowerSources(listPowerSources);
    setmListDevices(deviceList);
}

    public EnergyGrid(String houseGridDesignation,double maxContractedPower){
       setmName(houseGridDesignation);
       setmTotalPower(maxContractedPower);
    }

    public String getmName() {
        return mName;
    }

    public double getmTotalPower() {
       double sum = 0;
        for (Device d : mListDevices.getDeviceList()) {
            sum =+  d.getmTotalPowerDevice();
        }
        return sum;
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
