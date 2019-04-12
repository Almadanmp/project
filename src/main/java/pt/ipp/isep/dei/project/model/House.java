package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import javax.persistence.*;
import java.util.*;

/**
 * House Class. Defines de House
 */
@Entity
public class House implements Metered {

    @Id
    private String id;

    @Embedded
    private Address address;

    @Embedded
    private Local location;

    @Transient
    private int deviceMeteringPeriod;

    @Transient
    EnergyGridService energyGridService;

    @Transient
    private RoomService roomService;

    @OneToOne(cascade = CascadeType.ALL)
    private GeographicArea motherArea;

    private int gridMeteringPeriod;


    @Transient
    private List<DeviceType> deviceTypeList;

    /**
     * Standard constructor for a house object.
     *
     * @param id                   is the id of the house.
     * @param local                is the location of the central point of the house, in latitude, longitude and altitude coordinates.
     * @param gridMeteringPeriod   is the metering period of grids contained in the house.
     * @param deviceMeteringPeriod is the metering period of devices contained in the house.
     * @param deviceTypeConfig     is the list of possible device types that the house supports.
     */

    public House(String id, Local local, int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        this.id = id;
        this.roomService = new RoomService();
        this.energyGridService = new EnergyGridService();
        this.gridMeteringPeriod = gridMeteringPeriod;
        this.deviceMeteringPeriod = deviceMeteringPeriod;
        this.location = local;
        buildDeviceTypeList(deviceTypeConfig);
    }


    public House(String id, Address address, Local local, int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        this.id = id;
        this.address = address;
        this.roomService = new RoomService();
        this.energyGridService = new EnergyGridService();
        this.gridMeteringPeriod = gridMeteringPeriod;
        this.deviceMeteringPeriod = deviceMeteringPeriod;
        this.location = local;
        buildDeviceTypeList(deviceTypeConfig);
    }

    public House() {
        this.roomService = new RoomService();
        this.energyGridService = new EnergyGridService();
    }

    public House(RoomService roomService, EnergyGridService energyGridService) {  //FOR TESTING PURPOSES USING MOCKITO
        this.roomService = roomService;
        this.energyGridService = energyGridService;
    }


    //SETTERS AND GETTERS


    public void setDeviceTypeList(List<String> deviceTypeConfig) {
        buildDeviceTypeList(deviceTypeConfig);
    }


    /**
     * Standard getter method, to return the Address of the House.
     *
     * @return the string with the Address of the House.
     */
    public Address getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Standard getter method, to return the total nominal power of the devices in the House.
     *
     * @return the string with the nominal power of all the devices active in the House.
     */
    public double getNominalPower() {
        return this.roomService.getNominalPower();
    }

    /**
     * Standard setter method, to define the metering period of the energy grid.
     *
     * @param meteringPeriod is the period of time where the energy of the energy grid is calculated.
     */
    public void setGridMeteringPeriod(int meteringPeriod) {
        this.gridMeteringPeriod = meteringPeriod;
    }

    /**
     * Standard getter method, to return the period of time where the total energy consumption of all the energy grids
     * of the house.
     *
     * @return the value of the period of time where the energy consumption will be calculated.
     */
    public int getGridMeteringPeriod() {
        return gridMeteringPeriod;
    }

    /**
     * Standard setter method, to define the metering period of the devices.
     *
     * @param meteringPeriod is the period of time where the energy of the devices is calculated.
     */
    public void setDeviceMeteringPeriod(int meteringPeriod) {
        this.deviceMeteringPeriod = meteringPeriod;
    }

    /**
     * Standard getter method, to return the period of time where the energy consumed by all the devices present in
     * the house will be calculated.
     *
     * @return the value of the period of time where the energy consumption will be calculated.
     */
    public int getDeviceMeteringPeriod() {
        return deviceMeteringPeriod;
    }

    /**
     * Standard getter method, to return the location where the House is located in.
     *
     * @return the Local of the House.
     */
    public Local getLocation() {
        return location;
    }

    /**
     * Standard setter method, to define the location parameters of the House.
     *
     * @param latitude  is the latitude of the location.
     * @param longitude is the longitude of the location.
     * @param altitude  is the altitude of the location.
     */
    public void setLocation(double latitude, double longitude, double altitude) {
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setAltitude(altitude);
    }

    /**
     * Standard setter method, to define the Address of the House.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Standard setter method, to define the list of rooms to be added to the House.
     *
     * @param roomService is the room list to be set.
     */
    public void setRoomService(RoomService roomService) {
        if (roomService != null) {
            this.roomService = roomService;
        }
    }

    /**
     * Standard getter method, to return value of the Altitude of the location of the house
     *
     * @return the double value of the altitude.
     */
    public double getAltitude() {
        return this.location.getAltitude();
    }

    /**
     * Standard setter method, to define Geographical Area in which the House is contained.
     *
     * @param motherArea is the Geographical Area to be set.
     */
    public void setMotherArea(GeographicArea motherArea) {
        this.motherArea = motherArea;
    }

    /**
     * Standard getter method, to return Geographical Area where the House is located.
     *
     * @return the Geographical Area of the House.
     */
    public GeographicArea getMotherArea() {
        return motherArea;
    }

    /**
     * Standard getter method, to return the list of rooms present on the House.
     *
     * @return the RoomList associated to the House.
     */
    public RoomService getRoomService() {
        return this.roomService;
    }

    /**
     * Standard getter method, to return the list of energy grids present on the House.
     *
     * @return the EnergyGridList associated to the House.
     */
    public EnergyGridService getGridList() {
        return this.energyGridService;
    }


