package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * House Class. Defines de House
 */
@Entity
public class House {

    @Id
    private String id;
    @Embedded
    private Address address;
    @Embedded
    private Local location;
    @OneToOne(cascade = CascadeType.ALL)
    private GeographicArea motherArea;
    private int gridMeteringPeriod;
    private int deviceMeteringPeriod;
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
        this.gridMeteringPeriod = gridMeteringPeriod;
        this.deviceMeteringPeriod = deviceMeteringPeriod;
        this.location = local;
        buildDeviceTypeList(deviceTypeConfig);
    }


    public House(String id, Address address, Local local, int gridMeteringPeriod, int deviceMeteringPeriod, List<String> deviceTypeConfig) {
        this.id = id;
        this.address = address;
        this.gridMeteringPeriod = gridMeteringPeriod;
        this.deviceMeteringPeriod = deviceMeteringPeriod;
        this.location = local;
        buildDeviceTypeList(deviceTypeConfig);
    }

    public House() {
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
     * Standard getter method, to return the list of device types that can be created in the house.
     *
     * @return the list of Device Types associated to the House.
     */
    public List<DeviceType> getDeviceTypeList() {
        return deviceTypeList;
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
     * This method checks the house's device type list size.
     *
     * @return returns the house's device type list size as int.
     */
    public int deviceTypeListSize() {
        return this.deviceTypeList.size();
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