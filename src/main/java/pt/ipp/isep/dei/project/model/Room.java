package pt.ipp.isep.dei.project.model;

import java.util.Date;
import java.util.Objects;

public class Room {
    private String mRoomName;
    private int mHouseFloor;
    private double mRoomWidth;
    private double mRoomLength;
    private double mRoomHeight;
    private SensorList mRoomSensorList;


    public Room(String name, int houseFloor, double width, double length, double height) {
        setRoomName(name);
        setRoomHouseFloor(houseFloor);
        setRoomWidth(width);
        setRoomLength(length);
        setRoomHeight(height);
    }

    public SensorList getmRoomSensorList() {
        return mRoomSensorList;
    }

    public void setRoomName(String name) {
        mRoomName = name;
    }

    public void setRoomHouseFloor(int houseFloor) {
        mHouseFloor = houseFloor;
    }

    public void setRoomHeight(double height) {
        mRoomHeight = height;
    }

    public void setRoomLength(double length) {
        mRoomLength = length;
    }

    public void setRoomWidth(double width){mRoomWidth=width;}

    public double getRoomHeight() {
        return mRoomHeight;
    }

    public double getRoomLength() {
        return mRoomLength;
    }

    public double getRoomWidth(){ return mRoomWidth;}


    public void setRoomSensorList(SensorList sensorList) {
        mRoomSensorList = sensorList;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public int getHouseFloor() {
        return mHouseFloor;
    }


    public SensorList getRoomSensorList() {
        return mRoomSensorList;
    }

    public double getMaxTemperatureInARoomOnAGivenDay(Date day) {
        SensorList list = getRoomSensorList();
        House h = new House();
        TypeSensor type = new TypeSensor("temperature","Celsius");
        Sensor s = new Sensor("sensor1", type, h.getmLocation(), new Date());
        for (int i = 0; i < list.getSensors().length; i++) {
            s = list.getSensors()[i];
        }
        return s.getReadingList().getMaximumOfGivenDayValueReadings(day);
    }

    public boolean doesSensorListInARoomContainASensorByName(String name) {
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
     * @param day
     * @return
     */

    public double getCurrentRoomTemperature(Date day) {
        House h = new House();
        TypeSensor type = new TypeSensor("temperature","Celsius");
        Sensor s = new Sensor("sensor1", type, h.getmLocation(), new Date());
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



