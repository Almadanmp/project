package PT.IPP.ISEP.DEI.Project.Model;

public class Room {
    private String mRoomName;
    private int mHouseFloor;
    private double mRoomDimensions;
    private SensorList mRoomSensorList;
    private DeviceList mRoomDeviceList;
    private double mRoomTotalPower;

    public Room(String name, int houseFloor, double dimensions) {
        mRoomName = name;
        mHouseFloor = houseFloor;
        mRoomDimensions = dimensions;
    }

    public void setRoomName(String name) {
        mRoomName = name;
    }

    public void setRoomHouseFloor(int houseFloor) {
        mHouseFloor = houseFloor;
    }

    public void setRoomDimensions(double dimensions) {
        mRoomDimensions = dimensions;
    }

    public void setRoomSensorList(SensorList sensorList) {
        mRoomSensorList = sensorList;
    }

    public void setRoomDeviceList(DeviceList deviceList) {
        mRoomDeviceList = deviceList;
    }

    public void setRoomTotalPower(double totalPower) {
        mRoomTotalPower = totalPower;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public int getHouseFloor() {
        return mHouseFloor;
    }

    public double getRoomDimensions() {
        return mRoomDimensions;
    }

    public SensorList getRoomSensorList() {
        return mRoomSensorList;
    }

    public DeviceList getRoomDeviceList() {
        return mRoomDeviceList;
    }

    public double getRoomTotalPower() {
        return mRoomTotalPower;
    }
}
