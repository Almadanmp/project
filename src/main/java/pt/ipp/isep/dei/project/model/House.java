package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.*;

/**
 * House Class. Defines de House
 */

public class House implements Metered {
    private String id;
    private Address address;
    private Local location;
    private EnergyGridList mEGList;
    private RoomList mRoomList;
    private GeographicArea motherArea;
    private int gridMeteringPeriod;
    private int deviceMeteringPeriod;
    private List<DeviceType> deviceTypeList;

    //CONSTRUCTOR

    public House(String id, Address address, Local mLocation, GeographicArea mMotherArea,
                 int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        this.id = id;
        this.address = address;
        this.location = mLocation;
        this.motherArea = mMotherArea;
        this.mRoomList = new RoomList();
        this.mEGList = new EnergyGridList();
        this.gridMeteringPeriod = gridMeteringPeriod;
        this.deviceMeteringPeriod = deviceMeteringPeriod;
        buildDeviceTypeList(deviceTypeConfig);
    }

    /**
     * Method that will instantiate an object from each device Type path in device.properties file
     * and add it to the List<DeviceType> attribute in House class.
     *
     * @param deviceTypePaths List of Strings with all the device paths (values) from device.properties file
     */
    void buildDeviceTypeList(List<String> deviceTypePaths) {
        this.deviceTypeList = new ArrayList<>();
        for (String s : deviceTypePaths) {
            DeviceType aux;
            try {
                aux = (DeviceType) Class.forName(s).newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException("ERROR: Unable to create device type from path - " + e.getMessage());
            }
            deviceTypeList.add(aux);
        }
    }


    //SETTERS AND GETTERS


    public String getHouseId() {
        return this.id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public double getNominalPower() {
        double result = 0;
        for (Room r1 : mRoomList.getList()) {
            result += r1.getNominalPower();
        }
        return result;
    }

    void setGridMeteringPeriod(int meteringPeriod) {
        this.gridMeteringPeriod = meteringPeriod;
    }

    double getGridMeteringPeriod() {
        return gridMeteringPeriod;
    }

    void setDeviceMeteringPeriod(int meteringPeriod) {
        this.deviceMeteringPeriod = meteringPeriod;
    }

    double getDeviceMeteringPeriod() {
        return deviceMeteringPeriod;
    }

    Local getLocation() {
        return location;
    }

    public List<Device> getDeviceList() {
        return this.mRoomList.getListOfDevices();
    }

    public void setLocation(double latitude, double longitude, double altitude) {
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setAltitude(altitude);
    }

    public void setRoomList(RoomList roomList) {
        this.mRoomList = roomList;
    }

    public void setMotherArea(GeographicArea motherArea) {
        this.motherArea = motherArea;
    }

    public List<Room> getListOfRooms() {
        return this.mRoomList.getList();
    }

    /**
     * Method that gives the house room list.
     *
     * @return house RoomList
     **/
    public RoomList getRoomList() {
        return this.mRoomList;
    }

    public GeographicArea getMotherArea() {
        return motherArea;
    }

    public EnergyGridList getGridList() {
        return this.mEGList;
    }

    public List<EnergyGrid> getListOfGrids() {
        return this.mEGList.getEnergyGridList();
    }

    public void setEGList(EnergyGridList energyGridList) {
        this.mEGList = energyGridList;
    }

    public List<DeviceType> getDeviceTypeList() {
        return deviceTypeList;
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

    /**
     * calculates distance from the house to the sensor.
     *
     * @param sensor sensor from where to calculate the distance
     * @return returns the distance between sensor and the house
     */
    double calculateDistanceToSensor(Sensor sensor) {
        Local l = sensor.getLocal();
        return this.location.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Searches within the list of sensors of a given type in a given geographic area for the distance to
     * the closest sensor the house.
     *
     * @param type is the type we want to search for.
     * @return is the value of the distance of the house to sensor of the given type closest to it.
     */

    double getMinDistanceToSensorOfGivenType(String type) {
        List<Sensor> workingList = this.getMotherArea().getSensorList().getSensorListByType(type);
        ArrayList<Double> arrayList = new ArrayList<>();
        for (Sensor sensor : workingList) {
            arrayList.add(calculateDistanceToSensor(sensor));
        }
        return Collections.min(arrayList);
    }

    /**
     * This method returns the sensor closest to the house. If more than one sensor is close to it,
     * the one with the most recent reading should be used.
     *
     * @param sensorType
     * @return
     */
    public Sensor getClosestSensorOfGivenType(String sensorType) {
        Sensor sensor;
        SensorList sensorList = new SensorList();
        Sensor sensorError = new Sensor("EmptyList", new TypeSensor("temperature", " "), new Local(0, 0, 0), new GregorianCalendar(1900, 1, 1).getTime());
        for (Sensor s : this.getMotherArea().getSensorList().getSensorListByType(sensorType)) {
            if (Double.compare(this.getMinDistanceToSensorOfGivenType(sensorType), s.getDistanceToHouse(this)) == 0) {
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
     * @return string with energy grid list
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
     * @param time       represents a day in minutes
     * @return the sum of all daily estimate consumptions of that type
     */
    public double getDailyConsumptionByDeviceType(String deviceType, int time) {
        return mRoomList.getDailyConsumptionByDeviceType(deviceType, time);
    }

    public boolean addGrid(EnergyGrid energyGrid) {
        return this.mEGList.addGrid(energyGrid);
    }


    public double getEnergyConsumption(float time) {
        return 0;
    }

    /**
     * This method receives room parameters, checks if room exists in house and
     * returns room with same designation in case it does. In case the room does not
     * exit, a new room will be created and returned.
     *
     * @return room with characteristics given as parameters
     **/
    public Room createRoom(String roomDesignation, int roomHouseFloor, double width, double length, double height) {
        for (Room r : this.mRoomList.getList()) {
            String designation = r.getRoomName();
            if (roomDesignation.equals(designation)) {
                return r;
            }
        }
        Room room = new Room(roomDesignation, roomHouseFloor, width, length, height);
        this.mRoomList.addRoom(room);
        return room;
    }

    /**
     * This receives a room designation and checks if house has room with
     * that designation, returning true in case it does and false in case it does not.
     *
     * @return true in case a room with the given designation exists in house, false otherwise
     **/
    boolean containsRoomByName(String roomDesignation) {
        for (Room r : this.mRoomList.getList()) {
            String nameTest = r.getRoomName();
            if (nameTest.equals(roomDesignation)) {
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
        return Objects.equals(this.address, house.address);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

