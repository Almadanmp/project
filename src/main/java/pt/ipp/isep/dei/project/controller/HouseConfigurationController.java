package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

public class HouseConfigurationController {

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
    
}
