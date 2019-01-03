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

    public void setmName(String name){
        this.mName=name;
    }

    public void setmDeviceType(String type){
        this.mDeviceType=type;
    }

    public void setmParentRoom(Room parentRoom){
        this.mParentRoom=parentRoom;
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
