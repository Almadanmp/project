package PT.IPP.ISEP.DEI.Project.Model;


public class EnergyGrid {
    private String mName;
    private RoomList mListOfRooms;
    private double mMaxPower;
    private PowerSourceList mListPowerSources;
    private DeviceList mListDevices;
    private EnergyGridList mEnergyGridList;

public EnergyGrid(){}

public EnergyGrid(String name, RoomList listOfRooms, double totalPower, PowerSourceList listPowerSources, DeviceList deviceList,EnergyGridList energyGridList){
    setmName(name);
    setmListOfRooms(listOfRooms);
    setmMaxPower(totalPower);
    setmListPowerSources(listPowerSources);
    setmListDevices(deviceList);
    setmEnergyGridList(energyGridList);
}

    public EnergyGrid(String houseGridDesignation,double maxContractedPower){
       setmName(houseGridDesignation);
       setmMaxPower(maxContractedPower);
    }

    public String getmName() {
        return mName;
    }

    public double getTotalPower(){return this.mMaxPower;}

    public double getmMaxPower() {
       double sum = 0;
        for (Device d : mListDevices.getDeviceList()) {
            sum =+  d.getmTotalPowerDevice();
        }
        return sum;
    }
public void setmEnergyGridList(EnergyGridList energyGridList){this.mEnergyGridList = energyGridList;}

    public EnergyGridList getmEnergyGridList() {
        return this.mEnergyGridList;
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

    public boolean addPowerSource(PowerSource powerSource) {
        if (!(mListPowerSources.getPowerSourceList().contains(powerSource))) {
            mListPowerSources.getPowerSourceList().add(powerSource);
            return true;
        } else {
            return false;
        }
    }

    public void setmMaxPower(double mMaxPower) {
        this.mMaxPower = mMaxPower;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean addRoomToAEnergyGrid(Room room) {
         if(this.mListOfRooms.addRoom(room)){
            return true;
        } else {
            return false;
        }
    }
}
