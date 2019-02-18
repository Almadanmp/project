package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
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
    private int mGridMeteringPeriod;
    private int mDeviceMeteringPeriod;
    private List<DeviceType> mDeviceTypeList;

    //CONSTRUCTOR

    public House(String mId, String mStreet, String mZip, String mTown, Local mLocation, GeographicArea mMotherArea,
                 int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        this.mId = mId;
        this.mStreet = mStreet;
        this.mZip = mZip;
        this.mTown = mTown;
        this.mLocation = mLocation;
        this.mMotherArea = mMotherArea;
        this.mRoomList = new RoomList();
        this.mEGList = new EnergyGridList();
        this.mGridMeteringPeriod = gridMeteringPeriod;
        this.mDeviceMeteringPeriod = deviceMeteringPeriod;
        buildDeviceTypeList(deviceTypeConfig);
    }

    /**
     * Method that will instantiate an object from each device Type path in device.properties file
     * and add it to the List<DeviceType> attribute in House class.
     *
     * @param deviceTypePaths List of Strings with all the device paths (values) from device.properties file
     */
    void buildDeviceTypeList(List<String> deviceTypePaths) {
        this.mDeviceTypeList = new ArrayList<>();
        for (String s : deviceTypePaths) {
            DeviceType aux;
            try {
                aux = (DeviceType) Class.forName(s).newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException("ERROR: Unable to create device type from path - " + e.getMessage());
            }
            mDeviceTypeList.add(aux);
        }
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

    void setGridMeteringPeriod(int meteringPeriod) {
        this.mGridMeteringPeriod = meteringPeriod;
    }

    double getGridMeteringPeriod() {
        return mGridMeteringPeriod;
    }

    void setDeviceMeteringPeriod(int meteringPeriod) {
        this.mDeviceMeteringPeriod = meteringPeriod;
    }

    double getDeviceMeteringPeriod() {
        return mDeviceMeteringPeriod;
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

    public List<DeviceType> getDeviceTypeList() {
        return mDeviceTypeList;
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
     * @param sensor sensor from where to calculate the distance
     * @return returns the distance between sensor and the house
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
            Sensor sensor = ga.getSensorList().getSensors()[i];
            if (distance > calculateDistanceToSensor(sensor)) {
                distance = calculateDistanceToSensor(sensor);
            }
        }
        return distance;
    }

    /**
     * This method returns the sensor closest to the house. If more than one sensor is close to it,
     * the one with the most recent reading should be used.
     *
     * @param ga
     * @param sensorType
     * @return
     */
    public Sensor getClosestSensorOfGivenType(GeographicArea ga, String sensorType) {
        Sensor sensor;
        SensorList sensorList = new SensorList();
        Sensor sensorError = new Sensor("EmptyList", new TypeSensor("temperature", " "), new Local(0, 0, 0), new GregorianCalendar(1900, 1, 1).getTime());
        for (Sensor s : ga.getSensorList().getSensorListByType(sensorType)) {
            if (Double.compare(this.getMinDistanceFromHouseToSensor(ga), s.getDistanceToHouse(this)) == 0) {
                sensorList.addSensor(s);
            }
        }

        if (sensorList.getSensorList().isEmpty()) {
            return sensorError;
        }
        if (sensorList.getSensorList().size() >= 2) {
            sensor = sensorList.getMostRecentlyUsedSensor();
        } else {
            sensor = sensorList.getSensorList().get(0);
        }
        return sensor;
    }


    /**
     * This method builds a String for the GridList in the house.
     *
     * @return
     */
    public String buildGridListString() {
        String mStringEnhancer = "---------------\n";
        StringBuilder result = new StringBuilder(mStringEnhancer);
        if (this.mEGList.getEnergyGridList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.mEGList.getEnergyGridList().size(); i++) {
            EnergyGrid aux = this.mEGList.getEnergyGridList().get(i);
            result.append(i).append(") Designation: ").append(aux.getName()).append(" | ");
            result.append("Max Power: ").append(aux.getMaxContractedPower()).append("\n");
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
    public List<Device> getDevicesOfGivenType(String deviceType) {
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


    public String buildTypeListString(List<DeviceType> list) {
        StringBuilder result = new StringBuilder(new StringBuilder());
        if (list.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < list.size(); i++) {
            result.append(i).append(") DeviceType: ").append(list.get(i).getDeviceType()).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns the daily estimate of the consumption of all devices of a given type, in all rooms of this house.
     *
     * @param deviceType the device type
     * @return the sum of all daily estimate consumptions of that type
     */
    public double getDailyConsumptionByDeviceType(String deviceType) {
        return mRoomList.getDailyConsumptionByDeviceType(deviceType);
    }

    public boolean addGrid(EnergyGrid energyGrid) {
        return this.mEGList.addGrid(energyGrid);
    }


    public double getEnergyConsumption(float time) {
        return 0;
    }

    /**This method receives room parameters, checks if room exists in house and
     * creates room in case it doesn't. In the end, the room will be added to the house
     * and the method will return true.
     * @return true in case the room is added to house, false otherwise
     * **/
    public boolean createRoom(String roomDesignation, int roomHouseFloor, double width, double length, double height){
        if(!containsRoomByName(roomDesignation)){
            Room room = new Room(roomDesignation, roomHouseFloor, width, length, height);
            mRoomList.addRoom(room);
            return true;
        }
        return false;
    }

    /**This receives a room designation and checks if house has room with
     * that designation, returning true in case it does and false in case it does not.
     * @return true in case a room with the given designation exists in house, false otherwise
     * **/
    public boolean containsRoomByName(String roomDesignation){
        for(Room r : this.mRoomList.getList()){
            String nameTest = r.getRoomName();
            if(nameTest.equals(roomDesignation)){
                return true;
            }
        }
        return false;
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

