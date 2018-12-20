package PT.IPP.ISEP.DEI.Project.Model;

import java.util.Date;
import java.util.Objects;

public class Room {
    private String mRoomName;
    private int mHouseFloor;
    private double mRoomDimensions;
    private SensorList mRoomSensorList;
    private DeviceList mRoomDeviceList;
    private double mRoomMaxPower;

    public Room(String name, int houseFloor, double dimensions) {
        setRoomName(name);
        setRoomHouseFloor(houseFloor);
        setRoomDimensions(dimensions);
    }

    public Room(String name, int houseFloor, double dimensions, double roomTotalPower) {
        setRoomName(name);
        setRoomHouseFloor(houseFloor);
        setRoomDimensions(dimensions);
        setRoomMaxPower(roomTotalPower);
    }

    public Room(String name, int houseFloor, double dimensions, SensorList listSensor) {
        setRoomName(name);
        setRoomHouseFloor(houseFloor);
        setRoomDimensions(dimensions);
        setRoomSensorList(listSensor);
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

    public void setRoomMaxPower(double totalPower) {
        mRoomMaxPower = totalPower;
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

    public double getRoomMaxPower() {
        return mRoomMaxPower;
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

    public boolean doesSensorListInARoomContainASensorByName(String name){
        for(Sensor s: mRoomSensorList.getSensorList()){
            if(s.getName().equals(name)){
                return true;
            }
        } return false;
    }

        public boolean addSensor(Sensor sensor) {
        if (!(mRoomSensorList.getSensorList().contains(sensor))) {
            mRoomSensorList.getSensorList().add(sensor);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Gets most recent reading for current temperature.
     * @param day
     * @return
     */

    public double getCurrentRoomTemperature (Date day) {
        House h =new House();
        TypeSensor type = new TypeSensor("temperature");
        Sensor s = new Sensor("sensor1",type,h.getmGPS(), new Date());
        for (int i = 0; i<mRoomSensorList.getSensors().length; i++){
            s = mRoomSensorList.getSensors()[i];
        }
        return s.getReadingList().getMostRecentValueOfReading();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(mRoomName, room.mRoomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mRoomName);
    }
}



