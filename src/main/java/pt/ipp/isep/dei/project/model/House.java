package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

import java.util.Date;
import java.util.Objects;

/**
 * House Class. Defines de House
 */
public class House implements Metered {
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

    public String getStreet() {
        return this.mStreet;
    }

    public void setStreet(String mStreet) {
        this.mStreet = mStreet;
    }

    public String getZip() {
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

    public double getNominalPower() {
        double result = 0;
        for (Room r1 : mRoomList.getRoomList()) {
            result += r1.getNominalPower();
        }
        return result;
    }

    public Local getLocation() {
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
                return false;
        }
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

    public Sensor getSensorWithMinDistanceToHouse(GeographicArea ga, House house, String sensorType) {
        for (Sensor s : ga.getSensorList().getSensorListByType(sensorType)) {
            if (Double.compare(house.getMinDistanceFromHouseToSensor(ga), s.getDistanceToHouse(house)) == 0) {
                return s;
            }
        }
        return null;
    }

    public String printGridList() {
        String mStringEnhancer = "---------------\n";
        StringBuilder result = new StringBuilder(mStringEnhancer);
        if (this.mEGList.getEnergyGridList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.mEGList.getEnergyGridList().size(); i++) {
            EnergyGrid aux = this.mEGList.getEnergyGridList().get(i);
            result.append(i).append(") Designation: ").append(aux.getName()).append(" | ");
            result.append("Max Power: ").append(aux.getNominalPower()).append("\n");
        }
        result.append(mStringEnhancer);
        return result.toString();
    }

    /**
     * Returns the sum of the consumption of all devices of a given type, in a given day, in all rooms of this house.
     *
     * @param deviceType the device type
     * @param date       the day
     * @return the sum of all daily consumptions of that type
     */
    public double getDailyHouseConsumptionPerType(DeviceType deviceType, Date date) {
        double result = 0;
        for (Room r : mRoomList.getRoomList()) {
            result += r.getDailyRoomConsumptionPerType(deviceType, date);
        }
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
        House house = (House) o;
        return Objects.equals(mStreet, house.mStreet);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

