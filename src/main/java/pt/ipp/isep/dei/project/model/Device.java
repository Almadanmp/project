package pt.ipp.isep.dei.project.model;

public class Device {
    private String mName;
    private String mDeviceType;
    private Room mParentRoom;
    private ReadingList mEnergyConsumptionList;
    private double mTotalPowerDevice;

    public Device(String name, String type, Room parentRoom, ReadingList list, double totalPower){
        setmName(name);
        setmDeviceType(type);
        setmParentRoom(parentRoom);
        setmEnergyConsumptionList(list);
        setmTotalPowerDevice(totalPower);
    }

    public String getmName(){
        return this.mName;
    }

    public void setmName(String name){
        this.mName=name;
    }

    public String getmDeviceType(){
        return this.mDeviceType;
    }

    public void setmDeviceType(String type){
        this.mDeviceType=type;
    }

    public Room getmParentRoom(){
        return this.mParentRoom;
    }

    public void setmParentRoom(Room parentRoom){
        this.mParentRoom=parentRoom;
    }

    public ReadingList getmEnergyConsumptionList(){
        return mEnergyConsumptionList;
    }

    public void setmEnergyConsumptionList(ReadingList list){
        this.mEnergyConsumptionList =list;
    }

    public double getmTotalPowerDevice(){
        return this.mTotalPowerDevice;
    }

    public void setmTotalPowerDevice(double totalPower){
        this.mTotalPowerDevice =totalPower;
    }
}
