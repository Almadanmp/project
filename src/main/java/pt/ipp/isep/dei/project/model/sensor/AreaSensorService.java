package pt.ipp.isep.dei.project.model.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.*;

/**
 * Class that groups a number of Sensors.
 */
@Service
public class AreaSensorService {

    @Autowired
    AreaSensorRepository areaSensorRepository;

    @Autowired
    SensorTypeRepository sensorTypeRepository;

    /**
     * AreaSensorList() empty constructor that initializes an ArrayList of Sensors.
     */
    public AreaSensorService() {
    }

    public AreaSensorService(AreaSensorRepository areaSensorRepository, SensorTypeRepository sensorTypeRepository) {
        this.areaSensorRepository = areaSensorRepository;
        this.sensorTypeRepository = sensorTypeRepository;
    }

    /**
     * Method that goes through the sensor list and returns a list of those which
     * have readings. The method throws an exception in case the sensorList is empty.
     *
     * @return AreaSensorList of every sensor that has readings. It will return an empty list in
     * case the original list was empty from readings.
     */
    List<AreaSensor> getSensorsWithReadings(List<AreaSensor> areaSensors, ReadingService readingService) {
        List<AreaSensor> finalList = new ArrayList<>();
        if (areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty");
        }
        for (AreaSensor s : areaSensors) {
            List<Reading> sensorReadings = readingService.findReadingsBySensorID(s.getId());

            if (!sensorReadings.isEmpty()) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    public AreaSensor updateSensor(AreaSensor areaSensor) {
        return areaSensorRepository.save(areaSensor);
    }

    public List<AreaSensor> findByGeoAreaSensorsByID(Long geoAreaId) {
        return areaSensorRepository.findByGeographicAreaId(geoAreaId);
    }

    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param areaSensorToAdd is the sensor we want to addWithoutPersisting to the sensorList.
     * @return true if sensor was successfully added to the AreaSensorList, false otherwise.
     */

    public boolean addWithPersist(AreaSensor areaSensorToAdd) {
        AreaSensor areaSensor = areaSensorRepository.findByName(areaSensorToAdd.getName());
        if (areaSensor == null) {
            areaSensorRepository.save(areaSensorToAdd);
            return true;
        }
        return false;
    }


    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     *
     * @return a string of the sensors contained in the list.
     */

    public String buildString(List<AreaSensor> areaSensors) {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));

        if (areaSensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (AreaSensor as : areaSensors) {
            result.append(as.getId()).append(") Name: ").append(as.getName()).append(" | ");
            result.append("Type: ").append(as.getSensorTypeName()).append(" | ")
                    .append(as.printActive()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * This method receives a house and the distance of the sensor closest to it,
     * goes through the sensor list and returns the sensors closest to house.
     *
     * @param house   the House of the project
     * @param minDist the distance to the sensor
     * @return AreaSensorList with sensors closest to house.
     **/
    public List<AreaSensor> getSensorsByDistanceToHouse(List<AreaSensor> areaSensors, House house, double minDist) {
        List<AreaSensor> finalList = new ArrayList<>();
        for (AreaSensor s : areaSensors) {
            if (Double.compare(minDist, s.getDistanceToHouse(house)) == 0) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    /**
     * This method receives an index as parameter and gets a sensor from sensor list.
     *
     * @param id the index of the Sensor.
     * @return returns sensor that corresponds to index.
     */
    public AreaSensor getById(String id) {
        Optional<AreaSensor> value = areaSensorRepository.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Sensor with the selected ID.");
    }

    /**
     * This method receives a sensor ID, checks if that sensor exists in the repository.
     *
     * @param sensorID String of sensor ID
     * @return true in case the sensor exists, false otherwise.
     **/
    boolean sensorExistsInRepository(String sensorID) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        return value.isPresent();
    }

    /**
     * This method receives a sensor ID and a Date checks if that sensor exists in the repository
     * and if it was active at the moment of the given date.
     *
     * @param sensorID String of sensor ID
     * @param date     date to test
     * @return true in case the sensor exists and it was active during the given date, false otherwise.
     **/
    boolean sensorFromRepositoryIsActive(String sensorID, Date date) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        if (value.isPresent()) {
            AreaSensor areaSensor = value.get();
            Date startDate = areaSensor.getDateStartedFunctioning();
            if (date.equals(startDate) || date.after(startDate)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean remove(AreaSensor areaSensor) {
        Optional<AreaSensor> areaSensor2 = areaSensorRepository.findById(areaSensor.getId());
        if (areaSensor2.isPresent()) {
            areaSensorRepository.delete(areaSensor);
            return true;
        }
        return false;
    }

    public AreaSensor createSensor(String id, String name, String sensorName, String sensorUnit, Local local, Date dateStartedFunctioning,
                                   Long geographicAreaId) {

        SensorType sensorType = getTypeSensorByName(sensorName, sensorUnit);
        sensorTypeRepository.save(sensorType);

        return new AreaSensor(id, name, sensorType, local, dateStartedFunctioning, geographicAreaId);
    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    public SensorType getTypeSensorByName(String name, String unit) {
        Optional<SensorType> value = sensorTypeRepository.findByName(name);
        if (value.isPresent()) {
            return value.get();
        }
        return new SensorType(name, unit);
    }

    /**
     * This method returns the sensor closest to the house. If more than one sensor is close to it,
     * the one with the most recent reading should be used.
     *
     * @param sensorType the type of sensor to check
     * @return the closest sensor.
     */
    public AreaSensor getClosestSensorOfGivenType(List<AreaSensor> areaSensors, String sensorType, House house, ReadingService readingService) {

        AreaSensor areaSensor;

        List<AreaSensor> minDistSensor = new ArrayList<>();


        AreaSensor areaSensorError = new AreaSensor("RF12345", "EmptyList", new SensorType("temperature", " " +
                ""), new Local(0, 0, 0), new GregorianCalendar(1900, Calendar.FEBRUARY,
                1).getTime(), 2356L);

        List<AreaSensor> sensorsOfGivenType = getSensorsOfGivenType(areaSensors, sensorType);

        if (!sensorsOfGivenType.isEmpty()) {
            double minDist = getMinDistanceToSensorOfGivenType(sensorsOfGivenType, house);

            minDistSensor = getSensorsByDistanceToHouse(sensorsOfGivenType, house, minDist);
        }
        if (minDistSensor.isEmpty()) {
            return areaSensorError;
        }
        if (minDistSensor.size() > 1) {

            areaSensor = getMostRecentlyUsedSensor(minDistSensor, readingService);
        } else {
            areaSensor = minDistSensor.get(0);
        }
        return areaSensor;
    }


    /**
     * Method that goes through the sensor list and looks for the sensor
     * that was most recently used (that as the most recent reading).
     *
     * @return the most recently used sensor
     */
    public AreaSensor getMostRecentlyUsedSensor(List<AreaSensor> areaSensors, ReadingService readingService) {
        if (areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty.");
        }
        List<AreaSensor> areaSensors2 = getSensorsWithReadings(areaSensors, readingService);
        if (areaSensors2.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }

        AreaSensor mostRecent = areaSensors2.get(0);

        Reading recentReading = getMostRecentReading(mostRecent, readingService);
        Date recent = recentReading.getDate();


        for (AreaSensor s : areaSensors) {
            List<Reading> sensorReadings = new ArrayList<>();

            Date test = readingService.getMostRecentReadingDateDb(sensorReadings);
            if (recent.before(test)) {
                recent = test;
                mostRecent = s;
            }
        }
        return mostRecent;
    }


    /**
     * Method that goes through every Reading in the list and
     * returns the reading with the most recent Date.
     *
     * @return most recent reading
     * @author Carina (US600 e US605)
     **/
    Reading getMostRecentReading(AreaSensor areaSensor, ReadingService readingService) {

        List<Reading> sensorReadings = readingService.findReadingsBySensorID(areaSensor.getId());

        Reading error = new Reading(0, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime(), "C", "null");
        if (sensorReadings.isEmpty()) {
            return error;
        }
        Reading recentReading = sensorReadings.get(0);
        Date mostRecent = recentReading.getDate();
        for (Reading r : sensorReadings) {
            Date testDate = r.getDate();
            if (testDate.after(mostRecent)) {
                mostRecent = testDate;
                recentReading = r;
            }
        }
        return recentReading;
    }


    /**
     * Searches within the list of sensors of a given type in a given geographic area for the distance to
     * the closest sensor the house.
     *
     * @return is the value of the distance of the house to sensor of the given type closest to it.
     */

    double getMinDistanceToSensorOfGivenType(List<AreaSensor> areaSensors, House house) {
        List<Double> arrayList = getSensorsDistanceToHouse(house, areaSensors);
        return Collections.min(arrayList);
    }


    /**
     * Goes through the sensor list, calculates sensors distance to house and
     * returns values in ArrayList.
     *
     * @param house to calculate closest distance
     * @return List of sensors distance to house
     */
    public List<Double> getSensorsDistanceToHouse(House house, List<AreaSensor> areaSensors) {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (AreaSensor areaSensor : areaSensors) {
            arrayList.add(house.calculateDistanceToSensor(areaSensor));
        }
        return arrayList;
    }

    public List<AreaSensor> getSensorsOfGivenType(List<AreaSensor> areaSensors, String sensorType) {
        List<AreaSensor> sensorsOfGivenType = new ArrayList<>();
        for (AreaSensor aS : areaSensors) {
            if (aS.getSensorType().getName().equals(sensorType)) {
                sensorsOfGivenType.add(aS);
            }

        }
        return sensorsOfGivenType;
    }
}