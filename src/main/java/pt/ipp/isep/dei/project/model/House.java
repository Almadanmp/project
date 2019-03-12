package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.*;

/**
 * House Class. Defines de House
 */

public class House implements Metered {
    private String id;
    private Address address;
    private Local location;
    private EnergyGridList energyGridList;
    private RoomList roomList;
    private GeographicArea motherArea;
    private int gridMeteringPeriod;
    private int deviceMeteringPeriod;
    private List<DeviceType> deviceTypeList;

    // Constructor

    public House(String id, Address address, Local mLocation, GeographicArea mMotherArea,
                 int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        this.id = id;
        this.address = address;
        this.location = mLocation;
        this.motherArea = mMotherArea;
        this.roomList = new RoomList();
        this.energyGridList = new EnergyGridList();
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


    public void setId(String id) {
        this.id = id;
    }

    public String getHouseId() {
        return this.id;
    }

    public Address getAddress() {
        return address;
    }

    public double getNominalPower() {
        return this.roomList.getNominalPower();
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

    public void setLocation(double latitude, double longitude, double altitude) {
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setAltitude(altitude);
    }

    public void setAddress(String street, String zip, String town) {
        address.setStreet(street);
        address.setZip(zip);
        address.setTown(town);
    }

    public void setRoomList(RoomList roomList) {
        if (roomList != null) {
            this.roomList = roomList;
        }
    }

    public void setMotherArea(GeographicArea motherArea) {
        this.motherArea = motherArea;
    }

    /**
     * Method that gives the house room list.
     *
     * @return house RoomList
     **/
    public RoomList getRoomList() {
        return this.roomList;
    }

    public GeographicArea getMotherArea() {
        return motherArea;
    }

    public EnergyGridList getGridList() {
        return this.energyGridList;
    }

    public void setGridList(EnergyGridList energyGridList) {
        this.energyGridList = energyGridList;
    }

    public List<DeviceType> getDeviceTypeList() {
        return deviceTypeList;
    }

    public boolean addRoom(Room room) {
        return this.roomList.add(room);
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
        SensorList workingList = this.motherArea.getSensorsOfGivenType(type);
        List<Double> arrayList = workingList.getSensorsDistanceToHouse(this);
        return Collections.min(arrayList);
    }

    /**
     * This method returns the sensor closest to the house. If more than one sensor is close to it,
     * the one with the most recent reading should be used.
     *
     * @param sensorType the type of sensor to check
     * @return the closest sensor.
     */
    public Sensor getClosestSensorOfGivenType(String sensorType) {
        Sensor sensor;
        SensorList minDistSensors = new SensorList();
        Sensor sensorError = new Sensor("RF12345","EmptyList", new TypeSensor("temperature", " "), new Local(0, 0, 0), new GregorianCalendar(1900, 1, 1).getTime());
        SensorList sensorsType = this.motherArea.getSensorsOfGivenType(sensorType);
        if (!sensorsType.isEmpty()) {
            double minDist = this.getMinDistanceToSensorOfGivenType(sensorType);
            minDistSensors = sensorsType.getSensorsByDistanceToHouse(this, minDist);
        }
        if (minDistSensors.isEmpty()) {
            return sensorError;
        }
        if (minDistSensors.size() >= 2) {
            sensor = minDistSensors.getMostRecentlyUsedSensor();
        } else {
            sensor = minDistSensors.get(0);
        }
        return sensor;
    }


    /**
     * This method builds a String for the GridList in the house.
     *
     * @return string with energy grid list
     */
    public String buildGridListString() {
        return this.energyGridList.buildString();
    }

    /**
     * @return builds a string from the House's room list.
     */
    public String buildRoomListString() {
        return this.roomList.buildString();
    }


    /**
     * Returns a list of devices of a given type, in all rooms of this house.
     *
     * @param deviceType the device type
     * @return the list with all devices of a given type
     */
    public DeviceList getDevicesOfGivenType(String deviceType) {
        DeviceList houseDevices = getDeviceList();
        return houseDevices.getDevicesOfGivenType(deviceType);
    }

    /**
     * this method builds a String for the deviceTypes available in the house
     *
     * @return string with device types
     */

    public String buildDeviceTypeString() {
        StringBuilder result = new StringBuilder(new StringBuilder());
        if (deviceTypeList.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < deviceTypeList.size(); i++) {
            result.append(i).append(") DeviceType: ").append(deviceTypeList.get(i).getDeviceType()).append("\n");//TODO como encapsular este método?

        }
        return result.toString();
    }

    /**
     * Returns an estimate of what the house's energy consumption would be in a full day, for the devices of a given
     * type.
     *
     * @param deviceType the given device type.
     * @param time       represents a day in minutes.
     * @return the sum of all estimate daily consumptions for devices of that type.
     */
    public double getDailyConsumptionByDeviceType(String deviceType, int time) {
        return roomList.getDailyConsumptionByDeviceType(deviceType, time);
    }

    public boolean addGrid(EnergyGrid energyGrid) {
        return this.energyGridList.addGrid(energyGrid);
    }


    public double getEnergyConsumption(float time) {
        throw new UnsupportedOperationException("This operation is unsupported.");
    } //TODO Make this method throw an UnsupportedOperationException.

    /**
     * This method receives room parameters, checks if room exists in house and
     * returns room with same designation in case it does. In case the room does not
     * exit, a new room will be created and returned.
     *
     * @param roomDesignation the designation of the room.
     * @param roomHouseFloor  the floor of the room.
     * @param width           the width of the room.
     * @param height          the height of the room.
     * @param length          the length of the room.
     * @return room with characteristics given as parameters
     **/
    public Room createRoom(String roomDesignation, int roomHouseFloor, double width, double length, double height) {
        return this.roomList.createRoom(roomDesignation, roomHouseFloor, width, length, height);
    }

    /**
     * This method checks if house's RoomList is empty.
     *
     * @return true if house's RoomList is empty, false otherwise
     **/
    public boolean isRoomListEmpty() {
        return this.roomList.isEmpty();
    }


    /**
     * This method checks if house's list of Energy Grids is empty.
     *
     * @return true if house's EnergyGridList is empty, false otherwise.
     **/
    public boolean isEnergyGridListEmpty() {
        return this.energyGridList.isEmpty();
    }

    /**
     * This method checks the house's energy grid list size.
     *
     * @return returns the house's energy grid list size as int.
     */
    public int energyGridListSize() {
        return this.energyGridList.size();
    }

    /**
     * This method checks the house's room list size.
     *
     * @return returns the house's room list size as int.
     */
    public int roomListSize() {
        return this.roomList.size();
    }

    /**
     * This method receives an index as parameter and gets a room from the house's room list.
     *
     * @param index the Index of the room
     * @return returns room that corresponds to index.
     */
    public Room getRoomByIndex(int index) {
        if (this.roomList.isEmpty()) {
            throw new IndexOutOfBoundsException("The room list is empty.");
        }
        return this.roomList.get(index);
    }


    /**
     * This method checks the house's device type list size.
     *
     * @return returns the house's device type list size as int.
     */
    public int deviceTypeListSize() {
        return this.deviceTypeList.size();
    }

    /**
     * This method receives an index as parameter and gets energy grid from house's energy grid list.
     *
     * @param index the index of the Energy Grid
     * @return returns Energy grid that corresponds to index.
     */
    public EnergyGrid getEnergyGridByIndex(int index) {
        if (this.energyGridList.isEmpty()) {
            throw new IndexOutOfBoundsException("The energy grid list is empty.");
        }
        return this.energyGridList.get(index);
    }

    /**
     * This method gets every device in house. To do this, the method
     * goes through every room in house and gets every device in room.
     *
     * @return DeviceList with every device in house.
     **/
    public DeviceList getDeviceList() {
        return this.roomList.getDeviceList();
    }

    /**
     * This method call EnergyGridList method which creates an EnergyGrid.
     * @param designation - EnergyGrid designation
     * @param maxPower - EnergyGrid Maximum Power
     * @return a new EnergyGrid or the EnergyGrid with the same designation
     */
    public EnergyGrid createEnergyGrid(String designation, double maxPower) {
        return this.energyGridList.createEnergyGrid(designation, maxPower);
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