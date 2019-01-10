package pt.ipp.isep.dei.project.model;

import java.util.Objects;

/**
 * House Class. Defines de House
 */
public class House {
    private String mId;
    private String mStreet;
    private String mZip;
    private String mTown;
    private Local mLocation;
    private EnergyGridList mEGList;
    private RoomList mRoomList;
    private GeographicArea mMotherArea;
    private String mStringEnhancer = "---------------\n";

    //CONSTRUCTORS
    public House() {
        this.mRoomList = new RoomList();
    }

    public House(String mId, String mStreet, String mZip, String mTown, Local mLocation, GeographicArea mMotherArea, RoomList mRoomList) {
        this.mId = mId;
        this.mStreet = mStreet;
        this.mZip = mZip;
        this.mTown = mTown;
        this.mLocation = mLocation;
        this.mMotherArea = mMotherArea;
        this.mRoomList = mRoomList;
        this.mEGList = new EnergyGridList();
    }

    //SETTERS AND GETTERS

    public String getHouseId() {
        return mId;

    }

    void setId(String id) {
        this.mId = id;
    }

    String getStreet() {
        return this.mStreet;
    }

    public void setStreet(String mStreet) {
        this.mStreet = mStreet;
    }

    String getZip() {
        return mZip;
    }

    public void setZip(String mZip) {
        this.mZip = mZip;
    }

    String getTown() {
        return mTown;
    }

    void setTown(String town) {
        this.mTown = town;
    }

    Local getLocation() {
        return mLocation;
    }

    public void setLocation(double latitude, double longitude, double altitude) {
        mLocation.setLatitude(latitude);
        mLocation.setLongitude(longitude);
        mLocation.setAltitude(altitude);
    }

    public void setmMotherArea(GeographicArea mMotherArea) {
        this.mMotherArea = mMotherArea;
    }

    public void setRoomList(RoomList roomList) {
        this.mRoomList = roomList;
    }

    public RoomList getRoomList() {
        return this.mRoomList;
    }

    public GeographicArea getMotherArea() {
        return mMotherArea;
    }

    public EnergyGridList getEGList() {
        return this.mEGList;
    }

    public void setEGList(EnergyGridList energyGridList) {
        this.mEGList = energyGridList;
    }


    public boolean addRoomToRoomList(Room room) {
        String roomToAddName = room.getRoomName();
        for (Room r : this.mRoomList.getRoomList()) {
            String roomNameTest = r.getRoomName();
            if (roomNameTest.equals(roomToAddName))
                return false; }
        this.mRoomList.addRoom(room);
        return true;
    }

    String printHouse() {
        String result;
        result = this.mId + ", " + this.mStreet + ", " + this.mZip + ", " +
                this.mTown + ".\n";
        return result;
    }

    double calculateDistanceToSensor(Sensor sensor) {
        Local l = sensor.getLocal();
        return this.mLocation.getLinearDistanceBetweenLocalsInKm(l);
    }

    double getMinDistanceFromHouseToSensor(GeographicArea ga) {
        Sensor firstSensor = ga.getSensorList().getSensors()[0];
        double distance = calculateDistanceToSensor(firstSensor);
        for (int i = 0; i < ga.getSensorList().getSensors().length; i++) {
            Sensor copo = ga.getSensorList().getSensors()[i];
            if (distance > calculateDistanceToSensor(copo)) {
                distance = calculateDistanceToSensor(copo);
            }
        }
        return distance;
    }

    public Sensor getSensorWithMinDistanceToHouse(GeographicArea ga, House house) {
        for (Sensor s : ga.getSensorList().getSensorListByType("temperature")) {
            if (Double.compare(house.getMinDistanceFromHouseToSensor(ga), s.getDistanceToHouse(house)) == 0) {
                return s;
            }
        }
        return null;
    }

    public String printGridList() {
        StringBuilder result = new StringBuilder(mStringEnhancer);
        if (this.mEGList.getEnergyGridList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.mEGList.getEnergyGridList().size(); i++) {
            EnergyGrid aux = this.mEGList.getEnergyGridList().get(i);
            result.append(i).append(") Designation: ").append(aux.getName()).append(" | ");
            result.append("Max Power: ").append(aux.getMaxPower()).append("\n");
        }
        result.append(mStringEnhancer);
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        House house = (House) o;
        return Objects.equals(mStreet, house.mStreet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mStreet);
    }
}

