package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class HouseConfigurationController {
    /**
     * SHARED METHODS**/

    public List<Integer> matchTypeAreaIndexByString(String input, TypeAreaList typeAreaList) {
        return typeAreaList.matchGeographicAreaTypeIndexByString(input);
    }

    public String printTypeAreaElementsByIndex(List<Integer> listOfIndexesTypeGeographicAreas, TypeAreaList typeAreaList) {
        return typeAreaList.printGATypeElementsByIndex(listOfIndexesTypeGeographicAreas);
    }

    public String printTypeArea(TypeArea typeArea) {
        return typeArea.printTypeGeographicArea();
    }

    public String printGATypeList(TypeAreaList typeAreaList) {
        return typeAreaList.printGATypeWholeList(typeAreaList);
    }



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
     * User Story 02
     * <p>
     * As a System Administrator I want to receive a list of all the previously stated Types of area.
     */

    public String getTypeAreaList() {
        return mTypeAreaList.printTypeAreaList();
    }

    /**
     * User Story 3
     * As a System Administrator I want to Create a new Geographic Area
     * <p>
     * US 03 controller class. Provides methods to create a new GA and add it to a given list.
     */

    /**
     * Method to add a new geographic area to a list of geographic areas
     *
     * @param newGeoList geographic area list to add the new geographic area
     * @param newName    input string for geographic area name
     * @param typeArea   input string for type area
     * @param latitude   input number for latitude
     * @param longitude  input number for longitude
     * @return success if a new GA is added, false otherwise
     */
    public boolean addNewGeoAreaToList(GeographicAreaList newGeoList, String newName, String typeArea, double latitude, double longitude) {
        if (newGeoList == null) {
            return false;
        }
        GeographicArea geoToAdd = new GeographicArea(newName, new TypeArea(typeArea), new Local(latitude, longitude));
        return newGeoList.addGeographicAreaToGeographicAreaList(geoToAdd);
    }

    /**
     * User Story 04
     */

    private GeographicAreaList mGeographicAreaList;

    /**
     * Void Method to Set/Match a Geographic Area List with a Certain Type Area Given(String input).
     */
    public void matchGeoAreaTypeWithInput(String input) {
        mGeographicAreaList = this.mGeographicAreaList.matchGeographicAreaWithInputType(input);
    }

    /**
     * Geographic Area List getter.
     */
    public GeographicAreaList getGeographicAreaList() {
        return this.mGeographicAreaList;
    }


    /**
     * User Story 06
     * As a system administrator, I want to be able to manually input a new sensor and add it to a pre-input Geographic Area
     */

    private Local mLocal;
    private Date mDate;
    private TypeSensor mType;
    private Sensor mSensor;



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
     * US07
     */

    private GeographicArea mMotherArea;

    public GeographicArea matchGeoArea(String nameArea){
        return mGeographicAreaList.matchGeoArea(nameArea);
    }

    public void setMotherArea(GeographicArea daughterArea, GeographicArea motherArea){
        daughterArea.setMotherArea(motherArea);
        this.mMotherArea = motherArea;
    }

    GeographicArea getMotherArea (){
        return this.mMotherArea;

    }

    public String printGeographicAreaListNames() {
        return mGeographicAreaList.printGeoAreaList();
    }

    public boolean validateGeoArea(String ga) {

        return mGeographicAreaList.validateIfGeographicAreaToGeographicAreaList(ga);
    }

    /**
     * US08
     */

    private GeographicArea mGeographicAreaContained;
    private GeographicArea mGeographicAreaContainer;

    GeographicArea getmGeographicAreaContained() {
        return this.mGeographicAreaContained;
    }

    GeographicArea getmGeographicAreaContainer() {
        return this.mGeographicAreaContainer;
    }

    /**
     * This method define the GeographicAreas Container and Contained
     */

    public boolean matchGeographicAreas(String nameOfAreaContained, String nameOfAreaContainer) {
        if (mGeographicAreaList.checkIfListIsValid()) {
            for (GeographicArea ga : mGeographicAreaList.getGeographicAreaList()) {
                if (ga.getName().equals(nameOfAreaContained)) {
                    mGeographicAreaContained = ga;
                }
                if (ga.getName().equals(nameOfAreaContainer)) {
                    mGeographicAreaContainer = ga;
                }
            }
            return mGeographicAreaContained != null && mGeographicAreaContainer != null;
        }
        return false;
    }

    /**
     * This methods checks if one area (AreaContained) is contained in another area (AreaContainer)
     */
    public boolean seeIfItsContained() {
        return mGeographicAreaContained.checkIfAreaIsContained(mGeographicAreaContained, mGeographicAreaContainer);
    }

    /**
     * US101: As an As an Administrator, I want to in order to configure a house (zipcode, local and address).
     *
     */

    public HouseConfigurationController() {
    }


    public HouseConfigurationController(GeographicAreaList geoList) {
        this.mGeographicAreaList = geoList;
    }

    public GeographicAreaList getGeoList() {
        return mGeographicAreaList;
    }

    public String printGAList(GeographicAreaList geoAreaList) {
        return geoAreaList.printGaWholeList(geoAreaList);
    }

    public void setHouseList(HouseList houseList){this.mHouseList = houseList;}


    public void setHouseAddress(String address, House house) {
        house.setmAddress(address);
    }

    public List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList) {
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    public void setHouseZIPCode(String zipCode, House house) {
        house.setmZipCode(zipCode);
    }

    public void setHouseLocal(double latitude, double longitude, House house) {
        house.setGPS(latitude,longitude);
    }

    public String printGeoGraphicAreaElementsByIndex(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas);
    }

    public String printGA(GeographicArea geoArea) {
        return geoArea.printGeographicArea();
    }

    public String printHouseList(GeographicArea ga) {
       return ga.getHouseList().printHouseList(ga);
    }

    /**
     *   US105: As an Administrator, I want to add a new room to the house, in order to configure
     it (name, house floor and dimensions).
     */

    private HouseList mHouseList;
    private Room mRoom;


    public void createNewRoom(String roomDesignation, int roomHouseFloor, double roomDimensions) {
        this.mRoom = new Room(roomDesignation, roomHouseFloor ,roomDimensions);
    }


    public boolean addRoomToHouse(House house) {
        return (house.addRoomToRoomList(this.mRoom));}

    public String getHouseName(House house) {
        return house.getHouseDesignation();
    }

    /**
     US108
     **/


    public List<Integer> matchHouseIndexByString(String input, GeographicArea geoArea){
        return geoArea.getHouseList().matchHouseIndexByString(input);
    }

    public String printHouseElementsByIndex(List<Integer> listOfIndexesOfHouses, GeographicArea geoArea) {
        return geoArea.getHouseList().printElementsByIndex(listOfIndexesOfHouses);
    }

    public String printHouse (House house){
        return house.printHouse();
    }

    public List<Integer> matchRoomIndexByString(String input, House house){
        return house.getmRoomList().matchRoomIndexByString(input);
    }

    public String printRoomElementsByIndex(List<Integer> listOfIndexesOfRoom, House house) {
        return house.getmRoomList().printElementsByIndex(listOfIndexesOfRoom);
    }

    public String printRoomList(House house) {
        return house.getmRoomList().printRoomList(house);
    }

    public String printRoom (Room room){
        return room.printRoom();
    }

    public void editRoom(Room room, String roomName, int roomFloor, double roomDimensions) {
        room.setRoomName(roomName);
        room.setRoomHouseFloor(roomFloor);
        room.setRoomDimensions(roomDimensions);
    }


    /** US130
     * As an Administrator, I want to create a energy grid,
     * so that I can define the rooms that are attached to it and the contracted maximum power for that grid.
     **/

    private EnergyGrid mEnergyGrid;
    private String mHouseName;

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
     * so that the produced energy may be used by all devices on that grid.
     **/

    private PowerSource mPowerSource;
    private EnergyGridList mEnergyGridList;

    public void createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage) {
        this.mPowerSource = new PowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    PowerSource getPowerSource() {
        return this.mPowerSource;
    }

    public String seeIfEnergyGridListIsEmptyAndShowItsContent() {
        return this.mEnergyGridList.printEnergyGridList();
    }

    public boolean selectEnergyGrid(String name) {
        this.mEnergyGrid = mEnergyGridList.matchEnergyGrid(name);
        return this.mEnergyGrid != null;
    }

    public boolean addPowerSourceToEnergyGrid() {
        return this.mEnergyGrid.addPowerSource(mPowerSource);
    }

    /**
     * US145
     *
     */

    public HouseConfigurationController (Room room){
        this.mRoom = room;
    }

    public List<Integer> matchGridIndexByString(String gridName, House house){
        return house.getmEGList().matchGridListElementsByString(gridName);
    }

    public String printEnergyGridByIndex (List<Integer> list){
        return this.mEnergyGridList.printElementsByIndex(list);
    }

    public String printEnergyGrid(EnergyGrid grid){
        return grid.printGrid();
    }

    public String printGridList(House house){
        return house.printWholeGridList();
    }

    public String printRooms (RoomList roomList){
        return roomList.printRooms();
    }

    public boolean removeRoomFromGrid(EnergyGrid grid, Room room) {
        return grid.removeRoom(room);
    }
}
