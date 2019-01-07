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

    void setmName(String name){
        this.mName=name;
    }

    void setmDeviceType(String type){
        this.mDeviceType=type;
    }

    void setmParentRoom(Room parentRoom){
        this.mParentRoom=parentRoom;
    }

    void setmEnergyConsumptionList(ReadingList list){
        this.mEnergyConsumptionList =list;
    }

    double getmTotalPowerDevice(){
        return this.mTotalPowerDevice;
    }

    void setmTotalPowerDevice(double totalPower){
        this.mTotalPowerDevice =totalPower;
    }
}
