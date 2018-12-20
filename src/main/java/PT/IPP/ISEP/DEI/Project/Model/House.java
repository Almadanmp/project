package PT.IPP.ISEP.DEI.Project.Model;

import java.util.Objects;

/**
 * House Class. Defines de House
 */
public class House {
    private String mDesignation;
    private String mAddress;
    private Local mGPS;
    private String mZipCode;
    GeographicArea mMotherGA;
    private EnergyGridList mEGList;
    private RoomList mRoomList;

    //CONSTRUCTORS
    public House() {
    }

    public House(String mAddress, Local mGPS, String mZipCode) {
        this.mAddress = mAddress;
        this.mGPS = mGPS;
        this.mZipCode = mZipCode;

    }

    public House(String designation, String mAddress, Local mGPS, String mZipCode) {
        this.mDesignation = designation;
        this.mAddress = mAddress;
        this.mGPS = mGPS;
        this.mZipCode = mZipCode;

    }

    public House(String designation, String mAddress, Local mGPS, String mZipCode, GeographicArea mMotherGA) {
        this.mDesignation = designation;
        this.mAddress = mAddress;
        this.mGPS = mGPS;
        this.mZipCode = mZipCode;
        this.mMotherGA = mMotherGA;
    }

    public House(String designation, String mAddress, Local mGPS, String mZipCode, RoomList roomList) {
        this.mDesignation = designation;
        this.mAddress = mAddress;
        this.mGPS = mGPS;
        this.mZipCode = mZipCode;
        this.mRoomList = roomList;
    }

    //SETTERS AND GETTERS

    public String getHouseDesignation() {
        return mDesignation;
    }

    public void setHouseDesignation(String designation) {
        mDesignation = designation;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public Local getmGPS() {
        return mGPS;
    }

    public void setmGPS(Local mGPS) {
        this.mGPS = mGPS;
    }

    public String getmZipCode() {
        return mZipCode;
    }

    public void setmZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }

    public GeographicArea getmMotherGA() {
        return mMotherGA;
    }

    public void setmMotherGA(GeographicArea mMotherGA) {
        this.mMotherGA = mMotherGA;
    }

    public void setmRoomList(RoomList roomList) {
        this.mRoomList = roomList;
    }

    public boolean addRoomToRoomList(Room roomToAdd) {
        String roomToAddName = roomToAdd.getRoomName();
        for (Room r : this.mRoomList.getListOfRooms()) {
            String roomDesignationToTest = r.getRoomName();
            if (roomDesignationToTest.equals(roomToAddName)) {
                return false;
            }
        }
        this.mRoomList.addRoom(roomToAdd);
        return true;
    }

    public double calculateDistanceToSensor(Sensor sensor) {
        Local l = sensor.getLocal();
        return this.mGPS.getLinearDistanceBetweenLocalsInKm(l);
    }

    public double getTheMinorDistanceFromTheHouseToTheSensor() {
        Sensor firstSensor = getmMotherGA().getSensorList().getSensors()[0];
        double distance = calculateDistanceToSensor(firstSensor);
        for (int i = 0; i < getmMotherGA().getSensorList().getSensors().length; i++) {
            Sensor copo = getmMotherGA().getSensorList().getSensors()[i];
            if (distance > calculateDistanceToSensor(copo)) {
                distance = calculateDistanceToSensor(copo);
            }
        }
        return distance;
    }

    public Sensor getSensorWithTheMinimumDistanceToHouse(House house) {
        for (Sensor s : house.getmMotherGA().getSensorList().getSensors()) {
            if (house.getTheMinorDistanceFromTheHouseToTheSensor() == s.getDistanceToHouse(house)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(mAddress, house.mAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAddress);
    }
}

