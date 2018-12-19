package PT.IPP.ISEP.DEI.Project.Model;

public class Device {
    private String mName;
    private String mDeviceType;
    private Room mParentRoom;
    private ReadingList mReadingList;
    private double mTotalPowerDevice;

    public Device(String name, String type, Room parentRoom, ReadingList list, double totalPower){
        setmName(name);
        setmDeviceType(type);
        setmParentRoom(parentRoom);
        setmReadingList(list);
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

    public ReadingList getmReadingList(){
        return mReadingList;
    }

    public void setmReadingList(ReadingList list){
        this.mReadingList=list;
    }

    public double getmTotalPowerDevice(){
        return this.mTotalPowerDevice;
    }

    public void setmTotalPowerDevice(double totalPower){
        this.mTotalPowerDevice =totalPower;
    }
}