    /**
     * Standard setter method, to define the list of energy grids to be added to the House.
     *
     * @param energyGridService is the Energy grid list to be set.
     */
    public void setGridList(EnergyGridService energyGridService) {
        this.energyGridService = energyGridService;
    }

    /**
     * Standard getter method, to return the list of device types that can be created in the house.
     *
     * @return the list of Device Types associated to the House.
     */
    public List<DeviceType> getDeviceTypeList() {
        return deviceTypeList;
    }

    /**
     * Standard setter method, to add a Room to the House.
     *
     * @param room the Room to be added to the House.
     */
    public boolean addRoom(Room room) {
        return this.roomService.add(room);
    }

    /**
     * Checks if the attribute motherArea is null
     *
     * @return true or false
     */
    public boolean isMotherAreaNull() {
        return getMotherArea() == null;
    }

    /**
     * calculates distance from the house to the sensor.
     *
     * @param areaSensor sensor from where to calculate the distance
     * @return returns the distance between sensor and the house
     */
    public double calculateDistanceToSensor(AreaSensor areaSensor) {
        Local l = areaSensor.getLocal();
        return this.location.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Searches within the list of sensors of a given type in a given geographic area for the distance to
     * the closest sensor the house.
     *
     * @param type is the type we want to search for.
     * @return is the value of the distance of the house to sensor of the given type closest to it.
     */

    double getMinDistanceToSensorOfGivenType(AreaSensorService type) {
        List<Double> arrayList = type.getSensorsDistanceToHouse(this);
        return Collections.min(arrayList);
    }

    /**
     * This method returns the sensor closest to the house. If more than one sensor is close to it,
     * the one with the most recent reading should be used.
     *
     * @param sensorType the type of sensor to check
     * @return the closest sensor.
     */
    public AreaSensor getClosestSensorOfGivenType(String sensorType) {
        AreaSensor areaSensor;
        AreaSensorService minDistSensor = new AreaSensorService();
        AreaSensor areaSensorError = new AreaSensor("RF12345", "EmptyList", new SensorType("temperature", " " +
                ""), new Local(0, 0, 0), new GregorianCalendar(1900, Calendar.FEBRUARY,
                1).getTime(), 2356L);
        AreaSensorService sensorsType = this.motherArea.getSensorsOfGivenType(sensorType);
        if (!sensorsType.isEmpty()) {
            double minDist = this.getMinDistanceToSensorOfGivenType(sensorsType);
            minDistSensor = sensorsType.getSensorsByDistanceToHouse(this, minDist);
        }
        if (minDistSensor.isEmpty()) {
            return areaSensorError;
        }
        if (minDistSensor.size() > 1) {
            areaSensor = minDistSensor.getMostRecentlyUsedSensor();
        } else {
            areaSensor = minDistSensor.get(0);
        }
        return areaSensor;
    }


    /**
     * Method that will instantiate an object from each device Type path in device.properties file
     * and addWithoutPersisting it to the List<DeviceType> attribute in House class.
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


    /**
     * @return builds a string from the House's room list.
     */
    public String buildRoomListString() {
        return this.roomService.buildString();
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
            result.append(i).append(") DeviceType: ").append(deviceTypeList.get(i).getDeviceType()).append("\n");

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
        return roomService.getDailyConsumptionByDeviceType(deviceType, time);
    }

    public boolean addGrid(EnergyGrid energyGrid) {
        return this.energyGridService.addGrid(energyGrid);
    }

    /**
     * Method to get the energy consumption of the house
     *
     * @param time the consumption will be calculated for the input time
     * @return energy consumption on all house devices on a given time.
     */
    public double getEnergyConsumption(float time) {
        DeviceList deviceList = getDeviceList();
        double result = 0.0;
        for (int i = 0; i < deviceList.size(); i++) {
            result += deviceList.get(i).getEnergyConsumption(time);
        }
        return result;
    }

    /**
     * This method checks if house's RoomList is empty.
     *
     * @return true if house's RoomList is empty, false otherwise
     **/
    public boolean isRoomListEmpty() {
        return this.roomService.isEmptyDB();
    }


    /**
     * This method checks if house's list of Energy Grids is empty.
     *
     * @return true if house's EnergyGridList is empty, false otherwise.
     **/
    public boolean isEnergyGridListEmpty() {
        return this.energyGridService.isEmpty();
    }


    /**
     * This method checks the house's room list size.
     *
     * @return returns the house's room list size as int.
     */
    public int roomListSize() {
        return this.roomService.sizeDB();
    }

    /**
     * This method receives an index as parameter and gets a room from the house's room list.
     *
     * @param index the Index of the room
     * @return returns room that corresponds to index.
     */
    public Room getRoomByIndex(int index) { //METHOD CHECKS IF DATABASE IS EMPTY BUT THEN RETURNS A ROOM FROM LOCAL LIST (NOW CALLED SERVICE). FIX!
        if (this.roomService.isEmptyDB()) {
            throw new IndexOutOfBoundsException("The room list is empty.");
        }
        return this.roomService.get(index);
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
     * This method gets every device in house. To do this, the method
     * goes through every room in house and gets every device in room.
     *
     * @return DeviceList with every device in house.
     **/
    public DeviceList getDeviceList() {
        return this.roomService.getDeviceList();
    }


    /**
     * Method to check if an instance of this class is equal to another object.
     *
     * @param o is the object we want to check for equality.
     * @return is true if the object is a house with the same address.
     */
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