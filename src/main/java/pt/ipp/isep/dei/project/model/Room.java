package pt.ipp.isep.dei.project.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Room implements Metered {
    private String mRoomName;
    private int mHouseFloor;
    private double mRoomWidth;
    private double mRoomLength;
    private double mRoomHeight;
    private SensorList mRoomSensorList;
    private DeviceList mDeviceList;


    public Room(String name, int houseFloor, double width, double length, double height) {
        setRoomName(name);
        setRoomHouseFloor(houseFloor);
        setRoomWidth(width);
        setRoomLength(length);
        setRoomHeight(height);
        this.mRoomSensorList=new SensorList();
        this.mDeviceList = new DeviceList();
    }

    public SensorList getmRoomSensorList() {
        return mRoomSensorList;
    }

    private void setRoomName(String name) {
        mRoomName = name;
    }

    private void setRoomHouseFloor(int houseFloor) {
        mHouseFloor = houseFloor;
    }

    private void setRoomHeight(double height) {
        mRoomHeight = height;
    }

    private void setRoomLength(double length) {
        mRoomLength = length;
    }

    private void setRoomWidth(double width){mRoomWidth=width;}

    double getRoomHeight() {
        return mRoomHeight;
    }

    double getRoomLength() {
        return mRoomLength;
    }

    double getRoomWidth(){ return mRoomWidth;}


    public void setRoomSensorList(SensorList sensorList) {
        mRoomSensorList = sensorList;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public int getHouseFloor() {
        return mHouseFloor;
    }

    private List<Device> getDeviceList(){
        return this.mDeviceList.getDeviceList();
    }

    public double getNominalPower(){
        double result = 0;
        for (Device d : this.getDeviceList()){
            result += d.getNominalPower();
        }
        return result;
    }


    public SensorList getRoomSensorList() {
        return mRoomSensorList;
    }

    public double getMaxTemperatureInARoomOnAGivenDay(House house, Date day) {
        TypeSensor type = new TypeSensor("temperature","Celsius");
        Sensor s = new Sensor("sensor1", type, house.getLocation(), new Date());
        for (int i = 0; i < mRoomSensorList.getSensors().length; i++) {
            s = mRoomSensorList.getSensors()[i];
        }
        return s.getReadingList().getMaximumOfGivenDayValueReadings(day);
    }

    boolean doesSensorListInARoomContainASensorByName(String name) {
        for (Sensor s : mRoomSensorList.getSensorList()) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
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
     *
     */

    public double getCurrentRoomTemperature() {
        House h = new House();
        TypeSensor type = new TypeSensor("temperature","Celsius");
        Sensor s = new Sensor("sensor1", type, h.getLocation(), new Date());
        for (int i = 0; i < mRoomSensorList.getSensors().length; i++) {
            s = mRoomSensorList.getSensors()[i];
        }
        return s.getReadingList().getMostRecentValueOfReading();
    }

    public String printRoom() {
        String result;
        result = this.mRoomName + ", " + this.getHouseFloor() + ", " +
                this.getRoomWidth() + ", "+ this.getRoomLength() + ", " + this.getRoomHeight() + ".\n";
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(mRoomName, room.mRoomName);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}



