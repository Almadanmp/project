package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    //CONSTRUCTOR

    public House(String mId, String mStreet, String mZip, String mTown, Local mLocation, GeographicArea mMotherArea) {
        this.mId = mId;
        this.mStreet = mStreet;
        this.mZip = mZip;
        this.mTown = mTown;
        this.mLocation = mLocation;
        this.mMotherArea = mMotherArea;
        this.mRoomList = new RoomList();
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
        for (Room r1 : mRoomList.getList()) {
            result += r1.getNominalPower();
        }
        return result;
    }

    public Local getLocation() {
        return mLocation;
    }

    public List<Device> getDeviceList() {
        return this.mRoomList.getDeviceList();
    }

    public void setLocation(double latitude, double longitude, double altitude) {
        mLocation.setLatitude(latitude);
        mLocation.setLongitude(longitude);
        mLocation.setAltitude(altitude);
    }

    public void setRoomList(RoomList roomList) {
        this.mRoomList = roomList;
    }

    public void setMotherArea(GeographicArea motherArea) {
        this.mMotherArea = motherArea;
    }

    public List<Room> getRoomList() {
        return this.mRoomList.getList();
    }

    public GeographicArea getMotherArea() {
        return mMotherArea;
    }

    public EnergyGridList getEGListObject() {
        return this.mEGList;
    }

    public List<EnergyGrid> getEGList() {
        return this.mEGList.getEnergyGridList();
    }

    public void setEGList(EnergyGridList energyGridList) {
        this.mEGList = energyGridList;
    }


    public boolean addRoomToRoomList(Room room) {
        String roomToAddName = room.getRoomName();
        for (Room r : this.mRoomList.getList()) {
            String roomNameTest = r.getRoomName();
            if (roomNameTest.equals(roomToAddName))
                return false;
        }
        this.mRoomList.addRoom(room);
        return true;
    }

    String buildHouseString() {
        String result;
        result = this.mId + ", " + this.mStreet + ", " + this.mZip + ", " +
                this.mTown + ".\n";
        return result;
    }

    /**
     * calculates distance from the house to the sensor.
     *
     * @param sensor
     * @return
     */
    double calculateDistanceToSensor(Sensor sensor) {
        Local l = sensor.getLocal();
        return this.mLocation.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * calculates minimum distance from the house to a list of sensors
     *
     * @param ga
     * @return
     */
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

    /**
     * gets the sensor with minimum distance to the house.
     *
     * @param ga
     * @param house
     * @param sensorType
     * @return
     */
    public Sensor getSensorWithMinDistanceToHouse(GeographicArea ga, House house, String sensorType) {
        for (Sensor s : ga.getSensorList().getSensorListByType(sensorType)) {
            if (Double.compare(house.getMinDistanceFromHouseToSensor(ga), s.getDistanceToHouse(house)) == 0) {
                return s;
            }
        }
        return null;
    }

    public String buildGridListString() {
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
     * @return builds a string from the House's room list.
     */


    public String buildRoomListString() {
        return this.mRoomList.buildRoomsString();
    }

    /**
     * @param indexList is a list of integers that represent positions in a list.
     * @return builds a string from the individual elements in the RoomList that are contained in the positions
     * given by the list of indexes.
     */

    public String buildRoomsByIndexString(List<Integer> indexList) {
        return this.mRoomList.buildElementsByIndexString(indexList);
    }

    /**
     * @param input is the name of room we want to look for.
     * @return is a list of integers, representing positions in the roomList, of rooms whose name matches
     * input string.
     */
    public List<Integer> matchRoomIndexByString(String input) {
        return this.mRoomList.matchRoomIndexByString(input);
    }


    /**
     * Returns a list of devices of a given type, in all rooms of this house.
     *
     * @param deviceType the device type
     * @return the list with all devices of a given type
     */
    public List<Device> getDevicesOfGivenType(DeviceType deviceType) {
        List<Device> devicesOfGivenType = new ArrayList<>();
        for (Room r : mRoomList.getList()) {
            devicesOfGivenType.addAll(r.getDevicesOfGivenType(deviceType));
        }
        return devicesOfGivenType;
    }

    List<Device> getAllHouseDevices() {
        List<Device> allDevices = new ArrayList<>();
        for (Room r : mRoomList.getList()) {
            allDevices.addAll(r.getDeviceList());
        }
        return allDevices;
    }

    /**
     * Method to get all available device Types from the Configuration File.
     *
     * @return string will all available device types
     */
    public List<String> getDeviceTypes() {
        Properties props = new Properties();
        String propFileName = "resources/devices.properties";
        InputStream input = null;
        String deviceTypes = " ";
        try {
            input = new FileInputStream(propFileName);
            if (input != null) {
                props.load(input);
                deviceTypes = props.getProperty("allDeviceTypes");

            } else {
                throw new FileNotFoundException("property file " + propFileName + "not found in the classpath.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
        List<String> listOfDeviceTypes = new ArrayList<>(Arrays.asList(deviceTypes.split(",")));
        return listOfDeviceTypes;
    }

    /**
     * Method to return the path to a selected Device Type Class by the user
     * @param id - String with the identification of the device type selected
     * @return string with the path to the class file
     */
   public String getDeviceTypePathToClassById(String id) {
        Properties props = new Properties();
        String propFileName = "resources/devices.properties";
        InputStream input = null;
        String deviceTypePath = " ";
        try {
            input = new FileInputStream(propFileName);
            if (input != null) {
                props.load(input);
                deviceTypePath = props.getProperty(id);

            } else {
                throw new FileNotFoundException("property file " + propFileName + "not found in the classpath.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
        return deviceTypePath;
    }

    /**
     * Returns the daily estimate of the consumption of all devices of a given type, in all rooms of this house.
     *
     * @param deviceType the device type
     * @return the sum of all daily estimate consumptions of that type
     */
    public double getDailyConsumptionByDeviceType(DeviceType deviceType) {
        return mRoomList.getDailyConsumptionByDeviceType(deviceType);
    }

    public void addGrid(EnergyGrid energyGrid) {
        this.mEGList.addGrid(energyGrid);
    }


    @Override
    public double getEnergyConsumption(float time) {
        return 0;
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

