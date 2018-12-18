package PT.IPP.ISEP.DEI.Project.Model;

import java.util.Date;

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

    public Room(String name, int houseFloor, double dimensions, SensorList listSensor) {
        mRoomName = name;
        mHouseFloor = houseFloor;
        mRoomDimensions = dimensions;
        mRoomSensorList = listSensor;
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

    public double getMaxTemperatureInARoomOnAGivenDay(Date day){
        SensorList list= getRoomSensorList();
        House h =new House();
        TypeSensor type = new TypeSensor("temperature");
        Sensor s = new Sensor("sensor1",type,h.getmGPS(), new Date());
        for (int i = 0; i<list.getSensors().length; i++){
            s = list.getSensors()[i];
        }
        return s.getReadingList().getMaximumOfGivenDayValueReadings(day);
    }
    public boolean addDevice(Device device) {
        if (!(mRoomDeviceList.getDeviceList().contains(device))) {
            mRoomDeviceList.getDeviceList().add(device);
            return true;
        } else {
            return false;
        }
    }
}
