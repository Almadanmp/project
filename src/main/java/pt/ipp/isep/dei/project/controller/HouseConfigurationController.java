package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class HouseConfigurationController {

    /**
     * User Story 01
     * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
     */

    private TypeAreaList mTypeAreaList;

    /**
     * The Builder recieves a List Of Types Of Geographic Areas and sets it.
     */
    public HouseConfigurationController(TypeAreaList list) {
        this.mTypeAreaList = list;
    }

    /**
     * This method creates a new Type of Geographic Area and adds it to a List.
     *
     * @param input - the String name of the Type of Geographic Area.
     * @return true - the Type of Geographic Area was successfully created and added to a list;
     * false - the name is null.
     */
    public boolean createAndAddTypeAreaToList(String input) {
        return mTypeAreaList.newTAG(input);
    }

    /**
     * User Story 05
     * As a system administrator, I wish to specify the type of reading that a sensor is capable
     * of registering.
     */
    private SensorList mSensorList;

    public HouseConfigurationController(SensorList list) {
        this.mSensorList = list;
    }

    public boolean setTypeSensor(String name, String typeToSet) {
        return mSensorList.setTypeSensorByString(name,typeToSet);
    }

    public SensorList getSensorList() {
        return this.mSensorList;
    }

    /**
     * User Story 06
     * As a system administrator, I want to be able to manually input a new sensor and add it to a pre-input Geographic Area
     */

    private Local mLocal;
    private Date mDate;
    private TypeSensor mType;
    private Sensor mSensor;

    public HouseConfigurationController(){}

    /**
     * Method to create a Local with given doubles
     * Calls the original method from model
     * @param latitude Latitude
     * @param latitude Longitude
     * @param latitude Altitude
     * @return Local created
     */
    public Local createLocal(Double latitude, Double longitude, Double altitude) {
        Local local = new Local(latitude, longitude, altitude);
        this.mLocal = local;
        return this.mLocal;
    }
    public Date createDate(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        this.mDate = date;
        return this.mDate;
    }
    public TypeSensor createType(String sensorType){
        this.mType = new TypeSensor(sensorType);
        return this.mType;
    }
    public Sensor createSensor (String name, TypeSensor type, Local local, Date date){
        this.mSensor = new Sensor(name,type,local,date);
        return mSensor;
    }
    /**
     * Method to add a Sensor to a SensorList
     * Calls the original method from model
     */
    public boolean addSensor(Sensor sensor, SensorList sensorList){
        if(sensorList.containsSensor(sensor)) {
            sensorList.getSensorList().add(sensor);
            return false;
        }
        return true;
    }
    /**
     * Method to check if a list is either composed by null values or is empty
     *
     */
    private boolean checkIfListValid(List<GeographicArea> values) {
        return values != null && !values.isEmpty();
    }
    /**
     * Method to add a SensorList to a GeographicArea given that both the name of the Geographis Area and the name given
     * as parameter match
     * Calls the original method from model
     */

    public boolean addSensorToGeographicArea(String name, GeographicAreaList gaList, SensorList sensorList) {
        if (checkIfListValid(gaList.getGeographicAreaList())) {
            for (GeographicArea ga : gaList.getGeographicAreaList())
                if ((ga.getName().equals(name))) {
                    ga.setSensorList(sensorList);
                    return true;
                }
        }
        return false;
    }

    public Local getLocal() {
        return this.mLocal;
    }
    public TypeSensor getType() {
        return this.mType;
    }
    public Date getDate() {
        return this.mDate;
    }
    public Sensor getSensor() {
        return this.mSensor;
    }



    /**
     * US101
     */

    private GeographicAreaList mGeoList;


    public HouseConfigurationController(GeographicAreaList geoList) {
        this.mGeoList = geoList;
    }


    public void printGAList(GeographicAreaList geoAreaList) {
        geoAreaList.printGaWholeList(geoAreaList);
    }


    public void printHouseList() {
        mHouseList.printHouseList();
    }

    public void setHouseAddress(String address, int indexOfHouse) {
        mHouseList.getHouseList().get(indexOfHouse).setmAddress(address);
    }

    public List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList) {
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    public void setHouseZIPCode(String zipCode, int indexOfHouse) {
        mHouseList.getHouseList().get(indexOfHouse).setmZipCode(zipCode);
    }

    public void setHouseLocal(double latitude, double longitude, int indexOfHouse) {
        mHouseList.getHouseList().get(indexOfHouse).getmGPS().setLatitude(latitude);
        mHouseList.getHouseList().get(indexOfHouse).getmGPS().setLongitude(longitude);
    }

    public String printGeoGraphicAreaElementsByIndex(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas);
    }

    public String printGA(GeographicArea geoArea) {
        return geoArea.printGeographicArea();
    }

    public void printHouseList(GeographicArea ga) {
        ga.getHouseList().printHouseList(ga);
    }


    private EnergyGrid mEnergyGrid;
    private HouseList mHouseList;
    private String mHouseName;

    /** US130
     * As an Administrator, I want to create a energy grid,
     * so that I can define the rooms that are attached to it and the contracted maximum power for that grid.
     **/

    /**
     * The controller is initialized with the houseList given from the UI, which came from MainUI;
     */
    public HouseConfigurationController(HouseList houseList) {
        this.mHouseList = houseList;
    }

    /**
     * This method checks the house list which came from MainUI through UI for the given house name;
     */
    public boolean seeIfHouseExistsInHouseList(String houseName) {
        if (mHouseList.checkIfHouseListContainsHouseWithGivenDesignation(houseName)) {
            this.mHouseName = houseName;
            this.mEnergyGridList = mHouseList.getHouseByDesignation(houseName).getmEGList();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method directly adds the desired energy grid to the energy grid list from a selected house;
     */
    public boolean addEnergyGridToHouse() {
        return mHouseList.getHouseByDesignation(mHouseName).getmEGList().addEnergyGridToEnergyGridList(mEnergyGrid);
    }

    /**
     * This method creates an energy grid using a name and a max potency.
     */
    public void createEnergyGrid(String designation, double maxPower) {
        this.mEnergyGrid = new EnergyGrid(designation, maxPower);
    }

    /**
     * Method used only to test the createEnergyGrid method.
     */
    EnergyGrid getEnergyGrid() {
        return this.mEnergyGrid;
    }

    /**
     * US135
     * As an Administrator, I want to add a power source to an energy grid,
     * so that the produced energy may be used by all devices on that grid. **/

    private PowerSource mPowerSource;
    private EnergyGridList mEnergyGridList;

    public void createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){this.mPowerSource = new PowerSource(name, maxPowerOutput, maxEnergyStorage);}

    PowerSource getPowerSource(){return this.mPowerSource;}

    public String seeIfEnergyGridListIsEmptyAndShowItsContent(){
        return this.mEnergyGridList.printEnergyGridList();
    }

    public boolean selectEnergyGrid(String name){
        this.mEnergyGrid = mEnergyGridList.matchEnergyGrid(name);
        return this.mEnergyGrid != null;
    }

    public boolean addPowerSourceToEnergyGrid(){
        return this.mEnergyGrid.addPowerSource(mPowerSource);
    }
    
}
