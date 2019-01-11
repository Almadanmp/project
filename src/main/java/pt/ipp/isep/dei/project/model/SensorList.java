package pt.ipp.isep.dei.project.model;

import java.util.*;

/**
 * Represents a List of Sensors.
 */
public class SensorList {

    private List<Sensor> mSensorList;
    private String mStringEnhancer = "---------------\n";

    public SensorList() {
        this.mSensorList = new ArrayList<>();
    }

    /**
     * Constructor of an ArrayList of Arrays.
     *
     * @param sensorsToAdd
     */


    public SensorList(Sensor[] sensorsToAdd) {
        mSensorList = new ArrayList<>();
        for (int i = 0; i < sensorsToAdd.length; i++) {
            mSensorList.add(sensorsToAdd[i]);
        }
    }

    /**
     * Constructor to always create an Array of Sensors.
     *
     * @param sensorToAdd
     */
    public SensorList(Sensor sensorToAdd) {
        mSensorList = new ArrayList<>();
        mSensorList.add(sensorToAdd);
    }

    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param sensorToAdd
     * @return
     */

    public boolean addSensor(Sensor sensorToAdd) {
        if (!(mSensorList.contains(sensorToAdd))) {
            mSensorList.add(sensorToAdd);
        }
        return false;
    }

    /**
     * Checks if a Sensor is inside the Sensor List
     *
     * @param sensor
     * @return
     */
    public boolean containsSensor(Sensor sensor) {
        return mSensorList.contains(sensor);
    }

    /**
     * Getter (array of sensors)
     *
     * @return array of sensors
     */
    Sensor[] getSensors() {
        int sizeOfResultArray = mSensorList.size();
        Sensor[] result = new Sensor[sizeOfResultArray];
        for (int i = 0; i < mSensorList.size(); i++) {
            result[i] = mSensorList.get(i);
        }
        return result;
    }

    /**
     * Gettter (list of sensors)
     *
     * @return list of sensors
     */
    public List<Sensor> getSensorList() {
        return this.mSensorList;
    }

    /**
     * Removes a sensor from the Sensor List
     *
     * @param sensorToRemove
     */
    void removeSensor(Sensor sensorToRemove) {
        mSensorList.remove(sensorToRemove);
    }

    /**
     * Method to find the most recently used sensor in the sensor list
     *
     * @return the most recently used sensor
     */
    Sensor getMostRecentlyUsedSensor() {
        List<Sensor> listToTest = this.mSensorList;
        int indexMostRecentlyUsedSensor = 0;
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Date firstDate = listToTest.get(i).getReadingList().getMostRecentReading().getmDate();
            Date secondDate = listToTest.get(i + 1).getReadingList().getMostRecentReading().getmDate();
            if (firstDate.before(secondDate)) {
                indexMostRecentlyUsedSensor = i + 1;
            }
        }
        return listToTest.get(indexMostRecentlyUsedSensor);
    }

    /**
     * @param name name of the sensor to find in the list.
     * @return return the sensor whose name matches the name introduced.
     */
    Sensor getSensorByName(String name) {
        for (Sensor s : mSensorList) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public boolean doesSensorListContainSensorByName(String name) {
        for (Sensor s : mSensorList) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    Sensor getSensorByType(String name) {
        for (Sensor s : mSensorList) {
            if (s.getTypeSensor().getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
    public List<Integer> matchSensorIndexByString(String input){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < mSensorList.size(); i++){
            if (mSensorList.get(i).getName().equals(input)){
                result.add(i);
            }
        }
        return result;
    }

    public String printElementsByIndex (List<Integer> indexes){
        StringBuilder result = new StringBuilder();
        for (int pos : indexes) {
            result.append(pos).append(") ").append(mSensorList.get(pos).getName()).append(" which is a ").append(mSensorList.get(pos).getTypeSensor().getName()).append(" sensor.\n");
        }
        return result.toString();
    }

    public String printSensorList(Room room) {
        StringBuilder result = new StringBuilder(mStringEnhancer);
        if (room.getmRoomSensorList().getSensorList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < room.getmRoomSensorList().getSensorList().size(); i++) {
            Sensor aux = room.getmRoomSensorList().getSensorList().get(i);
            result.append(i).append(") Designation: ").append(aux.getName()).append(" | ");
            result.append("Sensor Type: ").append(aux.getTypeSensor().getName()).append("\n");
        }
        result.append(mStringEnhancer);
        System.out.print(result);
        return result.toString();
    }

    public List<Sensor> getSensorListByType(String name) {
        List<Sensor> containedTypeSensors = new ArrayList<>();
        for (Sensor sensor : this.mSensorList) {
            if (name.equals(sensor.getTypeSensor().getName()) && (!(containedTypeSensors.contains(sensor)))) {
                containedTypeSensors.add(sensor);
            }
        }
        return containedTypeSensors;
    }

    List<Sensor> getListOfSensorsContainedInGeographicArea(GeographicArea area, TypeSensor type) {
        List<Sensor> containedSensors = new ArrayList<>();
        for (Sensor sensor : mSensorList) {
            if (sensor.isSensorContainedInArea(area) && sensor.getTypeSensor().equals(type)) {
                containedSensors.add(sensor);
            }
        }
        return containedSensors;
    }

    List<Sensor> getSensorsInGAAtACertainTimePeriod(GregorianCalendar date1, GeographicArea ga) {
        List<Sensor> finalList = new ArrayList<>();
        for (Sensor s : mSensorList) {
            if (s.isSensorActiveOnGivenDate(date1) && s.isSensorContainedInArea(ga)) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    public boolean setTypeSensorByString(String nameOfSensor, String typeToSet) {
        if (!checkIfListInvalid()) {
            for (Sensor sensor : mSensorList)
                if (sensor.getName().equals(nameOfSensor)) {
                    sensor.getTypeSensor().setName(typeToSet);
                    return true;
                }
        }
        return false;
    }

    /**
     * Method to Match a Sensor By Name,
     * @return a list of Sensors with the input name.
     */
    public List<Integer> matchSensorListIndexByString(String input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < mSensorList.size(); i++) {
            if (mSensorList.get(i).getName().equals(input)) {
                result.add(i);
            }
        }
        return result;
    }
    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     */
    public String printSensorWholeList(SensorList sensorList) {
        StringBuilder result = new StringBuilder(new StringBuilder(mStringEnhancer));

        if (sensorList.getSensorList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (int i = 0; i < sensorList.getSensorList().size(); i++) {
            Sensor aux = sensorList.getSensorList().get(i);
            result.append(i).append(") Name: ").append(aux.getName()).append(" | ");
            result.append("Type: ").append(aux.getTypeSensor().getName()).append("\n");
        }
        result.append(mStringEnhancer);
        return result.toString();
    }

    private boolean checkIfListInvalid() {
        return (this.mSensorList.isEmpty());
    }

    boolean checkIfListIsValid() {
        return !mSensorList.isEmpty();
    }

    /**
     * Specific Methods.
     *
     * @param testObject
     * @return
     */

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof SensorList)) {
            return false;
        }
        SensorList list = (SensorList) testObject;
        return Arrays.equals(this.getSensors(), list.getSensors());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
