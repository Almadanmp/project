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

    //CONSTRUCTORS
    public House() {
        this.mRoomList = new RoomList();
        this.mEGList = new EnergyGridList();
        }

    public House(String mId, String mStreet, String mZip, String mTown, Local mLocation, GeographicArea mMotherArea, RoomList mRoomList) {
        this.mId = mId;
        this.mStreet = mStreet;
        this.mZip = mZip;
        this.mTown = mTown;
        this.mLocation = mLocation;
        this.mMotherArea = mMotherArea;
        this.mRoomList = mRoomList;
    }

    //SETTERS AND GETTERS

    public String getmHouseId() {
        return mId;

    }
    void setmId(String id){ this.mId = id;}

    String getmStreet() {
        return this.mStreet;
    }

    public void setmStreet(String mStreet) {
        this.mStreet = mStreet;
    }

    String getmZip() {
        return mZip;
    }

    public void setmZip(String mZip) {
        this.mZip = mZip;
    }

    String getmTown() {
        return mTown;
    }
    void setmTown(String town) {
        this.mTown = town;
    }

    Local getmLocation() {
        return mLocation;
    }

    public void setmLocation(double latitude, double longitude, double altitude) {
        mLocation.setLatitude(latitude);
        mLocation.setLongitude(longitude);
        mLocation.setAltitude(altitude);
    }

    public void setmRoomList(RoomList roomList) {
        this.mRoomList = roomList;
    }

    public RoomList getmRoomList() {
        return this.mRoomList;
    }

    public GeographicArea getmMotherArea() {
        return mMotherArea;
    }

    public void setmMotherArea(GeographicArea mMotherArea) {
        this.mMotherArea = mMotherArea;
    }

    public EnergyGridList getmEGList() {
        return this.mEGList;
    }

    public void setmEGList(EnergyGridList energyGridList) {
        this.mEGList = energyGridList;
    }


    public boolean addRoomToRoomList(Room room) {
        String roomToAddName = room.getRoomName();
        for (Room r : this.mRoomList.getListOfRooms()) {
            String roomDesignationToTest = r.getRoomName();
            if (roomDesignationToTest.equals(roomToAddName)) {
                return false; } }
        this.mRoomList.addRoom(room);
        return true;
    }

    public String printHouse() {
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
            if (distance > calculateDistanceToSensor(copo)) { distance = calculateDistanceToSensor(copo);} }
        return distance;
    }

    public Sensor getSensorWithMinDistanceToHouse(GeographicArea ga, House house) {
        for (Sensor s : ga.getSensorList().getSensorListByType("temperature")) {
            if (Double.compare(house.getMinDistanceFromHouseToSensor(ga), s.getDistanceToHouse(house)) == 0) {
                return s;
            } }
        return null;
    }

    public String printGridList() {
        String result = "---------------\n";
        if (this.mEGList.getEnergyGridList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.mEGList.getEnergyGridList().size(); i++) {
            EnergyGrid aux = this.mEGList.getEnergyGridList().get(i);
            result = result + i + ") Designation: " + aux.getName() + " | ";
            result = result + "Max Power: " + aux.getMaxPower() + "\n";
        }
        result = result + "---------------\n";
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        House house = (House) o;
        return Objects.equals(mStreet, house.mStreet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mStreet);
    }
}

